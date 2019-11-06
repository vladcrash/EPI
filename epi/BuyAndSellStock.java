package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class BuyAndSellStock {
  @EpiTest(testDataFile = "buy_and_sell_stock.tsv")
  public static double computeMaxProfit(List<Double> prices) {
    if (prices == null) {
      return 0;
    }

    double max = 0;
    double min = Double.MAX_VALUE;
    for (int i = 0; i < prices.size(); i++) {
        max = Math.max(max, prices.get(i) - min);
        min = Math.min(min, prices.get(i));
    }
    return max;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "BuyAndSellStock.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
