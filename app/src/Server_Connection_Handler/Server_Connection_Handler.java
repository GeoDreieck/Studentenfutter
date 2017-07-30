package Server_Connection_Handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class Server_Connection_Handler implements Server_Connection_Handler_Interface 
{
	//attributes
	final String host = "HOSTADRESS";
	final int portNumber = 81;
	
	public Server_Connection_Handler() 
	{
		System.out.println("Creating socket to '" + host + "' on port " + portNumber);
	}
	
	private List<List<String>> GetInfosfromServer(int infoindex, int restaurant_id) throws UnknownHostException, IOException
	{
		List<List<String>> infoarray = new ArrayList<List<String>>();
		Socket socket = new Socket(host, portNumber);
		
		//infoindex als output an den Server gibt an, welche Infos angefordert werden
		//Siehe Namen der unten stehenden Funktionen und die and diese Funktion uebergebenen Werte oder die Dokumentation als Referenz
		OutputStream out = socket.getOutputStream();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
		if(infoindex == 1 || infoindex == 2)
		{
			out.write(infoindex);
			out.write(restaurant_id);
		}
		else
		{
			out.write(infoindex);
		}
		out.flush();
		out.close();
		
		for(int i = 0; i < 0; i++)
		{
			List<String> templist = new ArrayList<String>();
			for(int i2 = 0; i2 < 0; i2++)
			{
				//Wenn temp == "", neue Liste.
				String temp = br.readLine();
				if(temp == "")
				{
					i = -2;
					break;
				}
				
				//Wenn temp == null, br leer.
				if(temp == null)
				{
					break;
				}
				
				templist.add(temp);
			}
			infoarray.add(templist);
		}
		
		socket.close();
		return infoarray;
	}
	
	public List<List<String>> GetFoodinfofromRestaurant(int restaurant_id) throws IOException
	{
		return GetInfosfromServer(1, restaurant_id);
	}

	public List<List<String>> GetDrinkinfofromRestaurant(int restaurant_id) throws IOException
	{
		return GetInfosfromServer(2, restaurant_id);
	}
	
	public List<List<String>> GetRestaurantinfo() throws IOException
	{
		return GetInfosfromServer(3, 0);
	}

	public void OrderwithCredits(List<List<String>> orderarray) throws IOException
	{
		Socket socket = new Socket(host, portNumber);
		
		OutputStream out = socket.getOutputStream();
		
		PrintWriter pr = new PrintWriter(out);
		
		for(int i = 0; i < orderarray.size(); i++)
		{
			for(int i2 = 0; i2 < orderarray.get(i).size(); i2++)
			{
				pr.println(orderarray.get(i).get(i2));
			}
			pr.println("");
			//pr.flush(); Achtung, moegliche Fehlerquelle.
		}
		
		out.flush();
		socket.close();
	}
}