package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class SortedListsMerge {
  @EpiTest(testDataFile = "sorted_lists_merge.tsv")
  //@include
  public static ListNode<Integer> mergeTwoSortedLists(ListNode<Integer> p,
                                                      ListNode<Integer> s) {
    ListNode<Integer> dummy = new ListNode<>(0, null);
    ListNode<Integer> head = dummy;
    while (p != null && s != null) {
        if (Integer.compare(p.data, s.data) < 0) {
            head.next = p;
            p = p.next;
        } else {
            head.next = s;
            s = s.next;
        }
        head = head.next;
    }
    head.next = p != null ? p : s;
    return dummy.next;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SortedListsMerge.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
