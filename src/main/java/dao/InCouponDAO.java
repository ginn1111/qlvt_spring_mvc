package dao;

import entity.InCoupon;
import entity.Number;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.List;

@Repository
public class InCouponDAO extends DAO<InCoupon> {
    @Override
    public boolean deleteById(InCoupon inCoupon) {
        return false;
    }

    @Override
    public boolean deleteByListId(List<InCoupon> list) {
        return false;
    }

    @Override
    public InCoupon findById(InCoupon inCoupon) {
        return null;
    }
    public Integer getNumOfCP() {
        List<Number> numbers = super.sessionFactory.getCurrentSession()
                .getNamedQuery("soLuongPhieuNhapTrongThang")
                .setParameter("m", Calendar.getInstance().get(Calendar.MONTH) + 1)
                .setParameter("y", Calendar.getInstance().get(Calendar.YEAR)).list();
        return numbers.get(0).getNumber();
    }
}
