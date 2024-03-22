package ua.javarush.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisStringCommands;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import ua.javarush.dao.CityDAO;
import ua.javarush.dao.CountryDAO;
import ua.javarush.entity.City;
import ua.javarush.entity.Country;
import ua.javarush.entity.CountryLanguage;
import ua.javarush.factory.SessionFactoryProvider;
import ua.javarush.redis.CityCountry;
import ua.javarush.redis.Language;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Service {
    private final SessionFactory sessionFactory;
    private final RedisClient redisClient;
    private final ObjectMapper mapper;
    private final CountryDAO countryDAO;
    private final CityDAO cityDAO;

    public Service(String configPath) {
        sessionFactory = SessionFactoryProvider.getSessionFactory(configPath);

        countryDAO = new CountryDAO(sessionFactory);
        cityDAO = new CityDAO(sessionFactory);

        redisClient = RedisClient.create(RedisURI.create("localhost", 6379));

        mapper = new ObjectMapper();
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

    public List<CityCountry> transformData(List<City> cities) {
        List<CityCountry> result = new ArrayList<>();
        for (City city : cities) {
            CityCountry cityCountry = new CityCountry();
            cityCountry.setId(city.getId());
            cityCountry.setName(city.getName());
            cityCountry.setDistrict(city.getDistrict());
            cityCountry.setPopulation(city.getPopulation());

            Country country = city.getCountry();

            cityCountry.setCountryCode(country.getCode());
            cityCountry.setCountryCode2(country.getCode2());
            cityCountry.setCountryName(country.getName());
            cityCountry.setContinent(country.getContinent());
            cityCountry.setCountryRegion(country.getRegion());
            cityCountry.setCountrySurfaceArea(country.getSurfaceArea());
            cityCountry.setCountryPopulation(country.getPopulation());
            cityCountry.setLanguages(transformLanguages(country));

            result.add(cityCountry);
        }
        return result;
    }

    public void pushToRedis(List<CityCountry> data) {
        try (StatefulRedisConnection<String, String> connection = redisClient.connect()) {
            RedisStringCommands<String, String> sync = connection.sync();

            for (CityCountry cityCountry : data) {
                try {
                    sync.set(String.valueOf(cityCountry.getId()), mapper.writeValueAsString(cityCountry));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void getRedisData(List<Integer> ids) {
        try (StatefulRedisConnection<String, String> connection = redisClient.connect()) {
            RedisStringCommands<String, String> sync = connection.sync();

            for (Integer id : ids) {
                String value = sync.get(String.valueOf(id));
                try {
                    mapper.readValue(value, CityCountry.class);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void getMysqlData(List<Integer> ids) {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();

            for (Integer id : ids) {
                City city = cityDAO.getById(id);
                Set<CountryLanguage> languages = city.getCountry().getLanguages();
            }

            transaction.commit();
        }
    }

    private static Set<Language> transformLanguages(Country country) {
        Set<CountryLanguage> countryLanguages = country.getLanguages();
        Set<Language> languages = new HashSet<>();

        for (CountryLanguage countryLanguage : countryLanguages) {
            Language language = new Language();
            language.setLanguage(countryLanguage.getLanguage());
            language.setIsOfficial(countryLanguage.getIsOfficial());
            language.setPercentage(countryLanguage.getPercentage());
            languages.add(language);
        }
        return languages;
    }
}
