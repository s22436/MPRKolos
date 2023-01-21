package pl.pjatk.s22436Bank.models;

public class User {
    private String id;
    private float balance;

    public User(String id, float balance) {
        this.id = id;
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public void subtractFromBalance(float amount){
        this.balance -= amount;
    }

    public void addToBalance(float amount){
        this.balance += amount;
    }
}
