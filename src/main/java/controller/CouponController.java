package controller;

import model.DetailInCouponModel;
import model.InCouponModel;
import model.SupplierModel;
import model.SupplyModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import request_bean.DeletedIdList;
import request_bean.DetailInCouponModelList;
import service.CouponService;
import service.SupplierService;
import service.SupplyService;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Controller
@Transactional
public class CouponController {
    private static String message;
    private static String btnTitle;
    private static String link;
    private static InCouponModel inCouponModel = new InCouponModel();
    private static DetailInCouponModelList detailInCouponModelList;

    @Autowired
    CouponService couponService;
    @Autowired
    SupplyService supplyService;
    @Autowired
    SupplierService supplierService;

    @RequestMapping("phieu")
    public String index(ModelMap model) {
        List<Object> resultOfInCouponService = couponService.getInCouponModelList();
        List<InCouponModel> inCouponModelList = (List<InCouponModel>) resultOfInCouponService.get(0);
        DeletedIdList deletedInCPIdList = (DeletedIdList) resultOfInCouponService.get(1);

        model.addAttribute("inCouponModel", inCouponModel);
        model.addAttribute("inCouponModelList", inCouponModelList);
        model.addAttribute("deletedInCPIdList", deletedInCPIdList);
        model.addAttribute("detailInCouponModelList", detailInCouponModelList);
        model.addAttribute("btnTitle", btnTitle);
        model.addAttribute("link", link);
        model.addAttribute("message", message);

        message = "";
        return "common/phieu";
    }
    @RequestMapping(value="phieu/phieu-nhap", params = "new")
    public String newInCoupon(HttpSession session) {
        List<SupplyModel> supplyModelList = supplyService.getSupplyModelList();
        session.setAttribute("supplies", supplyModelList);
        List<DetailInCouponModel> list = new ArrayList<>();
        for(int i = 0; i < supplyModelList.size(); i++) {
            list.add(new DetailInCouponModel());
        }

        inCouponModel = new InCouponModel();
        detailInCouponModelList = new DetailInCouponModelList(list);
        inCouponModel.setDetailInCouponModelList(detailInCouponModelList);

        btnTitle = "Thêm";
        link="phieu/phieu-nhap.htm?insert";
        return "redirect:/phieu.htm";
    }

    @RequestMapping(value="phieu/phieu-nhap", params = "insert", method = RequestMethod.POST)
    public String addSupplier(@ModelAttribute("inCouponModel") InCouponModel inCouponModel) {
        // Todo: validate
        message = couponService.addInCoupon(inCouponModel);
        return "redirect:/phieu.htm";
    }

    @RequestMapping(value="phieu/phieu-nhap/{iCPId}", params = "update")
    public String editSupplier(@PathVariable("iCPId") Integer iCPId) {
        inCouponModel = couponService.findInCouponId(iCPId);
        btnTitle = "Sửa";
        link="phieu/phieu-nhap.htm?update";
        return "redirect:/phieu.htm";
    }

    @RequestMapping(value="phieu/phieu-nhap", params = "update")
    public String editSupplier(@ModelAttribute("inCouponModel") InCouponModel inCouponModel) {
        message = couponService.editInCoupon(inCouponModel);
        return "redirect:/phieu.htm";
    }

    @RequestMapping(value="phieu/phieu-nhap", params = "delete", method = RequestMethod.POST)
    public String deleteSupplier(@ModelAttribute("deletedInCPIdList") DeletedIdList deletedInCPIdList) {
        message = couponService.deleteCoupon(deletedInCPIdList);
        return "redirect:/phieu.htm";
    }

    @ModelAttribute("supplierList")
    public List<SupplierModel> getSupplierList() {
        return supplierService.supplierList();
    }

}
