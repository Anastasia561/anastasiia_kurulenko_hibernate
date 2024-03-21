package ua.javarush.dao;

import org.hibernate.query.Query;
import org.hibernate.SessionFactory;
import ua.javarush.entity.City;

import java.util.List;

public class CityDAO {
    private final SessionFactory sessionFactory;

    public CityDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<City> getItems(int offset, int limit) {
        String hql = "from City";
        Query<City> query = sessionFactory.getCurrentSession().createQuery(hql, City.class);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.list();
    }

    public int getTotalCount() {
        String hql = "select count(c) from City c";
        Query<Long> query = sessionFactory.getCurrentSession().createQuery(hql, Long.class);
        return Math.toIntExact(query.uniqueResult());
    }

    public City getById(Integer id) {
        String hql = "select c from City c join fetch c.country where c.id = :id";
        Query<City> query = sessionFactory.getCurrentSession().createQuery(hql, City.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }
}
