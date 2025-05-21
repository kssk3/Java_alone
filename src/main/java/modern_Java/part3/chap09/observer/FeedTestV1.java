package modern_Java.part3.chap09.observer;

public class FeedTestV1 {

    public static void main(String[] args) {
        Feed feed = new Feed();
        feed.registerObserver(new Twitter());
        feed.registerObserver(new Facebook());
        feed.registerObserver(new LeMonde());

        feed.notifyObservers("Today Big News");
        feed.notifyObservers("raise exchange rate Money!");

        // AWESOME BIG NEWS in Facebook! Today Big News
        // Breaking news in Twitter! raise exchange rate Money!
    }
}
