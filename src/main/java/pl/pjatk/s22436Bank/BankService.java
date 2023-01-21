package pl.pjatk.s22436Bank;

import org.springframework.stereotype.Component;
import pl.pjatk.s22436Bank.models.Transaction;
import pl.pjatk.s22436Bank.models.TransactionStatus;
import pl.pjatk.s22436Bank.models.User;
import pl.pjatk.s22436Bank.storage.TransactionStorage;
import pl.pjatk.s22436Bank.storage.UserStorage;

@Component
public class BankService {

    private final UserStorage userStorage;
    private final TransactionStorage transactionStorage;
    public BankService (UserStorage userStorage, TransactionStorage transactionStorage) {
        this.userStorage = userStorage;
        this.transactionStorage = transactionStorage;
    }
    public void registerNewClient(float startingBalance){
        User newUser = new User(String.valueOf(userStorage.getUsers().size()), startingBalance);
        userStorage.addUser(newUser);
    }

    public Transaction createBankTransfer(String userId, float amount){
        User userToTransferFrom = userStorage.getUser(userId);
        if(userToTransferFrom == null){
            return new Transaction(TransactionStatus.USER_NOT_FOUND, 0, userId);
        }
        if(userToTransferFrom.getBalance() < amount){
            return new Transaction(TransactionStatus.INSUFFICIENT_FUNDS, userToTransferFrom.getBalance(), userId);
        }
        userToTransferFrom.subtractFromBalance(amount);
        return new Transaction(TransactionStatus.ACCEPTED, userToTransferFrom.getBalance(), userId);
    }

    public Transaction createBankDeposit(String userId, float amount){
        User userToDepositTo = userStorage.getUser(userId);
        if(userToDepositTo == null){
            return new Transaction(TransactionStatus.USER_NOT_FOUND, 0, userId);
        }
        userToDepositTo.addToBalance(amount);
        return new Transaction(TransactionStatus.ACCEPTED, userToDepositTo.getBalance(), userId);
    }
}
