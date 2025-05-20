package modern_Java.part3.chap09.Strategy;

class IsNumeric implements ValidationStrategy {
    @Override
    public boolean execute(String value) {
        return value.matches("//d+");
    }
}
