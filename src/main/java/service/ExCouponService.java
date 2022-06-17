package service;

import dao.BorrowedCouponDAO;
import dao.ExCouponDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExCouponService {
    @Autowired
    ExCouponDAO exCouponDAO;
    public Integer getNumberOfCPInMonth() {
        return exCouponDAO.getNumOfCP();
    }

}
