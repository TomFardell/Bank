package main;

import screens.*;
import javax.swing.*;
import java.awt.*;

public class GUI {

    private Bank bank;

    private JFrame frame;
    private JPanel cardPanel;
    private Screen selectionScreen, accountLoginScreen, accountScreen, depositScreen,
            withdrawScreen, bankScreen;

    public GUI(Bank bank) {
        this.bank = bank;

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

    // Creates a card layout containing the panel from each type of screen
    private void setupCardPanel() {
        cardPanel = new JPanel(new CardLayout());

        selectionScreen = new SelectionScreen(this);
        accountLoginScreen = new AccountLoginScreen(this);

        cardPanel.add("selection", selectionScreen.getPanel());
        cardPanel.add("accountLogin", accountLoginScreen.getPanel());
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
