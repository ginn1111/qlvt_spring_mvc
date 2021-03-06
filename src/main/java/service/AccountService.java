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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class AccountService implements  Validation<AccountModel> {
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
        String validStr = validate(accountModel);
        if(validStr != null) {
            return validStr;
        }
        Account account = new Account();

        account.setEmail(accountModel.getEmail());
        account.setPassword(MyUtils.passwordEncoder.encode(accountModel.getPassword()));

        account.setRole(new Role(accountModel.getRoleId()));

        account.setEmployee(new Employee(accountModel.getEmployeeModel().getEmployeeId()));

        if(accountDAO.findById(account) != null) {
            return "T??i kho???n ???? t???n t???i, vui l??ng th??? l???i!";
        }

        if(accountDAO.addNew(account)) {
            return "Th??m t??i kho???n th??nh c??ng!";
        }
        return "Th??m t??i kho???n th???t b???i, vui l??ng th??? l???i!";
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
            return "Xo?? t??i kho???n th??nh c??ng!";
        }
        return "C?? l???i x???y ra, vui l??ng th??? l???i.";
    }

    public String changePassword(AccountModel accountModel) {
        Account account = new Account();
        account.setEmail(accountModel.getEmail());
        account.setPassword(MyUtils.passwordEncoder.encode(accountModel.getPassword()));
        account.setStatus(true);
        account.setEmployee(new Employee(accountModel.getEmployeeModel().getEmployeeId()));
        account.setRole(new Role(accountModel.getRoleId()));

        if(accountDAO.update(account)) {
            return "?????i m???t kh???u th??nh c??ng!";
        }
        return "C?? l???i x???y ra, vui l??ng th??? l???i!";
    }

    @Override
    public String validate(AccountModel accountModel) {
        if(accountModel.getEmail().trim().length() == 0) {
            return "Email kh??ng ???????c ????? tr???ng!";
        }
        Pattern pattern = Pattern.compile(regexEmail);
        Matcher matcher = pattern.matcher(accountModel.getEmail());
        if(!matcher.matches()) {
            return "Email kh??ng h???p l???!";
        }
        Pattern patternPassword = Pattern.compile(regexPassword);
        Matcher matchPassword = patternPassword.matcher(accountModel.getPassword());
        if(!matchPassword.matches()) {
            return "M???t kh???u c???n c?? ??t nh???t m???t ch??? c??i th?????ng, 1 ch??? c??i in, 1 ch??? s???," +
                    " kh??ng c?? kho???ng tr???ng v?? c?? ??t nh???t 8 k?? t???!";
        }
        return null;
    }
}
