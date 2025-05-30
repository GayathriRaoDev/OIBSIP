package atm;

import java.util.List;
import java.util.Scanner;

public class ATM {
    private final Account acc;
    private final Bank bank;
    private final Scanner sc = new Scanner(System.in);

    public ATM(Account acc, Bank bank) {
        this.acc = acc;
        this.bank = bank;
    }
    public void start() {
    while (true) {
        System.out.println("\n---- ATM MENU ----");
        System.out.println("1) Transactions History");
        System.out.println("2) Withdraw");
        System.out.println("3) Deposit");
        System.out.println("4) Transfer");
        System.out.println("5) Quit");
        System.out.print("Choose option: ");

        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                showHistory();
                break;
            case 2:
                doWithdraw();
                break;
            case 3:
                doDeposit();
                break;
            case 4:
                doTransfer();
                break;
            case 5:
                System.out.println("Thank you!");
                return;
            default:
                System.out.println("Invalid option");
                break;
        }
    }
}

    private void showHistory() {
        List<Transaction> h = acc.getHistory();
        if (h.isEmpty()) System.out.println("No transactions yet.");
        else h.forEach(System.out::println);
        System.out.printf("Current balance: ₹%.2f%n", acc.getBalance());
    }
    private void doWithdraw() {
        System.out.print("Amount to withdraw: ");
        double amt = sc.nextDouble();
        if (acc.withdraw(amt))
            System.out.println("Please collect your cash.");
        else
            System.out.println("Insufficient funds.");
    }
    private void doDeposit() {
        System.out.print("Amount to deposit: ");
        double amt = sc.nextDouble();
        acc.deposit(amt);
        System.out.println("Deposit successful.");
    }
    private void doTransfer() {
        System.out.print("Destination account ID: ");
        int toId = sc.nextInt();
        System.out.print("Amount to transfer: ");
        double amt = sc.nextDouble();
        if (bank.transfer(acc, toId, amt))
            System.out.println("Transfer complete.");
        else
            System.out.println("Transfer failed (check ID or balance).");
    }
}
