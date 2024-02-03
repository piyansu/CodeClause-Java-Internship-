package CinemaTicketBookingApp;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class booking extends JFrame {

    String selected_cinema, name, date;

    public booking(String name, String date, String selected_cinema) {
        // TODO Auto-generated constructor stub
        this.selected_cinema = selected_cinema;
        this.name = name;
        this.date = date;

        Random random = new Random();
        int ticketNumber = 1000 + random.nextInt(9000);

        if (selected_cinema.equals("Fighter")) {
            URL iconURL = getClass().getResource("/icons/two.jpg");
            ImageIcon i1 = new ImageIcon(iconURL);
            Image i2 = i1.getImage().getScaledInstance(180, 220, Image.SCALE_DEFAULT);
            ImageIcon i3 = new ImageIcon(i2);
            JLabel label = new JLabel(i3);
            label.setBounds(60, 30, 180, 220);
            add(label);
        } else if (selected_cinema.equals("Jawan")) {
            URL iconURL = getClass().getResource("/icons/one.jpg");
            ImageIcon i1 = new ImageIcon(iconURL);
            Image i2 = i1.getImage().getScaledInstance(180, 220, Image.SCALE_DEFAULT);
            ImageIcon i3 = new ImageIcon(i2);
            JLabel label = new JLabel(i3);
            label.setBounds(60, 30, 180, 220);
            add(label);
        } else {
            URL iconURL = getClass().getResource("/icons/three.jpg");
            ImageIcon i1 = new ImageIcon(iconURL);
            Image i2 = i1.getImage().getScaledInstance(180, 220, Image.SCALE_DEFAULT);
            ImageIcon i3 = new ImageIcon(i2);
            JLabel label = new JLabel(i3);
            label.setBounds(60, 30, 180, 220);
            add(label);
        }

        URL iconURL1 = getClass().getResource("/icons/logo.png");
        ImageIcon i11 = new ImageIcon(iconURL1);
        Image i21 = i11.getImage().getScaledInstance(240, 100, Image.SCALE_DEFAULT);
        ImageIcon i31 = new ImageIcon(i21);
        JLabel label1 = new JLabel(i31);
        label1.setBounds(300, 90, 300, 100);
        add(label1);

        JLabel time = new JLabel("Timing : ");
        time.setFont(new Font("Osward", Font.BOLD, 25));
        time.setBounds(190, 280, 600, 40);
        add(time);

        JLabel ticket = new JLabel("Number of Tickets : ");
        ticket.setFont(new Font("Osward", Font.BOLD, 25));
        ticket.setBounds(60, 330, 600, 50);
        add(ticket);

        JLabel seats = new JLabel("Package : ");
        seats.setFont(new Font("Osward", Font.BOLD, 25));
        seats.setBounds(175, 390, 600, 50);
        add(seats);

        String[] timelist = {"10:00 am", "12:00 pm", "3:00 pm", "6:00 pm", "9:00"};
        JComboBox<String> timeDrop = new JComboBox<>(timelist);
        timeDrop.setBounds(320, 285, 290, 35);
        timeDrop.setFont(new Font("Osward", Font.PLAIN, 22));
        timeDrop.setBackground(Color.WHITE);
        add(timeDrop);

        String[] ticketlist = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        JComboBox<String> ticketDrop = new JComboBox<>(ticketlist);
        ticketDrop.setBounds(320, 340, 290, 35);
        ticketDrop.setFont(new Font("Osward", Font.PLAIN, 22));
        ticketDrop.setBackground(Color.WHITE);
        add(ticketDrop);

        String[] seatlist = {"Silver [Rs.110/ticket]", "Gold [Rs.180/ticket]", "Royal [Rs.220/ticket]"};
        JComboBox<String> seatDrop = new JComboBox<>(seatlist);
        seatDrop.setBounds(320, 400, 290, 35);
        seatDrop.setFont(new Font("Osward", Font.PLAIN, 22));
        seatDrop.setBackground(Color.WHITE);
        add(seatDrop);

        JButton cinema = new JButton("Book Tickets");
        cinema.setBounds(170, 500, 170, 45);
        cinema.setBackground(Color.BLACK);
        cinema.setFont(new Font("Osward", Font.BOLD, 20));
        cinema.setForeground(Color.WHITE);
        cinema.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedTime = (String) timeDrop.getSelectedItem();
                String selectedTicketsStr = (String) ticketDrop.getSelectedItem();
                int selectedTickets = Integer.parseInt(selectedTicketsStr);
                String selectedSeat = (String) seatDrop.getSelectedItem();
                int count;
                if (selectedSeat.equals("Silver [Rs.110/ticket]")) {
                    count = selectedTickets * 110;
                } else if (selectedSeat.equals("Gold [Rs.180/ticket]")) {
                    count = selectedTickets * 180;
                } else {
                    count = selectedTickets * 220;
                }

                // Insert data into the database
                insertData(ticketNumber , name, date, selected_cinema, selectedTime, selectedTickets, selectedSeat, count);
                new SplashScreenDemo2(name, date ,selectedTime , selected_cinema , selectedTicketsStr ,ticketNumber ,count );
                dispose();
            }
        });
        add(cinema);

        JButton exit = new JButton("Exit");
        exit.setBounds(400, 500, 100, 45);
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

        setSize(670, 600);
        setLocation(350, 50);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(209, 236, 98)); // Replace with your preferred color
        setUndecorated(true);
        setVisible(true);

        // Set the shape after the frame is visible
        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 20, 20));
    }

    // Method to insert data into the database
    private void insertData(int ticketNumber, String name, String date, String cinema, String time, int tickets, String seat, int cost) {
        try {
            // Create a Connection instance
            connection connectionManager = new connection();

           // Prepare the SQL statement
            String sql = "INSERT INTO booking_info (booking_id, name, date, cinema, time, tickets, seat, total_cost) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connectionManager.c.prepareStatement(sql);

            // Set the parameters
            preparedStatement.setString(1, Integer.toString(ticketNumber));
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, date);
            preparedStatement.setString(4, cinema);
            preparedStatement.setString(5, time);
            preparedStatement.setInt(6, tickets);
            preparedStatement.setString(7, seat);
            preparedStatement.setInt(8, cost);


            // Execute the query
            preparedStatement.executeUpdate();

            // Close resources
            preparedStatement.close();
            connectionManager.c.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new booking("", "", "");
    }
}
