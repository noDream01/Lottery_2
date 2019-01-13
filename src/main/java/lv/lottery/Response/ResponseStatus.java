package lv.lottery.Response;

public class ResponseStatus {

    private String status;

    public ResponseStatus(String status) {
        this.status = status;
    }

    public ResponseStatus() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
