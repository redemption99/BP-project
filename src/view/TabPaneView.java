package view;

import javax.swing.*;
import observer.*;

import java.awt.*;

public class TabPaneView extends JPanel implements Subscriber {

    private JTabbedPane tabPane;

    public TabPaneView() {
        this.tabPane = new JTabbedPane();
        setLayout(new GridLayout());
        add(tabPane);
        setVisible(true);
    }

    public void addTab(EntityView ev) {

        for (int i = 0; i < tabPane.getTabCount(); i++)
            if (ev.getName().equals(tabPane.getComponentAt(i).getName())) {
                tabPane.setSelectedIndex(i);
                return;
            }

        tabPane.addTab(ev.getName(), ev);
        tabPane.setSelectedIndex(tabPane.getTabCount()-1);
    }

    @Override
    public void update(Notification notification) {

    }
}
