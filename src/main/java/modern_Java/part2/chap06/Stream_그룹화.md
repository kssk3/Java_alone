## 그룹화 

데이터 집합을 하나 이상의 특성으로 분류해서 그룹화하는 연산도 데이터베이스에서 많이 수행되는 작업이다.  
그룹화 작업을 명령형으로 그룹화를 구현하려면 까다롭고, 할일이 많으며, 에러도 많이 발생한다.  
하지만 자바 8의 함수형을 이용하면 가독성 있는 한줄의 코드로 그룹화를 구현할 수 있다.  
  
`Collectors.groupingBy`를 이용해서 쉽게 메뉴를 그룹화할 수 있다.  
  
```java
public static <T, K> Collector<T, ?, Map<K, List<T>>>
    groupingBy(Function<? super T, ? extends K> classifier) {
        return groupingBy(classifier, toList());
    }

public static <T, K, A, D> Collector<T, ?, Map<K, D>> groupingBy
        (Function<? super T, ? extends K> classifier, Collector<? super T, A, D> downstream) {
    return groupingBy(classifier, HashMap::new, downstream);
}

public static <T, K, D, A, M extends Map<K, D>>
Collector<T, ?, M> groupingBy(Function<? super T, ? extends K> classifier,
                              Supplier<M> mapFactory,
                              Collector<? super T, A, D> downstream)
```

`groupingBy`메서드에는 3가지 경우로 사용할 수 있다.  
아래 예제 코드를 보고 어떤 결과와 반환 값을 주는지 확인해보자.  
  
```java
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
// 같은 타입끼리 그룹화
Map<Type, List<Dish>> result1 = menu.stream()
        .collect(groupingBy(Dish::getType));
System.out.println("result1 = " + result1);

Map<Type, Long> result2 = menu.stream()
        .collect(groupingBy(Dish::getType, counting()));
System.out.println("result2 = " + result2);

//result1 = {OTHER=[french fries, rice, season fruit, pizza], MEAT=[pork, beef, chicken], FISH=[prawns, salmon]}
//result2 = {OTHER=4, MEAT=3, FISH=2}
```

**그룹화된 요소 조작**
```java
// 조건에 맞지 않는 리스트는 반환하지 않는다.
Map<Type, List<Dish>> result3 = menu.stream()
        .filter(dish -> dish.getCalories() > 500)
        .collect(groupingBy(Dish::getType));
System.out.println("result3 = " + result3);

// 빈 배열도 반환
Map<Type, List<Dish>> result4 = menu.stream()
        .collect(groupingBy(Dish::getType, filtering(dish -> dish.getCalories() > 500, toList())));
System.out.println("result4 = " + result4);

Map<Type, List<String>> result5 = menu.stream()
        .collect(groupingBy(Dish::getType, mapping(Dish::getName, toList())));
System.out.println("result5 = " + result5);

//result3 = {OTHER=[french fries, pizza], MEAT=[pork, beef]}
//result4 = {OTHER=[french fries, pizza], MEAT=[pork, beef], FISH=[]}
//result5 = {OTHER=[french fries, rice, season fruit, pizza], MEAT=[pork, beef, chicken], FISH=[prawns, salmon]}
```

**다수준의 그룹화**
```java
Map<Type, Map<String, List<Integer>>> result6 = menu.stream()
        .collect(groupingBy(Dish::getType, groupingBy(Dish::getName, mapping(Dish::getCalories, toList()))));
System.out.println("result6 = " + result6);

Map<Type, Map<Integer, List<String>>> result7 = menu.stream()
        .collect(groupingBy(Dish::getType, groupingBy(Dish::getCalories, mapping(Dish::getName, toList()))));
System.out.println("result7 = " + result7);

Map<Type, Map<Boolean, List<Dish>>> result8 = menu.stream()
        .collect(groupingBy(Dish::getType, groupingBy(dish -> dish.getCalories() > 500, toList())));
        System.out.println("result8 = " + result8);

Map<Type, Map<CaloricLevel, List<Dish>>> result9 = menu.stream()
        .collect(groupingBy(Dish::getType, groupingBy(dish -> {
            if (dish.getCalories() <= 400) {return CaloricLevel.DIET;}
            else if (dish.getCalories() <= 700) {return CaloricLevel.NORMAL;}
            else {return CaloricLevel.FAT;}
        })));
        System.out.println("result9 = " + result9);

//result6 = {OTHER={season fruit=[120], pizza=[550], rice=[350], french fries=[530]}, MEAT={chicken=[400], beef=[700], pork=[800]}, FISH={salmon=[450], prawns=[300]}}
//result7 = {OTHER={530=[french fries], 550=[pizza], 120=[season fruit], 350=[rice]}, MEAT={400=[chicken], 800=[pork], 700=[beef]}, FISH={450=[salmon], 300=[prawns]}}
//result8 = {OTHER={false=[rice, season fruit], true=[french fries, pizza]}, MEAT={false=[chicken], true=[pork, beef]}, FISH={false=[prawns, salmon]}}
//result9 = {OTHER={DIET=[rice, season fruit], NORMAL=[french fries, pizza]}, MEAT={DIET=[chicken], FAT=[pork], NORMAL=[beef]}, FISH={DIET=[prawns], NORMAL=[salmon]}}
```
  
보통 `groupingBy`의 연상르 '버킷(물건을 담을 수 있는 양동이)' 개념으로 생각하면 쉽다.  
첫 번째 groupingBy는 각 키의 버킷을 만든다. 그리고 준비된 각각의 버킷을 서브스트림 컬렉터로 채워가기를 반복하면서  
n수준 그룹화를 달성한다.  
  
```java
Map<Type, Optional<Dish>> result10 = menu.stream()
        .collect(groupingBy(Dish::getType, maxBy(Comparator.comparingInt(Dish::getCalories))));
System.out.println("result10 = " + result10);
        
// result10 = {OTHER=Optional[pizza], MEAT=Optional[pork], FISH=Optional[salmon]}


Map<Type, Dish> result11 = menu.stream()
        .collect(groupingBy(Dish::getType,
                collectingAndThen(
                        maxBy(Comparator.comparingInt(Dish::getCalories)), Optional::get)));
        System.out.println("result11 = " + result11);

//result11 = {OTHER=pizza, MEAT=pork, FISH=salmon}
```


  

