package LeetCode;

import java.util.HashMap;
import java.util.Map;

public class LeetCode_1 {

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> numIndexes = new HashMap<>();
        for (int idx = 0, len = nums.length; idx < len; idx++) {
            int gap = target - nums[idx];
            if (numIndexes.containsKey(gap)) {
                return new int[]{numIndexes.get(gap), idx};
            }
            numIndexes.put(nums[idx], idx);
        }
        return new int[2];
    }
}
