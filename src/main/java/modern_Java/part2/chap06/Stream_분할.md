## 분할

분할은 **분할 함수**라 불리는 `Predicate`를 분류 함수로 사용하는 특수한 그룹화 기능이다.  
분할 함수는 불리언을 반환하므로 맵의 키 형식은 Boolean이다.  
결과적으로 맵은 최대 (참 아니면 거짓) 두 개의 그룹으로 분류된다.  
  
```java
public static <T> Collector<T, ?, Map<Boolean, List<T>>> partitioningBy
        (Predicate<? super T> predicate) {return partitioningBy(predicate, toList());}

public static <T, D, A> Collector<T, ?, Map<Boolean, D>> partitioningBy
        (Predicate<? super T> predicate, Collector<? super T, A, D> downstream)
```
  
```java
Map<Boolean, List<Dish>> result1 = menu.stream()
        .collect(partitioningBy(Dish::isVegetarian));
System.out.println("result1 = " + result1);

Map<Boolean, List<Dish>> result2 = menu.stream()
        .collect(partitioningBy(dish -> dish.getCalories() > 500));
System.out.println("result2 = " + result2);

// result1 = {false=[pork, beef, chicken, prawns, salmon], true=[french fries, rice, season fruit, pizza]}
// result2 = {false=[chicken, rice, season fruit, prawns, salmon], true=[pork, beef, french fries, pizza]}
```

### 분할의 장점 
분할 함수가 반환하는 참, 거짓 두 가지 요소의 스트림 리스트를 모두 유지한다는 것이 분할의 장점이다.  
  
```java
Map<Boolean, Map<Type, List<Dish>>> result3 = menu.stream()
        .collect(partitioningBy(Dish::isVegetarian, groupingBy(Dish::getType)));
System.out.println("result3 = " + result3);
// result3 = {false={FISH=[prawns, salmon], MEAT=[pork, beef, chicken]}, true={OTHER=[french fries, rice, season fruit, pizza]}}

Map<Boolean, Map<String, List<Integer>>> result4 = menu.stream()
        .collect(partitioningBy(Dish::isVegetarian,
                groupingBy(Dish::getName, mapping(Dish::getCalories, toList()))));
System.out.println("result4 = " + result4);
// result4 = {false={chicken=[400], salmon=[450], beef=[700], pork=[800], prawns=[300]}, true={season fruit=[120], pizza=[550], rice=[350], french fries=[530]}}

Map<Boolean, Dish> result5 = menu.stream()
        .collect(partitioningBy(Dish::isVegetarian,
                collectingAndThen(maxBy(Comparator.comparingInt(Dish::getCalories)), Optional::get)));
        System.out.println("result5 = " + result5);
// result5 = {false=pork, true=pizza}
```  
  

