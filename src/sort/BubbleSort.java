package sort;

public class BubbleSort extends BaseSort {

    @Override
    protected void sortInner() {
        for (int i = 0; i < nums.length - 1; ++i) {
            for (int j = 0; j < nums.length - i - 1; ++j) {
                if (nums[j] > nums[j+1]) {
                    int data = nums[j];
                    nums[j]= nums[j+1];
                    nums[j+1] = data;
                }
            }
        }
    }
}
