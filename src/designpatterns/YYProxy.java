package designpatterns;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;




public class YYProxy {

    /**
     * 表示功能的，厂家，商家都要完成的功能
     * 备注：下面只是定义了一个卖U盘的功能，也可以写其它的功能
     */
    public interface UsbSell {

        //定义方法 参数 amount：表示一次购买的数量，暂时不用
        //返回一个U盘的价格
        float sell(int amout);

    }

    class UsbKingFactory implements UsbSell {
        @Override
        public float sell(int amout) {
            //一个128G的U盘是85元
            return 85.0f;
        }
    }
    /**
     * 必须实现InvocationHandler接口，完成代理类要做的功能（1.调用目标方法 2.功能增强）
     */
    class MysellHandler implements InvocationHandler {

        private Object  target = null;
        //动态代理：目标对象是活动的，不是固定的，需要传入进来。
        //传入是谁，就给谁创建代理
        public MysellHandler(Object target){
            this.target = target;
        }
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            // float price = factory.sell(amout);//厂家的价格

            //商家需要加价，也就是代理需要增加价格
            //增强功能，代理类在完成目标类方法调用后，增强的功能。在目标类的方法调用后，你做的其它功能，都是增强的意思。
            //price = price + 25;
            //返回增加的价格

            //(1)执行目标方法
            Object res = null;
            method.invoke(target, args);//执行目标方法，target你传入的是A，那你的目标方法就是A，你传入的是B，那目标方法就是B
            //(2)进行功能增强
            if (res != null) {
                Float price = (Float) res;
                price = price+25;
                res = price;
            }
            return res;
        }
    }

    interface Hello {
        void morning(String name);
    }

    public void testDynamicProxy() {
        //创建代理对象，使用Proxy
        // 1、创建目标对象
        // UsbKingFactory factory = new UsbKingFactory();
        UsbSell factory = new UsbKingFactory();

        //2、创建InvocationHandler对象
        InvocationHandler handler = new MysellHandler(factory);

        //3、创建代理对象
        UsbSell proxy = (UsbSell) Proxy.newProxyInstance(factory.getClass().getClassLoader(),
                factory.getClass().getInterfaces(),
                handler);

        //4、通过代理执行方法
        float price = proxy.sell(1);
        System.out.println(price);
    }

    public void testDynamicProxy2() {
        InvocationHandler handler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println(method);
                if (method.getName().equals("morning")) {
                    System.out.println("Good morning, " + args[0]);
                }
                return null;
            }
        };
        Hello hello = (Hello) Proxy.newProxyInstance(
                Hello.class.getClassLoader(), // 传入ClassLoader
                new Class[] { Hello.class }, // 传入要实现的接口
                handler); // 传入处理调用方法的InvocationHandler
        hello.morning("Bob");
    }

    public interface HelloInterface {
        void sayHello();
    }

    public class Hello3 implements HelloInterface{
        @Override
        public void sayHello() {
            System.out.println("Hello zhanghao!");
        }
    }

    public class ProxyHandler implements InvocationHandler{
        private Object object;
        public ProxyHandler(Object object){
            this.object = object;
        }
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("Before invoke "  + method.getName());
            method.invoke(object, args);
            System.out.println("After invoke " + method.getName());
            return null;
        }
    }
    public void testDynamicProxy3() {
        System.getProperties().setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        HelloInterface hello = new Hello3();

        InvocationHandler handler = new ProxyHandler(hello);

        HelloInterface proxyHello = (HelloInterface) Proxy.newProxyInstance(hello.getClass().getClassLoader(), hello.getClass().getInterfaces(), handler);

        proxyHello.sayHello();
    }
}
