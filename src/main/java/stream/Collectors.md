# Collector

스트림이 중간 연산을 거쳐 최종 연산으로써 데이터를 처리할 때, 그 결과물이 필요한 경우가 많다.  
대표적으로 "리스트나, 맵 같은 자료 구조에 담고 싶다" 거나 "통계 데이터를 내고 싶다"는 요구가 있을 때  
이 최종 연산에 `Collectors`를 활용한다.  
  
collect 연산(예: `stream.collect()`)은 **반환값**을 만들어내는 최종 연산이다.  
`collect(Collector<? super T, A, R> collector)` 형태를 주로 사용하고, `Collectors` 클래스 안에  
준비된 여러 메서드를 통해 다양한 수집 방식을 할 수 있다.  
  
### 참고
필요한 대부분의 기능이 `Collectors`에 이미 구현되어 있기 때문에, `Collector`인터페이스를 직접 구현하는  
것보다는 `Collectors`의 사용법을 익히는 것이 중요하다.  
  
### 자주 사용하는 메서드나 주요한 메서드를 아래 예제 코드로 작성했다.
  
### Collectors.toList()

```java
List<String> nameList = productList.stream().
    map(String::getName),
    collect(Collectors.toList());
```  
해당 결과를 Set으로 받고 싶으면 Collectors.toSet()을 사용하면 된다.  
  
### Collectors.averagingInt(), Collectors.summingInt(), Collectors.summarizingInt();

```java
Double averageAmout = productList.stream().
        collect(Collectors.averagingInt(Product::getAmount));

Integer summingAmount = productList.stream().
        collect(Collectors.summingInt(Product::getAmout));

Integer summingAmount = productList.stream().
        mapToInt(Product::getAmount).
        sum();
```

stream() 하나로 max, min, average, sum 값을 모두 알고 싶다면 summarizingInt() 메서드를 사용하자.  
```java
 IntSummaryStatistics collect2 = books.stream().
        collect(Collectors.summarizingInt(Book::getPrice));
        System.out.println("collect2 = " + collect2);
        
collect2 = IntSummaryStatistics{count=3, sum=35000, min=10000, average=11666.666667, max=14000}
```
  
### Collectors.groupingBy()  
Stream에서 작업한 결과를 특정 그룹으로 묶기를 원할 수 있다. 이러한 경우에는 Collectors.groupingBy()를 이용할 수 있으며,  
결과를 Map으로 반환받게 된다. groupingBy()는 매개변수로 Function 함수형 인터페이스를 필요로 한다.  
```java
Map<String, List<Book>> collect3 = books.stream()
                .collect(Collectors.groupingBy(Book::getAuthor));
        System.out.println("collect3 = " + collect3);

collect3 = {한강=[Book{author='한강', name='소년이 온다', price=11000}],
            하퍼 리=[Book{author='하퍼 리', name='앵무새 죽이기', price=14000}],
            김훈=[Book{author='김훈', name='칼의 노래', price=10000}]}
```
  
### Collectors.partitioningBy()
Collectors.gropingBy()가 함수형 인터페이스 Function을 사용해서 특정 값을 기준으로 Stream 내의 요소들을 그룹핑하였다면,  
Collectors.partitionBy()는 함수형 인터페이스 Predicate를 받아 Boolean을 Key 값으로 partitioning한다.  
```java
 Map<Boolean, List<Book>> collect4 = books.stream().
                collect(Collectors.partitioningBy(n -> n.getPrice() > 10000));
        System.out.println("collect4 = " + collect4);

collect4 =
        {false=[Book{author='김훈', name='칼의 노래', price=9000}],
        true=[Book{author='하퍼 리', name='앵무새 죽이기', price=14000}, Book{author='한강', name='소년이 온다', price=11000}]}
```