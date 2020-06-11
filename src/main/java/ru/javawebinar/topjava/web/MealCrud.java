package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;

public interface MealCrud {
    void create(LocalDateTime dateTime, String description, int calories);
    Meal read(int id);
    void update(LocalDateTime dateTime, String description, int calories, int id);
    void remove(int id);
}
