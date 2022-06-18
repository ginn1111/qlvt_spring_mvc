package dao;


import entity.Sector;
import entity.Warehouse;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SectorDAO extends DAO<Sector>{
    public List<Sector> getList() {
        String query = "FROM Sector";
        return super.getList(query);
    }


    @Override
    public boolean deleteById(Sector sector) {
        return false;
    }

    @Override
    public boolean deleteByListId(List<Sector> list) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            for (Sector sector:
                    list) {
                session.delete(sector);
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
    public Sector findById(Sector sector) {
        return super.sessionFactory.getCurrentSession().get(Sector.class, sector.getSectorId());
    }

}
