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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import Database.Model.Group;
import UI.MainFrame;

public class GroupForm extends JFrame {
    private boolean editMode;
    private int id;

    private Dimension minSize;
    private Dimension maxSize;

    private JLabel title;
    private JPanel formElements;

    //Form Elements
    private JTextField input;
    private JButton submitButton;

    public GroupForm (boolean editMode, int id) {
        this.editMode = editMode;
        this.id = id;

        this.minSize = new Dimension(400,719);
        this.maxSize = new Dimension(401, 720);

        this.setMinimumSize(this.minSize);
        this.setMaximumSize(this.maxSize);
        this.setLayout(new BorderLayout());
        this.setVisible(true);

        if(this.editMode) {
            this.title = new JLabel("Edit Group", SwingConstants.CENTER); 
        }
        else {
            this.title = new JLabel("Add new Group", SwingConstants.CENTER); 
        }

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
        this.formElements.add(this.submitButton);
        
        this.add(this.title, BorderLayout.NORTH);
        this.add(this.formElements, BorderLayout.CENTER);

        this.submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GroupForm.this.create(GroupForm.this.input.getText());
            }
        });
    }

    private boolean create(String name) {
        Group groupModel = new Group();

        if(this.editMode) {
            try {
                groupModel.update("name = '" + name + "'", "id = " + this.id);
                this.dispose();
                MainFrame.getInstance().table.showGroups();
                return true;
            } catch(Exception exception) {
                System.out.println(exception.getMessage());
                return false;
            }
        } else {
            try {
                groupModel.create("'" + name + "'");
                this.dispose();
                MainFrame.getInstance().table.showGroups();
                return true;
            } catch(Exception exception) {
                System.out.println(exception.getMessage());
                return false;
            }
        }
    }
}
