package org.yeditepe.week9;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    @Test
    void test_withDummy() {
        PaymentGateway dummyGateway = amount -> true; // not important

        NotificationService dummyNotification = (u, m) -> {
            // do nothing
        };

        OrderService service = new OrderService(dummyGateway, dummyNotification);

        boolean result = service.placeOrder("Ali", 100);

        assertTrue(result);
    }

    @Test
    void test_withStub() {
        PaymentGateway stubGateway = amount -> true; // always succeeds

        NotificationService dummyNotification = (u, m) -> {};

        OrderService service = new OrderService(stubGateway, dummyNotification);

        boolean result = service.placeOrder("Ali", 100);

        assertTrue(result);
    }

    @Test
    void test_withFake() {
        PaymentGateway fakeGateway = new FakePaymentGateway();

        NotificationService dummyNotification = (u, m) -> {};

        OrderService service = new OrderService(fakeGateway, dummyNotification);

        boolean result = service.placeOrder("Ali", 100);

        assertTrue(result);
    }



    @Test
    void test_withMock() {
        PaymentGateway mockGateway = mock(PaymentGateway.class);
        NotificationService mockNotification = mock(NotificationService.class);

        when(mockGateway.pay(100)).thenReturn(true);

        OrderService service = new OrderService(mockGateway, mockNotification);

        boolean result = service.placeOrder("Ali", 100);

        assertTrue(result);

        // 🔥 verify behavior
        verify(mockNotification).send("Ali", "Order successful");
    }

}