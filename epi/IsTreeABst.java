package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IsTreeABst {
    @EpiTest(testDataFile = "is_tree_a_bst.tsv")

    public static boolean isBinaryTreeBST(BinaryTreeNode<Integer> root) {
        return isBST(root, new BinaryTreeNode<>(null));
    }

    private static boolean isBST(BinaryTreeNode<Integer> current, BinaryTreeNode<Integer> previous) {
        if (current == null) {
            return true;
        }

        boolean left = isBST(current.left, previous);

        if (previous.data == null) {
            previous.data = current.data;
        }

        if (Integer.compare(previous.data, current.data) <= 0) {
            previous.data = current.data;
        } else {
            return false;
        }

        boolean right = isBST(current.right, previous);

        return left && right;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IsTreeABst.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
