package sort;

public class QuickSort extends  BaseSort{

    private int partition(int low, int high) {
        int benchmark = nums[low];
        while (low < high) {
            while (low < high && nums[high] >= benchmark) {
                high--;
            }

            nums[low] = nums[high];

            while (low < high && nums[low] <= benchmark) {
                low++;
            }

            nums[high] = nums[low];
        }
        nums[low] = benchmark;
        return low;
    }

    private void sortIndeed(int low, int high) {
        if (low >= high) {
            return;
        }
        int pivot = partition(low, high);
        sortIndeed(low, pivot-1);
        sortIndeed(pivot+1, high);
    }
    @Override
    protected void sortInner() {
//        nums = new int[]{2, 3, 4, 4, 11, 11, 7 , 5};
        sortIndeed(0, nums.length - 1);
    }
}
