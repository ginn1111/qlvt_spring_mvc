package service;

import dao.CategoryDAO;
import model.CategoryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    @Autowired
    CategoryDAO categoryDAO;

    public List<CategoryModel> getCategoryModelList() {
       return categoryDAO.getList()
               .stream()
               .map(CategoryModel::new)
               .collect(Collectors.toList());
    }
}
