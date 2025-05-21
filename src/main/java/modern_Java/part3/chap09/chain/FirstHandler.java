package modern_Java.part3.chap09.chain;

public class FirstHandler implements Handler {
    private Handler nextHandler;

    @Override
    public void setNext(Handler handler) {
        this.nextHandler = handler;
    }

    @Override
    public void handleRequest(Request request) {
        if(request.getType().equals("type1")) {
            System.out.println("Type 1 request handler by FirstHandler " + request.getMessage());
        }else if(nextHandler != null) {
            nextHandler.handleRequest(request);
        }
    }
}
