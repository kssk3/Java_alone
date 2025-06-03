package modern_Java.part4.sec12;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Locale;

public class FormatTimeV1 {

    public static void main(String[] args) {
        LocalDate result1 = LocalDate.parse("20240428", DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println("result1 = " + result1);// result1 = 2024-04-28

        LocalDate result2 = LocalDate.parse("2024-04-28", DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println("result2 = " + result2);// result2 = 2024-04-28

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date1 = LocalDate.now();
        String result3 = date1.format(formatter);
        System.out.println("result3 = " + result3);// result3 = 2025-06-03

        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date2 = LocalDate.of(2022,1,22);
        String formattedDate = date2.format(formatter2);
        LocalDate result4 = LocalDate.parse(formattedDate, formatter2);
        System.out.println("result4 = " + result4);// result4 = 2022-01-22

        String result5 = date2.format(formatter2);
        System.out.println("result5 = " + result5);// result5 = 22/01/2022

        DateTimeFormatter canadaFormatter = new DateTimeFormatterBuilder()
                .appendText(ChronoField.DAY_OF_MONTH)
                .appendLiteral(". ")
                .appendText(ChronoField.MONTH_OF_YEAR)
                .appendLiteral(" ")
                .appendText(ChronoField.YEAR)
                .parseCaseInsensitive()
                .toFormatter(Locale.CANADA);

        String result6 = date1.format(canadaFormatter);
        System.out.println("result6 = " + result6);// result6 = 3. June 2025
    }
}
