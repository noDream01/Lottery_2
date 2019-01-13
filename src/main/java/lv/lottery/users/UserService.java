package lv.lottery.users;

import lv.lottery.CodeValidator.CodeValidator;
import lv.lottery.Response.ResponseUserReg;
import lv.lottery.registration.LotteryDAOImplementation;
import lv.lottery.registration.LotteryRegistration;
import lv.lottery.registration.LotteryService;
import org.apache.catalina.User;
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

        ResponseUserReg responseUserReg = new ResponseUserReg();

        Optional<LotteryRegistration> wrappedLottery = lotteryDAO.getById(usersRegistration.getAssignedLotteryId());
        if (wrappedLottery.isPresent()) {
            wrappedLottery.get().setUsersQty(wrappedLottery.get().getUsers().size() + 1);
            usersRegistration.setLottery(wrappedLottery.get());
            if (usersRegistration.getAge() < 21) {
                return new ResponseUserReg("Fail", "Participant age less than 21");
            } else if (usersRegistration.getEmail().isEmpty() &&
                    usersRegistration.getEmail() == null &&
                    usersRegistration.getAge() == null &&
                    usersRegistration.getCode() == null) {
                return new ResponseUserReg("Fail", "Please double check required fields, they cannot be left blank.");
            } else if (!CodeValidator.codeValid(usersRegistration, lotteryDAO)) {

                return new ResponseUserReg("Fail", "Code entered is wrong");

            }else if(!CodeValidator.uniqueCode(usersRegistration, usersDAO)){

                return new ResponseUserReg("Fail", "Code already been entered");

            } else {

                lotteryDAO.update(wrappedLottery.get());
                responseUserReg.setStatus("OK");
                usersRegistration.setLottery(wrappedLottery.get());
                usersDAO.insert(usersRegistration);
            }

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
