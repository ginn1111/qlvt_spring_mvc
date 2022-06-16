package dao;

import java.util.List;

public interface DAO<T> {
    List<T> getList();
    boolean addNew(T t);
    boolean deleteById(T t);
    boolean update(T t);
    boolean deleteByListId(List<T> list);
    T findById(T t);
}
