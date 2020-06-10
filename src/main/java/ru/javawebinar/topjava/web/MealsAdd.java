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

public class MealsAdd extends HttpServlet {
    private static final Logger log = getLogger(MealsAdd.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("redirect to mealsAdd GET");
        req.setCharacterEncoding("UTF-8");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("redirect to mealsAdd Post");
        req.setCharacterEncoding("UTF-8");
        String date = req.getParameter("Date");
        String description = req.getParameter("Description");
        String calories = req.getParameter("Calories");

        req.setAttribute("Date",date);
        req.setAttribute("Description",description);
        req.setAttribute("Calories",calories);

        LocalDateTime localDateTime = LocalDateTime.parse(date);
        int intCalories = Integer.parseInt(calories);

        MealsDate.addInMap(localDateTime,description,intCalories);
        req.getRequestDispatcher("/mealsInfo.jsp").forward(req, resp);

    }
}
