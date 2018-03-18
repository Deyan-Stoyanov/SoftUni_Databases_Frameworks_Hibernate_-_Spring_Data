package telefony;

public class Smartphone implements Callable, Browsable {

    public Smartphone() {
    }

    @Override
    public void browse(String s) {
        System.out.printf("Browsing: %s!%n", s);
    }

    @Override
    public void call(String s) {
        System.out.printf("Calling... %s%n", s);
    }
}
