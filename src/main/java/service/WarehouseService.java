package service;

import dao.WarehouseDAO;
import entity.Supplier;
import entity.Warehouse;
import model.SupplierModel;
import model.WarehouseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import request_bean.DeletedIdList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WarehouseService implements Validation<WarehouseModel> {

    @Autowired
    WarehouseDAO warehouseDAO;

    public List<Object> searchWarehouse(String key) {
        List<WarehouseModel> warehouseList = warehouseDAO.search(key).stream()
                .map(WarehouseModel::new).collect(Collectors.toList());
        List<Integer> dummyList = new ArrayList<Integer>();
        for(int i = 0; i < warehouseList.size(); i++) {
            dummyList.add(null);
        }
        DeletedIdList deletedIdList = new DeletedIdList(dummyList);
        return Arrays.asList(warehouseList, deletedIdList);
    }

    public List<Object> getWarehouseList() {
        List<WarehouseModel> warehouseList = warehouseDAO.getList().stream()
                .map(WarehouseModel::new).collect(Collectors.toList());
        List<Integer> dummyList = new ArrayList<Integer>();
        for(int i = 0; i < warehouseList.size(); i++) {
            dummyList.add(null);
        }
        DeletedIdList deletedIdList = new DeletedIdList(dummyList);
        return Arrays.asList(warehouseList, deletedIdList);
    }

    public String addWarehouse(WarehouseModel warehouseModel) {
        String validStr = validate(warehouseModel);
        if(validStr != null) {
            return validStr;
        }
        Warehouse warehouse = new Warehouse();
        warehouse.setName(warehouseModel.getName());
        warehouse.setAddress(warehouseModel.getAddress());

        if(warehouseDAO.addNew(warehouse)) {
            return "Thêm kho thành công!";
        }
        return "Thêm thất bại, có lỗi xảy ra!";
    }

    public String deleteWarehouse(DeletedIdList list) {
        List<Warehouse> warehouseList = new ArrayList<>();
        Warehouse tmp;
        for (Integer warehouseId :
                list.getList()) {
            if (warehouseId != null) {
                tmp = new Warehouse();
                tmp.setWarehouseId(warehouseId);
                warehouseList.add(tmp);
            }
        }

        if(warehouseDAO.deleteByListId(warehouseList)) {
            return "Xoá kho thành công!";
        }
        return "Kho đã được lập phiếu, không thể xoá!";
    }

    public WarehouseModel findWarehouseById(Integer warehouseId) {
        Warehouse warehouse = new Warehouse();
        warehouse.setWarehouseId(warehouseId);
        return new WarehouseModel(warehouseDAO.findById(warehouse));
    }

    public String editWarehouse(WarehouseModel warehouseModel) {
        String validStr = validate(warehouseModel);
        if(validStr != null) {
            return validStr;
        }
        Warehouse warehouse = new Warehouse();
        warehouse.setWarehouseId(warehouseModel.getWarehouseId());
        warehouse.setName(warehouseModel.getName());
        warehouse.setAddress(warehouseModel.getAddress());
        if(warehouseDAO.update(warehouse)) {
            return "Cập nhật thành công!";
        }
        return "Cập nhật thất bại!";
    }

    public List<WarehouseModel> getWorkerModelList() {
        return warehouseDAO.getList()
                .stream()
                .map(WarehouseModel::new)
                .collect(Collectors.toList());
    }

    @Override
    public String validate(WarehouseModel warehouseModel) {
        if(warehouseModel.getName().trim().length() == 0) {
            return "Tên không được để trống!";
        }
        return null;
    }
}
