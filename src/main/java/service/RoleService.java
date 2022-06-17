package service;

import dao.RoleDAO;
import entity.Role;
import model.RoleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService {
    @Autowired
    RoleDAO roleDAO;

    public List<RoleModel> getRoleList() {
        return roleDAO.getList().stream().map(RoleModel::new).collect(Collectors.toList());
    }
}
