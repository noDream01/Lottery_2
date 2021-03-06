package lv.lottery.users;


import lv.lottery.Response.ResponseUserReg;
import lv.lottery.registration.LotteryController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/register")
public class UserController {
    private final static Logger LOGGER = LoggerFactory.getLogger(LotteryController.class);

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseUserReg add(@RequestBody UsersRegistration usersRegistration) {
        LOGGER.info("Participant registered");
        return userService.add(usersRegistration);
    }
//    @GetMapping
//    public Collection<UsersRegistration> users() {
//        return userService.users();
//    }

//    @PutMapping(value = "/assign", produces = MediaType.APPLICATION_JSON_VALUE)
//    public boolean assign(@RequestParam (name = "userId")Long userId, @RequestParam Long lotteryId) {
//        return userService.assign(userId, lotteryId);
//    }

//    @PostMapping
//    public void assignedLottery(@RequestBody Long assignedId) {
//        LOGGER.info("lottery id recieved");
//        userService.getByAssigned(assignedId);
//    }

//    @RequestMapping( value = "start-registration/{id}", method = RequestMethod.GET)
//    public Optional<UsersRegistration> getById(@PathVariable Long id) {
//        return userService.get(id);
//    }
}
