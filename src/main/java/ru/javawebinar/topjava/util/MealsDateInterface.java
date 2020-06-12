package ru.javawebinar.topjava.util;
import ru.javawebinar.topjava.model.Meal;
import java.time.LocalDateTime;
import java.util.List;

public interface MealsDateInterface {
    List<Meal> getListMeal();
    void addInMap(LocalDateTime dateTime, String description, int calories);
    void removeFromMap(int id);
    void updateMap(int id, LocalDateTime dateTime, String description, int calories);


}
