package example.store;

import example.account.AccountManager;
import example.account.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

public class StoreV2Test {

    @Test
    void buyShouldFailIfQuantityIsZero() {
        // Arrange
        AccountManager accountManager = mock(AccountManager.class);
        Customer customer = mock(Customer.class);
        Store store = new StoreImpl(accountManager);
//        Product egg = new Product();
//        egg.setQuantity(0);
        Product egg = mock(Product.class);
        when(egg.getQuantity()).thenReturn(0);

        // Act
        RuntimeException exception =Assertions.assertThrows(RuntimeException.class,()->{
            store.buy(egg,customer);
        });

        // Assert
        Assertions.assertEquals("Product out of stock", exception.getMessage());
    }
    @Test
    void buyShouldFailIfWithDrawFail() {

        // Arrange
        AccountManager accountManager = mock(AccountManager.class);
        Customer customer = mock(Customer.class);

        when(accountManager.withdraw(customer,100)).thenReturn("insufficient account balance");//mock returning error

        Store store = new StoreImpl(accountManager);
        Product egg = mock(Product.class);
        when(egg.getQuantity()).thenReturn(3);
        when(egg.getPrice()).thenReturn(100);

        // Act
        Exception exception = Assertions.assertThrows(RuntimeException.class,()->{
            store.buy(egg,customer);
        });
        // Assert
        Assertions.assertEquals("Payment failure: ",exception.getMessage().substring(0,17));
    }

    @Test
    void buyShouldBeDoneIfWithDrawSucceedAndQuantityIsBiggerThanZeroInStore(){
        //arrange
        AccountManager accountManager = mock(AccountManager.class);

        Customer customer = mock(Customer.class);
        when(accountManager.withdraw(customer,5)).thenReturn("success");//mock returning error



        Product egg = new Product();
        egg.setQuantity(100);
        egg.setPrice(5);

        Store s =new StoreImpl(accountManager);


        //act
        Assertions.assertDoesNotThrow(()->{
            s.buy(egg,customer);
        });
        //assert
        Assertions.assertEquals(99,egg.getQuantity());
    }

}
