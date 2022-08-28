package generic;

import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

class Generic<T> {
    public T data;
}

class Generic2<T extends Number> {
    public T data;
}

class Generic3 {
    public <T extends  Number> T getData(T t) {
        return t;
    }
}

class Fruit {

}
class Apple extends Fruit {

}
class Banana extends Fruit {

}

class Tool<T> {
    T obj;
}

class ArrayMaker<T> {
    private Class<T> kind;
    public ArrayMaker(Class<T> k) {
        kind = k;
    }

    public T[] create(int size) {
        return (T[]) Array.newInstance(kind, size);
    }
}

public class MyGeneric {
    public static void test() {
        Generic a = new Generic<Integer>();
        System.out.println("MyFanxing, a=" + a.data);

        Generic2 a2 = new Generic2<Integer>();
        System.out.println("MyFanxing, a2=" + a2.data);

        Generic3 a3 = new Generic3();
        System.out.println("MyFanxing, a3=" + a3.getData(11));

        /**
         * 这里会add失败
         */
//        List<? extends Fruit> list = new ArrayList<>();
//        list.add(new Apple());
//        list.add(new Banana());

        /**
         * add 会成功，原来是super保证了下限为Fruit
         */
//        List<? super Fruit> list = new ArrayList<>();
//        list.add(new Apple());
//        list.add(new Banana());

        List<String> la = new ArrayList<String>(){};
        Type type = la.getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = ParameterizedType.class.cast(type);
        for (Type typeArgument : parameterizedType.getActualTypeArguments()) {
            System.out.println(typeArgument.getTypeName());
        }

        Tool<Integer> tool = new Tool<>();

        ArrayMaker<String> stringArrayMaker = new ArrayMaker<>(String.class);
        String[] arrayString = stringArrayMaker.create(10);
//        System.out.println("array string:" + Arrays.toString(arrayString));
    }
}
