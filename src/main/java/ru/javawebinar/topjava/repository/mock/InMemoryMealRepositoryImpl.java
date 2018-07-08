package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepositoryImpl.class);
    private Map<Integer, Meal> mealRepo = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(this::save);
    }

    @Override
    public Meal save(Meal meal) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            mealRepo.put(meal.getId(), meal);
            return meal;
        }
        // treat case: update, but absent in storage
        return mealRepo.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    /**
     * filter(v -> (v.getId().intValue() == id) - comparison of this kind imply that user can only delete his own meal, not everyone's
     */
    @Override
    public boolean delete(int id) {
        log.info("delete {}", id);
        return mealRepo.values().removeIf(v -> (v.getId().intValue() == id));
    }

    /**
     * filter(v -> (v.getId().intValue() == id) - comparison of this kind imply that user can only view his own meal, not everyone's
     */
    @Override
    public Meal get(int id) {
        log.info("get {}", id);
        return mealRepo.values().stream()
                .filter(v -> (v.getId().intValue() == id))
                .findAny().orElse(null);
    }

    @Override
    public List<Meal> getAll() {
        return mealRepo.values().stream()
                .filter(v -> (v.getId() != null))
                .sorted(Comparator.comparing(Meal::getDate).reversed())
                .collect(Collectors.toList());
    }
}

