package service;

import dao.CouponStatusDAO;
import model.CouponStatusModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CouponStatusService {
    @Autowired
    CouponStatusDAO couponStatusDAO;

    public List<CouponStatusModel> getCouponStatusModelList() {
        return couponStatusDAO.getList()
                .stream()
                .map(CouponStatusModel::new)
                .collect(Collectors.toList());
    }

}
