package UI.Forms;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

public class PersonForm extends JFrame {
    private Dimension minSize;
    private JLabel title;
    private JPanel formElements;

    //Form Elements
    private JTextField input;
    private JTextField lastNameInput;
    private JButton submitButton;

    public PersonForm () {
        this.minSize = new Dimension(400,719);

        this.setMinimumSize(this.minSize);
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
    }
}
