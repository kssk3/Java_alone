package java_ad3.optional.model;

public class Delivery {
    private String status;
    private boolean cancelled;

    public Delivery(String status, boolean cancelled) {
        this.status = status;
        this.cancelled = cancelled;
    }
    public String getStatus() {
        return status;
    }

    public boolean isCancelled() {
        return cancelled;
    }

}
