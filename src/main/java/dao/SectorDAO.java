package dao;


import entity.Sector;
import entity.Warehouse;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SectorDAO extends DAO<Sector>{

    public List<Sector> search(String key) {
        return sessionFactory.getCurrentSession()
                .getNamedQuery("timKhuVuc")
                .setParameter("key", key)
                .list();
    }
    public List<Sector> getList() {
        String query = "FROM Sector AS S WHERE S.status = true";
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
        Sector sectorTmp;

        try {
            for (Sector sector: list) {
                sectorTmp = session.get(Sector.class, sector.getSectorId());
                sectorTmp.setStatus(false);
                session.delete(sectorTmp);
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
