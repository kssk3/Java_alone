# optional

## 옵셔널이 필요한 이유
### NullPointException(NPE)문제
- 자바에서 `null`은 "값이 없음"을 표현하는 가장 기본적인 방법이다.
- 하지만 `null`을 잘못 사용하거나 `null`참조에 대해 메서드를 호출하면 `NullPointException`(NPE)이 발생하므로  
프로그램이 예기치 않게 종료될 수 있다.  
- 특히 여러 메서드가 연쇄적으로 호출되어 내부에서 `null`체크가 누락되면, 추적하기 어렵고 디버깅 비용이 증가한다.  
  
### 가독성 저하
- `null`을 반환하거나 사용하게 되면, 코드를 작성할 때마다 `if(obj == null){...}`같은 조건문으로  
`null`여부를 계속 확인해야 한다.
- 이러한 `null`체크 로직이 누적되면 코드가 복잡해지고 가독성이 떨어진다.
  
### 의도가 드러나지 않음
- 메서드 시그니처 (`String findNameById(Long id)`)만 보고서는 이 메서드가 `null`을 반환할 수도 있다는 사실을 명확하게 알기 어렵다.  
- 호출하는 입장에서는 "반드시 값이 존재할 것"이라고 가정했다가, 런타임에 `null`이 나와서 문제가 생길 수 있다.  
  
### Optional의 등장
- 이러한 문제를 해결하고자 자바 8부터 `Optional`클래스를 도입했다.
- `Optional`은 "값이 있을 수도 있고 없을 수도 있음"을 명시적으로 표현해주어, 메서드의 계약(Contract)이나  
호출 의도를 좀 더 분명하게 드러낸다.  
- `Optional`을 사용하면 "빈 값"을 표현할 때, 더 이상 `null`자체를 넘겨주지 않고 `Optional.empty()`처럼  
의도를 드러내는 객체를 사용할 수 있다.
- 그 결과, `Optional`을 사용하면 `null`체크 로직을 간결하게 만들고, 특정 경우에 NPE가 발생할 수 있는 부분을  
빌드 타임이나, IDE, 코드 리뷰에서 더 쉽게 파악할 수 있게 해준다.  
  
## Optional 메서드 종류

### Optional.of(), ofNullable(), empty()
```java
public class OptionalMain {
    public static void main(String[] args) {
        Optional<Integer> optValue1 = Optional.of(100);
        Optional<Integer> optValue2 = Optional.ofNullable(100);
        Optional<Integer> optEmpty1 = Optional.ofNullable(null);
        Optional<Object> optEmpty2 = Optional.empty();

        System.out.println("Optional.of(100) = " + optValue1);
        System.out.println("Optional.ofNullable(100) = " + optValue2);
        System.out.println("Optional.ofNullable(null) = " + optEmpty1);
        System.out.println("Optional.empty() = " + optEmpty2);
    }
}
```
```java
Optional.of(100) = Optional[100]
Optional.ofNullable(100) = Optional[100]
Optional.ofNullable(null) = Optional.empty
Optional.empty() = Optional.empty
```
- `of()`
  - of 메서드의 경우 값이 무조건 있을 때 사용하는 메서드이다.
  - 만약 값이 존재하지 않을 시 `NullPointException`이 발생한다.
- `ofNullable()`
  - 값이 있을 수도 있고, 없을 수도 있다는 명시적인 메서드로 값이 존재할 때는 반환하고 없을 때는 empty를 반환한다.  
- `empty()`
  - 값이 없다는 것을 명시할 때 사용한다.
  
## 값을 확인하거나 반환하는 메서드  

### isPresent(), isEmpty()
```java
public boolean isPresent() { return value != null; }
public boolean isEmpty() { return value == null; }
```

```
   public static void main(String[] args) {
        Optional<String> optValue = Optional.ofNullable("100");
        Optional<String> optEmpty = Optional.ofNullable(null);

        System.out.println(optValue.isPresent());   // true
        System.out.println(optValue.isEmpty());     // false
        System.out.println(optEmpty.isPresent());   // false
        System.out.println(optEmpty.isEmpty());     // true
    }
```

### get()  
```java
public T get() {
        if (value == null)
        {throw new NoSuchElementException("No value present");}
        return value;}
```
```java
    public static void main(String[] args) {
        Optional<String> optValue = Optional.ofNullable("100");
        Optional<String> optEmpty = Optional.ofNullable(null);

        System.out.println("optValue = " + optValue.get());// optValue = 100
        System.out.println("optEmpty = " + optEmpty.get());// NoSuchElementException
    }
```
- 직접 사용 시 주의해야하며, 가급적이면 다른 메서드 `orElse()`, `orElseXXX()` 계열 메서드를 사용하는 것이 안전하다.  
  
