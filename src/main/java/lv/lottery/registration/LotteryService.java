package lv.lottery.registration;

import lv.lottery.Response.Response;
import lv.lottery.Response.ResponseLotReg;
import lv.lottery.Response.ResponseStatus;
import lv.lottery.Response.ResponseStop;
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

    public ResponseLotReg addLottery(LotteryRegistration lotteryRegistration){

        ResponseLotReg responseLotReg = new ResponseLotReg();

        if( lotteryRegistration.getTitle() != null && !lotteryRegistration.getTitle().isEmpty() && lotteryRegistration.getLimit() != null) {


            lotteryRegistration.setCreatedDate(new Date());
            lotteryRegistration.setRegStatus(true);

//        lastId++;
//        lotteryRegistration.setId(lastId);
            lotteryDAOImplementation.insert(lotteryRegistration);

            responseLotReg.setId(lotteryRegistration.getId());
            responseLotReg.setStatus("OK");

        } else {
            responseLotReg.setStatus("Fail");
            responseLotReg.setReason("Wrong lottery title");
        }
        return responseLotReg;

    }

    public ResponseStop stopLotReg(Long id){

        ResponseStop responseStop = new ResponseStop();

        Optional<LotteryRegistration> wrappedLottery = lotteryDAOImplementation.getById(id);

        if(wrappedLottery.isPresent()){
            lotteryRegistration = wrappedLottery.get();
            if(!lotteryRegistration.getRegStatus()){

                return new ResponseStop("Fail", "Registration of lottery with id: " + id + " has already been stopped on " + lotteryRegistration.getEndDate());
            } else if(lotteryRegistration.getUsers().isEmpty()){
                return new ResponseStop("Fail", "Stopping registration without participants is impossible");
            } else {

                lotteryRegistration.setEndDate(new Date());
                lotteryRegistration.setRegStatus(false);
                lotteryDAOImplementation.update(lotteryRegistration);

                responseStop.setStatus("OK");
            }
        }
        else {
            System.out.println("Lottery with id "  + id + " , does not exist in DB");
            responseStop.setReason("Fail");
            responseStop.setStatus("There is no lottery with entered id: " + id);
        }

        return responseStop;
    }

    public Response winnerLotUser(Long id){

        Response response = new Response();

        response.setStatus("You lost");

        Optional<LotteryRegistration> wrappedLottery = lotteryDAOImplementation.getById(id);

        if(wrappedLottery.isPresent()) {
            lotteryRegistration = wrappedLottery.get();
            if (lotteryRegistration.getId() == null) {
                response.setStatus("Fail");
                response.setReason("Lottery with id: " + id + ", does not exists.");
            } else if (lotteryRegistration.getRegStatus()) {
                response.setStatus("Fail");
                response.setReason("Lottery registration is still open");
            }else {

                Random random = new Random();
                Integer winnerCode = random.nextInt(lotteryRegistration.getUsers().size() + 1);
                System.out.println("Amounts of participants:" + winnerCode);
                String winCode = lotteryRegistration.getUsers().get(winnerCode - 1).getCode();
                System.out.println(winCode);
                lotteryRegistration.setWinnerCode(winCode);

                lotteryDAOImplementation.update(lotteryRegistration);

                response.setWinnerCode(winCode);
                response.setStatus("OK");
            }
//            wrappedLottery.get().getUsers().get(winnerCode).setStatus("Winner");
//            usersDAOImplementation.update(wrappedLottery.get().getUsers().get(winnerCode));
        }
        return response;


    }

    public ResponseStatus participantStatus(Long id, String email, String code){

        ResponseStatus responseStatus = new ResponseStatus();
        Optional<LotteryRegistration> wrappedLottery = lotteryDAOImplementation.getById(id);
        if(wrappedLottery.isPresent()){
            lotteryRegistration = wrappedLottery.get();
            if(lotteryRegistration.getRegStatus()){
                responseStatus.setStatus("PENDING");
            } else if(lotteryRegistration.getWinnerCode().equals(code)){
                responseStatus.setStatus("WINNER");

            } else if(!lotteryRegistration.getWinnerCode().equals(code)){
                responseStatus.setStatus("LOOSER");
            } else{
                for(UsersRegistration user : wrappedLottery.get().getUsers()){
                    if(!user.getEmail().equals(email)){
                        responseStatus.setStatus("No such participant");
                    }
                }
            }
        }
        return responseStatus;
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

    public List<LotteryRegistration> getStats(){
//        lotteryRegistration.setUsersQty(lotteryRegistration.getUsers().size() + 1);

        return new ArrayList<>(lotteryDAOImplementation.getAll());
    }







}
