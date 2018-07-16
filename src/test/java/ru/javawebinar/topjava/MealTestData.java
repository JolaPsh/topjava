package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Joanna Pakosh on Июль, 2018
 */
public class MealTestData {
    public static final int MEAL_ID = 100;

    public static final Meal FIRST_MEAL = new Meal(MEAL_ID,
            LocalDateTime.of(2018, Month.JANUARY, 28, 01, 0),
            "Сheeseburger", 1200);
    public static final Meal SECOND_MEAL = new Meal(MEAL_ID + 1,
            LocalDateTime.of(2018, Month.JANUARY, 28, 9, 30),
            "Сorn porridge", 400);

    public static final Meal SIXTH_MEAL = new Meal(MEAL_ID + 6,
            LocalDateTime.of(2018, Month.MARCH, 03, 01, 0),
            "Kefir", 140);
    public static final Meal SEVENTH_MEAL = new Meal(MEAL_ID + 7,
            LocalDateTime.of(2018, Month.MARCH, 03, 8, 5),
            "Roll", 270);
    public static final Meal EIGHTH_MEAL = new Meal(MEAL_ID + 8,
            LocalDateTime.of(2018, Month.MARCH, 3, 11, 45),
            "Fried fish", 420);
    public static final Meal NINETH_MEAL = new Meal(MEAL_ID + 9, LocalDateTime.of(2018, Month.APRIL, 12, 21, 00),
            "Swiss roll", 620);

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).isEqualTo(expected);
    }
}
