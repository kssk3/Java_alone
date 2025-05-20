package modern_Java.part3.chap09.Strategy;

@FunctionalInterface
public interface ValidationStrategy {
    boolean execute(String value);
}
