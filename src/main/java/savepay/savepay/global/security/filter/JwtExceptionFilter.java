package savepay.savepay.global.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.filter.OncePerRequestFilter;
import savepay.savepay.global.ApiResponse;
import savepay.savepay.global.exception.GeneralException;

import java.io.IOException;
import java.nio.file.AccessDeniedException;

@RequiredArgsConstructor
public class JwtExceptionFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws ServletException, IOException {
        try {
            chain.doFilter(req, res);
        } catch (BadCredentialsException ex) {
            writeDefault(res);
            ApiResponse<Object> body = ApiResponse.onFailure(HttpStatus.UNAUTHORIZED.toString(), ex.getMessage(), null);
            writeBody(res, body);
        } catch (AccessDeniedException ex) {
            writeDefault(res);
            ApiResponse<Object> body = ApiResponse.onFailure(HttpStatus.FORBIDDEN.toString(), ex.getMessage(), null);
            writeBody(res, body);
        } catch (GeneralException ex) {
            writeDefault(res);
            ApiResponse<Object> body = ApiResponse.onFailure(ex.getErrorReasonHttpStatus().getCode(), ex.getErrorReasonHttpStatus().getMessage(), null);
            writeBody(res, body);
        }
    }

    private void writeDefault(HttpServletResponse res) {
        res.setContentType("application/json;charset=UTF-8");
    }

    private void writeBody(HttpServletResponse res, ApiResponse<Object> body) throws IOException {
        String json = objectMapper.writeValueAsString(body);
        res.getWriter().write(json);
    }

}
