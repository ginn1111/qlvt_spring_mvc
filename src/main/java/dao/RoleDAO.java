package dao;

import entity.Role;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleDAO implements  DAO<Role> {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Role> getList() {
       List<Role> list = sessionFactory.getCurrentSession().createQuery("FROM Role").list();
       return list;
    }

    @Override
    public boolean addNew(Role role) {
        return false;
    }

    @Override
    public boolean deleteById(Role role) {
        return false;
    }

    @Override
    public boolean update(Role role) {
        return false;
    }

    @Override
    public boolean deleteByListId(List<Role> list) {
        return false;
    }

    @Override
    public Role findById(Role role) {
        return null;
    }
}
