package screens;

import main.*;
import javax.swing.*;
import java.awt.*;

public abstract class Screen {
    protected static final Color[] COLOR_PALETTE = new Color[] {
            new Color(237, 241, 214),
            new Color(157, 192, 139),
            new Color(96, 153, 102),
            new Color(64, 81, 59)
    };
    protected static final Color TRANSPARENT = new Color(0, 0, 0, 0);

    protected static final Font DEFAULT_BUTTON_FONT = new Font("Helvetica", Font.BOLD, 20);
    protected static final Font DEFAULT_LABEL_FONT = new Font("Helvetica", Font.BOLD, 15);
    protected static final Font DEFAULT_TEXT_FIELD_FONT = new Font("Helvetica", Font.BOLD, 15);

    protected GUI gui;
    protected JPanel panel;

    public Screen(GUI gui) {
        this.gui = gui;

        setupComponents();
        setupPanel();
    }

    public JPanel getPanel() {
        return panel;
    }

    protected void setComponentAppearance(JComponent component, Font font, Color textColor,
            Color backgroundColor, boolean focusable) {
        component.setFont(font);
        component.setForeground(textColor);
        component.setBackground(backgroundColor);
        component.setFocusable(focusable);
    }

    protected void setButtonAppearance1(JButton button) {
        button.setBorder(BorderFactory.createEmptyBorder());
        setComponentAppearance(button, DEFAULT_BUTTON_FONT, COLOR_PALETTE[3],
                COLOR_PALETTE[1], false);
    }

    protected void setLabelAppearance1(JLabel label) {
        setComponentAppearance(label, DEFAULT_LABEL_FONT, COLOR_PALETTE[3], TRANSPARENT,
                false);
    }

    protected void setTextFieldAppearance1(JTextField field) {
        field.setBorder(BorderFactory.createEmptyBorder());
        setComponentAppearance(field, DEFAULT_TEXT_FIELD_FONT, COLOR_PALETTE[3],
                COLOR_PALETTE[1], true);
    }

    // Sets up buttons and other components. Should set up their appearance and any
    // functionality such as ActionListeners
    protected abstract void setupComponents();

    // Sets up the panel. Should arrange the components on the panel
    protected void setupPanel() {
        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(COLOR_PALETTE[0]);
    }
}