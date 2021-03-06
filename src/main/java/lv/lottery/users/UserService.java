package lv.lottery.users;

import lv.lottery.CodeValidator.CodeValidator;
import lv.lottery.Response.ResponseUserReg;
import lv.lottery.registration.LotteryController;
import lv.lottery.registration.LotteryDAOImplementation;
import lv.lottery.registration.LotteryRegistration;
import lv.lottery.registration.LotteryService;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

//    private Map<Long, UsersRegistration> userMap = new HashMap<>();

//    private final LotteryService lotteryService;
private final static Logger LOGGER = LoggerFactory.getLogger(LotteryController.class);

    private final UsersDAOImplementation usersDAO;
    private final LotteryDAOImplementation lotteryDAO;
    private LotteryRegistration lotteryRegistration;
    private UsersRegistration usersRegistration;

    private Long lastId = 0L;
//    private  LotteryRegistration lotteryRegistration;



    @Autowired
    public UserService(UsersDAOImplementation usersDAO, LotteryDAOImplementation lotteryDAO){
//        this.lotteryService = lotteryService;
        this.usersDAO = usersDAO;
        this.lotteryDAO = lotteryDAO;

    }

    public ResponseUserReg add(UsersRegistration usersRegistration) {
        if(!CodeValidator.lotIdempty(usersRegistration)){
            return new ResponseUserReg("Fail", "Enter Lottery ID and/or Age and/or Email and/or Code");
        }
        ResponseUserReg responseUserReg = new ResponseUserReg();

        Optional<LotteryRegistration> wrappedLottery = lotteryDAO.getById(usersRegistration.getAssignedLotteryId());
        if (wrappedLottery.isPresent()) {
            usersRegistration.setLottery(wrappedLottery.get());
            LOGGER.info("lottery: " + usersRegistration.getLottery());
            if(CodeValidator.lotIdempty(usersRegistration)) {
                wrappedLottery.get().setUsersQty(wrappedLottery.get().getUsers().size() + 1);
            }
            System.out.println("WP test:" + wrappedLottery.get().getUsersQty());
            lotteryDAO.update(wrappedLottery.get());

        }
            System.out.println("UR test: " + usersRegistration.getLottery());
            if (usersRegistration.getAge() < 21) {
                return new ResponseUserReg("Fail", "Participant age less than 21");
            } else if (CodeValidator.requiredData(usersRegistration)) {
                return new ResponseUserReg("Fail", "Please double check required fields, they cannot be left blank.");
            } else if (!CodeValidator.codeValid(usersRegistration, lotteryDAO)) {

                return new ResponseUserReg("Fail", "Code entered is wrong");

            }else if(!CodeValidator.regClosed(usersRegistration, lotteryDAO)){
                return new ResponseUserReg("Fail", "Registration closed");
            }else if(!CodeValidator.uniqueCode(usersRegistration, usersDAO)){

                return new ResponseUserReg("Fail", "Code already been entered");

            }else if(!CodeValidator.emailValid(usersRegistration.getEmail())){
                return new ResponseUserReg("Fail", "Email Format is wrong");
            } else if(!CodeValidator.limitReached(usersRegistration,lotteryDAO)){

                return new ResponseUserReg("Fail", "Participants limit has been reached");
            } else {


                responseUserReg.setStatus("OK");
                usersDAO.insert(usersRegistration);
            }
        return responseUserReg;
        }




    public Optional<UsersRegistration> get(Long id){
        return usersDAO.getById(id);
    }


    public boolean update(UsersRegistration usersRegistration) {
        usersDAO.update(usersRegistration);
        return true;
    }

    public boolean assign(Long userId, Long lotteryId) {

        Optional<UsersRegistration> wrappedUser = this.get(userId);
        Optional<LotteryRegistration> wrappedLottery = lotteryDAO.getById(lotteryId);

        if (wrappedUser.isPresent() && wrappedLottery.isPresent()) {
            UsersRegistration user = wrappedUser.get();
            user.setLottery(wrappedLottery.get());

            this.update(user);
            return true;
        }

        return false;
    }

}
