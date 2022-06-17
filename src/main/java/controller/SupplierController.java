package controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("nhacungcap")
public class SupplierController {

    @RequestMapping("index")
    public String index(ModelMap model) {

        return "nhacungcap/dash-board";
    }
}
