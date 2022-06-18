package interceptor;

import model.AccountModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import service.AccountService;
import service.EmployeeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

public class GlobalInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    AccountService accountService;
    @Transactional
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if(!SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser" )) {
            request.setAttribute("userInfo",
                accountService.findAccountModelByEmail(
                    SecurityContextHolder.getContext().getAuthentication().getName()).getEmployeeModel()
            );
        }
        return true;
    }
}
