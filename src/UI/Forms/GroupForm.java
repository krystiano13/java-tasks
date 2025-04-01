package UI.Forms;

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

public class GroupForm extends JFrame {
    private Dimension minSize;
    private Dimension maxSize;

    private JLabel title;
    private JPanel formElements;

    //Form Elements
    private JTextField input;
    private JButton submitButton;

    public GroupForm () {
        this.minSize = new Dimension(400,719);
        this.maxSize = new Dimension(401, 720);

        this.setMinimumSize(this.minSize);
        this.setMaximumSize(this.maxSize);
        this.setLayout(new BorderLayout());
        this.setVisible(true);

        this.title = new JLabel("Add new Group", SwingConstants.CENTER); 
        this.title.setBorder(new EmptyBorder(10,10,10,10));
        this.title.setFont(new Font("Arial", Font.PLAIN, 20));

        this.formElements = new JPanel();
        this.formElements.setLayout(new GridLayout(10,1,5,5));
        this.formElements.setBorder(new EmptyBorder(10,10,10,10));

        JLabel nameLabel = new JLabel("Group Name");

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
        this.formElements.add(this.submitButton);
        
        this.add(this.title, BorderLayout.NORTH);
        this.add(this.formElements, BorderLayout.CENTER);
    }
}
