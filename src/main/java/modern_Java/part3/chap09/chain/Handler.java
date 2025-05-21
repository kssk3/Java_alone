package modern_Java.part3.chap09.chain;

public interface Handler {
    void setNext(Handler handler);
    void handleRequest(Request request);
}
