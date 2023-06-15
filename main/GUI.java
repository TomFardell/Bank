package main;

import screens.*;
import javax.swing.*;
import java.awt.*;

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

        AccountScreen as = (AccountScreen) accountScreen;
        as.refreshTextFields();
    }

    // Creates a card layout containing the panel from each type of screen
    private void setupCardPanel() {
        cardPanel = new JPanel(new CardLayout());

        selectionScreen = new SelectionScreen(this);
        accountLoginScreen = new AccountLoginScreen(this);
        accountScreen = new AccountScreen(this);

        cardPanel.add("selection", selectionScreen.getPanel());
        cardPanel.add("accountLogin", accountLoginScreen.getPanel());
        cardPanel.add("account", accountScreen.getPanel());
    }

    // Shows the Screen with the inputted name
    public void showScreen(String name) {
        CardLayout cl = (CardLayout) cardPanel.getLayout();
        cl.show(cardPanel, name);
    }

    public static void main(String[] args) {
        Bank bank = new Bank("accounts.txt");
        new GUI(bank);
    }
}
