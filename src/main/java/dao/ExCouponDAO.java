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
public class ExCouponDAO extends DAO<ExCoupon>{

    public Integer getNumberCouponOfEmpInMonth(Integer employeeId) {
        List<Number> numbers = sessionFactory.getCurrentSession()
                .getNamedQuery("soLuongPhieuXuatTrongThangNhanVien")
                .setParameter("m", Calendar.getInstance().get(Calendar.MONTH) + 1)
                .setParameter("y", Calendar.getInstance().get(Calendar.YEAR))
                .setParameter("id", employeeId)
                .list();

        return numbers.get(0).getNumber();
    }
    public Integer getNumOfCP() {
        List<Number> numbers = super.sessionFactory.getCurrentSession()
                .getNamedQuery("soLuongPhieuXuatTrongThang")
                .setParameter("m", Calendar.getInstance().get(Calendar.MONTH) + 1)
                .setParameter("y", Calendar.getInstance().get(Calendar.YEAR)).list();
        return numbers.get(0).getNumber();
    }
    public List<ExCoupon> getList(Integer employeeId) {
        return sessionFactory.getCurrentSession()
                .createQuery("FROM ExCoupon AS ExCP WHERE ExCP.employee.employeeId = :employeeId")
                .setParameter("employeeId", employeeId)
                .list();
    }
    public List<ExCoupon> getList() {
        String query = "FROM ExCoupon";
        return super.getList(query);
    }
    @Override
    public boolean deleteById(ExCoupon exCoupon) {
        return false;
    }

    @Override
    public boolean deleteByListId(List<ExCoupon> list) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        ExCoupon exCouponTmp;
        int count = 0;

        try {
            for (ExCoupon exCoupon : list) {
                exCouponTmp = session.get(ExCoupon.class, exCoupon.getExCpId());
                exCouponTmp.setCpStatus(new CouponStatus(6)); // phi???u hu???
                session.update(exCouponTmp);
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
    public ExCoupon findById(ExCoupon exCoupon) {
        return sessionFactory.getCurrentSession().get(ExCoupon.class, exCoupon.getExCpId());
    }

    public List<Object> findInCPById(ExCoupon exCoupon) {
        ExCoupon inCP = sessionFactory.getCurrentSession().get(ExCoupon.class, exCoupon.getExCpId());

        List<DetailExCoupon> detailExCouponList = sessionFactory
                .getCurrentSession()
                .createQuery("FROM DetailExCoupon AS D WHERE D.exCoupon.exCpId = :id")
                .setParameter("id", inCP.getExCpId())
                .list();
        return Arrays.asList(inCP, detailExCouponList);
    }

    public Integer addNewExCoupon(ExCoupon exCoupon) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Integer res;

        try {
            res = (Integer) session.save(exCoupon);
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
