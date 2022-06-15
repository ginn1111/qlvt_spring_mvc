package controller;

import entity.Account;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import service.TestService;

import javax.transaction.Transactional;
import java.util.List;

@Controller
public class TestController {
    @Autowired
    SessionFactory ss;

    @Autowired
    TestService testService;

    @Transactional
    @RequestMapping("test")
    public String test(ModelMap model) {
        List<Account> listAccount = ss.getCurrentSession().createQuery("FROM Account AS A").list();

        for (Account account:
             listAccount) {
            System.out.println(account.getIdRole() + " " + account.getPassword());
        }

        testService.testService();

        model.addAttribute("tests", listAccount);

        return "test";
    }
}
