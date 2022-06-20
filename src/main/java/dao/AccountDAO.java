package dao;

import entity.Account;
import entity.Account;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountDAO extends DAO<Account> {

    public List<Account> getList() {
        String query = "FROM Account AS A WHERE A.status = true";
        return getList(query);
    }

    public List<Account> search(String key) {
        return sessionFactory.getCurrentSession()
                .getNamedQuery("timTaiKhoan")
                .setParameter("key", key)
                .list();
    }

    @Override
    public boolean deleteById(Account account) {
        return false;
    }

    @Override
    public boolean deleteByListId(List<Account> list) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Account accountTmp;

        try {
            for (Account account: list) {
                accountTmp = session.get(Account.class, account.getEmail());
                accountTmp.setStatus(false);
                session.update(accountTmp);
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
    public Account findById(Account account) {
        return sessionFactory.getCurrentSession().get(Account.class, account.getEmail());
    }
}
