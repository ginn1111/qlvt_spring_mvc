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
public class SupplyService implements Validation<SupplyModel> {
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
        String validStr = validate(supplyModel);
        if(validStr != null)  {
            return validStr;
        }
        Supply supply = new Supply();

        supply.setName(supplyModel.getName());
        supply.setCategory(new Category(supplyModel.getCategoryModel().getCategoryId()));
        supply.setImage(supplyModel.getImage());
        supply.setProducer(supplyModel.getProducer());
        supply.setQuantity(supplyModel.getQuantity());
        supply.setUnit(supplyModel.getUnit());
        supply.setStatus(true);
        if(supplyDAO.addNew(supply)) {
            return "Th??m v???t t?? th??nh c??ng";
        }
        return "Th??m v???t t?? th???t b???i!";
    }

    public SupplyModel findSupplyById(Integer supplyId) {
        Supply supply = new Supply();
        supply.setSupplyId(supplyId);
        return new SupplyModel(supplyDAO.findById(supply));
    }

    public String editSupply(SupplyModel supplyModel) {
        String validStr = validate(supplyModel);
        if(validStr != null)  {
            return validStr;
        }
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
            return "C???p nh???t th??nh c??ng!";
        }
        return "C???p nh???t th???t b???i!";
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
            return "Xo?? v???t t?? th??nh c??ng!";
        }
        return "C?? l???i x???y ra, vui l??ng th??? l???i.";
    }
    public List<SupplyModel> getSupplyModelList() {
        return supplyDAO.getList()
            .stream()
            .map(SupplyModel::new)
            .collect(Collectors.toList());
    }

    @Override
    public String validate(SupplyModel supplyModel) {
        if(supplyModel.getName().trim().length() == 0) {
            return "T??n v???t t?? kh??ng ???????c ????? tr???ng!";
        }
        if(supplyModel.getProducer().trim().length() == 0) {
            return "T??n nh?? s???n xu???t kh??ng ???????c ????? tr???ng!";
        }
        if(supplyModel.getUnit().trim().length() == 0) {
            return "T??n ????n v???  kh??ng ???????c ????? tr???ng!";
        }
        return null;
    }
}
