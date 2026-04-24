package org.yeditepe.week9;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;


public class OrderServiceTestwithMockito {

    @Mock
    NotificationService notificationService;
    @Mock
    PaymentGateway paymentGateway;
    //PaymentGateway paymentGateway=mock(PaymentGateway.class);
    @InjectMocks
    OrderService orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testwithMockObjects(){

        when(paymentGateway.pay(100)).thenReturn(true);
        boolean result = orderService.placeOrder("Engin", 100);

        verify(notificationService,times(1))
                .send("Engin","Order successful");
        ArgumentCaptor<String> usernameCapture=ArgumentCaptor.forClass(String.class);


        verify(notificationService).send(usernameCapture.capture(),
                usernameCapture.capture());
        System.out.println(usernameCapture.getAllValues());
        assertEquals("Engin",usernameCapture.getAllValues().get(0));



        assertTrue(result);
    }
}
