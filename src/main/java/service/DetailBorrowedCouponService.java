package service;

import dao.DetailBorrowedCouponDAO;
import entity.*;
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
}
