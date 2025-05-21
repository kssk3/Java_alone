package modern_Java.part3.chap09.observer;

public class Facebook implements Observer {
    @Override
    public void notify(String tweet) {
        if(tweet != null && tweet.contains("Big News")){
            System.out.println("AWESOME BIG NEWS in Facebook! " + tweet);
        }
    }
}
