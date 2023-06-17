package screens;

import main.*;
import javax.swing.*;
import java.awt.*;

public class AccountScreen extends Screen {

    private JLabel detailsLabel, numberLabel, nameLabel, balanceLabel, numberValue, nameValue,
            balanceValue;
    private JButton backButton, depositButton, withdrawButton;

    public AccountScreen(GUI gui) {
        super(gui);
    }

    @Override
    protected void setupComponents() {
        detailsLabel = new JLabel("Details");
        numberLabel = new JLabel("Account number:");
        nameLabel = new JLabel("Name:");
        balanceLabel = new JLabel("Balance:");
        numberValue = new JLabel();
        nameValue = new JLabel();
        balanceValue = new JLabel();

        backButton = new JButton("Back");
        depositButton = new JButton("Deposit Funds");
        withdrawButton = new JButton("Withdraw Funds");

        backButton.addActionListener(e -> backButtonPressed());
        depositButton.addActionListener(e -> depositButtonPressed());
        withdrawButton.addActionListener(e -> withdrawButtonPressed());

        setLabelAppearance4(detailsLabel);
        setLabelAppearance5(numberLabel);
        setLabelAppearance5(nameLabel);
        setLabelAppearance5(balanceLabel);
        setLabelAppearance3(numberValue);
        setLabelAppearance3(nameValue);
        setLabelAppearance3(balanceValue);

        setButtonAppearance1(backButton);
        setButtonAppearance2(withdrawButton);
        setButtonAppearance2(depositButton);
    }

    @Override
    protected void setupPanel() {
        super.setupPanel();
        panel.setLayout(new GridBagLayout());

        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(2, 5, 2, 5);

        addGB(Box.createVerticalStrut(8), 0, 5);

        addGB(detailsLabel, 1, 1);
        addGB(numberLabel, 1, 2);
        addGB(nameLabel, 1, 3);
        addGB(balanceLabel, 1, 4);
        addGB(numberValue, 2, 2);
        addGB(nameValue, 2, 3);
        addGB(balanceValue, 2, 4);

        addGB(backButton, 0, 0);

        addGB(withdrawButton, 1, 6);
        addGB(depositButton, 2, 6);
    }

    @Override
    public void refreshText() {
        super.refreshText();

        numberValue.setText(gui.getSelectedAccount().getNumber() + "");
        nameValue.setText(gui.getSelectedAccount().getFullName());
        balanceValue.setText(gui.getSelectedAccount().getFormattedBalance());
    }

    private void backButtonPressed() {
        gui.showScreen("accountLogin");
    }

    private void depositButtonPressed() {
        gui.showScreen("deposit");
    }

    private void withdrawButtonPressed() {
        gui.showScreen("withdraw");
    }
}
