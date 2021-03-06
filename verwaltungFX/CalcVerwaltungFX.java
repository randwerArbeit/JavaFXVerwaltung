package verwaltungFX;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class CalcVerwaltungFX {
	static ResultSet rs;
	
	public static Vector getData(String dbName) throws ClassNotFoundException, SQLException{
		Connection conn =connect(dbName);
		
		Vector rueck = new Vector();
		while(rs.next()){
			rueck.add(rs.getString("Titel"));
		}
		conn.close();
		return rueck;		
	}
	
	
	
	

	public static Connection connect(String dbName) throws ClassNotFoundException{
		// load the sqlite-JDBC driver using the current class loader
	    
		Class.forName("org.sqlite.JDBC");
	    Connection connection = null;
	        
	      // create a database connection
	      try {
			connection = DriverManager.getConnection("jdbc:sqlite:" + dbName);
			Statement statement = connection.createStatement();
		      statement.setQueryTimeout(30);  // set timeout to 30 sec.
		      rs = statement.executeQuery("select * from film");
		      
		} catch (SQLException e) {
			connection = null;
			e.printStackTrace();
		}
	      
	      
	      
	      
	      
	      
	  return connection; 
	   
	}
	
	@SuppressWarnings("unchecked")
	public static Vector getEintragData(int id, Vector rueckVector, String dbName){
		
		
		try {
			Connection conn =connect(dbName);
			Statement statement = conn.createStatement();
			rs = statement.executeQuery("Select * from film where FilmID =" + Integer.toString(id+1));
			rueckVector.add(rs.getString("Titel"));
			rueckVector.add( rs.getString("StoryLine"));
			rueckVector.add(rs.getString("ReleaseDate")); 
			rueckVector.add( rs.getString("RunTimeMin"));
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rueckVector;
	}
	
@SuppressWarnings("unchecked")
public static Vector getLabels(int SprachenID, Vector VectorListe){		
		int i = 0;		
		try {
			Connection conn =connect("MovieDb.db");
			Statement statement = conn.createStatement();
			rs = statement.executeQuery("Select * from Uebersetzung where SprachenID =" + Integer.toString(SprachenID));			
			while(rs.next()){
				VectorListe.add(rs.getString("Bezeichnung"));
				i++;
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return VectorListe;
	}
	

}
