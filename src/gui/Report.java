package gui;

import app.Main;
import resource.enums.AttributeType;
import resource.implementation.Attribute;
import resource.implementation.Entity;
import view.EntityView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Report extends JDialog {

    private JRadioButton countRadioButton = new JRadioButton("COUNT");
    private JRadioButton avgRadioButton = new JRadioButton("AVERAGE");
    private JList reportList;
    private JList groupByList;
    private JButton reportButton = new JButton("REPORT");
    private JButton selectButton = new JButton("Select");
    private JTextArea selectedColumnsArea = new JTextArea("Columns to group by will be displayed here!");

    private EntityView ev;
    private Entity entity;

    private List<String> groupByColumns = new ArrayList<>();

    private ButtonGroup group = new ButtonGroup();

    private JScrollPane sp1 = new JScrollPane();
    private JScrollPane sp2 = new JScrollPane();

    public Report(EntityView ev) {
        this.ev = ev;
        this.entity = ev.getEntity();

        DefaultListModel countList = new DefaultListModel();
        DefaultListModel avgList = new DefaultListModel();

        for (int i = 0; i < entity.getChildCount(); i++) {
            AttributeType type = ((Attribute)entity.getChildAt(i)).getType();
            if (type == AttributeType.NUMERIC || type == AttributeType.INT || type == AttributeType.FLOAT || type == AttributeType.DECIMAL)
                avgList.addElement(entity.getChildAt(i).toString());
            countList.addElement(entity.getChildAt(i).toString());
        }

        reportList = new JList(countList);
        groupByList = new JList(countList);
        reportList.setLayoutOrientation(JList.VERTICAL);
        groupByList.setLayoutOrientation(JList.VERTICAL);
        reportList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        groupByList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        sp1.setViewportView(reportList);
        sp2.setViewportView(groupByList);

        group.add(countRadioButton);
        group.add(avgRadioButton);
        countRadioButton.setSelected(true);

        selectedColumnsArea.setEditable(false);

        countRadioButton.addActionListener(e -> {
            reportList.setModel(countList);
        });

        avgRadioButton.addActionListener(e -> {
            reportList.setModel(avgList);
        });

        selectButton.addActionListener(e -> {
            if (groupByList.getSelectedValue() != null) {
                if (selectedColumnsArea.getText().startsWith("Columns to")) selectedColumnsArea.setText("");

                if (!groupByColumns.contains(groupByList.getSelectedValue().toString())) {
                    selectedColumnsArea.setText(selectedColumnsArea.getText() + " " + groupByList.getSelectedValue().toString());
                    groupByColumns.add(groupByList.getSelectedValue().toString());
                }
            }
        });

        reportButton.addActionListener(e -> {
            if (reportList.getSelectedValue() == null) {
                new Message("Please select a column to make your report on");
                return;
            }

            if (groupByColumns.size() == 0) {
                new Message("Please specify the columns to group by");
                return;
            }

            ev.getTableModel().setRows(MainFrame.getInstance().getDb().report(entity, countRadioButton.isSelected(), reportList.getSelectedValue().toString(), groupByColumns));
            dispose();
        });


        // GUI
        add (countRadioButton);
        add (avgRadioButton);
        add (sp1);
        add (sp2);
        add (reportButton);
        add (selectButton);
        add (selectedColumnsArea);

        countRadioButton.setBounds (60, 45, 100, 25);
        avgRadioButton.setBounds (175, 45, 100, 25);
        sp1.setBounds (50, 80, 210, 190);
        sp2.setBounds (380, 80, 210, 190);
        reportButton.setBounds (265, 380, 100, 25);
        selectButton.setBounds (435, 45, 100, 25);
        selectedColumnsArea.setBounds (45, 285, 545, 65);

        setLayout(null);
        setTitle("Report");
        setMinimumSize(new Dimension(650, 450));
        setLocationRelativeTo(null);
        setVisible(true);


    }
}
