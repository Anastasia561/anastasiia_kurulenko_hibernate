package ua.javarush.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import ua.javarush.entity.City;
import ua.javarush.entity.Country;
import ua.javarush.entity.CountryLanguage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class AbstractBaseDAOTest {
    protected SessionFactory sessionFactory;
    protected Session session;
    protected CityDAO cityDAO;
    protected CountryDAO countryDAO;


    @BeforeEach
    void setup() {
        sessionFactory = new Configuration()
                .configure("h2.cfg.xml")
                .addAnnotatedClass(City.class)
                .addAnnotatedClass(Country.class)
                .addAnnotatedClass(CountryLanguage.class)
                .buildSessionFactory();

        cityDAO = new CityDAO(sessionFactory);
        countryDAO = new CountryDAO(sessionFactory);

        runSqlScriptFile("src/test/resources/schema.sql");
        runSqlScriptFile("src/test/resources/data.sql");
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
    }

    @AfterEach
    void tear() {
        session.getTransaction().commit();
        sessionFactory.close();
    }

    void runSqlScriptFile(String path) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            String sqlScript = new String(Files.readAllBytes(Paths.get(path)));
            Query query = session.createNativeQuery(sqlScript);
            query.executeUpdate();

            transaction.commit();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
