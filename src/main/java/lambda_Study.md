# lambda

```java
package lambda.start;

public class Ex0Main {
    public static void main(String[] args) {
//        helloJava();
//        helloSpring();
        helloSomething("Hello Java");
        helloSomething("Hello Spring");
    }

    public static void helloSomething(String text) {
        System.out.println("프로그램 시작");
        System.out.println(text);
        System.out.println("프로그램 종료");
    }

    public static void helloJava() {
        System.out.println("프로그램 시작");
        System.out.println("Hello Java");
        System.out.println("프로그램 종료");
    }

    public static void helloSpring() {
        System.out.println("프로그램 시작");
        System.out.println("Hello Spring");
        System.out.println("프로그램 종료");
    }
}
```

단순한 이코드에서 핵심은 **변하는 부분과 변하지 않는 부분을 분리**하는 것이다.  
**변하는 부분은 그대로 유지하고 변하지 않는 부분은 어떻게 해결**할 것 인가에 집중하면 된다.  
  
### 값 매개변수화 (Value Parameterization)  
여기서 변하는 부분은 `"Hello java"`, `"Hello Spring"` 같은 문자값(**Value**)이다.  
  
```java
System.out.println("Hello Java");
System.out.println("Hello Spring");
```  
  
문자값(**Value**), 숫자값(**Value**)처럼 구체적인 값을 메서드(함수)안에 두는 것이 아니라, **매개변수**(파라미터)를 통해  
외부에서 전달 받도록 해서, 메서드의 동작을 달리하고, 재사용성을 높이는 방법을 **값 매개변수화(Value Parameterization)** 라 한다.  
  
### 하나의 메서드에서 실행 할 수 있도록 리팩토링 해보자.

```java
package lambda.start;

import lambda.Procedure;
import java.util.Random;

public class Ex1RefMain {

    public static void main(String[] args) {
        Procedure dice = new Dice();
        Procedure print = new Print();

        hello(print);
        hello(dice);
    }

    public static void hello(Procedure procedure) {
        long start = System.nanoTime();

        procedure.run();

        long end = System.nanoTime();
        System.out.println("실행 시간 : " + (end - start) + "\n");
    }

    static class Dice implements Procedure {
        @Override
        public void run() {
            int randomValue = new Random().nextInt(6) + 1;
            System.out.println("주사위 값 = " + randomValue);
        }
    }

    static class Print implements Procedure {
        @Override
        public void run() {
            for (int i = 1; i <= 3; i++) {
                System.out.println("i = " + i);
            }
        }
    }
}

//class Dice implements Procedure {
//
//    @Override
//    public void run() {
//        long start = System.nanoTime();
//
//        Random random = new Random();
//        int randomValue = random.nextInt(6) + 1;
//        System.out.println("주사위 값 = " + randomValue);
//
//        long end = System.nanoTime();
//        System.out.println("실행 시간 : " + (end - start) + "\n");
//    }
//}
//
//class Print implements Procedure {
//    @Override
//    public void run() {
//        long start = System.nanoTime();
//
//        for (int i = 1; i <= 3; i++) {
//            System.out.println(i);
//        }
//
//        long end = System.nanoTime();
//        System.out.println("실행 시간 : " + (end - start) + "\n");
//    }
//}
```

처음에는 생각이 아니질 않아서 컨닝 후 인터페이스를 활용하는 방법을 이해했다.  
그래서 처음에 내가 작성한 코드는 이런 방식이다.

```java
import lambda.Procedure;
import java.util.Random;

public class Ex1myRefMain {

    public static void main(String[] args) {
        new Dice().run();
        new Print().run();
    }
}

class Dice implements Procedure {

    @Override
    public void run() {
        long start = System.nanoTime();

        Random random = new Random();
        int randomValue = random.nextInt(6) + 1;
        System.out.println("주사위 값 = " + randomValue);

        long end = System.nanoTime();
        System.out.println("실행 시간 : " + (end - start) + "\n");
    }
}

class Print implements Procedure {
    @Override
    public void run() {
        long start = System.nanoTime();

        for (int i = 1; i <= 3; i++) {
            System.out.println(i);
        }

        long end = System.nanoTime();
        System.out.println("실행 시간 : " + (end - start) + "\n");
    }
}
```
위에 방법은 인터페이스를 상속해서 run() 메서드 안에서 직접 코드를 작성했을 뿐이지.  
위에 코드처럼 인스턴스를 직접 매개변수로 넘겨 인스턴스로 run() 메서드를 작동하는 것과 달리  
아래와 방식으로 직접 new 인스턴스를 생성후 run() 메서드를 작동했다.  
동작하는 것에는 무리가 없으나 **동작 매개변수화** 방면에서는 다른 점이 있다.  
  
