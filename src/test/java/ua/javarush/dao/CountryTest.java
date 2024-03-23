package ua.javarush.dao;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountryTest extends AbstractBaseTest {
    @Test
    void getAllTestShouldReturn5() {
        int actual = countryDAO.getAll().size();
        assertEquals(15, actual);
    }
}
