package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MealsDate implements MealsDateInterface{
    private static Map<Integer, Meal> mealMap = new ConcurrentHashMap<>();
    private static AtomicInteger count = new AtomicInteger(0);

    public List<Meal> getListMeal() {
        return Collections.synchronizedList(new ArrayList<>(mealMap.values()));
    }


    public void addInMap(LocalDateTime dateTime, String description, int calories) {
        Meal meal = new Meal(dateTime, description, calories, count.get());
        mealMap.put(count.intValue(), meal);
        count.incrementAndGet();
    }

    public void removeFromMap(int id) {
        synchronized (this) {
            if (mealMap.containsKey(id)) {
                mealMap.remove(id);
                count.decrementAndGet();
            }
        }
    }

    public void updateMap(int id, LocalDateTime dateTime, String description, int calories) {
        synchronized (this) {
            if (mealMap.containsKey(id)) {
                Meal meal = mealMap.get(id);
                meal.setDateTime(dateTime);
                meal.setCalories(calories);
                meal.setDescription(description);
                mealMap.put(id, meal);
            }
        }
    }

    public void createTestList(){
        addInMap(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500);
        addInMap(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000);
        addInMap(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500);
        addInMap(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100);
        addInMap(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000);
        addInMap(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500);
        addInMap(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410);

    }


}


