package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.List;

public class ThreeSum {
    @EpiTest(testDataFile = "three_sum.tsv")

    public static boolean hasThreeSum(List<Integer> nums, int num) {
        if (nums == null) {
            return false;
        }
        Collections.sort(nums);
        for (Integer i : nums) {
            if (twoSum(num - i, nums)) {
                return true;
            }
        }
        return false;
    }

    private static boolean twoSum(int num, List<Integer> nums) {
        int start = 0;
        int end = nums.size() - 1;
        while (start <= end) {
            int result = nums.get(start) + nums.get(end);
            if (result == num) {
                return true;
            } else if (result > num) {
                end--;
            } else {
                start++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "ThreeSum.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
