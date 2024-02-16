package HospitalManagementSystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.sql.*;

public class options extends JFrame implements ActionListener {

    options() {
        setLayout(null);

        JLabel text = new JLabel("<html><u>Choose Your Actions</u></html>");
        text.setFont(new Font("Osward", Font.BOLD, 30));
        text.setBounds(180, 50, 600, 40);
        add(text);

        JButton add = new JButton("Add Patient");
        add.setBounds(265, 150, 150, 40);
        add.setBackground(Color.BLACK);
        add.setBorder(null); // Set border to null to remove the white border
        add.setFont(new Font("Osward", Font.BOLD, 20));
        add.setForeground(Color.white);
        add.addActionListener(this);
        add(add);

        JButton show = new JButton("Show Patients");
        show.setBounds(251, 220, 180, 40);
        show.setBackground(Color.BLACK);
        show.setFont(new Font("Osward", Font.BOLD, 20));
        show.setForeground(Color.white);
        show.addActionListener(this);
        add(show);

        JButton update = new JButton("Update Data");
        update.setBounds(265, 290, 150, 40);
        update.setBackground(Color.BLACK);
        update.setFont(new Font("Osward", Font.BOLD, 20));
        update.setForeground(Color.white);
        update.addActionListener(this);
        add(update);

        getContentPane().setBackground(new Color(240, 252, 74)); // Replace with your preferred color

        setSize(670, 450);
        setUndecorated(true);

        // Set custom shape for JFrame with only top-left and top-right corners curved
        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 20, 20));

        setVisible(true);
        setLocation(360, 130);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Add Patient")) {
            new add();
            dispose();
        } else if (e.getActionCommand().equals("Show Patients")) {
            // Call method to show patient data
            showPatientData();
        } else if (e.getActionCommand().equals("Update Data")) {
        	new update1();
        	dispose();
        }
    }

    private void showPatientData() {
        try {
            // Establish connection using the connection class
            connection conn = new connection();
            
            // Execute query
            ResultSet resultSet = conn.s.executeQuery("SELECT * FROM patient");

            // Create JTable to display data
            JTable table = new JTable(buildTableModel(resultSet));

            // Show JTable in a JScrollPane
            JScrollPane scrollPane = new JScrollPane(table);
            JFrame frame = new JFrame("Patient Data");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.getContentPane().add(scrollPane);
            frame.setLocation(295, 130);
            frame.setSize(800, 400); 
            frame.setVisible(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static DefaultTableModel buildTableModel(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();

        // Names of columns
        int columnCount = metaData.getColumnCount();
        String[] columnNames = new String[columnCount];
        for (int column = 1; column <= columnCount; column++) {
            columnNames[column - 1] = metaData.getColumnName(column);
        }

        // Data of the table
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(columnNames);
        while (resultSet.next()) {
            Object[] rowData = new Object[columnCount];
            for (int i = 0; i < columnCount; i++) {
                rowData[i] = resultSet.getObject(i + 1);
            }
            tableModel.addRow(rowData);
        }
        return tableModel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new options();
        });
    }
}
