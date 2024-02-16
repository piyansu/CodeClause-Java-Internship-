package HospitalManagementSystem;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.DocumentFilter.FilterBypass;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

public class update1 extends JFrame implements ActionListener {

    JTextField adminfield;
    JButton search, back;

    update1() {
        setLayout(null);

        JLabel text = new JLabel("<html><u>Update Data</u></html>");
        text.setFont(new Font("Osward", Font.BOLD, 30));
        text.setBounds(210, 40, 600, 40);
        add(text);

        JLabel adminid = new JLabel("Enter Patient No.");
        adminid.setFont(new Font("Osward", Font.PLAIN, 23));
        adminid.setBounds(217, 130, 600, 40);
        add(adminid);

        adminfield = new JTextField();
        adminfield.setFont(new Font("Osward", Font.PLAIN, 25));
        adminfield.setBounds(180, 180, 240, 30);
        ((AbstractDocument) adminfield.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                    throws BadLocationException {
                if (string.matches("\\d*")) {
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                    throws BadLocationException {
                if (text.matches("\\d*")) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });
        add(adminfield);

        JButton search = new JButton("Search"); // Changed from "Login" to "Search"
        search.setBounds(170, 270, 100, 40); // Adjusted position
        search.setBackground(Color.BLACK);
        search.setFont(new Font("Osward", Font.BOLD, 20));
        search.setForeground(Color.white);
        search.addActionListener(this);
        add(search);

        JButton back = new JButton("Back");
        back.setBounds(330, 270, 100, 40);
        back.setBackground(Color.BLACK);
        back.setFont(new Font("Osward", Font.BOLD, 20));
        back.setForeground(Color.white);
        back.addActionListener(this);
        add(back);

        getContentPane().setBackground(new Color(240, 252, 74)); // Replace with your preferred color

        setSize(600, 420);
        setUndecorated(true);
        
        // Set custom shape for JFrame with only top-left and top-right corners curved
        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 20, 20));

        setVisible(true);
        setLocation(400, 150);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Search")) {
            String patientNo = adminfield.getText();
            try {
                int patientNumber = Integer.parseInt(patientNo);
                connection conn = new connection(); // Create an instance of your connection class
                boolean patientExists = conn.patientExists(patientNumber); // Check if patient exists
                if (patientExists) {
                    // Proceed with updating the data for the patient
                    // You can add your code here to update the patient data
                	new SplashScreen1(patientNumber);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Patient not found. Please enter a valid patient number.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid patient number.");
            }
        } else if (e.getActionCommand().equals("Back")) {
            new options();
            dispose();
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new update1();
        });
    }
}
