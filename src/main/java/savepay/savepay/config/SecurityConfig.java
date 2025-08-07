package savepay.savepay.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import savepay.savepay.global.security.filter.CustomJwtFilter;
import savepay.savepay.global.security.handler.CustomFailureHandler;
import savepay.savepay.global.security.handler.CustomSuccessHandler;
import savepay.savepay.global.security.service.CustomOAuth2UserService;

@EnableWebSecurity
@RequiredArgsConstructor
@Configuration
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;

    private final CustomSuccessHandler customSuccessHandler;

    private final CustomFailureHandler customFailureHandler;

    private final CustomJwtFilter customJwtFilter;
    /*
        Swagger 접속을 위한 위한 Security Config 입니다.
     */
    @Bean
    @Order(1)
    public SecurityFilterChain swaggerFilterChain(HttpSecurity http) throws Exception {
        http.securityMatcher("/swagger-ui/**", "/login", "/logout")
                .authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers("/login", "/logout")
                                .permitAll()
                                .anyRequest().permitAll() // 임시
//                                .anyRequest().hasRole("ADMIN") // swagger-ui 접근은 admin 이상 권한 요구
                ).formLogin(Customizer.withDefaults())
                .sessionManagement(
                        session -> session.invalidSessionUrl("/login") // 세션 만료시 로그인 페이지로 이동
                                .maximumSessions(7)) // ADMIN 로그인 4명까지 가능 (스터디원 4명)
                .csrf(
                        csrf -> csrf.disable())// csrf 끄기
                .userDetailsService(userDetailsService());



        return http.build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.securityMatcher("/api/**", "/oauth2/**", "/oauth2Login/**", "/login/**")
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/oauth2Login/**", "/oauth2/**","/login/**", "/", "/error")
                        .permitAll()
                        .anyRequest()
                        .authenticated())
                .csrf(
                        csrf -> csrf.disable())
                .oauth2Login(oauth2 -> oauth2
                        .userInfoEndpoint(userinfo -> userinfo.userService(customOAuth2UserService))
                        .successHandler(customSuccessHandler)
                        .failureHandler(customFailureHandler));
//                .formLogin(Customizer.withDefaults()); // 임시 테스트 용으로, 일단 swagger 제외한 모든 api 제한없이 접근 가능
//        http.oauth2Client(Customizer.withDefaults());
        http.addFilterBefore(customJwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


    @Bean
    @Order(3)
    public SecurityFilterChain testSecurityChain(HttpSecurity http) throws Exception {
        http.securityMatcher("/test/**")
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().permitAll()
                ).csrf(
                        csrf -> csrf.disable()
                );

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

    /*
        임시 유저 정보들...
     */
    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails user = User.withUsername("user").password("{noop}1111").roles("USER").build();
        UserDetails admin = User.withUsername("admin").password("{noop}1111").roles("ADMIN").build();
        return new InMemoryUserDetailsManager(user, admin);
    }

    /*
        role 간의 계층적 구조 추가를 위하여 Hierarchy를 추가합니다.
        상위 role은 하위 role의 권한이 필요하더라도 접근할 수 있습니다.
        (Hierarchy 설정을 안하면 접근 못합니다!)
     */
    @Bean
    public RoleHierarchy roleHierarchy() {
        return RoleHierarchyImpl.fromHierarchy(
                "ROLE_ADMIN > ROLE_USER"
        );
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/static/js/**", "/static/images/**", "/static/css/**","/static/scss/**");
    }



}