- 문자열, 숫자 같은 값 데이터를 메서드에 전달할 때는 `String`, `int`와 같은 각 데이터에 맞는 값을 전달하면 된다.
- 코드 조각을 메서드에 전달할 때는 인스턴스를 전달하고 해당 인스턴스에 있는 메서드를 호출하면 된다.  
  
## 동작 매개변수화 (Behavior Parameterization)
  
**값 매개변수 화(Value Parameterization)**  
- 문자값(**Value**), 숫자값(**Value**)처럼 구체적인 값을 메서드(함수)안에 두는 것이 아니라, **매개변수**(파라미터)를  
통해 외부에서 전달 받도록 해서, 메서드의 동작을 달리하고, 재사용성을 높이는 방법을 **값 매개변수 화**라 한다.
- 값 매개변수화, 값 파라미터화 등으로 부른다.
  
**동작 매개변수화(Behavior Parameterization)**  
- 코드 조각 (코드의 동작 방법, 로직 **Behavior**)을 메서드 (함수)안에 두는 것이 아니라, **매개변수**(파라미터)를  
통해서 외부에서 전달 받도록 해서, 메서드의 동작을 달리하고, 재사용성을 높이는 방법을 동작 매개변수화라 한다.  
- 동작 매개변수화, 동작 파라미터화, 행동 매개변수화(파라미터화), 행위 파라미터화 등으로 부른다.
  
- **값 매개변수화** : 값(숫자, 문자열)을 바꿔가며 메서드(함수)의 동작을 달리 함
- **동작 매개변수화** : 어떤 동작(로직)을 수행할지를 메서드(함수)에 전달 (인스턴스 참조, 람다)
  
### 익명 클래스 
  
익명 클래스를 사용해서 코드를 개선해보자.

```java
package lambda.start;

import lambda.Procedure;
import java.util.Random;

// 익명 클래스 사용, 변수 제거, 익명 클래스의 참조값을 매개변수(파라미터)에 직접 전달
public class Ex1RefMainV3 {

    public static void main(String[] args) {

        hello(new Procedure() {
            @Override
            public void run() {
                final int randomValue = new Random().nextInt(6) + 1;
                System.out.println("주사위 값 = " + randomValue);
            }
        });

        hello(new Procedure() {
            @Override
            public void run() {
                for (int i = 1; i <= 3; i++) {
                    System.out.println("i = " + i);
                }
            }
        });
    }

    public static void hello(Procedure procedure) {
        long start = System.nanoTime();

        procedure.run();

        long end = System.nanoTime();
        System.out.println("실행 시간 : " + (end - start));
    }

}
```
  
```java
주사위 값 = 5
실행 시간 : 3299083
i = 1
i = 2
i = 3
실행 시간 : 175833
```
메서드 파라미터에 직접 new 객체를 생성해서 익명 클래스를 활용하였다.  
정상적으로 작동한다.  
  
### 익명 클래스를 람다로 변경해보자.

```java
import lambda.Procedure;
import java.util.Random;

// 람다 사용
public class Ex1RefMainV4 {

    public static void main(String[] args) {

        final String text = "\n안녕하세요\n";

        hello(() -> {
            final int randomValue = new Random().nextInt(6) + 1;
            System.out.println("주사위 값 = " + randomValue);
        });

        hello(() -> {
            for (int i = 1; i <= 3; i++) {
                System.out.println("i = " + i);
            }
        });

        hello(() -> System.out.println(text));
    }

    public static void hello(Procedure procedure) {
        long start = System.nanoTime();

        procedure.run();

        long end = System.nanoTime();
        System.out.println("실행 시간 : " + (end - start));
    }

}

```  
  
```java
주사위 값 = 6
실행 시간 : 4311334
i = 1
i = 2
i = 3
실행 시간 : 119541

안녕하세요

실행 시간 : 27166
```

