package lv.lottery.Response;

import java.util.Date;

public class ResponseLotStats {
    private Long id;
    private String title;
    private Date startDate;
    private Date endDate;
    private Integer participants;

    public ResponseLotStats(Long id, String title, Date startDate, Date endDate, Integer participants) {
        this.id = id;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.participants = participants;
    }

    public ResponseLotStats() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getParticipants() {
        return participants;
    }

    public void setParticipants(Integer participants) {
        this.participants = participants;
    }
}
