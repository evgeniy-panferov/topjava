package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static ru.javawebinar.topjava.MealTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Autowired
    private MealRepository repository;

    @Test
    public void get() throws Exception {
        Meal meal = service.get(MEAL_IDUSER1, USER_ID);
        Meal meal1 = service.get(MEAL_IDUSER2, USER_ID);
        Meal meal2 = service.get(MEAL_IDADMIN1, ADMIN_ID);
        Meal meal3 = service.get(MEAL_IDADMIN2, ADMIN_ID);
        assertMatch(meal, MEALUSER1);
        assertMatch(meal1, MEALUSER2);
        assertMatch(meal2, MEALADMIN1);
        assertMatch(meal3, MEALADMIN2);
    }

    @Test
    public void getNotFount() throws Exception {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUND, USER_ID));
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUND, ADMIN_ID));
    }


    @Test
    public void getAlien() throws Exception {
        assertThrows(NotFoundException.class, () -> service.get(MEAL_IDUSER2, ADMIN_ID));
        assertThrows(NotFoundException.class, () -> service.get(MEAL_IDADMIN1, USER_ID));
    }

    @Test
    public void delete() throws Exception {
        service.delete(MEAL_IDUSER1, USER_ID);
        assertNull(repository.get(MEAL_IDUSER1, USER_ID));
    }

    @Test
    public void deletedNotFount() throws Exception {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUND, USER_ID));
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUND, ADMIN_ID));
    }

    @Test
    public void deleteAlien() throws Exception {
        assertThrows(NotFoundException.class, () -> service.delete(MEAL_IDUSER2, ADMIN_ID));
        assertThrows(NotFoundException.class, () -> service.delete(MEAL_IDADMIN1, USER_ID));
    }

    @Test
    public void getBetweenInclusive() throws Exception {
        List<Meal> timeMealsUser = service.getBetweenInclusive(LocalDateTime.of(2020, Month.JUNE, 19, 15, 0).toLocalDate(),
                LocalDateTime.of(2020, Month.JUNE, 19, 19, 0).toLocalDate(), USER_ID);
        timeMealsUser.sort(Comparator.comparing(Meal::getDateTime).reversed());
        assertMatch(timeMealsUser, MEALUSER2,MEALUSER1);
    }

    @Test
    public void getAll() throws Exception {
        List<Meal> userMeals = service.getAll(USER_ID);
        List<Meal> adminMeals = service.getAll(ADMIN_ID);
        assertMatch(userMeals, MEALUSER2, MEALUSER1);
        assertMatch(adminMeals, MEALADMIN2, MEALADMIN1);
    }

    @Test
    public void updateAlien() throws Exception {
        Meal updated = getUpdated();
        assertThrows(NotFoundException.class, () -> service.update(updated, ADMIN_ID));
    }

    @Test
    public void update() throws Exception {
        Meal updated = getUpdated();
        service.update(updated, USER_ID);
        assertMatch(service.get(MEALUSER1.getId(), USER_ID), updated);
    }

    @Test
    public void create() throws Exception {
        Meal mealNew = getNew();
        Meal created = service.create(mealNew, USER_ID);
        Integer newId = created.getId();
        mealNew.setId(newId);
        assertMatch(created, mealNew);
        assertMatch(service.get(newId, USER_ID), mealNew);
    }

}