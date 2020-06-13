package ru.javawebinar.topjava.service;

import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.util.Collection;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;
@Service
public class MealService {

    private MealRepository repository;
    private int userId = SecurityUtil.authUserId();;

    public MealService(MealRepository repository) {
        this.repository = repository;

    }

    public Meal create(Meal meal) {
        return repository.save(meal, userId);
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id, userId), id);
    }

    public Meal get(int id) {
        return checkNotFoundWithId(repository.get(id, userId), id);
    }

    public Collection<Meal> getAll() {
        return repository.getAll(userId);
    }

    public void update(Meal meal){
        checkNotFoundWithId(repository.save(meal,userId),meal.getId());
    }
}