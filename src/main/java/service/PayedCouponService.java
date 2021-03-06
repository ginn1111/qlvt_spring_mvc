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
    BorrowedCouponService borrowedCouponService;

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
                && detailPayedCouponService.addListDetail(pyCouponModel.getDetailPayedCouponModelList(), couponId)
            ) {
            return "Th??m phi???u th??nh c??ng!";
        }
        return "Th??m phi???u th???t b???i, c?? l???i x???y ra!";
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
            return "Xo?? th??nh c??ng!";
        }
        return "C?? l???i x???y ra, vui l??ng th??? l???i.";
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
            return "C???p nh???t th??nh c??ng!";
        }
        return "C???p nh???t th???t b???i!";
    }
    public Integer getNumberOfCPInMonth() {
        return payedCouponDAO.getNumOfCP();
    }
    public Integer getNumberOfCPOfEmpInMonth(Integer employeeId) {
        return payedCouponDAO.getNumberCouponOfEmpInMonth(employeeId);
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
        return isLeastOne ? null : "Phi???u ph???i c?? ??t nh???t m???t v???t t??";
    }
}
