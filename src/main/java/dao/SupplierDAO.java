package dao;

import entity.Supplier;
import entity.Worker;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SupplierDAO extends DAO<Supplier>{


    public List<Supplier> getList() {
        String query = "FROM Supplier AS E WHERE E.status = true";
        return super.getList(query);
    }

    public boolean addNew(Supplier supplier) {
        return super.addNew(supplier);
    }

    public boolean update(Supplier supplier) {

        return super.update(supplier);
    }



    @Override
    public boolean deleteById(Supplier supplier) {
        return false;
    }

    @Override
    public boolean deleteByListId(List<Supplier> list) {
        return false;
    }

    @Override
    public Supplier findById(Supplier supplier) {
        return null;
    }
}
