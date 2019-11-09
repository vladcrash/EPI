package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class SortedArraysMerge {
  @EpiTest(testDataFile = "sorted_arrays_merge.tsv")

  public static List<Integer>
  mergeSortedArrays(List<List<Integer>> sortedArrays) {
    List<Integer> result = new ArrayList<>();
    if (sortedArrays == null) {
        return result;
    }

    PriorityQueue<Item> pq = new PriorityQueue<>((a, b) -> a.value - b.value);

    for (int i = 0; i < sortedArrays.size(); i++) {
        if (sortedArrays.get(i) != null && !sortedArrays.get(i).isEmpty()) {
            pq.offer(new Item(sortedArrays.get(i).get(0), i, 0));
        }
    }

    while (!pq.isEmpty()) {
        Item item = pq.poll();
        int min = item.value;
        result.add(min);
        List<Integer> list = sortedArrays.get(item.i);
        if (item.j + 1 < list.size()) {
            pq.offer(new Item(list.get(item.j + 1), item.i, item.j + 1));
        }
    }

    return result;
  }

  private static class Item {
      private int value;
      private int i;
      private int j;

      public Item(int value, int i, int j) {
          this.value = value;
          this.i = i;
          this.j = j;
      }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SortedArraysMerge.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
