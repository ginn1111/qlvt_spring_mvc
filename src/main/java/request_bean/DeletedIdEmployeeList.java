package request_bean;

import java.util.List;

public class DeletedIdEmployeeList {
    private List<Integer> list;

    public DeletedIdEmployeeList(List<Integer> list) {
        this.list = list;
    }

    public DeletedIdEmployeeList() {}

    public List<Integer> getList() {
        return list;
    }

    public void setList(List<Integer> list) {
        this.list = list;
    }
}
