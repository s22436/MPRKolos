package pl.pjatk.s22436Bank.models;

public class Transaction {
    private TransactionStatus status;
    private float newBalance;
    private String userId;

    public Transaction(TransactionStatus status, float newBalance, String userId) {
        this.status = status;
        this.newBalance = newBalance;
        this.userId = userId;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public float getNewBalance() {
        return newBalance;
    }

    public String getUserId() {
        return userId;
    }
}
