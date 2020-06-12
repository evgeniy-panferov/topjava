package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsDate;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class MealDoCrud implements MealCrud {

    @Override
    public void create(LocalDateTime dateTime, String description, int calories) {
        Meal meal = new Meal(dateTime, description, calories);
        MealsDate.addInMap(meal);
    }

    @Override
    public Meal getMeal(int id) {
        return MealsDate.getMealFromMap(id);
    }

    @Override
    public void update(LocalDateTime dateTime, String description, int calories, int id) {
        Meal meal = new Meal(dateTime, description, calories);
        meal.setDateTime(dateTime);
        meal.setCalories(calories);
        meal.setDescription(description);
        MealsDate.updateMap(meal, id);
    }

    @Override
    public void remove(int id) {
        MealsDate.removeFromMap(id);
    }

    @Override
    public List<Meal> getListMeal() {
        return Collections.synchronizedList(MealsDate.getListMeal());

    }
}
