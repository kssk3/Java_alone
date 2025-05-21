package modern_Java.part3.chap09.chain;

public class ThirdHandler implements Handler {

    private Handler nextHandler;

    @Override
    public void setNext(Handler handler) {
        this.nextHandler = handler;
    }

    @Override
    public void handleRequest(Request request) {
        if (request.getType().equals("type3")) {
            System.out.println("Type 3 request handler by ThirdHandler " + request.getMessage());
        } else if (nextHandler != null) {
            nextHandler.handleRequest(request);
        } else {
            System.out.println("Request Not Supported");
        }

    }
}
