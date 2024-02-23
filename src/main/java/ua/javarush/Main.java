package ua.javarush;

import ua.javarush.domain.City;
import ua.javarush.engine.Engine;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Engine engine = new Engine();
        List<City> allCities = engine.fetchData();
        System.out.println(allCities.size());
    }
}
