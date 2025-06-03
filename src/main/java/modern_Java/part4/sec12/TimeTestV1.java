package modern_Java.part4.sec12;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;

public class TimeTestV1 {

    public static void main(String[] args) {
        LocalDate date = LocalDate.of(2017, 8, 14);
        int year = date.getYear();
        int month = date.getMonthValue();
        int day = date.getDayOfMonth();
        DayOfWeek dow = date.getDayOfWeek();
        int len = date.lengthOfMonth();
        boolean leapYear = date.isLeapYear();

        LocalDate now = LocalDate.now();

        System.out.println("year = " + year);        //year = 2017
        System.out.println("month = " + month);      //month = 8
        System.out.println("day = " + day);          //day = 14
        System.out.println("dow = " + dow);          //dow = MONDAY
        System.out.println("len = " + len);          //len = 31
        System.out.println("leapYear = " + leapYear);//leapYear = false

        System.out.println("now = " + now);          //now = 2025-05-29

//        int year = date.get(ChronoField.YEAR);
//        int month = date.get(ChronoField.MONTH_OF_YEAR);
//        int day = date.get(ChronoField.DAY_OF_MONTH);
//
//        System.out.println("year = " + year);
//        System.out.println("month = " + month);
//        System.out.println("day = " + day);
    }
}
