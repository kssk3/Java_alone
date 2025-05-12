## 매핑 map()

```java
<R> Stream<R> map(Function<? super T, ? extends R> mapper);
```

스트림은 함수를 인수로 받는 map 메서드를 지원한다. 인수로 제공된 함수는 각 요소에 적용되며  
함수를 적용한 결과가 새로운 요소로 매핑된다. (이 과정은 기존의 값을 '고친다'라는 개념보다는 '새로운 버전을 만든다'  
라는 개념에 가까우므로 '**변환**'에 가까운 '**매핑**'이라는 단어를 사용한다.)  
예를 들어 다음은 Dish::getName을 map 메서드로 전달해서 스트림의 요리명을 추출하는 코드이다.

```java
List<String> result = menu.stream()
                .map(Dish::getName)
                .toList();

System.out.println("result = " + result);
// result = [pork, beef, chicken, french fries, rice, season fruit, pizza, prawns, salmon]
```
  
getName은 문자열을 반환하므로 map 메서드의 출력 스트림은 Stream(String) 형식을 갖는다.  
  
각 요리명의 길이를 알고 싶으면 어떻게 해야 할까?  
  
```java
List<Integer> result2 = menu.stream() // Stream<Dish>
                .map(Dish::getName) // Dish 클래스에서 getName -> Stream<String> 변환
                .map(String::length) // Stream<String> -> Stream<Integer>
                .toList();

System.out.println("result2 = " + result2);
// result2 = [4, 4, 7, 12, 4, 12, 5, 6, 6]
```  
  
## 스트림 평면화 flatMap()  
  
우리가 처리해야할 데이터가 2중 배열 또는 2중 리스트로 되어 있고, 이를 1차원으로 처리해야 한다면 어떻게 해야 할까?  
위에 사용했던 map() 으로 2중 배열을 1차원 변경후 중복된 단어를 제거하여 리스트로 반환해보자.
  
```java
List<String> words = Arrays.asList("Hello", "World");

List<String[]> result1 = words.stream()
        .map(n -> n.split(""))
        .distinct()
        .toList();
        
System.out.println("result1 = " + result1);
// result1 = [[Ljava.lang.String;@63961c42, [Ljava.lang.String;@65b54208]
```
  
위에 코드와 같이 list()로 반환할 시 List<String[]> 이 반환된다.  
이는 words.stream()을 반환할 시 아래와 같은 코드로 반환된다.  
  
```java
Stream<String> stream = words.stream();
```  

![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FcWBsve%2FbtqUuMPcQhG%2FEKVs0ZmVB5IwhETi4nN3g1%2Fimg.jpg)  


### flatMap을 활용하여 2차 배열을 1차 배열로 변환하기  
```java
List<String> words = Arrays.asList("Hello", "World");

List<String[]> result1 = words.stream()
        .map(n -> n.split(""))
        .distinct()
        .toList();
System.out.println("result1 = " + result1);
// result1 = [[Ljava.lang.String;@63961c42, [Ljava.lang.String;@65b54208]

List<String> result2 = words.stream()
        .map(n -> n.split(""))
        .flatMap(Arrays::stream)
        .distinct()
        .toList();
System.out.println("result2 = " + result2);
// result2 = [H, e, l, o, W, r, d]
```

- **flatMap**은 각 배열을 스트림이 아니라 스트림의 콘텐츠로 매핑한다.  
  - 즉, map(Arrays::Stream)과 달리 flatMap은 하나의 평면화된 스트림을 반환한다.  
  - flatMap 메서드는 스트림의 각 값을 다른 스트림으로 만든 다음에 모든 스트림을 하나의 스트림으로 연결하는 기능을 수행한다.  

![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FmmDug%2FbtqUwzaL8Zu%2FAd2HK6e1S2B6pfXMNM8WeK%2Fimg.jpg)  

```java
List<Integer> numbers = List.of(1, 2, 3, 4, 5);

List<Integer> result1 = numbers.stream()
        .map(n -> n * n)
        .toList();
System.out.println("result1 = " + result1);
// result1 = [1, 4, 9, 16, 25]

List<Integer> numbers1 = List.of(1, 2, 3);
List<Integer> numbers2 = List.of(4, 5);
List<List<Integer>> result2 = numbers1.stream()
        .flatMap(n -> numbers2.stream().map(
                m -> List.of(n, m)
        )).toList();
System.out.println("result2 = " + result2);
// result2 = [[1, 4], [1, 5], [2, 4], [2, 5], [3, 4], [3, 5]]
```
