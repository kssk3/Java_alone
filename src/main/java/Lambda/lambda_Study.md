# Lambda

```java
package Lambda.start;

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
package Lambda.start;

import Lambda.Procedure;
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
            for(int i = 1; i <= 3; i++){
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
import Lambda.Procedure;
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
package Lambda.start;

import Lambda.Procedure;
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
                for(int i = 1; i <= 3; i++){
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
import Lambda.Procedure;
import java.util.Random;

// 람다 사용
public class Ex1RefMainV4 {

    public static void main(String[] args) {

        final String text = "\n안녕하세요\n";

        hello(() ->  {
            final int randomValue = new Random().nextInt(6) + 1;
            System.out.println("주사위 값 = " + randomValue);
        });

        hello(() -> {
            for(int i = 1; i <= 3; i++){
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
  


  
