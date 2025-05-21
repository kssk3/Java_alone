package modern_Java.part3.chap09.chain;

public class ChainHandlerTestV1 {

    public static void main(String[] args) {
        Handler firsthandler = new FirstHandler();
        Handler secondHandler = new SecondHandler();
        Handler thirdHandler = new ThirdHandler();

        firsthandler.setNext(secondHandler);
        secondHandler.setNext(thirdHandler);

        Request request1 = new Request("type1", "Request 1");
        Request request2 = new Request("type2", "Request 2");
        Request request3 = new Request("type3", "Request 3");
        Request request4 = new Request("type4", "Request 4");

        firsthandler.handleRequest(request1);// Type 1 request handler by FirstHandler Request 1
        firsthandler.handleRequest(request2);// Type 2 request handler by SecondHandler Request 2
        firsthandler.handleRequest(request3);// Type 3 request handler by ThirdHandler Request 3
        firsthandler.handleRequest(request4);// Request Not Supported
    }
}
