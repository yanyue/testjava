import advancedjava.TwoThread;
import designpatterns.YYProxy;
import designpatterns.YyObserver;
import dynamicprogram.FragJump;
import dynamicprogram.UniquePath;
import generic.MyGeneric;
import leetcode.FloatValueGet;
import leetcode.IpAddressValidate;
import leetcode.RevertNode;
import lock.ThreadInteruptDemoTest;
import sort.BaseSort;
import sort.BubbleSort;
import sort.InsertSort;
import sort.QuickSort;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Main {

    public static void testSort() {
//        BaseSort bs = new QuickSort();
//        BaseSort bs = new BubbleSort();
//        BaseSort bs = new InsertSort();
        BaseSort bs = new QuickSort();
        bs.sort();
    }

    public static void testDynamic() {
        final int count = 10;
        System.out.print("青蛙达到" + count + "级的方案：" + FragJump.resolve(count) + "\n");

        final int m = 7;
        final int n = 3;
        System.out.print(m + "*" + n + "的方案：" + UniquePath.resolve(m, n));
    }

    public static void normalTest() {
        Test.test();
    }

    public static void designPatternsTest() {
        YyObserver.test();
    }

    public static  void leetCodeTest() {
        IpAddressValidate.test();
    }

    public static void lockTesk() {
        try {
//            ThreadInteruptDemoTest.exeInteruptTest();
//            ThreadInteruptDemoTest.exeInteruptSynTest();
            ThreadInteruptDemoTest.exeCondition();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void genericTest() {
        MyGeneric.test();
    }

    public static void testDesignPattern() {
        new YYProxy().testDynamicProxy();
    }

    public static void testStringToValue() {
        String s = "-10.7531";
        System.out.println(s + " = " + FloatValueGet.atof(s));
    }
    public static void main(String[] args) {

//        testDynamic();
//        YyObserver.test();
//        normalTest();
//        leetCodeTest();
//        lockTesk();
//        Test.test();
//        genericTest();
//        lockTesk();
//        testDesignPattern();
//        RevertNode.test();
        Map<String, Integer> map = new HashMap<>();
        new TwoThread().test();
//        testStringToValue();
//        testSort();
    }
}