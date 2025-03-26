package UI.Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Sidebar extends JPanel {
    JButton newTaskButton;
    JButton newPersonButton;
    JButton newGroupButton;

    public Sidebar() {
        this.setPreferredSize(new Dimension(200, 100));
        this.setBackground(new Color(220,220,220));
        this.addCreationButtons();
    }

    private void addCreationButtons() {
        JPanel ButtonWrapper = new JPanel();

        ButtonWrapper.setLayout(new GridLayout(3,1,5,5));
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

        ButtonWrapper.add(newTaskButton);
        ButtonWrapper.add(newPersonButton);
        ButtonWrapper.add(newGroupButton);

        this.add(ButtonWrapper);
    }
}
