## 7.3 Spliterator   
  
자바 8은 `Spliterator`라는 새로운 인터페이스를 제공한다. Spliterator는 '분할할 수 있느 반복자' 라는 의미다.  
`Iterator`처럼 `Spliterator`는 소스의 요소 탐색 기능을 제공한다는 점은 같지만 `Spliterator`는 병렬 작업에 특화되어 있다.  
  
```java
public interface Spliterator<T>{
    boolean tryAdvance(Consumer<? super T> action);
    Spliterator<T> trySplit();
    long estimateSize();
    int characteristics();
}
``` 

여기서 T는 `Spliterator`에서 탐색하는 요소의 형식을 가리킨다. tryAdvance 메서드는 Spliterator의 요소를 하나씩 순차적으로  
소비하면서 탐색해야 할 요소가 남아있으면 참을 반환한다.(즉, 일반적인 iterator 동작과 같다.) 반면 trySplit 메서드는  
Spliterator의 일부 요소(자신이 반환한 요소)를 분할해서 두 번째 Spliterator를 생성하는 메서드다.  
`Splieterator`에서는 estimateSize 메서드로 탐색해야 할 요소 수 정보를 제공할 수 있다.  
특히 탐색해야 할 요소 수가 정확하진 않더라도 제공된 값을 이용해서 더 쉽고 공평하게 Spliterator를 분할할 수 있다.  
이 기능을 더 효과적으로 이용하려면 분할 과정이 어떻게 진행되는지 이해하는 것이 좋다.  
  
- 참고 사이트
  - https://docs.oracle.com/javase/8/docs/api/java/util/Spliterator.html  
  - https://www.geeksforgeeks.org/java-util-interface-spliterator-java8/  
  
```java
String text = "test index 123456789";
```

- `tryAdvance`  
  - tryAdvance 메서드는 문자열에서 현재 인덱스에 해당하는 문자를 Consumer에 제공한 다음에 인덱스를 증가시킨다.  
  인수로 전달된 Consumer는 스트림을 탐색하면서 적용해야 하는 함수 집합이 작업을 처리할 수 있도록 소비한 문자를 전달하는  
  자바 내부 클래스다. 스트림을 탐색하면서 전체 문자열 길이보다 작으면 참을 반환하며 이는 반복 탐색해야 할 문자가  
  남아 있음을 의미한다.  
- `trySplit`
  - trySplit 메서드는 반복될 자료구조를 분할하는 로직을 포함하므로 `Spliterator`에서 가장 중요한 메서드다.  
  우선 분할 동작을 중단할 한계를 설정해야 한다. 분활 과정에서 남은 문자 수가 한계값 이하면 Null을 반환,  
  즉 분할을 중지하도록 지시한다. 이때 단어 중간을 분할하지 않도록 빈 문자가 나올때까지 분할 위치를 이동시킨다.  
  분할할 위치를 찾았으면 새로운 `Spliterator`를 만든다. 새로 만든 `Spliterator`는 현재 위치부터  
  분할된 위치까지의 문자를 탐색해야 한다.

  

