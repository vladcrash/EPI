package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
public class SearchFirstKey {
  @EpiTest(testDataFile = "search_first_key.tsv")

  public static int searchFirstOfK(List<Integer> A, int k) {
      if (A == null || A.isEmpty()) {
          return -1;
      }
      
      int left = 0;
      int right = A.size() - 1;
      while (left <= right) {
          int mid = (right - left) / 2 + left;
          if (A.get(mid) < k) {
              left = mid + 1;
          } else {
              right = mid - 1;
          }
      }

      return left < A.size() && A.get(left) == k ? left : -1;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SearchFirstKey.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
