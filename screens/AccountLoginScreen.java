package screens;

import main.*;
import javax.swing.*;
import java.awt.*;

public class AccountLoginScreen extends Screen {

    private GridBagConstraints gbc;

    public AccountLoginScreen(GUI gui) {
        super(gui);
    }

    @Override
    protected void setupComponents() {
        labels.put("number", new JLabel("Number:"));
        labels.put("password", new JLabel("Password:"));
        labels.put("badLogin", new JLabel(""));

        textFields.put("number", new JTextField());
        textFields.put("password", new JTextField());

        buttons.put("login", new JButton("Login"));
        buttons.get("login").addActionListener(e -> loginButtonPressed());
        buttons.put("back", new JButton("Back"));
        buttons.get("back").addActionListener(e -> backButtonPressed());
    }

    @Override
    protected void setupPanel() {
        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setLayout(new GridBagLayout());

        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;

        addGB(buttons.get("back"), 0, 0);
        addGB(labels.get("badLogin"), 1, 1, 2);
        addGB(labels.get("number"), 1, 2);
        addGB(textFields.get("number"), 2, 2);
        addGB(labels.get("password"), 1, 3);
        addGB(textFields.get("password"), 2, 3);
        addGB(buttons.get("login"), 1, 4, 2);
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
