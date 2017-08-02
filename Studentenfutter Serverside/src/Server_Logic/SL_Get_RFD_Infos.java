package Server_Logic;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Database_Access.*;

public class SL_Get_RFD_Infos 
{
	//attributes
	OutputStream out;
	Database_Access_Interface _database_access;
	

	public SL_Get_RFD_Infos(Database_Access_Interface database_access) {
		_database_access = database_access;
		
	}
	
	protected void Get_Restaurant_Info(Socket socket) throws SQLException, IOException
	{
		System.out.println("Restaurantinfos angefordert.");
		List<List<String>> infoarray = new ArrayList<List<String>>();
		System.out.println("Now trying to connect to database...");
		infoarray = _database_access.Get_R_From_Database();
		
		out = socket.getOutputStream();
		PrintWriter pr = new PrintWriter(out);
		
		for(int i = 0; i < infoarray.size(); i++)
		{
			for(int i2 = 0; i2 < infoarray.get(i).size(); i2++)
			{
				System.out.println(infoarray.get(i).get(i2));
				pr.println(infoarray.get(i).get(i2));
			}
		}
		System.out.println("ENDOFINFOS");
		pr.println("ENDOFINFOS");
		pr.flush();
		out.flush();
	}
	
	protected void Get_Food_Info(int restaurant_id, Socket socket) throws SQLException, IOException
	{
		System.out.println("Speiseninfos angeforedert.");
		out = socket.getOutputStream();
		List<List<String>> infoarray = new ArrayList<List<String>>();
		infoarray = _database_access.Get_F_From_Database(restaurant_id);
		PrintWriter pr = new PrintWriter(out);
		
		for(int i = 0; i < infoarray.size(); i++)
		{
			for(int i2 = 0; i2 < infoarray.get(i).size(); i2++)
			{
				pr.println(infoarray.get(i).get(i2));
			}
		}
		System.out.println("ENDOFINFOS");
		pr.println("ENDOFINFOS");
		pr.flush();
		out.flush();
	}
	
	protected void Get_Drink_Info(int restaurant_id, Socket socket) throws SQLException, IOException
	{
		System.out.println("Getraenkeinfos angefordert.");
		out = socket.getOutputStream();
		List<List<String>> infoarray = new ArrayList<List<String>>();
		infoarray = _database_access.Get_D_From_Database(restaurant_id);
		PrintWriter pr = new PrintWriter(out);
		
		for(int i = 0; i < infoarray.size(); i++)
		{
			for(int i2 = 0; i2 < infoarray.get(i).size(); i2++)
			{
				pr.println(infoarray.get(i).get(i2));
			}
		}
		System.out.println("ENDOFINFOS");
		pr.println("ENDOFINFOS");
		pr.flush();
		out.flush();
	}
}
