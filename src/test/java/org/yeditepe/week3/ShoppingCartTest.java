package org.yeditepe.week3;

import net.jqwik.api.Disabled;
import org.junit.Before;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ShoppingCartTest {

    private static ShoppingCart cart;

    @BeforeEach
    public  void setup(){
        cart=new ShoppingCart();
    }

    @Test
    @Order(1)
    public void testItemCountWhereNoItemAdded(){

        assertEquals(0,cart.getItemCount());
    }

    @Test
    @DisplayName("Test where quantity or price is negative")
    @Order(3)
    public void testAddItemWhereQuantityorPriceInvalid(){

        assertThrows(IllegalArgumentException.class,
                ()->{cart.addItem("Tshirt",-5,10);
        });
        assertThrows(IllegalArgumentException.class,
                ()->{cart.addItem("Thirst",10,-5);}
        );
        assertThrows(IllegalArgumentException.class,
                ()->{cart.addItem("Thirt",-10,-10);});
    }
    @Test
    @Order(2)
    public void testAddSingleItem(){

        cart.addItem("Thirt",5,12);
        assertEquals(5,cart.getItemCount());

    }

    @RepeatedTest(value=10)
    @Order(4)
    public void testApplyDiscountWherePercentageIsValid(){
        cart.addItem("Thsirt",10,10);
        cart.applyDiscount(20);
        double actual= cart.getTotalCost();
        double expected = 80;
        assertEquals(expected,actual);
    }
@Test
@Disabled
public void testRemoveItemWhereItemDoesNotExist(){
        cart.addItem("Thirt",1,10);

        Throwable t = catchThrowable(()->{cart.removeItem("apple",2);});
        assertThat(t).hasMessage("Invalid item or quantity.");
}
@Test
public void testRemoveItemWhereQuantityIsInvalid(){
    cart.addItem("Thirt",1,10);

    assertThatThrownBy(()->cart.removeItem("apple",-5))
            .hasCauseExactlyInstanceOf(IllegalArgumentException.class)
            .hasMessage("Invalid item or quantity.");
}





}