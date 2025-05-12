package modern_Java.part2.chap05.Ex1;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class MainTestV1 {

    public static void main(String[] args) {

        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milano");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = List.of(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        List<Transaction> result1 = transactions.stream()
                .filter(n -> n.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .toList();

        System.out.println("result1 = " + result1);
        // result1 = [Transaction{trader=Trader{name='Brian', city='Cambridge'}, year=2011, value=300}, Transaction{trader=Trader{name='Raoul', city='Cambridge'}, year=2011, value=400}]
        System.out.println();

        List<String> result2 = transactions.stream()
                .map(n -> n.getTrader().getCity())
                .distinct()
                .toList();
        System.out.println("result2 = " + result2);
        // result2 = [Cambridge, Milan]
        System.out.println();

        List<Trader> result3 = transactions.stream()
                .map(Transaction::getTrader)
                .filter(t -> t.getCity().equals("Cambridge"))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .toList();

        System.out.println("result3 = " + result3);
        // result3 = [Trader{name='Alan', city='Cambridge'}, Trader{name='Brian', city='Cambridge'}, Trader{name='Raoul', city='Cambridge'}]
        System.out.println();

        List<Trader> result4 = transactions.stream()
                .map(Transaction::getTrader)
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .toList();

        System.out.println("result4 = " + result4);
        // result4 = [Trader{name='Alan', city='Cambridge'}, Trader{name='Brian', city='Cambridge'}, Trader{name='Mario', city='Milan'}, Trader{name='Raoul', city='Cambridge'}]
        System.out.println();

//        List<String> result5 = transactions.stream()
//                .map(Transaction::getTrader)
//                .filter(n -> n.getCity().equals("Milan"))
//                .map(Trader::getName)
//                .distinct()
//                .toList();
//        System.out.println("result5 = " + result5);

        boolean result5 = transactions.stream()
                .anyMatch(f -> f.getTrader().getCity().equals("Milano"));
        System.out.println("result5 = " + result5);
        // result5 = true
        System.out.println();

        Optional<Integer> result6 = transactions.stream()
                .filter(n -> n.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .reduce(Integer::sum);

        System.out.println("result6 = " + result6);
        // result6 = Optional[2650]
        System.out.println();

        Optional<Integer> result7 = transactions.stream()
                .map(Transaction::getValue)
                .max(Integer::compareTo);
        // .ifPresent(System.out::println);

        System.out.println("result7 = " + result7);
        // result7 = Optional[1000]
        System.out.println();

        Optional<Integer> result8 = transactions.stream()
                .map(Transaction::getValue)
                .min(Integer::compareTo);
        // .ifPresent(System.out::println);

        System.out.println("result8 = " + result8);
        // result8 = Optional[300]



    }
}
