package screens;

import main.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.*;

public class BankScreen extends Screen {

    private static final Dimension SCROLL_PANE_DIMENSION = new Dimension(450, 150);
    private static final int MAX_DISPLAY_LENGTH = 17;

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
        accountsLabel = new JLabel("Accounts");
        detailsLabel = new JLabel("Details");
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

        // Labels for titles of columns in the data pane
        JLabel[] titleLabels = new JLabel[] { new JLabel("Number"), new JLabel("Name"),
                new JLabel("Balance"), new JLabel("Password") };

        // Places the title labels
        for (int i = 0; i < titleLabels.length; i++) {
            addGB(titleLabels[i], i, 0, 1, 1, scrollPanePanel);

            setLabelAppearance3(titleLabels[i]);
        }

        // Places the account data text fields on the scroll pane
        for (int i = 0; i < accountDataEntries.size(); i++) {
            for (int j = 0; j < Account.NUMBER_OF_FIELDS; j++) {
                JLabel entry = accountDataEntries.get(i)[j];

                addGB(entry, j, i + 1, 1, 1, scrollPanePanel);

                setLabelAppearance6(entry);
            }
        }

        scrollPane = new JScrollPane(scrollPanePanel);
        scrollPane.setMinimumSize(SCROLL_PANE_DIMENSION);
        scrollPane.setMaximumSize(SCROLL_PANE_DIMENSION);
        scrollPane.setPreferredSize(SCROLL_PANE_DIMENSION);
        scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        setScrollBarAppearance1(scrollPane.getVerticalScrollBar());

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

        setLabelAppearance4(accountsLabel);
        setLabelAppearance4(detailsLabel);
        setLabelAppearance5(numberOfAccountsLabel);
        setLabelAppearance5(totalBalanceLabel);
        setLabelAppearance3(numberOfAccountsValue);
        setLabelAppearance3(totalBalanceValue);

        setButtonAppearance1(backButton);
    }

    @Override
    public void refreshText() {
        // Refreshes the text of the account data fields
        for (int i = 0; i < accountDataEntries.size(); i++) {
            for (int j = 0; j < Account.NUMBER_OF_FIELDS; j++) {
                // Stops entries from being too large requiring a horizontal scroll
                String text = gui.getBank().getAccountOfIndex(i).getTextFromCode(j);
                if (text.length() > MAX_DISPLAY_LENGTH) {
                    text = text.substring(0, MAX_DISPLAY_LENGTH - 3) + "...";
                }
                accountDataEntries.get(i)[j].setText(text);
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
