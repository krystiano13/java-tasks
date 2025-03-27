package UI;

import javax.swing.JFrame;
import javax.swing.border.Border;

import UI.Main.Header;
import UI.Main.Sidebar;
import UI.Main.Table;

import java.awt.BorderLayout;
import java.awt.Dimension;

public class MainFrame extends JFrame {
    Dimension minSize;
    Header header;
    Sidebar sidebar;
    Table table;

    public MainFrame() {
        this.sidebar = new Sidebar();
        this.header = new Header();
        this.table = new Table();

        this.minSize = new Dimension(1280,720);

        this.setSize(1280, 720);
        this.setMinimumSize(this.minSize);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setVisible(true);

        this.add(this.header, BorderLayout.NORTH);
        this.add(this.sidebar, BorderLayout.WEST);
        this.add(this.table, BorderLayout.CENTER);

        this.table.showTasks();
    }
}
