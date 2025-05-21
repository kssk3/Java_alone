package modern_Java.part3.chap09.strategy;

class IsAllLowerCase implements ValidationStrategy {
    @Override
    public boolean execute(String value) {
        return value.matches("[a-z]+");
    }
}
