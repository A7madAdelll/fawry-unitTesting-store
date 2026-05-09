package example.store;

import example.account.AccountManager;
import example.account.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

public class StoreV2Test {

    @Test
    void buyShouldFailIfQuantityIsZero() {
        // Arrange
        AccountManager accountManager = mock(AccountManager.class);
        Store store = new StoreImpl(accountManager);
        Product egg = new Product();
        Customer customer = new Customer();
        egg.setQuantity(0);


        // Act
        RuntimeException exception =Assertions.assertThrows(RuntimeException.class,()->{
            store.buy(egg,customer);
        });

        // Assert
        Assertions.assertEquals("Product out of stock", exception.getMessage());
    }

}
