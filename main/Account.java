package main;

import java.io.*;
import java.util.Scanner;

public class Account {

    public static final int NUMBER_OF_FIELDS = 4;

    private static final String ACCOUNT_NUMBER_FILE_NAME = "number.txt";

    private static boolean showPasswordsInDisplay = false;

    private int number;
    private String forename;
    private String surname;
    private double balance;
    private String password;

    public Account(String forename, String surname, String password, double balance) {
        this.number = getNumberWithIncrement();
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
    // number,forename,surname,balance,password
    public String getAsCSVString() {
        return String.format("%d,%s,%s,%.2f,%s", number, forename, surname, balance,
                password);
    }

    public String toString() {
        return String.format("%d: %s %s, %s, %s", number, forename, surname,
                getFormattedBalance(), password);
    }

    public String getFormattedBalance() {
        return String.format("£%,.2f", balance);
    }

    // Method to help display information about accounts
    public String getTextFromCode(int code) {
        if (code == 0) {
            return number + "";
        }
        if (code == 1) {
            return getFullName();
        }
        if (code == 2) {
            return getFormattedBalance();
        }
        if (code == 3) {
            if (showPasswordsInDisplay) {
                return password;
            }
            return "********";
        }
        return "";
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

    public boolean isPassword(String password) {
        return password.equals(this.password);
    }

    public double getBalance() {
        return balance;
    }

    public static void togglePasswordsInDisplay() {
        showPasswordsInDisplay = !showPasswordsInDisplay;
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

    // Reads the account number from the file, returning -1 for error
    public static int readNextNumber() {
        int number = -1;

        try {
            File file = new File(ACCOUNT_NUMBER_FILE_NAME);
            if (file.createNewFile()) {
                number = 0;
            } else {
                Scanner scanner = new Scanner(file);
                number = Integer.parseInt(scanner.nextLine());
                scanner.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return number;
    }

    // Reads the account number and writes the incremented value to the file,
    // returning -1 for error
    private int getNumberWithIncrement() {
        int num = readNextNumber();

        if (num >= 0) {
            try {
                PrintWriter writer = new PrintWriter(ACCOUNT_NUMBER_FILE_NAME);
                writer.print(num + 1);
                writer.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return num;
    }
}
