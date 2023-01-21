package pl.pjatk.s22436Bank;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.pjatk.s22436Bank.models.Transaction;
import pl.pjatk.s22436Bank.models.TransactionStatus;
import pl.pjatk.s22436Bank.models.User;
import pl.pjatk.s22436Bank.storage.UserStorage;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BankServiceTests {

    @Mock
    private UserStorage userStorage;

    @InjectMocks
    private BankService bankService;


    @Test
    void shouldRegisterNewUserAndPrintHisDataCorrectly(){
        //given
        User user = new User("11", 200);
        when(userStorage.getUser(any())).thenReturn(user);

        //when
        String userData = bankService.getClientData("11");
        //then
        assertThat(userData).isEqualTo("User - id: 11, balance: 200.0");
    }

    @Test
    void shouldSuccessfullyTransferMoneyOutOfAccount(){
        //given
        User user = new User("11", 200);
        when(userStorage.getUser(any())).thenReturn(user);

        //when
        Transaction transaction = bankService.createBankTransfer("11", 50);
        //then
        assertThat(transaction.getStatus()).isEqualTo(TransactionStatus.ACCEPTED);
        assertThat(transaction.getNewBalance()).isEqualTo(150);
        assertThat(user.getBalance()).isEqualTo(150);
    }

    @Test
    void shouldGiveInformationWhenUserDoesNotExistWhileTransfer(){
        //given
        when(userStorage.getUser(any())).thenReturn(null);

        //when
        Transaction transaction = bankService.createBankTransfer("11", 50);

        //then
        assertThat(transaction.getStatus()).isEqualTo(TransactionStatus.USER_NOT_FOUND);
    }

    @Test
    void shouldGiveInformationWhenUserDoesNotHaveEnoughFundsWhileTransfer(){
        //given
        User user = new User("11", 200);
        when(userStorage.getUser(any())).thenReturn(user);

        //when
        Transaction transaction = bankService.createBankTransfer("11", 350);
        //then
        assertThat(transaction.getStatus()).isEqualTo(TransactionStatus.DECLINED);
        assertThat(transaction.getNewBalance()).isEqualTo(200);
        assertThat(user.getBalance()).isEqualTo(200);
    }

    @Test
    void shouldDepositMoneyCorrectly(){
        //given
        User user = new User("11", 200);
        when(userStorage.getUser(any())).thenReturn(user);

        //when
        Transaction transaction = bankService.createBankDeposit("11", 350);
        //then
        assertThat(transaction.getStatus()).isEqualTo(TransactionStatus.ACCEPTED);
        assertThat(transaction.getNewBalance()).isEqualTo(550);
        assertThat(user.getBalance()).isEqualTo(550);
    }

    @Test
    void shouldGiveInformationWhenUserDoesNotExistWhileDeposit(){
        //given
        when(userStorage.getUser(any())).thenReturn(null);

        //when
        Transaction transaction = bankService.createBankDeposit("11", 350);
        //then
        assertThat(transaction.getStatus()).isEqualTo(TransactionStatus.USER_NOT_FOUND);
    }

}
