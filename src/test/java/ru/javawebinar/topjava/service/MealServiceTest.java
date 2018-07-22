package ru.javawebinar.topjava.service;

import org.junit.*;
import org.junit.rules.*;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;
import java.time.LocalDate;
import java.time.Month;
import java.util.concurrent.TimeUnit;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {
    private static final Logger log = LoggerFactory.getLogger(MealServiceTest.class);
    @Rule
    public final ExpectedException thrown = ExpectedException.none();
    @Rule
    public final TestName testName = new TestName();
    @Rule
    public final Stopwatch stopwatch = new Stopwatch() {
        @Override
        public long runtime(TimeUnit unit) {
            return super.runtime(unit);
        }
    };

    static {
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Test
    public void delete() throws Exception {
        service.delete(MEAL1_ID, USER_ID);
        assertMatch(service.getAll(USER_ID), MEAL6, MEAL5, MEAL4, MEAL3, MEAL2);
        log.info(String.format("Execution time of delete() method: %d", stopwatch.runtime(TimeUnit.MILLISECONDS)));
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound() throws Exception {
        service.delete(MEAL1_ID, 1);
        log.info(String.format("Execution time of deleteNotFound() method: %d", stopwatch.runtime(TimeUnit.MILLISECONDS)));
    }

    @Test
    public void create() throws Exception {
        Meal created = getCreated();
        service.create(created, USER_ID);
        assertMatch(service.getAll(USER_ID), created, MEAL6, MEAL5, MEAL4, MEAL3, MEAL2, MEAL1);
        log.info(String.format("Execution time of create() method: %d", stopwatch.runtime(TimeUnit.MILLISECONDS)));
    }

    @Test
    public void get() throws Exception {
        Meal actual = service.get(ADMIN_MEAL_ID, ADMIN_ID);
        assertMatch(actual, ADMIN_MEAL1);
        log.info(String.format("Execution time of get method(): %d", stopwatch.runtime(TimeUnit.MILLISECONDS)));
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() throws Exception {
        service.get(MEAL1_ID, ADMIN_ID);
        log.info(String.format("Execution time of getNotFound() method: %d", stopwatch.runtime(TimeUnit.MILLISECONDS)));
    }

    @Test
    public void update() throws Exception {
        Meal updated = getUpdated();
        service.update(updated, USER_ID);
        assertMatch(service.get(MEAL1_ID, USER_ID), updated);
        log.info(String.format("Execution time of update() method: %d", stopwatch.runtime(TimeUnit.MILLISECONDS)));
    }

    @Test(expected = NotFoundException.class)
    public void updateNotFound() throws Exception {
        service.update(MEAL1, ADMIN_ID);
        log.info(String.format("Execution time of updateNotFound() method: %d", stopwatch.runtime(TimeUnit.MILLISECONDS)));
    }
  
    @Test
    public void getAll() throws Exception {
        assertMatch(service.getAll(USER_ID), MEALS);
        log.info(String.format("Execution time of getAll() method: %d ", stopwatch.runtime(TimeUnit.MILLISECONDS)));
    }

    @Test
    public void getBetween() throws Exception {
        assertMatch(service.getBetweenDates(
                LocalDate.of(2015, Month.MAY, 30),
                LocalDate.of(2015, Month.MAY, 30), USER_ID), MEAL3, MEAL2, MEAL1);
        log.info(String.format("Execution time of getBetween() method: %d", stopwatch.runtime(TimeUnit.MILLISECONDS)));
    }

    @After
    public void afterClass() {
        log.info(String.format("Test name : %s, execution time: %d ms",
                testName.getMethodName(), stopwatch.runtime(TimeUnit.MILLISECONDS)));
    }

    @Test
    public void setThrownException() {
        thrown.expect(NullPointerException.class);
        throw new NullPointerException("NullPointer Test");
    }
}