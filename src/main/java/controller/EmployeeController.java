package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("nhanvien")
public class EmployeeController {

    @RequestMapping("index")
    public String index(ModelMap model) {

        return "nhanvien/dash-board";
    }
}
