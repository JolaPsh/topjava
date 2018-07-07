package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
public class MealRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());


    @Autowired
    private MealService service;

    public List<MealWithExceed> getAll() {
        log.info("Controller: all");
        return MealsUtil.getWithExceeded(service.getAll(), MealsUtil.DEFAULT_CALORIES_PER_DAY);
    }

    public MealWithExceed get(int id) {
        log.info("Controller:  get: " + id);           ////////////////////////////////////////
        return MealsUtil.getWithExceeded(service.getAll(), MealsUtil.DEFAULT_CALORIES_PER_DAY).get(id);
    }

    public MealWithExceed create(Meal meal) {
        log.info("Controller:  create: " + meal.toString());
        service.create(meal); //////////////////////////////////////////
        return get(meal.getId());
    }

    public void delete(int id) {
        log.info("Controller:  delete: " + id);
        service.delete(id);
    }

    public void update(Meal meal) {
        log.info("Controller:  uddate: " + meal.toString());
        service.update(meal);
    }

    public List<MealWithExceed> getBetween(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        return null;
    }

}