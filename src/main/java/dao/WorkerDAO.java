package dao;

import entity.Worker;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WorkerDAO extends DAO<Worker>{
    @Override
    public boolean deleteById(Worker worker) {
        return false;
    }

    public List<Worker> getList() {
       String query = "FROM Worker AS W WHERE W.status = true";
       return super.getList(query);
    }

    @Override
    public boolean deleteByListId(List<Worker> list) {
        return false;
    }

    @Override
    public Worker findById(Worker worker) {
        return null;
    }
}
