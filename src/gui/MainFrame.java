package gui;

import controller.TabChangeListener;
import database.Database;
import database.DatabaseImplementation;
import database.MSSQLRepository;
import tree.DatabaseTree;
import tree.DatabaseTreeModel;
import view.*;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private static MainFrame instance = null;

    private JScrollPane scrollPane;

    private Database db;
    private DatabaseTree tree;
    private DatabaseTreeModel treeModel;
    private JSplitPane splitPane, splitPaneR;
    private TabPaneView topTp, botTp;

    private MainFrame() {
        this.db = new DatabaseImplementation(new MSSQLRepository());
    }

    public static MainFrame getInstance() {
        if (instance == null) {
            instance = new MainFrame();
            instance.initialize();
        }
        return instance;
    }

    private void initialize() {

        treeModel = new DatabaseTreeModel(db.loadResource());
        tree = new DatabaseTree();
        tree.setModel(treeModel);

        scrollPane = new JScrollPane();
        scrollPane.setViewportView(tree);
        scrollPane.setMinimumSize(new Dimension(200, 150));

        topTp = new TabPaneView();
        topTp.getTabPane().addChangeListener(new TabChangeListener());
        botTp = new TabPaneView();

        splitPaneR = new JSplitPane(JSplitPane.VERTICAL_SPLIT, topTp, botTp);
        splitPaneR.setDividerLocation(720 / 2);

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollPane, splitPaneR);

        setTitle("Baze Podataka - Projekat - Tim 14");
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(splitPane);
        setVisible(true);
    }

    public Database getDb() {
        return db;
    }

    public void setDb(Database db) {
        this.db = db;
    }

    public TabPaneView getTopTp() {
        return topTp;
    }

    public void setTopTp(TabPaneView topTp) {
        this.topTp = topTp;
    }

    public TabPaneView getBotTp() {
        return botTp;
    }

    public void setBotTp(TabPaneView botTp) {
        this.botTp = botTp;
    }

}
