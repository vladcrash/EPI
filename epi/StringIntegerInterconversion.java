package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
public class StringIntegerInterconversion {

  public static String intToString(int x) {
    // TODO - you fill in here.
    return "";
  }
  public static int stringToInt(String s) {
    if (s == null || s.isEmpty()) {
      return 0;
    }
    int sign = s.charAt(0) == '-' ? -1 : 1;
    int start = sign < 0 ? 1 : 0;
    int exp = 1;
    int result = 0;
    for(int i = s.length() - 1; i >= start; i--) {
      result += Character.getNumericValue(s.charAt(i)) * exp;
      exp *= 10;
    }

    return result * sign;
  }
  @EpiTest(testDataFile = "string_integer_interconversion.tsv")
  public static void wrapper(int x, String s) throws TestFailure {
    if (stringToInt(s) != x) {
      throw new TestFailure("String to int conversion failed");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "StringIntegerInterconversion.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
