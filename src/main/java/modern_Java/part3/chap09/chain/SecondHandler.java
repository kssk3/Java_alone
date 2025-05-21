package modern_Java.part3.chap09.chain;

public class SecondHandler implements Handler {

    private Handler nextHandler;

    @Override
    public void setNext(Handler handler) {
        this.nextHandler = handler;
    }

    @Override
    public void handleRequest(Request request) {
        if(request.getType().equals("type2")) {
            System.out.println("Type 2 request handler by SecondHandler " + request.getMessage());
        }else if (nextHandler != null) {
            nextHandler.handleRequest(request);
        }

    }
}
