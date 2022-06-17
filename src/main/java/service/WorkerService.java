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
        List<WorkerModel> workerModelList = workerDAO.getList().stream()
                .map(WorkerModel::new).collect(Collectors.toList());
        List<Integer> dummyList = new ArrayList<Integer>();
        for(int i = 0; i < workerModelList.size(); i++) {
            dummyList.add(null);
        }
        DeletedIdList deletedIdList = new DeletedIdList(dummyList);
        return Arrays.asList(workerModelList, deletedIdList);
    }

    public String addWorker(WorkerModel workerModel) {
        Worker worker = new Worker();
        worker.setName(workerModel.getName());
        worker.setAddress(workerModel.getAddress());
        worker.setPhone(workerModel.getPhone());
        worker.setDob(workerModel.getDob());
        // default value
        worker.setStatus(true);
        if(workerDAO.addNew(worker)) {
            return "Thêm công nhân thành công!";
        }
        return "Thêm nhân công nhân bại, có lỗi xảy ra!";
    }

    public String deleteWorker(DeletedIdList list) {
        List<Worker> listWorker = new ArrayList<>();
        Worker tmp;
        for (Integer workerId :
                list.getList()) {
            if (workerId != null) {
                tmp = new Worker();
                tmp.setWorkerId(workerId);
                listWorker.add(tmp);
            }
        }

        if(workerDAO.deleteByListId(listWorker)) {
            return "Xoá công nhân thành công!";
        }
        return "Có lỗi xảy ra, vui lòng thử lại.";
    }

    public WorkerModel findWorkerById(Integer workerId) {
        Worker worker = new Worker();
        worker.setWorkerId(workerId);

        return new WorkerModel(workerDAO.findById(worker));
    }

    public String editWorker(WorkerModel workerModel) {
        Worker worker = new Worker();
        worker.setWorkerId(workerModel.getWorkerId());
        worker.setName(workerModel.getName());
        worker.setAddress(workerModel.getAddress());
        worker.setPhone(workerModel.getPhone());
        worker.setDob(workerModel.getDob());
        worker.setStatus(true);
        if(workerDAO.update(worker)) {
            return "Sửa công nhân thành công!";
        }
        return "Sửa công nhân thất bại!";
    }

    public WorkerModel getWorker(Integer workerId) {
        return new WorkerModel(workerDAO.findById(new Worker(workerId)));
    }
}
