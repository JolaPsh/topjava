package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.service.ServiceImpl;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.javawebinar.topjava.util.MealsUtil.getFilteredWithExceeded;

/**
 * Created by Joanna Pakosh on Июль, 2018
 */

/*
 Controller
 */
public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(UserServlet.class);
    private ServiceImpl service;

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm");

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        List<MealWithExceed> listWithMeals = getFilteredWithExceeded(MealsUtil.MEALS, LocalTime.MIN, LocalTime.MAX, 2000);

        String crudAction = request.getParameter("action");
        if (crudAction == null) {
            request.setAttribute("meals", listWithMeals);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/meals.jsp");
            if (dispatcher != null) {
                dispatcher.forward(request, response);
            }
        }

        int id = Integer.parseInt(request.getParameter("id"));
        LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("dateTime"));
        String description = request.getParameter("description");
        int calories = Integer.parseInt(request.getParameter("calories"));
        Meal meal = new Meal(dateTime, description, calories);

        switch (crudAction) {
            case "add":
                service.save(meal);

                break;
            case "edit":

                break;
            case "delete":
                service.delete(id);
                response.sendRedirect("meals");
                break;

            default:
                break;
        }


        request.setAttribute("meals", listWithMeals);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/meals.jsp");
        if (dispatcher != null) {
            dispatcher.forward(request, response);
        }
    }
}