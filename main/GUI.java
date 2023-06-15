package main;

import screens.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI {

    private Bank bank;
    private Account selectedAccount;

    private JFrame frame;
    private JPanel cardPanel;
    private Screen selectionScreen, accountLoginScreen, accountScreen, depositScreen,
            withdrawScreen, bankScreen;

    public GUI(Bank bank) {
        this.bank = bank;
        // Just so the selected account isn't null. Shouldn't ever be displayed in the
        // account screen
        selectedAccount = new Account("0,Dummy,Account,0,password");

        frame = new JFrame();

        setupCardPanel();

        frame.add(cardPanel, BorderLayout.CENTER);

        frame.setTitle("Bank");
        frame.setPreferredSize(new Dimension(600, 400));
        frame.setLocation(400, 200);

        // Saves the accounts when the window closes
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                bank.writeAccountsToFile();
                System.exit(0);
            }
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public Bank getBank() {
        return bank;
    }

    public Account getSelectedAccount() {
        return selectedAccount;
    }

    public void setSelectedAccount(Account account) {
        selectedAccount = account;
    }

    // Creates a card layout containing the panel from each type of screen
    private void setupCardPanel() {
        cardPanel = new JPanel(new CardLayout());

        selectionScreen = new SelectionScreen(this);
        accountLoginScreen = new AccountLoginScreen(this);
        accountScreen = new AccountScreen(this);
        withdrawScreen = new WithdrawScreen(this);
        depositScreen = new DepositScreen(this);

        cardPanel.add("selection", selectionScreen.getPanel());
        cardPanel.add("accountLogin", accountLoginScreen.getPanel());
        cardPanel.add("account", accountScreen.getPanel());
        cardPanel.add("withdraw", withdrawScreen.getPanel());
        cardPanel.add("deposit", depositScreen.getPanel());
    }

    // Shows the Screen with the inputted name
    public void showScreen(String name) {
        CardLayout cl = (CardLayout) cardPanel.getLayout();
        cl.show(cardPanel, name);

        selectionScreen.refreshText();
        accountLoginScreen.refreshText();
        accountScreen.refreshText();
        withdrawScreen.refreshText();
        depositScreen.refreshText();
    }

    public static void main(String[] args) {
        Bank bank = new Bank("accounts.txt");
        new GUI(bank);
    }
}
