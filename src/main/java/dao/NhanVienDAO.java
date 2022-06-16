package dao;

import entity.NhanVien;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import request_bean.NhanVienRequest;

import java.util.List;

@Repository
public class NhanVienDAO implements DAO<NhanVien> {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<NhanVien> getList() {
        List<NhanVien> listNhanVien = sessionFactory
                .getCurrentSession().createQuery("FROM NhanVien").list();
        return listNhanVien;
    }

    @Override
    public boolean addNew(NhanVien nhanvien) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.save(nhanvien);
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
    public boolean deleteById(NhanVien nhanVien) {

        return true;
    }

    @Override
    public boolean update(NhanVien nhanVien) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.update(nhanVien);
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
    public boolean deleteByListId(List<NhanVien> list) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            for (NhanVien nv :
                    list) {
                session.delete(nv);
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
    public NhanVien findById(NhanVien nhanVien) {
        NhanVien nv = sessionFactory.getCurrentSession().get(NhanVien.class, nhanVien.getMaNhanVien());
        return nv;
    }

}
