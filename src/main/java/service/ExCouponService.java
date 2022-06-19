package service;

import dao.ExCouponDAO;
import entity.*;
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
public class ExCouponService {
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
    public String addExCoupon(ExCouponModel exCouponModel) {
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
            return "Thêm phiếu thành công!";
        }
        return "Thêm phiếu thất bại, có lỗi xảy ra!";
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
            return "Xoá thành công!";
        }
        return "Có lỗi xảy ra, vui lòng thử lại.";
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
            return "Cập nhật thành công!";
        }
        return "Cập nhật thất bại!";
    }
    public Integer getNumberOfCPInMonth() {
        return exCouponDAO.getNumOfCP();
    }

}
