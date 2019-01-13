//package lv.lottery.CodeValidator;
//
//import lv.lottery.registration.LotteryRegistration;
//import lv.lottery.users.UsersRegistration;
//
//public class codeValidator {
//
//    private LotteryRegistration lotteryRegistration;
//
//    public boolean codeValid(UsersRegistration usersRegistration){
//
//
//        Integer userCode = Integer.parseInt(usersRegistration.getCode());
//
//        Integer numDate = Integer.parseInt(usersRegistration.getLottery().getCreatedDate());
//
//
//
//        if(userCode >= 16){
//            return false;
//        } else if(!usersRegistration.getCode().contains("[0-9]+")){
//            return false;
//        }
//
//        return codeValid(usersRegistration);
//    }
//
//
//}
