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
public class CategoryService implements Validation<CategoryModel> {
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
        String validStr = validate(categoryModel);
        if(validStr != null) {
            return validStr;
        }
        Category category = new Category();

        category.setName(categoryModel.getName());
        category.setSector(new Sector(categoryModel.getSectorModel().getSectorId()));
        category.setStatus(true);

        if(categoryDAO.addNew(category)) {
            return "Th??m danh m???c th??nh c??ng!";
        }
        return "Th??m th???t b???i, c?? l???i x???y ra!";
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
            return "Xo?? th??nh c??ng!";
        }
        return "C?? l???i x???y ra, vui l??ng th??? l???i.";
    }

    public CategoryModel findCategoryById(Integer categoryId) {
        return new CategoryModel(categoryDAO.findById(new Category(categoryId)));
    }

    public String editCategory(CategoryModel categoryModel) {
        String validStr = validate(categoryModel);
        if(validStr != null) {
            return validStr;
        }
        Category category = new Category();
        category.setCategoryId(categoryModel.getCategoryId());
        category.setName(categoryModel.getName());
        category.setSector(new Sector(categoryModel.getSectorModel().getSectorId()));
        category.setStatus(true);

        if(categoryDAO.update(category)) {
            return "C???p nh???t th??nh c??ng!";
        }
        return "C???p nh???t th???t b???i!";
    }
    public List<CategoryModel> getCategoryModelList() {
       return categoryDAO.getList()
               .stream()
               .map(CategoryModel::new)
               .collect(Collectors.toList());
    }

    @Override
    public String validate(CategoryModel categoryModel) {
        if(categoryModel.getName().trim().length() == 0) {
            return "T??n kh??ng ???????c ????? tr???ng!";
        }
        return null;
    }
}
