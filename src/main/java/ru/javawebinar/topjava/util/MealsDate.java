package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;

import java.time.LocalDateTime;
import java.util.concurrent.CopyOnWriteArrayList;

public class MealsDate {
    private static CopyOnWriteArrayList<Meal> mealsList = new CopyOnWriteArrayList<>();

    public static CopyOnWriteArrayList<Meal> getMealsList() {
        return mealsList;
    }

    public static void setMealsList(CopyOnWriteArrayList<Meal> mealsList) {
        MealsDate.mealsList = mealsList;
    }

    public static void addInList(LocalDateTime dateTime, String description, int calories) {
        Meal meal = new Meal(dateTime, description, calories, getId());
        mealsList.add(meal);
    }

    public static void removeFromList(int id) {
        int deleteId = -1;
        for (int i = 0; i < mealsList.size(); i++) {
            if (mealsList.get(i).getId() == id)
                deleteId = i;
        }

        if (deleteId != -1) {
            mealsList.remove((deleteId - 1));
        }
    }

    public static void updateList(int id,LocalDateTime dateTime, String description, int calories) {
        int updateId = -1;
        for (int i = 0; i < mealsList.size(); i++) {
            if (mealsList.get(i).getId() == id)
                updateId = i;
        }

        if (updateId != -1) {
            mealsList.get(updateId).setDateTime(dateTime);
            mealsList.get(updateId).setCalories(calories);
            mealsList.get(updateId).setDescription(description);
        }
    }


    private static int getId() {
        int i = mealsList.get(mealsList.size() - 1).getId();
        return i;
    }


}


