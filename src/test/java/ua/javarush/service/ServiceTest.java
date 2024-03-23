package ua.javarush.service;

import org.junit.jupiter.api.Test;
import ua.javarush.dao.AbstractBaseTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServiceTest extends AbstractBaseTest {
    private final Service service = new Service("h2.cfg.xml");

    @Test
    void transformDataTestShouldTReturn5() {
        int actual = service.transformData(service.fetchData()).size();
        assertEquals(5, actual);
    }
}
