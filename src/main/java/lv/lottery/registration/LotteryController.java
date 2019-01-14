package lv.lottery.registration;


import lv.lottery.Response.*;
import lv.lottery.Response.ResponseStatus;
import lv.lottery.users.UsersRegistration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
public class LotteryController {
    private final static Logger LOGGER = LoggerFactory.getLogger(LotteryController.class);
    private final LotteryService lotteryService;

    @Autowired
    private LotteryController(LotteryService lotteryService){
        this.lotteryService = lotteryService;
    }

    @RequestMapping(value = "/start-registration", method = RequestMethod.GET)
    public Collection<LotteryRegistration> get(){
        LOGGER.info("lottery registered");
        return lotteryService.get();

    }

    @RequestMapping(value = "/start-registration", method = RequestMethod.POST)
    public ResponseLotReg create(@RequestBody LotteryRegistration lotteryRegistration){
        return lotteryService.addLottery(lotteryRegistration);
    }

    @RequestMapping( value = "start-registration/{id}", method = RequestMethod.GET)
    public Optional<LotteryRegistration> getById(@PathVariable Long id) {
        return lotteryService.get(id);
    }

    @RequestMapping(value = "/stop-registration", method = RequestMethod.POST)
    public ResponseStop stopLotReg(@RequestBody LotteryRegistration lotteryRegistration){
        LOGGER.info("stop registration");
        Long id = lotteryRegistration.getId();
        return lotteryService.stopLotReg(id);
    }

    @RequestMapping(value = "/choose-winner", method = RequestMethod.POST)
    public Response getWinner(@RequestBody LotteryRegistration lotteryRegistration){
        LOGGER.info("choose-winner");

        Long id = lotteryRegistration.getId();

        return lotteryService.winnerLotUser(id);
    }

    @RequestMapping(value = "/status",method = RequestMethod.GET)
    public ResponseStatus userStatus(@RequestParam Long id, String email, String code ){
        return lotteryService.participantStatus(id, email, code);
    }

    @RequestMapping(value = "/stats", method = RequestMethod.GET)
    public List<LotteryRegistration> getStats(){
        return lotteryService.getStats();
    }



}
