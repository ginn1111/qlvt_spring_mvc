package service;

import dao.InCouponDAO;
import dao.InCouponDAO;
import entity.*;
import model.InCouponModel;
import model.DetailInCouponModel;
import model.DetailInCouponModel;
import model.InCouponModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import request_bean.DeletedIdList;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InCouponService implements Validation<InCouponModel> {
    @Autowired
    InCouponDAO inCouponDAO;
    @Autowired
    DetailInCouponService detailInCouponService;

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
    public List<Object> getInCouponListForEmp(Integer employeeId) {
        List<InCouponModel> inCouponModelList =
                inCouponDAO.getList(employeeId).stream()
                        .map(InCouponModel::new).collect(Collectors.toList());
        List<Integer> dummyList = new ArrayList<>();
        for(int i = 0; i < inCouponModelList.size(); i++) {
            dummyList.add(null);
        }
        DeletedIdList deletedIdList = new DeletedIdList(dummyList);
        return Arrays.asList(inCouponModelList, deletedIdList);
    }
    public String addInCoupon(InCouponModel inCouponModel) {
        String validStr = validate(inCouponModel);
        if(validStr != null) {
           return validStr;
        }
        InCoupon inCoupon = new InCoupon();
        inCoupon.setDate(new Date());
        inCoupon.setEmployee(new Employee(inCouponModel.getEmployeeModel().getEmployeeId()));
        inCoupon.setSupplier(new Supplier(inCouponModel.getSupplierModel().getSupplierId()));
        inCoupon.setNote(inCouponModel.getNote());
        inCoupon.setCpStatus(new CouponStatus(7));
        inCoupon.setTotal(new BigDecimal("0"));


        Integer couponId = inCouponDAO.addNewInCoupon(inCoupon);
        Double total = detailInCouponService.addListDetail(inCouponModel.getDetailInCouponModelList(), couponId);
        if(couponId != null && total != null) {
            inCoupon.setTotal(new BigDecimal(total.toString()));
            inCouponDAO.update(inCoupon);
            return "Th??m phi???u th??nh c??ng!";
        }
        return "Th??m phi???u th???t b???i, c?? l???i x???y ra!";
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
            return "Xo?? th??nh c??ng!";
        }
        return "C?? l???i x???y ra, vui l??ng th??? l???i.";
    }

    public InCouponModel findInCouponId(Integer iCPId) {

        List<Object> res = inCouponDAO.findInCPById(new InCoupon(iCPId));
        InCouponModel inCouponModel = new InCouponModel((InCoupon) res.get(0));
        List<DetailInCouponModel> detailInCouponModelList = ((List<DetailInCoupon>)res.get(1))
                .stream().map(DetailInCouponModel::new).collect(Collectors.toList());

        inCouponModel.setDetailInCouponModelList(detailInCouponModelList);
        return inCouponModel;
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

        inCoupon.setTotal(inCouponModel.getTotal());

        inCoupon.setCpStatus(new CouponStatus(inCouponModel.getCouponStatusModel().getId()));

        if (inCouponDAO.update(inCoupon)) {
            return "C???p nh???t th??nh c??ng!";
        }
        return "C???p nh???t th???t b???i!";
    }
    public Integer getNumberOfCPInMonth() {
        return inCouponDAO.getNumOfCP();
    }

    public Integer getNumberOfCPOfEmpInMonth(Integer employeeId) {
        return inCouponDAO.getNumberCouponOfEmpInMonth(employeeId);
    }

    @Override
    public String validate(InCouponModel inCouponModel) {
        boolean isLeastOne = false;
        for (DetailInCouponModel detail: inCouponModel.getDetailInCouponModelList()) {
           if(detail.getSupplyModel().getSupplyId() != null) {
               isLeastOne = true;
               if(detail.getPrice() == null) {
                   return "Kh??ng ???????c ????? tr???ng gi??!";
               }
               if(detail.getQuantity() == null) {
                   return "Kh??ng ???????c ????? tr???ng s??? l?????ng!";
               }
           }
        }
        return isLeastOne ? null : "Phi???u ph???i c?? ??t nh???t m???t v???t t??";
    }
}
