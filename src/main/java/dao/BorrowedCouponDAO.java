package dao;

import entity.*;
import entity.BorrowedCoupon;
import entity.Number;
import model.BorrowedCouponModel;
import model.DetailBorrowedCouponModel;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sun.util.resources.en.CalendarData_en;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class BorrowedCouponDAO extends DAO<BorrowedCoupon> {

    public List<BorrowedCoupon> getList() {
        String query = "FROM BorrowedCoupon";
        return getList(query);
    }

    public List<BorrowedCoupon> getListBrCPNotCompOrNotPayed() {
        // Phiếu chưa hoàn thành or chưa trả hết.
        String query = "FROM BorrowedCoupon AS BrCP WHERE BrCP.cpStatus.cpStatusId in (4,2)";
        return getList(query);
    }

    public List<BorrowedCoupon> getTopBrCouponMaturityInMonth(Integer top) {
        return sessionFactory.getCurrentSession()
                .getNamedQuery("topPhieuMuonToiHanTrongThang")
                .setParameter("num", top)
                .setParameter("m", Calendar.getInstance().get(Calendar.MONTH) + 1)
                .setParameter("y", Calendar.getInstance().get(Calendar.YEAR))
                .list();
    }
    public List<BorrowedCoupon> getList(Integer employeeId) {
        return sessionFactory.getCurrentSession()
                .createQuery("FROM BorrowedCoupon AS BrCP WHERE BrCP.employee.employeeId = :employeeId")
                .setParameter("employeeId", employeeId)
                .list();
    }
    @Override
    public boolean deleteById(BorrowedCoupon borrowedCoupon) {
        return false;
    }

    @Override
    public boolean deleteByListId(List<BorrowedCoupon> list) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        BorrowedCoupon brCouponTmp;
        int count = 0;

        try {
            for (BorrowedCoupon borrowedCoupon : list) {
                brCouponTmp = session.get(BorrowedCoupon.class, borrowedCoupon.getBrCpId());
                brCouponTmp.setCpStatus(new CouponStatus(6)); // phiếu huỷ
                session.update(brCouponTmp);
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
    public BorrowedCoupon findById(BorrowedCoupon borrowedCoupon) {
        return null;
    }

    public List<Object> findBrCPById(BorrowedCoupon borrowedCoupon) {
        BorrowedCoupon br = sessionFactory.getCurrentSession().get(BorrowedCoupon.class, borrowedCoupon.getBrCpId());

        List<DetailBorrowedCoupon> detailBorrowedCouponList = sessionFactory
                                .getCurrentSession()
                                .createQuery("FROM DetailBorrowedCoupon AS D WHERE D.borrowedCoupon.brCpId = :id")
                                .setParameter("id", br.getBrCpId())
                                .list();
        return Arrays.asList(br, detailBorrowedCouponList);
    }
    public Integer addNewBorrowedCoupon(BorrowedCoupon brCoupon) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Integer res;

        try {
            res = (Integer) session.save(brCoupon);
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
    public Integer getNumberCouponOfEmpInMonth(Integer employeeId) {
        List<Number> numbers = super.sessionFactory.getCurrentSession()
                .getNamedQuery("soLuongPhieuMuonTrongThangNhanVien")
                .setParameter("m", Calendar.getInstance().get(Calendar.MONTH) + 1)
                .setParameter("y", Calendar.getInstance().get(Calendar.YEAR))
                .setParameter("id", employeeId)
                .list();
        return numbers.get(0).getNumber();
    }
    public Integer getNumOfCP() {
         List<Number> numbers = super.sessionFactory.getCurrentSession()
                .getNamedQuery("soLuongPhieuMuonTrongThang")
                .setParameter("m", Calendar.getInstance().get(Calendar.MONTH) + 1)
                .setParameter("y", Calendar.getInstance().get(Calendar.YEAR)).list();
         return numbers.get(0).getNumber();
    }
}
