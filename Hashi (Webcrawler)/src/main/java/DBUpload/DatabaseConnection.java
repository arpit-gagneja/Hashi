package DBUpload;

import java.sql.Connection;
import java.sql.DriverManager;

class DatabaseConnection {

	static Connection c = null;
	
	static Connection getConnection () {
	      
	      try {
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/hashi",
	            "postgres", "postgres");
	      } catch (Exception e) {
	         e.printStackTrace();
	         System.err.println(e.getClass().getName()+": "+e.getMessage());
	         System.exit(0);
	      }
	      System.out.println("Opened database successfully");
	      return c;
	   }

}