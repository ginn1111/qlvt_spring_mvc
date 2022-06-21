package service;

import dao.DetailBorrowedCouponDAO;
import entity.*;
import model.BorrowedCouponModel;
import model.DetailBorrowedCouponModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DetailBorrowedCouponService {
    @Autowired
    DetailBorrowedCouponDAO detailBorrowedCouponDAO;

    public boolean addListDetail(List<DetailBorrowedCouponModel> detailBorrowedCouponModelList, Integer couponId) {
        Supply supply;
        BorrowedCoupon borrowedCoupon;
        List<DetailBorrowedCoupon> detailBorrowedCouponList = new ArrayList<>();

        for (DetailBorrowedCouponModel detail : detailBorrowedCouponModelList) {
            if(detail.getSupplyModel().getSupplyId() == null) continue;
            supply = new Supply(detail.getSupplyModel().getSupplyId());
            borrowedCoupon = new BorrowedCoupon(couponId);

            detailBorrowedCouponList.add(
                    new DetailBorrowedCoupon(
                            detail.getId()
                            , supply, detail.getQuantity()
                            , borrowedCoupon
                    )
            );
        }

        return detailBorrowedCouponDAO.addListDetail(detailBorrowedCouponList);
    }

//    public boolean editDetailBorrowedCouponList(List<DetailBorrowedCouponModel> detailBorrowedCouponModelList, Integer brCPId) {
//        DetailBorrowedCoupon detailBorrowedCoupon;
//        List<DetailBorrowedCoupon> detailBorrowedCouponList = new ArrayList<>();
//        BorrowedCoupon
//
//        for (DetailBorrowedCouponModel detail: detailBorrowedCouponModelList) {
//           detailBorrowedCoupon = new DetailBorrowedCoupon(
//
//           );
//        }
//
//        DetailBorrowedCoupon detailBorrowedCoupon = new DetailBorrowedCoupon();
//        detailBorrowedCoupon.setId(detailBorrowedCouponModel.getId());
//        detailBorrowedCoupon.setQuantity(detailBorrowedCouponModel.getQuantity());
//        detailBorrowedCoupon.setBorrowedCoupon(new BorrowedCoupon(detailBorrowedCouponModel.getBorrowedCouponModel().getCouponId()));
//        detailBorrowedCoupon.setSupply(new Supply(detailBorrowedCouponModel.getSupplyModel().getSupplyId()));
//        return detailBorrowedCouponDAO.update(detailBorrowedCoupon);
//    }
}
