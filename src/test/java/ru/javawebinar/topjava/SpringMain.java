package ru.javawebinar.topjava;

import org.springframework.context.support.GenericXmlApplicationContext;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.web.meal.MealRestController;
import ru.javawebinar.topjava.web.user.AdminRestController;

import java.time.Month;
import java.util.Arrays;

import static java.time.LocalDateTime.of;
import static ru.javawebinar.topjava.MealTestData.MEAL1;
import static ru.javawebinar.topjava.MealTestData.MEAL1_ID;

public class SpringMain {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.getEnvironment().setActiveProfiles(Profiles.getActiveDbProfile(), Profiles.DATAJPA);
        ctx.load("spring/spring-app.xml", "spring/spring-db.xml");
        ctx.refresh();

        System.out.println("Bean definition names: " + Arrays.toString(ctx.getBeanDefinitionNames()));
        AdminRestController adminUserController = ctx.getBean(AdminRestController.class);
        adminUserController.getByMail("user@yandex.ru");

        MealRestController mealController = ctx.getBean(MealRestController.class);
        mealController.getAll();
        ctx.close();

    }
}
