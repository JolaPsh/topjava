package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Joanna Pakosh on Июль, 2018
 */
public class ServiceImpl implements Service {
    Map<Integer, Meal> mapWithMeals = new HashMap<>();

    @Override
    public Meal save(Meal meal) {
        return mapWithMeals.put(meal.getId(), meal);
    }

    @Override
    public void delete(int id) {
        mapWithMeals.remove(id);
    }

    @Override
    public Collection<Meal> getAll() {
        return mapWithMeals.values();
    }

    @Override
    public void edit(int id) {
        //dopisac
    }
}
