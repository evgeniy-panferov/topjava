package ru.javawebinar.topjava.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


@Repository
public class InMemoryMealRepository implements MealRepository {
    private Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        save(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),1);
        save(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),1);
        save(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),1);
        save(new Meal(LocalDateTime.of(2020, Month.JUNE, 17, 10, 0), "Завтрак111", 500),1);
        save(new Meal(LocalDateTime.of(2020, Month.JUNE, 17, 12, 0), "Обед111", 1000),1);
        save(new Meal(LocalDateTime.of(2020, Month.JUNE, 17, 18, 0), "Ужин111", 500),1);
        save(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),2);
        save(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),2);
        save(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),2);
        save(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410),2);
        save(new Meal(LocalDateTime.of(2020, Month.JUNE, 17, 0, 0), "Еда на граничное значение1111", 100),2);
        save(new Meal(LocalDateTime.of(2020, Month.JUNE, 17, 10, 0), "Завтрак1111", 1000),2);
        save(new Meal(LocalDateTime.of(2020, Month.JUNE, 17, 13, 0), "Обед1111", 500),2);
        save(new Meal(LocalDateTime.of(2020, Month.JUNE, 17, 20, 0), "Ужин1111", 410),2);
    }

    @Override
    public Meal save(Meal meal, int userId) {
        repository.computeIfAbsent(userId, k -> new HashMap<>());
        if (meal.isNew()) {
            Map<Integer, Meal> temp = repository.get(userId);
            meal.setId(counter.incrementAndGet());
            meal.setUserId(userId);
            temp.put(meal.getId(), meal);
            return meal;
        }
        // handle case: update, but not present in storage
        return repository.get(userId).computeIfPresent(meal.getId(), (k, v) -> meal);
    }

    @Override
    public boolean delete(int id, int userId) {
        return repository.get(userId) != null && repository.get(userId).remove(id) != null;

    }

    @Override
    public Meal get(int id, int userId) {
        Meal meal;
        if(repository.get(userId)==null){
            meal = null;
            return meal;
        }
        meal = repository.get(userId).get(id);
        return meal;
    }

    @Override
    public List<Meal> getAll(int userId) {
        List<Meal> list = new ArrayList<>();
        if (repository.get(userId) != null) {
            list = repository.get(userId).values().stream()
                    .sorted((o1, o2) -> o2.getDate().compareTo(o1.getDate()))
                    .collect(Collectors.toList());
        }
        return list;
    }

}

