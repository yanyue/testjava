package designpatterns;

import java.util.HashSet;

interface Subject {
    void register(Observer o);
    void unregister(Observer o);
    void clear();

    void notifyDataChanged();
}

interface Observer {
    void onUpdate(Object o);
}

class ConcreteSubject implements Subject {
    HashSet<Observer> listeners = new HashSet<>();
    @Override
    public void register(Observer o) {
        if (listeners.contains(o)) {
            return;
        }

        listeners.add(o);
    }

    @Override
    public void unregister(Observer o) {
        if (listeners.contains(o)) {
            listeners.remove(o);
        }
    }

    @Override
    public void clear() {
        listeners.clear();
    }

    @Override
    public void notifyDataChanged() {
        Object value = 11;
        for (Observer o: listeners) {
            o.onUpdate(value);
        }
    }
}

class ConcreteObserver implements Observer {

    @Override
    public void onUpdate(Object o) {
        System.out.println(o);
    }
}
public class YyObserver {
    public static void test() {
        Observer o1 = new ConcreteObserver();
        Observer o2 = new ConcreteObserver();
        Subject subject = new ConcreteSubject();
        subject.register(o1);
        subject.register(o2);
        subject.notifyDataChanged();
    }
}