### OrElse()
```java
 public T orElse(T other) {
    return value != null ? value : other;}
```

```java
public static void main(String[] args) {
    Optional<Integer> optValue = Optional.ofNullable(100);
    Optional<Integer> optEmpty = Optional.ofNullable(null);

    System.out.println("optValue.orElse(50) = " + optValue.orElse(50));// optValue.orElse(50) = 100
  System.out.println("optEmpty.orElse(50) = " + optEmpty.orElse(50));// optEmpty.orElse(50) = 50
}
```
- 값이 있으면 원래 값 반환
- 없으면 `other`반환

### orElseGet()
```java
public T orElseGet(Supplier<? extends T> supplier) {
        return value != null ? value : supplier.get();
    }
```

```java
public static void main(String[] args) {
    Optional<Integer> optValue = Optional.ofNullable(100);
    Optional<Integer> optEmpty = Optional.ofNullable(null);

    System.out.println("optValue.orElseGet(() -> 500) = " + optValue.orElseGet(() -> 500));
    // optValue.orElseGet(() -> 500) = 100
    System.out.println("optEmpty.orElseGet(() -> 500) = " + optEmpty.orElseGet(() -> 500));
    // optEmpty.orElseGet(() -> 500) = 500
}
```
- 값이 있으면 원래 값 반환 
- 값이 존재하지 않으면 Supplier.get() 반환

### OrElse(), OrElseGet()의 메서드 차이점
- 위에 예제 코드를 보면 값이 있으면 원래 값 반환하고 없으면 다른 값을 반환하는 같은 메서드로 보이지만 둘의 차이점은 크게 있다.
- **즉시 반환**과 **지연 반환**의 차이점이 있다.
- `orElse()`의 경우 매개변수 `(T other)` 객체의 값을 직접 반환하고 있고
- `orElseGet()`의 경우 매개변수 `Supplier other` 함수형 인터페이스를 반환하고 있다. 
- 아래 예제 코드를 보고 그 차이점을 알아보자.
  
```java

public class OptionalMain {
    static String email = "16516541-151654195-152121";
    public static void main(String[] args) {
      Optional<String> emailA = Optional.ofNullable(email);
      Optional<String> emailB = Optional.ofNullable(email);

        System.out.println("=== 즉시 연산 ===");
        String result1 = emailA.orElse(findOrElseEmail());
        System.out.println("result1 = " + result1);
        System.out.println();

        System.out.println("=== 지연 연산 ===");
        String result2 = emailB.orElseGet(() -> findOrElseGetEmail());
        //String result2 = emailB.orElseGet(OptionalMain::findOrElseGetEmail);
        System.out.println("result2 = " + result2);

    }
   static String findOrElseEmail() {
     System.out.println("이메일 데이터를 찾고 있습니다.");
     String email = "0000000_000000_00000";
     return email;
   }
   static String findOrElseGetEmail() {
     System.out.println("이메일 데이터를 찾고 있습니다.");
     String email = "0000000_000000_00000";
     return email;
   }
}
```
```java
=== 즉시 연산 ===
이메일 데이터를 찾고 있습니다.
result1 = 16516541-151654195-152121

=== 지연 연산 ===
result2 = 16516541-151654195-152121
```
- `orElse`
  - 출력 값을 보면 email 값이 있는 상태에도 orElse() 메서드의 경우 객체 값을 반환하기 때문에  
    메서드가 즉시 실행되고 다른 값을 반환한다. 하지만 기존 값이 있으므로 반환된 새로운 값은 사라진다.
  - Optional의 단말 연산으로 orElse()를 사용할 경우 조회 결과와 무관하게 `findOrElseEmail`메서드가 실행된다.  
  - 자바는 메서드 호출 전 괄호 안의 표현식을 먼저 계산하는 (**즉시 평가**) 특징이 있어, `orElse()`에 전달된 값이  
  필요하지 않아도 미리 실행될 수 있다.
- `OrElseGet()`
  - 메서드의 경우 값이 없으면 Supplier를 통해 대체된 값을 계산하여 반환하기 때문에 메서드가 실행하지 않는다.
  - `orElseGet()`에 람다(`Supplier`)를 넘기면, 실제로 대체 값이 필요할 때만 (**지연 평가**) 람다가 실행되어  
  불필요한 연산을 방지할 수 있다.  
  
### orElseThrow(...)
- 값이 있으면 그 값을 반환
- 값이 없으면 지정한 예외를 던진다. 
  
### or(Supplier< ? extends Optional< ? extends T >> supplier)
- 값이 있으면 해당 `Optional`을 그대로 반환
- 값이 없으면 `supplier`가 제공하는 다른 `Optional`반환
- 값 대신 `Optional`을 반환한다는 특징
  
  





참고 사이트
- https://mangkyu.tistory.com/70  
- https://mangkyu.tistory.com/203