package service;

import dao.BorrowedCouponDAO;
import dao.PayedCouponDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayedCouponService {
    @Autowired
    PayedCouponDAO payedCouponDAO;
    public Integer getNumberOfCPInMonth() {
        return payedCouponDAO.getNumOfCP();
    }

}
