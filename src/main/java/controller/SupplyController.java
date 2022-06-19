package controller;

import model.CategoryModel;
import model.SupplyModel;
import model.WorkerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import request_bean.DeletedIdList;
import service.CategoryService;
import service.SupplyService;

import javax.transaction.Transactional;
import java.util.List;

@Controller
@Transactional
public class SupplyController {

    private static String message = "";
    private static SupplyModel supplyModel = new SupplyModel();
    private static String link;
    private static String btnTitle;

    @Autowired
    SupplyService supplyService;
    @Autowired
    CategoryService categoryService;

    @RequestMapping("vat-tu")
    public String supply(ModelMap model) {

        List<Object> resultSupplyService = supplyService.getSupplyList();
        List<SupplyModel> supplyModelList = (List<SupplyModel>)resultSupplyService.get(0);
        DeletedIdList deletedIdList = (DeletedIdList) resultSupplyService.get(1);

        model.addAttribute("supplyModelList", supplyModelList);
        model.addAttribute("deletedSupplyIdList", deletedIdList);
        model.addAttribute("supplyModel", supplyModel);
        model.addAttribute("link", link);
        model.addAttribute("btnTitle", btnTitle);
        model.addAttribute("message", message);
        message = "";
        return "common/vat-tu";
    }

    @RequestMapping(value="vat-tu", params = "new")
    public String newSupply() {
        supplyModel = new SupplyModel();
        btnTitle = "Thêm";
        link="vat-tu.htm?insert";
        return "redirect:/vat-tu.htm";
    }

    @RequestMapping(value="vat-tu", params = "insert", method = RequestMethod.POST)
    public String addSupply(@ModelAttribute("supplyModel") SupplyModel supplyModel) {
        // Todo: validate
        System.out.println(supplyModel);
        message = supplyService.addSupply(supplyModel);
        return "redirect:/vat-tu.htm";
    }

    @RequestMapping(value="vat-tu/{supplyId}", params = "update")
    public String editSupply(@PathVariable("supplyId") Integer supplyId) {
        supplyModel = supplyService.findSupplyById(supplyId);
        link = "vat-tu.htm?update";
        btnTitle = "Sửa";
        return "redirect:/vat-tu.htm";
    }

    @RequestMapping(value="vat-tu", params = "update")
    public String editSupply(@ModelAttribute("supplyModel") SupplyModel supplyModel) {
        message = supplyService.editSupply(supplyModel);
        return "redirect:/vat-tu.htm";
    }

    @RequestMapping(value="vat-tu", params = "delete", method = RequestMethod.POST)
    public String deleteSupply(@ModelAttribute("deletedIdSupplyList") DeletedIdList deletedIdList) {
        message = supplyService.deleteSupply(deletedIdList);
        return "redirect:/vat-tu.htm";
    }

    @ModelAttribute("categoryModelList")
    public List<CategoryModel> getCategoryModelList() {
        return categoryService.getCategoryModelList();
    }
}
