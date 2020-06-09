package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MealsDate {
    private Map<AtomicInteger, Meal> mealMap = new ConcurrentHashMap<>();
    private static AtomicInteger count = new AtomicInteger(0);


    public Map<AtomicInteger, Meal> getMealsMap() {
        return mealMap;
    }

    public void addInList(LocalDateTime dateTime, String description, int calories) {
        Meal meal = new Meal(dateTime, description, calories, getId());
        mealMap.put(getId(), meal);

    }

    public void removeFromList(int id) {
        AtomicInteger idAtom = new AtomicInteger(id);
        if(mealMap.containsKey(idAtom)){
            mealMap.remove(idAtom);
        }
    }

    public void updateList(int id, LocalDateTime dateTime, String description, int calories) {
        AtomicInteger idAtom = new AtomicInteger(id);
        if(mealMap.containsKey(idAtom)){
            Meal meal = mealMap.get(idAtom);
            meal.setDateTime(dateTime);
            meal.setCalories(calories);
            meal.setDescription(description);
            mealMap.put(idAtom,meal);
        }
    }


    private AtomicInteger getId() {
        count.addAndGet(1);
        return count;
    }


}


