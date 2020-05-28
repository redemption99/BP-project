package gui;

import javax.swing.*;

public class Message {

    JFrame jf;

    public Message(String message) {
        jf = new JFrame();
        JOptionPane.showMessageDialog(jf, message);
    }

}
