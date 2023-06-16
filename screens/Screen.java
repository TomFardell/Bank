package screens;

import main.*;
import customcomponents.*;
import javax.swing.*;
import java.awt.*;

public abstract class Screen {
    public static final Color[] COLOR_PALETTE = new Color[] {
            new Color(237, 241, 214),
            new Color(157, 192, 139),
            new Color(96, 153, 102),
            new Color(64, 81, 59)
    };
    public static final Color TRANSPARENT = new Color(0, 0, 0, 0);

    protected static final Font DEFAULT_BUTTON_FONT = new Font("Helvetica", Font.BOLD, 20);
    protected static final Font SMALLER_BUTTON_FONT = new Font("Helvetica", Font.BOLD, 17);
    protected static final Font TITLE_BUTTON_FONT = new Font("Helvetica", Font.BOLD, 30);
    protected static final Font DEFAULT_LABEL_FONT = new Font("Helvetica", Font.BOLD, 15);
    protected static final Font PLAIN_LABEL_FONT = new Font("Helvetica", Font.PLAIN, 15);
    protected static final Font TITLE_LABEL_FONT = new Font("Helvetica", Font.BOLD, 25);
    protected static final Font DEFAULT_TEXT_FIELD_FONT = new Font("Helvetica", Font.BOLD, 15);

    protected GUI gui;
    protected JPanel panel;
    protected GridBagConstraints gbc;

    public Screen(GUI gui) {
        this.gui = gui;

        setupComponents();
        setupPanel();
    }

    public JPanel getPanel() {
        return panel;
    }

    // Needs to be called after setting the component's background color
    private void widenComponentBackground(JComponent component) {
        component.setBorder(BorderFactory.createMatteBorder(0, 4, 0, 4,
                component.getBackground()));
    }

    protected void setComponentAppearance(JComponent component, Font font, Color textColor,
            Color backgroundColor, boolean focusable) {
        component.setFont(font);
        component.setForeground(textColor);
        component.setBackground(backgroundColor);
        component.setFocusable(focusable);
    }

    protected void setButtonAppearance1(JButton button) {
        setComponentAppearance(button, DEFAULT_BUTTON_FONT, COLOR_PALETTE[3],
                COLOR_PALETTE[1], false);
        widenComponentBackground(button);
    }

    protected void setButtonAppearance2(JButton button) {
        setComponentAppearance(button, SMALLER_BUTTON_FONT, COLOR_PALETTE[3],
                COLOR_PALETTE[1], false);
        widenComponentBackground(button);
    }

    protected void setButtonAppearance3(JButton button) {
        setComponentAppearance(button, TITLE_BUTTON_FONT, COLOR_PALETTE[3], COLOR_PALETTE[1],
                false);
        button.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10,
                button.getBackground()));
    }

    protected void setLabelAppearance1(JLabel label) {
        label.setOpaque(false);
        setComponentAppearance(label, DEFAULT_LABEL_FONT, COLOR_PALETTE[3], TRANSPARENT,
                false);
    }

    protected void setLabelAppearance2(JLabel label) {
        label.setOpaque(false);
        setComponentAppearance(label, DEFAULT_LABEL_FONT, COLOR_PALETTE[2], TRANSPARENT,
                false);
    }

    protected void setLabelAppearance3(JLabel label) {
        label.setOpaque(true);
        setComponentAppearance(label, DEFAULT_LABEL_FONT, COLOR_PALETTE[3], COLOR_PALETTE[1],
                false);
        widenComponentBackground(label);
    }

    protected void setLabelAppearance4(JLabel label) {
        label.setOpaque(false);
        setComponentAppearance(label, TITLE_LABEL_FONT, COLOR_PALETTE[3], TRANSPARENT,
                false);
    }

    protected void setLabelAppearance5(JLabel label) {
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        setLabelAppearance1(label);
    }

    protected void setLabelAppearance6(JLabel label) {
        label.setOpaque(true);
        setComponentAppearance(label, PLAIN_LABEL_FONT, COLOR_PALETTE[3], COLOR_PALETTE[1],
                false);
        widenComponentBackground(label);
    }

    protected void setTextFieldAppearance1(JTextField field) {
        field.setBorder(BorderFactory.createEmptyBorder());
        setComponentAppearance(field, DEFAULT_TEXT_FIELD_FONT, COLOR_PALETTE[3],
                COLOR_PALETTE[1], true);
        widenComponentBackground(field);
    }

    protected void setTextFieldAppearance2(JTextField field) {
        field.setBorder(BorderFactory.createEmptyBorder());
        setComponentAppearance(field, DEFAULT_TEXT_FIELD_FONT, COLOR_PALETTE[3],
                COLOR_PALETTE[1], false);
        widenComponentBackground(field);
    }

    protected void setScrollBarAppearance1(JScrollBar scrollBar) {
        scrollBar.setUI(new BankScrollBarUI());
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

    // Refreshes text fields. Default behaviour for a Screen with no text fields
    public void refreshText() {
        return;
    }

    protected void addGB(Component component, int x, int y, int width, int height, JPanel panel) {
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = width;
        gbc.gridheight = height;
        panel.add(component, gbc);
    }

    protected void addGB(Component component, int x, int y, int width, int height) {
        addGB(component, x, y, width, height, panel);
    }

    protected void addGB(Component component, int x, int y, int width) {
        addGB(component, x, y, width, 1);
    }

    // Default width of 1
    protected void addGB(Component component, int x, int y) {
        addGB(component, x, y, 1);
    }
}