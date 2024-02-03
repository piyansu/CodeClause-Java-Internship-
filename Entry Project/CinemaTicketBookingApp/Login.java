package CinemaTicketBookingApp;

import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Login extends JFrame {

    String user;

	public Login(String user) {
    	
    	this.user = user ; 
    	
    	URL iconURL = getClass().getResource("/icons/logo.png");
        ImageIcon i1 = new ImageIcon(iconURL);
        Image i2 = i1.getImage().getScaledInstance(300, 125, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel label = new JLabel(i3);
        label.setBounds(200, 30, 300, 120);
        add(label);

        JLabel name = new JLabel("Enter Your Name : ");
        name.setFont(new Font("Osward", Font.BOLD, 25));
        name.setBounds(78, 168, 600, 40);
        add(name);

        JTextField namefield = new JTextField();
        namefield.setFont(new Font("Osward", Font.PLAIN, 25));
        namefield.setBounds(320, 175, 290, 35);
        namefield.setText(user);
        
        add(namefield);

        JLabel date = new JLabel("Select Booking Date : ");
        date.setFont(new Font("Osward", Font.BOLD, 25));
        date.setBounds(40, 230, 600, 50);
        add(date);

        String[] datelist = getUpcomingDates();
        JComboBox<String> dateDrop = new JComboBox<>(datelist);
        dateDrop.setBounds(320, 240, 290, 35);
        dateDrop.setFont(new Font("Osward", Font.PLAIN, 22));
        dateDrop.setBackground(Color.WHITE);
        add(dateDrop);
        
        JButton cinema = new JButton("Find Cinemas");
        cinema.setBounds(170, 340, 170, 45);
        cinema.setBackground(Color.BLACK);
        cinema.setFont(new Font("Osward", Font.BOLD, 20));
        cinema.setForeground(Color.WHITE);
        cinema.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String enteredName = namefield.getText();
                String selectedDate = (String) dateDrop.getSelectedItem();

                if (enteredName.isEmpty()) {
                    JOptionPane.showMessageDialog(Login.this, "Please enter your name.", "Name Required", JOptionPane.WARNING_MESSAGE);
                } else {
                    // Open SplashScreenDemo
                    new SplashScreenDemo(enteredName, selectedDate);
                   //new cinema(enteredName, selectedDate);
                    dispose();
                }
            }
        });
        add(cinema);

        JButton exit = new JButton("Exit");
        exit.setBounds(400, 340, 100, 45);
        exit.setBackground(Color.BLACK);
        exit.setFont(new Font("Osward", Font.BOLD, 20));
        exit.setForeground(Color.white);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add the logic for the "Exit" button here
                System.exit(0);
            }
        });
        add(exit);

        setSize(670, 470);
        setLocation(350, 150);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(209, 236, 98)); // Replace with your preferred color
        setUndecorated(true);
        setVisible(true);

        // Set the shape after the frame is visible
        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 20, 20));
    }

    private String[] getUpcomingDates() {
        String[] datelist = new String[5];
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (int i = 0; i < 5; i++) {
            datelist[i] = currentDate.plusDays(i).format(formatter);
        }
        return datelist;
    }

    public static void main(String[] args) {
        new Login("");
    }
}
