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
public class WarehouseService {

    @Autowired
    WarehouseDAO warehouseDAO;

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
        Warehouse warehouse = new Warehouse();
        warehouse.setName(warehouseModel.getName());
        warehouse.setAddress(warehouseModel.getAddress());

        if(warehouseDAO.addNew(warehouse)) {
            return "Thêm nhà thành công!";
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
            return "Xoá thành công!";
        }
        return "Có lỗi xảy ra, vui lòng thử lại.";
    }

    public WarehouseModel findWarehouseById(Integer warehouseId) {
        Warehouse warehouse = new Warehouse();
        warehouse.setWarehouseId(warehouseId);
        return new WarehouseModel(warehouseDAO.findById(warehouse));
    }

    public String editWarehouse(WarehouseModel warehouseModel) {
        Warehouse warehouse = new Warehouse();
        warehouse.setWarehouseId(warehouseModel.getWarehouseId());
        warehouse.setName(warehouseModel.getName());
        warehouse.setAddress(warehouseModel.getAddress());
        if(warehouseDAO.update(warehouse)) {
            return "Cập nhật thành công!";
        }
        return "Cập nhật thất bại!";
    }

}
