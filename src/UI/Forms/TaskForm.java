package UI.Forms;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import Database.Model.Group;
import Database.Model.Person;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskForm extends JFrame {
    private Dimension minSize;
    private Dimension maxSize;

    private JLabel title;
    private JPanel formElements;

    //Form Elements
    private JTextField input;
    private JComboBox<String> groupBox;
    private JComboBox<String> personBox;
    private JButton submitButton;

    public TaskForm () {
        this.minSize = new Dimension(400,719);
        this.maxSize = new Dimension(401, 720);

        this.setMinimumSize(this.minSize);
        this.setMaximumSize(this.maxSize);
        this.setLayout(new BorderLayout());
        this.setVisible(true);

        this.title = new JLabel("Add new Task", SwingConstants.CENTER); 
        this.title.setBorder(new EmptyBorder(10,10,10,10));
        this.title.setFont(new Font("Arial", Font.PLAIN, 20));

        this.formElements = new JPanel();
        this.formElements.setLayout(new GridLayout(10,1,5,5));
        this.formElements.setBorder(new EmptyBorder(10,10,10,10));

        JLabel nameLabel = new JLabel("Task Name");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        this.input = new JTextField();
        this.input.setMinimumSize(new Dimension(400, 40));
        this.input.setMaximumSize(new Dimension(2000, 41));
        this.input.setFont(new Font("Arial", Font.PLAIN, 16));
        this.input.setName("Test");

        this.submitButton = new JButton();
        this.submitButton.setText("Create");
        this.submitButton.setMinimumSize(new Dimension(400, 40));
        this.submitButton.setMaximumSize(new Dimension(2000, 41));

        this.formElements.add(nameLabel);
        this.formElements.add(this.input);
        this.initComboBoxes();
        this.formElements.add(this.submitButton);
        
        this.add(this.title, BorderLayout.NORTH);
        this.add(this.formElements, BorderLayout.CENTER);
    }

    private void initComboBoxes() {
        Person personModel = new Person();
        Group groupModel = new Group();

        List<String> groupIds = new ArrayList<String>();
        List<String> personIds = new ArrayList<String>();

        List<String> groupNames = new ArrayList<String>();
        List<String> personNames = new ArrayList<String>();

        try {
            ResultSet groups = groupModel.select("id, name", "");
            ResultSet persons = personModel.select("id, name, last_name", "");

            while(groups.next()) {
                groupIds.add(groups.getString(1));
                groupNames.add(groups.getString(2));
            }

            while(persons.next()) {
                personIds.add(persons.getString(1));
                personNames.add(persons.getString(2) + " " + persons.getString(3));
            }
        } catch(SQLException exception) {
            System.out.println(exception.getMessage());
        }

        String[] personArray = new String[personNames.size()];
        String[] groupArray = new String[groupNames.size()];

        for (int i = 0; i < personNames.size(); i++) {
            personArray[i] = personNames.get(i).toString();
        }

        for (int i = 0; i < groupNames.size(); i++) {
            groupArray[i] = groupNames.get(i).toString();
        }

        JLabel personLabel = new JLabel("Person");
        personLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        JLabel groupLabel = new JLabel("Group");
        groupLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        this.personBox = new JComboBox<String>(personArray);
        this.groupBox = new JComboBox<String>(groupArray);

        this.formElements.add(personLabel);
        this.formElements.add(this.personBox);
        this.formElements.add(groupLabel);
        this.formElements.add(this.groupBox);
    }
}
