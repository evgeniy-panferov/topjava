package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealsController extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("redirect to mealsCrud GET");
        req.setCharacterEncoding("UTF-8");
        List<MealTo> mealTolist = MealsUtil.filteredByStreams(MealsUtil.createMealList(), LocalTime.MIN,
                LocalTime.MAX, 2000);
        req.setAttribute("mealTo", mealTolist);
        req.getRequestDispatcher("/meals.jsp").forward(req, resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("redirect to mealsCrud Post");
        req.setCharacterEncoding("UTF-8");
        String date = req.getParameter("Date");
        String eating = req.getParameter("Eating");
        String calories = req.getParameter("Calories");
        req.setAttribute("Date", date);
        req.setAttribute("Eating", eating);
        req.setAttribute("Calories", calories);

        req.getRequestDispatcher("/mealsCrud.jsp").forward(req, resp);

    }
}
