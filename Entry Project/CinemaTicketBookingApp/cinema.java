package CinemaTicketBookingApp;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class cinema extends JFrame{
	
	String name , date;

	public cinema(String name , String date) {
		this.name = name ;
		this.date = date ; 		
		
		
		
		URL iconURL = getClass().getResource("/icons/two.jpg");
        ImageIcon i1 = new ImageIcon(iconURL);
        Image i2 = i1.getImage().getScaledInstance(300, 400, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel label = new JLabel(i3);
        label.setBounds(20, 30, 300, 400);
        add(label);
	        
        JButton cinema = new JButton("Book Now");
        cinema.setBounds(85, 450, 170, 45);
        cinema.setBackground(Color.RED);
        cinema.setFont(new Font("Osward", Font.BOLD, 20));
        cinema.setForeground(Color.WHITE);
        cinema.setFocusable(false); // Disable the focus border
        cinema.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Example: Open a new cinema frame
            	String selected_cinema = "Fighter";
            	new booking(name , date , selected_cinema);
                dispose();
            }
        });
        add(cinema);

        
        URL iconURL1 = getClass().getResource("/icons/one.jpg");
        ImageIcon i1_1 = new ImageIcon(iconURL1);
        Image i2_1 = i1_1.getImage().getScaledInstance(300, 400, Image.SCALE_DEFAULT);
        ImageIcon i3_1 = new ImageIcon(i2_1);
        JLabel label1 = new JLabel(i3_1);
        label1.setBounds(400, 30, 300, 400);
        add(label1);
	        
        JButton cinema1 = new JButton("Book Now");
        cinema1.setBounds(470, 450, 170, 45);
        cinema1.setBackground(Color.RED);
        cinema1.setFont(new Font("Osward", Font.BOLD, 20));
        cinema1.setForeground(Color.WHITE);
        cinema1.setFocusable(false); // Disable the focus border
        cinema1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Example: Open a new cinema frame
            	String selected_cinema = "Jawan";
            	new booking(name , date , selected_cinema);
                dispose();
            }
        });
        add(cinema1);

        
        URL iconURL2 = getClass().getResource("/icons/three.jpg");
        ImageIcon i1_2 = new ImageIcon(iconURL2);
        Image i2_2 = i1_2.getImage().getScaledInstance(300, 400, Image.SCALE_DEFAULT);
        ImageIcon i3_2 = new ImageIcon(i2_2);
        JLabel label2 = new JLabel(i3_2);
        label2.setBounds(780, 30, 300, 400);
        add(label2);
	        
        JButton cinema2 = new JButton("Book Now");
        cinema2.setBounds(855, 450, 170, 45);
        cinema2.setBackground(Color.RED);
        cinema2.setFont(new Font("Osward", Font.BOLD, 20));
        cinema2.setForeground(Color.WHITE);
        cinema2.setFocusable(false); // Disable the focus border
        cinema2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Example: Open a new cinema frame
            	String selected_cinema = "Tiger3";
                new booking(name , date , selected_cinema);
                dispose();
            }
        });
        add(cinema2);

        
        JButton back = new JButton("Back");
        back.setBounds(400, 550, 100, 45);
        back.setBackground(Color.BLACK);
        back.setFont(new Font("Osward", Font.BOLD, 20));
        back.setForeground(Color.WHITE);
        back.setFocusable(false); // Disable the focus border
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Example: Open a new cinema frame
                new Login(name);
                dispose();
            }
        });
        add(back);


        JButton exit = new JButton("Exit");
        exit.setBounds(600, 550, 100, 45);
        exit.setBackground(Color.BLACK);
        exit.setFont(new Font("Osward", Font.BOLD, 20));
        exit.setForeground(Color.WHITE);
        exit.setFocusable(false); // Disable the focus border
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add the logic for the "Exit" button here
                System.exit(0);
            }
        });
        add(exit);


	        setSize(1100, 650);
	        setLocation(135, 50);
	        setLayout(null);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        getContentPane().setBackground(new Color(209, 236, 98)); // Replace with your preferred color
	        setUndecorated(true);
	        setVisible(true);

	        // Set the shape after the frame is visible
	        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 20, 20));
	}
	
	public static void main(String[] args) {
        new cinema("","");
    }
}
