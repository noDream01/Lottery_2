package lv.lottery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Optional;

public abstract class BaseDAOImplementation<T> implements BaseDAO<T> {
    public SessionFactory sessionFactory;

    protected BaseDAOImplementation(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<T> getAll(Class<T> clazz) {
        Session session = sessionFactory.openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(clazz);
        query.select(query.from(clazz));

        List<T> users = session.createQuery(query).getResultList();
//        session.close();
        return users;
    }

    @Override
    public Optional<T> getById(Long id, Class<T> clazz) {
        Session session = sessionFactory.openSession();

        T obj = session.get(clazz, id);

        session.close();
        return Optional.ofNullable(obj);
    }

    @Override
    public Long insert(T obj) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        Long id = (Long) session.save(obj);

        tx.commit();
        session.close();

        return id;
    }

    @Override
    public void update(T obj) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        session.update(obj);

        tx.commit();
        session.close();
    }

//    @Override
//    public Optional<T> getByAssignedId(Long assignedId, Class<T> clazz) {
//        Session session = sessionFactory.openSession();
//
//        T obj = session.get(clazz, assignedId);
//
//        session.close();
//        return Optional.ofNullable(obj);
//
//    }
}
