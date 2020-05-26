package gui;

import database.Database;
import database.DatabaseImplementation;
import database.MSSQLRepository;
import database.Repository;
import tree.DatabaseTree;
import tree.DatabaseTreeModel;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private static MainFrame instance = null;

    private JScrollPane scrollPane;
    private Database db;
    private DatabaseTree tree;
    private DatabaseTreeModel treeModel;
    private JSplitPane splitPane;

    public MainFrame() {
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

        scrollPane = new JScrollPane();
        treeModel = new DatabaseTreeModel(db.loadResource());
        tree = new DatabaseTree();
        tree.setModel(treeModel);
        scrollPane.setViewportView(tree);
        scrollPane.setMinimumSize(new Dimension(200, 150));
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollPane, new JPanel());

        setTitle("Baze Podataka - Projekat");
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(splitPane);
    }
}
