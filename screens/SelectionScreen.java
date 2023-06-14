package screens;

import main.*;
import javax.swing.*;
import java.awt.*;

public class SelectionScreen extends Screen {

    private JButton accountButton, bankButton;

    public SelectionScreen(GUI gui) {
        super(gui);
    }

    @Override
    protected void setupComponents() {
        accountButton = new JButton("Account Login");
        bankButton = new JButton("Bank View");

        accountButton.addActionListener(e -> accountButtonPressed());
        bankButton.addActionListener(e -> bankButtonPressed());

        setButtonAppearance1(accountButton);
        setButtonAppearance1(bankButton);
    }

    @Override
    protected void setupPanel() {
        super.setupPanel();
        panel.setLayout(new GridLayout(2, 1, 0, 10));
        panel.add(accountButton);
        panel.add(bankButton);
    }

    private void accountButtonPressed() {
        gui.showScreen("accountLogin");
    }

    private void bankButtonPressed() {
        gui.showScreen("bank");
    }
}
