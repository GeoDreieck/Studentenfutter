package Server_Logic;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Database_Access.*;

public class SL_Get_RFD_Infos 
{
	//attributes
	final String host = "HOSTADRESS";
	final int portNumber = 81;
	Socket socket;
	OutputStream out;
	Database_Access_Interface _database_access;
	

	public SL_Get_RFD_Infos(Database_Access_Interface database_access) {
		try {
			_database_access = database_access;
			socket = new Socket(host, portNumber);
			out = socket.getOutputStream();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void Get_Restaurant_Info() throws SQLException, IOException
	{
		List<List<String>> infoarray = new ArrayList<List<String>>();
		infoarray = _database_access.Get_R_From_Database();
		PrintWriter pr = new PrintWriter(out);
		
		for(int i = 0; i < infoarray.size(); i++)
		{
			for(int i2 = 0; i2 < infoarray.get(i).size(); i2++)
			{
				pr.print(infoarray.get(i).get(i2));
			}
			pr.print("");
		}
		out.flush();
	}
	
	protected void Get_Food_Info(int restaurant_id) throws SQLException, IOException
	{
		List<List<String>> infoarray = new ArrayList<List<String>>();
		infoarray = _database_access.Get_F_From_Database(restaurant_id);
		PrintWriter pr = new PrintWriter(out);
		
		for(int i = 0; i < infoarray.size(); i++)
		{
			for(int i2 = 0; i2 < infoarray.get(i).size(); i2++)
			{
				pr.print(infoarray.get(i).get(i2));
			}
			pr.print("");
		}
		out.flush();
	}
	
	protected void Get_Drink_Info(int restaurant_id) throws SQLException, IOException
	{
		List<List<String>> infoarray = new ArrayList<List<String>>();
		infoarray = _database_access.Get_D_From_Database(restaurant_id);
		PrintWriter pr = new PrintWriter(out);
		
		for(int i = 0; i < infoarray.size(); i++)
		{
			for(int i2 = 0; i2 < infoarray.get(i).size(); i2++)
			{
				pr.print(infoarray.get(i).get(i2));
			}
			pr.print("");
		}
		out.flush();
	}
}
