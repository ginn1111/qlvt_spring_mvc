package service;

import dao.BorrowedCouponDAO;
import dao.TransCouponDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransCouponService {
    @Autowired
    TransCouponDAO transCouponDAO;
    public Integer getNumberOfCPInMonth() {
        return transCouponDAO.getNumOfCP();
    }

}
