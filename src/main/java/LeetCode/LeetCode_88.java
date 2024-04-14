package LeetCode;

public class LeetCode_88 {

    public static void main(String[] args) {
        merge(new int[]{1, 2, 3, 0, 0, 0}, 3, new int[]{2, 5, 6}, 3);
        merge(new int[]{4, 5, 6, 0, 0, 0}, 3, new int[]{1, 2, 3}, 3);
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        if (m == 0) {
            for (int idx = 0; idx < n; idx++) {
                nums1[idx] = nums2[idx];
            }
            return;
        }
        if (n == 0) {
            return;
        }
        for (int idx = n - 1; idx >= 0; idx--) {
            int insert = nums2[idx];
            if (nums1[m - 1] <= insert) {
                nums1[m] = insert;
            } else {
                int mid = findInsertIdx(nums1, m, insert);
                if (mid == -1) {
                    mid++;
                }
                for (int i = m; i > mid; i--) {
                    nums1[i] = nums1[i - 1];
                }
                nums1[mid] = insert;
            }
            m++;
        }
    }

    private static int findInsertIdx(int[] nums1, int m, int insert) {
        int start = 0;
        int end = m - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (nums1[mid] < insert) {
                start = mid + 1;
            } else if (nums1[mid] >= insert) {
                end = mid - 1;
            }
        }
        return start;
    }
}
