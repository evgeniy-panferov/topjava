package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.util.MealsDate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

import static org.slf4j.LoggerFactory.getLogger;

public class MealsUpdate extends HttpServlet {
    MealsDate mealsDate=new MealsDate();
    private static final Logger log = getLogger(MealsRemove.class);


    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("redirect to mealsUpdate GET");
        req.setCharacterEncoding("UTF-8");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("redirect to mealsUpdate Post");
        req.setCharacterEncoding("UTF-8");
//        req.getRequestDispatcher("/mealsUpdate.jsp").forward(req, resp);


        LocalDateTime localDateTime = LocalDateTime.parse(req.getParameter("Date"));
        String description = req.getParameter("Description");
        int intCalories = Integer.parseInt(req.getParameter("Calories"));
        String parameter = req.getParameter("Id");
        int id = Integer.parseInt(parameter);
        MealsDate.updateMap(id-1, localDateTime, description, intCalories);
        resp.sendRedirect("meals");
    }
}
