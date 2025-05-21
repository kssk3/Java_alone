package modern_Java.part3.chap09.observer;

public interface Subject {
    void registerObserver(Observer observer);
    void notifyObservers(String tweet);
}
