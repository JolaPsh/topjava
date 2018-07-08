package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Joanna Pakosh on Июль, 2018
 */
public class ServiceImpl implements Service {
    private Map<Integer, Meal> mapWithMeals = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEAL_LIST.forEach(this::save);
    }

    @Override
    public Meal save(Meal meal) {
        if (meal.isNew()) {
            meal.setId(counter.getAndIncrement());
            mapWithMeals.put(meal.getId(), meal);
            return meal;
        }
        return mapWithMeals.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);

    }

    @Override
    public void delete(int id) {
        mapWithMeals.remove(id);
    }

    @Override
    public List<Meal> getAll() {
        return new ArrayList<>(mapWithMeals.values());
    }

    @Override
    public Meal get(int id) {
        return mapWithMeals.get(id);
    }
}
