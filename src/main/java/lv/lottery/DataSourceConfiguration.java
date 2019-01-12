package lv.lottery;

import lv.lottery.registration.LotteryRegistration;
import lv.lottery.users.UsersRegistration;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfiguration {

    @Bean
    public SessionFactory sessionFactory() {
        return new org.hibernate.cfg.Configuration()
                .addAnnotatedClass(LotteryRegistration.class)
                .addAnnotatedClass(UsersRegistration.class)
                .configure()
                .buildSessionFactory();
    }
}
