package main;

import java.io.*;
import java.util.Scanner;

public class Account {

    private static final String ACCOUNT_NUMBER_FILE_NAME = "number.txt";

    private int number;
    private String forename;
    private String surname;
    private double balance;
    private String password;

    public Account(String forename, String surname, String password, int balance) {
        this.number = getNextNumber();
        this.forename = forename;
        this.surname = surname;
        this.password = password;
        this.balance = balance;
    }

    public Account(String forename, String surname, String password) {
        this(forename, surname, password, 0);
    }

    // Constructor from data stored in file
    public Account(String fileData) {
        String[] data = fileData.split(",");
        number = Integer.parseInt(data[0]);
        forename = data[1];
        surname = data[2];
        balance = Double.parseDouble(data[3]);
        password = data[4];
    }

    // Returns the details of the account as a CSV String in the form:
    // number,forename,surname,balance
    public String getAsCSVString() {
        return number + "," + forename + "," + surname + "," + balance + "," + password;
    }

    public String toString() {
        return String.format("%d: %s %s, £%.2f, %s", number, forename, surname, balance, password);
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

    public void setPassword(String password) {
        this.password = password;
    }

    // More secure than just a getter
    public boolean isPassword(String password) {
        return password.equals(this.password);
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
        int num = -1;

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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return num;
    }

    public static void main(String[] args) {
        Account account1 = new Account("Tom", "Fardell", "gaypenis");
        Account account2 = new Account("Matthew", "Hilton", "sexwithmen");
        System.out.println(account1.number);
        System.out.println(account2.number);
    }
}