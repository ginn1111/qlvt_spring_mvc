package controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import entity.NhanVien;
import entity.VaiTro;
import model.NhanVienModel;
import model.TaiKhoanModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import request_bean.ListMaNhanVienMuonXoa;
import request_bean.NhanVienRequest;
import service.NhanVienService;
import service.VaiTroService;
import utils.MyUtils;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Controller
@Transactional
@RequestMapping("quanly")
public class QuanLyController {
    private static String message = "";
    private static NhanVienModel nhanvien = new NhanVienModel();
    private static TaiKhoanModel taikhoan = new TaiKhoanModel();
    private static String link;
    private static String btnTitle;
    @InitBinder
    public void customizeBinding (WebDataBinder binder) {
        MyUtils.DF_DATE.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(MyUtils.DF_DATE, false));
    }
    @Autowired
    NhanVienService nhanVienService;
    @Autowired
    VaiTroService vaiTroService;
    @RequestMapping("index")
    public String index() {
        return "quanly/dash-board";
    }

    // GET quanly/nhan-vien.htm
    @RequestMapping("nhan-vien")
    public String nhanvien(ModelMap model) {
        List<Object> kqNVService = nhanVienService.getListNhanVien();
        List<NhanVienModel> listNhanVien = (List<NhanVienModel>)kqNVService.get(0);
        ListMaNhanVienMuonXoa listMaNhanVienMuonXoa = (ListMaNhanVienMuonXoa) kqNVService.get(1);

        model.addAttribute("listNhanVien", listNhanVien);
        model.addAttribute("listNhanVienMuonXoa", listMaNhanVienMuonXoa);
        model.addAttribute("nhanvien", nhanvien);
        model.addAttribute("taikhoan", taikhoan);
        model.addAttribute("link", link);
        model.addAttribute("btnTitle", btnTitle);
        model.addAttribute("message", message);
        message = "";
        return "quanly/nhan-vien";
    }

    // GET quanly/nhan-vien.htm?new
    @RequestMapping(value="nhan-vien", params = "new")
    public String newNhanVien() {

        nhanvien = new NhanVienModel();
        btnTitle = "Thêm";
        link="quanly/nhan-vien.htm?insert";
        return "redirect:/quanly/nhan-vien.htm";
    }

    // POST quanly/nhan-vien.htm?insert
    @RequestMapping(value="nhan-vien", params = "insert", method = RequestMethod.POST)
    public String themNhanVien(@ModelAttribute("nhanvien") NhanVienModel nhanvien) {
        // Todo: validate
        message = nhanVienService.themNhanVien(nhanvien);
        return "redirect:/quanly/nhan-vien.htm";
    }

    // GET quanly/nhan-vien/{maNV}.htm?update
    @RequestMapping(value="nhan-vien/{maNV}", params = "update")
    public String suaNhanVien(@PathVariable("maNV") Integer maNV) {
        nhanvien = nhanVienService.timNhanVienTheoMa(maNV);
        link = "quanly/nhan-vien.htm?update";
        btnTitle = "Sửa";
        return "redirect:/quanly/nhan-vien.htm";
    }

    // GET quanly/nhan-vien.htm?update
    @RequestMapping(value="nhan-vien", params = "update")
    public String suaNhanVien(@ModelAttribute("nhanvien") NhanVienModel nhanvien) {
        message = nhanVienService.suaNhanVien(nhanvien);
        return "redirect:/quanly/nhan-vien.htm";
    }

    // POST quanly/nhan-vien.htm?delete
    @RequestMapping(value="nhan-vien", params = "delete", method = RequestMethod.POST)
    public String xoaNhanVien(@ModelAttribute("listNhanVienMuonXoa") ListMaNhanVienMuonXoa listNhanVienMuonXoa) {
        message = nhanVienService.xoaNhanVien(listNhanVienMuonXoa);
        return "redirect:/quanly/nhan-vien.htm";
    }

    // GET quanly/nhan-vien/{maNV}.htm?update
    @RequestMapping(value="nhan-vien/{maNV}", params = "accounts")
    public String taiKhoanNhanVien(@PathVariable("maNV") Integer maNV, HttpSession httpSession) {
        httpSession.setAttribute("nhanvien", nhanVienService.getNhanVien(maNV));
        link = "quanly/nhan-vien.htm?accounts";
        return "redirect:/quanly/nhan-vien.htm";
    }

    @RequestMapping(value="nhan-vien", params = "accounts", method = RequestMethod.POST)
    public String taiKhoanNhanVien(@ModelAttribute("taikhoan") TaiKhoanModel taikhoan) {
        System.out.println(taikhoan.getEmail() + " " + taikhoan.getMaVaiTro());
        return "redirect:/quanly/nhan-vien.htm";
    }

    @ModelAttribute("listVaiTro")
    public List<VaiTro> getListVaiTro() {
        return vaiTroService.getListVaiTro();
    }
}
