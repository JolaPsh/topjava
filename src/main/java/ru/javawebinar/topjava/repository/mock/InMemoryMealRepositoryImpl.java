package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepositoryImpl.class);
    private Map<Integer, Map<Integer, Meal>> mealRepo = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(meal -> save(meal, 1));
        save(new Meal(LocalDateTime.of(2017, Month.JUNE, 6, 14, 0), "Lunch", 510), 2);
        save(new Meal(LocalDateTime.of(2017, Month.JUNE, 1, 23, 0), "Supper", 1500), 2);
        save(new Meal(LocalDateTime.of(2018, Month.JUNE, 1, 14, 0), "Lunch", 510), 2);
        save(new Meal(LocalDateTime.of(2018, Month.JUNE, 12, 21, 0), "Supper", 1500), 2);
    }

    @Override
    public Meal save(Meal meal, int userId) {
        Map<Integer, Meal> userMeal = mealRepo.computeIfAbsent(userId, ConcurrentHashMap::new);
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            userMeal.put(meal.getId(), meal);
            return meal;
        }
        // treat case: update, but absent in storage
        return userMeal.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int id, int userId) {
        log.info("delete {}", id);
        Map<Integer, Meal> userMeal = mealRepo.get(userId);
        return userMeal != null && userMeal.remove(id) != null;
    }

    @Override
    public Meal get(int id, int userId) {
        log.info("get {}", id);
        Map<Integer, Meal> userMeal = mealRepo.get(userId);
        return userMeal == null ? null : userMeal.get(id);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return getAllFiltered(userId, meal -> true);
    }

    public List<Meal> getAllFiltered(int userId, Predicate<Meal> filter) {
        Map<Integer, Meal> userMeal = mealRepo.get(userId);
        if (userMeal.values() == null || userMeal == null) return Collections.emptyList();
        return userMeal.values().stream()
                .filter(filter)
                .sorted(Comparator.comparing(Meal::getDate).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return getAllFiltered(userId, meal -> DateTimeUtil.isBetween(meal.getDateTime(), startDateTime, endDateTime));
    }
}

