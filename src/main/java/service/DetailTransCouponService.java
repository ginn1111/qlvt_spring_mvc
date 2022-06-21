package service;


import dao.DetailTransCouponDAO;
import entity.*;
import model.DetailInCouponModel;
import model.DetailTransCouponModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DetailTransCouponService {

    @Autowired
    DetailTransCouponDAO detailTransCouponDAO;

    public boolean addListDetail(List<DetailTransCouponModel> detailTransCouponModelList, Integer couponId) {
        Supply supply;
        TransCoupon transCoupon;
        List<DetailTransCoupon> detailTransCouponList = new ArrayList<>();

        for (DetailTransCouponModel detail : detailTransCouponModelList) {
            if(detail.getSupplyModel().getSupplyId() == null) continue;
            supply = new Supply(detail.getSupplyModel().getSupplyId());
            transCoupon = new TransCoupon(couponId);

            detailTransCouponList.add(
                    new DetailTransCoupon(
                            detail.getId()
                            , supply, detail.getQuantity()
                            , transCoupon
                    )
            );
        }

        return detailTransCouponDAO.addListDetail(detailTransCouponList);
    }
}
