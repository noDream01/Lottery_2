package lv.lottery.Response;

public class ResponseStatus {

    private String status;
    private String reason;

    public ResponseStatus(String status, String reason) {
        this.status = status;
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
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
