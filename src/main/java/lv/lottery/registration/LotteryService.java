package lv.lottery.registration;

import lv.lottery.users.UsersDAOImplementation;
import lv.lottery.users.UsersRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class LotteryService {

//    private Map<Long, LotteryRegistration> lotteryStorage = new HashMap<>();
    private final LotteryDAOImplementation lotteryDAOImplementation;
    private final UsersDAOImplementation usersDAOImplementation;

    private LotteryRegistration lotteryRegistration;

    @Autowired
    public LotteryService(LotteryDAOImplementation lotteryDAOImplementation, UsersDAOImplementation usersDAOImplementation){
        this.lotteryDAOImplementation = lotteryDAOImplementation;
        this.usersDAOImplementation = usersDAOImplementation;
    }

//    private Long lastId = 0L;

    public Long addLottery(LotteryRegistration lotteryRegistration){
        lotteryRegistration.setCreatedDate(new Date());
        lotteryRegistration.setRegStatus(true);

//        lastId++;
//        lotteryRegistration.setId(lastId);

        return lotteryDAOImplementation.insert(lotteryRegistration);

    }

    public void stopLotReg(Long id){
        Optional<LotteryRegistration> wrappedLottery = lotteryDAOImplementation.getById(id);

        if(wrappedLottery.isPresent()){
            lotteryRegistration = wrappedLottery.get();
            lotteryRegistration.setEndDate(new Date());
            lotteryRegistration.setRegStatus(false);
            lotteryDAOImplementation.update(lotteryRegistration);

        }
        else {
            System.out.println("Lottery with id "  + id + " , does not exist in DB");
        }
    }

    public void winnerLotUser(Long id){
        Optional<LotteryRegistration> wrappedLottery = lotteryDAOImplementation.getById(id);

        if(wrappedLottery.isPresent()){
            lotteryRegistration = wrappedLottery.get();
            Random random = new Random();
        }
    }

    public List<LotteryRegistration> get(){

        return lotteryDAOImplementation.getAll();
    }



    public Optional<LotteryRegistration> get(Long id){

        return lotteryDAOImplementation.getById(id);

    }

    public void update(Long id, LotteryRegistration lotteryRegistration){
        lotteryRegistration.setId(id);
        lotteryDAOImplementation.update(lotteryRegistration);
    }

    public boolean update(LotteryRegistration newLottery) {
        lotteryDAOImplementation.update(newLottery);
        return true;
    }



}
