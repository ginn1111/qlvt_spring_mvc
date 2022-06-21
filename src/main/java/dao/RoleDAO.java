package dao;

import entity.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleDAO extends DAO<Role> {
    public List<Role> getList() {
        String query = "FROM Role";
       return super.getList(query);
    }

    @Override
    public boolean deleteById(Role role) {
        return false;
    }

    @Override
    public boolean deleteByListId(List<Role> list) {
        return false;
    }

    @Override
    public Role findById(Role role) {
        return sessionFactory.getCurrentSession().get(Role.class, role.getRoleId());
    }
}
