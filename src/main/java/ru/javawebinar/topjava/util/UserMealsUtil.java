package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
Реализовать метод `UserMealsUtil.filteredByCycles` через циклы (`forEach`):
-  должны возвращаться только записи между `startTime` и `endTime`
-  поле `UserMealWithExcess.excess` должно показывать,
                                     превышает ли сумма калорий за весь день значение `caloriesPerDay`

Т.е `UserMealWithExcess` - это запись одной еды, но поле `excess` будет одинаково для всех записей за этот день.

- Проверьте результат выполнения ДЗ (можно проверить логику в http://topjava.herokuapp.com , список еды)
- Оцените Time complexity алгоритма. Если она больше O(N), например O(N*N) или N*log(N), сделайте O(N).
 */

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)

        );

        List<UserMealWithExcess> mealsTo = filteredByCycles(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
          mealsTo.forEach(System.out::println);
        System.out.println(filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
    }

    public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO return filtered list with excess. Implement by cycles

        List<UserMealWithExcess> satisfyMeals = new ArrayList<>();
        Map<LocalDate, Integer> dayCaloriesMap = new HashMap<>();

        for (UserMeal userMeal : meals) {
            LocalDate localDate = userMeal.getDateTime().toLocalDate();
            dayCaloriesMap.merge(localDate, userMeal.getCalories(), (a, b) -> b = a + userMeal.getCalories());
        }

        for (UserMeal userMeal : meals) {
            LocalDate localDate = userMeal.getDateTime().toLocalDate();
            LocalTime dayTime = userMeal.getDateTime().toLocalTime();
            boolean isExcess = false;
            if (dayCaloriesMap.get(localDate) > caloriesPerDay) {
                isExcess = true;
            }
            if (TimeUtil.isBetweenHalfOpen(dayTime, startTime, endTime)) {
                satisfyMeals.add(new UserMealWithExcess(userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories(), isExcess));
            }
        }
        return satisfyMeals;
    }

    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO Implement by streams

        Map<LocalDate, Integer> dayCaloriesMap = meals.stream().collect(Collectors.toMap(
                userMeal -> userMeal.getDateTime().toLocalDate(),
                UserMeal::getCalories,
                Integer::sum
        ));

        List<UserMealWithExcess> satisfyMeals;
        satisfyMeals = meals.stream()
                .filter(userMeal -> TimeUtil.isBetweenHalfOpen(userMeal.getDateTime().toLocalTime(), startTime, endTime))
                .map(userMeal -> {
                    boolean isExcess = false;
                    if (dayCaloriesMap.get(userMeal.getDateTime().toLocalDate()) > caloriesPerDay) {
                        isExcess = true;
                    }
                    return new UserMealWithExcess(userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories(), isExcess);
                }).collect(Collectors.toList());
        return satisfyMeals;
    }
}
