package dao;

import org.springframework.stereotype.Repository;

import java.util.List;

public interface DAO {
    List getList();
    boolean addNew();
    boolean deleteById();
    boolean updateById();
}
