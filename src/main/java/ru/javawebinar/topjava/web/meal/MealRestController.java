package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;
import static ru.javawebinar.topjava.web.SecurityUtil.authUserId;
import static ru.javawebinar.topjava.web.SecurityUtil.authUserCaloriesPerDay;


@Controller
public class MealRestController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MealService service;

    public List<MealWithExceed> getAll() {
        log.info("Controller: all");
        return MealsUtil.getWithExceeded(service.getAll(authUserId()), authUserCaloriesPerDay());
    }

    public Meal get(int id) {
        log.info("Controller:  get: " + id);
        return service.get(id, authUserId());
    }

    public Meal create(Meal meal) {
        log.info("Controller:  create: " + meal);
        checkNew(meal);
        return service.create(meal, authUserId());
    }

    public void delete(int id) {
        log.info("Controller:  delete: " + id);
        service.delete(id, authUserId());
    }

    public void update(Meal meal, int id) {
        log.info("Controller:  update: " + meal);
        assureIdConsistent(meal, id);
        service.update(meal, authUserId());
    }

    public List<MealWithExceed> getAllByDateOrTime(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        List<Meal> mealList = service.getBetweenDates(
                startDate != null ? startDate : LocalDate.MIN,
                endDate != null ? endDate : LocalDate.MAX,
                authUserId());

        return MealsUtil.getFilteredWithExceeded(mealList,
                startTime != null ? startTime : LocalTime.MIN,
                endTime != null ? endTime : LocalTime.MAX,
                SecurityUtil.authUserCaloriesPerDay());

    }
}