package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;

public class IntersectSortedArrays {
    @EpiTest(testDataFile = "intersect_sorted_arrays.tsv")

    public static List<Integer> intersectTwoSortedArrays(List<Integer> a,
                                                         List<Integer> b) {
        if (a == null) {
            return b;
        }

        if (b == null) {
            return a;
        }

        List<Integer> result = new ArrayList<>();
        int pointerA = 0;
        int pointerB = 0;
        while (pointerA < a.size() && pointerB < b.size()) {
            if (a.get(pointerA).equals(b.get(pointerB))) {
                if (pointerA == 0 || !a.get(pointerA).equals(a.get(pointerA - 1))) {
                    result.add(a.get(pointerA));
                }
                pointerA++;
                pointerB++;
            } else if (Integer.compare(a.get(pointerA), b.get(pointerB)) < 0) {
                pointerA++;
            } else {
                pointerB++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IntersectSortedArrays.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
