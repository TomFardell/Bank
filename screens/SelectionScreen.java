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

        setButtonAppearance3(accountButton);
        setButtonAppearance3(bankButton);
    }

    @Override
    protected void setupPanel() {
        super.setupPanel();
        panel.setLayout(new GridBagLayout());

        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 0, 10, 0);

        addGB(accountButton, 0, 0);
        addGB(bankButton, 0, 1);
    }

    private void accountButtonPressed() {
        gui.showScreen("accountLogin");
    }

    private void bankButtonPressed() {
        gui.showScreen("bank");
    }
}
