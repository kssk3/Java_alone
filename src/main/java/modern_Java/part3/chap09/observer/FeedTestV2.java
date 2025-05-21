package modern_Java.part3.chap09.observer;

public class FeedTestV2 {
    public static void main(String[] args) {
        Feed feed = new Feed();

        feed.registerObserver(text -> {
            if(text != null && text.contains("Money")) {
                System.out.println("AWESOME BIG NEWS in Facebook! " + text);
            }
        });

        feed.registerObserver(text -> {
            if(text != null && text.contains("Wine")) {
                System.out.println("Introduce Today New Wine " + text);
            }
        });

        feed.notifyObservers("Show me the Money!!");
        feed.notifyObservers("De Monde Wine");

        // AWESOME BIG NEWS in Facebook! Show me the Money!!
        // Introduce Today New Wine De Monde Wine
    }
}
