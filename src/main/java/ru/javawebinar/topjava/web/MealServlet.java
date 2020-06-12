package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);

    private MealDoCrud mealDoCrud = new MealDoCrud();
    private List<MealTo> mealList;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("redirect to meals");
        req.setCharacterEncoding("UTF-8");

        mealList = Collections.synchronizedList(MealsUtil.filteredByStreams(mealDoCrud.getListMeal(), LocalTime.MAX, LocalTime.MIN, 2000));

        String forward;
        String action = req.getParameter("action");

        if (action == null) {
            forward = "/mealsList.jsp";
        } else if (action.equalsIgnoreCase("update")) {
            forward = "/mealsAddOrUpdate.jsp";
            int id = Integer.parseInt(req.getParameter("id"));
            Meal meal = mealDoCrud.getMeal(id);
            req.setAttribute("mealTo", meal);

        } else if (action.equalsIgnoreCase("remove")) {
            int id = Integer.parseInt(req.getParameter("id"));
            mealDoCrud.remove(id);
            forward = "/mealsList.jsp";
            req.setAttribute("mealTo", mealList);

        } else if (action.equalsIgnoreCase("mealslist")) {

            forward = "/mealsList.jsp";
            req.setAttribute("mealTo", mealList);

        } else if (action.equalsIgnoreCase("add")) {
            forward = "/mealsAddOrUpdate.jsp";
        } else {
            forward = "/mealsList.jsp";
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(forward);
        requestDispatcher.forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("redirect from Post to meals");
        req.setCharacterEncoding("UTF-8");

        String action = req.getParameter("action");
        if (action == null) {
            LocalDateTime localDateTime = LocalDateTime.parse(req.getParameter("Date"));
            String description = req.getParameter("Description");
            int intCalories = Integer.parseInt(req.getParameter("Calories"));
            mealDoCrud.create(localDateTime, description, intCalories);
            req.setAttribute("mealTo", mealList);
        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/mealsList.jsp");
        requestDispatcher.forward(req, resp);

    }
}
