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
public class TransCouponDAO extends DAO<TransCoupon> {

    public List<TransCoupon> getList() {
        String query = "FROM TransCoupon";
        return getList(query);
    }
    public List<TransCoupon> getList(Integer employeeId) {
        return sessionFactory.getCurrentSession()
                .createQuery("FROM TransCoupon AS TrCP WHERE TrCP.employee.employeeId = :employeeId")
                .setParameter("employeeId", employeeId)
                .list();
    }
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
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        TransCoupon trCouponTmp;
        int count = 0;

        try {
            for (TransCoupon transCoupon : list) {
                trCouponTmp = session.get(TransCoupon.class, transCoupon.getTrCpId());
                trCouponTmp.setCpStatus(new CouponStatus(6)); // phiếu huỷ
                session.update(trCouponTmp);
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
    public TransCoupon findById(TransCoupon transCoupon) {
        return null;
    }

    public List<Object> findTrCPById(TransCoupon transCoupon) {
        TransCoupon br = sessionFactory.getCurrentSession().get(TransCoupon.class, transCoupon.getTrCpId());

        List<DetailTransCoupon> detailTransCouponList = sessionFactory
                .getCurrentSession()
                .createQuery("FROM DetailTransCoupon AS D WHERE D.transCoupon.trCpId = :id")
                .setParameter("id", br.getTrCpId())
                .list();
        return Arrays.asList(br, detailTransCouponList);
    }

    public Integer addNewTransCoupon(TransCoupon trCoupon) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Integer res;

        try {
            res = (Integer) session.save(trCoupon);
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
}
