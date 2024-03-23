package ua.javarush.dao;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CityTest extends AbstractBaseTest {
    @Test
    void getByIdTestShouldReturnNameString() {
        String actual = cityDAO.getById(1).getName().trim();
        assertEquals("Kabul", actual);
    }

    @Test
    void getTotalCountTestShouldReturn5() {
        int actual = cityDAO.getTotalCount();
        assertEquals(5, actual);
    }

    @Test
    void getItemsTestShouldReturn3() {
        int actual = cityDAO.getItems(0, 3).size();
        assertEquals(3, actual);
    }
}
