package DBUpload;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class LatLong
{
	double lat=0;
	double lon=0;
	
	LatLong(double a, double b)
	{
		lat = a;
		lon= b;
	}
	
	double getLat()
	{
		return lat;
	}
	
	double getLon()
	{
		return lon;
	}
}


public class Matcher {
	
	private HashMap<LatLong,Integer> restaurantList = new HashMap();
	private Connection con = DatabaseConnection.getConnection();
	private Statement stmt = null;
	
	void pullData()
	{
		restaurantList.clear();
		try{
				stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery( "SELECT * FROM RESTAURANT;" );
				while ( rs.next() ) {
		            int id = rs.getInt("id");
		            String  name = rs.getString("name");
		            double lati  = rs.getDouble("latitude");
		            double longi  = rs.getDouble("longitude");
		            System.out.println("DB result - "+id+" , "+name+" , "+lati+" , "+longi);
		            restaurantList.put(new LatLong(lati,longi), id); 
				}
				rs.close();
				stmt.close();
				//con.close();
	         } catch ( Exception e ) {
	         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
	         System.exit(0);
	       }
	}
	
	void addLocalData(double lati, double longi, int id)
	{
		restaurantList.put(new LatLong(lati,longi), id);
	}
	
	void pushData(Review rev)
	{
		try
		{
			String sql = "INSERT INTO REVIEW (ID,AUTHOR,TEXT,RESTAURANT_ID) "
		               + "VALUES (?,?,?,?);";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1,rev.getId());
	        statement.setString(2,rev.getAuthor());
	        statement.setString(3, rev.getText());
	        statement.setInt(4, rev.getRestaurant_id());
			statement.executeUpdate();

			statement.close();
		    //con.commit();
		 } catch (Exception e) {
	         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
	         System.exit(0);
	      }
	}
	
	
	void pushData(Restaurant res)
	{
		try
		{
			String sql = "INSERT INTO RESTAURANT (ID,NAME,LATITUDE,LONGITUDE,ADDRESS,CITY_ID,RATING) "
		               + "VALUES (?,?,?,?,?,?,?);";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1,res.getId());
	        statement.setString(2,res.getName());
	        statement.setDouble(3, res.getLatitude());
	        statement.setDouble(4, res.getLongitude());
	        statement.setString(5, res.getName());
	        statement.setInt(6, res.getCity_id());
	        statement.setInt(7, res.getRating());
	        
			statement.executeUpdate();

			statement.close();
		    //con.commit();
		 } catch (Exception e) {
	         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
	         System.exit(0);
	      }
	}
	
	
	int matchData(double lati, double loni)
	{
		//Get a set of the entries
	    Set<Entry<LatLong, Integer>> set = restaurantList.entrySet();
	    // Get an iterator
	    Iterator i = set.iterator();
	    // Display elements
	    while(i.hasNext()) {
	    	Map.Entry me = (Map.Entry)i.next();
	    	if (lati == ((LatLong) me.getKey()).getLat() && loni == ((LatLong) me.getKey()).getLon())
	    	{
	    		return (Integer) me.getValue();
	    	}
//	        System.out.print(((LatLong) me.getKey()).getLat() + " , "+((LatLong) me.getKey()).getLon()+"  :  ");
//	        System.out.println(me.getValue());
	    }
	    return 0;
	}
	
	int maxRestaurantID()
	{
		int RestaurantID=0;
		for(Entry<LatLong, Integer> entry : restaurantList.entrySet()) {
		    if (RestaurantID == 0 || entry.getValue() > RestaurantID) {
		    	RestaurantID = entry.getValue();
		    }
		}
		
		return RestaurantID;
		
	}
	
	int maxReviewID()
	{
		int ReviewID=0;
		try{
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery( "SELECT max(id) AS MAXID FROM REVIEW;" );
			rs.next();
			ReviewID = rs.getInt("MAXID");
			rs.close();
			stmt.close();
			//con.close();
         } catch ( Exception e ) {}
		
		return ReviewID;
		
	}
	
	void closeConn()
	{
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
