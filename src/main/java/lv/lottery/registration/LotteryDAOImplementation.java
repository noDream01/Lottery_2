package lv.lottery.registration;

import lv.lottery.BaseDAOImplementation;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class LotteryDAOImplementation extends BaseDAOImplementation<LotteryRegistration> {

    @Autowired
    protected LotteryDAOImplementation(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<LotteryRegistration> getAll() {
        return super.getAll(LotteryRegistration.class);
    }

    public Optional<LotteryRegistration> getById(Long id) {
        return super.getById(id, LotteryRegistration.class);
    }


}
