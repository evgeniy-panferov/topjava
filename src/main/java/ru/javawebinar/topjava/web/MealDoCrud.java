package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsDate;

import java.time.LocalDateTime;

public class MealDoCrud implements MealCrud {

    @Override
    public void create(LocalDateTime dateTime, String description, int calories) {
        Meal meal = new Meal(dateTime, description, calories);
        MealsDate.addInMap(meal);
    }

    @Override
    public Meal read(int id) {
        return MealsDate.readMealFromMap(id);
    }

    @Override
    public void update(LocalDateTime dateTime, String description, int calories, int id) {
        Meal meal = new Meal(dateTime, description, calories);
        meal.setDateTime(dateTime);
        meal.setCalories(calories);
        meal.setDescription(description);
        MealsDate.updateMap(meal,id);
    }

    @Override
    public void remove(int id) {
        MealsDate.removeFromMap(id);
    }
}
