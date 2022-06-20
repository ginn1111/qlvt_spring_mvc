package service;

import dao.TransCouponDAO;
import dao.TransCouponDAO;
import entity.*;
import model.DetailBorrowedCouponModel;
import model.TransCouponModel;
import model.TransCouponModel;
import model.DetailTransCouponModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import request_bean.DeletedIdList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransCouponService implements Validation<TransCouponModel>{

    @Autowired
    TransCouponDAO transCouponDAO;

    @Autowired
    DetailTransCouponService detailTransCouponService;

    public List<Object> getTransCouponList() {
        List<TransCouponModel> trCouponModelList =
                transCouponDAO.getList().stream()
                        .map(TransCouponModel::new).collect(Collectors.toList());
        System.out.println("Service" + trCouponModelList);
        List<Integer> dummyList = new ArrayList<>();
        for(int i = 0; i < trCouponModelList.size(); i++) {
            dummyList.add(null);
        }
        DeletedIdList deletedIdList = new DeletedIdList(dummyList);
        return Arrays.asList(trCouponModelList, deletedIdList);
    }
    public List<Object> getTransCouponListForEmp(Integer employeeId) {
        List<TransCouponModel> trCouponModelList =
                transCouponDAO.getList(employeeId).stream()
                        .map(TransCouponModel::new).collect(Collectors.toList());
        List<Integer> dummyList = new ArrayList<>();
        for(int i = 0; i < trCouponModelList.size(); i++) {
            dummyList.add(null);
        }
        DeletedIdList deletedIdList = new DeletedIdList(dummyList);
        return Arrays.asList(trCouponModelList, deletedIdList);
    }
    public String addTransCoupon(TransCouponModel trCouponModel) {
        String validStr = validate(trCouponModel);
        if(validStr != null) {
            return validStr;
        }
        TransCoupon trCoupon = new TransCoupon();

        trCoupon.setCpStatus(new CouponStatus(7));
        trCoupon.setDate(new Date());
        trCoupon.setEmployee(new Employee(trCouponModel.getEmployeeModel().getEmployeeId()));
        trCoupon.setNote(trCouponModel.getNote());
        trCoupon.setWarehouse(new Warehouse(trCouponModel.getWarehouseModel().getWarehouseId()));

        Integer couponId = transCouponDAO.addNewTransCoupon(trCoupon);
        if(couponId != null
                && detailTransCouponService.addListDetail(trCouponModel.getDetailTransCouponModelList(), couponId)) {
            return "Thêm phiếu thành công!";
        }
        return "Thêm phiếu thất bại, có lỗi xảy ra!";
    }

    public String deleteCoupon(DeletedIdList list) {
        List<TransCoupon> deletedInCPList = new ArrayList<>();
        TransCoupon trCPTmp;
        for (Integer iCPId :
                list.getList()) {
            if (iCPId != null) {
                trCPTmp = new TransCoupon();
                trCPTmp.setTrCpId(iCPId);
                deletedInCPList.add(trCPTmp);
            }
        }

        if(transCouponDAO.deleteByListId(deletedInCPList)) {
            return "Xoá thành công!";
        }
        return "Có lỗi xảy ra, vui lòng thử lại.";
    }

    public TransCouponModel findTransCouponId(Integer iCPId) {
        List<Object> res = transCouponDAO.findTrCPById(new TransCoupon(iCPId));
        TransCouponModel transCouponModel = new TransCouponModel((TransCoupon) res.get(0));
        List<DetailTransCouponModel> detailTransCouponModelList = ((List<DetailTransCoupon>)res.get(1))
                .stream().map(DetailTransCouponModel::new).collect(Collectors.toList());

        transCouponModel.setDetailTransCouponModelList(detailTransCouponModelList);
        return transCouponModel;
    }

    public String editTransCoupon(TransCouponModel trCouponModel) {
        TransCoupon trCoupon = new TransCoupon();

        trCoupon.setTrCpId(trCouponModel.getCouponId());
        trCoupon.setCpStatus(new CouponStatus(trCouponModel.getCouponStatusModel().getId()));
        trCoupon.setDate(trCouponModel.getDate());
        trCoupon.setEmployee(new Employee(trCouponModel.getEmployeeModel().getEmployeeId()));
        trCoupon.setNote(trCouponModel.getNote());
        trCoupon.setWarehouse(new Warehouse(trCouponModel.getWarehouseModel().getWarehouseId()));

        if (transCouponDAO.update(trCoupon)) {
            return "Cập nhật thành công!";
        }
        return "Cập nhật thất bại!";
    }
    public Integer getNumberOfCPInMonth() {
        return transCouponDAO.getNumOfCP();
    }

    @Override
    public String validate(TransCouponModel transCouponModel) {
        boolean isLeastOne = false;
        for (DetailTransCouponModel detail :
                transCouponModel.getDetailTransCouponModelList()) {
            if(!isLeastOne && detail.getSupplyModel().getSupplyId() != null) {
                isLeastOne = true;
            }
        }
        return isLeastOne ? null : "Phiếu phải có ít nhất một vật tư";
    }
}
