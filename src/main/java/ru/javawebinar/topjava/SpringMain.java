package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.web.meal.MealRestController;
import ru.javawebinar.topjava.web.user.AdminRestController;
import java.util.Arrays;

public class SpringMain {
    public static void main(String[] args) {
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
            adminUserController.create(new User(null, "userName", "email@mail.ru", "password", Role.ROLE_ADMIN));
        }

        System.out.println("\n" + "----------------------------------------------------------" + "\n");
        ConfigurableApplicationContext appCtx2 = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        System.out.println("Bean definition names: " + Arrays.toString(appCtx2.getBeanDefinitionNames()));
        MealRestController mealRestController = appCtx2.getBean(MealRestController.class);

        /*mealRestController.getAll();
        mealRestController.create(new Meal(1, 1, LocalDateTime.of(2018, Month.MAY, 30, 10, 0), "Завтрак", 500));
        mealRestController.update(MealsUtil.MEALS.get(0), 0);*/
        mealRestController.get(0);
    }
}
