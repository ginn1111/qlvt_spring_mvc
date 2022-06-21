package service;

import dao.StatisticSupplyDAO;
import entity.Supply;
import entity.statistic.StatisticSupply;
import model.StatisticSupplyModel;
import model.SupplyModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatisticSupplyService {
    @Autowired
    StatisticSupplyDAO statisticSupplyDAO;
    @Autowired
    SupplyService supplyService;

    public List<StatisticSupplyModel> getTop10InPortSupplyInMonth() {
        List<StatisticSupplyModel> statisticSupplyModelList = new ArrayList<>();
        List<StatisticSupply> statisticSupplyList = statisticSupplyDAO.getTop10InPortInMonth();
        SupplyModel supplyModelTmp;

        for (StatisticSupply supply : statisticSupplyList) {
           supplyModelTmp = supplyService.findSupplyById(supply.getSupplyId());
           statisticSupplyModelList.add(new StatisticSupplyModel(supplyModelTmp.getName(), supply.getQuantity()));
        }

        return statisticSupplyModelList;
    }

    public List<StatisticSupplyModel> getTop10ExPortSupplyInMonth() {
        List<StatisticSupplyModel> statisticSupplyModelList = new ArrayList<>();
        List<StatisticSupply> statisticSupplyList = statisticSupplyDAO.getTop10ExPortInMonth();
        SupplyModel supplyModelTmp;

        for (StatisticSupply supply : statisticSupplyList) {
            supplyModelTmp = supplyService.findSupplyById(supply.getSupplyId());
            statisticSupplyModelList.add(new StatisticSupplyModel(supplyModelTmp.getName(), supply.getQuantity()));
        }

        return statisticSupplyModelList;
    }
}
