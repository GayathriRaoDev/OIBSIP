package atm;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    public enum Type { DEPOSIT, WITHDRAW, TRANSFER }

    private final Type type;
    private final double amount;
    private final String note;
    private final LocalDateTime timestamp;

    public Transaction(Type type, double amount, String note) {
        this.type = type;
        this.amount = amount;
        this.note = note;
        this.timestamp = LocalDateTime.now();
    }
    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return String.format("[%s] %-8s ₹%.2f  %s",
                timestamp.format(fmt), type, amount, note);
    }
}
