package interceptor;

import model.AccountModel;
import model.RoleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import service.AccountService;
import service.EmployeeService;
import service.RoleService;
import utils.MyUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.Date;

public class GlobalInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    AccountService accountService;
    @Autowired
    RoleService roleService;
    @Transactional
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if(!SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser" )) {
            AccountModel accountModel = accountService.findAccountModelByEmail(
                            SecurityContextHolder.getContext().getAuthentication().getName());
            request.setAttribute("userInfo",accountModel.getEmployeeModel());

            //MANAGER AND EMPLOYEE
            request.setAttribute("role", roleService.findById(accountModel.getRoleId()).getRoleName());
            request.setAttribute("dateNow", MyUtils.formatDate(MyUtils.DF_DATE, new Date()));
        }
        return true;
    }
}
