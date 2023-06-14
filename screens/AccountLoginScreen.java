package screens;

import main.*;
import javax.swing.*;
import java.awt.*;

public class AccountLoginScreen extends Screen {

    private GridBagConstraints gbc;
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

        setLabelAppearance1(badLoginLabel);
        setTextFieldAppearance1(numberField);
        setTextFieldAppearance1(passwordField);

        setButtonAppearance1(loginButton);
        setButtonAppearance1(backButton);

    }

    // Given a component and x, y and width, places the component on the
    // GridBagLayout at this position
    private void addGB(Component component, int x, int y, int width) {
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = width;
        panel.add(component, gbc);
    }

    // Default width of 1
    private void addGB(Component component, int x, int y) {
        addGB(component, x, y, 1);
    }

    private void loginButtonPressed() {
        // TODO actually add login system here
        gui.showScreen("account");
    }

    private void backButtonPressed() {
        gui.showScreen("selection");
    }
}
