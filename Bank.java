import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Bank {

    private ArrayList<Account> accounts;
    private String accountFile;

    public Bank(String accountFile) {
        this.accountFile = accountFile;
        accounts = getAccountsFromFile();
    }

    public Account getAccount(int number) {
        for (int i = 0; i < accounts.size(); i++) {
            if (number == accounts.get(i).getNumber()) {
                return accounts.get(i);
            }
        }

        return null;
    }

    // Gets the number of accounts stored in the file. Returns -1 for error
    private int getAccountFileLength() {
        int i = 0;

        try {
            File file = new File(accountFile);
            if (file.createNewFile()) {
                return 0;
            } else {
                Scanner scanner = new Scanner(file);

                while (scanner.hasNextLine()) {
                    scanner.nextLine();
                    i++;
                }

                scanner.close();
                return i;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    // Reads the account file and returns an array of accounts
    private ArrayList<Account> getAccountsFromFile() {
        ArrayList<Account> accounts = new ArrayList<Account>();

        try {
            File file = new File(accountFile);

            if (!file.createNewFile()) {
                Scanner scanner = new Scanner(file);

                while (scanner.hasNextLine()) {
                    accounts.add(new Account(scanner.nextLine()));
                }

                scanner.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return accounts;
    }

    // Writes the accounts array to the file
    public void writeAccountsToFile() {
        try {
            PrintWriter writer = new PrintWriter(accountFile);

            for (int i = 0; i < accounts.size(); i++) {
                writer.println(accounts.get(i).getAsCSVString());
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Adds an account to the bank
    public void addAccount(Account account) {
        accounts.add(account);
    }

    public String toString() {
        String result = "";
        result += String.format("%d accounts:", accounts.size());

        for (int i = 0; i < accounts.size(); i++) {
            result += "\n" + accounts.get(i);
        }

        return result;
    }

    public static void main(String[] args) {
        Bank bank = new Bank("accounts.txt");
        System.out.println(bank);

        bank.getAccount(14).depositFunds(4284.68);
        bank.getAccount(15).depositFunds(20.41);
        bank.getAccount(15).withdrawFunds(400);
        bank.getAccount(15).withdrawFunds(12);

        System.out.println(bank);
    }
}