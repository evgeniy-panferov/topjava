package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;

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


    public static void addInMap(Meal meal) {
        meal.setId(count.intValue());
        mealMap.put(count.intValue(), meal);
        count.incrementAndGet();
    }

    public static void removeFromMap(int id) {
        synchronized (MealsDate.class) {
            if (mealMap.containsKey(id)) {
                mealMap.remove(id);
                count.decrementAndGet();
            }
        }
    }

    public static void updateMap(Meal meal, int id) {
        synchronized (MealsDate.class) {
            if (mealMap.containsKey(id)) {
                meal.setId(id);
                mealMap.put(id, meal);
            }
        }
    }

    public static Meal readMealFromMap(int id) {
        Meal meal=null;
        synchronized (MealsDate.class) {
            if (mealMap.containsKey(id)) {
                 meal = mealMap.get(id);
            }
        }
        return meal;
    }

}


