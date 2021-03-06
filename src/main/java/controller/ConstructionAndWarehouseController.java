package controller;


import model.ConstructionModel;
import model.WarehouseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import request_bean.DeletedIdList;
import service.ConstructionService;
import service.WarehouseService;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.List;

@Controller
@Transactional
public class ConstructionAndWarehouseController {

    private static String message = "";
    private static ConstructionModel constructionModel = new ConstructionModel();
    private static WarehouseModel warehouseModel = new WarehouseModel();
    private static String link;
    private static String btnTitle;
    private static boolean isSearchConstruction = false;
    private static boolean isSearchWarehouse = false;

    @Autowired
    ConstructionService constructionService;

    @Autowired
    WarehouseService warehouseService;

    @RequestMapping("kho-va-cong-trinh")
    public String index(ModelMap model, HttpSession session) {

        List<Object> resultConstructionService = null;
        List<Object> resultWarehouseService = null;

        if(isSearchConstruction) {
            isSearchConstruction = false;
            resultConstructionService = constructionService.searchConstruction((String) session.getAttribute("key"));
        } else {
             resultConstructionService = constructionService.getConstructionList();
        }

        if(isSearchWarehouse) {
            isSearchWarehouse = false;
            resultWarehouseService = warehouseService.searchWarehouse((String) session.getAttribute("key"));
        } else {
            resultWarehouseService = warehouseService.getWarehouseList();
        }

        List<ConstructionModel> constructionModelList = (List<ConstructionModel>)resultConstructionService.get(0);
        DeletedIdList deletedConstructionIdList = (DeletedIdList) resultConstructionService.get(1);

        List<WarehouseModel> warehouseModelList = (List<WarehouseModel>)resultWarehouseService.get(0);
        DeletedIdList deletedWarehouseIdList = (DeletedIdList) resultWarehouseService.get(1);

        model.addAttribute("constructionModelList", constructionModelList);
        model.addAttribute("deletedConstructionIdList", deletedConstructionIdList);
        model.addAttribute("warehouseModelList", warehouseModelList);
        model.addAttribute("deletedWarehouseIdList", deletedWarehouseIdList);
        model.addAttribute("constructionModel", constructionModel);
        model.addAttribute("warehouseModel", warehouseModel);
        model.addAttribute("link", link);
        model.addAttribute("btnTitle", btnTitle);
        model.addAttribute("message", message);
        message = "";
        return "common/kho-va-cong-trinh";
    }

    @RequestMapping(value="kho-va-cong-trinh/cong-trinh", params = "new")
    public String newConstruction() {

        constructionModel = new ConstructionModel();
        btnTitle = "Th??m";
        link="kho-va-cong-trinh/cong-trinh.htm?insert";
        return "redirect:/kho-va-cong-trinh.htm";
    }

    @RequestMapping(value="kho-va-cong-trinh/cong-trinh", params = "insert", method = RequestMethod.POST)
    public String addConstruction(@ModelAttribute("constructionModel") ConstructionModel constructionModel) {
        // Todo: validate
        message = constructionService.addConstruction(constructionModel);
        return "redirect:/kho-va-cong-trinh.htm";
    }

    @RequestMapping(value="kho-va-cong-trinh/cong-trinh/{constructionId}", params = "update")
    public String editConstruction(@PathVariable("constructionId") Integer constructionId) {
        constructionModel = constructionService.findConstructionById(constructionId);

        link = "kho-va-cong-trinh/cong-trinh.htm?update";
        btnTitle = "S???a";
        return "redirect:/kho-va-cong-trinh.htm";
    }

    @RequestMapping(value="kho-va-cong-trinh/cong-trinh", params = "update")
    public String editConstruction(@ModelAttribute("constructionModel") ConstructionModel constructionModel) {
        message = constructionService.editConstruction(constructionModel);
        return "redirect:/kho-va-cong-trinh.htm";
    }

    @RequestMapping(value="kho-va-cong-trinh/cong-trinh", params = "delete", method = RequestMethod.POST)
    public String deleteConstruction(@ModelAttribute("deletedConstructionIdList") DeletedIdList deletedConstructionIdList) {
        message = constructionService.deleteConstruction(deletedConstructionIdList);
        return "redirect:/kho-va-cong-trinh.htm";
    }

    @RequestMapping("kho-va-cong-trinh/cong-trinh")
    public String searchConstruction(@RequestParam("data") String data, HttpSession session) {
        if(data.trim().length() != 0) {
            isSearchConstruction = true;
            session.setAttribute("key", data.trim());
        }
        return "redirect:/kho-va-cong-trinh.htm";
    }


    @RequestMapping(value="kho-va-cong-trinh/kho", params = "new")
    public String newWarehouse() {
        warehouseModel = new WarehouseModel();
        btnTitle = "Th??m";
        link="kho-va-cong-trinh/kho.htm?insert";
        return "redirect:/kho-va-cong-trinh.htm";
    }

    @RequestMapping(value="kho-va-cong-trinh/kho", params = "insert", method = RequestMethod.POST)
    public String addWarehouse(@ModelAttribute("warehouseModel") WarehouseModel warehouseModel) {
        // Todo: validate
        message = warehouseService.addWarehouse(warehouseModel);
        return "redirect:/kho-va-cong-trinh.htm";
    }

    @RequestMapping(value="kho-va-cong-trinh/kho/{warehouseId}", params = "update")
    public String editWarehouse(@PathVariable("warehouseId") Integer warehouseId) {
        warehouseModel = warehouseService.findWarehouseById(warehouseId);

        link = "kho-va-cong-trinh/kho.htm?update";
        btnTitle = "S???a";
        return "redirect:/kho-va-cong-trinh.htm";
    }

    @RequestMapping(value="kho-va-cong-trinh/kho", params = "update")
    public String editWarehouse(@ModelAttribute("warehouseModel") WarehouseModel warehouseModel) {
        message = warehouseService.editWarehouse(warehouseModel);
        return "redirect:/kho-va-cong-trinh.htm";
    }

    @RequestMapping(value="kho-va-cong-trinh/kho", params = "delete", method = RequestMethod.POST)
    public String deleteWarehouse(@ModelAttribute("deletedWarehouseIdList") DeletedIdList deletedWarehouseIdList) {
        message = warehouseService.deleteWarehouse(deletedWarehouseIdList);
        return "redirect:/kho-va-cong-trinh.htm";
    }

    @RequestMapping("kho-va-cong-trinh/kho")
    public String searchWarehouse(@RequestParam("data") String data, HttpSession session) {
        if(data.trim().length() != 0) {
            isSearchWarehouse = true;
           session.setAttribute("key", data.trim());
        }
        return "redirect:/kho-va-cong-trinh.htm";
    }
}

