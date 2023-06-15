package screens;

import main.*;
import javax.swing.*;
import java.awt.*;

public class AccountLoginScreen extends Screen {

    private JLabel numberLabel, passwordLabel, badLoginLabel;
    private JTextField numberField, passwordField;
    private JButton loginButton, backButton;

    public AccountLoginScreen(GUI gui) {
        super(gui);
    }

    @Override
    protected void setupComponents() {
        numberLabel = new JLabel("Number:");
        passwordLabel = new JLabel("Password:");
        badLoginLabel = new JLabel("");

        numberField = new JTextField(10);
        passwordField = new JPasswordField(10);

        loginButton = new JButton("Login");
        backButton = new JButton("Back");

        loginButton.addActionListener(e -> loginButtonPressed());
        backButton.addActionListener(e -> backButtonPressed());
    }

    @Override
    protected void setupPanel() {
        super.setupPanel();
        panel.setLayout(new GridBagLayout());

        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10);

        addGB(numberLabel, 1, 2);
        addGB(passwordLabel, 1, 3);

        addGB(badLoginLabel, 1, 1, 2);
        addGB(numberField, 2, 2);
        addGB(passwordField, 2, 3);

        addGB(loginButton, 1, 4, 2);
        addGB(backButton, 0, 0);

        setLabelAppearance1(numberLabel);
        setLabelAppearance1(passwordLabel);
        setLabelAppearance2(badLoginLabel);

        setTextFieldAppearance1(numberField);
        setTextFieldAppearance1(passwordField);

        setButtonAppearance1(loginButton);
        setButtonAppearance1(backButton);

    }

    @Override
    public void refreshText() {
        badLoginLabel.setText("");
        numberField.setText("");
        passwordField.setText("");
    }

    private void loginButtonPressed() {
        int number;

        try {
            number = Integer.parseInt(numberField.getText());
        } catch (NumberFormatException e) {
            badLoginLabel.setText("Number format incorrect");
            return;
        }

        String password = passwordField.getText();
        Account account = gui.getBank().getAccount(number);

        if (account == null) {
            badLoginLabel.setText("No such account exists");
            return;
        }
        if (!account.isPassword(password)) {
            badLoginLabel.setText("Incorrect password");
            return;
        }

        gui.setSelectedAccount(account);
        gui.showScreen("account");
    }

    private void backButtonPressed() {
        gui.showScreen("selection");
    }
}
