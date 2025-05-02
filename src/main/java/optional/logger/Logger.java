package optional.logger;

import java.util.function.Supplier;

public class Logger {

    private boolean debug = false;

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public void debug(Supplier message) {
        if (debug) {
            System.out.println("[DEBUG] = " + message.get());
        }
    }
}
