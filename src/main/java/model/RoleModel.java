package model;

import entity.Role;

enum RoleName {
    MANAGER("Quản lý"), EMPLOYEE("Nhân viên");

    private final String value;

    RoleName(String value) {
        this.value = value;
    }

    public String getRoleName() {
        return this.value;
    }
}
public class RoleModel {
    private Integer roleId;
    private RoleName roleName;

   public RoleModel(Role role)  {
       this.roleId = role.getRoleId();
       this.roleName = role.getRoleName().equals("QUANLY") ? RoleName.MANAGER : RoleName.EMPLOYEE;
   }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public RoleName getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleName roleName) {
        this.roleName = roleName;
    }

    public RoleModel(Integer roleId, RoleName roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }
}
