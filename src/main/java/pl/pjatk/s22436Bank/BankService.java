package pl.pjatk.s22436Bank;

import org.springframework.stereotype.Component;
import pl.pjatk.s22436Bank.models.Transaction;
import pl.pjatk.s22436Bank.models.TransactionStatus;
import pl.pjatk.s22436Bank.models.User;
import pl.pjatk.s22436Bank.storage.UserStorage;

@Component
public class BankService {

    private final UserStorage userStorage;
    public BankService (UserStorage userStorage) {
        this.userStorage = userStorage;
    }
    public User registerNewClient(float startingBalance){
        User newUser = new User(String.valueOf(userStorage.getUsers().size()), startingBalance);
        userStorage.addUser(newUser);
        return newUser;
    }

    public Transaction createBankTransfer(String userId, float amount){
        User userToTransferFrom = userStorage.getUser(userId);
        if(userToTransferFrom == null){
            return new Transaction(TransactionStatus.USER_NOT_FOUND, 0, userId);
        }
        if(userToTransferFrom.getBalance() < amount){
            return new Transaction(TransactionStatus.DECLINED, userToTransferFrom.getBalance(), userId);
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

    public String getClientData(String userId){
        User userToPrintData = userStorage.getUser(userId);
        if(userToPrintData == null){
            return "USER_NOT_FOUND";
        }

        return userToPrintData.toString();
    }
}
