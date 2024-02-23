package ua.javarush.engine;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import ua.javarush.dao.CityDAO;
import ua.javarush.dao.CountryDAO;
import ua.javarush.domain.City;
import ua.javarush.domain.Country;
import ua.javarush.domain.CountryLanguage;

import java.util.ArrayList;
import java.util.List;

public class Engine {
    private final SessionFactory sessionFactory;
    private final CountryDAO countryDAO;
    private final CityDAO cityDAO;

    public Engine() {
        sessionFactory = new Configuration()
                .configure()
                .addAnnotatedClass(Country.class)
                .addAnnotatedClass(City.class)
                .addAnnotatedClass(CountryLanguage.class)
                .buildSessionFactory();

        countryDAO = new CountryDAO(sessionFactory);
        cityDAO = new CityDAO(sessionFactory);
    }

    public List<City> fetchData() {
        try (Session session = sessionFactory.getCurrentSession()) {
            List<City> allCities = new ArrayList<>();
            Transaction transaction = session.beginTransaction();

            List<Country> countries = countryDAO.getAll();

            int totalCount = cityDAO.getTotalCount();
            int step = 500;
            for (int i = 0; i < totalCount; i += step) {
                allCities.addAll(cityDAO.getItems(i, step));
            }

            transaction.commit();
            return allCities;
        }
    }

}
