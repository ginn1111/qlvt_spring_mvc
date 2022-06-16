package controller;

import entity.NhanVien;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.transaction.Transactional;
import java.util.List;


@Controller
@Transactional
public class TestController {
    @Autowired
    SessionFactory sessionFactory;
    @RequestMapping("test")
    public String test(ModelMap model) {
        List<NhanVien> list = (List<NhanVien>) sessionFactory.getCurrentSession().createQuery("FROM NhanVien").list();
        System.out.println(list.size());

        return "test";
    }
}
