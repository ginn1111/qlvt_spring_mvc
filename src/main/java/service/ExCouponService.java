package service;

import dao.ExCouponDAO;
import entity.*;
import model.*;
import model.DetailExCouponModel;
import model.ExCouponModel;
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
public class ExCouponService implements Validation<ExCouponModel>{
    @Autowired
    ExCouponDAO exCouponDAO;

    @Autowired
    DetailExCouponService detailExCouponService;

    public List<Object> getExCouponList() {
        List<ExCouponModel> exCouponModelList = exCouponDAO.getList()
                .stream().map(ExCouponModel::new)
                .collect(Collectors.toList());
        List<Integer> dummyList = new ArrayList<>();
        for(int i = 0; i < exCouponModelList.size(); i++) {
            dummyList.add(null);
        }
        DeletedIdList deletedIdList = new DeletedIdList(dummyList);
        return Arrays.asList(exCouponModelList, deletedIdList);
    }
    public List<Object> getExCouponListForEmp(Integer employeeId) {
        List<ExCouponModel> exCouponModelList =
                exCouponDAO.getList(employeeId).stream()
                        .map(ExCouponModel::new).collect(Collectors.toList());
        List<Integer> dummyList = new ArrayList<>();
        for(int i = 0; i < exCouponModelList.size(); i++) {
            dummyList.add(null);
        }
        DeletedIdList deletedIdList = new DeletedIdList(dummyList);
        return Arrays.asList(exCouponModelList, deletedIdList);
    }
    public String addExCoupon(ExCouponModel exCouponModel) {
        String validStr = validate(exCouponModel);
        if(validStr != null) {
            return validStr;
        }
        ExCoupon exCoupon = new ExCoupon();

        exCoupon.setDate(exCouponModel.getDate());
        exCoupon.setEmployee(new Employee(exCouponModel.getEmployeeModel().getEmployeeId()));
        exCoupon.setNote(exCouponModel.getNote());
        exCoupon.setCpStatus(new CouponStatus(7));
        exCoupon.setConstruction(new Construction(exCouponModel.getConstructionModel().getConstructionId()));
        exCoupon.setWorker(new Worker(exCouponModel.getWorkerModel().getWorkerId()));

        Integer couponId = exCouponDAO.addNewExCoupon(exCoupon);
        if(couponId != null &&
                detailExCouponService.addListDetail(exCouponModel.getDetailExCouponModelList(), couponId) ) {
            return "Th??m phi???u th??nh c??ng!";
        }
        return "Th??m phi???u th???t b???i, c?? l???i x???y ra!";
    }

    public String deleteCoupon(DeletedIdList list) {
        List<ExCoupon> deletedInCPList = new ArrayList<>();
        ExCoupon iCPTmp;
        for (Integer iCPId :
                list.getList()) {
            if (iCPId != null) {
                iCPTmp = new ExCoupon();
                iCPTmp.setExCpId(iCPId);
                deletedInCPList.add(iCPTmp);
            }
        }

        if(exCouponDAO.deleteByListId(deletedInCPList)) {
            return "Xo?? th??nh c??ng!";
        }
        return "C?? l???i x???y ra, vui l??ng th??? l???i.";
    }

    public ExCouponModel findExCouponId(Integer iCPId) {

        List<Object> res = exCouponDAO.findInCPById(new ExCoupon(iCPId));
        ExCouponModel exCouponModel = new ExCouponModel((ExCoupon) res.get(0));
        List<DetailExCouponModel> detailExCouponModelList = ((List<DetailExCoupon>)res.get(1))
                .stream().map(DetailExCouponModel::new).collect(Collectors.toList());

        exCouponModel.setDetailExCouponModelList(detailExCouponModelList);
        return exCouponModel;
    }

    public String editExCoupon(ExCouponModel exCouponModel) {
        ExCoupon exCoupon = new ExCoupon();

        exCoupon.setExCpId(exCouponModel.getCouponId());
        exCoupon.setEmployee(new Employee(exCouponModel.getEmployeeModel().getEmployeeId()));
        exCoupon.setWorker(new Worker(exCouponModel.getWorkerModel().getWorkerId()));
        exCoupon.setDate(exCouponModel.getDate());
        exCoupon.setConstruction(new Construction(exCouponModel.getConstructionModel().getConstructionId()));
        exCoupon.setCpStatus(new CouponStatus(exCouponModel.getCouponStatusModel().getId()));
        exCoupon.setNote(exCouponModel.getNote());

        if (exCouponDAO.update(exCoupon)) {
            return "C???p nh???t th??nh c??ng!";
        }
        return "C???p nh???t th???t b???i!";
    }
    public Integer getNumberOfCPInMonth() {
        return exCouponDAO.getNumOfCP();
    }

    public Integer getNumberOfCPOfEmpInMonth(Integer employeeId) {
        return exCouponDAO.getNumberCouponOfEmpInMonth(employeeId);
    }
    @Override
    public String validate(ExCouponModel exCouponModel) {
        boolean isLeastOne = false;
        for (DetailExCouponModel detail :
                exCouponModel.getDetailExCouponModelList()) {
            if(!isLeastOne && detail.getSupplyModel().getSupplyId() != null) {
                isLeastOne = true;
            }
        }
        return isLeastOne ? null : "Phi???u ph???i c?? ??t nh???t m???t v???t t??";
    }
}
