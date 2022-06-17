package dao;

import entity.Number;
import entity.PayedCoupon;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.List;

@Repository
public class PayedCouponDAO extends DAO<PayedCoupon>{
    public Integer getNumOfCP() {
        List<Number> numbers = super.sessionFactory.getCurrentSession()
                .getNamedQuery("soLuongPhieuTraTrongThang")
                .setParameter("m", Calendar.getInstance().get(Calendar.MONTH) + 1)
                .setParameter("y", Calendar.getInstance().get(Calendar.YEAR)).list();
        return numbers.get(0).getNumber();
    }
    @Override
    public boolean deleteById(PayedCoupon payedCoupon) {
        return false;
    }

    @Override
    public boolean deleteByListId(List<PayedCoupon> list) {
        return false;
    }

    @Override
    public PayedCoupon findById(PayedCoupon payedCoupon) {
        return null;
    }
}
