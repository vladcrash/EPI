package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashMap;
import java.util.Map;

public class PowerXY {
	@EpiTest(testDataFile = "power_x_y.tsv")
	public static double power(double x, int y) {
		if (y < 0) {
			y *= -1;
			x = 1.0 / x;
		}

		Map<Integer, Double> values = new HashMap<>();
		double result = 1.0;
		int i = 2;
		values.put(1, x);
		while (i <= y) {
			values.put(i, values.get(i / 2) * values.get(i / 2));
			i *= 2;
		}

		while (y != 0) {
			while (y % i == y) {
				i /= 2;
			}
			y -= i;
			result *= values.get(i);
		}

		return result;
	}

	public static void main(String[] args) {
		System.exit(
				GenericTest
						.runFromAnnotations(args, "PowerXY.java",
								new Object() {
								}.getClass().getEnclosingClass())
						.ordinal());
	}
}
