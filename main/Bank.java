package main;

import java.util.*;
import java.io.*;

public class Bank {

    private ArrayList<Account> accounts;
    private String accountFile;

    public Bank(String accountFile) {
        this.accountFile = accountFile;
        accounts = getAccountsFromFile();
    }

    public Account getAccountOfNumber(int number) {
        for (int i = 0; i < accounts.size(); i++) {
            if (number == accounts.get(i).getNumber()) {
                return accounts.get(i);
            }
        }

        return null;
    }

    public Account getAccountOfIndex(int index) {
        if (index >= 0 && index < accounts.size()) {
            return accounts.get(index);
        }
        return null;
    }

    public int getNumberOfAccounts() {
        return accounts.size();
    }

    public double getTotalBalance() {
        double total = 0;

        for (int i = 0; i < getNumberOfAccounts(); i++) {
            total += getAccountOfIndex(i).getBalance();
        }

        return total;
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
}