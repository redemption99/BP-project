package gui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public static MainFrame instance = null;

    public MainFrame() {

    }

    public static MainFrame getInstance() {
        if (instance == null) {
            instance = new MainFrame();
            instance.initialize();
        }
        return instance;
    }

    private void initialize() {
        setTitle("Baze Podataka - Projekat");
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
