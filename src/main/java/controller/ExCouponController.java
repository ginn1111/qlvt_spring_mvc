package controller;

import model.*;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@Transactional
@RequestMapping("phieu")
public class ExCouponController {

    private static String message;
    private static String btnTitle;
    private static String link;
    private static ExCouponModel exCouponModel = new ExCouponModel();

    @Autowired
    ExCouponService exCouponService;
    @Autowired
    SupplyService supplyService;
    @Autowired
    WorkerService workerService;
    @Autowired
    ConstructionService constructionService;
    @Autowired
    CouponStatusService couponStatusService;

    @InitBinder
    public void customizeBinding (WebDataBinder binder) {
        MyUtils.DF_DATE.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(MyUtils.DF_DATE, false));
    }

    @RequestMapping("phieu-xuat")
    public String index(ModelMap model, HttpSession session,
                @RequestAttribute("role") RoleName role,
                @RequestAttribute("userInfo") EmployeeModel user
    ) {
        session.setAttribute("couponType", "PHIEUXUAT");

        List<Object> resultOfExCouponService = null;

        if(role.equals(RoleName.EMPLOYEE)) {
            resultOfExCouponService = exCouponService.getExCouponListForEmp(user.getEmployeeId());
        } else if(role.equals(RoleName.MANAGER)) {
            resultOfExCouponService = exCouponService.getExCouponList();
        }

        List<ExCouponModel> exCouponModelList = (List<ExCouponModel>) resultOfExCouponService.get(0);
        DeletedIdList deletedExCPIdList = (DeletedIdList) resultOfExCouponService.get(1);


        model.addAttribute("exCouponModel", exCouponModel);
        model.addAttribute("exCouponModelList", exCouponModelList);
        model.addAttribute("deletedExCPIdList", deletedExCPIdList);

        model.addAttribute("btnTitle", btnTitle);
        model.addAttribute("link", link);
        model.addAttribute("message", message);

        message = "";
        return "common/phieu";
    }

    @RequestMapping(value = "phieu-xuat", params = "new")
    public String newExCoupon(HttpSession session) {
        List<SupplyModel> supplyModelList = supplyService.getSupplyModelList();
        session.setAttribute("supplies", supplyModelList);

        List<DetailExCouponModel> detailExCouponModelList = new ArrayList<>();
        for (int i = 0; i < supplyModelList.size(); i++) {
            detailExCouponModelList.add(new DetailExCouponModel());
        }

        exCouponModel = new ExCouponModel();
        exCouponModel.setDetailExCouponModelList(detailExCouponModelList);

        btnTitle = "Thêm";
        link = "phieu/phieu-xuat.htm?insert";
        return "redirect:/phieu/phieu-xuat.htm";
    }

    @RequestMapping(value = "phieu-xuat", params = "insert", method = RequestMethod.POST)
    public String addCoupon(@ModelAttribute("exCouponModel") ExCouponModel exCouponModel) {
        // Todo: validate
        message = exCouponService.addExCoupon(exCouponModel);
        return "redirect:/phieu/phieu-xuat.htm";
    }

    @RequestMapping(value = "phieu-xuat/{exCPId}", params = "update")
    public String editExCoupon(@PathVariable("exCPId") Integer exCPId, HttpSession session) {
        exCouponModel = exCouponService.findExCouponId(exCPId);
        System.out.println(exCouponModel.getDetailExCouponModelList());
        session.setAttribute("detailExCouponModelList", exCouponModel.getDetailExCouponModelList());
        btnTitle = "Sửa";
        link = "phieu/phieu-xuat.htm?update";
        return "redirect:/phieu/phieu-xuat.htm";
    }

    @RequestMapping(value = "phieu-xuat", params = "update")
    public String editExCoupon(@ModelAttribute("exCouponModel") ExCouponModel exCouponModel) {
        exCouponModel.setWorkerModel(ExCouponController.exCouponModel.getWorkerModel());
        exCouponModel.setConstructionModel(ExCouponController.exCouponModel.getConstructionModel());
        message = exCouponService.editExCoupon(exCouponModel);
        return "redirect:/phieu/phieu-xuat.htm";
    }

    @RequestMapping(value = "phieu-xuat", params = "delete", method = RequestMethod.POST)
    public String deleteExCoupon(@ModelAttribute("deletedExCPIdList") DeletedIdList deletedExCPIdList) {
        message = exCouponService.deleteCoupon(deletedExCPIdList);
        return "redirect:/phieu/phieu-xuat.htm";
    }

    @ModelAttribute("workerList")
    public List<WorkerModel> workerModelList() {
        return workerService.getWorkerModelList();
    }

    @ModelAttribute("constructionList")
    public List<ConstructionModel> constructionModelList() {
        return constructionService.getConstructionModelList();
    }

    @ModelAttribute("couponStatusList")
    public List<CouponStatusModel> couponStatusModelList() {
        return couponStatusService.getCouponStatusModelList();
    }
}
