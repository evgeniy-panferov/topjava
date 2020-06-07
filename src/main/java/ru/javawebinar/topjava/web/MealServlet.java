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

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("redirect to meals");
        req.setCharacterEncoding("UTF-8");
        List<MealTo> mealTolist = MealsUtil.filteredByStreams(MealsUtil.create(), LocalTime.of(0, 0),
                LocalTime.of(23, 0), 2000);
        req.setAttribute("mealTo", mealTolist);
        req.getRequestDispatcher("/meals.jsp").forward(req, resp);
    }
}
