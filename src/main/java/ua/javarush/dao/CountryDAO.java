package ua.javarush.dao;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ua.javarush.entity.Country;

import java.util.List;

public class CountryDAO {
    private final SessionFactory sessionFactory;

    public CountryDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Country> getAll() {
        String hql = " select c from Country c join fetch c.languages";
        Query<Country> query = sessionFactory.getCurrentSession().createQuery(hql, Country.class);
        return query.list();
    }
}
