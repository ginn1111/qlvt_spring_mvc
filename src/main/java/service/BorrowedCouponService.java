package service;

import dao.BorrowedCouponDAO;
import entity.Number;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BorrowedCouponService {
    @Autowired
    BorrowedCouponDAO borrowedCouponDAO;
    public Integer getNumberOfCPInMonth() {
        return borrowedCouponDAO.getNumOfCP();
    }
}