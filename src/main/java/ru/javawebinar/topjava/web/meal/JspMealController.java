package ru.javawebinar.topjava.web.meal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Objects;

import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalDate;
import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalTime;

@Controller
@RequestMapping("/meals")
public class JspMealController extends AbstractMealController {

    public JspMealController(MealService service) {
        super(service);
    }

    @GetMapping
    public String get(Model model) {
        model.addAttribute("meals", getAll());
        return "meals";
    }

    @GetMapping("/delete")
    public ModelAndView delete(@RequestParam String paramId) {
        int id = getId(paramId);
        delete(id);
        return new ModelAndView("redirect:meals");
    }

    @GetMapping("/create")
    public String create(Model model) {
        final Meal meal = new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000);
        model.addAttribute("meal", meal);
        return "mealForm";
    }

    @GetMapping("/update")
    public String update(@RequestParam String id, Model model) {
        final Meal meal = get(getId(id));
        model.addAttribute("meal", meal);
        return "mealForm";
    }

    @PostMapping
    protected ModelAndView doPost(HttpServletRequest request) {
        Meal meal = new Meal(
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.parseInt(request.getParameter("calories")));
        String id = request.getParameter("id");
        if (StringUtils.isEmpty(id)) {
            create(meal);
        } else {
            update(meal, getId(id));
        }
        return new ModelAndView("redirect:meals");
    }

    @GetMapping("/filter")
    public String filter(@RequestParam Map<String, String> allParams, Model model) {
        LocalDate startDate = parseLocalDate(allParams.get("startDate"));
        LocalDate endDate = parseLocalDate(allParams.get("endDate"));
        LocalTime startTime = parseLocalTime(allParams.get("startTime"));
        LocalTime endTime = parseLocalTime(allParams.get("endTime"));
        model.addAttribute("meals", getBetween(startDate, startTime, endDate, endTime));
        return "meals";
    }

    private int getId(String id) {
        String paramId = Objects.requireNonNull(id);
        return Integer.parseInt(paramId);
    }

}
