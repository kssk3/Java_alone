# 함수형 데이터 처리

## 스트림 소개
  
### 4.1 스트림이란 무엇인가?
**스트림**은 자바 8 API에 새로 추가된 기능이다.  
스트림을 이용하면 선언형 (즉, 데이터를 처리하는 임시 구현 코드 대신 질의로 표현할 수 있다.)으로 컬렉션 데이터를 처리할 수 있다.  
스트림을 이용하면 멀티스레드 코드를 구현하지 않아도 데이터를 **투명하게** 병렬로 처리할 수 있다.  
우선은 스트림이 어떤 유용한 기능을 제공하는지 확인하자.  

**스트림을 사용하지 않은 리스트 정리**  
```java
public class StreamTestMainV1 {
    public static void Main(String[] args) {
        List<Dish> menu = List.of(
                new Dish("말랑카우", 360),
                new Dish("스파게티", 410),
                new Dish("스테이크", 560),
                new Dish("차돌박이 솥밥", 370));
        System.out.println("menu = " + menu);

        List<Dish> lowCalorieDish = new ArrayList<>();
        for (Dish dish : menu) {
            if (dish.getCalories() < 400) {
                lowCalorieDish.add(dish);
            }
        }

        Collections.sort(lowCalorieDish, new Comparator<Dish>() {
            @Override
            public int compare(Dish o1, Dish o2) {
                return o1.getCalories() - o2.getCalories();
            }
        });
        System.out.println("lowCalorieDish = " + lowCalorieDish);

        List<Dish> lowCaloriesNames = new ArrayList<>();
        for (Dish dish : lowCalorieDish) {
            lowCaloriesNames.add(dish.getName());
        }
        System.out.println("lowCaloriesNames = " + lowCaloriesNames);
    }
}
```
```java
menu = [Dish{name='말랑카우', calories=360}, Dish{name='스파게티', calories=410}, Dish{name='스테이크', calories=560}, Dish{name='차돌박이 솥밥', calories=370}]
lowCalorieDish = [Dish{name='말랑카우', calories=360}, Dish{name='차돌박이 솥밥', calories=370}]
lowCaloriesNames = [Dish{name='말랑카우', calories=360}, Dish{name='차돌박이 솥밥', calories=370}]
```
  
**스트림을 활용한 리스트**  
```java

public class StreamTestMainV2 {
    public static void Main(String[] args) {

        List<Dish> menu = List.of(
                new Dish("말랑카우", 360),
                new Dish("스파게티", 410),
                new Dish("스테이크", 560),
                new Dish("차돌박이 솥밥", 370));

        System.out.println("menu = " + menu);
        System.out.println();

        List<Dish> lowCaloriesMenus = menu.stream()
                .filter(n -> n.getCalories() < 400)
                .sorted(Comparator.comparing(Dish::getCalories))
                .map(Dish::getName)
                .toList();
        System.out.println("lowCaloriesMenus = " + lowCaloriesMenus);
    }
}
```  
```java
menu = [Dish{name='말랑카우', calories=360}, Dish{name='스파게티', calories=410}, Dish{name='스테이크', calories=560}, Dish{name='차돌박이 솥밥', calories=370}]
lowCaloriesMenus = [Dish{name='말랑카우', calories=360}, Dish{name='차돌박이 솥밥', calories=370}]
```

- 선언형 으로 코드를 구현할 수 있다. 즉 루프와 if 조건문 등의 제어 블록을 사용해서 어떻게 동작을 구현할지 지정할 필요 없이  
"저칼로리의 요리만 선택하라" 같은 동작의 수행을 지정할 수 있다. 
- `filter`, `sorted`, `map`, `toList()` 같은 여러 빌딩 블록 연산을 연결해서 복잡한 데이터 처리 파이프라인을 만들 수 있다.  
여러 연산을 파이프라인으로 연결해도 여전히 가독성과 명확성이 유지된다.  
  
### 4.2 스트림 시작하기
  
간단한 스트림 작업인 컬렉션 스트림부터 살펴보자. 자바 8 컬렉션에는 스트림을 반환하는 stream 메서드가 추가됐다.  

스트림이란 적확히 뭘까? 스트림이란 **데이터 처리 연산을 지원하도록 소스에서 추출된 연속된 요소**로 정의할 수 있다.  
  
스트림에는 두 가지 중요 특징이 있다.  
- **파이프라이닝** : 대부분의 스트림 연산은 스트림 연산끼리 연결해서 커다란 파이프라인을 구성할 수 있도록 스트림 자신을 반환한다.  
그 덕분에 **게으름(Lazy)**, **쇼트서킷** 같은 최적화도 얻을 수 있다. 연산 파이프라인은 데이터 소스에 적용하는 데이터베이스 질의와 비슷하다.  
- **내부 반복** : 반복자를 이용해서 명시적으로 반복하는 컬렉션과 달리 스틂은 내부 반복을 지원한다.  
  
