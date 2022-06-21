package dao;

import entity.PayedCoupon;
import entity.DetailPayedCoupon;
import entity.Supply;
import model.DetailPayedCouponModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DetailPayedCouponService {
    @Autowired
    DetailPayedCouponDAO detailPayedCouponDAO;

    public boolean addListDetail(List<DetailPayedCouponModel> detailPayedCouponModelList, Integer couponId) {
        Supply supply;
        PayedCoupon payedCoupon;
        List<DetailPayedCoupon> detailPayedCouponList = new ArrayList<>();

        for (DetailPayedCouponModel detail : detailPayedCouponModelList) {
            if(detail.getSupplyModel().getSupplyId() == null) continue;
            supply = new Supply(detail.getSupplyModel().getSupplyId());
            payedCoupon = new PayedCoupon(couponId);

            detailPayedCouponList.add(
                    new DetailPayedCoupon(
                            detail.getId()
                            , supply, detail.getQuantity()
                            , payedCoupon
                    )
            );
        }

        return detailPayedCouponDAO.addListDetail(detailPayedCouponList);
    }
}
