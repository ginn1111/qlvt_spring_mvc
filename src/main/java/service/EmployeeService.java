package service;

import dao.EmployeeDAO;
import entity.Account;
import entity.Employee;
import model.AccountModel;
import model.EmployeeModel;
import model.RoleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import request_bean.DeletedIdList;
import utils.MyUtils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class EmployeeService implements Validation<EmployeeModel> {
    @Autowired
    EmployeeDAO employeeDAO;
    @Autowired
    RoleService roleService;

    public List<Object> searchEmployee(String key) {
        List<EmployeeModel> employeeList = employeeDAO.search(key).stream()
                .map(EmployeeModel::new).collect(Collectors.toList());
        List<Integer> dummyList = new ArrayList<Integer>();
        for(int i = 0; i < employeeList.size(); i++) {
            dummyList.add(null);
        }
        DeletedIdList deletedIdList = new DeletedIdList(dummyList);
        return Arrays.asList(employeeList, deletedIdList);
    }
    public List<Object> getEmployeeList() {
        List<EmployeeModel> employeeList = employeeDAO.getList().stream()
                .map(EmployeeModel::new).collect(Collectors.toList());
        List<Integer> dummyList = new ArrayList<Integer>();
        for(int i = 0; i < employeeList.size(); i++) {
            dummyList.add(null);
        }
        DeletedIdList deletedIdList = new DeletedIdList(dummyList);
        return Arrays.asList(employeeList, deletedIdList);
    }

    public String addEmployee(EmployeeModel employeeModel) {
        String validStr = validate(employeeModel);
        if(validStr != null) {
            return validStr;
        }

        Employee employee = new Employee();
        employee.setName(employeeModel.getName());
        employee.setAddress(employeeModel.getAddress());
        employee.setPhone(employeeModel.getPhone());
        employee.setDob(employeeModel.getDob());
        if(employeeDAO.addNew(employee)) {
            return "Thêm nhân viên thành công!";
        }
        return "Thêm nhân viên thất bại, có lỗi xảy ra!";
    }
    
    public String deleteEmployee(DeletedIdList list) {
        List<Employee> listEmployee = new ArrayList<Employee>();
        Employee tmp;
        for (Integer employeeId : list.getList()) {
            if (employeeId != null) {
                tmp = new Employee();
                tmp.setEmployeeId(employeeId);
                listEmployee.add(tmp);
            }
        }
       if(employeeDAO.deleteByListId(listEmployee)) {
           return "Xoá nhân viên thành công!";
       }
       return "Có lỗi xảy ra, vui lòng thử lại.";
    }

    public EmployeeModel findEmployeeById(Integer employeeId) {
        Employee employee = new Employee();
        employee.setEmployeeId(employeeId);
        return new EmployeeModel(employeeDAO.findById(employee));
    }

    public String editEmployee(EmployeeModel employeeModel) {
        String validStr = validate(employeeModel);
        if(validStr != null) {
            return validStr;
        }
        Employee employee = new Employee();
        employee.setEmployeeId(employeeModel.getEmployeeId());
        employee.setName(employeeModel.getName());
        employee.setAddress(employeeModel.getAddress());
        employee.setPhone(employeeModel.getPhone());
        employee.setDob(employeeModel.getDob());
        employee.setStatus(true);
        if(employeeDAO.update(employee)) {
            return "Sửa nhân viên thành công!";
        }
        return "Sửa nhân viên thất bại!";
    }

    public List<Object> getAccountModelListOfEmployee(Integer employeeId) {
        Employee employee = new Employee();
        employee.setEmployeeId(employeeId);

        List<Account> accountList = (List<Account>) employeeDAO.findById(employee).getAccountList();
        List<AccountModel> accountModelList = new ArrayList<>();
        for (Account account : accountList) {
           if(account.getStatus() == false) continue;
           accountModelList.add(new AccountModel(account));
        }

        Map<Integer, RoleModel> map = new HashMap<>();

        for (AccountModel accountModel : accountModelList) {
            map.put(accountModel.getRoleId(), roleService.findById(accountModel.getRoleId()));
        }

       return Arrays.asList(accountModelList, map);
    }

    public EmployeeModel getEmployee(Integer employeeId) {
        return new EmployeeModel(employeeDAO.findById(new Employee(employeeId)));
    }

    @Override
    public String validate(EmployeeModel employeeModel) {
        if(employeeModel.getName().trim().length() == 0) {
            return "Tên không được để trống!";
        }
        String phone = employeeModel.getPhone();
        if(phone != null && phone.trim().length() != 0) {
            phone = phone.trim();
            Pattern pattern = Pattern.compile(regexPhone);
            Matcher matcher = pattern.matcher(phone);
            if(!matcher.matches()) {
                return "Số điện thoại không hợp lệ";
            }
        }
        return null;
    }
}
