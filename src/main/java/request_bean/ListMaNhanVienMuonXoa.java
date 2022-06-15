package request_bean;

import java.util.List;

public class ListMaNhanVienMuonXoa {
    private List<Integer> list;

    public ListMaNhanVienMuonXoa(List<Integer> list) {
        this.list = list;
    }

    public ListMaNhanVienMuonXoa() {}

    public List<Integer> getList() {
        return list;
    }

    public void setList(List<Integer> list) {
        this.list = list;
    }
}
