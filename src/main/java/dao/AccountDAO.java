package dao;

import entity.Account;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountDAO extends DAO<Account> {

    @Override
    public boolean deleteById(Account account) {
        return false;
    }

    @Override
    public boolean deleteByListId(List<Account> list) {
        return false;
    }

    @Override
    public Account findById(Account account) {
        return sessionFactory.getCurrentSession().get(Account.class, account.getEmail());
    }
}
