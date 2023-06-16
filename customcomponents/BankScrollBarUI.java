package customcomponents;

import screens.Screen;
import java.awt.Dimension;
import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class BankScrollBarUI extends BasicScrollBarUI {

    @Override
    protected void configureScrollBarColors() {
        this.thumbColor = Screen.COLOR_PALETTE[2];
        this.trackColor = Screen.COLOR_PALETTE[0];
        this.trackHighlightColor = Screen.COLOR_PALETTE[0];
    }

    private JButton createEmptyButton() {
        JButton button = new JButton();
        // There is a tiny but incredibly frustrating misalignment at the bottom of the
        // scroll bar with (0, 0);
        Dimension zeroDimension = new Dimension(-1, -1);

        button.setPreferredSize(zeroDimension);
        button.setMinimumSize(zeroDimension);
        button.setMaximumSize(zeroDimension);

        return button;
    }

    @Override
    protected JButton createDecreaseButton(int orientation) {
        return createEmptyButton();
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
        return createEmptyButton();
    }
}
