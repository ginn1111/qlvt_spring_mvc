package service;

import dao.NhanVienDAO;
import model.NhanVienModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import request_bean.ListMaNhanVienMuonXoa;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service()
public class NhanVienService {
    @Autowired
    NhanVienDAO nhanVienDAO;

    public List<Object> getListNhanVien() {
        List<NhanVienModel> listNhanVien = nhanVienDAO.getList().stream()
                .map(NhanVienModel::new).collect(Collectors.toList());
        List<Integer> dummyList = new ArrayList<Integer>();
        for(int i = 0; i < listNhanVien.size(); i++) {
            dummyList.add(null);
        }
        ListMaNhanVienMuonXoa listMaNhanVienMuonXoa = new ListMaNhanVienMuonXoa(dummyList);
        return Arrays.asList(listNhanVien, listMaNhanVienMuonXoa);
    }
}
