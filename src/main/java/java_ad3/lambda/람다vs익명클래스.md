# 람다 vs 익명클래스 

## 1. 문법 차이
- ### 익명 클래스

```java
Button button = new Button();
button.setOnClickListener(new Runnable() {
    @Override
    public void run() {
        System.out.println("Runnable");
    }
});
```
- 익명 클래스는 클래스를 선언하고 즉시 인스턴스를 생성하는 방식이다.
- 반드시 `new 인터페이스명()`형태로 작성해야하며, 메서드를 오버라이드해서 구현한다.
- 익명 클래스도 하나의 클래스다.
  
- ### 람다 표현식

```java
Button button = new Button();
button.setOnClickListener(v -> System.out.println("버튼 클릭"))
```
- 람다 표현식은 함수를 간결하게 표현할 수 있는 방식이다.
- 함수형 인터페이스 (메서드가 하나인 인터페이스)를 간단히 구현할 때 주로 사용한다.
- 람다는 `->`연산자를 사용하여 표현하며, 매개변수와 실행할 내용을 간결하게 작성할 수 있다.
- 람다도 인스턴스가 생성된다.
  
## 2. 코드의 간결함

## 3. 상속 관계
- **익명 클래스**는 일반적인 클래스처럼 다양한 인터페이스와 클래스를 구현하거나 상속할 수 있다.  
즉 여러 메서드를 가진 인터페이스를 구현할 때도 사용할 수 있다.
- **람다 표현식**은 메서드를 딱 하나만 가지는 **함수형 인터페이스**만을 구현할 수 있다.
  - 람다 표현식은 **클래식을 상속**할 수 없다. 오직 함수형 인터페이스만 구현할 수 있으며, **상태(필드, 멤버변수)**나  
    추가적인 메서드 오버라이딩은 불가능하다.
  - 람다는 단순한 함수를 정의하는 것으로, 상태나 추가적인 상속 관계를 필요로 하지 않는 상황에서만 사용할 수 있다.
  
## 4. 호환성 
- **익명 클래스**는 자바의 오래전 버전에서도 사용할 수 있다.
- **람다 표현식**은 자바 8부터 도입되었기 때문에 그 이전 버전에서는 사용할 수 없다.
  
## 5. this 키워드의 의미
- **익명 클래스** 내부에서 `this`는 익명 클래스 자신을 가리킨다. 외부 클래스와 별도의 컨텍스트를 가진다.
- **람다 표현식**에서 `this`는 람다를 선언한 클래스의 인스턴스를 가리킨다. 즉, 람다 표현식은 별도의 컨텍스트를 가지는 것이 아니라  
    람다를 선언한 클래스의 컨텍스트를 유지한다.
  - 람다 내부의 `this`는 **람다가 선언된 외부 클래스의**`this`와 동일하다.

```java
public class OuterMain {
    private String name = "외부클래스";
    public static void Main(String[] args) {
        OuterMain outerMain = new OuterMain();
        System.out.println("[외부 클래스]" + outerMain);
        System.out.println("======================================");
        outerMain.execute();
    }
    private void execute() {
        Runnable anonymous = new Runnable() {
            String name = "익명클래스";
            @Override
            public void run() {
                System.out.println("[익명 클래스] this = " + this);
                System.out.println("[익명 클래스] this.getClass = " + this.getClass());
                System.out.println("[익명 클래스] this.name = " + this.name);
            }
        };
        Runnable lambda = () -> {
            String name = "람다 클래스";
            System.out.println("[람다] this = " + this);
            System.out.println("[람다] this.getClass = " + this.getClass());
            System.out.println("[람다] this.name = " + this.name);
        };
        anonymous.run();
        System.out.println("======================================");
        lambda.run();
    }
}
```
```
[외부 클래스]lambda.lambda6.OuterMain@a09ee92
======================================
[익명 클래스] this = lambda.lambda6.OuterMain$1@4a574795
[익명 클래스] this.getClass = class lambda.lambda6.OuterMain$1
[익명 클래스] this.name = 익명클래스
======================================
[람다] this = lambda.lambda6.OuterMain@a09ee92
[람다] this.getClass = class lambda.lambda6.OuterMain
[람다] this.name = 외부클래스
```

