package dao;

import entity.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
//public class EmployeeDAO implements DAO<Employee> {
//    @Autowired
//    SessionFactory sessionFactory;
//
//    @Override
//    public List<Employee> getList() {
//        List<Employee> listEmployee = sessionFactory
//                .getCurrentSession().createQuery("FROM Employee AS E WHERE E.status = true").list();
//        return listEmployee;
//    }
//
//    @Override
//    public boolean addNew(Employee employee) {
//        Session session = sessionFactory.openSession();
//        Transaction transaction = session.beginTransaction();
//
//        try {
//            session.save(employee);
//            transaction.commit();
//        } catch(Exception ex) {
//           transaction.rollback();
//           ex.printStackTrace();
//           return false;
//        } finally {
//            session.close();
//        }
//        return true;
//    }
//
//    @Override
//    public boolean deleteById(Employee employee) {
//
//        return true;
//    }
//
//    @Override
//    public boolean update(Employee employee) {
//
//        Session session = sessionFactory.openSession();
//        Transaction transaction = session.beginTransaction();
//
//        try {
//            session.update(employee);
//            transaction.commit();
//        } catch(Exception ex) {
//            transaction.rollback();
//            ex.printStackTrace();
//            return false;
//        } finally {
//            session.close();
//        }
//        return true;
//    }
//
//    @Override
//    public boolean deleteByListId(List<Employee> list) {
//        Session session = sessionFactory.openSession();
//        Transaction transaction = session.beginTransaction();
//
//        try {
//            for (Employee employee :
//                    list) {
//                session.delete(employee);
//            }
//            transaction.commit();
//        } catch(Exception ex) {
//            transaction.rollback();
//            ex.printStackTrace();
//            return false;
//        } finally {
//            session.close();
//        }
//        return true;
//    }
//
//    @Override
//    public Employee findById(Employee employee) {
//        return sessionFactory.getCurrentSession().get(Employee.class, employee.getEmployeeId());
//    }
//
//}

@Repository
public class EmployeeDAO extends DAO<Employee> {

    public List<Employee> getList() {
        String query = "FROM Employee AS E WHERE E.status = true";
        return super.getList(query);
    }

    public boolean addNew(Employee employee) {
        return super.addNew(employee);
    }

    @Override
    public boolean deleteById(Employee employee) {
        return true;

    }

    @Override
    public boolean update(Employee employee) {

        return super.update(employee);
    }

    @Override
    public boolean deleteByListId(List<Employee> list) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            for (Employee employee :
                    list) {
                session.delete(employee);
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
