package dao;

import entity.Supplier;
import entity.Worker;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SupplierDAO extends DAO<Supplier>{

    public List<Supplier> search(String key) {
        return sessionFactory.getCurrentSession()
                .getNamedQuery("timNhaCungCap")
                .setParameter("key", key)
                .list();
    }
    public List<Supplier> getList() {
        String query = "FROM Supplier AS E WHERE E.status = true";
        return super.getList(query);
    }

    @Override
    public boolean deleteById(Supplier supplier) {
        return false;
    }

    @Override
    public boolean deleteByListId(List<Supplier> list) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            for (Supplier sup:
                    list) {
                session.delete(sup);
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
    public Supplier findById(Supplier supplier) {
        return super.sessionFactory.getCurrentSession().get(Supplier.class, supplier.getSupplierId());
    }
}
