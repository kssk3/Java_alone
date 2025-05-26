package modern_Java.part3.chap10.dslPattern;

public class TradeBuilder {

    private final MethodChainingOrderBuilder builder;
    private final Trade trade = new Trade();

    protected TradeBuilder(MethodChainingOrderBuilder builder, Type type, int quantity) {
        this.builder = builder;
        trade.setType(type);
        trade.setQuantity(quantity);
    }

    public StockBuilder stock(String symbol) {
        return new StockBuilder(builder, trade, symbol);
    }

}
