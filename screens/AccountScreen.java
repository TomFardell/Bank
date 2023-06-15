package screens;

import main.*;
import javax.swing.*;
import java.awt.*;

public class AccountScreen extends Screen {

    private JLabel detailsLabel, numberLabel, nameLabel, balanceLabel;
    private JTextField numberField, nameField, balanceField;
    private JButton backButton, depositButton, withdrawButton;

    public AccountScreen(GUI gui) {
        super(gui);
    }

    @Override
    protected void setupComponents() {
        detailsLabel = new JLabel("Details:");
        numberLabel = new JLabel("Account number:");
        nameLabel = new JLabel("Name:");
        balanceLabel = new JLabel("Balance:");

        numberField = new JTextField(10);
        nameField = new JTextField(15);
        balanceField = new JTextField(10);

        backButton = new JButton("Back");
        depositButton = new JButton("Deposit Funds");
        withdrawButton = new JButton("Withdraw Funds");

        backButton.addActionListener(e -> backButtonPressed());
        depositButton.addActionListener(e -> depositButtonPressed());
        withdrawButton.addActionListener(e -> withdrawButtonPressed());
    }

    @Override
    protected void setupPanel() {
        super.setupPanel();
        panel.setLayout(new GridBagLayout());

        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10);

        addGB(detailsLabel, 1, 1);
        addGB(numberLabel, 1, 2);
        addGB(nameLabel, 1, 3);
        addGB(balanceLabel, 1, 4);

        addGB(numberField, 2, 2);
        addGB(nameField, 2, 3);
        addGB(balanceField, 2, 4);

        addGB(backButton, 0, 0);
        addGB(withdrawButton, 1, 5);
        addGB(depositButton, 2, 5);

        setLabelAppearance1(detailsLabel);
        setLabelAppearance1(numberLabel);
        setLabelAppearance1(nameLabel);
        setLabelAppearance1(balanceLabel);

        setTextFieldAppearance2(numberField);
        setTextFieldAppearance2(nameField);
        setTextFieldAppearance2(balanceField);

        setButtonAppearance1(backButton);
        setButtonAppearance1(withdrawButton);
        setButtonAppearance1(depositButton);
    }

    @Override
    public void refreshText() {
        numberField.setText(gui.getSelectedAccount().getNumber() + "");
        nameField.setText(gui.getSelectedAccount().getFullName());
        balanceField.setText(gui.getSelectedAccount().getFormattedBalance());
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
