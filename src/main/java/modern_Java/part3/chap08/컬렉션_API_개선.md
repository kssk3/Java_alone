## 컬렉션 API 개선

### removeIf 메서드

`removeIf`메서드는 Java 8에서 추가된 Collection 인터페이스의 기본 메서드로, 특정 조건에 맞는 요소들을 효율적으로  
제거할 수 있게 해준다.  
  
```java
boolean removeIf(Predicate<? super E> filter)
```
- 반환 값 : 하나 이상의 요소가 제거되었다면, `true`, 아무것도 제거되지 않았다면 `false`
- 파라미터 : 제거할 요소의 조건을 정의하는 `Predicate` 함수형 인터페이스
- 예외 : 컬렉션이 수정 불가능한 경우 `UnsupportedOperationException` 발생 ex ) 불변 리스트 `List.of()`, `Arrays.asList()`
  
```java
List<Integer> numbers = new ArrayList<>();
numbers.add(1);
numbers.add(2);
numbers.add(3);
numbers.add(4);
numbers.add(5);

System.out.println("원본 리스트: " + numbers); // 원본 리스트: [1, 2, 3, 4, 5]
        
// 짝수 제거
boolean removed = numbers.removeIf(n -> n % 2 == 0);
        
System.out.println("요소 제거 여부: " + removed); // 요소 제거 여부: true
System.out.println("짝수 제거 후: " + numbers);  // 짝수 제거 후: [1, 3, 5]
```
  
**문자열 리스트에서 특정 조건의 문자열 제거하기**  
```java
List<String> fruits = new ArrayList<>();
fruits.add("apple");
fruits.add("banana");
fruits.add("orange");
fruits.add("grape");
fruits.add("avocado");
        
System.out.println("원본 리스트: " + fruits); // 원본 리스트: [apple, banana, orange, grape, avocado]
        
// 'a'로 시작하는 문자열 제거
fruits.removeIf(fruit -> fruit.startsWith("a"));
        
System.out.println("'a'로 시작하는 과일 제거 후: " + fruits); // 'a'로 시작하는 과일 제거 후: [banana, orange, grape]
```

### replaceAll 메서드
`List` 인터페이스의 `replaceAll` 메서드를 이용해 리스트의 각 요소를 새로운 요소로 바꿀 수 있다.  
  
```java
default void replaceAll(UnaryOperator<E> operator) {
    Objects.requireNonNull(operator);
    final ListIterator<E> li = this.listIterator();
    while (li.hasNext()) {
        li.set(operator.apply(li.next()));
    }
}
```

**첫 번째 문자 대문자로 변환후 기존 리스트 출력**
```java
List<String> list = new ArrayList<>();
list.add("apple");
list.add("banana");
list.add("orange");
list.add("kiwi");

System.out.println("Before replaceAll() = " + list);
// Before replaceAll() = [apple, banana, orange, kiwi]
        
list.replaceAll(x -> Character.toUpperCase(x.charAt(0)) + x.substring(1));
        
System.out.println("After replaceAll() = " + list);
// After replaceAll() = [Apple, Banana, Orange, Kiwi]
```
  
## 맵 처리
자바 8에서는 `Map` 인터페이스에 몇 가지 디폴트 메서드를 추가했다. 아래 예제로 코드를 확인해보자.  

### 정렬 메서드
다음 두 개의 새로운 유틸리티를 이용하면 맵의 항목을 값 또는 키를 기준으로 정렬할 수 있다.  
- Entry.comparingByValue
- Entry.comparingByKey

```java
Map<Integer, String> numbers1 = Map.ofEntries(Map.entry(1, "one"), Map.entry(2, "two"),
                Map.entry(3, "three"));

System.out.println("numbers1 = " + numbers1);
//numbers1 = {3=three, 2=two, 1=one}

List<Entry<Integer, String>> result1 = numbers1.entrySet()
        .stream()
        .sorted(Entry.comparingByKey())
        .toList();
System.out.println("result1 = " + result1);
// result1 = [1=one, 2=two, 3=three]

Map<Integer, String> numbers2 = Map.of(2, "two", 1, "one", 3, "three");

System.out.println("numbers2 = " + numbers2);
// numbers2 = {3=three, 2=two, 1=one} // 맵의 값의 순서는 매번 달라진다.

List<Entry<Integer, String>> result2 = numbers2.entrySet()
        .stream()
        .sorted(Entry.comparingByKey())
        .toList();
System.out.println("result2 = " + result2);
// result2 = [1=one, 2=two, 3=three]
```
  
### getOrDefault 메서드
  
기존에는 찾으려는 키가 존재하지 않으면 널이 반환되므로 `NullPointerException`을 방지하려면 요청 결과가  
널인지 확인해야 한다. 기본값을 반환하는 방식으로 이 문제를 해결할 수 있다. `getOrDefault` 메서드를 이용하면  
쉽게 이 문제를 해결할 수 있다. 이 메서드는 첫 번째 인수로 키를, 두 번째 인수로 기본값을 받으며 맵에 키가 존재하지 않으면  
두 번째 인수로 받은 기본값을 반환한다.  
  
```java
Map<Integer, String> numbers = Map.ofEntries(
        Map.entry(1, "One"),
        Map.entry(2, "Two"),
        Map.entry(3, "Three"));
System.out.println("numbers = " + numbers);
// numbers = {1=One, 2=Two, 3=Three}

System.out.println("numbers.getOrDefault(3, \"Three\") = " + numbers.getOrDefault(3, "값이 없습니다."));
// numbers.getOrDefault(3, "Three") = Three
System.out.println("numbers.getOrDefault(4, \"Four\") = " + numbers.getOrDefault(4, "값이 없습니다."));
// numbers.getOrDefault(4, "Four") = 값이 없습니다.
```
  
