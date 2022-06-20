package request_bean;

import java.util.List;

public class DeletedEmailList {
    List<String> list;

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public DeletedEmailList() {
    }

    public DeletedEmailList(List<String> list) {
        this.list = list;
    }
}
