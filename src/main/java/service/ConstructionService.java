package service;


import dao.ConstructionDAO;
import dao.EmployeeDAO;
import entity.Construction;
import entity.Employee;
import model.AccountModel;
import model.ConstructionModel;
import model.EmployeeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import request_bean.DeletedIdList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConstructionService implements Validation<ConstructionModel> {
    @Autowired
    ConstructionDAO constructionDAO;


    public List<Object> searchConstruction(String key) {
        List<ConstructionModel> constructionList = constructionDAO.search(key).stream()
                .map(ConstructionModel::new).collect(Collectors.toList());
        List<Integer> dummyList = new ArrayList<Integer>();
        for(int i = 0; i < constructionList.size(); i++) {
            dummyList.add(null);
        }
        DeletedIdList deletedConstructionIdList = new DeletedIdList(dummyList);
        return Arrays.asList(constructionList, deletedConstructionIdList);
    }
    public List<Object> getConstructionList() {
        List<ConstructionModel> constructionList = constructionDAO.getList().stream()
                .map(ConstructionModel::new).collect(Collectors.toList());
        List<Integer> dummyList = new ArrayList<Integer>();
        for(int i = 0; i < constructionList.size(); i++) {
            dummyList.add(null);
        }
        DeletedIdList deletedConstructionIdList = new DeletedIdList(dummyList);
        return Arrays.asList(constructionList, deletedConstructionIdList);
    }

    public String addConstruction(ConstructionModel constructionModel) {
        String validStr = validate(constructionModel);
        if(validStr != null) {
            return validStr;
        }
        Construction construction = new Construction();
        construction.setName(constructionModel.getName());

        // default value
        construction.setStatus(true);
        if(constructionDAO.addNew(construction)) {
            return "Thêm công trình thành công!";
        }
        return "Thêm công trình thất bại, có lỗi xảy ra!";
    }

    public String deleteConstruction(DeletedIdList list) {
        List<Construction> constructionList = new ArrayList<>();
        Construction tmp;
        for (Integer constructionId :
                list.getList()) {
            if (constructionId != null) {
                tmp = new Construction();
                tmp.setConstructionId(constructionId);
                constructionList.add(tmp);
            }
        }

        if(constructionDAO.deleteByListId(constructionList)) {
            return "Xoá thành công!";
        }
        return "Có lỗi xảy ra, vui lòng thử lại.";
    }

    public ConstructionModel findConstructionById(Integer constructionId) {
        Construction construction = new Construction();
        construction.setConstructionId(constructionId);
        return new ConstructionModel(constructionDAO.findById(construction));
    }

    public String editConstruction(ConstructionModel constructionModel) {
        String validStr = validate(constructionModel);
        if(validStr != null) {
            return validStr;
        }
        Construction construction = new Construction();
        construction.setConstructionId(constructionModel.getConstructionId());
        construction.setName(constructionModel.getName());
        construction.setStatus(true);
        if(constructionDAO.update(construction)) {
            return "Cập nhật thành công!";
        }
        return "Cập nhật thất bại!";
    }

    public List<ConstructionModel> getConstructionModelList() {
        return constructionDAO.getList()
                .stream().map(ConstructionModel::new)
                .collect(Collectors.toList());
    }

    @Override
    public String validate(ConstructionModel constructionModel) {
        if(constructionModel.getName().trim().length() == 0) {
            return "Tên không được để trống!";
        }
        return null;
    }
}
