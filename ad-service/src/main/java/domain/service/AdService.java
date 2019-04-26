package domain.service;

import java.sql.*;
import java.util.UUID;

public class AdService {
	
	public static void addAnnonce(String userID, String name, String categorie, String state) 
	{
		
        /**
         * 3306 is the default port for MySQL in XAMPP. Note both the 
         * MySQL server and Apache must be running. 
         */
        String url = "jdbc:mysql://localhost/pinfo_wanted?useLegacyDatetimeCode=false&serverTimezone=UTC";

        /**
         * The MySQL user.
         */
        String user = "root";

        /**
         * Password for the above MySQL user. If no password has been 
         * set (as is the default for the root user in XAMPP's MySQL),
         * an empty string can be used.
         */
        String password = "";

        try
        {
//	            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connection successful");
            
            String query = " insert into wanted_info (Wanted_ID, User_ID, Name, Categorie, State)"
                    + " values (?, ?, ?, ?, ?)";
            
            String WantedID = UUID.randomUUID().toString();
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString (1, WantedID);
            preparedStmt.setString (2, userID);
            preparedStmt.setString (3, name);
            preparedStmt.setString (4, categorie);
            preparedStmt.setString (5, state);
            
            preparedStmt.execute();
            con.close();
            System.out.println("Wanted added to pinfo_wanted");
            

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
	}
	
	public static void removeAnnonce(String wantedID) 
	{
		
        String url = "jdbc:mysql://localhost/pinfo_wanted?useLegacyDatetimeCode=false&serverTimezone=UTC";
        String user = "root";
        String password = "";

        try
        {
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connection successful");
            
            String query = "delete from wanted_info where Wanted_ID=?";
            
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString (1, wantedID);
            preparedStmt.execute();
            con.close();
            System.out.println("Wanted " + wantedID + " deleted successfully");

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
	}
	
    public static void main(String[] args) {	
    	
    }
}

