package service;

import dao.EmployeeDAO;
import dao.SupplierDAO;
import entity.Employee;
import entity.Supplier;
import model.AccountModel;
import model.EmployeeModel;
import model.SupplierModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import request_bean.DeletedIdList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class SupplierService implements Validation<SupplierModel> {
    @Autowired
    SupplierDAO supplierDAO;

    public List<Object> searchSupplier(String key) {
        List<SupplierModel> supplierList = supplierDAO.search(key).stream()
                .map(SupplierModel::new).collect(Collectors.toList());
        List<Integer> dummyList = new ArrayList<Integer>();
        for(int i = 0; i < supplierList.size(); i++) {
            dummyList.add(null);
        }
        DeletedIdList deletedIdList = new DeletedIdList(dummyList);
        return Arrays.asList(supplierList, deletedIdList);
    }
    public List<Object> getSupplierList() {
        List<SupplierModel> supplierList = supplierDAO.getList().stream()
                .map(SupplierModel::new).collect(Collectors.toList());
        List<Integer> dummyList = new ArrayList<Integer>();
        for(int i = 0; i < supplierList.size(); i++) {
            dummyList.add(null);
        }
        DeletedIdList deletedIdList = new DeletedIdList(dummyList);
        return Arrays.asList(supplierList, deletedIdList);
    }

    public String addSupplier(SupplierModel supplierModel) {
        String validStr = validate(supplierModel);
        if(validStr != null) {
            return validStr;
        }
        Supplier supplier = new Supplier();
        supplier.setName(supplierModel.getName());
        supplier.setAddress(supplierModel.getAddress());
        supplier.setPhone(supplierModel.getPhone());

        // default value
        supplier.setStatus(true);
        System.out.println(supplier);
        if(supplierDAO.addNew(supplier)) {
            return "Thêm nhà cung cấp thành công!";
        }
        return "Thêm nhà cung cấp thất bại, có lỗi xảy ra!";
    }

    public String deleteSupplier(DeletedIdList list) {
        List<Supplier> listSupplier = new ArrayList<>();
        Supplier tmp;
        for (Integer supplierId :
                list.getList()) {
            if (supplierId != null) {
                tmp = new Supplier();
                tmp.setSupplierId(supplierId);
                listSupplier.add(tmp);
            }
        }

        if(supplierDAO.deleteByListId(listSupplier)) {
            return "Xoá nhà cung cấp thành công!";
        }
        return "Nhà cung cấp đã lập phiếu nhập, không thể xoá!";
    }

    public SupplierModel findSupplierById(Integer supplierId) {
        Supplier supplier = new Supplier();
        supplier.setSupplierId(supplierId);
        return new SupplierModel(supplierDAO.findById(supplier));
    }

    public String editSupplier(SupplierModel supplierModel) {
        String validStr = validate(supplierModel);
        if(validStr != null) {
            return validStr;
        }
        Supplier supplier = new Supplier();
        supplier.setSupplierId(supplierModel.getSupplierId());
        supplier.setName(supplierModel.getName());
        supplier.setAddress(supplierModel.getAddress());
        supplier.setPhone(supplierModel.getPhone());
        supplier.setStatus(true);
        if(supplierDAO.update(supplier)) {
            return "Sửa nhà cung cấp thành công!";
        }
        return "Sửa nhà cung cấp thất bại!";
    }

    public SupplierModel getSupplier(Integer supplierId) {
        return new SupplierModel(supplierDAO.findById(new Supplier(supplierId)));
    }

    public List<SupplierModel> supplierList() {
       return supplierDAO.getList()
               .stream()
               .map(SupplierModel::new)
               .collect(Collectors.toList());
    }

    @Override
    public String validate(SupplierModel supplierModel) {
        if(supplierModel.getName().trim().length() == 0) {
            return "Tên không được để trống!";
        }
        String phone = supplierModel.getPhone();
        if(phone != null && phone.trim().length() != 0) {
            phone = phone.trim();
            Pattern pattern = Pattern.compile(regexPhone);
            Matcher matcher = pattern.matcher(phone);
            if(!matcher.matches()) {
                return "Số điện thoại không hợp lệ";
            }
        }
        return null;
    }
}
