package service;

import dao.EmployeeDAO;
import entity.Account;
import entity.Employee;
import model.AccountModel;
import model.EmployeeModel;
import model.InformationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import request_bean.ChangeAccount;
import utils.MyUtils;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class InformationService implements Validation<ChangeAccount>{
    @Autowired
    EmployeeDAO employeeDAO;
    @Autowired
    AccountService accountService;

    public String updateInformation(EmployeeModel employeeModel) {
        Employee employee = new Employee();
        employee.setEmployeeId(employeeModel.getEmployeeId());
        employee.setName(employeeModel.getName());
        employee.setAddress(employeeModel.getAddress());
        employee.setPhone(employeeModel.getPhone());
        employee.setDob(employeeModel.getDob());
        employee.setStatus(true);

        if(employeeDAO.update(employee)) {
            return "Cập nhật thông tin thành công!";
        }
        return "Cập nhật thất bại, vui lòng thử lại!";
    }

    public String changePassword(ChangeAccount changeAccount) {
       String validStr = validate(changeAccount);
       if(validStr != null) {
           return validStr;
       }

       AccountModel accountModel = accountService.findAccountModelByEmail(changeAccount.getEmail());
       accountModel.setPassword(changeAccount.getNewPassword());

       return accountService.changePassword(accountModel);

    }

    @Override
    public String validate(ChangeAccount changeAccount) {
        AccountModel accountModel = accountService.findAccountModelByEmail(changeAccount.getEmail());
        if(!MyUtils.passwordEncoder.matches(changeAccount.getOldPassword(), accountModel.getPassword())) {
            return "Sai mật khẩu!";
        }

        Pattern pattern = Pattern.compile(regexPassword);
        Matcher match = pattern.matcher(changeAccount.getNewPassword());
        if(!match.matches()) {
            return "Mật khẩu cần có ít nhất một chữ cái thường, 1 chữ cái in, 1 chữ số," +
                    " không có khoảng trắng và có ít nhất 8 ký tự!";
        }
        return null;
    }
}
