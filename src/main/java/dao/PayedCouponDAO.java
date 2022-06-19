package dao;

import entity.*;
import entity.Number;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

@Repository
public class PayedCouponDAO extends DAO<PayedCoupon>{
    public List<PayedCoupon> getList() {
        String query = "FROM PayedCoupon";
        return getList(query);
    }
    @Override
    public boolean deleteById(PayedCoupon payedCoupon) {
        return false;
    }

    @Override
    public boolean deleteByListId(List<PayedCoupon> list) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        PayedCoupon pyCouponTmp;
        int count = 0;

        try {
            for (PayedCoupon payedCoupon : list) {
                pyCouponTmp = session.get(PayedCoupon.class, payedCoupon.getPyCpId());
                pyCouponTmp.setCpStatus(new CouponStatus(6)); // phiếu huỷ
                session.update(pyCouponTmp);
                if(++count % 10 == 0) {
                    session.flush();
                }
            }
            transaction.commit();
        } catch(Exception ex) {
            transaction.rollback();
            ex.printStackTrace();
            return false;
        } finally {
            session.close();
        }
        return true;
    }

    @Override
    public PayedCoupon findById(PayedCoupon payedCoupon) {
        return null;
    }

    public List<Object> findPyCPById(PayedCoupon payedCoupon) {
        PayedCoupon py = sessionFactory.getCurrentSession().get(PayedCoupon.class, payedCoupon.getPyCpId());

        List<DetailPayedCoupon> detailPayedCouponList = sessionFactory
                .getCurrentSession()
                .createQuery("FROM DetailPayedCoupon AS D WHERE D.payedCoupon.pyCpId = :id")
                .setParameter("id", py.getPyCpId())
                .list();
        return Arrays.asList(py, detailPayedCouponList);
    }
    public Integer addNewPayedCoupon(PayedCoupon pyCoupon) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Integer res;

        try {
            res = (Integer) session.save(pyCoupon);
            transaction.commit();
        } catch(Exception ex) {
            transaction.rollback();
            ex.printStackTrace();
            return null;
        } finally {
            session.close();
        }
        return res;
    }
    public Integer getNumOfCP() {
        List<Number> numbers = super.sessionFactory.getCurrentSession()
                .getNamedQuery("soLuongPhieuTraTrongThang")
                .setParameter("m", Calendar.getInstance().get(Calendar.MONTH) + 1)
                .setParameter("y", Calendar.getInstance().get(Calendar.YEAR)).list();
        return numbers.get(0).getNumber();
    }
}
