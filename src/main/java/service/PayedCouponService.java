package service;

import dao.DetailPayedCouponService;
import dao.PayedCouponDAO;
import dao.PayedCouponDAO;
import entity.*;
import model.BorrowedCouponModel;
import model.PayedCouponModel;
import model.DetailPayedCouponModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import request_bean.DeletedIdList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PayedCouponService implements Validation<PayedCouponModel>{
    @Autowired
    PayedCouponDAO payedCouponDAO;

    @Autowired
    DetailPayedCouponService detailPayedCouponService;

    public List<Object> getPayedCouponList() {
        List<PayedCouponModel> pyCouponModelList =
                payedCouponDAO.getList().stream()
                        .map(PayedCouponModel::new).collect(Collectors.toList());
        List<Integer> dummyList = new ArrayList<>();
        for(int i = 0; i < pyCouponModelList.size(); i++) {
            dummyList.add(null);
        }
        DeletedIdList deletedIdList = new DeletedIdList(dummyList);
        return Arrays.asList(pyCouponModelList, deletedIdList);
    }
    public List<Object> getPayedCouponListForEmp(Integer employeeId) {
        List<PayedCouponModel> pyCouponModelList =
                payedCouponDAO.getList(employeeId).stream()
                        .map(PayedCouponModel::new).collect(Collectors.toList());
        List<Integer> dummyList = new ArrayList<>();
        for(int i = 0; i < pyCouponModelList.size(); i++) {
            dummyList.add(null);
        }
        DeletedIdList deletedIdList = new DeletedIdList(dummyList);
        return Arrays.asList(pyCouponModelList, deletedIdList);
    }
    public String addPayedCoupon(PayedCouponModel pyCouponModel) {
        String validStr = validate(pyCouponModel);
        if(validStr != null) {
            return validStr;
        }
        PayedCoupon pyCoupon = new PayedCoupon();

        pyCoupon.setCpStatus(new CouponStatus(7));
        pyCoupon.setEmployee(new Employee(pyCouponModel.getEmployeeModel().getEmployeeId()));
        pyCoupon.setNote(pyCouponModel.getNote());
        pyCoupon.setWorker(new Worker(pyCouponModel.getWorkerModel().getWorkerId()));
        pyCoupon.setBorrowedCoupon(new BorrowedCoupon(pyCouponModel.getBorrowedCouponModel().getCouponId()));
        pyCoupon.setPayedDate(pyCouponModel.getPayedDate());

        Integer couponId = payedCouponDAO.addNewPayedCoupon(pyCoupon);
        if(couponId != null
                && detailPayedCouponService.addListDetail(pyCouponModel.getDetailPayedCouponModelList(), couponId)) {
            return "Thêm phiếu thành công!";
        }
        return "Thêm phiếu thất bại, có lỗi xảy ra!";
    }

    public String deleteCoupon(DeletedIdList list) {
        List<PayedCoupon> deletedInCPList = new ArrayList<>();
        PayedCoupon brCPTmp;
        for (Integer iCPId :
                list.getList()) {
            if (iCPId != null) {
                brCPTmp = new PayedCoupon();
                brCPTmp.setPyCpId(iCPId);
                deletedInCPList.add(brCPTmp);
            }
        }

        if(payedCouponDAO.deleteByListId(deletedInCPList)) {
            return "Xoá thành công!";
        }
        return "Có lỗi xảy ra, vui lòng thử lại.";
    }

    public PayedCouponModel findPayedCouponId(Integer iCPId) {
        List<Object> res = payedCouponDAO.findPyCPById(new PayedCoupon(iCPId));
        PayedCouponModel payedCouponModel = new PayedCouponModel((PayedCoupon) res.get(0));
        List<DetailPayedCouponModel> detailPayedCouponModelList = ((List<DetailPayedCoupon>)res.get(1))
                .stream().map(DetailPayedCouponModel::new).collect(Collectors.toList());

        payedCouponModel.setDetailPayedCouponModelList(detailPayedCouponModelList);
        return payedCouponModel;
    }

    public String editPayedCoupon(PayedCouponModel pyCouponModel) {
        PayedCoupon pyCoupon = new PayedCoupon();

        pyCoupon.setPyCpId(pyCouponModel.getCouponId());
        pyCoupon.setCpStatus(new CouponStatus(pyCouponModel.getCouponStatusModel().getId()));
        pyCoupon.setEmployee(new Employee(pyCouponModel.getEmployeeModel().getEmployeeId()));
        pyCoupon.setNote(pyCouponModel.getNote());
        pyCoupon.setWorker(new Worker(pyCouponModel.getWorkerModel().getWorkerId()));
        pyCoupon.setBorrowedCoupon(new BorrowedCoupon(pyCouponModel.getBorrowedCouponModel().getCouponId()));
        pyCoupon.setPayedDate(pyCouponModel.getPayedDate());

        if (payedCouponDAO.update(pyCoupon)) {
            return "Cập nhật thành công!";
        }
        return "Cập nhật thất bại!";
    }
    public Integer getNumberOfCPInMonth() {
        return payedCouponDAO.getNumOfCP();
    }

    @Override
    public String validate(PayedCouponModel payedCouponModel) {
        boolean isLeastOne = false;
        for (DetailPayedCouponModel detail :
                payedCouponModel.getDetailPayedCouponModelList()) {
            if(!isLeastOne && detail.getSupplyModel().getSupplyId() != null) {
                isLeastOne = true;
            }
        }
        return isLeastOne ? null : "Phiếu phải có ít nhất một vật tư";
    }
}
