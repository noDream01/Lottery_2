//package lv.lottery.users;
//
//import java.util.Objects;
//
//public class UsersView extends UsersRegistration {
//
//    private String lotteryName;
//
//
//    public UsersView() {
//    }
//
//    public UsersView(String lotteryName) {
//        this.lotteryName = lotteryName;
//    }
//
//    public UsersView(Long id, String email, Byte age,String code, Long assignedLotteryId, String lotteryName) {
//        super(id, email, age,code, assignedLotteryId);
//        this.lotteryName = lotteryName;
//    }
//
//
//    public String getLotteryName() {
//        return lotteryName;
//    }
//
//    public void setLotteryName(String lotteryName) {
//        this.lotteryName = lotteryName;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        if (!super.equals(o)) return false;
//        UsersView usersView = (UsersView) o;
//        return Objects.equals(lotteryName, usersView.lotteryName);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(super.hashCode(), lotteryName);
//    }
//
//    @Override
//    public String toString() {
//        return "UsersView{}";
//    }
//}
