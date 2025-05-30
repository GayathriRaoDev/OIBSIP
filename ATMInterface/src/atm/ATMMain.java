package atm;

import java.util.Scanner;

public class ATMMain {
    public static void main(String[] args) {

        // ----- seed demo data -----
        Bank bank = new Bank();
        bank.addAccount(new Account(1001, 1234, 5000));
        bank.addAccount(new Account(1002, 4321, 3000));

        Scanner sc = new Scanner(System.in);
        Account loggedIn = null;

        // ----- login loop -----
        for (int tries = 0; tries < 3 && loggedIn == null; tries++) {
            System.out.print("Enter User ID: ");
            int id = sc.nextInt();
            System.out.print("Enter PIN: ");
            int pin = sc.nextInt();

            Account acc = bank.getAccount(id);
            if (acc != null && acc.validatePin(pin)) loggedIn = acc;
            else System.out.println("Invalid credentials.\n");
        }
        if (loggedIn == null) {
            System.out.println("Too many failed attempts. Exiting.");
            System.exit(0);
        }
        // ----- start session -----
        new ATM(loggedIn, bank).start();
    }
}
