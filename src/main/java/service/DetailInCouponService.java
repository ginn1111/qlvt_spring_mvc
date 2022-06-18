package service;

import dao.DetailInCouponDAO;
import entity.DetailInCoupon;
import entity.InCoupon;
import entity.Supply;
import model.DetailInCouponModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DetailInCouponService {
   @Autowired
   DetailInCouponDAO detailInCouponDAO;

   public boolean addListDetail(List<DetailInCouponModel> detailInCouponModelList, Integer couponId) {
      Supply supply;
      InCoupon inCoupon;
      List<DetailInCoupon> detailInCouponList = new ArrayList<>();

      for (DetailInCouponModel detail : detailInCouponModelList) {
         if(detail.getSupplyModel().getSupplyId() == null) continue;
         supply = new Supply(detail.getSupplyModel().getSupplyId());
         inCoupon = new InCoupon(couponId);

         detailInCouponList.add(
            new DetailInCoupon(
               detail.getId()
               , supply, detail.getQuantity()
               , detail.getPrice(), inCoupon
            )
         );
      }

      return detailInCouponDAO.addListDetail(detailInCouponList);
   }
}
