package info.dmerej;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CaptureNotifier implements Notifier {
  int count;

  public CaptureNotifier() {
    this.count = 0;
  }

  @Override
  public void notify(User user, String message) {
    count++;
  }
}

record Notification(User user, String message) {
}

class MockNotifier implements Notifier {
  public final List<Notification> calls;

  MockNotifier() {
    this.calls = new ArrayList<>();
  }

  @Override
  public void notify(User user, String message) {
    var notification = new Notification(user, message);
    calls.add(notification);
  }
}


public class DiscountApplierTest {
  private final User pablo = new User("pablo", "pablo@gmail.com");

  private final User pablito = new User("pablito", "pablito@gmail.com");


  @Test
  void should_notify_twice_when_applying_discount_for_two_users_v1() {
    var notifier = new CaptureNotifier();
    var discount = new DiscountApplier(notifier);
    discount.applyV1(10, List.of(pablo, pablito));
    assertEquals(2, notifier.count);
  }

  @Test
  void should_notify_twice_when_applying_discount_for_two_users_v2() {
    var notifier = new CaptureNotifier();
    var discount = new DiscountApplier(notifier);
    discount.applyV2(10, List.of(pablo, pablito));

  }

}
