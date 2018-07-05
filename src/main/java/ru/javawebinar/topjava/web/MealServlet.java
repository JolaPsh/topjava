package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.ServiceImpl;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.service.Service;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by Joanna Pakosh on Июль, 2018
 */

/*
 Controller
 */
public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(UserServlet.class);
    private Service service;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        service = new ServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        String id = request.getParameter("id");
        Meal meal = new Meal(id.isEmpty() ? null : Integer.parseInt(id),
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.parseInt(request.getParameter("calories")));

        service.save(meal);
        response.sendRedirect("meals");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        log.info("All meals");

        String crudAction = request.getParameter("action");

        switch (crudAction == null ? "all" : crudAction) {
            case "delete":
                int id = getId(request);
                log.info("Delete : " + id);
                service.delete(id);
                response.sendRedirect("meals");
                break;
            case "add":
            case "edit":
                final Meal meal = "add".equalsIgnoreCase(crudAction) ?
                        new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 0) :
                        service.get(getId(request));

                request.setAttribute("meal", meal);
                request.getRequestDispatcher("/meals_form.jsp").forward(request, response);
                break;
            case "all":
            default:
                log.info("All meals");
                request.setAttribute("meals", MealsUtil.getFilteredWithExceeded(service.getAll(), LocalTime.MIN, LocalTime.MAX, MealsUtil.CALORIES_PER_DAY));
                request.getRequestDispatcher("/meals.jsp").forward(request, response);
                break;
        }
    }

    private int getId(HttpServletRequest request) {
        return Integer.parseInt(request.getParameter("id"));
    }
}


