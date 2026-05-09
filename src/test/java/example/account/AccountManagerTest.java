package example.account;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AccountManagerTest {

    @Test
    void givenCustomerWithSufficientBalance_whenWithdraw_thenSucceed() {
        // Arrange
        AccountManager am = new AccountManagerImpl();
        Customer c = new Customer();
        c.setBalance(1000);

        // Act
        String result = am.withdraw(c, 500);

        // Assert
        Assertions.assertEquals("success", result);
        Assertions.assertEquals(500, c.getBalance());
    }
    @Test
    void withDrawShouldFailIfNewBalanceIsLessThanMaxCreditAndCustomerNotVip() {
        // Arrange
        AccountManager am = new AccountManagerImpl();
        Customer c = new Customer();
        c.setBalance(0);
        c.setCreditAllowed(true);
        c.setVip(false);
        int overflowwithdrawAmount =AccountManagerImpl.getMaxCredit()+1;
        // Act
        String result = am.withdraw(c, overflowwithdrawAmount);


        // Assert
        Assertions.assertEquals("maximum credit exceeded", result);
    }
    @Test
    void withDrawShouldFailIfCustomerIsnotisCreditAllowedAndNotVip() {
        // Arrange
        AccountManager am = new AccountManagerImpl();
        Customer c = new Customer();
        c.setBalance(0);
        c.setCreditAllowed(false);
        c.setVip(false);
        int overflowwithdrawAmount =1;
        // Act
        String result = am.withdraw(c, overflowwithdrawAmount);


        // Assert
        Assertions.assertEquals("insufficient account balance", result);
    }

    @Test
    void givenCustomerDepositingNegativeAmountToBalanceThenFail(){
        AccountManager am = new AccountManagerImpl();
        Customer c = new Customer();
        c.setBalance(0);
        String result=am.deposit(c,-300);
        Assertions.assertEquals("fail", result);
    }
    @Test
    void givenCustomerDepositingNonNegativeAmountToBalanceThenSucceed(){
        AccountManager am = new AccountManagerImpl();
        Customer c = new Customer();
        c.setBalance(0);
        String result=am.deposit(c,300);
        Assertions.assertEquals("succeed", result);
        Assertions.assertEquals(300, c.getBalance());
    }



}
