package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class ReverseSublist {
	@EpiTest(testDataFile = "reverse_sublist.tsv")

	public static ListNode<Integer> reverseSublist(ListNode<Integer> head, int start,
												   int finish) {
		if (head == null || start < 1 || finish <= start) {
			return head;
		}
		ListNode<Integer> beforeStart = null;
		ListNode<Integer> end = null;
		ListNode<Integer> temp = head;
		int index = 1;
		while (temp != null) {
			if (index + 1 == start) {
				beforeStart = temp;
			}
			if (index + 1 == finish) {
				end = temp.next;
				break;
			}
			temp = temp.next;
			index++;
		}

		ListNode<Integer> startSublist = beforeStart != null ? beforeStart.next : head;
		ListNode<Integer> afterEndSublist = end != null ? end.next : head;

		temp = startSublist;
		ListNode<Integer> previous = null;
		while (temp != null && temp != afterEndSublist) {
			ListNode<Integer> next = temp.next;
			temp.next = previous;
			previous = temp;
			temp = next;
		}

		startSublist.next = afterEndSublist;
		if (beforeStart != null) {
			beforeStart.next = end;
		}
		return start > 1 ? head : end;
	}

	private static ListNode<Integer> reverseList(ListNode<Integer> head) {
		ListNode<Integer> previous = null;
		while (head != null) {
			ListNode<Integer> temp = head.next;
			head.next = previous;
			previous = head;
			head = temp;
		}

		return previous;
	}

	public static void main(String[] args) {
		System.exit(
				GenericTest
						.runFromAnnotations(args, "ReverseSublist.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
