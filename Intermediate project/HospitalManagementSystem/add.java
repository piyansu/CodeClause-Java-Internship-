package HospitalManagementSystem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.util.Random;
import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import com.toedter.calendar.JDateChooser;

public class add extends JFrame implements ActionListener {

    long random;
    JLabel formno, personaldetails, name, phno, dob, gender, mail, marital, address, city, state, pincode;
    JTextField namefield, phnofield, mailfield, addressfield, cityfield, statefield, pincodefield;
    JDateChooser dateChooser;
    JRadioButton male, female, others, married, unmarried;
    JButton exit , next , back;
    Image backgroundImage;

    add() {
        setLayout(null);
        
        Random ran = new Random();
        int random = ran.nextInt(50) + 1;
        
        formno = new JLabel("Patient No. " + random);
        formno.setFont(new Font("Railway", Font.BOLD, 40));
        formno.setBounds(220, 40, 600, 40);
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

        mail = new JLabel("Email : ");
        mail.setFont(new Font("Railway", Font.BOLD, 20));
        mail.setBounds(90, 310, 600, 50);
        add(mail);

        mailfield = new JTextField();
        mailfield.setFont(new Font("Railway", Font.PLAIN, 15));
        mailfield.setBounds(260, 320, 300, 30);
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

        address = new JLabel("Address : ");
        address.setFont(new Font("Railway", Font.BOLD, 20));
        address.setBounds(90, 390, 600, 50);
        add(address);

        addressfield = new JTextField();
        addressfield.setFont(new Font("Railway", Font.PLAIN, 15));
        addressfield.setBounds(260, 400, 300, 30);
        add(addressfield);

        city = new JLabel("City : ");
        city.setFont(new Font("Railway", Font.BOLD, 20));
        city.setBounds(90, 430, 600, 50);
        add(city);

        cityfield = new JTextField();
        cityfield.setFont(new Font("Railway", Font.PLAIN, 15));
        cityfield.setBounds(260, 440, 300, 30);
        
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

        next = new JButton("Register");
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
        if (e.getActionCommand().equals("Register")) {
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
                    String query = "INSERT INTO patient (name, ph_no, dob, gender, email, marital, address, city, state, pincode) VALUES ('"+name+"' , '"+phno+"' , '"+dob+"' , '"+gender+"', '"+email+"', '"+marital+"' , '"+address+"', '"+city+"', '"+state+"', '"+pincode+"' )";
                    c.s.executeUpdate(query);
                    JOptionPane.showMessageDialog(this, "Patient added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    new options();
                    dispose();
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        else if(e.getActionCommand().equals("Back")) {
        	new options();
        	dispose();
        }
    }

    public static void main(String[] args) {
        new add();
    }
}
