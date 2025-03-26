package UI;

import javax.swing.JFrame;
import javax.swing.border.Border;

import UI.Main.Header;
import UI.Main.Sidebar;

import java.awt.BorderLayout;
import java.awt.Dimension;

public class MainFrame extends JFrame {
    Dimension minSize;

    public MainFrame() {
        this.minSize = new Dimension(1280,720);

        this.setSize(1280, 720);
        this.setMinimumSize(this.minSize);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setVisible(true);

        this.add(new Header(), BorderLayout.NORTH);
        this.add(new Sidebar(), BorderLayout.WEST);
    }
}
