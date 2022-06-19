package dao;


import entity.Category;
import entity.Sector;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryDAO extends DAO<Category>{
    public List<Category> getList() {
        String query = "FROM Category AS C WHERE C.status = true";
        return super.getList(query);
    }


    @Override
    public boolean deleteById(Category category) {

        return false;
    }

    @Override
    public boolean deleteByListId(List<Category> list) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Category categoryTmp;

        try {
            for (Category category: list) {
                categoryTmp = session.get(Category.class, category.getCategoryId());
                categoryTmp.setStatus(false);
                session.delete(categoryTmp);
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
    public Category findById(Category category) {
        return super.sessionFactory.getCurrentSession().get(Category.class, category.getCategoryId());
    }
}
