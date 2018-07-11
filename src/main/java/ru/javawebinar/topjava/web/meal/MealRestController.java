package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;
import static ru.javawebinar.topjava.web.SecurityUtil.authUserId;


@Controller
public class MealRestController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MealService service;

    public List<MealWithExceed> getAll() {
        log.info("Controller: all");
        return MealsUtil.getWithExceeded(service.getAll(authUserId()), MealsUtil.DEFAULT_CALORIES_PER_DAY);
    }

    public Meal get(int id) {
        log.info("Controller:  get: " + id);
        checkNotFoundWithId(id > 0, id);
        return service.get(id, authUserId());
    }

    public Meal create(Meal meal) {
        log.info("Controller:  create: " + meal.toString());
        //  assureIdConsistent(meal, meal.getId()); - not finished yet
        return service.create(meal);
    }

    public void delete(int id) {
        log.info("Controller:  delete: " + id);
        checkNotFoundWithId(id > 0, id);
        assureIdConsistent(service.get(id, authUserId()), id);
        service.delete(id, authUserId());
    }

    public void update(Meal meal, int id) {
        log.info("Controller:  update: " + meal.toString());
        assureIdConsistent(meal, id);
        service.update(meal);
    }

    public List<MealWithExceed> getAllByDateOrTime(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        return MealsUtil.getWithExceeded(service.getAll(authUserId()), MealsUtil.DEFAULT_CALORIES_PER_DAY).stream()
                .filter(v -> DateTimeUtil.isBetweenDateOrTime(v.getDateTime().toLocalDate(),
                        startDate != null ? startDate : LocalDate.MIN,
                        endDate != null ? endDate : LocalDate.MAX))
                .filter(v -> DateTimeUtil.isBetweenDateOrTime(v.getDateTime().toLocalTime(),
                        startTime != null ? startTime : LocalTime.MIN,
                        endTime != null ? endTime : LocalTime.MAX))
                .collect(Collectors.toList());

    }
}