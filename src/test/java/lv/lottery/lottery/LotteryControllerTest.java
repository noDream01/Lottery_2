package lv.lottery.lottery;

import lv.lottery.registration.LotteryController;
import lv.lottery.registration.LotteryRegistration;
import lv.lottery.users.UserController;
import lv.lottery.users.UsersRegistration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

@SuppressWarnings("OptionalGetWithoutIsPresent")
@RunWith(SpringRunner.class)
@SpringBootTest
public class LotteryControllerTest {

    @Autowired
    private LotteryController lotteryController;

    @Autowired
    private UserController userController;

    @Test
    public void shouldCreateLottery(){
        LotteryRegistration input = new LotteryRegistration();

        input.setTitle("Test Lottery");
        input.setLimit(10);

        lotteryController.create(input);
        Optional<LotteryRegistration> lottery = lotteryController.getById(1L);

        assertThat(lottery).isPresent();
        assertThat(lottery.get().getTitle()).isEqualTo("Test Lottery");
        assertThat(lottery.get().getLimit()).isEqualTo(10);


    }

    @Test
    public void shouldStopLottery(){
        LotteryRegistration input = new LotteryRegistration();

        input.setTitle("Test Lottery");
        input.setLimit(10);
        input.setRegStatus(true);

        lotteryController.create(input);


        UsersRegistration inputUser = new UsersRegistration();

        inputUser.setAge((byte) 22);
        inputUser.setEmail("test2@test.com");
        inputUser.setAssignedLotteryId(1L);
        inputUser.setCode("1601191400000001");

        userController.add(inputUser);



        lotteryController.stopLotReg(input);



        Optional<LotteryRegistration> lottery = lotteryController.getById(1L);

        assertThat(lottery).isPresent();
        assertThat(lottery.get().getTitle()).isEqualTo("Test Lottery");
        assertThat(lottery.get().getLimit()).isEqualTo(10);
        assertThat(lottery.get().getRegStatus()).isEqualTo(false);


    }


}
