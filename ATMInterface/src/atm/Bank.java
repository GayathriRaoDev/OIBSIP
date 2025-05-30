package atm;

import java.util.HashMap;
import java.util.Map;

public class Bank {
    private final Map<Integer, Account> accounts = new HashMap<>();

    public void addAccount(Account acc) { accounts.put(acc.getId(), acc); }

    public Account getAccount(int id)   { return accounts.get(id); }

    /** Returns true if transfer succeeds */
    public boolean transfer(Account from, int toId, double amt) {
        Account to = accounts.get(toId);
        if (to == null || !from.withdraw(amt)) return false;
        to.deposit(amt);
        from.addTransfer(-amt, "Sent to " + toId);
        to.addTransfer(+amt, "Received from " + from.getId());
        return true;
    }
}
