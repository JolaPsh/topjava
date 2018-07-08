package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

@Controller
public class MealRestController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MealService service;

    public List<MealWithExceed> getAll() {
        log.info("Controller: all");
        return MealsUtil.getWithExceeded(service.getAll(), MealsUtil.DEFAULT_CALORIES_PER_DAY);
    }

    public Meal get(int id) {
        log.info("Controller:  get: " + id);
        checkNotFoundWithId(id > 0, id);
        return service.get(id);
    }

    public Meal create(Meal meal) {
        log.info("Controller:  create: " + meal.toString());
        //   checkNew(meal);
        return service.create(meal);
    }

    public void delete(int id) {
        log.info("Controller:  delete: " + id);
        checkNotFoundWithId(id > 0, id);
        service.delete(id);
    }

    public void update(Meal meal, int id) {
        log.info("Controller:  update: " + meal.toString());
        assureIdConsistent(meal, id);
        service.update(meal);
    }

}