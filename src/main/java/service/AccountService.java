package service;

import dao.AccountDAO;
import dao.EmployeeDAO;
import entity.Account;
import entity.Employee;
import entity.Role;
import model.AccountModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.MyUtils;

@Service
public class AccountService {
    @Autowired
    AccountDAO accountDAO;

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
}