키가 존재하더라도 값이 널인 상황에서는 `getOrDefault`가 널을 반환할 수 있다는 사실을 주목하자.   
즉 키가 존재하느냐의 여부에 따라서 두 번째 인수가 반환될지 결정된다.  
  
자바 8에서는 키의 값이 존재하는지 여부를 확인할 수 있는지 더 복잡한 몇 개의 패턴도 제공한다.
  
### 계산 패턴
  
맵에 키가 존재하는지 여부에 따라 어떤 동작을 실행하고 결과를 저장해야 하는 상황이 필요한 때가 있다.  
예를 들어 키를 이용해 값비싼 동작을 실행해서 얻은 결과를 캐시하려 한다. 키가 존재하면 결과를 다시 계산할 필요가 없다.  
다음의 세 가지 연산이 이런 상황에서 도움을 준다.  
- `computeIfAbsent` : 제공된 키에 해당하는 값이 없으면 (값이 없거나 널), 키를 이용해 새 값을 계산하고 맵에 추가한다.
- `computeIfPresent` : 제공된 키가 존재하면 새 값을 계산하고 맵에 추가한다.
- `compute` : 제공된 키로 새 값을 계산하고 맵에 저장한다.  
  
정보를 캐시할 때 `computeIfAbsent`를 활용할 수 있다.
  
**computeIfAbsent**
```java
Map<Integer, String> numbers = new HashMap<>();
numbers.put(1, "one");
numbers.put(2, "two");
numbers.put(3, "three");

System.out.println("Before numbers = " + numbers);
// Before numbers = {1=one, 2=two, 3=three}

System.out.println("numbers.computeIfAbsent(4, k -> \"four\") = " + numbers.computeIfAbsent(4, k -> "four"));
// numbers.computeIfAbsent(4, k -> "four") = four
System.out.println("numbers.computeIfAbsent(2, k -> \"two\") = " + numbers.computeIfAbsent(2, k -> "two"));
// numbers.computeIfAbsent(2, k -> "two") = two
        
System.out.println("After numbers = " + numbers);
// After numbers = {1=one, 2=two, 3=three, 4=four}
```

**computeIfPresent**  
```java
Map<String, Integer> numbers = new HashMap<>();
numbers.put("one", 1);
numbers.put("two", 2);
numbers.put("three", 3);

System.out.println("Before numbers = " + numbers);
// Before numbers = {one=1, two=2, three=3}

numbers.computeIfPresent("one", (key, value) -> value + 100);
numbers.computeIfPresent("oone", (key, value) -> value + 100);

System.out.println("After numbers = " + numbers);
// After numbers = {one=101, two=2, three=3}
```
  
`computeIfPresent` 메서드는 현재 키와 관련된 값이 맵에 존재하며 널이 아닐 때만 새 값을 계산한다.  
이 메서드의 미묘한 실행 과정에 주목하자. 값을 만드는 함수가 널을 반환하면 현재 매핑을 맵에서 제거한다.  
하지만 매핑을 제거할 때는 remove 메서드를 오버라이드하는 것이 더 적합하다.  
  
### 개선된 ConcurrentHashMap

`ConcurrentHashMap` 클래스는 동시성 친화적이며 최신 기술을 반영한 HashMap 버전이다.  
`ConcurrentHashMap`은 내부 자료구조의 특정 부분만 잠궈 동시 추가, 갱신 작업을 허용한다.   
따라서 동기화된 Hashtable 버전에 비해 읽기 쓰기 연산 성능이 월등하다. (표준 HashMap은 비동기로 동작한다.)  
  
다음처럼 키에 함수 받기, 값, Map.Entry, (키, 값) 인수를 이용한 네 가지 연산 형태를 지원한다.  
- 키, 값으로 연산 (forEach, reduce, search)
- 키로 연산 (forEachKey, reduceKeys, searchKeys)
- 값으로 연산 (forEachValue, reduceValues, searchValues)
- Map.Entry 객체로 연산 (forEachEntry, reduceEntries, searchEntries)
  
이들 연산은 `ConcurrentHashMap`의 상태를 잠그지 않고 연산을 수행한다는 점을 주목하자.  
따라서 이들 연산에 제동한 함수는 계산이 진행되는 동안 바뀔 수 있는 객체, 값, 순서 등에 의존하지 않아야 한다.  
  
또한 이들 연산에 병렬성 기준값 `(threshold)`을 지정해야 한다.   
맵의 크기가 주어진 기준값보다 작으면 순차적으로 연산을 실행한다.  
기준값을 1로 지정하면 공통 스레드 풀을 이용해 병렬성을 극대화한다.  
`Long.MAX_VALUE`를 기준값으로 설정하면 한 개의 스레드로 연산을 실행한다.  
기준값 규칙을 따르는 것이 좋다. 
  
### 정리
- 자바 9는 바꿀 수 없는 리스트, 집합, 맵을 쉽게 만들 수 있도록 `List.of`, `Set.of`, `Map.ofEntries` 등의   
컬렉션 팩토리를 지원한다.  
- 이들 컬렉션 팩토리가 반환한 객첸는 만들어진 다음 바꿀 수 없다.  
- List 인터페이스는 removeIf, replaceAll, sort 세 가지 디폴트 메서드를 지원한다.
- Set 인터페이스는 removeIf 디폴트 메서드를 지원한다.
- Map 인터페이스는 자주 사용하는 패턴과 버그를 방지할 수 있도록 다양한 디폴트 메서드를 지원한다.
- ConcurrentHashMap은 Map에서 상속받은 새 디폴트 메서드를 지원함과 동시에 스레드 안정성도 제공한다.  
  

  

