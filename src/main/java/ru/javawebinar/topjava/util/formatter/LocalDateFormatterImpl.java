package ru.javawebinar.topjava.util.formatter;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LocalDateFormatterImpl implements Formatter<LocalDate> {

    private String pattern;

    public LocalDateFormatterImpl() {
        this.pattern = "yyyy-MM-dd";
    }

    @Override
    public String print(LocalDate localDate, Locale locale) {
        if (localDate == null) {
            return "";
        }
        return localDate.format(getDateFormat(locale));
    }

    @Override
    public LocalDate parse(String formatted, Locale locale) throws ParseException {
        if (formatted.length() == 0) {
            return null;
        }
        return LocalDate.parse(formatted, getDateFormat(locale));
    }

    protected DateTimeFormatter getDateFormat(Locale locale) {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd", locale);
    }
}
