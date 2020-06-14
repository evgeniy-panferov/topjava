package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.util.Collection;

import static org.slf4j.LoggerFactory.getLogger;


@Controller
public class MealRestController {
  private static final Logger log = getLogger(MealRestController.class);

    @Autowired
    private MealService service;

    private int userId = SecurityUtil.authUserId();

    public Meal create(Meal meal) {
        log.info("create {}",meal);
        return service.create(meal, userId);
    }

    public void delete(int id) {
        log.info("delete {}",id);
        service.delete(id, userId);
    }

    public Meal get(int id) {
        Meal meal = service.get(id, userId);
        log.info("get {}",meal);
        return meal;
    }

    public Collection<Meal> getAll() {
        log.info("getAll");
        return service.getAll(userId);
    }


    public void update(Meal meal) {
        log.info("update {}",meal);
        service.update(meal, userId);
    }

}