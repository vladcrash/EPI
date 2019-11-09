package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsTreeBalanced {

  @EpiTest(testDataFile = "is_tree_balanced.tsv")

  public static boolean isBalanced(BinaryTreeNode<Integer> root) {
    return isBalancedInternal(root) != Integer.MIN_VALUE;
  }

  private static int isBalancedInternal(BinaryTreeNode<Integer> root) {
      if (root == null) {
          return -1;
      }

      int left = isBalancedInternal(root.left);
      if (left == Integer.MIN_VALUE) {
          return Integer.MIN_VALUE;
      }

      int right = isBalancedInternal(root.right);
      if (right == Integer.MIN_VALUE) {
          return Integer.MIN_VALUE;
      }

      if (Math.abs(left - right) < 2) {
          return Math.max(left, right) + 1;
      } else {
          return Integer.MIN_VALUE;
      }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsTreeBalanced.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
