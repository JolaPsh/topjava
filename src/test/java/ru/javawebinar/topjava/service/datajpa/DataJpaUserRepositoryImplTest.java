package ru.javawebinar.topjava.service.datajpa;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.AbstractUserServiceTest;

import java.util.Arrays;
import java.util.List;

import static ru.javawebinar.topjava.MealTestData.*;

/**
 * Created by Joanna Pakosh on Июль, 2018
 */
@ActiveProfiles(Profiles.DATAJPA)
public class DataJpaUserRepositoryImplTest extends AbstractUserServiceTest {

    @Test
    public void getUserByIdWithMeal() {
        List<Meal> userMeals = service.getUserByIdWithMeal(100000);
        List<Meal> expectedUserMeals = Arrays.asList(MEAL1, MEAL2, MEAL3, MEAL4, MEAL5, MEAL6);
        assertMatch(userMeals, expectedUserMeals);
        UserTestData.assertMatch(userMeals.iterator().next().getUser(), UserTestData.USER);
    }
}