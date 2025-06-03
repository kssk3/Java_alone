## LocalDate, LocalTime, Instant, Duration, Period 클래스
  
### LocalDate와 LocalTime 사용
  
새로운 날짜와 시간 API를 사용할 때 처음 접하게 되는 것이 LocalDate다. LocalDate 인스턴스는 시간을 제외한  
날짜를 표현하는 불변 객체다. 객체는 어떤 시간대 정보도 포함하지 않는다.  
  
정적 팩토리 메서드 of로 인스턴스를 만들 수 있다. 다음 코드에서 보여주는 것처럼  
LocalDate 인스턴스는 연도, 달, 요일 등을 반환하는 메서드를 제공한다.  
  
```java
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
```
  
### 날짜와 시간 객체 출력과 파싱  
`DateTimeFormmater`를 이용하여 날짜 객체를 생성하고 포매팅을 이용하여 특정 형식의 문자열을 출력해보자.  
```java
LocalDate date = LocalDate.now();
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd");
String text = date.format(formatter);
LocalDate parsedDate = LocalDate.parse(text, formatter);
```
  
```java
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
```  
  
### 다양한 시간대와 캘린더 활용 방법
  
기존의 `java.util.TimeZone`을 대체할 수 있는 `java.time.ZoneId`클래스가 새롭게 등장했다.  
새로운 클래스를 이용하면 서머타임(**DST**) 같은 복잡한 사항이 자동으로 처리된다.  
