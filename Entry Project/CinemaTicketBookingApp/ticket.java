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
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ticket extends JFrame {
	
	String selected_cinema, name, date , time , number;
	int ticketnumber ;
	int cost;
	
	public ticket(String name, String date , String time , String selected_cinema , String number , int ticketnumber , int cost) {
		this.selected_cinema = selected_cinema;
        this.name = name;
        this.date = date;
        this.time = time ;
        this.number = number ; 
        this.ticketnumber = ticketnumber ;
        this.cost = cost ; 
        
        if (selected_cinema.equals("Fighter")) {
            URL iconURL = getClass().getResource("/icons/two.jpg");
            ImageIcon i1 = new ImageIcon(iconURL);
            Image i2 = i1.getImage().getScaledInstance(130, 180, Image.SCALE_DEFAULT);
            ImageIcon i3 = new ImageIcon(i2);
            JLabel label = new JLabel(i3);
            label.setBounds(60, 30, 130, 180);
            add(label);
        } else if (selected_cinema.equals("Jawan")) {
            URL iconURL = getClass().getResource("/icons/one.jpg");
            ImageIcon i1 = new ImageIcon(iconURL);
            Image i2 = i1.getImage().getScaledInstance(130, 180, Image.SCALE_DEFAULT);
            ImageIcon i3 = new ImageIcon(i2);
            JLabel label = new JLabel(i3);
            label.setBounds(60, 30, 130, 180);
            add(label);
        } else {
            URL iconURL = getClass().getResource("/icons/three.jpg");
            ImageIcon i1 = new ImageIcon(iconURL);
            Image i2 = i1.getImage().getScaledInstance(130, 180, Image.SCALE_DEFAULT);
            ImageIcon i3 = new ImageIcon(i2);
            JLabel label = new JLabel(i3);
            label.setBounds(60, 30, 130, 180);
            add(label);
        }
        
        JLabel l3 = new JLabel(selected_cinema+" (U/A)");
        l3.setFont(new Font("Osward", Font.BOLD, 25));
        l3.setBounds(250, 30, 600, 40);
        add(l3);
        
        JLabel l1 = new JLabel("Hindi , 2D");
        l1.setFont(new Font("Osward", Font.PLAIN, 17));
        l1.setBounds(251, 68, 600, 40);
        add(l1);
        
        JLabel l2 = new JLabel(date + "  |  " + time);
        l2.setFont(new Font("Osward", Font.PLAIN, 17));
        l2.setBounds(251, 100 , 600, 40);
        add(l2);
        
        URL iconURL = getClass().getResource("/icons/qr.png");
        ImageIcon i1 = new ImageIcon(iconURL);
        Image i2 = i1.getImage().getScaledInstance(90, 90, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel label = new JLabel(i3);
        label.setBounds(80, 250, 90, 90);
        add(label);
        
        JLabel l4 = new JLabel(number+" Ticket(s)");
        l4.setFont(new Font("Osward", Font.PLAIN, 17));
        l4.setBounds(275, 230 , 600, 40);
        add(l4);
        
        JLabel l5 = new JLabel("BOOKING ID : "+ticketnumber);
        l5.setFont(new Font("Osward", Font.BOLD, 17));
        l5.setBounds(250, 270 , 600, 40);
        add(l5);
        
        JLabel l6 = new JLabel("Total Amonut : Rs."+cost);
        l6.setFont(new Font("Osward", Font.BOLD, 17));
        l6.setBounds(232, 300 , 600, 40);
        add(l6);
        
        JButton exit = new JButton("Exit");
        exit.setBounds(190, 400, 100, 45);
        exit.setBackground(Color.BLACK);
        exit.setFont(new Font("Osward", Font.BOLD, 20));
        exit.setForeground(Color.white);
        exit.setFocusable(false); 
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add the logic for the "Exit" button here
                System.exit(0);
            }
        });
        add(exit);
        
        setSize(480, 530);
        setLocation(450, 80);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE); // Replace with your preferred color
        setUndecorated(true);
        setVisible(true);
        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 20, 20));
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ticket(""  ,"" , "", "" , "", 0  , 0);
	}

}
