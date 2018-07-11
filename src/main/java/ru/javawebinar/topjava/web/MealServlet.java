package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.web.meal.MealRestController;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class MealServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(MealServlet.class);
    private MealRestController controller;
    private ConfigurableApplicationContext springContext = new ClassPathXmlApplicationContext("spring/spring-app.xml");

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        controller = springContext.getBean(MealRestController.class);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String userId = request.getParameter("id");
        String action = request.getParameter("action");
        if (action == null) {
            final Meal meal = new Meal(Integer.valueOf(userId),
                    LocalDateTime.parse(request.getParameter("dateTime")),
                    request.getParameter("description"),
                    Integer.valueOf(request.getParameter("calories")));

            if (request.getParameter("id").isEmpty()) {
                log.info("Create {}", meal);
                controller.create(meal);
            } else {
                log.info("Update {}", meal);
                controller.update(meal, getId(request));
            }
            response.sendRedirect("meals");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        switch (action == null ? "all" : action) {
            case "delete":
                int id = getId(request);
                log.info("Delete {}", id);
                controller.delete(id);
                response.sendRedirect("meals");
                break;

            case "create":
            case "update":
                final Meal meal = "create".equals(action) ?
                        new Meal(1, LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000) :
                        controller.get(getId(request));
                request.setAttribute("meal", meal);
                request.getRequestDispatcher("/mealForm.jsp").forward(request, response);
                break;
            case "all":
            default:
                log.info("getAll");
                LocalDate startDate = DateTimeUtil.parseLocalDate(request.getParameter("start-date"));
                LocalTime startTime = DateTimeUtil.parseLocalTime(request.getParameter("start-time"));
                LocalDate endDate = DateTimeUtil.parseLocalDate(request.getParameter("end-date"));
                LocalTime endTime = DateTimeUtil.parseLocalTime(request.getParameter("end-time"));
                request.setAttribute("meals", controller.getAllByDateOrTime(startDate, startTime, endDate, endTime));
                request.getRequestDispatcher("/meals.jsp").forward(request, response);
                break;
        }
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }

    @Override
    public void destroy() {
        springContext.close();
        super.destroy();
    }
}
