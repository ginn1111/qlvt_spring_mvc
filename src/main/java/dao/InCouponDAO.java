package dao;

import entity.*;
import entity.Number;
import model.DetailBorrowedCouponModel;
import model.InCouponModel;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class InCouponDAO extends DAO<InCoupon> {
    public List<InCoupon> getList() {
        String query = "FROM InCoupon";
        return super.getList(query);
    }
    @Override
    public boolean deleteById(InCoupon inCoupon) {
        return false;
    }

    @Override
    public boolean deleteByListId(List<InCoupon> list) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        InCoupon inCouponTmp;
        int count = 0;

        try {
            for (InCoupon inCoupon : list) {
                inCouponTmp = session.get(InCoupon.class, inCoupon.getInCpId());
                inCouponTmp.setCpStatus(new CouponStatus(6)); // phiếu huỷ
                session.update(inCouponTmp);
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
    public InCoupon findById(InCoupon inCoupon) {
        return sessionFactory.getCurrentSession().get(InCoupon.class, inCoupon.getInCpId());
    }

    public List<Object> findInCPById(InCoupon inCoupon) {
        InCoupon inCP = sessionFactory.getCurrentSession().get(InCoupon.class, inCoupon.getInCpId());

        List<DetailInCoupon> detailInCouponList = sessionFactory
                .getCurrentSession()
                .createQuery("FROM DetailInCoupon AS D WHERE D.inCoupon.inCpId = :id")
                .setParameter("id", inCP.getInCpId())
                .list();
        return Arrays.asList(inCP, detailInCouponList);
    }
    public Integer getNumOfCP() {
        List<Number> numbers = super.sessionFactory.getCurrentSession()
                .getNamedQuery("soLuongPhieuNhapTrongThang")
                .setParameter("m", Calendar.getInstance().get(Calendar.MONTH) + 1)
                .setParameter("y", Calendar.getInstance().get(Calendar.YEAR)).list();
        return numbers.get(0).getNumber();
    }

    public Integer addNewInCoupon(InCoupon inCoupon) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Integer res;

        try {
            res = (Integer) session.save(inCoupon);
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
