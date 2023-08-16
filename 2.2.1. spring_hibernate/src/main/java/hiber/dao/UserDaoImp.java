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
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public List<User> findUsersByCar(String model, int series) {
        System.out.println(String.format("Пользователь с моделью машины = %s и серией = %s:", model, series));
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("SELECT u from User u where u.car.model = :param1 and u.car.series = :param2");
        query.setParameter("param1", model);
        query.setParameter("param2", series);
        return query.getResultList();
    }
}
