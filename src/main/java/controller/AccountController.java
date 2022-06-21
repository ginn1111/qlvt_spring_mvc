package controller;

import model.AccountModel;
import model.AccountModel;
import model.RoleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import request_bean.DeletedEmailList;
import service.AccountService;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Controller
@Transactional
@RequestMapping("quanly")
public class AccountController {

    private static String message = "";
    private static AccountModel accountModel = new AccountModel();
    private static String link;
    private static String btnTitle;
    private static boolean isSearch = false;
    @Autowired
    AccountService accountService;

    @RequestMapping("tai-khoan-nhan-vien")
    public String account(ModelMap model, HttpSession session) {

        List<Object> resultAccountService = null;

        if(isSearch) {
            resultAccountService = accountService.searchAccount((String) session.getAttribute("key"));
            isSearch = false;
        } else {
            resultAccountService = accountService.getAccountList();
        }

        List<AccountModel> accountModelList = (List<AccountModel>)resultAccountService.get(0);
        DeletedEmailList deletedIdList = (DeletedEmailList) resultAccountService.get(1);
        Map<Integer, RoleModel> roleModelMap = (Map<Integer, RoleModel>) resultAccountService.get(2);

        model.addAttribute("accountModelList", accountModelList);
        model.addAttribute("deletedAccountIdList", deletedIdList);
        model.addAttribute("accountModel", accountModel);
        model.addAttribute("roleModelMap", roleModelMap);
        model.addAttribute("link", link);
        model.addAttribute("btnTitle", btnTitle);
        model.addAttribute("message", message);
        message = "";
        return "quanly/tai-khoan-nhan-vien";
    }

    @RequestMapping(value="tai-khoan-nhan-vien", params = "delete", method = RequestMethod.POST)
    public String deleteSupply(@ModelAttribute("deletedAccountIdList") DeletedEmailList deletedIdList) {
        message = accountService.deleteAccounts(deletedIdList);
        return "redirect:/quanly/tai-khoan-nhan-vien.htm";
    }

    @RequestMapping(value = "tai-khoan-nhan-vien", params = "search")
    public String search(@RequestParam("data") String data, HttpSession session) {
        if(data.trim().length() != 0) {
           isSearch = true;
           session.setAttribute("key", data);
        }

        return "redirect:/quanly/tai-khoan-nhan-vien.htm";
    }
}
