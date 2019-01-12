package lv.lottery.users;

import lv.lottery.BaseDAOImplementation;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UsersDAOImplementation extends BaseDAOImplementation<UsersRegistration> {

    @Autowired
    public UsersDAOImplementation(SessionFactory sessionFactory) {
        super(sessionFactory);
    }


    public List<UsersRegistration> getAll() {
//        Session session = sessionFactory.openSession();
////
////        CriteriaBuilder builder = session.getCriteriaBuilder();
////        CriteriaQuery<UsersRegistration> query = builder.createQuery(UsersRegistration.class);
////        query.select(query.from(UsersRegistration.class));
////
////        List<UsersRegistration> tasks = session.createQuery(query).getResultList();
////        session.close();
////        return tasks;
        return super.getAll(UsersRegistration.class);
    }


    public Optional<UsersRegistration> getById(Long id) {
//        Session session = sessionFactory.openSession();
//
//        CriteriaBuilder builder = session.getCriteriaBuilder();
//        CriteriaQuery<UsersRegistration> query = builder.createQuery(UsersRegistration.class);
//        Root<UsersRegistration> root = query.from(UsersRegistration.class);
//        query.where(builder.equal(root.get("id"), id));
//        query.select(root);
//
//        Optional<UsersRegistration> task = Optional.ofNullable(session.createQuery(query).getSingleResult());
//        session.close();
//        return task;
        return super.getById(id, UsersRegistration.class);
    }

//    public Optional<UsersRegistration> getByAssignedId(Long assignedId) {
//
//        return  super.getByAssignedId(assignedId, UsersRegistration.class);
//    }

//    @Override
//    public Long insert(UsersRegistration usersRegistration) {
//        Session session = sessionFactory.openSession();
//        Transaction tx = session.beginTransaction();
//
//        session.save(usersRegistration);
//
//        tx.commit();
//        session.close();
//
//        return usersRegistration.getId();
//    }
//
//    @Override
//    public void update(Long userID, UsersRegistration usersRegistration) {
//        Session session = sessionFactory.openSession();
//        Transaction tx = session.beginTransaction();
//
//        session.update(usersRegistration);
//
//        tx.commit();
//        session.close();


}
