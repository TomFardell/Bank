package screens;

import main.*;
import javax.swing.*;
import java.awt.*;

public class SelectionScreen extends Screen {

    public SelectionScreen(GUI gui) {
        super(gui);
    }

    @Override
    protected void setupComponents() {
        buttons.put("account", new JButton("Account Login"));
        buttons.get("account").addActionListener(e -> accountButtonPressed());
        buttons.put("bank", new JButton("Bank View"));
        buttons.get("bank").addActionListener(e -> bankButtonPressed());
    }

    @Override
    protected void setupPanel() {
        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setLayout(new GridLayout(2, 1));

        panel.add(buttons.get("account"));
        panel.add(buttons.get("bank"));
    }

    private void accountButtonPressed() {
        gui.showScreen("accountLogin");
    }

    private void bankButtonPressed() {
        gui.showScreen("bank");
    }
}
