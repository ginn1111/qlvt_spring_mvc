package dao;

import entity.Number;
import entity.TransCoupon;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.List;

@Repository
public class TransCouponDAO extends DAO<TransCoupon> {
    public Integer getNumOfCP() {
        List<Number> numbers = super.sessionFactory.getCurrentSession()
                .getNamedQuery("soLuongPhieuChuyenKhoTrongThang")
                .setParameter("m", Calendar.getInstance().get(Calendar.MONTH) + 1)
                .setParameter("y", Calendar.getInstance().get(Calendar.YEAR)).list();
        return numbers.get(0).getNumber();
    }
    @Override
    public boolean deleteById(TransCoupon transCoupon) {
        return false;
    }

    @Override
    public boolean deleteByListId(List<TransCoupon> list) {
        return false;
    }

    @Override
    public TransCoupon findById(TransCoupon transCoupon) {
        return null;
    }
}
