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
public class TransCouponController {
    private static String message;
    private static String btnTitle;
    private static String link;
    private static TransCouponModel trCouponModel = new TransCouponModel();

    @Autowired
    TransCouponService transCouponService;
    @Autowired
    SupplyService supplyService;
    @Autowired
    WarehouseService warehouseService;

    @Autowired
    CouponStatusService couponStatusService;

    @InitBinder
    public void customizeBinding (WebDataBinder binder) {
        MyUtils.DF_DATE.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(MyUtils.DF_DATE, false));
    }

    @RequestMapping("phieu-chuyen-kho")
    public String index(ModelMap model, HttpSession httpSession) {
        httpSession.setAttribute("couponType", "PHIEUCHUYENKHO");

        List<Object> resultOfTrCouponService = transCouponService.getTransCouponList();
        List<TransCouponModel> trCouponModelList = (List<TransCouponModel>) resultOfTrCouponService.get(0);
        DeletedIdList deletedTrCPIdList = (DeletedIdList) resultOfTrCouponService.get(1);


        model.addAttribute("trCouponModel", trCouponModel);
        model.addAttribute("trCouponModelList", trCouponModelList);
        model.addAttribute("deletedTrCPIdList", deletedTrCPIdList);

        model.addAttribute("btnTitle", btnTitle);
        model.addAttribute("link", link);
        model.addAttribute("message", message);

        message = "";
        return "common/phieu";
    }

    @RequestMapping(value = "phieu-chuyen-kho", params = "new")
    public String newTrCoupon(HttpSession session) {
        List<SupplyModel> supplyModelList = supplyService.getSupplyModelList();

        session.setAttribute("supplies", supplyModelList);

        List<DetailTransCouponModel> detailTransCouponModelList = new ArrayList<>();
        for (int i = 0; i < supplyModelList.size(); i++) {
            detailTransCouponModelList.add(new DetailTransCouponModel());
        }

        trCouponModel = new TransCouponModel();
        trCouponModel.setDetailTransCouponModelList(detailTransCouponModelList);

        btnTitle = "Thêm";
        link = "phieu/phieu-chuyen-kho.htm?insert";
        return "redirect:/phieu/phieu-chuyen-kho.htm";
    }

    @RequestMapping(value = "phieu-chuyen-kho", params = "insert", method = RequestMethod.POST)
    public String addTrCoupon(@ModelAttribute("trCouponModel") TransCouponModel trCouponModel) {
        // Todo: validate
        message = transCouponService.addTransCoupon(trCouponModel);
        return "redirect:/phieu/phieu-chuyen-kho.htm";
    }

    @RequestMapping(value = "phieu-chuyen-kho/{brCPId}", params = "update")
    public String editTrCoupon(@PathVariable("brCPId") Integer brCPId, HttpSession session) {
        trCouponModel = transCouponService.findTransCouponId(brCPId);
        session.setAttribute("detailTransCouponModelList", trCouponModel.getDetailTransCouponModelList());
        btnTitle = "Sửa";
        link = "phieu/phieu-chuyen-kho.htm?update";
        return "redirect:/phieu/phieu-chuyen-kho.htm";
    }

    @RequestMapping(value = "phieu-chuyen-kho", params = "update")
    public String editSupplier(@ModelAttribute("trCouponModel") TransCouponModel trCouponModel) {
        message = transCouponService.editTransCoupon(trCouponModel);
        return "redirect:/phieu/phieu-chuyen-kho.htm";
    }

    @RequestMapping(value = "phieu-chuyen-kho", params = "delete", method = RequestMethod.POST)
    public String deleteTrCoupon(@ModelAttribute("deletedTrCPIdList") DeletedIdList deletedTrCPIdList) {
        message = transCouponService.deleteCoupon(deletedTrCPIdList);
        return "redirect:/phieu/phieu-chuyen-kho.htm";
    }

    @ModelAttribute("warehouseList")
    public List<WarehouseModel> warehouseModelList() {
        return warehouseService.getWorkerModelList();
    }

    @ModelAttribute("couponStatusList")
    public List<CouponStatusModel> couponStatusModelList() {
        return couponStatusService.getCouponStatusModelList();
    }
}

