package dao;

import entity.BorrowedCoupon;
import entity.Number;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.List;

@Repository
public class BorrowedCouponDAO extends DAO<BorrowedCoupon> {

    @Override
    public boolean deleteById(BorrowedCoupon borrowedCoupon) {
        return false;
    }

    @Override
    public boolean deleteByListId(List<BorrowedCoupon> list) {
        return false;
    }

    @Override
    public BorrowedCoupon findById(BorrowedCoupon borrowedCoupon) {
        return null;
    }
    public Integer getNumOfCP() {
         List<Number> numbers = super.sessionFactory.getCurrentSession()
                .getNamedQuery("soLuongPhieuMuonTrongThang")
                .setParameter("m", Calendar.getInstance().get(Calendar.MONTH) + 1)
                .setParameter("y", Calendar.getInstance().get(Calendar.YEAR)).list();
         return numbers.get(0).getNumber();
    }
}
