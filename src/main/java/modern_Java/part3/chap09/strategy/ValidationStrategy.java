package modern_Java.part3.chap09.strategy;

@FunctionalInterface
public interface ValidationStrategy {
    boolean execute(String value);
}
