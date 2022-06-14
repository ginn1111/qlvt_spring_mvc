package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("quanly")
public class QuanLyController {
    @RequestMapping("index")
    public String index() {
        return "quanly/index";
    }
}
