package UI.Main;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

/**
 * Klasa odpowiedzialna za stworzenie UI elementu nagłówkowego
 */
public class Header extends JPanel {
    JLabel label;

    public Header() {
        this.setPreferredSize(new Dimension(200, 80));
        this.setLayout(new BorderLayout());
        //this.setBackground(new Color(220,220,220));
        this.createTitle();
    }

    /**
     * Funkcja tworzy tytuł dla elementu nagłówkowego
     */
    private void createTitle() {
        this.label = new JLabel();
        this.label.setText("TASKS APP");
        this.label.setFont(new Font("Arial", Font.PLAIN, 20));
        this.setBorder(new EmptyBorder(0,40,0,0));
        this.add(this.label, BorderLayout.WEST);
    }
}
