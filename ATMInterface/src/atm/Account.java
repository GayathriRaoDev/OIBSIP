package atm;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private final int id;
    private final int pin;
    private double balance;
    private final List<Transaction> history = new ArrayList<>();

    public Account(int id, int pin, double openingBalance) {
        this.id = id;
        this.pin = pin;
        this.balance = openingBalance;
    }
    public int getId()         { return id; }
    public double getBalance() { return balance; }
    public List<Transaction> getHistory() { return history; }

    public boolean validatePin(int input) { return this.pin == input; }

    public void deposit(double amount) {
        balance += amount;
        history.add(new Transaction(Transaction.Type.DEPOSIT, amount, "Cash deposit"));
    }
    public boolean withdraw(double amount) {
        if (amount > balance) return false;
        balance -= amount;
        history.add(new Transaction(Transaction.Type.WITHDRAW, amount, "Cash withdrawal"));
        return true;
    }
    public void addTransfer(double amount, String note) {
        history.add(new Transaction(Transaction.Type.TRANSFER, amount, note));
    }
}