- 메서드 파라마티에 매개변수가 없을 경우 `()`괄호만 작성하고 `->` 문자로 동작할 코드를 전달해주자.  
- 실행 코드가 한 줄 뿐이라면 `{}` 중괄호는 생략이 가능하다.
- 람다를 사용한 코드를 보면 클래스나 인스턴스를 정의하지 않고, 매구 간편하게 코드 블럭을 직접 정의하고, 전달하는 것을 볼 수 있다.
  
**람다는 함수다.** 따라서 람다를 제대로 이해하기 위해서는 먼저 함수에 대해서 알아야 한다.  
함수와 메서드의 차이를 간단하게 알아보자.  
  

## 람다 정의
- 자바 8부터 도입된 람다는 자바에서 함수형 프로그래밍을 지원하기 위한 핵심 기능이다.
  - 함수형 프로그래밍에 대해서는 나중에 뒤에서 설명한다.
- **람다는 익명 함수**이다. 따라서 이름 없는 함수를 표현한다.  
   
```java
package lambda.lambda1;

import lambda.Procedure;

public class InstanceMain {

    public static void main(String[] args) {
        Procedure procedure1 = new Procedure() {
            @Override
            public void run() {
                System.out.println("hello lambda");
            }
        };

        System.out.println("class.class = " + procedure1.getClass());
        // class.class = class lambda.lambda1.InstanceMain$1 -> 익명 클래스는 $ 표시가 있다.
        System.out.println("class.Instance = " + procedure1);
        // class.Instance = lambda.lambda1.InstanceMain$1@452b3a41 익명 클래스의 인스턴스도 $ 표시가 포함되고 뒤에 주소값이 있다.

        Procedure procedure2 = () -> System.out.println("hello lambda");

        System.out.println("lambda.getClass() = " + procedure2.getClass());
        // lambda.getClass() = class lambda.lambda1.InstanceMain$$Lambda/0x0000007001003618 -> 람다는 $$ 표시가 2개 붙는다.
        System.out.println("lambda.instance = " + procedure2);
        // lambda.instance = lambda.lambda1.InstanceMain$$Lambda/0x0000007001003618@f6f4d33 -> 람다의 클래스 참조이다. 0x0000007001003618

    }
}
```

```java
class.class = class lambda.lambda1.InstanceMain$1
class.Instance = lambda.lambda1.InstanceMain$1@452b3a41

procedure2.getClass() = class lambda.lambda1.InstanceMain$$Lambda/0x0000007001003618
procedure2.getClass() = lambda.lambda1.InstanceMain$$Lambda/0x0000007001003618@f6f4d33
```
  
- 익명 클래스의 경우 `$`로 구분하고 뒤에 숫자가 붙는다.
- 람다의 경우 `$$`로 구분하고 뒤에 복잡한 숫자가 붙는다.
- 실행 환경에 따라 결과는 다를 수 있다.  
  
**정리**  
- 람다를 사용하면 익명 클래스 사용의 보일러플레이트 코드를 크게 줄이고, 간결한 코드로 생산성과 가독성을 높일 수 있다.  
- 대부분의 익명 클래스는 람다로 대체할 수 있다.
  - 참고로 람다가 익명 클래스를 완전히 대체할 수 있는 것은 아니다. 람다와 익명 클래스의 차이는 뒤에서 정리한다. 
- 람다를 사용할 때 `new`키워드를 사용하지 않지만, 람다도 익명 클래스처럼 인스턴스가 생성된다.  
- 지금은 람다를 익명 클래스의 구현을 간단히 표현할 수 있는 **문법 설탕**(Syntactic sugar, 코드를 간결하게 만드는 문법적 편의)  
역할 정도로만 생각하자. 람다와 익명 클래스의 차이는 뒤에서 설명한다.  
  
## 함수형 인터페이스 
- **함수형 인터페이스**는 정확히 하나의 추상 메서드를 가지는 인터페이스를 말한다.
- 람다는 추상 메서드가 하나인 **함수형 인터페이스**에만 할당할 수 있다.
- 단일 추상 메서드를 줄여서 **SAM**(Single Abstract Method)이라 한다.
- 참고로 람다는 클래스, 추상 클래스에는 할당할 수 없다. 오직 단일 추상 메서드를 가지는 인터페이스에만 할당할 수 있다.
  
**여러 추상 메서드**
```java
interface NotSamInterface{
    void run();
    void go();
}
```
- 단일 추상 메서드가 아니다. 이 인터페이스는 람다를 할당할 수 없다.
  
