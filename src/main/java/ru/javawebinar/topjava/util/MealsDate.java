package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MealsDate {
    private static Map<Integer, Meal> mealMap = new ConcurrentHashMap<>();
    private static AtomicInteger count = new AtomicInteger(0);

    public static List<Meal> getListMeal() {

        return Collections.synchronizedList(new ArrayList<>(mealMap.values()));
    }


    public static void addInMap(LocalDateTime dateTime, String description, int calories) {
        Meal meal = new Meal(dateTime, description, calories, count.get());
        mealMap.put(count.intValue(), meal);
        count.incrementAndGet();
    }

    public static void removeFromMap(int id) {
        if (mealMap.containsKey(id)) {
            mealMap.remove(id);
            count.decrementAndGet();
        }

    }

    public static void updateMap(int id, LocalDateTime dateTime, String description, int calories) {
        if (mealMap.containsKey(id)) {
            Meal meal = mealMap.get(id);
            meal.setDateTime(dateTime);
            meal.setCalories(calories);
            meal.setDescription(description);
            mealMap.put(id, meal);
        }
    }


}


