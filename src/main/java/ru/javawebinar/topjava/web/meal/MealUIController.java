package ru.javawebinar.topjava.web.meal;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealTo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/user/meals")
public class MealUIController extends AbstractMealController {

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MealTo> getAll() {
        return super.getAll();
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void create(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTime,
                       @RequestParam String description,
                       @RequestParam int calories) {

        Meal meal = new Meal(dateTime, description, calories);
        super.create(meal);
    }

    @Override
    @GetMapping(value = "/filter")
    public List<MealTo> getBetween(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(pattern = "HH:mm") LocalTime startTime,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam @DateTimeFormat(pattern = "HH:mm") LocalTime endTime) {

        LocalDate startDateNotNull = startDate == null ? LocalDate.MIN : startDate;
        LocalDate endDateNotNull = endDate == null ? LocalDate.MAX : endDate;
        LocalTime startTimeNotNull = startTime == null ? LocalTime.MIN : startTime;
        LocalTime endTimeNotNull = endTime == null ? LocalTime.MAX : endTime;

        return super.getBetween(startDateNotNull, startTimeNotNull, endDateNotNull, endTimeNotNull);
    }

}
