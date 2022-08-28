package sort;

public class InsertSort extends BaseSort {
    @Override
    protected void sortInner() {
        for (int i = 0; i < nums.length; ++i) {
            int min = i;
            for (int j = i+1;j < nums.length; ++j) {
                if (nums[min] > nums[j]) {
                    min = j;
                }
            }

            if (min != i) {
                int data = nums[i];
                nums[i] = nums[min];
                nums[min] = data;
            }
        }
    }
}
