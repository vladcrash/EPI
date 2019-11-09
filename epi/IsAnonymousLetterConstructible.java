package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashMap;
import java.util.Map;

public class IsAnonymousLetterConstructible {
  @EpiTest(testDataFile = "is_anonymous_letter_constructible.tsv")

  public static boolean isLetterConstructibleFromMagazine(String letter,
                                                          String magazine) {
    if (letter == null || magazine == null) {
        return false;
    }
    Map<Character, Integer> frequency = new HashMap<>();
    for (char c : letter.toCharArray()) {
        frequency.put(c, frequency.getOrDefault(c, 0) + 1);
    }

    for (char c : magazine.toCharArray()) {
        if (frequency.containsKey(c)) {
            frequency.put(c, frequency.get(c) - 1);
            if (frequency.get(c) == 0) {
                frequency.remove(c);
                if (frequency.isEmpty()) {
                    break;
                }
            }

        }
    }

    return frequency.isEmpty();
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsAnonymousLetterConstructible.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
