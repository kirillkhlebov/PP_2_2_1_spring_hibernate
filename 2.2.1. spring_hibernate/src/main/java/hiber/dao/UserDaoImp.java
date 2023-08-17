package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("SELECT u FROM User u LEFT JOIN FETCH u.car");
        return query.getResultList();
    }

    @Override
    public User findUsersByCar(String model, int series) {
        System.out.println(String.format("Пользователь с моделью машины = %s и серией = %s:", model, series));
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("SELECT u from User u LEFT JOIN FETCH u.car WHERE u.car.model = :model and u.car.series = :series");
        query.setParameter("model", model);
        query.setParameter("series", series);
        return query.getSingleResult();
    }
}
