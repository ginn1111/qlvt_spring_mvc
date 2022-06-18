package service;


import dao.SectorDAO;
import entity.Sector;
import entity.Warehouse;
import model.SectorModel;
import model.WarehouseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import request_bean.DeletedIdList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SectorService {
    @Autowired
    SectorDAO sectorDAO;
    public List<Object> getWarehouseList() {
        List<SectorModel> sectorModelList = sectorDAO.getList().stream()
                .map(SectorModel::new).collect(Collectors.toList());
        List<Integer> dummyList = new ArrayList<Integer>();
        for(int i = 0; i < sectorModelList.size(); i++) {
            dummyList.add(null);
        }
        DeletedIdList deletedIdList = new DeletedIdList(dummyList);
        return Arrays.asList(sectorModelList, deletedIdList);
    }

    public String addSector(SectorModel sectorModel) {
        Sector sector = new Sector();
        sector.setName(sectorModel.getName());

        if(sectorDAO.addNew(sector)) {
            return "Thêm khu vực thành công!";
        }
        return "Thêm thất bại, có lỗi xảy ra!";
    }

    public String deleteSector(DeletedIdList list) {
        List<Sector> sectorList = new ArrayList<>();
        Sector tmp;
        for (Integer sectorId :
                list.getList()) {
            if (sectorId != null) {
                tmp = new Sector();
                tmp.setSectorId(sectorId);
                sectorList.add(tmp);
            }
        }

        if(sectorDAO.deleteByListId(sectorList)) {
            return "Xoá thành công!";
        }
        return "Có lỗi xảy ra, vui lòng thử lại.";
    }

    public SectorModel findSectorById(Integer sectorId) {
        Sector sector = new Sector();
        sector.setSectorId(sectorId);
        return new SectorModel(sectorDAO.findById(sector));
    }

    public String editSector(SectorModel sectorModel) {
        Sector sector = new Sector();
        sector.setSectorId(sectorModel.getSectorId());
        sector.setName(sectorModel.getName());
        if(sectorDAO.update(sector)) {
            return "Cập nhật thành công!";
        }
        return "Cập nhật thất bại!";
    }
}
