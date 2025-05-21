package modern_Java.part3.chap09.Factory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ProductFactory {

    final static Map<String, Supplier<Product>> map = new HashMap<>();

    static {
        map.put("loan", Loan::new);
        map.put("stock", Stock::new);
        map.put("bond", Bond::new);
    }

    public static Product createProduct(String name) {
        Supplier<Product> supplier = map.get(name);
        if(supplier != null) {return supplier.get();}
        throw new IllegalArgumentException("No such product: " + name);
    }

    public static void main(String[] args) {
        Supplier<Product> loanSupplier = Loan::new;
        Product result1 = loanSupplier.get();
        System.out.println("result1.getClass() = " + result1.getClass()); // result1.getClass() = class modern_Java.part3.chap09.Factory.Loan

        Product result2 = createProduct("stock");
        System.out.println("result2.getClass() = " + result2.getClass()); // result2.getClass() = class modern_Java.part3.chap09.Factory.Stock
    }

//    public static Product createProduct(String name) {
//        return switch (name) {
//            case "loan" -> new Loan();
//            case "stock" -> new Stock();
//            case "bond" -> new Bond();
//            default -> throw new RuntimeException(" No such product " + name);
//        };
//    }
}
