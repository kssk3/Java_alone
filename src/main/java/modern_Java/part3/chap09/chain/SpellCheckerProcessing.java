package modern_Java.part3.chap09.chain;

public class SpellCheckerProcessing extends ProcessingObject<String> {
    @Override
    protected String handleWork(String text) {
        return text.replace("labda", "lambda");
    }
}
