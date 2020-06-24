package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MealTestData {
    public static final int NOT_FOUND = 10;
    public static final int MEAL_IDUSER1 = 100002;
    public static final int MEAL_IDUSER2 = 100003;
    public static final int MEAL_IDADMIN1 = 100004;
    public static final int MEAL_IDADMIN2 = 100005;
    public static final int USER_ID = SecurityUtil.authUserId();
    public static final int ADMIN_ID = SecurityUtil.authUserId()+1;

    public static final Meal MEALUSER1 = new Meal(MEAL_IDUSER1, LocalDateTime.of(2020, Month.JUNE, 19, 15, 0), "Завтрак", 300);
    public static final Meal MEALUSER2 = new Meal(MEAL_IDUSER2, LocalDateTime.of(2020, Month.JUNE, 19, 19, 0), "Обед", 900);
    public static final Meal MEALADMIN1 = new Meal(MEAL_IDADMIN1, LocalDateTime.of(2020, Month.JUNE, 19, 15, 0), "Обед", 1300);
    public static final Meal MEALADMIN2 = new Meal(MEAL_IDADMIN2, LocalDateTime.of(2020, Month.JUNE, 19, 19, 0), "Ужин", 750);


    public static Meal getNew() {
        return new Meal(null, LocalDateTime.of(2030, Month.DECEMBER, 30, 10, 0), "Ужин", 520);
    }

    public static Meal getUpdated() {
        Meal updated = new Meal(MEALUSER1);
        updated.setDescription("UpdatedName");
        updated.setCalories(330);
        updated.setDateTime(LocalDateTime.of(2040, Month.JUNE, 30, 10, 0));
        return updated;
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualTo(expected);
    }


    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        List<Meal> meals = Arrays.asList(expected);
        assertMatch(actual,meals);
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).isEqualTo(expected);
    }
}
