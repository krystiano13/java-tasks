package UI;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

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
        this.header = new Header();
        this.table = new Table();

        this.minSize = new Dimension(1279,719);

        this.setMinimumSize(this.minSize);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setVisible(true);

        this.add(this.header, BorderLayout.NORTH);

        JScrollPane scroller = new JScrollPane(this.table);
        scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroller.setVisible(true);
        
        this.getContentPane().add(scroller, BorderLayout.CENTER);

        this.table.setVisible(true);
        this.table.setLayout(new BoxLayout(this.table, BoxLayout.Y_AXIS));;
        
        this.sidebar = new Sidebar(this.table);
        this.add(this.sidebar, BorderLayout.WEST);
        this.table.showTasks();

        this.table.revalidate();
        this.table.repaint();
        this.setSize(1280, 720);
    }
}
