package ua.javarush;

import ua.javarush.domain.City;
import ua.javarush.engine.Engine;
import ua.javarush.redis.CityCountry;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Engine engine = new Engine();

        List<City> cities = engine.fetchData();
        List<CityCountry> preparedData = engine.transformData(cities);

        engine.pushToRedis(preparedData);

        List<Integer> ids = List.of(3, 2545, 123, 4, 189, 89, 3458, 1189, 10, 102);

        long startRedis = System.currentTimeMillis();
        engine.getRedisData(ids);
        long endRedis = System.currentTimeMillis();


        long startMysql = System.currentTimeMillis();
        engine.getMysqlData(ids);
        long endMysql = System.currentTimeMillis();

        System.out.println("Redis with ObjectMapper: " + (endRedis - startRedis));
        System.out.println("Mysql: " + (endMysql - startMysql));
    }
}
