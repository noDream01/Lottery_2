package lv.lottery.users;

import lv.lottery.registration.LotteryRegistration;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "DZ_LOTTERY_PARTICIPANTS")
public class UsersRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;
    @Column(name = "email")
    private String email;
    @Column(name = "age")
    private Byte age;
    @Column(name = "code")
    private String code;
    @Column(name = "assigned_lottery_id")
    private Long assignedLotteryId;

//    @ManyToOne
//    @JoinColumn(name = "assigned_lottery_id")
//    private LotteryRegistration lottery;

    @ManyToOne
    @JoinColumn(name ="assigned_lot_id")
    private LotteryRegistration lottery;

    public LotteryRegistration getLottery() {
        return lottery;
    }

    public void setLottery(LotteryRegistration lottery) {
        this.lottery = lottery;
    }

    public UsersRegistration(Long id, String email, Byte age, String code, Long assignedLotteryId, LotteryRegistration lottery) {
        this.id = id;
        this.email = email;
        this.age = age;
        this.code = code;
        this.assignedLotteryId = assignedLotteryId;
        this.lottery = lottery;
    }

    public UsersRegistration(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Byte getAge() {
        return age;
    }

    public void setAge(Byte age) {
        this.age = age;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersRegistration usersRegistration = (UsersRegistration) o;
        return Objects.equals(id, usersRegistration.id) &&
                Objects.equals(email, usersRegistration.email) &&
                Objects.equals(age, usersRegistration.age) &&
                Objects.equals(code, usersRegistration.code) &&
                Objects.equals(assignedLotteryId, usersRegistration.assignedLotteryId) &&
                Objects.equals(lottery.getId(), usersRegistration.lottery.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, age, code, assignedLotteryId, lottery.getId());
    }

    @Override
    public String toString() {
        return "UsersRegistration{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", code='" + code + '\'' +
                ", assignedLotteryId=" + assignedLotteryId +
                ", lottery=" + (lottery != null ? lottery.getId() : "") +
                '}';
    }

    public Long getAssignedLotteryId() {
        return assignedLotteryId;
    }

    public void setAssignedLotteryId(Long assignedLotteryId) {
        this.assignedLotteryId = assignedLotteryId;
    }
}
