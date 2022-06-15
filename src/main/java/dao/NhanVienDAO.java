package dao;

import entity.NhanVien;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import utils.HibernateUtils;

import javax.transaction.Transactional;
import java.util.List;

@Repository()
public class NhanVienDAO implements DAO {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<NhanVien> getList() {
        List<NhanVien> listNhanVien = sessionFactory
                .getCurrentSession().createQuery("FROM NhanVien").list();
        return listNhanVien;
    }

    @Override
    public boolean addNew() {
        return false;
    }

    @Override
    public boolean deleteById() {
        return false;
    }

    @Override
    public boolean updateById() {
        return false;
    }
}
