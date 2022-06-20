package service;

import dao.AccountDAO;
import dao.EmployeeDAO;
import entity.Account;
import entity.Employee;
import entity.Role;
import entity.Account;
import model.AccountModel;
import model.RoleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import request_bean.DeletedEmailList;
import utils.MyUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AccountService {
    @Autowired
    AccountDAO accountDAO;

    @Autowired
    RoleService roleService;

    public List<Object> searchAccount(String key) {
        List<AccountModel> accountModelList = accountDAO.search(key)
                .stream()
                .map(AccountModel::new)
                .collect(Collectors.toList());

        List<String> dummyList = new ArrayList<>();
        for (AccountModel accountModel: accountModelList) {
            dummyList.add(null);
        }
        DeletedEmailList deletedEmailList = new DeletedEmailList(dummyList);

        Map<Integer, RoleModel> map = new HashMap<>();

        for (AccountModel accountModel :
                accountModelList) {
            map.put(accountModel.getRoleId(), roleService.findById(accountModel.getRoleId()));
        }

        return Arrays.asList(accountModelList, deletedEmailList, map);
    }

    public List<Object> getAccountList() {
        List<AccountModel> accountModelList = accountDAO.getList()
                .stream()
                .map(AccountModel::new)
                .collect(Collectors.toList());

        List<String> dummyList = new ArrayList<>();
        for (AccountModel accountModel: accountModelList) {
           dummyList.add(null);
        }
        DeletedEmailList deletedEmailList = new DeletedEmailList(dummyList);

        Map<Integer, RoleModel> map = new HashMap<>();

        for (AccountModel accountModel :
                accountModelList) {
            map.put(accountModel.getRoleId(), roleService.findById(accountModel.getRoleId()));
        }

        return Arrays.asList(accountModelList, deletedEmailList, map);
    }

    public String addNewAccount(AccountModel accountModel) {
        Account account = new Account();

        account.setEmail(accountModel.getEmail());
        account.setPassword(MyUtils.passwordEncoder.encode(accountModel.getPassword()));

        account.setRole(new Role(accountModel.getRoleId()));

        account.setEmployee(new Employee(accountModel.getEmployeeModel().getEmployeeId()));

        if(accountDAO.findById(account) != null) {
            return "Tài khoản đã tồn tại, vui lòng thử lại!";
        }

        if(accountDAO.addNew(account)) {
            return "Thêm tài khoản thành công!";
        }
        return "Thêm tài khoản thất bại, vui lòng thử lại!";
    }

    public AccountModel findAccountModelByEmail(String email) {
        return new AccountModel(accountDAO.findById(new Account(email)));
    }

    public String deleteAccounts(DeletedEmailList list) {
        List<Account> accountList = new ArrayList<>();
        Account tmp;
        for (String email : list.getList()) {
            if (email != null) {
                tmp = new Account();
                tmp.setEmail(email);
                accountList.add(tmp);
            }
        }

        if(accountDAO.deleteByListId(accountList)) {
            return "Xoá tài khoản thành công!";
        }
        return "Có lỗi xảy ra, vui lòng thử lại.";
    }

    public String changePassword(AccountModel accountModel) {
        Account account = new Account();
        account.setEmail(accountModel.getEmail());
        account.setPassword(MyUtils.passwordEncoder.encode(accountModel.getPassword()));
        account.setStatus(true);
        account.setEmployee(new Employee(accountModel.getEmployeeModel().getEmployeeId()));
        account.setRole(new Role(accountModel.getRoleId()));

        if(accountDAO.update(account)) {
            return "Đổi mật khẩu thành công!";
        }
        return "Có lỗi xảy ra, vui lòng thử lại!";
    }

}
