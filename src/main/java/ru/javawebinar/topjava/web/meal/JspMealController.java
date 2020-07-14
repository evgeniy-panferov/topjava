package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import java.util.Objects;

import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalDate;
import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalTime;

@Controller
@RequestMapping("/meals")
public class JspMealController extends AbstractRestController {

    @Qualifier("abstractRestController")
    @Autowired
    private AbstractRestController abstractRestController;

    public JspMealController(MealService service) {
        super(service);
    }

    @GetMapping
    public String getMeals(Model model) {
        model.addAttribute("meals", abstractRestController.getAll());
        return "meals";
    }

    @GetMapping(params = {"delete", "id"})
    public ModelAndView delete(@RequestParam(value = "id") String paramId) {
        int id = getId(paramId);
        abstractRestController.delete(id);
        return new ModelAndView("redirect:/meals");
    }

    @GetMapping(params = "action")
    public String createOrUpdate(@RequestParam(value = "action") String action,
                                 HttpServletRequest request, Model model) {
        String id = request.getParameter("id");
        final Meal meal = "create".equals(action) ?
                new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000) :
                abstractRestController.get(getId(id));
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
            abstractRestController.create(meal);
        } else {
            abstractRestController.update(meal, getId(id));
        }
        return new ModelAndView("redirect:/meals");
    }

    @GetMapping(params = "filter")
    public String filter(HttpServletRequest request) {
        LocalDate startDate = parseLocalDate(request.getParameter("startDate"));
        LocalDate endDate = parseLocalDate(request.getParameter("endDate"));
        LocalTime startTime = parseLocalTime(request.getParameter("startTime"));
        LocalTime endTime = parseLocalTime(request.getParameter("endTime"));
        request.setAttribute("meals", abstractRestController.getBetween(startDate, startTime, endDate, endTime));
        return "meals";
    }

    private int getId(String id) {
        String paramId = Objects.requireNonNull(id);
        return Integer.parseInt(paramId);
    }

}
