package dao;

import entity.Employee;
import entity.Worker;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class WorkerDAO extends DAO<Worker>{

    public List<Worker> getList() {
        String query = "FROM Worker AS E WHERE E.status = true";
        return super.getList(query);
    }

    public boolean addNew(Worker worker) {
        return super.addNew(worker);
    }

    @Override
    public boolean deleteById(Worker worker) {
        return true;

    }

    @Override
    public boolean update(Worker worker) {

        return super.update(worker);
    }

    @Override
    public boolean deleteByListId(List<Worker> list) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            for (Worker worker :
                    list) {
                session.delete(worker);
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
    public Worker findById(Worker worker) {
        return sessionFactory.getCurrentSession().get(Worker.class, worker.getWorkerId());
    }
}
