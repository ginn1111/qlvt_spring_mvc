package auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

public class MyAuthenSucessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authenticate)
            throws IOException, ServletException {
        Set<String> roles = AuthorityUtils.authorityListToSet(authenticate.getAuthorities());
        if(roles.contains("ROLE_1")) {
            response.sendRedirect(request.getContextPath() + "/quanly/index.htm");
            return;
        }
        response.sendRedirect(request.getContextPath() + "/nhanvien/index.htm");
    }
}
