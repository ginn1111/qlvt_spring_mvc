package controller;

import model.CategoryModel;
import model.ConstructionModel;
import model.SectorModel;
import model.SectorModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import request_bean.DeletedIdList;
import service.CategoryService;
import service.SectorService;

import javax.transaction.Transactional;
import java.util.List;

@Controller
@Transactional
public class CategoryAndSectorController {

    private static String message = "";
    private static CategoryModel categoryModel = new CategoryModel();
    private static SectorModel sectorModel = new SectorModel();
    private static String link;
    private static String btnTitle;

    @Autowired
    CategoryService categoryService;

    @Autowired
    SectorService sectorService;


    @RequestMapping("danh-muc-va-khu-vuc")
    public String index(ModelMap model) {
        List<Object> resultCategoryService = categoryService.getCategoryList();
        List<CategoryModel> categoryModelList = (List<CategoryModel>)resultCategoryService.get(0);
        DeletedIdList deletedCategoryIdList = (DeletedIdList) resultCategoryService.get(1);

        List<Object> resultSectorService = sectorService.getSectorList();
        List<SectorModel> sectorModelList = (List<SectorModel>)resultSectorService.get(0);
        DeletedIdList deletedSectorIdList = (DeletedIdList) resultSectorService.get(1);

        model.addAttribute("categoryModelList", categoryModelList);
        model.addAttribute("deletedCategoryIdList", deletedCategoryIdList);
        model.addAttribute("sectorModelList", sectorModelList);
        model.addAttribute("deletedSectorIdList", deletedSectorIdList);
        model.addAttribute("categoryModel", categoryModel);
        model.addAttribute("sectorModel", sectorModel);
        model.addAttribute("link", link);
        model.addAttribute("btnTitle", btnTitle);
        model.addAttribute("message", message);
        message = "";
        return "common/danh-muc-va-khu-vuc";
    }

    @RequestMapping(value="danh-muc-va-khu-vuc/danh-muc", params = "new")
    public String newCategory() {

        categoryModel = new CategoryModel();
        btnTitle = "Thêm";
        link="danh-muc-va-khu-vuc/danh-muc.htm?insert";
        return "redirect:/danh-muc-va-khu-vuc.htm";
    }

    @RequestMapping(value="danh-muc-va-khu-vuc/danh-muc", params = "insert", method = RequestMethod.POST)
    public String addCategory(@ModelAttribute("categoryModel") CategoryModel categoryModel) {
        // Todo: validate
        message = categoryService.addCategory(categoryModel);
        return "redirect:/danh-muc-va-khu-vuc.htm";
    }

    @RequestMapping(value="danh-muc-va-khu-vuc/danh-muc/{categoryId}", params = "update")
    public String editCategory(@PathVariable("categoryId") Integer categoryId) {
        categoryModel = categoryService.findCategoryById(categoryId);

        link = "danh-muc-va-khu-vuc/danh-muc.htm?update";
        btnTitle = "Sửa";
        return "redirect:/danh-muc-va-khu-vuc.htm";
    }

    @RequestMapping(value="danh-muc-va-khu-vuc/danh-muc", params = "update")
    public String editCategory(@ModelAttribute("categoryModel") CategoryModel categoryModel) {
        message = categoryService.editCategory(categoryModel);
        return "redirect:/danh-muc-va-khu-vuc.htm";
    }

    @RequestMapping(value="danh-muc-va-khu-vuc/danh-muc", params = "delete", method = RequestMethod.POST)
    public String deleteCategory(@ModelAttribute("deletedCategoryIdList") DeletedIdList deletedCategoryIdList) {
        message = categoryService.deleteCategory(deletedCategoryIdList);
        return "redirect:/danh-muc-va-khu-vuc.htm";
    }



    @RequestMapping(value="danh-muc-va-khu-vuc/khu-vuc", params = "new")
    public String newSector() {
        sectorModel = new SectorModel();
        btnTitle = "Thêm";
        link="danh-muc-va-khu-vuc/khu-vuc.htm?insert";
        return "redirect:/danh-muc-va-khu-vuc.htm";
    }

    @RequestMapping(value="danh-muc-va-khu-vuc/khu-vuc", params = "insert", method = RequestMethod.POST)
    public String addSector(@ModelAttribute("sectorModel") SectorModel sectorModel) {
        // Todo: validate
        message = sectorService.addSector(sectorModel);
        return "redirect:/danh-muc-va-khu-vuc.htm";
    }

    @RequestMapping(value="danh-muc-va-khu-vuc/khu-vuc/{sectorId}", params = "update")
    public String editSector(@PathVariable("sectorId") Integer sectorId) {
        sectorModel = sectorService.findSectorById(sectorId);

        link = "danh-muc-va-khu-vuc/khu-vuc.htm?update";
        btnTitle = "Sửa";
        return "redirect:/danh-muc-va-khu-vuc.htm";
    }

    @RequestMapping(value="danh-muc-va-khu-vuc/khu-vuc", params = "update")
    public String editSector(@ModelAttribute("sectorModel") SectorModel sectorModel) {
        message = sectorService.editSector(sectorModel);
        return "redirect:/danh-muc-va-khu-vuc.htm";
    }

    @RequestMapping(value="danh-muc-va-khu-vuc/khu-vuc", params = "delete", method = RequestMethod.POST)
    public String deleteSector(@ModelAttribute("deletedSectorIdList") DeletedIdList deletedSectorIdList) {
        message = sectorService.deleteSector(deletedSectorIdList);
        return "redirect:/danh-muc-va-khu-vuc.htm";
    }

    @ModelAttribute("sectorModelList")
    public List<SectorModel> getSectorModelList() {
        return sectorService.getSectorModelList();
    }
}
