package screens;

import main.*;

public class WithdrawScreen extends MoneyScreen {

    public WithdrawScreen(GUI gui) {
        super(gui);
    }

    @Override
    protected String getActionText() {
        return "Withdraw";
    }

    @Override
    protected void processAmount(double amount) {
        if (gui.getSelectedAccount().withdrawFunds(amount)) {
            badInputLabel.setText("Transaction processed");
        } else {
            badInputLabel.setText("Transaciton unsuccessful");
        }
    }
}