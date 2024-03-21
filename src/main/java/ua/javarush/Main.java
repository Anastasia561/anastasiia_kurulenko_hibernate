package ua.javarush;

import ua.javarush.entity.City;
import ua.javarush.service.Service;
import ua.javarush.redis.CityCountry;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Service service = new Service();

        List<City> cities = service.fetchData();
        List<CityCountry> preparedData = service.transformData(cities);

        service.pushToRedis(preparedData);

        List<Integer> ids = List.of(3, 2545, 123, 4, 189, 89, 3458, 1189, 10, 102);

        long startRedis = System.currentTimeMillis();
        service.getRedisData(ids);
        long endRedis = System.currentTimeMillis();


        long startMysql = System.currentTimeMillis();
        service.getMysqlData(ids);
        long endMysql = System.currentTimeMillis();

        System.out.println("Redis with ObjectMapper: " + (endRedis - startRedis));
        System.out.println("Mysql: " + (endMysql - startMysql));
    }
}
