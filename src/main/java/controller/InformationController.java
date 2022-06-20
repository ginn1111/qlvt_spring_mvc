package controller;

import model.AccountModel;
import model.EmployeeModel;
import model.RoleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import request_bean.ChangeAccount;
import service.EmployeeService;
import service.InformationService;
import utils.MyUtils;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@Transactional
public class InformationController {

    @Autowired
    EmployeeService employeeService;
    @Autowired
    InformationService informationService;

    @InitBinder
    public void customizeBinding (WebDataBinder binder) {
        MyUtils.DF_DATE.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(MyUtils.DF_DATE, false));
    }

    private static String message;

    private static ChangeAccount changeAccount = new ChangeAccount();

    @RequestMapping("information")
    public String index(ModelMap model, @RequestAttribute("userInfo") EmployeeModel userInfo) {
        List<Object> accountModelListAndRoleModelMap = employeeService.getAccountModelListOfEmployee(userInfo.getEmployeeId());
        List<AccountModel>  accountModelList = (List<AccountModel>) accountModelListAndRoleModelMap.get(0);
        Map<Integer, RoleModel> roleModelMap = (Map<Integer, RoleModel>) accountModelListAndRoleModelMap.get(1);

        model.addAttribute("userInfo", userInfo);
        model.addAttribute("accountModelList", accountModelList);
        model.addAttribute("roleModelMap", roleModelMap);
        model.addAttribute("changeAccount", changeAccount);
        model.addAttribute("message", message);
        message = "";

       return "common/thong-tin-ca-nhan";
    }

    @RequestMapping(value="information", params = "update", method = RequestMethod.POST)
    public String update(@ModelAttribute("userInfo") EmployeeModel userInfo) {
        message = informationService.updateInformation(userInfo);
       return "redirect:/information.htm";
    }

    @RequestMapping(value="information/{email}", params = "change-password")
    public String getAccount(@PathVariable("email") String email) {
        changeAccount = new ChangeAccount(email);
        return "redirect:/information.htm";
    }

    @RequestMapping(value="information", params = "change-password", method = RequestMethod.POST)
    public String changePassword(@ModelAttribute("changeAccount") ChangeAccount changeAccount) {
        message = informationService.changePassword(changeAccount);
        return "redirect:/information.htm";
    }

}
