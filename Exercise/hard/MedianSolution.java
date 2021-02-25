package hard;

import java.util.Arrays;

/**
 * 给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。找出并返回这两个正序数组的中位数。
 * @author hao.nan 2021/2/25
 */
public class MedianSolution {

    public static void main(String[] args) {
        int[] nums1 = {1,3};
        int[] nums2 = {2,4};
       double count =  findMedianSortedArrays1(nums1,nums2);
        System.out.println(count);
    }

    /**'
     * 执行率慢
     * @param nums1 数组1
     * @param nums2 数组2
     * @return 中位值数
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 1合并两数组
        int[] c= new int[nums1.length+nums2.length];
        System.arraycopy(nums1, 0, c, 0, nums1.length);
        System.arraycopy(nums2, 0, c, nums1.length, nums2.length);
        // 数组升序排列
        Arrays.sort(c);
        if(c.length%2==0){
           double num = c[c.length/2-1]+c[c.length/2];
           return num/2;
        }else{
            return c[(c.length+1)/2-1];
        }
    }

    public static double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays1(nums2, nums1);
        }
        int m = nums1.length;
        int n = nums2.length;
        int left = 0, right = m;
        // median1：前一部分的最大值
        // median2：后一部分的最小值
        int median1 = 0, median2 = 0;
        while (left <= right) {
            // 前一部分包含 nums1[0 .. i-1] 和 nums2[0 .. j-1]
            // 后一部分包含 nums1[i .. m-1] 和 nums2[j .. n-1]
            int i = (left + right) / 2;
            int j = (m + n + 1) / 2 - i;
            // nums_im1, nums_i, nums_jm1, nums_j 分别表示 nums1[i-1], nums1[i], nums2[j-1], nums2[j]
            int nums_im1 = (i == 0 ? Integer.MIN_VALUE : nums1[i - 1]);
            int nums_i = (i == m ? Integer.MAX_VALUE : nums1[i]);
            int nums_jm1 = (j == 0 ? Integer.MIN_VALUE : nums2[j - 1]);
            int nums_j = (j == n ? Integer.MAX_VALUE : nums2[j]);

            if (nums_im1 <= nums_j) {
                median1 = Math.max(nums_im1, nums_jm1);
                median2 = Math.min(nums_i, nums_j);
                left = i + 1;
            } else {
                right = i - 1;
            }
        }

        return (m + n) % 2 == 0 ? (median1 + median2) / 2.0 : median1;
    }
}