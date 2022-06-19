package service;

import dao.DetailExCouponDAO;
import dao.DetailInCouponDAO;
import entity.*;
import model.DetailExCouponModel;
import model.DetailInCouponModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DetailExCouponService {
    @Autowired
    DetailExCouponDAO detailInCouponDAO;

    public boolean addListDetail(List<DetailExCouponModel> detailExCouponModelList, Integer couponId) {
        Supply supply;
        ExCoupon exCoupon;
        List<DetailExCoupon> detailExCouponList = new ArrayList<>();

        for (DetailExCouponModel detail : detailExCouponModelList) {
            if(detail.getSupplyModel().getSupplyId() == null) continue;
            supply = new Supply(detail.getSupplyModel().getSupplyId());
            exCoupon = new ExCoupon(couponId);

            detailExCouponList.add(
                    new DetailExCoupon(
                            detail.getId()
                            , supply, detail.getQuantity()
                            ,exCoupon
                    )
            );
        }

        return detailInCouponDAO.addListDetail(detailExCouponList);
    }
}
