package ru.netology;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateCreator {
    public static String getDate(int dayAfterToday) {

        return LocalDate.now().plusDays(dayAfterToday).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

}
