package lv.lottery.CodeValidator;

import lv.lottery.registration.LotteryController;
import lv.lottery.registration.LotteryDAOImplementation;
import lv.lottery.registration.LotteryRegistration;
import lv.lottery.users.UsersDAOImplementation;
import lv.lottery.users.UsersRegistration;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

            String emailCheck = usersRegistration.getEmail();
            Integer emailIntCheck = emailCheck.length();
            String emailStCheck = emailIntCheck.toString();

            String firstPart = strDate + emailStCheck;

            String codeDate = usersRegistration.getCode().substring(0, 8);
            return codeDate.equals(firstPart);
        }
        return false;
    }

    public static boolean uniqueCode(UsersRegistration usersRegistration, UsersDAOImplementation usersDAOImplementation){

        List<UsersRegistration> userList = usersDAOImplementation.getAll();

        for (int i =0; i< userList.size(); i++){
            UsersRegistration userReg = userList.get(i);
            if(usersRegistration.getCode().equals(userReg.getCode())){
                return false;
            }
        }

        return true;

    }

    public static boolean requiredData(UsersRegistration usersRegistration){

        return usersRegistration.getCode() != null &&
                usersRegistration.getEmail() !=null &&
                usersRegistration.getEmail().isEmpty() &&
                usersRegistration.getAssignedLotteryId() != null &&
                usersRegistration.getAge() != null;
    }

    public static boolean limitReached(UsersRegistration usersRegistration, LotteryDAOImplementation lotteryDAOImplementation){

        Optional<LotteryRegistration> wrappedLottery = lotteryDAOImplementation.getById(usersRegistration.getLottery().getId());

        if(wrappedLottery.isPresent()){
            LotteryRegistration lotteryRegistration = wrappedLottery.get();

            Integer actualQty = usersRegistration.getLottery().getUsersQty();
            LOGGER.info("code validator 2" + actualQty);
            Integer currQty = lotteryRegistration.getLimit();
            LOGGER.info("code validator 2" + currQty);

            if(actualQty > currQty){
                return false;
            }

        }

        return true;

    }

    public static boolean emailValid(String email){
        String EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
        Pattern pattern;
        Matcher matcher;
        pattern = Pattern.compile(EMAIL_REGEX,Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean regClosed(UsersRegistration usersRegistration, LotteryDAOImplementation lotteryDAOImplementation){
        Optional<LotteryRegistration> wrappedLottery = lotteryDAOImplementation.getById(usersRegistration.getLottery().getId());
        if(wrappedLottery.isPresent()){
            LotteryRegistration lotteryRegistration = wrappedLottery.get();

            if(!lotteryRegistration.getRegStatus()){
                LOGGER.info("Lottery Registration Status: " + lotteryRegistration.getRegStatus());
                return false;
            }

        }

        return true;
    }

    public static boolean lotIdempty(UsersRegistration usersRegistration){

        if(usersRegistration.getAssignedLotteryId() == null
                && (usersRegistration.getCode() == null || usersRegistration.getCode().isEmpty())
                && (usersRegistration.getEmail() == null || usersRegistration.getEmail().isEmpty())){
            LOGGER.info("Lottery ID: " + usersRegistration.getAssignedLotteryId());
            return false;
        } else if(usersRegistration.getAge() == null){
            return false;
        }
        return true;
    }
}





