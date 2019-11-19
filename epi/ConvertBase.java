package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class ConvertBase {
	@EpiTest(testDataFile = "convert_base.tsv")

	public static String convertBase(String num, int b1, int b2) {
		if (num == null || num.isEmpty()) {
			return null;
		}
		if (num.equals("-0")) {
			return num;
		}
		int numInBaseTen = Integer.parseInt(num, b1);
		return Integer.toString(numInBaseTen, b2).toUpperCase();
	}

	public static void main(String[] args) {
		System.exit(
				GenericTest
						.runFromAnnotations(args, "ConvertBase.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