- 람다에서 사용한 `this.getClass()`가 외부 클래스인 `lambda.lambda.OutMain`를 가리킨다.
- 람다에서 사용한 `this`와 외부 클래스의 인스턴스 참조값이 `a09ee92` 같다.
- 익명 클래스는 자신 클래스 `(OuterMain$1)`와 인스턴스 `(4a574795)`가 별도로 존재한다.
  
## 6. 캡쳐링
- **익명 클래스**
  - 익명 클래스는 외부 변수에 접근할 수 있지만, 지역 변수는 반드시 `final`혹은 **사실상 final**인 변수만 캡처할 수 있다.
    - 사실상 final 변수란 말은 String name = "이름"; 으로 설정된 후 name 변수가 변경하는 순간이 없다면 **사실상 final** 변수이다.
- **람다 표현식**
  - 람다도 익명 클래스와 같이 캡쳐링을 지원한다. 지역 변수는 반드시 `final`혹은 **사실상 final**인 변수만 캡쳐할 수 있다.
  
>**용어 - 사실상 final**  
> 
>영어로 effectively final이라 한다. 사실상 `final` 지역 변수는 지역 변수에 `final` 키워드를 사용하지는 않았 지만,  
> 값을 변경하지 않는 지역 변수를 뜻한다. `final` 키워드를 넣지 않았을 뿐이지, 실제로는 `final` 키워드 를 넣은 것 처럼  
> 중간에 값을 변경하지 않은 지역 변수이다. 따라서 사실상 `final` 지역 변수는 `final` 키워드 를 넣어도 동일하게 작동해야 한다.
  
## 7. 생성 방식
- **익명 클래스**  
  - 컴파일 시 실제로 `OuterClass$1.class`와 같은 클래스 파일이 생성된다.
  - 일반적인 클래스와 같은 방식으로 동작한다.
  - 해당 클래스 파일을 JVM에 불러서 사용하는 과정이 필요하다.
- **람다**
  - 컴파일 시점에 별도의 클래스 파일이 생성되지 않는다.
  - 자바를 실행하는 실행 시점에 동적으로 필요한 코드를 처리한다.
  
## 8. 상태 관리
- **익명 클래스**
  - 익명 클래스는 인스턴스 내부에 **상태(필드, 멤버 변수)** 를 가질 수 있다. 예를 들어, 익명 클래스 내부에 멤버 변수를 선언하고  
    해당 변수의 값을 변경하거나 상태를 관리할 수 있다.
  - 이처럼 상태를 필요로 하는 경우, 익명 클래스가 유리하다.
- **람다**
  - 클래스는 그 내부에 **상태(필드, 멤버 변수)** 와 **기능(메서드)** 을 가진다. 반면에 함수는 그 내부에 **상태(필드)** 를 가지지 않고  
    기능만 제공한다.
  - 함수인 람다는 기본적으로 필드(멤버 변수)가 없으므로 스스로 **상태를 유지하지는 않는다.**
  
## 9. 익명 클래스와 람다의 용도 구분
- **익명 클래스**
  - **상태를 유지**하거나 다중 메서드를 구현할 필요가 있는 경우
  - 기존 클래스 또는 인터페이스를 상속하거나 구현할 때
  - 복잡한 인터페이스 구현이 필요할 때

- **람다**
  - **상태를 유지할 필요가 없고**, 간결함이 중요한 경우
  - 단일 메서드만 필요한 간단한 함수형 인터페이스 구현 시
  - 더 나은 성능 또는 간결한 코드가 필요한 경우
