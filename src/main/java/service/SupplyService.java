package service;

import dao.SupplyDAO;
import model.SupplyModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplyService {
    @Autowired
    SupplyDAO supplyDAO;

    @ModelAttribute("supplyList")
    public List<SupplyModel> getSupplyModelList() {
        return supplyDAO.getList()
                .stream()
                .map(SupplyModel::new)
                .collect(Collectors.toList());
    }
}
