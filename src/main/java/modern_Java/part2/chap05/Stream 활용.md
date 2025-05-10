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
  
