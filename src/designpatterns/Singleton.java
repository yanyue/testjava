package designpatterns;

public class Singleton {
    private static volatile Singleton instance;
    public static Singleton getInstance() {
        if (instance != null) {
            return instance;
        }

        synchronized (Singleton.class) {
            if (instance != null) {
                return instance;
            }

            instance = new Singleton();
            return instance;
        }
    }
}
