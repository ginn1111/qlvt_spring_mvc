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
public class PayedCouponController {
    private static String message;
    private static String btnTitle;
    private static String link;
    private static PayedCouponModel pyCouponModel = new PayedCouponModel();

    @Autowired
    PayedCouponService payedCouponService;
    @Autowired
    SupplyService supplyService;
    @Autowired
    WorkerService workerService;
    @Autowired
    BorrowedCouponService borrowedCouponService;

    @Autowired
    CouponStatusService couponStatusService;

    @InitBinder
    public void customizeBinding (WebDataBinder binder) {
        MyUtils.DF_DATE.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(MyUtils.DF_DATE, false));
    }

    @RequestMapping("phieu-tra")
    public String index(ModelMap model, HttpSession httpSession,
                        @RequestAttribute("role") RoleName role,
                        @RequestAttribute("userInfo") EmployeeModel user
    ) {
        httpSession.setAttribute("couponType", "PHIEUTRA");

        List<Object> resultOfPyCouponService = null;

        if(role.equals(RoleName.EMPLOYEE)) {
            resultOfPyCouponService = payedCouponService.getPayedCouponListForEmp(user.getEmployeeId());
        } else if(role.equals(RoleName.MANAGER)) {
            resultOfPyCouponService = payedCouponService.getPayedCouponList();
        }

        List<PayedCouponModel> pyCouponModelList = (List<PayedCouponModel>) resultOfPyCouponService.get(0);
        DeletedIdList deletedPyCPIdList = (DeletedIdList) resultOfPyCouponService.get(1);


        model.addAttribute("pyCouponModel", pyCouponModel);
        model.addAttribute("pyCouponModelList", pyCouponModelList);
        model.addAttribute("deletedPyCPIdList", deletedPyCPIdList);

        model.addAttribute("btnTitle", btnTitle);
        model.addAttribute("link", link);
        model.addAttribute("message", message);

        message = "";
        return "common/phieu";
    }

    @RequestMapping(value = "phieu-tra", params = "new")
    public String newPyCoupon(@RequestParam("brCouponId") Integer brCouponId, HttpSession session) {

        BorrowedCouponModel borrowedCouponModel = borrowedCouponService.findBorrowedCouponId(brCouponId);

        List<SupplyModel> supplyModelList = new ArrayList<>();

        Integer supplyIdTmp;
        SupplyModel supplyModelTmp;
        for (DetailBorrowedCouponModel detail: borrowedCouponModel.getDetailBorrowedCouponModelList()) {
            supplyIdTmp = detail.getSupplyModel().getSupplyId();
            supplyModelTmp = supplyService.findSupplyById(supplyIdTmp);
            if(supplyModelTmp.getQuantity() == 0) {
                continue;
            }
            supplyModelList.add(
                    new SupplyModel(
                        supplyIdTmp
                        ,detail.getSupplyModel().getName()
                        ,detail.getSupplyModel().getUnit()
                        ,Math.min(detail.getQuantity(), supplyModelTmp.getQuantity())
                    ));
        }

        session.setAttribute("supplies", supplyModelList);

        List<DetailPayedCouponModel> detailPayedCouponModelList = new ArrayList<>();

        for(int i = 0; i < supplyModelList.size(); i++) {
            detailPayedCouponModelList.add(new DetailPayedCouponModel());
        }

        pyCouponModel = new PayedCouponModel();
        pyCouponModel.setBorrowedCouponModel(borrowedCouponModel);
        pyCouponModel.setDetailPayedCouponModelList(detailPayedCouponModelList);

        btnTitle = "Thêm";
        link = "phieu/phieu-tra.htm?insert";
        return "redirect:/phieu/phieu-tra.htm";
    }

    @RequestMapping(value = "phieu-tra", params = "insert", method = RequestMethod.POST)
    public String addPyCoupon(@ModelAttribute("pyCouponModel") PayedCouponModel pyCouponModel) {
        // Todo: validate
        message = payedCouponService.addPayedCoupon(pyCouponModel);
        return "redirect:/phieu/phieu-tra.htm";
    }

    @RequestMapping(value = "phieu-tra/{pyCPId}", params = "update")
    public String editPyCoupon(@PathVariable("pyCPId") Integer pyCPId, HttpSession session) {
        pyCouponModel = payedCouponService.findPayedCouponId(pyCPId);
        session.setAttribute("detailPayedCouponModelList", pyCouponModel.getDetailPayedCouponModelList());
        btnTitle = "Sửa";
        link = "phieu/phieu-tra.htm?update";
        return "redirect:/phieu/phieu-tra.htm";
    }

    @RequestMapping(value = "phieu-tra", params = "update")
    public String editSupplier(@ModelAttribute("pyCouponModel") PayedCouponModel pyCouponModel) {
        pyCouponModel.setWorkerModel(PayedCouponController.pyCouponModel.getWorkerModel());
        pyCouponModel.setBorrowedCouponModel(PayedCouponController.pyCouponModel.getBorrowedCouponModel());
        message = payedCouponService.editPayedCoupon(pyCouponModel);
        return "redirect:/phieu/phieu-tra.htm";
    }

    @RequestMapping(value = "phieu-tra", params = "delete", method = RequestMethod.POST)
    public String deletePyCoupon(@ModelAttribute("deletedPyCPIdList") DeletedIdList deletedPyCPIdList) {
        message = payedCouponService.deleteCoupon(deletedPyCPIdList);
        return "redirect:/phieu/phieu-tra.htm";
    }

    @ModelAttribute("workerList")
    public List<WorkerModel> workerModelList() {
        return workerService.getWorkerModelList();
    }

    @ModelAttribute("couponStatusList")
    public List<CouponStatusModel> couponStatusModelList() {
        return couponStatusService.getCouponStatusModelList();
    }

    @ModelAttribute("borrowedCouponList")
    public List<BorrowedCouponModel> borrowedCouponModelList() {
        return borrowedCouponService.getBorrowedCouponModelListNotCompOrNotPayed();
    }
}
