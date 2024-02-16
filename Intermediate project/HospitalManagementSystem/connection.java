package HospitalManagementSystem;

import java.sql.*;  

public class connection {
    Connection c;
    Statement s;
    
    public connection(){  
        try{  
            Class.forName("com.mysql.cj.jdbc.Driver");  
            c = DriverManager.getConnection("jdbc:mysql:///HOSPITAL","root","Piyansu@2002");    
            s = c.createStatement(); 
        } catch(Exception e){ 
            System.out.println(e);
        }  
    } 
    
    public boolean patientExists(int patientNumber) {
        try {
            String query = "SELECT * FROM patient WHERE patient_no = ?";
            PreparedStatement ps = c.prepareStatement(query);
            ps.setInt(1, patientNumber);
            ResultSet rs = ps.executeQuery();
            return rs.next(); // Returns true if patient exists, false otherwise
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
