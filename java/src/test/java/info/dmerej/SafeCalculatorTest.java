package info.dmerej;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SafeCalculatorTest {

  @Mock
  Authorizer authorizer;

  @InjectMocks
  SafeCalculator calculator;

  @Test
  void should_not_throw_when_authorized() {
    when(authorizer.authorize()).thenReturn(true);
    int result = calculator.add(1, 2);
    assertEquals(3, result);
  }
}

