package dao;

import entity.Construction;
import entity.Employee;
import entity.Supplier;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ConstructionDAO extends DAO<Construction>{

    public List<Construction> getList() {
        String query = "FROM Construction AS C WHERE C.status=true";
        return getList(query);
    }
    @Override
    public boolean deleteById(Construction construction) {
        return false;
    }

    @Override
    public boolean deleteByListId(List<Construction> list) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Construction conTmp;

        try {
            for (Construction con: list) {
                conTmp = session.get(Construction.class, con.getConstructionId());
                conTmp.setStatus(false);
                session.update(conTmp);
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
    public Construction findById(Construction construction) {
        return sessionFactory.getCurrentSession().get(Construction.class, construction.getConstructionId());
    }
}
