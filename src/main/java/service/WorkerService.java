package service;

import dao.WorkerDAO;
import entity.Worker;
import model.WorkerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import request_bean.DeletedIdList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkerService {
   @Autowired
    WorkerDAO workerDAO;

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
        Worker worker = new Worker();
        worker.setWorkerId(workerModel.getWorkerId());
        worker.setAddress(workerModel.getAddress());
        worker.setDob(workerModel.getDob());
        worker.setName(workerModel.getName());
        worker.setPhone(workerModel.getPhone());
        worker.setStatus(true);
        if(workerDAO.addNew(worker)) {
            return "Thêm công nhân thành công";
        }
        return "Thêm công nhân thất bại!";
    }

    public WorkerModel findWorkerById(Integer workerId) {
        return null;
    }

    public String editWorker(WorkerModel workerModel) {
        return null;
    }

    public String deleteWorker(DeletedIdList deletedIdList) {
        return null;
    }
}
