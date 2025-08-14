package savepay.savepay.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import savepay.savepay.global.security.domain.token.service.TokenService;
import savepay.savepay.global.security.filter.CustomJwtFilter;
import savepay.savepay.global.security.filter.JwtExceptionFilter;
import savepay.savepay.global.security.handler.CustomFailureHandler;
import savepay.savepay.global.security.handler.CustomSuccessHandler;
import savepay.savepay.global.security.service.CustomOAuth2UserService;

import java.util.List;

@EnableWebSecurity
@RequiredArgsConstructor
@Configuration
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;

    private final CustomSuccessHandler customSuccessHandler;

    private final CustomFailureHandler customFailureHandler;

    private final TokenService tokenService;

    private final ObjectMapper objectMapper;


    @Value("${spring.security.user.name}")
    private String swaggerAdminName;

    @Value("${spring.security.user.password}")
    private String swaggerAdminPassword;

    @Value("${spring.security.user.roles}")
    private String[] swaggerAdminRoles;
    /*
        Swagger 접속을 위한 위한 Security Config 입니다.
     */
    @Bean
    @Order(1)
    public SecurityFilterChain swaggerFilterChain(HttpSecurity http) throws Exception {
        http.securityMatcher("/swagger-ui/**", "/v3/api-docs/**", "/login", "/logout", "/test/**")
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers("/login", "/logout")
                                .permitAll()
                                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                                .anyRequest().hasRole("ADMIN")
                ).formLogin(Customizer.withDefaults())
                .sessionManagement(
                        session -> session.invalidSessionUrl("/login") // 세션 만료시 로그인 페이지로 이동
                                .maximumSessions(7))
                .csrf(
                        csrf -> csrf.disable())
                .userDetailsService(userDetailsService());// csrf 끄기



        return http.build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain testSecurityChain(HttpSecurity http) throws Exception {
        http.securityMatcher("/api/auth/**")
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().permitAll()
                ).csrf(
                        csrf -> csrf.disable()
                );

        return http.build();
    }

    @Bean
    @Order(3)
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.securityMatcher("/api/**", "/oauth2/**", "/oauth2Login/**", "/login/**")
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/oauth2Login/**", "/oauth2/**","/login/**", "/", "/error")
                        .permitAll()
                        .requestMatchers(
                                "/api/v1/auth/login",      // 프리플라이트/로그인 시작 허용
                                "/api/v1/auth/**",
                                "/api/token"
                        ).permitAll()
                        .anyRequest()
                        .authenticated())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(
                        csrf -> csrf.disable())
                .oauth2Login(oauth2 -> oauth2
                        .userInfoEndpoint(userinfo -> userinfo.userService(customOAuth2UserService))
                        .successHandler(customSuccessHandler)
                        .failureHandler(customFailureHandler));

        http.addFilterBefore(new JwtExceptionFilter(objectMapper), UsernamePasswordAuthenticationFilter.class);
        http.addFilterAfter(new CustomJwtFilter(tokenService), JwtExceptionFilter.class);
        return http.build();
    }

    @Bean
    @Order(4)
    public SecurityFilterChain defaultSecurityChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
                .csrf(csrf -> csrf.disable());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails admin = User.withUsername(swaggerAdminName).password(swaggerAdminPassword)
                .roles(swaggerAdminRoles).build();
        return new InMemoryUserDetailsManager(admin);
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/static/js/**", "/static/images/**", "/static/css/**","/static/scss/**");
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        // 개발용: 프론트 주소
        config.setAllowedOrigins(List.of("http://localhost:5173"));
        // 배포시: config.setAllowedOrigins(List.of("https://app.example.com"));
        config.setAllowedMethods(List.of("GET","POST","PUT","DELETE","PATCH","OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);
        config.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
