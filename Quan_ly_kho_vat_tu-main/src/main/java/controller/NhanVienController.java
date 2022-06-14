package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("nhanvien")
public class NhanVienController {
    @RequestMapping("index")
    public String index() {

        return "nhanvien/index";
    }
}
