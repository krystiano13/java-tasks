package UI.Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import UI.Forms.GroupForm;
import UI.Forms.PersonForm;
import UI.Forms.TaskForm;

public class Sidebar extends JPanel {
    private String[] tables = { "Tasks", "Groups", "Persons" };
    private JButton newTaskButton;
    private JButton newPersonButton;
    private JButton newGroupButton;
    private JComboBox comboBox;

    public Sidebar(Table table) {
        this.comboBox = new JComboBox<String>(this.tables);
        this.setPreferredSize(new Dimension(200, 100));
        this.setBackground(new Color(220,220,220));
        this.addCreationButtons();

        this.newGroupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               GroupForm gf = new GroupForm(false, -1);
            }
        });

        this.newTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               TaskForm tf = new TaskForm(false, 0);
            }
        });

        this.newPersonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               PersonForm pf = new PersonForm(false, 0);
            }
        });

        this.comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedItem = (String) comboBox.getSelectedItem();

                switch (selectedItem) {
                    case "Tasks":
                        table.showTasks();
                        break;
                    case "Groups":
                        table.showGroups();
                        break;
                    case "Persons":
                        table.showPersons();
                        break;    

                    default:
                        break;
                }
            }
        });
    }

    private void addCreationButtons() {
        JPanel ButtonWrapper = new JPanel();

        ButtonWrapper.setLayout(new GridLayout(4,1,5,5));
        ButtonWrapper.setBorder(new EmptyBorder(10,0,0,0));
        ButtonWrapper.setBackground(new Color(220,220,220));

        this.newTaskButton = new JButton();
        this.newPersonButton = new JButton();
        this.newGroupButton = new JButton();
        
        this.newTaskButton.setFocusable(false);
        this.newGroupButton.setFocusable(false);
        this.newPersonButton.setFocusable(false);

        this.newTaskButton.setText("Create Task");
        this.newGroupButton.setText("Create Group");
        this.newPersonButton.setText("Create Person");

        ButtonWrapper.add(this.comboBox);
        ButtonWrapper.add(newTaskButton);
        ButtonWrapper.add(newPersonButton);
        ButtonWrapper.add(newGroupButton);

        this.add(ButtonWrapper);
    }
}
