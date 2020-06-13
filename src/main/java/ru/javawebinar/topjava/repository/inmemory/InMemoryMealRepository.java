package ru.javawebinar.topjava.repository.inmemory;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;



public class InMemoryMealRepository implements MealRepository {
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(meal -> this.save(meal, SecurityUtil.authUserId()));
    }

    @Override
    public Meal save(Meal meal, int userId) {
        if (meal.isNew()) {
            meal.setUserId(userId);
            meal.setId(counter.incrementAndGet());
            repository.put(meal.getId(), meal);
            return meal;
        }
        // handle case: update, but not present in storage
        return repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int id, int userId) {
        List<Meal> collect = repository.values().stream()
                .filter(meal -> meal.getUserId() == userId)
                .limit(1)
                .collect(Collectors.toList());

        return !collect.isEmpty() && repository.remove(collect.get(0).getId()) != null;

    }

    @Override
    public Meal get(int id, int userId) {
        List<Meal> collect = repository.values().stream()
                .filter(meal -> meal.getUserId() == userId)
                .limit(1)
                .collect(Collectors.toList());
        return collect.isEmpty() ? null : repository.get(collect.get(0).getId());

    }

    @Override
    public Collection<Meal> getAll(int userId) {

        List<Meal> collect = repository.values().stream()
                .filter(meal -> meal.getUserId() == userId)
                .sorted(Comparator.comparing(Meal::getDate))
                .collect(Collectors.toList());


        return null;
    }
}

