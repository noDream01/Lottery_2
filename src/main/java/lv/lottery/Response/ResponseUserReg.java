package lv.lottery.Response;

public class ResponseUserReg {
    private String status;
    private String reason;

    public ResponseUserReg(String status, String reason) {
        this.status = status;
        this.reason = reason;
    }

    public ResponseUserReg() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
