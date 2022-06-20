package dao;

import entity.Employee;
import entity.Worker;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WorkerDAO extends DAO<Worker>{
    @Override
    public boolean deleteById(Worker worker) {
        return false;
    }

    public List<Worker> search(String key) {
        return sessionFactory.getCurrentSession()
                .getNamedQuery("timCongNhan")
                .setParameter("key", key)
                .list();
    }

    public List<Worker> getList() {
       String query = "FROM Worker AS W WHERE W.status = true";
       return super.getList(query);
    }

    @Override
    public boolean deleteByListId(List<Worker> list) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            for (Worker worker:
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
