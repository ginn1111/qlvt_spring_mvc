package controller;

import model.AccountModel;
import model.WorkerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import request_bean.DeletedIdList;
import service.WorkerService;
import utils.MyUtils;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Controller
@Transactional
public class WorkerController {

    private static String message = "";
    private static WorkerModel workerModel = new WorkerModel();
    private static String link;
    private static String btnTitle;

    private static boolean isSearch;

    @InitBinder
    public void customizeBinding (WebDataBinder binder) {
        MyUtils.DF_DATE.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(MyUtils.DF_DATE, false));
    }
    @Autowired
    WorkerService workerService;

    @RequestMapping("cong-nhan")
    public String worker(ModelMap model, HttpSession session) {

        List<Object> resultWorkerService = null;

        if(isSearch)  {
            isSearch = false;
            resultWorkerService = workerService.searchWorker((String) session.getAttribute("key"));
        } else {
            resultWorkerService = workerService.getWorkerList();
        }

        List<WorkerModel> workerModelList = (List<WorkerModel>)resultWorkerService.get(0);
        DeletedIdList deletedIdWorkerList = (DeletedIdList) resultWorkerService.get(1);

        model.addAttribute("workerModelList", workerModelList);
        model.addAttribute("deletedIdWorkerList", deletedIdWorkerList);
        model.addAttribute("workerModel", workerModel);
        model.addAttribute("link", link);
        model.addAttribute("btnTitle", btnTitle);
        model.addAttribute("message", message);
        message = "";
        return "common/cong-nhan";
    }

    // GET cong-nhan.htm?new
    @RequestMapping(value="cong-nhan", params = "new")
    public String newWorker() {
        workerModel = new WorkerModel();
        btnTitle = "Thêm";
        link="cong-nhan.htm?insert";
        return "redirect:/cong-nhan.htm";
    }

    // POST cong-nhan.htm?insert
    @RequestMapping(value="cong-nhan", params = "insert", method = RequestMethod.POST)
    public String addWorker(@ModelAttribute("workerModel") WorkerModel workerModel) {
        // Todo: validate
        message = workerService.addWorker(workerModel);
        return "redirect:/cong-nhan.htm";
    }

    // GET cong-nhan/{workerId}.htm?update
    @RequestMapping(value="cong-nhan/{workerId}", params = "update")
    public String editWorker(@PathVariable("workerId") Integer workerId) {
        workerModel = workerService.findWorkerById(workerId);
        link = "cong-nhan.htm?update";
        btnTitle = "Sửa";
        return "redirect:/cong-nhan.htm";
    }

    // GET cong-nhan.htm?update
    @RequestMapping(value="cong-nhan", params = "update")
    public String editWorker(@ModelAttribute("workerModel") WorkerModel workerModel) {
        message = workerService.editWorker(workerModel);
        return "redirect:/cong-nhan.htm";
    }

    // POST cong-nhan.htm?delete
    @RequestMapping(value="cong-nhan", params = "delete", method = RequestMethod.POST)
    public String deleteWorker(@ModelAttribute("deletedIdWorkerList") DeletedIdList deletedIdList) {
        message = workerService.deleteWorker(deletedIdList);
        return "redirect:/cong-nhan.htm";
    }

    @RequestMapping(value = "cong-nhan", params = "search")
    public String search(@RequestParam("data") String data, HttpSession session) {
        if(data.trim().length() !=0 ) {
            isSearch = true;
            session.setAttribute("key", data);
        }
        return "redirect:/cong-nhan.htm";
    }
}
