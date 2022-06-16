package service;

import dao.NhanVienDAO;
import entity.NhanVien;
import model.NhanVienModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import request_bean.ListMaNhanVienMuonXoa;
import request_bean.NhanVienRequest;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
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

    public String themNhanVien(NhanVienModel nhanVienModel) {
        NhanVien nhanVien = new NhanVien();
        nhanVien.setTen(nhanVienModel.getTen());
        nhanVien.setDiaChi(nhanVienModel.getDiaChi());
        nhanVien.setSDT(nhanVienModel.getSDT());
        nhanVien.setNgaySinh(nhanVienModel.getNgaySinh());
        // default value
        nhanVien.setTrangThai(true);
        if(nhanVienDAO.addNew(nhanVien)) {
            return "Thêm nhân viên thành công!";
        }
        return "Thêm nhân viên thất bại, có lỗi xảy ra!";
    }
    
    public String xoaNhanVien(ListMaNhanVienMuonXoa list) {
        List<NhanVien> listNhanVien = new ArrayList<NhanVien>();
        NhanVien tmp;
        for (Integer maNV :
                list.getList()) {
            if (maNV != null) {
                tmp = new NhanVien();
                tmp.setMaNhanVien(maNV);
                listNhanVien.add(tmp);
            }
        }

       if(nhanVienDAO.deleteByListId(listNhanVien)) {
           return "Xoá nhân viên thành công!";
       }
       return "Có lỗi xảy ra, vui lòng thử lại.";
    }

    public NhanVienModel timNhanVienTheoMa(Integer maNV) {
        NhanVien nhanVien = new NhanVien();
        nhanVien.setMaNhanVien(maNV);
        NhanVienModel nhanVienModel = new NhanVienModel(nhanVienDAO.findById(nhanVien));

        return nhanVienModel;
    }

    public String suaNhanVien(NhanVienModel nhanVienModel) {
        NhanVien nhanVien = new NhanVien();
        nhanVien.setMaNhanVien(nhanVienModel.getMaNhanVien());
        nhanVien.setTen(nhanVienModel.getTen());
        nhanVien.setDiaChi(nhanVienModel.getDiaChi());
        nhanVien.setSDT(nhanVienModel.getSDT());
        nhanVien.setNgaySinh(nhanVienModel.getNgaySinh());
        nhanVien.setTrangThai(true);
        if(nhanVienDAO.update(nhanVien)) {
            return "Sửa nhân viên thành công!";
        }
        return "Sửa nhân viên thất bại!";
    }

    public NhanVienModel getNhanVien(Integer maNV) {
        return new NhanVienModel(nhanVienDAO.findById(new NhanVien(maNV)));
    }
}
