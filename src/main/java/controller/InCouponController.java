package controller;

import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import request_bean.DeletedIdList;
import service.*;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Controller
@Transactional
@RequestMapping("phieu")
public class InCouponController {
    private static String message;
    private static String btnTitle;
    private static String link;
    private static InCouponModel inCouponModel = new InCouponModel();

    @Autowired
    InCouponService inCouponService;
    @Autowired
    SupplyService supplyService;
    @Autowired
    SupplierService supplierService;

    @Autowired
    CouponStatusService couponStatusService;

    @RequestMapping("phieu-nhap")
    public String index(ModelMap model, HttpSession session,
                        @RequestAttribute("role") RoleName role,
                        @RequestAttribute("userInfo") EmployeeModel user
    ) {
        session.setAttribute("couponType", "PHIEUNHAP");

        List<Object> resultOfInCouponService = null;


        if(role.equals(RoleName.EMPLOYEE)) {
            resultOfInCouponService = inCouponService.getInCouponListForEmp(user.getEmployeeId());
        } else if(role.equals(RoleName.MANAGER)) {
            resultOfInCouponService = inCouponService.getInCouponModelList();
        }

        List<InCouponModel> inCouponModelList = (List<InCouponModel>) resultOfInCouponService.get(0);
        DeletedIdList deletedInCPIdList = (DeletedIdList) resultOfInCouponService.get(1);


        model.addAttribute("inCouponModel", inCouponModel);
        model.addAttribute("inCouponModelList", inCouponModelList);
        model.addAttribute("deletedInCPIdList", deletedInCPIdList);

        model.addAttribute("btnTitle", btnTitle);
        model.addAttribute("link", link);
        model.addAttribute("message", message);

        message = "";
        return "common/phieu";
    }

    @RequestMapping(value = "phieu-nhap", params = "new")
    public String newInCoupon(HttpSession session) {
        List<SupplyModel> supplyModelList = supplyService.getSupplyModelList();
        session.setAttribute("supplies", supplyModelList);
        List<DetailInCouponModel> detailInCouponModelList = new ArrayList<>();
        for (int i = 0; i < supplyModelList.size(); i++) {
            detailInCouponModelList.add(new DetailInCouponModel());
        }

        inCouponModel = new InCouponModel();
        inCouponModel.setDetailInCouponModelList(detailInCouponModelList);

        btnTitle = "Thêm";
        link = "phieu/phieu-nhap.htm?insert";
        return "redirect:/phieu/phieu-nhap.htm";
    }

    @RequestMapping(value = "phieu-nhap", params = "insert", method = RequestMethod.POST)
    public String addCoupon(@ModelAttribute("inCouponModel") InCouponModel inCouponModel) {
        // Todo: validate
        message = inCouponService.addInCoupon(inCouponModel);
        return "redirect:/phieu/phieu-nhap.htm";
    }

    @RequestMapping(value = "phieu-nhap/{iCPId}", params = "update")
    public String editInCoupon(@PathVariable("iCPId") Integer iCPId, HttpSession session) {
        inCouponModel = inCouponService.findInCouponId(iCPId);
        System.out.println(inCouponModel.getDetailInCouponModelList());
        session.setAttribute("detailInCouponModelList", inCouponModel.getDetailInCouponModelList());
        btnTitle = "Sửa";
        link = "phieu/phieu-nhap.htm?update";
        return "redirect:/phieu/phieu-nhap.htm";
    }

    @RequestMapping(value = "phieu-nhap", params = "update")
    public String editInCoupon(@ModelAttribute("inCouponModel") InCouponModel inCouponModel) {
        inCouponModel.setSupplierModel(InCouponController.inCouponModel.getSupplierModel());
        message = inCouponService.editInCoupon(inCouponModel);
        return "redirect:/phieu/phieu-nhap.htm";
    }

    @RequestMapping(value = "phieu-nhap", params = "delete", method = RequestMethod.POST)
    public String deleteInCoupon(@ModelAttribute("deletedInCPIdList") DeletedIdList deletedInCPIdList) {
        message = inCouponService.deleteCoupon(deletedInCPIdList);
        return "redirect:/phieu/phieu-nhap.htm";
    }

    @ModelAttribute("supplierList")
    public List<SupplierModel> getSupplierList() {
        return supplierService.supplierList();
    }

    @ModelAttribute("couponStatusList")
    public List<CouponStatusModel> couponStatusModelList() {
        return couponStatusService.getCouponStatusModelList();
    }

}