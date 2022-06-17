package service;

import dao.BorrowedCouponDAO;
import dao.InCouponDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InCouponService {
    @Autowired
    InCouponDAO inCouponDAO;
    public Integer getNumberOfCPInMonth() {
        return inCouponDAO.getNumOfCP();
    }

}
