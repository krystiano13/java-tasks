package UI.Main;

import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Array;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import Database.Model.Task;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.ResultSet;

public class Table extends JPanel {
    JTable jTable;
    String[][] data = {};
    String[] columnNames = {};

    public Table() {
        this.setPreferredSize(new Dimension(200, 100));
        this.setLayout(new BorderLayout());

        if(this.data.length > 0 && this.columnNames.length > 0) {
            this.jTable = new JTable(data, columnNames);
            this.add(jTable, BorderLayout.CENTER);
        }
        else {
            this.jTable = new JTable();
            this.add(this.jTable);
        }
    }

    public void showGroups() {

    }

    public void showTasks() {
        Task tasks = new Task();
        ResultSet result = tasks.select("*", "");

        try {
            List<String[]> tempRows = new ArrayList<>();

            while (result.next()) {
                String[] rowData = new String[tasks.columns.length];
                for (int col = 0; col < tasks.columns.length; col++) {
                    rowData[col] = result.getString(tasks.columns[col]);
                }
                tempRows.add(rowData);
            }

            this.data = new String[tempRows.size()][tasks.columns.length];
            for (int i = 0; i < tempRows.size(); i++) {
                this.data[i] = tempRows.get(i);
            }

            if (this.jTable != null) {
                this.remove(this.jTable);
            }

            this.jTable = new JTable(this.data, tasks.columns);

            this.add(new JScrollPane(this.jTable), BorderLayout.CENTER); // Dodajemy JScrollPane aby umożliwić przewijanie

            this.revalidate();
            this.repaint();

        } catch (SQLException e) {
            System.out.println("Błąd podczas pobierania danych z tabeli tasks: " + e.getMessage());
        }
    }

    public void showPersons() {

    }
}
