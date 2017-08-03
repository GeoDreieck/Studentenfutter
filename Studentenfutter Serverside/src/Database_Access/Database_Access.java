package Database_Access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class Database_Access implements Database_Access_Interface
{
	//attributes
	String username = "applikationUser";
	String username2 = "dataUser";
	String password = "12345";
	String password2 = "12345";
	String ip_address = "jdbc:mysql://localhost:3306/applikationData";
	String ip_address2= "jdbc:mysql://localhost:3306/userData";
	Connection conn;

	public Database_Access() throws SQLException {
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		//Class.forName("com.mysql.jdbc.Driver");
	}

	//Über eine Methode wird eine Connection aufgebaut. Dafür werden String-Variablen verwendet
	public Connection getConnection() throws SQLException
	{
		Connection sqlconnection = DriverManager.getConnection(ip_address, username, password);
		return sqlconnection;
	}
	
	public Connection getConnection2() throws SQLException
	{
		Connection sqlconnection = DriverManager.getConnection(ip_address2, username2, password2);
		return sqlconnection;
	}
	
	public List<List<String>> Get_R_From_Database() throws SQLException
	{
		//Erst wird die Connection-Methode aufgerufen, dann ein Statement erstellt
		conn = getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM RESTAURANT_INFO");
		
		List<List<String>> infoarray = new ArrayList<List<String>>();
		
		//Solange noch unausgelesene Rows im resultset sind, wähle nächste Row aus, füge die einzelnen Informationen an eine Liste und uebergib dieser der return-Liste
		while(rs.next())
		{
			List<String> stringlist = new ArrayList<String>();
			stringlist.add(rs.getString(1));
			stringlist.add(rs.getString(2));
			stringlist.add(rs.getString(3));
			stringlist.add(rs.getString(4));
			stringlist.add(rs.getString(5));
			stringlist.add(rs.getString(6));
			stringlist.add(rs.getString(7));
			stringlist.add(rs.getString(8));
			stringlist.add(rs.getString(9));
			stringlist.add("ENDOFTEMPLIST");
			infoarray.add(stringlist);
		}
		System.out.println("Database infos returned to calling method.");
		
		//Alle Verbindungen muessen am Ende noch geschlossen werden
		rs.close();
		stmt.close();
		conn.close();
		return infoarray;
	}
	
	//Für weitere Infos, sehen sie in der Beschreibung für Get_R_From_Database nach. Nur das Returnstatement und die Informationen pro Reihe sind verschieden
	public List<List<String>> Get_F_From_Database(int restaurant_id) throws SQLException
	{
		conn = getConnection();
		Statement stmt = conn.createStatement();
		System.out.println("Restaurant-ID:" + restaurant_id);
		ResultSet rs = stmt.executeQuery("SELECT * FROM SPEISEN_INFO WHERE RESTAURANT_ID = " + restaurant_id);
		
		List<List<String>> infoarray = new ArrayList<List<String>>();
		
		while(rs.next())
		{
			List<String> stringlist = new ArrayList<String>();
			stringlist.add(rs.getString(1));
			stringlist.add(rs.getString(2));
			stringlist.add(rs.getString(3));
			stringlist.add(rs.getString(4));
			stringlist.add(rs.getString(5));
			stringlist.add("ENDOFTEMPLIST");
			infoarray.add(stringlist);
		}
		System.out.println("Database infos returned to calling method.");
		
		rs.close();
		stmt.close();
		conn.close();
		return infoarray;
	}

	//Für weitere Infos, sehen sie in der Beschreibung für Get_R_From_Database nach. Nur das Returnstatement und die Informationen pro Reihe sind verschieden
	public List<List<String>> Get_D_From_Database(int restaurant_id) throws SQLException
	{
		conn = getConnection();
		Statement stmt = conn.createStatement();
		System.out.println("Restaurant-ID:" + restaurant_id);
		ResultSet rs = stmt.executeQuery("SELECT * FROM DRINK_INFO WHERE RESTAURANT_ID = " + restaurant_id);
		
		List<List<String>> infoarray = new ArrayList<List<String>>();
		
		while(rs.next())
		{
			List<String> stringlist = new ArrayList<String>();
			stringlist.add(rs.getString(1));
			stringlist.add(rs.getString(2));
			stringlist.add(rs.getString(3));
			stringlist.add(rs.getString(4));
			stringlist.add(rs.getString(5));
			stringlist.add("ENDOFTEMPLIST");
			infoarray.add(stringlist);
		}
		System.out.println("Database infos returned to calling method.");
		
		rs.close();
		stmt.close();
		conn.close();
		return infoarray;
	}
	
	public int Write_Order(List<List<String>> orderlist) throws SQLException
	{
		//Nachdem eine Verbindung zur DB aufgebaut wurde, wird eine Liste dynamisch mit Strings für Statements gefuellt und dann nacheinander abgesendet
		conn = getConnection();
		Statement stmt = conn.createStatement();
		List<String> insertstatementlist = new ArrayList<String>();
		
		for(int i = 0; i < orderlist.size(); i++)
		{
			if(orderlist.get(i).get(4) == "FOOD")
			{
				insertstatementlist.add("" + orderlist.get(i).get(0) + ", NULL, " + orderlist.get(i).get(3) + ", " + orderlist.get(i).get(5));
			}
			else
			{
				if(orderlist.get(i).get(4) == "DRINK")
				{
					insertstatementlist.add("NULL, " + orderlist.get(i).get(0) + ", " + orderlist.get(i).get(3) + ", " + orderlist.get(i).get(5));
				}
				//Falls etwas weder Essen noch Trinken sein sollte, wird die gesammt bestellung verworfen, da nur unvollstaendige Bestellungen nicht rechtens sind
				else
				{
					stmt.close();
					conn.close();
					return 0;
				}
			}
			
		}
		String insertstatement = "";
		
		for(int i = 0; i < insertstatementlist.size(); i++)
		{
			insertstatement = insertstatement + "(" + insertstatementlist.get(i) + ")";
			if(i != 0 && i != insertstatementlist.size())
				insertstatement = insertstatement + ", ";
		}
		
		int result = stmt.executeUpdate("INSERT INTO BESTELLUNG_INFO (GERICHT_ID, DRINK_ID, ANZAHL, PERSON_ID) VALUES " + insertstatement);
		ResultSet rs = stmt.getGeneratedKeys();
		if (rs.next()){
		    result = rs.getInt(1);
		}
		
		stmt.close();
		conn.close();
		
		return result;
	}

	public Boolean Check_Credits(double creditprize, String user_id) throws SQLException
	{
		//Nachdem die verbindung aufgebaut wurde, wird der Creditstand des Users abgefragt und entsprechend der Gueltigkeit der Bestellung returned.
		conn = getConnection2();
		Statement stmt = conn.createStatement();
		
		ResultSet rs = stmt.executeQuery("SELECT * FROM USER_INFO WHERE idUSER_INFO = " + user_id);
		rs.next();
		int creditsize = rs.getInt("CREDITS");
		
		stmt.close();
		conn.close();
		
		if(creditsize >= creditprize)
			return true;
		else
			return false;
	}
	
	public Boolean Check_Userlogin(List<String> arraylist) throws SQLException
	{
		conn = getConnection2();
		Statement stmt = conn.createStatement();
		
		ResultSet rs = stmt.executeQuery("SELECT * FROM USER_INFO WHERE USER_NAME = " + arraylist.get(1) + " and USER_PASSWORD = " + arraylist.get(2));
		
		stmt.close();
		conn.close();
		
		if(rs.next())
			return true;
		else
			return false;
	}
}
