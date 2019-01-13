package lv.lottery.CodeValidator;

import lv.lottery.registration.LotteryController;
import lv.lottery.registration.LotteryDAOImplementation;
import lv.lottery.registration.LotteryRegistration;
import lv.lottery.users.UsersRegistration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.Random;

public class CodeValidator {
    private final static Logger LOGGER = LoggerFactory.getLogger(LotteryController.class);

    public static boolean codeValid(UsersRegistration usersRegistration, LotteryDAOImplementation lotteryDAOImplementation) {


        Optional<LotteryRegistration> wrappedLottery = lotteryDAOImplementation.getById(usersRegistration.getLottery().getId());
        LOGGER.info("code validator 1");
        if (wrappedLottery.isPresent() && usersRegistration.getCode().length() == 16 && usersRegistration.getCode().matches("[0-9]+")) {
            LOGGER.info("code validator 2");
            LotteryRegistration lotteryRegistration = wrappedLottery.get();
            LOGGER.info("code validator 3" + lotteryRegistration);
            Date date = lotteryRegistration.getCreatedDate();
            System.out.println("date: " +  lotteryRegistration.getCreatedDate());



            DateFormat dateFormat = new SimpleDateFormat("ddMMyy");
            String strDate = dateFormat.format(date);
            LOGGER.info("lottery registered" + strDate);
            System.out.println("Code Validator date is: " + strDate);

            String codeDate = usersRegistration.getCode().substring(0, 6);
            return codeDate.equals(strDate);
        }
        return false;
    }
}
//            Date date = lotteryRegistration.getCreatedDate();
//
//            DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
//            String strDate = dateFormat.format(date);
//            System.out.println("Code Validator date is: " + date);
//
//            char[] mailChar = usersRegistration.getEmail().toCharArray();
//
//            if (mailChar.length > 10) {
//                strDate = strDate + mailChar.length;
//            } else {
//                strDate = strDate + "0" + mailChar.length;
//            }
//
//            Random random = new Random();
//
//            for (int i = 0; i < 8; i++) {
//                strDate = strDate + random.nextInt(10);
//            }
//
//        }


//            if(usersRegistration.getCode().equals(strDate))


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



