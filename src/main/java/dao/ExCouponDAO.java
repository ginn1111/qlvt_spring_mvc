package dao;

import entity.Number;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.List;

@Repository
public class ExCouponDAO extends DAO<ExCouponDAO>{
    public Integer getNumOfCP() {
        List<Number> numbers = super.sessionFactory.getCurrentSession()
                .getNamedQuery("soLuongPhieuXuatTrongThang")
                .setParameter("m", Calendar.getInstance().get(Calendar.MONTH) + 1)
                .setParameter("y", Calendar.getInstance().get(Calendar.YEAR)).list();
        return numbers.get(0).getNumber();
    }

    @Override
    public boolean deleteById(ExCouponDAO exCouponDAO) {
        return false;
    }

    @Override
    public boolean deleteByListId(List<ExCouponDAO> list) {
        return false;
    }

    @Override
    public ExCouponDAO findById(ExCouponDAO exCouponDAO) {
        return null;
    }
}
