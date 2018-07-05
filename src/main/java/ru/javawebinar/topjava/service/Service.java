package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

/**
 * Created by Joanna Pakosh on Июль, 2018
 */
public interface Service {
    Meal save(Meal meal);

    void delete(int id);

    List<Meal> getAll();

    Meal get(int id);
}

