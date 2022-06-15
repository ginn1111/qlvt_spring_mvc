package controller;

import model.NhanVienModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import service.NhanVienService;

import java.util.List;

@Controller
@RequestMapping("nhanvien")
public class NhanVienController {

    @RequestMapping("index")
    public String index(ModelMap model) {

        return "nhanvien/dash-board";
    }
}
