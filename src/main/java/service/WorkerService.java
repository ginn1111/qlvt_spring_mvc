package service;

import dao.WorkerDAO;
import entity.Employee;
import entity.Worker;
import model.WorkerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import request_bean.DeletedIdList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class WorkerService implements Validation<WorkerModel> {
   @Autowired
    WorkerDAO workerDAO;

    public List<Object> searchWorker(String key) {
        List<WorkerModel> workerList = workerDAO.search(key).stream()
                .map(WorkerModel::new).collect(Collectors.toList());

        List<Integer> dummyList = new ArrayList<>();
        for(int i = 0; i < workerList.size(); i++) {
            dummyList.add(null);
        }
        DeletedIdList deletedIdEmployeeList = new DeletedIdList(dummyList);
        return Arrays.asList(workerList, deletedIdEmployeeList);
    }

    public List<Object> getWorkerList() {
        List<WorkerModel> workerList = workerDAO.getList().stream()
                .map(WorkerModel::new).collect(Collectors.toList());

        List<Integer> dummyList = new ArrayList<>();
        for(int i = 0; i < workerList.size(); i++) {
            dummyList.add(null);
        }
        DeletedIdList deletedIdEmployeeList = new DeletedIdList(dummyList);
        return Arrays.asList(workerList, deletedIdEmployeeList);
    }

    public String addWorker(WorkerModel workerModel) {
        String validStr = validate(workerModel);
        if(validStr != null) {
            return validStr;
        }
        Worker worker = new Worker();
        worker.setWorkerId(workerModel.getWorkerId());
        worker.setAddress(workerModel.getAddress());
        worker.setDob(workerModel.getDob());
        worker.setName(workerModel.getName());
        worker.setPhone(workerModel.getPhone());
        worker.setStatus(true);
        if(workerDAO.addNew(worker)) {
            return "Th??m c??ng nh??n th??nh c??ng";
        }
        return "Th??m c??ng nh??n th???t b???i!";
    }

    public WorkerModel findWorkerById(Integer workerId) {
        Worker worker = new Worker();
        worker.setWorkerId(workerId);
        return new WorkerModel(workerDAO.findById(worker));
    }

    public String editWorker(WorkerModel workerModel) {
        String validStr = validate(workerModel);
        if(validStr != null) {
            return validStr;
        }
        Worker worker = new Worker();
        worker.setWorkerId(workerModel.getWorkerId());
        worker.setName(workerModel.getName());
        worker.setDob(workerModel.getDob());
        worker.setStatus(true);
        worker.setAddress(workerModel.getAddress());
        worker.setPhone(workerModel.getPhone());
        if(workerDAO.update(worker)) {
            return "C???p nh???t th??nh c??ng!";
        }
        return "C???p nh???t th???t b???i!";
    }

    public String deleteWorker(DeletedIdList list) {
        List<Worker> workerList = new ArrayList<Worker>();
        Worker tmp;
        for (Integer workerId :
                list.getList()) {
            if (workerId != null) {
                tmp = new Worker();
                tmp.setWorkerId(workerId);
                workerList.add(tmp);
            }
        }

        if(workerDAO.deleteByListId(workerList)) {
            return "Xo?? c??ng nh??n th??nh c??ng!";
        }
        return "C??ng nh??n ???? ???????c l???p phi???u, kh??ng th??? xo??!";
    }

    public List<WorkerModel> getWorkerModelList() {
       return workerDAO.getList()
               .stream()
               .map(WorkerModel::new)
               .collect(Collectors.toList());
    }

    @Override
    public String validate(WorkerModel workerModel) {
        if(workerModel.getName().trim().length() == 0) {
            return "T??n kh??ng ???????c ????? tr???ng!";
        }
        String phone = workerModel.getPhone();
        if(phone != null && phone.trim().length() != 0) {
            phone = phone.trim();
            Pattern pattern = Pattern.compile(regexPhone);
            Matcher matcher = pattern.matcher(phone);
            if(!matcher.matches()) {
                return "S??? ??i???n tho???i kh??ng h???p l???";
            }
        }
        return null;
    }
}
