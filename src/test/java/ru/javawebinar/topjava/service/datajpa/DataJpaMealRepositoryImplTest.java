package ru.javawebinar.topjava.service.datajpa;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.AbstractMealServiceTest;

import static ru.javawebinar.topjava.MealTestData.MEAL2;
import static ru.javawebinar.topjava.MealTestData.assertMatch;

/**
 * Created by Joanna Pakosh on Июль, 2018
 */
@ActiveProfiles(Profiles.DATAJPA)
public class DataJpaMealRepositoryImplTest extends AbstractMealServiceTest {

    @Test
    public void getMealWithUserId() {
        Meal meal = service.getMealWithUserId(100003, 100000);
        assertMatch(meal, MEAL2);
        UserTestData.assertMatch(meal.getUser(), UserTestData.USER);
    }
}