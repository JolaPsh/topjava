package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.*;

/**
 * Created by Joanna Pakosh on Июль, 2018
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateMealDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Test
    public void get() throws Exception {
        Meal meal = service.get(MEAL_ID, USER_ID);
        assertMatch(meal, FIRST_MEAL);
    }

    // not our meal
    @Test(expected = NotFoundException.class)
    public void notFoundToGet() throws Exception {
        service.get(108, USER_ID);
    }

    @Test
    public void delete() throws Exception {
        service.delete(MEAL_ID + 7, ADMIN_ID);
        assertMatch(service.getAll(ADMIN_ID), NINETH_MEAL, EIGHTH_MEAL, SIXTH_MEAL);

    }

    // not our meal
    @Test(expected = NotFoundException.class)
    public void notFoundToDelete() throws Exception {
        service.delete(108, USER_ID);
    }

    @Test
    public void getBetweenDates() throws Exception {
        LocalDate startDate = LocalDate.of(2018, Month.JANUARY, 28);
        LocalDate endDate = startDate;
        List<Meal> allBetweenDates = service.getBetweenDates(startDate, endDate, ADMIN_ID);
        assertMatch(allBetweenDates);
    }

    @Test
    public void getBetweenDateTimes() throws Exception {
        LocalDateTime startDateTime = LocalDateTime.of(LocalDate.of(2018, Month.JANUARY, 28), LocalTime.of(01, 00, 00));
        LocalDateTime endDateTime = LocalDateTime.of(LocalDate.of(2018, Month.JANUARY, 28), LocalTime.of(12, 00, 00));
        List<Meal> allBetweenTimes = service.getBetweenDateTimes(startDateTime, endDateTime, USER_ID);
        assertMatch(allBetweenTimes, SECOND_MEAL, FIRST_MEAL);
    }

    @Test
    public void getAll() throws Exception {
        List<Meal> all = service.getAll(ADMIN_ID);
        assertMatch(all, NINETH_MEAL, EIGHTH_MEAL, SEVENTH_MEAL, SIXTH_MEAL);
    }

    @Test
    public void update() throws Exception {
        Meal updatedMeal = new Meal(FIRST_MEAL);
        updatedMeal.setDescription("Coca-Cola");
        updatedMeal.setCalories(180);
        service.update(updatedMeal, USER_ID);
        assertMatch(service.get(MEAL_ID, USER_ID), updatedMeal);
    }

    // not our meal
    @Test(expected = NotFoundException.class)
    public void notFoundToUpdate() throws Exception {
        service.update(FIRST_MEAL, ADMIN_ID);
    }

    @Test
    public void create() throws Exception {
        Meal newMeal = new Meal(null, LocalDateTime.of(2018, Month.JUNE, 15, 18, 0), "Сroissant", 410);
        Meal createdMeal = service.create(newMeal, ADMIN_ID);
        newMeal.setId(createdMeal.getId());
        assertMatch(service.getAll(ADMIN_ID), createdMeal, NINETH_MEAL, EIGHTH_MEAL, SEVENTH_MEAL, SIXTH_MEAL);
    }

    @Test(expected = DataAccessException.class)
    public void duplicateDateCreate() throws Exception {
        service.create(new Meal(null, LocalDateTime.of(2018, Month.JANUARY, 28, 01, 0),
                "Duplicate", 560), USER_ID);
    }
}