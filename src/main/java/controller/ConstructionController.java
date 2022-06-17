package controller;


import model.ConstructionModel;
import model.SupplierModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import request_bean.DeletedIdList;
import service.ConstructionService;

import javax.transaction.Transactional;
import java.util.List;

@Controller
@Transactional
public class ConstructionController {

    private static String message = "";
    private static ConstructionModel constructionModel = new ConstructionModel();
    private static String link;
    private static String btnTitle;

    @Autowired
    ConstructionService constructionService;
    @RequestMapping("index")
    public String index(ModelMap model) {
        List<Object> resultConstructionService = constructionService.getConstructionList();
        List<ConstructionModel> constructionModelList = (List<ConstructionModel>)resultConstructionService.get(0);
        DeletedIdList deletedConstructionIdList = (DeletedIdList) resultConstructionService.get(1);

        model.addAttribute("constructionModelList", constructionModelList);
        model.addAttribute("deletedConstructionIdList", deletedConstructionIdList);
        model.addAttribute("constructionModel", constructionModel);
        model.addAttribute("link", link);
        model.addAttribute("btnTitle", btnTitle);
        model.addAttribute("message", message);
        message = "";
        return "common/nha-cung-cap";
    }

    @RequestMapping(value="nhan-vien", params = "new")
    public String newConstruction() {

        constructionModel = new ConstructionModel();
        btnTitle = "Thêm";
        link="nhacungcap/nhan-vien.htm?insert";
        return "redirect:/quanly/nhan-vien.htm";
    }

    @RequestMapping(value="nhan-vien", params = "insert", method = RequestMethod.POST)
    public String addConstruction(@ModelAttribute("constructionModel") ConstructionModel constructionModel) {
        // Todo: validate
        message = constructionService.addConstruction(constructionModel);
        return "redirect:/quanly/nhan-vien.htm";
    }

    @RequestMapping(value="nhan-vien/{constructionId}", params = "update")
    public String editConstruction(@PathVariable("constructionId") Integer constructionId) {
        constructionModel = constructionService.findConstructionById(constructionId);

        link = "quanly/nhan-vien.htm?update";
        btnTitle = "Sửa";
        return "redirect:/quanly/nhan-vien.htm";
    }

    @RequestMapping(value="nhan-vien", params = "update")
    public String editSConstruction(@ModelAttribute("constructionModel") ConstructionModel constructionModel) {
        message = constructionService.editConstruction(constructionModel);
        return "redirect:/quanly/nhan-vien.htm";
    }

    // POST quanly/nhan-vien.htm?delete
    @RequestMapping(value="nhan-vien", params = "delete", method = RequestMethod.POST)
    public String deleteConstruction(@ModelAttribute("deletedSupplierIdList") DeletedIdList deletedConstructionIdList) {
        message = constructionService.deleteConstruction(deletedConstructionIdList);
        return "redirect:/quanly/nhan-vien.htm";
    }
}
