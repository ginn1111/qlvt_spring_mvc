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
            return "Th??m c??ng tr??nh th??nh c??ng!";
        }
        return "Th??m c??ng tr??nh th???t b???i, c?? l???i x???y ra!";
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
            return "Xo?? th??nh c??ng!";
        }
        return "C?? l???i x???y ra, vui l??ng th??? l???i.";
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
            return "C???p nh???t th??nh c??ng!";
        }
        return "C???p nh???t th???t b???i!";
    }

    public List<ConstructionModel> getConstructionModelList() {
        return constructionDAO.getList()
                .stream().map(ConstructionModel::new)
                .collect(Collectors.toList());
    }

    @Override
    public String validate(ConstructionModel constructionModel) {
        if(constructionModel.getName().trim().length() == 0) {
            return "T??n kh??ng ???????c ????? tr???ng!";
        }
        return null;
    }
}