**단일 추상 메서드** 
```java
@FunctionalInterface
interface SamInterface{
    void run();
}
```
- 여기에는 `run()` 한 개의 추상 메서드만 선언되어 있다.
- 단일 추상 메서드(SAM)이다. 이 인터페이스는 람다를 할당할 수 있다.
  
```java
package lambda.lambda1;

public class SamMain {

    public static void main(String[] args) {
        samInterface sam = ()  -> {
            System.out.println("sam.interface");
        };

        /*
        컴파일 오류 notSamInterface의 하나의 abstract void (추상 메서드)만 가질 수 있다.
        2개 이상 있을 경우 컴파일 에러 발생
        NotSamInterface notSam = () -> {
            System.out.println("notSam.interface");
        };
        */

        sam.run();
//        notSam.run();
    }
}
```

단일 추상 메서드를 가지는 인터페이스에 `@FunctionalInterface` 선언하면 이후 누가 추상 메서드를 추가할 경우 컴파일 에러가 발생한다.  
```java
java: Unexpected @FunctionalInterface annotation
    lambda.lambda1.SamInterface is not a functional interface
      multiple non-overriding abstract methods found in interface
  lambda.lambda1.SamInterface
```
  
## 람다와 시그니처
람다를 함수형 인터페이스에 할당할 때는 메서드의 형태를 정의하는 요소인 메서드 시그니처가 일치해야 한다.  
메서드 시그니처의 주요 구성 요소는 다음과 같다.  
1. 메서드 이름
2. 매개변수의 수와 타입(순서 포함)
3. 반환 타입
  
### MyFunction 예시
예를 들어 `MyFunction`의 `apply`메서드를 살펴보자.  
  
```java
@FunctionalInterface
public interface MyFunction {
    int apply(int a, int b);
}
```
이 메서드의 시그니처  
- 이름 : `apply`
- 매개 변수 : `int`, `int`
- 반환 타입 : `int`

```java
import lambda.MyFunction;

public static void main(String[] args) {
  MyFunction myFunction = (int a, int b) -> a + b;
  int result = myFunction.apply(1, 2);
  System.out.println("myFunction.apply(1, 2)  = " + result);  // result = 3
}
```
  
람다는 익명 함수이므로 시그니처에서 이름은 제외하고`(int x, int y)`, **매개변수, 반환 타입이 함수형 인터페이스에 선언한 메서드와 맞아야 한다.**  
이 람다는 매개변수로 `int a`, `int b` 그리고 반환 값으로 `a + b`인 `int` 타입을 반환하므로 시그니처가 맞다.  
따라서 람다를 함수형 인터페이스에 할당할 수 있다.  
  
## 람다 전달 

### 람다를 변수에 대입하기
  
```java
package lambda.lambda2;

import lambda.MyFunction;

// 1. 람다를 변수에 대입하기
public class LambdaPassMain1 {

    public static void main(String[] args) {
        MyFunction add = (a, b) -> a + b;
//        MyFunction add = new MyFunction() {
//            @Override
//            public int apply(int a, int b) {
//                return a + b;
//            }
//        };
        MyFunction sub = (a, b) -> a - b;

        System.out.println("add.apply(1, 2) = " + add.apply(1, 2));
        System.out.println("sub.apply(1, 2) = " + sub.apply(1, 2));

        MyFunction cal =  add;
        System.out.println("cal(add).apply(1, 2) = " + cal.apply(1, 2));

        cal = sub;
        System.out.println("cal(sub).apply(1, 2) = " + cal.apply(1, 2));
    }
}
```

```java
add.apply(1, 2) = 3
sub.apply(1, 2) = -1
cal(add).apply(1, 2) = 3
cal(sub).apply(1, 2) = -1
```
함수형 인터페이스로 선언한 변수에 람다를 대입하는 것은 람다 인스턴스의 참조값을 대입하는 것이다.  
이해가 잘 안된다면 익명 클래스의 인스턴스를 생성하고 대입한다고 생각해보자. 참고로 함수형 인터페이스도 인터페이스다.  
  
람다도 인터페이스 (함수형 인터페이스)를 사용하므로, 람다 인터페이스의 참조값을 변수에 전달할 수 있다.  
변수에 참조값을 전달할 수 있으므로 다음과 같이 사용할 수 있다.  
- 매개변수를 통해 메서드(함수)에 람다를 전달할 수 있다. (정확히는 람다 인스턴스의 참조값을 전달)
- 메서드가 람다를 반환할 수 있다. (정확히는 람다 인스턴스의 참조값을 반환)
  

