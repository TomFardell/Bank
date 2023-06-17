package screens;

import main.*;
import javax.swing.*;
import java.awt.*;

public class AddAccountScreen extends Screen {

    private JLabel numberLabel, forenameLabel, surnameLabel, passwordLabel, badInputLabel,
            numberValue;
    private JTextField forenameField, surnameField, passwordField;
    private JButton createAccountButton, backButton;

    public AddAccountScreen(GUI gui) {
        super(gui);
    }

    @Override
    protected void setupComponents() {
        numberLabel = new JLabel("Number:");
        forenameLabel = new JLabel("Forename:");
        surnameLabel = new JLabel("Surname:");
        passwordLabel = new JLabel("Password:");
        badInputLabel = new JLabel();
        numberValue = new JLabel();

        forenameField = new JTextField(21);
        surnameField = new JTextField(21);
        passwordField = new JTextField(21);

        createAccountButton = new JButton("Create Account");
        backButton = new JButton("Back");

        createAccountButton.addActionListener(e -> createAccountButtonPressed());
        backButton.addActionListener(e -> backButtonPressed());

        setLabelAppearance5(numberLabel);
        setLabelAppearance5(forenameLabel);
        setLabelAppearance5(surnameLabel);
        setLabelAppearance5(passwordLabel);
        setLabelAppearance2(badInputLabel);
        setLabelAppearance3(numberValue);

        setTextFieldAppearance1(forenameField);
        setTextFieldAppearance1(surnameField);
        setTextFieldAppearance1(passwordField);

        setButtonAppearance2(createAccountButton);
        setButtonAppearance1(backButton);
    }

    @Override
    protected void setupPanel() {
        super.setupPanel();
        panel.setLayout(new GridBagLayout());

        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(4, 5, 4, 5);

        addGB(Box.createVerticalStrut(8), 1, 6);

        addGB(numberLabel, 1, 2);
        addGB(forenameLabel, 1, 3);
        addGB(surnameLabel, 1, 4);
        addGB(passwordLabel, 1, 5);
        addGB(badInputLabel, 1, 1, 2);
        addGB(numberValue, 2, 2);

        addGB(forenameField, 2, 3);
        addGB(surnameField, 2, 4);
        addGB(passwordField, 2, 5);

        addGB(createAccountButton, 1, 7, 2);
        addGB(backButton, 0, 0);
    }

    @Override
    public void refreshText() {
        super.refreshText();

        badInputLabel.setText(" ");
        numberValue.setText(Account.readNextNumber() + "");
        forenameField.setText("");
        surnameField.setText("");
        passwordField.setText("");
    }

    private String capitaliseString(String str) {
        if (str == null || str.length() <= 1) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() +
                str.substring(1).toLowerCase();
    }

    private void createAccountButtonPressed() {
        String forename = forenameField.getText();
        String surname = surnameField.getText();
        String password = passwordField.getText();

        if (password.contains(",")) {
            badInputLabel.setText("Password cannot cointain commas");
            return;
        }

        if (!(forename + surname).matches("[a-zA-Z]+")) {
            badInputLabel.setText("Names must be one word, no special characters");
            return;
        }

        if (password.isBlank()) {
            badInputLabel.setText("Cannot have an empty password");
            return;
        }

        forename = capitaliseString(forename);
        surname = capitaliseString(surname);

        badInputLabel.setText("Account created");
        gui.getBank().addAccount(new Account(forename, surname, password));
    }

    private void backButtonPressed() {
        gui.refreshBankScreen();
        gui.showScreen("bank");
    }
}