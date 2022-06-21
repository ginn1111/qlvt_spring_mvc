package dao;

import entity.statistic.StatisticSupply;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.List;

@Repository
public class StatisticSupplyDAO extends DAO<StatisticSupply>{
    public List<StatisticSupply> getTop10InPortInMonth() {
        return sessionFactory.getCurrentSession()
                .getNamedQuery("getTopVatTuNhapTrongThang")
                .setParameter("num", 10)
                .setParameter("m", Calendar.getInstance().get(Calendar.MONTH) + 1)
                .setParameter("y", Calendar.getInstance().get(Calendar.YEAR))
                .list();
    }
    public List<StatisticSupply> getTop10ExPortInMonth() {
        return sessionFactory.getCurrentSession()
                .getNamedQuery("getTopVatTuXuatTrongThang")
                .setParameter("num", 10)
                .setParameter("m", Calendar.getInstance().get(Calendar.MONTH) + 1)
                .setParameter("y", Calendar.getInstance().get(Calendar.YEAR))
                .list();
    }
    @Override
    public boolean deleteById(StatisticSupply statisticSupply) {
        return false;
    }

    @Override
    public boolean deleteByListId(List<StatisticSupply> list) {
        return false;
    }

    @Override
    public StatisticSupply findById(StatisticSupply statisticSupply) {
        return null;
    }
}
