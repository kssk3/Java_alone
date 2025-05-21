package modern_Java.part3.chap09.observer;

public class LeMonde implements Observer {
    @Override
    public void notify(String tweet) {
        if(tweet != null && tweet.contains("Wine")){
            System.out.println("Today cheese, wine and news! " + tweet);
        }
    }
}
