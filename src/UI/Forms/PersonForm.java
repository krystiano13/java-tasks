package UI.Forms;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;

import Database.Model.Person;
import UI.MainFrame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Klasa odpowiedzialna za stworzenie formularza, który pozwala na tworzenie
 * lub edycję osób
 */
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

        if(this.editMode) {
            this.title = new JLabel("Edit Person", SwingConstants.CENTER); 
        }
        else {
            this.title = new JLabel("Add new Person", SwingConstants.CENTER); 
        }

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
       
        if(this.editMode) {
            this.submitButton.setText("Update");
        }
        else {
            this.submitButton.setText("Create");
        }

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

    
    /** 
     * Funkcja odpowiedzialna za stworzenie lub edycję osób
     * @param name
     * @param lastName
     * @return boolean
     */
    private boolean create(String name, String lastName) {
        Person personModel = new Person();

        if(this.input.getText().trim().isEmpty() || this.lastNameInput.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Fill all fields before submiting", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if(this.editMode) {
            try {
                personModel.update("name = '" + name + "', last_name = '" + lastName + "'", "id = " + this.id);
                this.dispose();
                MainFrame.getInstance().table.showPersons();
                return true;
            } catch(Exception exception) {
                JOptionPane.showMessageDialog(this, exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        else {
            try {
                personModel.create("'" + name + "'," + "'" + lastName + "'");
                this.dispose();
                MainFrame.getInstance().table.showPersons();
                return true;
            } catch(Exception exception) {
                JOptionPane.showMessageDialog(this, exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
    }
}

