package lv.lottery.users;

import lv.lottery.registration.LotteryDAOImplementation;
import lv.lottery.registration.LotteryRegistration;
import lv.lottery.registration.LotteryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

//    private Map<Long, UsersRegistration> userMap = new HashMap<>();

//    private final LotteryService lotteryService;


    private final UsersDAOImplementation usersDAO;
    private final LotteryDAOImplementation lotteryDAO;
    private LotteryRegistration lotteryRegistration;

    private Long lastId = 0L;
//    private  LotteryRegistration lotteryRegistration;



    @Autowired
    public UserService(UsersDAOImplementation usersDAO, LotteryDAOImplementation lotteryDAO){
//        this.lotteryService = lotteryService;
        this.usersDAO = usersDAO;
        this.lotteryDAO = lotteryDAO;

    }

    public Long add(UsersRegistration usersRegistration) {

        Optional<LotteryRegistration> wrappedLottery = lotteryDAO.getById(usersRegistration.getAssignedLotteryId());
        if (wrappedLottery.isPresent()) {
            usersRegistration.setLottery(wrappedLottery.get());
        }

        return usersDAO.insert(usersRegistration);
    }

//    public List<UsersRegistration> users() {
//        return usersDAO.getAll();
//    }

    public Optional<UsersRegistration> get(Long id){
        return usersDAO.getById(id);
    }

//    public Optional<UsersRegistration> getByAssigned(Long assignedId){
//        return usersDAO.getByAssignedId(assignedId);
//    }

//    public UsersRegistration get(Long id) {
//        Optional<UsersRegistration> user = usersDAO.getById(id);
//
//        if (user.isPresent()) {
//            return mapToTaskView(user.get());
//        } else {
//            return null;
//        }
//    }
//
//    private UsersRegistration mapToTaskView(UsersRegistration usersRegistration) {
//        LotteryRegistration lotteryRegistration = lotteryService.get(usersRegistration.getAssignedLotteryId());
//
//        return new UsersView(
//                usersRegistration.getId(),
//                usersRegistration.getEmail(),
//                usersRegistration.getAge(),
//                usersRegistration.getCode(),
//                usersRegistration.getAssignedLotteryId(),
//                lotteryRegistration == null ? null : lotteryRegistration.getTitle());
//    }

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
