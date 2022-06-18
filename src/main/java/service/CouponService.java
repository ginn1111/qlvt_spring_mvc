package service;

import dao.*;
import entity.Employee;
import entity.InCoupon;
import entity.Supplier;
import model.AccountModel;
import model.EmployeeModel;
import model.InCouponModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import request_bean.DeletedIdList;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CouponService {
    @Autowired
    InCouponDAO inCouponDAO;
//    @Autowired
//    ExCouponDAO exCouponDAO;
//    @Autowired
//    BorrowedCouponDAO borrowedCouponDAO;
//    @Autowired
//    PayedCouponDAO payedCouponDAO;
//    @Autowired
//    TransCouponDAO transCouponDAO;


    public List<Object> getInCouponModelList() {
        List<InCouponModel> inCouponModelList = inCouponDAO.getList()
                .stream().map(InCouponModel::new)
                .collect(Collectors.toList());
        List<Integer> dummyList = new ArrayList<>();
        for(int i = 0; i < inCouponModelList.size(); i++) {
            dummyList.add(null);
        }
        DeletedIdList deletedIdList = new DeletedIdList(dummyList);
        return Arrays.asList(inCouponModelList, deletedIdList);
    }
    public String addInCoupon(InCouponModel inCouponModel) {
        InCoupon inCoupon = new InCoupon();
        Employee employee = new Employee();
        Supplier supplier = new Supplier();
        inCoupon.setInCpId(inCouponModel.getCouponId());
        inCoupon.setDate(inCouponModel.getDate());

        employee.setEmployeeId(inCouponModel.getEmployeeModel().getEmployeeId());
        inCoupon.setEmployee(employee);

        supplier.setSupplierId(inCouponModel.getSupplierModel().getSupplierId());
        inCoupon.setSupplier(supplier);

        inCoupon.setNote(inCouponModel.getNote());
        inCoupon.setDate(inCouponModel.getDate());

        // Todo: viết trigger tính tổng tiền
        inCoupon.setTotal(new BigDecimal("123131"));
        if(inCouponDAO.addNew(inCoupon)) {
            return "Thêm phiếu thành công!";
        }
        return "Thêm phiếu thất bại, có lỗi xảy ra!";
    }

    public String deleteCoupon(DeletedIdList list) {
        List<InCoupon> deletedInCPList = new ArrayList<>();
        InCoupon iCPTmp;
        for (Integer iCPId :
                list.getList()) {
            if (iCPId != null) {
                iCPTmp = new InCoupon();
                iCPTmp.setInCpId(iCPId);
                deletedInCPList.add(iCPTmp);
            }
        }

        if(inCouponDAO.deleteByListId(deletedInCPList)) {
            return "Xoá thành công!";
        }
        return "Có lỗi xảy ra, vui lòng thử lại.";
    }

    public InCouponModel findInCouponId(Integer iCPId) {
        InCoupon inCoupon = new InCoupon(iCPId);
        return new InCouponModel(inCouponDAO.findById(inCoupon));
    }

    public String editInCoupon(InCouponModel inCouponModel) {
        InCoupon inCoupon = new InCoupon();
        Employee employee = new Employee();
        Supplier supplier = new Supplier();

        inCoupon.setInCpId(inCouponModel.getCouponId());
        inCoupon.setDate(inCouponModel.getDate());

        employee.setEmployeeId(inCouponModel.getEmployeeModel().getEmployeeId());
        inCoupon.setEmployee(employee);

        supplier.setSupplierId(inCouponModel.getSupplierModel().getSupplierId());
        inCoupon.setSupplier(supplier);

        inCoupon.setNote(inCouponModel.getNote());
        inCoupon.setDate(inCouponModel.getDate());

        if (inCouponDAO.update(inCoupon)) {
            return "Cập nhật thành công!";
        }
        return "Cập nhật thất bại!";
    }
}
