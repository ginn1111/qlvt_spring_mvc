package service;

import dao.CategoryDAO;
import entity.Category;
import entity.Category;
import entity.Sector;
import model.CategoryModel;
import model.CategoryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import request_bean.DeletedIdList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    @Autowired
    CategoryDAO categoryDAO;

    public List<Object> searchCategory(String key) {
        List<CategoryModel> categoryModelList = categoryDAO.search(key).stream()
                .map(CategoryModel::new).collect(Collectors.toList());
        List<Integer> dummyList = new ArrayList<Integer>();
        for(int i = 0; i < categoryModelList.size(); i++) {
            dummyList.add(null);
        }
        DeletedIdList deletedIdList = new DeletedIdList(dummyList);
        return Arrays.asList(categoryModelList, deletedIdList);
    }
    public List<Object> getCategoryList() {
        List<CategoryModel> categoryModelList = categoryDAO.getList().stream()
                .map(CategoryModel::new).collect(Collectors.toList());
        List<Integer> dummyList = new ArrayList<Integer>();
        for(int i = 0; i < categoryModelList.size(); i++) {
            dummyList.add(null);
        }
        DeletedIdList deletedIdList = new DeletedIdList(dummyList);
        return Arrays.asList(categoryModelList, deletedIdList);
    }

    public String addCategory(CategoryModel categoryModel) {
        Category category = new Category();

        category.setName(categoryModel.getName());
        category.setSector(new Sector(categoryModel.getSectorModel().getSectorId()));
        category.setStatus(true);

        if(categoryDAO.addNew(category)) {
            return "Thêm danh mục thành công!";
        }
        return "Thêm thất bại, có lỗi xảy ra!";
    }

    public String deleteCategory(DeletedIdList list) {
        List<Category> categoryList = new ArrayList<>();
        Category tmp;
        for (Integer categoryId :
                list.getList()) {
            if (categoryId != null) {
                tmp = new Category();
                tmp.setCategoryId(categoryId);
                categoryList.add(tmp);
            }
        }

        if(categoryDAO.deleteByListId(categoryList)) {
            return "Xoá thành công!";
        }
        return "Có lỗi xảy ra, vui lòng thử lại.";
    }

    public CategoryModel findCategoryById(Integer categoryId) {
        return new CategoryModel(categoryDAO.findById(new Category(categoryId)));
    }

    public String editCategory(CategoryModel categoryModel) {
        Category category = new Category();
        category.setCategoryId(categoryModel.getCategoryId());
        category.setName(categoryModel.getName());
        category.setSector(new Sector(categoryModel.getSectorModel().getSectorId()));
        category.setStatus(true);

        if(categoryDAO.update(category)) {
            return "Cập nhật thành công!";
        }
        return "Cập nhật thất bại!";
    }
    public List<CategoryModel> getCategoryModelList() {
       return categoryDAO.getList()
               .stream()
               .map(CategoryModel::new)
               .collect(Collectors.toList());
    }
}
