package screens;

import main.*;

public class DepositScreen extends MoneyScreen {

    public DepositScreen(GUI gui) {
        super(gui);
    }

    @Override
    protected String getActionText() {
        return "Deposit";
    }

    @Override
    protected void processAmount(double amount) {
        if (gui.getSelectedAccount().depositFunds(amount)) {
            badInputLabel.setText("Transaction processed");
        } else {
            badInputLabel.setText("Transaction unsuccessful");
        }
    }
}