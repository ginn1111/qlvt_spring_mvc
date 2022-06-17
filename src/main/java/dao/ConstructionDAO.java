package dao;

import entity.Construction;
import entity.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ConstructionDAO extends DAO<Construction>{


    public List<Construction> getList() {
        String query = "FROM Construction";
        return super.getList(query);
    }
    public boolean addNew(Construction construction) {
        return super.addNew(construction);
    }

    public boolean update(Construction construction) {

        return super.update(construction);
    }

    @Override
    public boolean deleteById(Construction construction) {
        return false;
    }

    @Override
    public boolean deleteByListId(List<Construction> list) {
        return false;
    }

    @Override
    public Construction findById(Construction construction) {
        return null;
    }
}
