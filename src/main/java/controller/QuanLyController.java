package controller;

import model.NhanVienModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import request_bean.ListMaNhanVienMuonXoa;
import service.NhanVienService;

import javax.transaction.Transactional;
import java.util.List;

@Controller
@Transactional
@RequestMapping("quanly")
public class QuanLyController {

    @Autowired
    NhanVienService nhanVienService;
    @RequestMapping("index")
    public String index() {
        return "quanly/dash-board";
    }

    @RequestMapping("nhan-vien")
    public String nhanvien(ModelMap model) {
        List<Object> kqNVService = nhanVienService.getListNhanVien();
        List<NhanVienModel> listNhanVien = (List<NhanVienModel>)kqNVService.get(0);
        ListMaNhanVienMuonXoa listMaNhanVienMuonXoa = (ListMaNhanVienMuonXoa) kqNVService.get(1);

        model.addAttribute("listNhanVien", listNhanVien);
        model.addAttribute("listNhanVienMuonXoa", listMaNhanVienMuonXoa);
        return "quanly/nhan-vien";
    }

    @RequestMapping("tai-khoan-nhan-vien")
    public String taiKhoanNhanVien() {
        return "quanly/tai-khoan-nhan-vien";
    }
}
