package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import model.EmployeeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import service.*;

import javax.transaction.Transactional;
import java.nio.charset.StandardCharsets;

@Controller
@Transactional
@RequestMapping("nhanvien")
public class EmployeeController {

    @Autowired
    InCouponService inCouponService;
    @Autowired
    ExCouponService exCouponService;
    @Autowired
    BorrowedCouponService borrowedCouponService;
    @Autowired
    PayedCouponService payedCouponService;
    @Autowired
    TransCouponService transCouponService;
    @Autowired
    StatisticSupplyService statisticSupplyService;

    @RequestMapping("index")
    public String index(ModelMap model, @RequestAttribute("userInfo") EmployeeModel user) {

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String jsonInPort = null, jsonExPort = null;
        try {
            jsonInPort = new String(
                    ow.writeValueAsBytes(statisticSupplyService.getTop10InPortSupplyInMonth()),
                    StandardCharsets.UTF_8
            );
            jsonExPort = new String(
                    ow.writeValueAsBytes(statisticSupplyService.getTop10ExPortSupplyInMonth()),
                    StandardCharsets.UTF_8
            );
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        model.addAttribute("jsonInPort",jsonInPort);
        model.addAttribute("jsonExPort",jsonExPort);

        model.addAttribute("top10BrCouponMaturityInMonth", borrowedCouponService.getTopBrCouponModelMaturityInMonth(10));
        model.addAttribute("numOfInCPInMonth", inCouponService.getNumberOfCPOfEmpInMonth(user.getEmployeeId()));
        model.addAttribute("numOfExCPInMonth", exCouponService.getNumberOfCPOfEmpInMonth(user.getEmployeeId()));
        model.addAttribute("numOfBrCPInMonth", borrowedCouponService.getNumberOfCPOfEmpInMonth(user.getEmployeeId()));
        model.addAttribute("numOfPyCPInMonth", payedCouponService.getNumberOfCPOfEmpInMonth(user.getEmployeeId()));
        model.addAttribute("numOfTrCPInMonth", transCouponService.getNumberOfCPOfEmpInMonth(user.getEmployeeId()));
        return "nhanvien/dash-board";
    }
}


