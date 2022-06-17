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

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("nhacungcap")
public class SupplierController {

    private static String message = "";
    private static SupplierModel supplierModel = new SupplierModel();
    private static String link;
    private static String btnTitle;

    @Autowired
    SupplierService supplierService;
    @RequestMapping("index")
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

    @RequestMapping(value="nhan-vien", params = "new")
    public String newSupplier() {

        supplierModel = new SupplierModel();
        btnTitle = "Thêm";
        link="nhacungcap/nhan-vien.htm?insert";
        return "redirect:/quanly/nhan-vien.htm";
    }

    @RequestMapping(value="nhan-vien", params = "insert", method = RequestMethod.POST)
    public String addSupplier(@ModelAttribute("supplierModel") SupplierModel supplierModel) {
        // Todo: validate
        message = supplierService.addSupplier(supplierModel);
        return "redirect:/quanly/nhan-vien.htm";
    }

    @RequestMapping(value="nhan-vien/{supplierId}", params = "update")
    public String editSupplier(@PathVariable("supplierId") Integer supplierId) {
        supplierModel = supplierService.findSupplierById(supplierId);

        link = "quanly/nhan-vien.htm?update";
        btnTitle = "Sửa";
        return "redirect:/quanly/nhan-vien.htm";
    }

    @RequestMapping(value="nhan-vien", params = "update")
    public String editSupplier(@ModelAttribute("supplierModel") SupplierModel supplierModel) {
        message = supplierService.editSupplier(supplierModel);
        return "redirect:/quanly/nhan-vien.htm";
    }

    // POST quanly/nhan-vien.htm?delete
    @RequestMapping(value="nhan-vien", params = "delete", method = RequestMethod.POST)
    public String deleteSupplier(@ModelAttribute("deletedSupplierIdList") DeletedIdList deletedSupplierIdList) {
        message = supplierService.deleteSupplier(deletedSupplierIdList);
        return "redirect:/quanly/nhan-vien.htm";
    }

}
