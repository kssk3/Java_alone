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
    public static void main(String[] args) {
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
    public static void main(String[] args) {

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

## 스트림 활용

## 스트림으로 데이터 수집

## 병령 데이터 처리와 성능
