package Client_Connetion_Handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import Server_Logic.*;

public class Client_Connection_Handler implements Client_Connection_Handler_Interface
{
	//attributes
	Server_Logic_Interface sli;
	ServerSocket providersocket;
	
	public Client_Connection_Handler(Server_Logic_Interface server_logic_interface) throws InterruptedException, IOException 
	{
		sli = server_logic_interface;
		providersocket = new ServerSocket(81);
	}
	
	public void HandleClientCalls(CountDownLatch latch) throws UnknownHostException, IOException, SQLException
	{
		System.out.println("waiting for client...");
		Socket socket = providersocket.accept();
		System.out.println("Connected to Client.");		
		latch.countDown();
		int endbit = 0;
		
		//Funktion hoert auf Calls des Clienten und gibt diese an Funktionen wieder, wo sie ausgewertet und verarbeitet werden.
		
		BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
		while(true)
		{
			String temp = br.readLine();
			if(temp != null)
			{
				System.out.println(temp);
				switch(temp)
				{
					case "1":
					case "2":
						int restaurant_id = Integer.parseInt(br.readLine());
						sli.Get_RFD(temp, restaurant_id, socket);
						break;
						
					case "3":
						System.out.println("Restaurantinfo angefordert");
						sli.Get_RFD(temp, -1, socket);
						break;
				
					case "Credits":
						List<List<String>> orderlist = new ArrayList<List<String>>();
						for(int i = 0; i < 0; i++)
						{
							List<String> templist = new ArrayList<String>();
							for(int i2 = 0; i2 < 0; i2++)
							{
								//Wenn temp == "", neue Liste.
								String tempstring = br.readLine();
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
								
								templist.add(tempstring);
							}
							orderlist.add(templist);
						}
						sli.Handle_Order_Credits(orderlist, socket);
						endbit = 1;
						break;
				}
				System.out.println("Alles zum Cienten geflusht");
			}			
			if(endbit == 1)
				break;
		}
		providersocket.close();
		socket.close();
	}
	
	
}

