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
  
  

  
