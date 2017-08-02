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
	String password = "12345";
	String ip_address = "jdbc:mysql://localhost:3306/applikationData";
	Connection conn;

	public Database_Access() throws SQLException {
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		//Class.forName("com.mysql.jdbc.Driver");
	}

	public Connection getConnection() throws SQLException
	{
		Connection sqlconnection = DriverManager.getConnection(ip_address, username, password);
		return sqlconnection;
	}
	
	public List<List<String>> Get_R_From_Database() throws SQLException
	{
		conn = getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM RESTAURANT_INFO");
		
		List<List<String>> infoarray = new ArrayList<List<String>>();
		
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
		
		rs.close();
		stmt.close();
		conn.close();
		return infoarray;
	}
	
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
		List<String> insertstatementlist = new ArrayList<String>();
		try (Connection connection = DriverManager.getConnection(ip_address, username, password)) {
			conn = connection;
			conn.setCatalog("applikationData");
			 System.out.println("Database connected!");
		} catch (SQLException e) {
			conn.close();
		    return 0;
		}
		Statement stmt = conn.createStatement();
		for(int i = 0; i < orderlist.size(); i++)
		{
			if(orderlist.get(i).get(3) == "FOOD")
			{
				insertstatementlist.add("" + orderlist.get(i).get(0) + ", NULL, " + orderlist.get(i).get(3) + ", " + orderlist.get(i).get(5));
			}
			else
			{
				if(orderlist.get(i).get(3) == "DRINK")
				{
					insertstatementlist.add("NULL, " + orderlist.get(i).get(0) + ", " + orderlist.get(i).get(3) + ", " + orderlist.get(i).get(5));
				}
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

	public Boolean Check_Credits(int creditprize) throws SQLException
	{
		try (Connection connection = DriverManager.getConnection(ip_address, "dataUser", password)) {
			conn = connection;
			conn.setCatalog("userData");
			 System.out.println("Database connected!");
		} catch (SQLException e) {
			conn.close();
		    return false;
		}
		Statement stmt = conn.createStatement();
		
		ResultSet rs = stmt.executeQuery("SELECT * FROM DRINK_INFO");
		rs.next();
		int creditsize = rs.getInt("CREDITS");
		
		stmt.close();
		conn.close();
		
		if(creditsize >= creditprize)
			return true;
		else
			return false;
	}
}
