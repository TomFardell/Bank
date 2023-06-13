package screens;

import main.*;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public abstract class Screen {
    protected GUI gui;

    protected JPanel panel;
    protected HashMap<String, JButton> buttons;
    protected HashMap<String, JTextField> textFields;
    protected HashMap<String, JLabel> labels;
    protected HashMap<String, Component> components;

    public Screen(GUI gui) {
        this.gui = gui;

        buttons = new HashMap<String, JButton>();
        textFields = new HashMap<String, JTextField>();
        labels = new HashMap<String, JLabel>();
        components = new HashMap<String, Component>();

        setupComponents();
        setupPanel();
    }

    public JPanel getPanel() {
        return panel;
    }

    // Sets up buttons and other components. Should set up their appearance and any
    // functionality such as ActionListeners
    protected abstract void setupComponents();

    // Sets up the panel. Should arrange the components on the panel
    protected abstract void setupPanel();
}