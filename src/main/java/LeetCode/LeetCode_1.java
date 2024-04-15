package LeetCode;

public class LeetCode_1 {

    public int[] twoSum(int[] nums, int target) {
        int len = nums.length;
        for (int left = 0; left < len; left++) {
            for (int right = left + 1; right < len; right++) {
                if (nums[left] + nums[right] == target) {
                    return new int[]{left, right};
                }
            }
        }
        return new int[2];
    }
}
