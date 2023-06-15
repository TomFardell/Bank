package screens;

import main.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class BankScreen extends Screen {

    private static final Dimension SCROLL_PANE_DIMENSION = new Dimension(450, 150);

    private JPanel scrollPanePanel;
    private JScrollPane scrollPane;

    private JLabel accountsLabel, detailsLabel, numberOfAccountsLabel, totalBalanceLabel,
            numberOfAccountsValue, totalBalanceValue;
    private ArrayList<JLabel[]> accountDataEntries;
    private JButton backButton;

    public BankScreen(GUI gui) {
        super(gui);
    }

    @Override
    protected void setupComponents() {
        accountsLabel = new JLabel("Accounts:");
        detailsLabel = new JLabel("Details:");
        numberOfAccountsLabel = new JLabel("Number of accounts:");
        totalBalanceLabel = new JLabel("Total balance:");
        numberOfAccountsValue = new JLabel();
        totalBalanceValue = new JLabel();

        // Initialises the text fields for the account data
        accountDataEntries = new ArrayList<JLabel[]>();
        for (int i = 0; i < gui.getBank().getNumberOfAccounts(); i++) {
            accountDataEntries.add(new JLabel[Account.NUMBER_OF_FIELDS]);
            for (int j = 0; j < Account.NUMBER_OF_FIELDS; j++) {
                accountDataEntries.get(i)[j] = new JLabel();
            }
        }

        backButton = new JButton("Back");

        backButton.addActionListener(e -> backButtonPressed());
    }

    @Override
    protected void setupPanel() {
        super.setupPanel();
        panel.setLayout(new GridBagLayout());

        scrollPanePanel = new JPanel();
        scrollPanePanel.setLayout(new GridBagLayout());
        scrollPanePanel.setBackground(COLOR_PALETTE[1]);

        // GridBagConstraints for data pane
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 4, 0, 4);

        // Places the account data text fields on the scroll pane
        for (int i = 0; i < accountDataEntries.size(); i++) {
            for (int j = 0; j < Account.NUMBER_OF_FIELDS; j++) {
                JLabel entry = accountDataEntries.get(i)[j];

                gbc.gridx = j;
                gbc.gridy = i;
                scrollPanePanel.add(entry, gbc);

                setLabelAppearance3(entry);
            }
        }

        scrollPane = new JScrollPane(scrollPanePanel);
        scrollPane.setMinimumSize(SCROLL_PANE_DIMENSION);
        scrollPane.setMaximumSize(SCROLL_PANE_DIMENSION);
        scrollPane.setPreferredSize(SCROLL_PANE_DIMENSION);

        // GridBagConstraints for whole screen
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(2, 10, 2, 10);

        addGB(accountsLabel, 1, 1);
        addGB(detailsLabel, 5, 1);
        addGB(numberOfAccountsLabel, Account.NUMBER_OF_FIELDS + 1, 2);
        addGB(totalBalanceLabel, Account.NUMBER_OF_FIELDS + 1, 3);

        addGB(scrollPane, 1, 2, Account.NUMBER_OF_FIELDS, 5);

        addGB(numberOfAccountsValue, Account.NUMBER_OF_FIELDS + 2, 2);
        addGB(totalBalanceValue, Account.NUMBER_OF_FIELDS + 2, 3);

        addGB(backButton, 0, 0);

        setLabelAppearance1(accountsLabel);
        setLabelAppearance1(detailsLabel);
        setLabelAppearance1(numberOfAccountsLabel);
        setLabelAppearance1(totalBalanceLabel);

        setLabelAppearance3(numberOfAccountsValue);
        setLabelAppearance3(totalBalanceValue);

        setButtonAppearance1(backButton);
    }

    @Override
    public void refreshText() {
        // Refreshes the text of the account data fields
        for (int i = 0; i < accountDataEntries.size(); i++) {
            for (int j = 0; j < Account.NUMBER_OF_FIELDS; j++) {
                accountDataEntries.get(i)[j].setText(
                        gui.getBank().getAccountOfIndex(i).getTextFromCode(j));
            }
        }

        numberOfAccountsValue.setText(gui.getBank().getNumberOfAccounts() + "");
        totalBalanceValue.setText(String.format(
                "£%.2f", gui.getBank().getTotalBalance()));
    }

    private void backButtonPressed() {
        gui.showScreen("selection");
    }
}
