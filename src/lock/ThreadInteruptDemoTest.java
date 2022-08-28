package lock;
public class ThreadInteruptDemoTest {

    /**
     * 测试ReentrantLock响应中断
     * @throws Exception
     */
    public static void exeInteruptTest() throws Exception{
        ThreadInteruptDemo demo=new ThreadInteruptDemo();
        demo.exeInterupt();
    }

    /**
     * 测试Synchronized响应中断
     * @throws Exception
     */
    public static void exeInteruptSynTest() throws Exception{
        ThreadInteruptDemo demo=new ThreadInteruptDemo();
        demo.exeInteruptSyn();
    }

    public static void exeCondition() {
        ThreadInteruptDemo demo=new ThreadInteruptDemo();
        demo.alternateTask();
    }
}
