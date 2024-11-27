package info.dmerej;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CaptureNotifier implements Notifier {
  int count = 0;

  @Override
  public void notify(User user, String message) {
    count++;
  }
}

record Notification(User user, String message) {
}

class MockNotifier implements Notifier {
  public final List<Notification> calls = new ArrayList<>();

  @Override
  public void notify(User user, String message) {
    calls.add(new Notification(user, message));
  }
}

public class DiscountApplierTest {
  private User pablo;
  private User pablito;
  private List<User> users;

  @BeforeEach
  void setUp() {
    pablo = new User("pablo", "pablo@gmail.com");
    pablito = new User("pablito", "pablito@gmail.com");
    users = List.of(pablo, pablito);
  }

  @Test
  void should_notify_twice_when_applying_discount_for_two_users_v1() {
    var notifier = new CaptureNotifier();
    var discount = new DiscountApplier(notifier);
    discount.applyV1(10, users);
    assertEquals(2, notifier.count);
  }

  @Test
  void should_notify_twice_when_applying_discount_for_two_users_v2() {
    var mockNotifier = new MockNotifier();
    var discount = new DiscountApplier(mockNotifier);
    discount.applyV2(10, users);

    var expectedEmails = users.stream().map(User::email).toList();
    var actualEmails = mockNotifier.calls.stream().map(n -> n.user().email()).toList();
    assertEquals(expectedEmails, actualEmails);
  }
}