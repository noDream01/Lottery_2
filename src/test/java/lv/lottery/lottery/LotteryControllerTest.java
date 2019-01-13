package lv.lottery.lottery;

import lv.lottery.registration.LotteryController;
import lv.lottery.registration.LotteryRegistration;
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
}
