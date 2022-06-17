package dao;

import entity.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAO extends DAO<Employee> {

    public List<Employee> getList() {
        String query = "FROM Employee AS E WHERE E.status = true";
        return getList(query);
    }

    @Override
    public boolean deleteById(Employee employee) {
        return true;
    }

    @Override
    public boolean deleteByListId(List<Employee> list) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Employee eTmp;

        try {
            for (Employee employee : list) {
                eTmp = session.get(Employee.class, employee.getEmployeeId());
                eTmp.setStatus(false);
                session.update(employee);
            }
            transaction.commit();
        } catch(Exception ex) {
            transaction.rollback();
            ex.printStackTrace();
            return false;
        } finally {
            session.close();
        }
        return true;
    }

    @Override
    public Employee findById(Employee employee) {
        return sessionFactory.getCurrentSession().get(Employee.class, employee.getEmployeeId());
    }

}
