package controller;

import model.AccountModel;
import model.WorkerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import request_bean.DeletedIdList;
import service.WorkerService;

import javax.transaction.Transactional;
import java.util.List;

@Controller
@Transactional
public class WorkerController {

    private static String message = "";
    private static WorkerModel workerModel = new WorkerModel();
    private static String link;
    private static String btnTitle;

    @Autowired
    WorkerService workerService;

    @RequestMapping("cong-nhan")
    public String employee(ModelMap model) {

        List<Object> resultWorkerService = workerService.getWorkerList();
        List<WorkerModel> workerModelList = (List<WorkerModel>)resultWorkerService.get(0);
        DeletedIdList deletedIdWorkerList = (DeletedIdList) resultWorkerService.get(1);

        model.addAttribute("workerModelList", workerModelList);
        model.addAttribute("deletedIdWorkerList", deletedIdWorkerList);
        model.addAttribute("workerModel", workerModel);
        model.addAttribute("link", link);
        model.addAttribute("btnTitle", btnTitle);
        model.addAttribute("message", message);
        message = "";
        return "cong-nhan";
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
    public String addEmployee(@ModelAttribute("workerModel") WorkerModel workerModel) {
        // Todo: validate
        message = workerService.addWorker(workerModel);
        return "redirect:/cong-nhan.htm";
    }

    // GET cong-nhan/{workerId}.htm?update
    @RequestMapping(value="cong-nhan/{workerId}", params = "update")
    public String editEmployee(@PathVariable("workerId") Integer workerId) {
        workerModel = workerService.findWorkerById(workerId);
        link = "cong-nhan.htm?update";
        btnTitle = "Sửa";
        return "redirect:/cong-nhan.htm";
    }

    // GET cong-nhan.htm?update
    @RequestMapping(value="cong-nhan", params = "update")
    public String editEmployee(@ModelAttribute("workerModel") WorkerModel workerModel) {
        message = workerService.editWorker(workerModel);
        return "redirect:/cong-nhan.htm";
    }

    // POST cong-nhan.htm?delete
    @RequestMapping(value="cong-nhan", params = "delete", method = RequestMethod.POST)
    public String deleteEmployee(@ModelAttribute("deletedIdWorkerList") DeletedIdList deletedIdList) {
        message = workerService.deleteWorker(deletedIdList);
        return "redirect:/cong-nhan.htm";
    }
}
