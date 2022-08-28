package sort;

import java.util.Arrays;

abstract class T {
    abstract void test();
}

public abstract class BaseSort {
//    protected int[] nums = {11, 2, 4, 9, 3, 7};
    protected int[] nums = {2,2,2};
    protected abstract void sortInner();
    public void sort() {
        System.out.println("before sort:" + Arrays.toString(nums));
        System.out.println("[" + getClass().getSimpleName() + "]");
        sortInner();
        System.out.println("after  sort:" + Arrays.toString(nums));
    }
}
