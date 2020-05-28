package view;

import javax.swing.*;

import gui.QueryToolbar;
import observer.*;

import java.awt.*;

public class TabPaneView extends JPanel implements Subscriber {

    private JTabbedPane tabPane;
    private QueryToolbar toolbar;

    public TabPaneView() {
        this.tabPane = new JTabbedPane();
        this.toolbar = new QueryToolbar();
        toolbar.setTabPane(tabPane);
        setLayout(new BorderLayout());
        add(toolbar, BorderLayout.SOUTH);
        add(tabPane, BorderLayout.CENTER);
        setVisible(true);
    }

    public void addTab(EntityView ev) {

        toolbar.setVisible(true);

        for (int i = 0; i < tabPane.getTabCount(); i++)
            if (ev.getName().equals(tabPane.getComponentAt(i).getName())) {
                tabPane.setSelectedIndex(i);
                return;
            }

        tabPane.addTab(ev.getName(), ev);
        tabPane.setSelectedIndex(tabPane.getTabCount()-1);
    }

    public JTabbedPane getTabPane() {
        return tabPane;
    }

    @Override
    public void update(Notification notification) {

    }
}
