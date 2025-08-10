package savepay.savepay.global.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import savepay.savepay.global.security.domain.service.TokenService;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomJwtFilter extends OncePerRequestFilter {

    private final TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = request.getHeader("Authorization");

        if (token != null) {
            UsernamePasswordAuthenticationToken authenticationToken = tokenService.getAuthenticationToken(token);
            SecurityContextHolder.getContextHolderStrategy().getContext().setAuthentication(authenticationToken);
        }

        filterChain.doFilter(request, response);
    }


}
