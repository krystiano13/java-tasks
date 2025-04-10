package UI.Main;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import Database.Model.Group;
import Database.Model.Person;
import Database.Model.Task;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;

import Enums.ETable;
import UI.Forms.TaskForm;
import UI.Forms.PersonForm;
import UI.Forms.GroupForm;

public class Table extends JPanel {
    String[][] data = {};
    String[] columnNames = {};

    private String currentTable = "Tasks";

    public Table() {
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        this.setBorder(new EmptyBorder(20,20,20,20));
    }

    /**
     * Funkcja pozwala na załoadowanie danych tabeli "groups" do
     * wizualnej tabelki
     */
    public void showGroups() {
        this.removeAll();

        Group groups = new Group();
        ResultSet result = groups.select("*", "");
        this.renderResult(result, groups.columns, ETable.GROUPS);
        this.currentTable = "Groups";
    }

    /**
     * Funkcja pozwala na załoadowanie danych tabeli "tasks" do
     * wizualnej tabelki
     */
    public void showTasks() {
        this.removeAll();
        
        Task tasks = new Task();
        ResultSet result = tasks.select("*", "");
        this.renderResult(result, tasks.columns, ETable.TASKS);
        this.currentTable = "Tasks";
    }

    /**
     * Funkcja pozwala na załoadowanie danych tabeli "persons" do
     * wizualnej tabelki
     */
    public void showPersons() {
        this.removeAll();

        Person persons = new Person();
        ResultSet result = persons.select("*", "");
        this.renderResult(result, persons.columns, ETable.PERSONS);
        this.currentTable = "Persons";
    }
    
    /** 
     * Funkcja pozwala na konwersję rezultatu zapytania SELECT na tablicę 
     * wielowymiarową Stringów. Następnie tworzy elementy UI, w których
     * będą wtłoczone dane z tej włańie tablicy 
     * @param result
     * @param columns
     * @param table
     */
    private void renderResult(ResultSet result, String[] columns, ETable table) {
        try {
            List<String[]> tempRows = new ArrayList<>();

            while (result.next()) {
                String[] rowData = new String[columns.length];
                for (int col = 0; col < columns.length; col++) {
                    rowData[col] = result.getString(columns[col]);
                }
                tempRows.add(rowData);
            }

            this.data = new String[tempRows.size()][columns.length];
            for (int i = 0; i < tempRows.size(); i++) {
                this.data[i] = tempRows.get(i);
            }
        

            for (String[] item : this.data) {
                JPanel panel = new JPanel();
                panel.setLayout(new BorderLayout());
                panel.setBackground(new Color(210,210,210));
                panel.setBorder(new EmptyBorder(5,5,5,5));
                panel.setMinimumSize(new Dimension(850, 100));
                panel.setMaximumSize(new Dimension(5000, 101));

                JButton deleteButton = new JButton();
                deleteButton.setText("Delete");

                deleteButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String id = item[0];

                        switch(currentTable) {
                            case "Tasks":
                                Task taskModel = new Task();
                                taskModel.delete(Integer.parseInt(id));
                                Table.this.showTasks();
                                break;
                            case "Persons":
                                Person personModel = new Person();
                                personModel.delete(Integer.parseInt(id));
                                Table.this.showPersons();
                                break;
                            case "Groups":
                                Group groupModel = new Group();
                                groupModel.delete(Integer.parseInt(id));    
                                Table.this.showGroups();
                                break;

                            default:
                                break;    
                        }
                    }
                });

                JButton editButton = new JButton();
                editButton.setText("Edit");

                switch(table) {
                    case TASKS:
                        editButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                TaskForm form = new TaskForm(true, Integer.parseInt(item[0]));
                            }
                        });
                        break;
                    case PERSONS:
                        editButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                PersonForm form = new PersonForm(true, Integer.parseInt(item[0]));
                            }
                        });
                        break;
                    case GROUPS:
                        editButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                GroupForm form = new GroupForm(true, Integer.parseInt(item[0]));
                            }
                        });
                        break;
                }
                
                JLabel label = new JLabel();

                if(item.length > 2) {
                    if(columns[2] == "last_name") {
                        label.setText(item[1] + " " + item[2]);
                    }
                    else {
                        label.setText(item[1]);
                    } 
                }
                else {
                    label.setText(item[1]);
                }
 

                label.setFont(new Font("Arial", Font.PLAIN ,20));

                JPanel textContainer = new JPanel();
                textContainer.setLayout(new GridLayout(3, 1));

                JPanel buttonContainer = new JPanel();
                buttonContainer.setLayout(new GridLayout(1, 2));

                buttonContainer.add(editButton);
                buttonContainer.add(deleteButton);

                textContainer.add(label, BorderLayout.CENTER);

                if(item.length > 3) {
                    if(columns[2] == "group_id") {
                        JLabel groupLabel = new JLabel();

                        Group groupModel = new Group();
                        ResultSet groupName = groupModel.select("name", "id=" + item[2]);

                        groupLabel.setText("Group: " + groupName.getString(1));
                        textContainer.add(groupLabel);
                    }

                    if(columns[3] == "person_id") {
                        JLabel personLabel = new JLabel();

                        Person personModel = new Person();
                        ResultSet personName = personModel.select("name, last_name", "id=" + item[3]);

                        personLabel.setText("Person: " + personName.getString(1) + " " + personName.getString(2));
                        textContainer.add(personLabel);
                    }
                }

                panel.add(textContainer, BorderLayout.CENTER);
                panel.add(buttonContainer, BorderLayout.EAST);

                JPanel emptyPanel = new JPanel();
                emptyPanel.setMaximumSize(new Dimension(5000, 10));

                this.add(panel);
                this.add(emptyPanel);
            }

            this.revalidate();
            this.repaint();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
