package service;

import dao.EmployeeDAO;
import entity.Employee;
import model.AccountModel;
import model.EmployeeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import request_bean.DeletedIdEmployeeList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    @Autowired
    EmployeeDAO employeeDAO;
    public List<Object> getEmployeeList() {
        List<EmployeeModel> employeeList = employeeDAO.getList().stream()
                .map(EmployeeModel::new).collect(Collectors.toList());
        List<Integer> dummyList = new ArrayList<Integer>();
        for(int i = 0; i < employeeList.size(); i++) {
            dummyList.add(null);
        }
        DeletedIdEmployeeList deletedIdEmployeeList = new DeletedIdEmployeeList(dummyList);
        return Arrays.asList(employeeList, deletedIdEmployeeList);
    }

    public String addEmployee(EmployeeModel employeeModel) {
        Employee employee = new Employee();
        employee.setName(employeeModel.getName());
        employee.setAddress(employeeModel.getAddress());
        employee.setPhone(employeeModel.getPhone());
        employee.setDob(employeeModel.getDob());
        // default value
        employee.setStatus(true);
        if(employeeDAO.addNew(employee)) {
            return "Thêm nhân viên thành công!";
        }
        return "Thêm nhân viên thất bại, có lỗi xảy ra!";
    }
    
    public String deleteEmployee(DeletedIdEmployeeList list) {
        List<Employee> listEmployee = new ArrayList<Employee>();
        Employee tmp;
        for (Integer maNV :
                list.getList()) {
            if (maNV != null) {
                tmp = new Employee();
                tmp.setEmployeeId(maNV);
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
        EmployeeModel employeeModel = new EmployeeModel(employeeDAO.findById(employee));

        return employeeModel;
    }

    public String editEmployee(EmployeeModel employeeModel) {
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

    public List<AccountModel> getAccountModelListOfEmployee(Integer employeeId) {
        Employee employee = new Employee();
        employee.setEmployeeId(employeeId);
        return employeeDAO.findById(employee)
                .getAccountList()
                .stream()
                .map(AccountModel::new)
                .collect(Collectors.toList());
    }

    public EmployeeModel getEmployee(Integer employeeId) {
        return new EmployeeModel(employeeDAO.findById(new Employee(employeeId)));
    }
}
