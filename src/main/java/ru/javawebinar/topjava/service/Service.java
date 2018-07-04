package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;

import java.util.Collection;

/**
 * Created by Joanna Pakosh on Июль, 2018
 */
public interface Service {
    Meal save(Meal meal);

    void delete(int id);

    Collection<Meal> getAll();

    void edit(int id);
}

