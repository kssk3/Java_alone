package modern_Java.part3.chap09.observer;

public class Twitter implements Observer {
    @Override
    public void notify(String tweet) {
        if(tweet != null && tweet.contains("Money")){
            System.out.println("Breaking news in Twitter! " + tweet);
        }
    }
}
