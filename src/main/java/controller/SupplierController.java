package controller;


import model.AccountModel;
import model.EmployeeModel;
import model.RoleModel;
import model.SupplierModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import request_bean.DeletedIdList;
import service.SupplierService;

import javax.transaction.Transactional;
import java.util.List;

@Controller
@Transactional
public class SupplierController {
    private static String message = "";
    private static SupplierModel supplierModel = new SupplierModel();
    private static String link;
    private static String btnTitle;

    @Autowired
    SupplierService supplierService;

    @RequestMapping("nha-cung-cap")
    public String index(ModelMap model) {
        List<Object> resultSupplierService = supplierService.getSupplierList();
        List<SupplierModel> supplierModelList = (List<SupplierModel>)resultSupplierService.get(0);
        DeletedIdList deletedSupplierIdList = (DeletedIdList) resultSupplierService.get(1);

        model.addAttribute("supplierModelList", supplierModelList);
        model.addAttribute("deletedSupplierIdList", deletedSupplierIdList);
        model.addAttribute("supplierModel", supplierModel);
        model.addAttribute("link", link);
        model.addAttribute("btnTitle", btnTitle);
        model.addAttribute("message", message);
        message = "";
        return "common/nha-cung-cap";
    }

    @RequestMapping(value="nha-cung-cap", params = "new")
    public String newSupplier() {

        supplierModel = new SupplierModel();
        btnTitle = "Thêm";
        link="nha-cung-cap.htm?insert";
        return "redirect:/nha-cung-cap.htm";
    }

    @RequestMapping(value="nha-cung-cap", params = "insert", method = RequestMethod.POST)
    public String addSupplier(@ModelAttribute("supplierModel") SupplierModel supplierModel) {
        // Todo: validate
        message = supplierService.addSupplier(supplierModel);
        System.out.println(supplierModel);
        return "redirect:/nha-cung-cap.htm";
    }

    @RequestMapping(value="nha-cung-cap/{supplierId}", params = "update")
    public String editSupplier(@PathVariable("supplierId") Integer supplierId) {
        supplierModel = supplierService.findSupplierById(supplierId);
        link = "nha-cung-cap.htm?update";
        btnTitle = "Sửa";
        return "redirect:/nha-cung-cap.htm";
    }

    @RequestMapping(value="nha-cung-cap", params = "update")
    public String editSupplier(@ModelAttribute("supplierModel") SupplierModel supplierModel) {
        message = supplierService.editSupplier(supplierModel);
        return "redirect:/nha-cung-cap.htm";
    }

    @RequestMapping(value="nha-cung-cap", params = "delete", method = RequestMethod.POST)
    public String deleteSupplier(@ModelAttribute("deletedSupplierIdList") DeletedIdList deletedSupplierIdList) {
        message = supplierService.deleteSupplier(deletedSupplierIdList);
        return "redirect:/nha-cung-cap.htm";
    }
}
