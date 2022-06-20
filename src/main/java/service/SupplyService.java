package service;

import dao.SupplyDAO;
import dao.WorkerDAO;
import entity.Category;
import entity.Supply;
import entity.Worker;
import model.SupplyModel;
import model.WorkerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import request_bean.DeletedIdList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplyService {
    @Autowired
    SupplyDAO supplyDAO;

    public List<Object> searchSupply(String key) {
        List<SupplyModel> supplyModelList = supplyDAO.search(key).stream()
                        .map(SupplyModel::new)
                        .collect(Collectors.toList());

        List<Integer> dummyList = new ArrayList<>();
        for(int i = 0; i < supplyModelList.size(); i++) {
            dummyList.add(null);
        }
        DeletedIdList deletedSupplyIdList = new DeletedIdList(dummyList);
        return Arrays.asList(supplyModelList, deletedSupplyIdList);
    }
    public List<Object> getSupplyList() {
        List<SupplyModel> supplyModelList = supplyDAO.getList().stream()
                .map(SupplyModel::new).collect(Collectors.toList());

        List<Integer> dummyList = new ArrayList<>();
        for(int i = 0; i < supplyModelList.size(); i++) {
            dummyList.add(null);
        }
        DeletedIdList deletedSupplyIdList = new DeletedIdList(dummyList);
        return Arrays.asList(supplyModelList, deletedSupplyIdList);
    }

    public String addSupply(SupplyModel supplyModel) {
        Supply supply = new Supply();

        supply.setName(supplyModel.getName());
        supply.setCategory(new Category(supplyModel.getCategoryModel().getCategoryId()));
        supply.setImage(supplyModel.getImage());
        supply.setProducer(supplyModel.getProducer());
        supply.setQuantity(supplyModel.getQuantity());
        supply.setUnit(supplyModel.getUnit());
        supply.setStatus(true);
        if(supplyDAO.addNew(supply)) {
            return "Thêm vật tư thành công";
        }
        return "Thêm vật tư thất bại!";
    }

    public SupplyModel findSupplyById(Integer supplyId) {
        Supply supply = new Supply();
        supply.setSupplyId(supplyId);
        return new SupplyModel(supplyDAO.findById(supply));
    }

    public String editSupply(SupplyModel supplyModel) {
        Supply supply = new Supply();

        supply.setSupplyId(supplyModel.getSupplyId());
        supply.setName(supplyModel.getName());
        supply.setCategory(new Category(supplyModel.getCategoryModel().getCategoryId()));
        supply.setImage(supplyModel.getImage());
        supply.setProducer(supplyModel.getProducer());
        supply.setQuantity(supplyModel.getQuantity());
        supply.setUnit(supplyModel.getUnit());
        supply.setStatus(true);

        if(supplyDAO.update(supply)) {
            return "Cập nhật thành công!";
        }
        return "Cập nhật thất bại!";
    }

    public String deleteSupply(DeletedIdList list) {
        List<Supply> supplyList = new ArrayList<>();
        Supply tmp;
        for (Integer supplyId :
                list.getList()) {
            if (supplyId != null) {
                tmp = new Supply();
                tmp.setSupplyId(supplyId);
                supplyList.add(tmp);
            }
        }

        if(supplyDAO.deleteByListId(supplyList)) {
            return "Xoá vật tư thành công!";
        }
        return "Có lỗi xảy ra, vui lòng thử lại.";
    }
    public List<SupplyModel> getSupplyModelList() {
        return supplyDAO.getList()
            .stream()
            .map(SupplyModel::new)
            .collect(Collectors.toList());
    }
}
