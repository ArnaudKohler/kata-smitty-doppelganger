package info.dmerej;

import org.junit.jupiter.api.Test;

public class SafeCalculatorTest {
  @Test
  void should_not_throw_when_authorized() {
    SafeCalculator calculator = new SafeCalculator(() -> true);
    calculator.add(1, 2);
  }

  public static void main(String[] args) {
    SafeCalculatorTest test = new SafeCalculatorTest();
    test.should_not_throw_when_authorized();
  }
}

