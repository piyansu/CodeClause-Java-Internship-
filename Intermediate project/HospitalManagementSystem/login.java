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
import java.net.URL;

public class login extends JFrame implements ActionListener {

    JTextField adminfield;
    JButton login, clear;
    JPasswordField pinfield;

    login() {
        setLayout(null);

        URL iconURL = getClass().getResource("/photos/logo.jpg");
        ImageIcon i1 = new ImageIcon(iconURL);
        Image i2 = i1.getImage().getScaledInstance(540, 400, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel label = new JLabel(i3);
        label.setBounds(0, 85, 540, 400);
        add(label);

        JLabel text = new JLabel("<html><u>Welcome to Hospital Management System</u></html>");
        text.setFont(new Font("Osward", Font.BOLD, 30));
        text.setBounds(120, 25, 600, 40);
        add(text);

        JLabel adminid = new JLabel("Admin ID");
        adminid.setFont(new Font("Osward", Font.PLAIN, 23));
        adminid.setBounds(550, 130, 600, 40);
        add(adminid);

        adminfield = new JTextField();
        adminfield.setFont(new Font("Osward", Font.PLAIN, 25));
        adminfield.setBounds(550, 180, 290, 30);
        add(adminfield);

        JLabel pin = new JLabel("Password");
        pin.setFont(new Font("Osward", Font.PLAIN, 23));
        pin.setBounds(550, 220, 600, 40);
        add(pin);

        pinfield = new JPasswordField();
        pinfield.setFont(new Font("Osward", Font.BOLD, 25));
        pinfield.setBounds(550, 270, 290, 30);

        // Apply the DocumentFilter to restrict input to 4 digits
        ((AbstractDocument) pinfield.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                    throws BadLocationException {
                if (isDigit(string) && (fb.getDocument().getLength() + string.length()) <= 4) {
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                    throws BadLocationException {
                if (isDigit(text) && (fb.getDocument().getLength() + text.length() - length) <= 4) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }

            private boolean isDigit(String text) {
                return text.matches("\\d*");
            }
        });

        add(pinfield);

        JButton login = new JButton("Login");
        login.setBounds(570, 350, 100, 40);
        login.setBackground(Color.BLACK);
        login.setFont(new Font("Osward", Font.BOLD, 20));
        login.setForeground(Color.white);
        login.addActionListener(this);
        add(login);

        JButton clear = new JButton("Clear");
        clear.setBounds(710, 350, 100, 40);
        clear.setBackground(Color.BLACK);
        clear.setFont(new Font("Osward", Font.BOLD, 20));
        clear.setForeground(Color.white);
        clear.addActionListener(this);
        add(clear);

        JButton exit = new JButton("Exit");
        exit.setBounds(640, 410, 100, 40);
        exit.setBackground(Color.BLACK);
        exit.setFont(new Font("Osward", Font.BOLD, 20));
        exit.setForeground(Color.white);
        exit.addActionListener(this);
        add(exit);

        getContentPane().setBackground(new Color(240, 252, 74)); // Replace with your preferred color


        setSize(850, 485);
        setUndecorated(true);
        
        // Set custom shape for JFrame with only top-left and top-right corners curved
        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 20, 20));

        setVisible(true);
        setLocation(270, 130);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Login")) {
            String adminNo = adminfield.getText();
            char[] pinChars = pinfield.getPassword();
            String pinNo = new String(pinChars);

            // Use .equals() for string comparison
            if (adminNo.equals("piyansu") && pinNo.equals("1234")) {
                setVisible(false);
                new SplashScreen();
                
            } else {
                JOptionPane.showMessageDialog(null, "Incorrect Card Number or Pin");
            }
        } else if (e.getActionCommand().equals("Clear")) {
            adminfield.setText("");
            pinfield.setText("");
        } else if (e.getActionCommand().equals("Exit")) {
            dispose();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new login();
        });
    }
}
