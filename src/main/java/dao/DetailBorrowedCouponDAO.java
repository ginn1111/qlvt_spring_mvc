package dao;

import entity.BorrowedCoupon;
import entity.DetailBorrowedCoupon;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DetailBorrowedCouponDAO extends DAO<BorrowedCoupon> {
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

    public boolean addListDetail(List<DetailBorrowedCoupon> detailBorrowedCouponList) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            for (DetailBorrowedCoupon d: detailBorrowedCouponList) {
                session.save(d);
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
}
