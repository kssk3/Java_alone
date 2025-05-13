# Chap.5 스트림 활용 

## 5.2 스트림 슬라이싱

스트림의 요소를 선택하거나 스킵하는 다양한 방법을 설명한다.  
`Predicate`를 이용하는 방법, 스트림의 처음 몇 개의 요소를 무시하는 방법, 특정 크기로 스트림을 줄이는 방법 등  
다양한 방법을 이용해 효율적으로 이런 작업을 수행할 수 있다.   
  
### 5.2.1 Predicate를 이용한 슬라이싱
  
```java
@FunctionalInterface
public interface Predicate<T> {
    boolean test(T t);
    
public class StreamSlicingMainV1 {
    public static void main(String[] args) {

        List<Integer> numbers = List.of(150, 170, 210, 250, 310, 350);
        System.out.println("numbers = " + numbers);

        List<Integer> result1 = numbers.stream()
                .filter(n -> n > 200)
                .toList();
        System.out.println("result1 = " + result1);
    }
}
// numbers = [150, 170, 210, 250, 310, 350]
// result1 = [210, 250, 310, 350]
```
  
### TAKE WHILE, DROPWHILE 활용  
  
```java

public class StreamSlicingMainV1 {
    public static void main(String[] args) {

        List<Integer> numbers = List.of(150, 170, 210, 250, 310, 350);
        System.out.println("numbers = " + numbers);

        List<Integer> result1 = numbers.stream()
                .takeWhile(n -> n < 210)
                .toList();
        System.out.println("result1 = " + result1);

        List<Integer> result2 = numbers.stream()
                .dropWhile(n -> n < 210)
                .toList();
        System.out.println("result2 = " + result2);
    }
}
// numbers = [150, 170, 210, 250, 310, 350]
// result1 = [150, 170]
// result2 = [210, 250, 310, 350]
```

- **TAKE WHILE**
 - `takeWhile`은 `Predicate`가 처음으로 거짓이 되는 지점까지 발견된 요소를 모두 담고 나머지는 버린다.
 - numbers 리스트에 210 이상의 값이 나오기 전까지 값을 리스트로 만들어서 반환한다.
- **DROP WHILE**
  - `dropWhile`은 `Predicate`가 처음으로 거짓이 되는 지점까지 발견된 요소를 버린다.
  - numbers 리스트에 210 이상의 값이 나오면 전의 값은 버리고 나머지 값의 모두를 리스트로 반환한다.
  
### 5.2.2 스트림 축소
  
스트림은 주어진 값 이하의 크기를 갖는 새로운 스트림을 반환하는 limit(n) 메서드를 지원한다. 스트림이 정렬되어 있으면  
최대 요소 n개를 반환할 수 있다.   
  
```java
public class StreamSlicingMainV1 {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(150, 170, 210, 250, 310, 350);
        System.out.println("numbers = " + numbers);

        List<Integer> result = numbers.stream()
                .filter(n -> n > 170)
                .limit(3)
                .toList();

        System.out.println("result = " + result);
    }
}
// numbers = [150, 170, 210, 250, 310, 350]
// result = [210, 250, 310]
```
  
### 5.8.5 함수로 무한 스트림 만들기  
  
스트림 API는 함수에서 스트림을 만들 수 있는 두 정적 메서드 Stream.iterate와 Stream.generate를 제공한다.  
두 연산을 이용해서 **무한 스트림**, 즉 고정된 컬렉션에서 고정된 크기로 스트림을 만들었던 것과는 달리 크기가 고정되지 않은  
스트림을 만들 수 있다.  
  
```java
public static<T> Stream<T> iterate(final T seed, final UnaryOperator<T> f) 
```  
```java
List<Integer> result1 = Stream.iterate(1, n -> n + 2)
        .limit(5)
        .toList();
System.out.println("result1 = " + result1);

List<Integer> result2 = Stream.iterate(0, n -> n + 2)
        .limit(10)
        .toList();
System.out.println("result2 = " + result2);

// result1 = [1, 3, 5, 7, 9]
// result2 = [0, 2, 4, 6, 8, 10, 12, 14, 16, 18]
```  
  
iterate 메서드는 초깃값과 람다(`UnaryOperator<T>` 사용)를 인수로 받아서 새로운 값을 끊임없이 생산할 수 있다.  
iterate는 요청할 때마다 값을 생산할 수 있으며 끝이 없으므로 **무한 스트림**을 만든다. 이러한 스트림을 **언바운드 스트림**이라고 표현한다.  
예제에서 limit 메서드를 이용해서 스트림의 크기를 명시적으로 작성했지만 limit을 설정하지 않을 경우 무한 스트림이므로 최종 연산까지 불러올 수 없다.  
`limit`을 설정하지 않을 경우 쓸모 없는 스트림을 생선한 것과 같다.  
  
### generate 메서드
  
iterate와 비슷하게 generate도 요구할 때 값을 계산하는 무한 스트림을 만들 수 있다. 하지만 iterate와 달리 generate는  
생산된 각 값을 연속적으로 계산하지 않는다. generate는 `Supplier<T>`를 인수로 받아서 새로운 값을 생산한다.   
  
```java
Stream.generate(Math::random)
.limit(5)
.forEach(System.out::println);

// 0.7579631740758049
// 0.012014378912227786
// 0.6307140108638752
// 0.8074415490570976
// 0.522283568951757
```
  

