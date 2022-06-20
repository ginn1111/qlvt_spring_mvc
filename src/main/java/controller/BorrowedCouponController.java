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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@Transactional
@RequestMapping("phieu")
public class BorrowedCouponController {
    private static String message;
    private static String btnTitle;
    private static String link;
    private static BorrowedCouponModel brCouponModel = new BorrowedCouponModel();

    @Autowired
    BorrowedCouponService borrowedCouponService;
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
        binder.registerCustomEditor(Date.class, new CustomDateEditor(MyUtils.DF_DATE, true));
    }

    @RequestMapping("phieu-muon")
    public String index(ModelMap model, HttpSession httpSession,
            @RequestAttribute("role") RoleName role,
            @RequestAttribute("userInfo") EmployeeModel user
        ) {
            httpSession.setAttribute("couponType", "PHIEUMUON");

        List<Object> resultOfBrCouponService = null;

        if(role.equals(RoleName.EMPLOYEE)) {
            resultOfBrCouponService = borrowedCouponService.getBorrowedCouponListForEmp(user.getEmployeeId());
        } else if(role.equals(RoleName.MANAGER)) {
             resultOfBrCouponService = borrowedCouponService.getBorrowedCouponList();
        }

        List<BorrowedCouponModel> brCouponModelList = (List<BorrowedCouponModel>) resultOfBrCouponService.get(0);
        DeletedIdList deletedBrCPIdList = (DeletedIdList) resultOfBrCouponService.get(1);

        model.addAttribute("brCouponModel", brCouponModel);
        model.addAttribute("brCouponModelList", brCouponModelList);
        model.addAttribute("deletedBrCPIdList", deletedBrCPIdList);

        model.addAttribute("btnTitle", btnTitle);
        model.addAttribute("link", link);
        model.addAttribute("message", message);

        message = "";
        return "common/phieu";
    }

    @RequestMapping(value = "phieu-muon", params = "new")
    public String newBrCoupon(HttpSession session) {
        List<SupplyModel> supplyModelList = supplyService.getSupplyModelList();

        session.setAttribute("supplies", supplyModelList);

        List<DetailBorrowedCouponModel> detailBorrowedCouponModelList = new ArrayList<>();
        for (int i = 0; i < supplyModelList.size(); i++) {
            detailBorrowedCouponModelList.add(new DetailBorrowedCouponModel());
        }

        brCouponModel = new BorrowedCouponModel();
        brCouponModel.setDetailBorrowedCouponModelList(detailBorrowedCouponModelList);

        btnTitle = "Thêm";
        link = "phieu/phieu-muon.htm?insert";
        return "redirect:/phieu/phieu-muon.htm";
    }

    @RequestMapping(value = "phieu-muon", params = "insert", method = RequestMethod.POST)
    public String addBrCoupon(@ModelAttribute("brCouponModel") BorrowedCouponModel brCouponModel) {
        // Todo: validate
        message = borrowedCouponService.addBorrowedCoupon(brCouponModel);
        return "redirect:/phieu/phieu-muon.htm";
    }

    @RequestMapping(value = "phieu-muon/{brCPId}", params = "update")
    public String editBrCoupon(@PathVariable("brCPId") Integer brCPId, HttpSession session) {
        brCouponModel = borrowedCouponService.findBorrowedCouponId(brCPId);
        session.setAttribute("detailBorrowedCouponModelList", brCouponModel.getDetailBorrowedCouponModelList());
        btnTitle = "Sửa";
        link = "phieu/phieu-muon.htm?update";
        return "redirect:/phieu/phieu-muon.htm";
    }

    @RequestMapping(value = "phieu-muon", params = "update")
    public String editSupplier(@ModelAttribute("brCouponModel") BorrowedCouponModel brCouponModel) {
        brCouponModel.setWorkerModel(BorrowedCouponController.brCouponModel.getWorkerModel());
        brCouponModel.setConstructionModel(BorrowedCouponController.brCouponModel.getConstructionModel());
        message = borrowedCouponService.editBorrowedCoupon(brCouponModel);
        return "redirect:/phieu/phieu-muon.htm";
    }

    @RequestMapping(value = "phieu-muon", params = "delete", method = RequestMethod.POST)
    public String deleteBrCoupon(@ModelAttribute("deletedBrCPIdList") DeletedIdList deletedBrCPIdList) {
        message = borrowedCouponService.deleteCoupon(deletedBrCPIdList);
        return "redirect:/phieu/phieu-muon.htm";
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
