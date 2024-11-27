package info.dmerej;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

@ExtendWith(MockitoExtension.class)
public class DiscountApplierTest {

  @Mock
  private Notifier notifier;

  @InjectMocks
  private DiscountApplier discountApplier;

  private User user1 = new User("A", "A@test.com");
  private User user2 = new User("B", "B@test.com");

  @Test
  void should_notify_twice_when_applying_discount_for_two_users_v1() {
    
    discountApplier.applyV1(10, List.of(user1, user2));
    verify(notifier, times(1)).notify(user1, "You've got a new discount of 10%");
    verify(notifier, times(1)).notify(user2, "You've got a newdiscount of 10%");

  }

  @Test
  void should_notify_twice_when_applying_discount_for_two_users_v2() {
    discountApplier.applyV2(10, List.of(user1, user2));
    verify(notifier, times(1)).notify(user1, "You've got a new discount of 10%");
    verify(notifier, times(1)).notify(user2, "You've got a new discount of 10%");
  }
}