### 람다를 메서드(함수)에 전달하기 
람다는 변수에 전달할 수 있다.  
같은 원리로 람다를 매개변수를 통해 메서드(함수)에 전달할 수 있다.  
  
```java
import lambda.MyFunction;
// 2. 람다를 메서드 함수에 전달하기
public class LambdaPassMain2 {

    public static void main(String[] args) {
        MyFunction add = (a, b) -> a + b;
        MyFunction sub = (a, b) -> a - b;

        System.out.println("변수를 통해 전달");
        calculate(add);
        calculate(sub);

        System.out.println("람다를 직접 전달");
        calculate((a, b) -> a + b);
        calculate((a, b) -> a - b);
    }

    static void calculate(MyFunction function) {
        int a = 1;
        int b = 2;

        System.out.println("계산 시작");
        int result = function.apply(a, b);
        System.out.println("계산 결과 = " + result);
    }
}
```

```java
변수를 통해 전달
계산 시작
계산 결과 = 3
계산 시작
계산 결과 = -1
람다를 직접 전달
계산 시작
계산 결과 = 3
계산 시작
계산 결과 = -1
```

```java
void calculate(MyFunction myFunction);
```
- `calculate()` 메서드의 매개변수는 `MyFunction`함수형 인터페이스이다. 따라서 람다를 전달할 수 있다.

```java
// 람다를 변수에 담은 후에 매개변수에 전달 분석
MyFunction add = (a, b) -> a + b; // 람다 인스턴스 생성
MyFunction add = 001; // 참조값 반환
add =001;

calcuate(add);
calcuate(001);

// 메서드 호출, 매개변수에 참조값 대입
void calculate(MyFunction myFunction = 001);
```
  
- 람다 인스턴스의 참조를 매개변수에 전달하는 것이기 때문에 이해하는데 어려움은 없을 것이다.
- 일반적인 참조를 매개변수에 전달하는 것과 같다.  
  
### 람다 반환
  
```java
package lambda.lambda2;

import lambda.MyFunction;

public class LambdaPassMain3 {

    public static void main(String[] args) {
        MyFunction add = getOperation("add");
        System.out.println("add.apply(1, 2) = " + add.apply(1, 2));

        MyFunction sub = getOperation("sub");
        System.out.println("sub.apply(1, 2 = " + sub.apply(1, 2));

        MyFunction some = getOperation("xxx");
        System.out.println("some.apply(1, 2) = " + some.apply(1, 2));
    }

    // 람다를 반환하는 메서드
    static MyFunction getOperation(String operation) {
        switch (operation) {
            case "add": return (a, b) -> a + b;
            case "sub": return (a, b) -> a - b;
            default: return (a, b) -> 0;
        }
    }
}
```
```java
add.apply(1, 2) = 3
sub.apply(1, 2 = -1
some.apply(1, 2) = 0
```
  
## 함수형 인터페이스

### 기본 함수형 인터페이스  
  
**자바가 제공하는 대표적인 함수형 인터페이스**  
- `Function` : 입력 O, 반환 O
- `Consumer` : 입력 O, 반환 X
- `Supplier` : 입력 X, 반환 O
- `Runnable` : 입력 X, 반환 X
  
|       인터페이스        |     메서드 시그니처     |   입력   |   출력   |    대표 사용 예시     |
|:------------------:|:----------------:|:------:|:------:|:---------------:|
| **Function<T, R>** | \<R> apple(T t)  | 1개 (T) | 1개 (R) | 데이터 변환, 필드 추출 등 |
|  **Consumer<T>**   | void accept(T t) | 1개 (T) |   없음   | 로그 출력, DB 저장 등  |
|  **Supplier<T>**   |     T get()      |   없음   | 1개 (T) |  객체 생성, 값 반환 등  |
|    **Runnable**    |      run()       |   없음   |   없음   | 스레드 실행 (멀티스레드)  |  
  
## 특화 함수형 인터페이스 
  
특화 함수형 인터페이스는 의도를 명확하게 만든 조금 특별한 함수형 인터페이스다.  
- `Predicate` : 입력 O, 반환 `boolean`
  - 조건 검사, 필터링 용도
