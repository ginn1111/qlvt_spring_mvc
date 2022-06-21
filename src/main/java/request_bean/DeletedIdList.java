package request_bean;

import java.util.List;

public class DeletedIdList {
    private List<Integer> list;

    public DeletedIdList(List<Integer> list) {
        this.list = list;
    }

    public DeletedIdList() {}

    public List<Integer> getList() {
        return list;
    }

    public void setList(List<Integer> list) {
        this.list = list;
    }
}
