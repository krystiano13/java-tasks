package UI.Forms;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Database.Model.Person;
import UI.MainFrame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PersonForm extends JFrame {
    private boolean editMode;
    private int id;

    private Dimension minSize;
    private Dimension maxSize;

    private JLabel title;
    private JPanel formElements;

    //Form Elements
    private JTextField input;
    private JTextField lastNameInput;
    private JButton submitButton;

    public PersonForm (boolean editMode, int id) {
        this.editMode = editMode;
        this.id = id;

        this.minSize = new Dimension(400,719);
        this.maxSize = new Dimension(401, 720);

        this.setMinimumSize(this.minSize);
        this.setMaximumSize(this.maxSize);
        this.setLayout(new BorderLayout());
        this.setVisible(true);

        this.title = new JLabel("Add new Person", SwingConstants.CENTER); 
        this.title.setBorder(new EmptyBorder(10,10,10,10));
        this.title.setFont(new Font("Arial", Font.PLAIN, 20));

        this.formElements = new JPanel();
        this.formElements.setLayout(new GridLayout(10,1,5,5));
        this.formElements.setBorder(new EmptyBorder(10,10,10,10));

        JLabel nameLabel = new JLabel("Name");
        JLabel lastNameLabel = new JLabel("Last Name");

        nameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        lastNameLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        this.input = new JTextField();
        this.input.setMinimumSize(new Dimension(400, 40));
        this.input.setMaximumSize(new Dimension(2000, 41));
        this.input.setFont(new Font("Arial", Font.PLAIN, 16));
        this.input.setName("Test");

        this.lastNameInput = new JTextField();
        this.lastNameInput.setMinimumSize(new Dimension(400, 40));
        this.lastNameInput.setMaximumSize(new Dimension(2000, 41));
        this.lastNameInput.setFont(new Font("Arial", Font.PLAIN, 16));
        this.lastNameInput.setName("Test");

        this.submitButton = new JButton();
        this.submitButton.setText("Create");
        this.submitButton.setMinimumSize(new Dimension(400, 40));
        this.submitButton.setMaximumSize(new Dimension(2000, 41));

        this.formElements.add(nameLabel);
        this.formElements.add(this.input);
        this.formElements.add(lastNameLabel);
        this.formElements.add(this.lastNameInput);
        this.formElements.add(this.submitButton);
        
        this.add(this.title, BorderLayout.NORTH);
        this.add(this.formElements, BorderLayout.CENTER);

        this.submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PersonForm.this.create(PersonForm.this.input.getText(), PersonForm.this.lastNameInput.getText());
            }
        });
    }

    private boolean create(String name, String lastName) {
        Person personModel = new Person();

        try {
            personModel.create("'" + name + "'," + "'" + lastName + "'");
            this.dispose();
            MainFrame.getInstance().table.showPersons();
            return true;
        } catch(Exception exception) {
            System.out.println(exception.getMessage());
            return false;
        }
    }
}