```java

public class DIshMainEx1 {
    public static void Main(String[] args) {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Type.MEAT),
                new Dish("beef", false, 700, Type.MEAT),
                new Dish("chicken", false, 400, Type.MEAT),
                new Dish("french fries", true, 530, Type.OTHER),
                new Dish("rice", true, 350, Type.OTHER),
                new Dish("season fruit", true, 120, Type.OTHER),
                new Dish("pizza", true, 550, Type.OTHER),
                new Dish("prawns", false, 300, Type.FISH),
                new Dish("salmon", false, 450, Type.FISH)
        );

        List<String> threeHighCaloricDishNames = menu.stream() // 메뉴(요리 리스트)에서 스트림을 얻는다.
                .filter(d -> d.getCalories() > 300) // 고칼로리 요리를 필터링한다.
                .map(Dish::getName)// 요리명 추출
                .limit(3)// 선착순 세 개만 선택
                .toList();// 결과를 다른 리스트로 저장

        System.out.println("threeHighCaloricDishNames = " + threeHighCaloricDishNames);
    }
}

// threeHighCaloricDishNames = [pork, beef, chicken]
```
  
### 4.3 스트림과 컬렉션
  
자바의 기존 컬렉션과 새로운 스트림 모두 연속된 요소 형식의 값을 저장하는 자료구조의 인터페이스를 제공한다.  
여기서 **연속된**이라는 표현은 순서와 상관없이 아무 값에나 접속하는 것이 아니라 순차적으로 접근하다는 것을 의미한다.  
이제 컬렉션과 스트림의 차이를 살펴보자.  
  
- 컬렉션과 스트림의 차이
- **컬렉션**은 현재 자료구조가 포함하는 **모든** 값을 메모리에 저장하는 자료구조다. 즉 컬렉션의 모든 요소는 컬렉션에 추가하기 전에 계산되어야 한다.  
(컬렉션에 요소를 추가하거나 컬렉션의 요소를 삭제할 수 있다.) 이런 연산을 수행할 때마다 컬렉션의 모든 요소를 메모리에 저장해야 하며  
컬렉션에 추가하려는 요소는 미리 계산되어야 한다.
- **스트림**은 이론적으로 **요청할 때만 요소를 계산**하는 고정된 자료구조다. (스트림에 요소를 추가하거나 스트림에서 요소를 제거할 수 없다.)  
이러한 스트림의 특성은 프로그래밍에 큰 도움을 준다. 사용자가 요청하는 값만 스트림에서 추출한다는 것이 핵심이다.  
물론 사용자 입장에서 이러한 변화를 알 수 없다. 결과적으로 스트림은 생산자와 소비자 관계를 형성한다.  
또한 스트림은 게으르게 만들어지는 컬렉션과 같다. 즉, 사용자가 데이터를 요청할 때만 값을 계산한다.  
이 과정을 **중간 계산**, **종료 계산**이라고 한다.  
  
### 4.3.1 딱 한 번만 탐색할 수 있다.  
  
반복자와 마찬가지로 스트림도 한 번만 탐색할 수 있다. 즉, 탐색된 스트림의 요소는 소비된다. 반복자와 마찬가지로 한 번 탐색한 요소를  
다시 탐색하려면 초기 데이터 소스에서 새로운 스트림을 만들어야 한다. (그러려면 컬렉션처럼 반복 사용할 수 있는 데이터 소스여야 한다.  
만일 데이터 소스가 I/O 채널이라면 소스를 반복 사용할 수 없으므로 새로운 스트림을 만들 수 없다.)  
  
```java
public class StreamTestMainV1 {
    public static void Main(String[] args) {

        List<String> title = List.of("java8", "In", "Action");
        title.stream()
                .forEach(System.out::println)
                .forEach(System.out::println); // 스트림이 이미 소비되었거나 닫힘
    }
}
// java: void cannot be dereferenced
```

스트림은 단 한 번만 소비할 수 있다는 점을 명심하자.  
  
컬렉션과 스트림의 또 다른 차이점은 데이터 반복 처리 방법이다.  

### 정리

- 스트림은 소스에서 추출된 연속 요소로 데이터 처리 연산을 지원한다.
- 스트림은 내부 반복을 지원한다. 내부 반복은 `filter`, `map`, `sorted`등의 연산으로 반복을 추상화한다.  
- 스트림에는 중간 연산과 최종 연산이 있다.
- 중간 연산은 `filter`와 `map`처럼 스트림을 반환하면서 다른 연산과 연결되는 연산이다. 중간 연산을 이용해서 파이프라인을 구성할 수 있지만  
중간 연산으로는 어떤 결과도 생성할 수 없다.
- `forEach`나 `count`처럼 스트림 파이르라인을 처리해서 스트림이 아닌 결과를 반환하는 연산을 최종 연산이라고 한다.  
- 스트림의 요소는 요청할 때 게으르게 계산된다.  

## 스트림 활용

## 스트림으로 데이터 수집

## 병령 데이터 처리와 성능
