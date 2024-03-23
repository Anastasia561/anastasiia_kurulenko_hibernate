package ua.javarush.factory;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ua.javarush.entity.City;
import ua.javarush.entity.Country;
import ua.javarush.entity.CountryLanguage;

public class SessionFactoryProvider {
    public static SessionFactory getSessionFactory(String configPath) {
        return new Configuration()
                .configure(configPath)
                .addAnnotatedClass(Country.class)
                .addAnnotatedClass(City.class)
                .addAnnotatedClass(CountryLanguage.class)
                .buildSessionFactory();
    }
}
