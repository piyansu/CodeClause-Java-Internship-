package HospitalManagementSystem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;
import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import com.toedter.calendar.JDateChooser;

public class update2 extends JFrame implements ActionListener {

    long random;
    JLabel formno, personaldetails, name, phno, dob, gender, mail, marital, address, city, state, pincode;
    JTextField namefield, phnofield, mailfield, addressfield, cityfield, statefield, pincodefield;
    JDateChooser dateChooser;
    JRadioButton male, female, others, married, unmarried;
    JButton exit , next , back;
    Image backgroundImage;
	int patientNumber;

    update2(int patientNumber) {
    	this.patientNumber = patientNumber ;
        setLayout(null);        
        
        Random ran = new Random();
        int random = ran.nextInt(50) + 1;
        
        String name1 = ""; // Declare variables outside try block
        String phNo1 = "";
        String dob1 = "";
        String gender1 = "";
        String email1 = "";
        String marital1 = "";
        String address1 = "";
        String city1 = "";
        String state1 = "";
        String pincode1 = "";

        connection conn = null;
        try {
            // Establishing connection
            conn = new connection();
            String query = "SELECT * FROM patient WHERE patient_no = ?";
            try (PreparedStatement stmt = conn.c.prepareStatement(query)) {
                stmt.setInt(1, patientNumber);
                try (ResultSet rs = stmt.executeQuery()) {
                    // Processing the result set
                    while (rs.next()) {
                        // Fetching data
                        int patientNoResult = rs.getInt("patient_no");
                        name1 = rs.getString("name");
                        phNo1 = rs.getString("ph_no");
                        dob1 = rs.getString("dob");
                        gender1 = rs.getString("gender");
                        email1 = rs.getString("email");
                        marital1 = rs.getString("marital");
                        address1 = rs.getString("address");
                        city1 = rs.getString("city");
                        state1 = rs.getString("state");
                        pincode1 = rs.getString("pincode");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Closing resources
            if (conn != null) {
                try {
                    conn.c.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        
        formno = new JLabel("Patient No. " + patientNumber);
        formno.setFont(new Font("Railway", Font.BOLD, 40));
        formno.setBounds(235, 40, 600, 40);
        add(formno);

        personaldetails = new JLabel("<html><u>Personal Details</u></html>");
        personaldetails.setFont(new Font("Railway", Font.BOLD, 25));
        personaldetails.setBounds(255, 85, 600, 50);
        add(personaldetails);

        name = new JLabel("Name : ");
        name.setFont(new Font("Railway", Font.BOLD, 20));
        name.setBounds(90, 150, 600, 50);
        add(name);

        namefield = new JTextField();
        namefield.setFont(new Font("Railway", Font.PLAIN, 15));
        namefield.setBounds(260, 161, 300, 30);
        namefield.setText(name1+"");
        
     // Apply DocumentFilter to allow only alphabets in namefield
        ((AbstractDocument) namefield.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                    throws BadLocationException {
                if (string.matches("[a-zA-Z]+")) {
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                    throws BadLocationException {
                if (text.matches("[a-zA-Z]+")) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });
        
        add(namefield);

        phno = new JLabel("Phone Number: ");
        phno.setFont(new Font("Railway", Font.BOLD, 20));
        phno.setBounds(90, 190, 600, 50);
        add(phno);

        phnofield = new JTextField();
        phnofield.setFont(new Font("Railway", Font.PLAIN, 15));
        phnofield.setBounds(260, 200, 300, 30);
        phnofield.setText(phNo1+"");
        ((AbstractDocument) phnofield.getDocument()).setDocumentFilter(new DocumentFilter() {
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
        add(phnofield);
        
        dob = new JLabel("D.O.B : ");
        dob.setFont(new Font("Railway", Font.BOLD, 20));
        dob.setBounds(90, 230, 600, 50);
        add(dob);

        dateChooser = new JDateChooser();
        dateChooser.setFont(new Font("Railway", Font.PLAIN, 10));
        dateChooser.setBounds(260, 240, 170, 30);
        add(dateChooser);

        // Parsing dob1 string into a Date object
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
        java.util.Date dobDate = null; // Change the type to java.util.Date
        try {
            dobDate = sdf.parse(dob1);
        } catch (ParseException e1) {
            e1.printStackTrace();
        }

        // Setting the java.util.Date object to the JDateChooser
        dateChooser.setDate(dobDate);





        gender = new JLabel("Gender : ");
        gender.setFont(new Font("Railway", Font.BOLD, 20));
        gender.setBounds(90, 270, 600, 50);
        add(gender);

        male = new JRadioButton("Male");
        male.setFont(new Font("Railway", Font.BOLD, 18));
        male.setBounds(260, 285, 66, 20);
        male.setBackground(new Color(240, 252, 74));
        add(male);

        female = new JRadioButton("Female");
        female.setFont(new Font("Railway", Font.BOLD, 18));
        female.setBounds(370, 285, 90, 20);
        female.setBackground(new Color(240, 252, 74));
        add(female);

        others = new JRadioButton("Others");
        others.setFont(new Font("Railway", Font.BOLD, 18));
        others.setBounds(500, 285, 90, 20);
        others.setBackground(new Color(240, 252, 74));
        add(others);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(male);
        genderGroup.add(female);
        genderGroup.add(others);
        
        if (gender1.equals("Male")) {
            male.setSelected(true);
        } else if (gender1.equals("Female")) {
            female.setSelected(true);
        } else {
            others.setSelected(true);
        }

        mail = new JLabel("Email : ");
        mail.setFont(new Font("Railway", Font.BOLD, 20));
        mail.setBounds(90, 310, 600, 50);        
        add(mail);

        mailfield = new JTextField();
        mailfield.setFont(new Font("Railway", Font.PLAIN, 15));
        mailfield.setBounds(260, 320, 300, 30);
        mailfield.setText(pincode1);
        add(mailfield);

        marital = new JLabel("Marital Status : ");
        marital.setFont(new Font("Railway", Font.BOLD, 20));
        marital.setBounds(90, 350, 600, 50);
        add(marital);

        married = new JRadioButton("Married");
        married.setFont(new Font("Railway", Font.BOLD, 18));
        married.setBounds(260, 365, 100, 20);
        married.setBackground(new Color(240, 252, 74));
        add(married);

        unmarried = new JRadioButton("Unmarried");
        unmarried.setFont(new Font("Railway", Font.BOLD, 18));
        unmarried.setBounds(390, 365, 130, 20);
        unmarried.setBackground(new Color(240, 252, 74));
        add(unmarried);

        ButtonGroup maritalstatus = new ButtonGroup();
        maritalstatus.add(married);
        maritalstatus.add(unmarried);
        
        if (marital1.equals("Married")) {
        	married.setSelected(true);
        } else if (marital1.equals("Unmarried")) {
        	unmarried.setSelected(true);
        } else {
            others.setSelected(true);
        }

        address = new JLabel("Address : ");
        address.setFont(new Font("Railway", Font.BOLD, 20));
        address.setBounds(90, 390, 600, 50);
        add(address);

        addressfield = new JTextField();
        addressfield.setFont(new Font("Railway", Font.PLAIN, 15));
        addressfield.setBounds(260, 400, 300, 30);
        addressfield.setText(address1);
        add(addressfield);

        city = new JLabel("City : ");
        city.setFont(new Font("Railway", Font.BOLD, 20));
        city.setBounds(90, 430, 600, 50);
        add(city);

        cityfield = new JTextField();
        cityfield.setFont(new Font("Railway", Font.PLAIN, 15));
        cityfield.setBounds(260, 440, 300, 30);
        cityfield.setText(city1);
        
        ((AbstractDocument) cityfield.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                    throws BadLocationException {
                if (string.matches("[a-zA-Z]+")) {
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                    throws BadLocationException {
                if (text.matches("[a-zA-Z]+")) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });
        add(cityfield);

        state = new JLabel("State : ");
        state.setFont(new Font("Railway", Font.BOLD, 20));
        state.setBounds(90, 470, 600, 50);
        add(state);

        statefield = new JTextField();
        statefield.setFont(new Font("Railway", Font.PLAIN, 15));
        statefield.setBounds(260, 480, 300, 30);
        statefield.setText(state1);
        
        ((AbstractDocument) statefield.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                    throws BadLocationException {
                if (string.matches("[a-zA-Z]+")) {
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                    throws BadLocationException {
                if (text.matches("[a-zA-Z]+")) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });
        add(statefield);

        pincode = new JLabel("Pincode : ");
        pincode.setFont(new Font("Railway", Font.BOLD, 20));
        pincode.setBounds(90, 510, 600, 50);
        add(pincode);

        pincodefield = new JTextField();
        pincodefield.setFont(new Font("Railway", Font.PLAIN, 15));
        pincodefield.setBounds(260, 520, 300, 30);
        pincodefield.setText(pincode1);
        
        // Apply DocumentFilter to allow only digits in pincodefield
        ((AbstractDocument) pincodefield.getDocument()).setDocumentFilter(new DocumentFilter() {
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
        add(pincodefield);

        next = new JButton("Update");
        next.setBounds(190, 590, 150, 40);
        next.setBackground(Color.BLACK);
        next.setFont(new Font("Raleway", Font.BOLD, 20));
        next.setForeground(Color.white);
        next.addActionListener(this);
        add(next);
        
        back = new JButton("Back");
        back.setBounds(370, 590, 150, 40);
        back.setBackground(Color.BLACK);
        back.setFont(new Font("Raleway", Font.BOLD, 20));
        back.setForeground(Color.white);
        back.addActionListener(this);
        add(back);

        getContentPane().setBackground(new Color(240, 252, 74));

        setSize(700, 700);
        setLocation(350, 10);
        setUndecorated(true);
        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 20, 20)); // Adjust the values for the desired roundness
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Update")) {
            String name = ""+namefield.getText();
            String phno = phnofield.getText();
            String dob = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();

            String gender = null;
            if (male.isSelected())
                gender = "Male";
            else if (female.isSelected())
                gender = "Female";
            else if (others.isSelected())
                gender = "Others";

            String email = mailfield.getText();

            String marital = null;
            if (married.isSelected())
                marital = "Married";
            else if (unmarried.isSelected())
                marital = "Un-Married";

            String address = addressfield.getText();
            String city = cityfield.getText();
            String state = statefield.getText();
            String pincode = pincodefield.getText();

            try {
                if (name.equals("") || phno.equals("") || dob.equals("") || gender == null || email.equals("")
                        || marital == null || address.equals("") || city.equals("") || state.equals("")
                        || pincode.equals("")) {
                    JOptionPane.showMessageDialog(null, "All fields are required");
                } else {
                    connection c = new connection();
                    String query = "UPDATE patient SET name=?, ph_no=?, dob=?, gender=?, email=?, marital=?, address=?, city=?, state=?, pincode=? WHERE patient_no=?";
                    PreparedStatement stmt = c.c.prepareStatement(query);
                    stmt.setString(1, name);
                    stmt.setString(2, phno);
                    stmt.setString(3, dob);
                    stmt.setString(4, gender);
                    stmt.setString(5, email);
                    stmt.setString(6, marital);
                    stmt.setString(7, address);
                    stmt.setString(8, city);
                    stmt.setString(9, state);
                    stmt.setString(10, pincode);
                    stmt.setInt(11, patientNumber);
                    int rowsUpdated = stmt.executeUpdate();
                    if (rowsUpdated > 0) {
                        JOptionPane.showMessageDialog(this, "Patient Updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        new options();
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "Update failed!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    c.c.close(); // Close the connection
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        else if(e.getActionCommand().equals("Back")) {
            new update1();
            dispose();
        }
    }


    public static void main(String[] args) {
        new update2(0);
    }
}
