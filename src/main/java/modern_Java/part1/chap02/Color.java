package modern_Java.part1.chap02;

public enum Color {
    GREEN("Green"), YELLOW("Yellow"), RED("Red"),;

    private final String color;

    Color(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
