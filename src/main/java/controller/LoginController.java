package controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    @RequestMapping("login")
    public String loginFailed(ModelMap model, @RequestParam("error") boolean error){
        if(error){
            model.addAttribute("message", "Sai thông tin đăng nhập");
        }
        return "../../index";
    }
}
