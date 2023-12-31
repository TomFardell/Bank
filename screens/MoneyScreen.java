package screens;

import main.*;
import javax.swing.*;
import java.awt.*;

public abstract class MoneyScreen extends Screen {

    private JLabel currencyLabel;
    private JTextField amountField;
    private JButton backButton, actionButton;

    protected JLabel badInputLabel;

    public MoneyScreen(GUI gui) {
        super(gui);
    }

    @Override
    protected void setupComponents() {
        currencyLabel = new JLabel("£");
        badInputLabel = new JLabel();

        amountField = new JTextField(16);

        backButton = new JButton("Back");
        actionButton = new JButton(getActionText());

        backButton.addActionListener(e -> backButtonPressed());
        actionButton.addActionListener(e -> actionButtonPressed());

        setLabelAppearance5(currencyLabel);
        setLabelAppearance2(badInputLabel);

        setTextFieldAppearance1(amountField);

        setButtonAppearance1(backButton);
        setButtonAppearance2(actionButton);
    }

    @Override
    protected void setupPanel() {
        super.setupPanel();
        panel.setLayout(new GridBagLayout());

        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 2, 10, 2);

        addGB(currencyLabel, 1, 2);
        addGB(badInputLabel, 1, 1, 2);

        addGB(amountField, 2, 2);

        addGB(backButton, 0, 0);
        addGB(actionButton, 1, 3, 2);
    }

    @Override
    public void refreshText() {
        super.refreshText();

        badInputLabel.setText(" ");
        amountField.setText("");
    }

    protected abstract String getActionText();

    private void backButtonPressed() {
        gui.showScreen("account");
    }

    private void actionButtonPressed() {
        double amount;

        try {
            amount = Double.parseDouble(amountField.getText());
        } catch (NumberFormatException e) {
            badInputLabel.setText("Input must be a valid number");
            return;
        }

        if (amount < 0) {
            badInputLabel.setText("Amount must be positive");
            return;
        }

        processAmount(amount);
    }

    protected abstract void processAmount(double amount);
}
