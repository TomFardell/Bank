import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Account {

    private static final String ACCOUNT_NUMBER_FILE_NAME = "number.txt";

    private String forename;
    private String surname;
    private Bank bank;
    private int number;
    private double balance;

    public Account(String forename, String surname) {
        this.forename = forename;
        this.surname = surname;
        this.number = getNextNumber();
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFullName() {
        return forename + " " + surname;
    }

    public double getBalance() {
        return balance;
    }

    // Adds the amount to the balance. Returns whether the transaciton is successful
    public boolean depositFunds(double amount) {
        if (amount >= 0) {
            balance += amount;
            return true;
        }
        return false;
    }

    // Deducts the amount from the balance. Only goes through if the account
    // contains enough money. Returns whether the transaction is successful
    public boolean withdrawFunds(double amount) {
        if (amount >= 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public int getNumber() {
        return number;
    }

    // Reads the account number from the file and increments it. Returns -1 for an
    // error
    private int getNextNumber() {
        int num;

        // Reads the file, creating a new one if no such file exists
        try {
            File file = new File(ACCOUNT_NUMBER_FILE_NAME);
            if (file.createNewFile()) {
                num = 0;
            } else {
                Scanner scanner = new Scanner(file);
                num = Integer.parseInt(scanner.nextLine());
                scanner.close();
            }

            // Writes the incrememnted number to the file
            PrintWriter writer = new PrintWriter(ACCOUNT_NUMBER_FILE_NAME);
            writer.print(num + 1);
            writer.close();

            return num;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static void main(String[] args) {
        Account account1 = new Account("Tom", "Fardell");
        Account account2 = new Account("Matthew", "Hilton");
        System.out.println(account1.number);
        System.out.println(account2.number);
    }
}
