package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.util.MealsDate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;


public class MealsRemove extends HttpServlet {
    private static final Logger log = getLogger(MealsRemove.class);
    private MealDoCrud mealDoCrud = new MealDoCrud();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("redirect to mealsRemove GET");
        req.setCharacterEncoding("UTF-8");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("redirect to mealsRemove Post");
        req.setCharacterEncoding("UTF-8");
        String parameter = req.getParameter("Id");
        int id = Integer.parseInt(parameter);
        mealDoCrud.remove(id);
        resp.sendRedirect("meals");
    }
}