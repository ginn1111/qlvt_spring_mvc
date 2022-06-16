package dao;

import entity.VaiTro;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Repository
public class VaiTroDAO implements  DAO<VaiTro> {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<VaiTro> getList() {
       List<VaiTro> list = sessionFactory.getCurrentSession().createQuery("FROM VaiTro").list();
       return list;
    }

    @Override
    public boolean addNew(VaiTro vaiTro) {
        return false;
    }

    @Override
    public boolean deleteById(VaiTro vaiTro) {
        return false;
    }

    @Override
    public boolean update(VaiTro vaiTro) {
        return false;
    }

    @Override
    public boolean deleteByListId(List<VaiTro> list) {
        return false;
    }

    @Override
    public VaiTro findById(VaiTro vaiTro) {
        return null;
    }
}
