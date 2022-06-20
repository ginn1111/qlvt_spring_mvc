package service;

import dao.BorrowedCouponDAO;
import entity.*;
import entity.Number;
import model.BorrowedCouponModel;
import model.DetailBorrowedCouponModel;
import model.DetailExCouponModel;
import model.InCouponModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import request_bean.DeletedIdList;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BorrowedCouponService implements Validation<BorrowedCouponModel> {
    @Autowired
    BorrowedCouponDAO borrowedCouponDAO;

    @Autowired
    DetailBorrowedCouponService detailBorrowedCouponService;

    public List<Object> getBorrowedCouponList() {
        List<BorrowedCouponModel> brCouponModelList =
                borrowedCouponDAO.getList().stream()
                        .map(BorrowedCouponModel::new).collect(Collectors.toList());
        List<Integer> dummyList = new ArrayList<>();
        for(int i = 0; i < brCouponModelList.size(); i++) {
            dummyList.add(null);
        }
        DeletedIdList deletedIdList = new DeletedIdList(dummyList);
        return Arrays.asList(brCouponModelList, deletedIdList);
    }
    public List<Object> getBorrowedCouponListForEmp(Integer employeeId) {
        List<BorrowedCouponModel> brCouponModelList =
                borrowedCouponDAO.getList(employeeId).stream()
                        .map(BorrowedCouponModel::new).collect(Collectors.toList());
        List<Integer> dummyList = new ArrayList<>();
        for(int i = 0; i < brCouponModelList.size(); i++) {
            dummyList.add(null);
        }
        DeletedIdList deletedIdList = new DeletedIdList(dummyList);
        return Arrays.asList(brCouponModelList, deletedIdList);
    }
    public String addBorrowedCoupon(BorrowedCouponModel brCouponModel) {
        String validStr = validate(brCouponModel);
        if(validStr != null) {
            return validStr;
        }

        BorrowedCoupon brCoupon = new BorrowedCoupon();

        brCoupon.setCpStatus(new CouponStatus(7));
        brCoupon.setBorrowedDate(new Date());
        brCoupon.setConstruction(new Construction(brCouponModel.getConstructionModel().getConstructionId()));
        brCoupon.setWorker(new Worker(brCouponModel.getWorkerModel().getWorkerId()));
        brCoupon.setEmployee(new Employee(brCouponModel.getEmployeeModel().getEmployeeId()));
        brCoupon.setNote(brCouponModel.getNote());
        brCoupon.setPayedDate(brCouponModel.getPayedDate());

        Integer couponId = borrowedCouponDAO.addNewBorrowedCoupon(brCoupon);
        if(couponId != null
                && detailBorrowedCouponService.addListDetail(brCouponModel.getDetailBorrowedCouponModelList(), couponId)) {
            return "Thêm phiếu thành công!";
        }
        return "Thêm phiếu thất bại, có lỗi xảy ra!";
    }

    public String deleteCoupon(DeletedIdList list) {
        List<BorrowedCoupon> deletedInCPList = new ArrayList<>();
        BorrowedCoupon brCPTmp;
        for (Integer iCPId :
                list.getList()) {
            if (iCPId != null) {
                brCPTmp = new BorrowedCoupon();
                brCPTmp.setBrCpId(iCPId);
                deletedInCPList.add(brCPTmp);
            }
        }

        if(borrowedCouponDAO.deleteByListId(deletedInCPList)) {
            return "Xoá thành công!";
        }
        return "Có lỗi xảy ra, vui lòng thử lại.";
    }

    public BorrowedCouponModel findBorrowedCouponId(Integer iCPId) {
        List<Object> res = borrowedCouponDAO.findBrCPById(new BorrowedCoupon(iCPId));
        BorrowedCouponModel borrowedCouponModel = new BorrowedCouponModel((BorrowedCoupon) res.get(0));
        List<DetailBorrowedCouponModel> detailBorrowedCouponModelList = ((List<DetailBorrowedCoupon>)res.get(1))
                .stream().map(DetailBorrowedCouponModel::new).collect(Collectors.toList());

        borrowedCouponModel.setDetailBorrowedCouponModelList(detailBorrowedCouponModelList);
        return borrowedCouponModel;
    }

    public String editBorrowedCoupon(BorrowedCouponModel brCouponModel) {
        BorrowedCoupon brCoupon = new BorrowedCoupon();

        brCoupon.setBrCpId(brCouponModel.getCouponId());
        brCoupon.setBorrowedDate(brCouponModel.getBorrowedDate());
        brCoupon.setPayedDate(brCouponModel.getPayedDate());
        brCoupon.setEmployee(new Employee(brCouponModel.getEmployeeModel().getEmployeeId()));
        brCoupon.setWorker(new Worker(brCouponModel.getWorkerModel().getWorkerId()));
        brCoupon.setNote(brCouponModel.getNote());
        brCoupon.setConstruction(new Construction(brCouponModel.getConstructionModel().getConstructionId()));
        brCoupon.setCpStatus(new CouponStatus(brCouponModel.getCouponStatusModel().getId()));

        if (borrowedCouponDAO.update(brCoupon)) {
            return "Cập nhật thành công!";
        }
        return "Cập nhật thất bại!";
    }
    public List<BorrowedCouponModel> getBorrowedCouponModelList() {
        return borrowedCouponDAO.getList()
                .stream()
                .map(BorrowedCouponModel::new)
                .collect(Collectors.toList());
    }
    public Integer getNumberOfCPInMonth() {
        return borrowedCouponDAO.getNumOfCP();
    }

    @Override
    public String validate(BorrowedCouponModel borrowedCouponModel) {
        boolean isLeastOne = false;
        for (DetailBorrowedCouponModel detail :
                borrowedCouponModel.getDetailBorrowedCouponModelList()) {
            if(!isLeastOne && detail.getSupplyModel().getSupplyId() != null) {
                isLeastOne = true;
            }
        }
        return isLeastOne ? null : "Phiếu phải có ít nhất một vật tư";
    }
}