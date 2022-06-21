package dao;

import entity.Supplier;
import entity.Warehouse;
import model.WarehouseModel;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class WarehouseDAO extends DAO<Warehouse> {

    public List<Warehouse> search(String key) {
        return sessionFactory.getCurrentSession()
                .getNamedQuery("timKho")
                .setParameter("key", key)
                .list();
    }
    public List<Warehouse> getList() {
        String query = "FROM Warehouse";
        return super.getList(query);
    }


    @Override
    public boolean deleteById(Warehouse warehouse) {
        return false;
    }

    @Override
    public boolean deleteByListId(List<Warehouse> list) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            for (Warehouse warehouse:
                    list) {
                session.delete(warehouse);
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
    public Warehouse findById(Warehouse warehouse) {
        return super.sessionFactory.getCurrentSession().get(Warehouse.class, warehouse.getWarehouseId());
    }
}
