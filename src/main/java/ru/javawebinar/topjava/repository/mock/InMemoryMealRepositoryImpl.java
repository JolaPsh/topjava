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
     * (v.getId().intValue() == id) - comparison of this kind imply that meal exists
     * (v.getUserId().intValue() ==userId) - comparison of this kind imply that user can only delete his own meal, not other
     */
    @Override
    public boolean delete(int id, int userId) {
        log.info("delete {}", id);
        return mealRepo.values().removeIf(v -> (v.getId().intValue() == id) && (v.getUserId().intValue() == userId));
    }

    /**
     * (v.getId().intValue() == id) - comparison of this kind imply that meal exists
     * (v.getUserId().intValue() ==userId) - comparison of this kind imply that user can only delete his own meal, not other
     */
    @Override
    public Meal get(int id, int userId) {
        log.info("get {}", id);
        return mealRepo.values().stream()
                .filter(v -> (v.getId().intValue() == id) && (v.getUserId().intValue() == userId))
                .findAny().orElse(null);
    }

    @Override
    public List<Meal> getAll(int userId) {
        if (mealRepo.values() == null || mealRepo == null) return Collections.emptyList();
        return mealRepo.values().stream()
                .filter(v -> (v.getId() != null) && (v.getUserId().intValue() == userId))
                .sorted(Comparator.comparing(Meal::getDate).reversed())
                .collect(Collectors.toList());
    }
}

