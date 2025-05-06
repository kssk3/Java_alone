## FlatMap을 통한 중첩 구조 제거

--- 

**[FlatMap]이란?**  
  
`Map`의 경우 각 요소를 하나의 값으로 변환하지만 `FlatMap`은 각 요소를 Stream(또는 다른 요소)으로 변환한 뒤  
그 결과를 하나의 스트림으로 평탄화 (flatten) 해준다.  
  
`flatMap`은 Function 함수형 인터페이스를 매개 변수로 받고 있다.  
```java
// flatMap 함수  
<R> Stream<R> flatMap(Function<? super P_OUT, ? extends Stream<? extends R>> mapper)
```

### 중첩 리스트 요소를 출력한다고 했을 때 코드를 확인해보자.  
```java
List<List<Integer>> outerList = List.of(
                List.of(1, 2, 3),
                List.of(3, 4, 5),
                List.of(5, 6, 7));
        List<Integer> forResult = new ArrayList<>();

        for (List<Integer> list : outerList) {
            for (Integer i : list) {
                forResult.add(i);}
        }

forResult = [1, 2, 3, 3, 4, 5, 5, 6, 7]
```

### flatMap을 사용한 중첩 리스트  
```java
List<Integer> result2 = outerList.stream()
                .flatMap(list -> list.stream())
                .distinct().
                toList();
        System.out.println("result2 = " + result2);
        
result2 = [1, 2, 3, 4, 5, 6, 7]
```
이 코드에는 중복된 요소도 제거하는 `distinct()` 메서드가 있어 위에 코드 결과랑 다르게 나온다.

flapMap을 사용하면 코드 안에서 변화는 이런 식으로 이해하면 좋다.  
```java
List<List<Integer>> numbers = List.of(List.of(1,2,3), List.of(3,4,5), List,of(7,8,9));

Stream<List<Integer> stream = number.stream();
stream.flapMap(n -> n.stream()); 
stream = Stream(List<Integer> list = {1,2,3}),Stream(List<Integer> list = {3,4,5}),Stream(List<Integer> list = {7,8,9});
이런 코드라고 이해하면 좋다.
이 과정에서 distinct() 중복을 제거하고 toList() <- 불변리스트로 반환하면 result2의 값이 출력된다.
```

```java
[1, 2, 3]    →    Stream<1, 2, 3>    \
[4, 5]       →    Stream<4, 5>        } → Stream<1, 2, 3, 4, 5, 6, 7, 8>
[6, 7, 8]    →    Stream<6, 7, 8>    /
```

```java
List<Stream<Integer>> result1 = outerList.stream()
                .map(list -> list.stream())
                .toList();

        System.out.println("result1 = " + result1);
```
- `flatMap`을 사용하지 않고 `map`을 사용할 경우 아래의 값이 출력된다.
  
```java
result1 = [java.util.stream.ReferencePipeline$Head@23fc625e,
            java.util.stream.ReferencePipeline$Head@3f99bd52,
            java.util.stream.ReferencePipeline$Head@4f023edb]
```
  
그림을 보고 이해하면 좋다.  
  
### Map()  
![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FcWBsve%2FbtqUuMPcQhG%2FEKVs0ZmVB5IwhETi4nN3g1%2Fimg.jpg)  
출처 : https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FcWBsve%2FbtqUuMPcQhG%2FEKVs0ZmVB5IwhETi4nN3g1%2Fimg.jpg  
  
### FlatMap()  
![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FmmDug%2FbtqUwzaL8Zu%2FAd2HK6e1S2B6pfXMNM8WeK%2Fimg.jpg)  
출처 : https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FmmDug%2FbtqUwzaL8Zu%2FAd2HK6e1S2B6pfXMNM8WeK%2Fimg.jpg  
  
**정리**  
- `flatMap`은 **중첩 구조**(컬렉션 안의 컬렉션, 배열 안의 배열 등)를 **일차원으로 펼치는 데** 사용된다.
- 예를 들어, 문자열 리스트들이 들어있는 리스트를 평탄화하고, 하나의 연속된 문자열 리스트로 만들 수 있다.  


