package controller;

import model.EmployeeModel;
import model.AccountModel;
import model.RoleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import request_bean.DeletedIdList;
import service.*;
import utils.MyUtils;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Controller
@Transactional
@RequestMapping("quanly")
public class ManagerController {
    private static String message = "";
    private static EmployeeModel employeeModel = new EmployeeModel();
    private static AccountModel accountModel = new AccountModel();
    private static String link;
    private static String btnTitle;
    private static boolean isSearch = false;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    RoleService roleService;
    @Autowired
    InCouponService inCouponService;
    @Autowired
    ExCouponService exCouponService;
    @Autowired
    BorrowedCouponService borrowedCouponService;
    @Autowired
    PayedCouponService payedCouponService;
    @Autowired
    TransCouponService transCouponService;
    @Autowired
    AccountService accountService;

    @InitBinder
    public void customizeBinding (WebDataBinder binder) {
        MyUtils.DF_DATE.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(MyUtils.DF_DATE, true));
    }
    @RequestMapping("index")
    public String index(ModelMap model) {

        model.addAttribute("numberOfInCP", inCouponService.getNumberOfCPInMonth());
        model.addAttribute("numberOfExCP", exCouponService.getNumberOfCPInMonth());
        model.addAttribute("numberOfBorrowedCP", borrowedCouponService.getNumberOfCPInMonth());
        model.addAttribute("numberOfPayedCP", payedCouponService.getNumberOfCPInMonth());
        model.addAttribute("numberOfTransCP", transCouponService.getNumberOfCPInMonth());
        return "quanly/dash-board";
    }

    // GET quanly/nhan-vien.htm
    @RequestMapping("nhan-vien")
    public String employee(ModelMap model, HttpSession session) {

        List<Object> resultEmployeeService = null;

        if(isSearch) {
           isSearch = false;
           resultEmployeeService = employeeService.searchEmployee((String) session.getAttribute("key"));
        } else {

            resultEmployeeService = employeeService.getEmployeeList();
        }

        List<EmployeeModel> employeeModelList = (List<EmployeeModel>)resultEmployeeService.get(0);
        DeletedIdList deletedIdEmployeeList = (DeletedIdList) resultEmployeeService.get(1);

        model.addAttribute("employeeModelList", employeeModelList);
        model.addAttribute("deletedIdEmployeeList", deletedIdEmployeeList);
        model.addAttribute("employeeModel", employeeModel);
        model.addAttribute("accountModel", accountModel);
        model.addAttribute("link", link);
        model.addAttribute("btnTitle", btnTitle);
        model.addAttribute("message", message);
        message = "";
        return "quanly/nhan-vien";
    }

    // GET quanly/nhan-vien.htm?new
    @RequestMapping(value="nhan-vien", params = "new")
    public String newEmployee() {

        employeeModel = new EmployeeModel();
        btnTitle = "Thêm";
        link="quanly/nhan-vien.htm?insert";
        return "redirect:/quanly/nhan-vien.htm";
    }

    // POST quanly/nhan-vien.htm?insert
    @RequestMapping(value="nhan-vien", params = "insert", method = RequestMethod.POST)
    public String addEmployee(@ModelAttribute("employeeModel") EmployeeModel employeeModel) {
        // Todo: validate
        message = employeeService.addEmployee(employeeModel);
        return "redirect:/quanly/nhan-vien.htm";
    }

    // GET quanly/nhan-vien/{employeeId}.htm?update
    @RequestMapping(value="nhan-vien/{employeeId}", params = "update")
    public String editEmployee(@PathVariable("employeeId") Integer employeeId) {
        employeeModel = employeeService.findEmployeeById(employeeId);
        link = "quanly/nhan-vien.htm?update";
        btnTitle = "Sửa";
        return "redirect:/quanly/nhan-vien.htm";
    }

    // GET quanly/nhan-vien.htm?update
    @RequestMapping(value="nhan-vien", params = "update", method = RequestMethod.POST)
    public String editEmployee(@ModelAttribute("employeeModel") EmployeeModel employeeModel) {
        message = employeeService.editEmployee(employeeModel);
        return "redirect:/quanly/nhan-vien.htm";
    }

    // POST quanly/nhan-vien.htm?delete
    @RequestMapping(value="nhan-vien", params = "delete", method = RequestMethod.POST)
    public String deleteEmployee(@ModelAttribute("deletedIdEmployeeList") DeletedIdList deletedIdList) {
        message = employeeService.deleteEmployee(deletedIdList);
        return "redirect:/quanly/nhan-vien.htm";
    }

    // GET quanly/nhan-vien/{employeeId}.htm?update
    @RequestMapping(value="nhan-vien/{employeeId}", params = "accounts")
    public String employeeAccount(@PathVariable("employeeId") Integer employeeId, HttpSession httpSession) {
        httpSession.setAttribute("employeeModel", employeeService.findEmployeeById(employeeId));
        httpSession.setAttribute("accountModelList", employeeService.getAccountModelListOfEmployee(employeeId).get(0));
        httpSession.setAttribute("accountMapRole", employeeService.getAccountModelListOfEmployee(employeeId).get(1));
        link = "quanly/nhan-vien.htm?accounts";
        return "redirect:/quanly/nhan-vien.htm";
    }

    @RequestMapping(value="nhan-vien", params = "accounts", method = RequestMethod.POST)
    public String employeeAccount(@ModelAttribute("accountModel") AccountModel accountModel) {
        System.out.println(accountModel.toString());
        message = accountService.addNewAccount(accountModel);
        return "redirect:/quanly/nhan-vien.htm";
    }

    @RequestMapping(value = "nhan-vien", params = "search")
    public String search(@RequestParam("data") String data, HttpSession session) {
        if(data.trim().length() != 0) {
            session.setAttribute("key", data);
            isSearch = true;
        }
        return "redirect:/quanly/nhan-vien.htm";
    }

    @ModelAttribute("roleList")
    public List<RoleModel> getRoleList() {
        return roleService.getRoleList();
    }
}