- `Operator(UnaryOperator, BinaryOperator)` : 입력 O, 반환 O
  - 동일한 타입의 연산 수행, 입력과 같은 타입을 반환하는 연산 용도
  
  
### Predicate
```java
@FunctionalInterface
public interface Predicate<T> {

    boolean test(T t);

    default Predicate<T> and(Predicate<? super T> other) {
        Objects.requireNonNull(other);
        return (t) -> test(t) && other.test(t);
    }

    default Predicate<T> negate() {
        return (t) -> !test(t);
    }

    default Predicate<T> or(Predicate<? super T> other) {
        Objects.requireNonNull(other);
        return (t) -> test(t) || other.test(t);
    }

    static <T> Predicate<T> isEqual(Object targetRef) {
        return (null == targetRef)
                ? Objects::isNull
                : object -> targetRef.equals(object);
    }

    @SuppressWarnings("unchecked")
    static <T> Predicate<T> not(Predicate<? super T> target) {
        Objects.requireNonNull(target);
        return (Predicate<T>)target.negate();
    }
}
```
지금은 추상 메서드 `test()` 대해서만 알아보자.  
  
- Predicate는 수학/논리학에서 "술어"를 의마하며, 참/거짓을 판별하는 명제를 표현한다.
  - 술어: 어떤 대상의 성질이나 관계를 설명하면서 그 설명이 참인지 거짓인지를 판단할 수 있게 해주는 표현
- test는 "시험하다"라는 의미로, 주어진 입력값이 조건을 만족하는지 테스트한다는 의미이다. 그래서 반환값이 `boolean`이다.
  
```java
import java.util.function.Predicate;

public class PredicateMain {
  public static void main(String[] args) {

    Predicate<Integer> predicate1 = new Predicate<Integer>() {
      @Override
      public boolean test(Integer integer) {
        return integer > 2;
      }
    };
    System.out.println("predicate1.test() = " + predicate1.test(1)); // predicate1.test() = false

    Predicate<Integer> predicate2 = n -> n / 2 == 1;
    System.out.println("predicate2.test() = " + predicate2.test(3)); // predicate2.test() = true
  }
}

```
  
### Predicate가 꼭 필요한가?
`Predicate`는 입력이 `T` 반환이 `boolean`이기 때문에 결과적으로 `Function<T, Boolean>`으로 대체할 수 있다.  
그럼에도 불구하고 `Predicate`를 별도로 만드는 이유는 다음과 같다.  
  
```java
Function<Integer, Boolean> f1 = value -> value % 2 == 0;
Predicate<Integer> f1 = value -> value % 2 == 0;
```
`Predicate<T>`는 "입력 값을 받아 `ture/false`로 결과를 판단한다"라는 **의도를 명시적으로 드러내기 위해 정의**된 함수이다.  
물론 "`boolean`을 반환하는 함수"라는 측면에서 보면 `Function<T, Boolean>`으로 충분히 구현할 수 있다.  
하지만 `Predicate<T>`를 별도로 둠으로써 다음돠 같은 이점들을 얻을 수 있다.  
  
- **의미 명확성**
  - `Predicate<T>`를 사용하면 "이 함수는 조건을 검사하거나 필터링 용도로 쓰인다"라는 **의도가 더 분명**해 진다.
  - `Function<T, Boolean>`을 쓰면 "이 함수는 무언가를 계산해 `Boolean`을 반환한다"라고 볼 수 있지만,  
  "조건 검사"라는 목적이 분명히 드러나지 않을 수 있다.  
- **가독성 및 유지보수성**  
  - 여러 사람과 협업하는 프로젝트에서, "조건을 판단하는 함수"는 `Predicate<T>`라는 패턴을 사용하므로써 의미 전달이 명확해진다.  
  
**의도가 가장 중요한 핵심**  
자바가 제공하는 다양한 함수형 인터페이스들을 선택할 때는 단순히 입력값, 반환값만 보고 선택하는게 아니라 해당 함수형 인터페이스가 제공하는  
**의도**가 중요하다. 예를 들어서 조건 검사, 필터링 등을 사용한다면 `Function`이 아니라 `Predicate`를 선택해야 한다.  
그래야 "다른 개발자가 의도를 파악하고 조건 검사등에 사용할 의도가 있구나"하고 코드를 더욱 쉽게 이해할 수 있다.  
  
### Operator  
Operator는 `UnaryOperator`, `BinaryOperator` 2가지 종류가 제공된다.  
  

  
