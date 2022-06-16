package service;

import dao.VaiTroDAO;
import entity.VaiTro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VaiTroService {
    @Autowired
    VaiTroDAO vaiTroDAO;

    public List<VaiTro> getListVaiTro() {
        return vaiTroDAO.getList();
    }
}
