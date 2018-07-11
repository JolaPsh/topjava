package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;

public class UserServlet extends HttpServlet {
    private static final Logger log = getLogger(UserServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("forward to users");

        String userId = request.getParameter("userId");
        List<Meal> userMeal = MealsUtil.MEALS.stream().filter(v -> v.getUserId().equals(userId)).collect(Collectors.toList());
        request.setAttribute("userId", userMeal);
        response.sendRedirect("meals");
        //  request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }
}
