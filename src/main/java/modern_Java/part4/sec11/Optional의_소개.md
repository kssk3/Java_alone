## Optional 클래스 소개
  
`Optional`은 선택형값을 캡슐화하는 클래스다. 값이 있으면 Optional 클래스는 값을 감싼다.  
반면 값이 없으면 Optional.empty() 메서드로 Optional을 반환한다.   
Optional.empty는 Optional의 특별한 싱글턴 인스턴스를 반환하는 정적 패토리 메서드다.  
  
null 대신 Optional을 사용하면서 Car 형식이 Optional<Car>로 봐꾸었다. 이는 값이 없을 수 있음을 명시적으로 보여준다.  
반면 Car 형식을 사용했을 때는 Car에 null 참조가 할당될 수 있는데 이것이 올바른 값인지 아니면  
잘못된 값인지 판단할 아무 정보도 없다.  
  
```java
public class Person {
    private Optional<Car> car;
    public Optional<Car> getCar() {return car;}
}

public class Car {
    private Optional<Insurance> insurance;
    public Optional<Insurance> getInsurance() {return insurance;}
}

public class Insurance {
    private String name;
    public String getName() {return name;}
}
```
  
Optional을 이용하면 값이 없는 상황이 우리 데이터에 문제가 있는 것인지 아니면 알고리즘의 버그인지 명확하게 구분할 수 있다.  
모든 null 참조는 Optional로 대치하는 것은 바람직하지 않다. Optional의 역할은 더 이해하기 쉬운 API를 설계하도록 돕는 것이다.  
즉 메서드의 시그니처만 보고도 선택형값인지 여부를 구별할 수 있다. Optional이 등장하면 이를 언랩해서 값이 없을 수 있는 상황에  
적절하게 대응하도록 강제하는 효과가 있다.  
  
```java
Person person = new Person();

//        Optional<Person> optValue = Optional.of(person);
//        String result1 = optValue.map(Person::getCar) 
//                .map(Car::getInsurance)   -- > Car 객체가 없을 경우 Optional<Car>를 반환하므로 컴파일 오류
//                .map(Insurance::getName)  -- > Insuracne 객체가 없을 경우 Optional<Insurance>를 반환하므로 컴파일 오류
//                .toString();

Optional<Person> optValue = Optional.of(person);
String result2 = optValue.flatMap(Person::getCar)
        .flatMap(Car::getInsurance)
        .map(Insurance::getName)
        .orElse("Unknown");

System.out.println("result2 = " + result2);
// Exception in thread "main" java.lang.NullPointerException
```  
  
일반 맵으로 변환을 할 경우 위에 주석처리와 같이 Optional<> 객체를 반환하므로 우리가 원하는 변환값을 얻지 못한다.  
그래서 `flatMap`을 메서드를 사용하여 값을 변환한다. `flatMap`은 함수를 인수로 받아서 다른 스트림을 반환하는 메서드다.  
보통 인수로 받은 함수를 스트림의 각 요소에 적용하면 스트림의 스트림이 만들어진다. 하지만 flatMap은 인수로 받은 함수를  
적용해서 생성된 각각의 스트림에서 콘텐츠만 남긴다. 즉, 함수를 적용해서 생성된 모든 스트림이 하나의 스트림으로 병합되어 평준화된다.  
우리도 이차원 Optional을 일차원 Optional로 평준화해야 한다.  
  
Optional을 사용하므로 도메인 모델과 관련한 암묵적인 지식에 의존하지 않고 명시적으로 형식 시스템을 정의할 수 있었다.  
정확한 정보 전달은 언어의 가장 큰 목표 중 하나다.(물론 프로그래밍 언어도 예외는 아니다.) Optional을 인수로 받거나  
Optional을 반환하는 메서드를 정의한다면 결과적으로 이 메서드를 사용하는 모든 사람에게 이 메서드가 빈 값을 받거나 빈 결과를  
반환할 수 있음을 잘 문서화해서 제공하는 것과 같다.  
  
### 도메인 모델에 Optional을 사용했을 때 데이터를 직렬화할 수 없는 이유  
Optional의 용도는 선택형 반환값을 지원하는 용도로 만들어졌다.  
  
Optional 클래스는 필드 형식으로 사용할 것을 가정하지 않았으므로 `Serializable`인터페이스를 구현하지 않는다.  
따라서 우리 도메인 모델에 Optional을 사용한다면 직렬화 모델을 사용하는 도구나 프레임워크에서 문제가 생길 수 있다.  
이와 같은 단점에도 불구하고 여전히 Optional을 사용해서 도메인 모델을 구성하는 것이 바람직하다고 생각한다.  
직렬화 모델이 필요하다면 다음 예제에서 보여주는 것처럼 Optional로 값을 반환받을 수 있는 메서드를 추가하는 방식을 권장한다.

```java

public class Person {
    private Car car;
    public Optional<Car> getCarAsOptional() {
        return Optional.ofNullable(car);
    }
}
```  
