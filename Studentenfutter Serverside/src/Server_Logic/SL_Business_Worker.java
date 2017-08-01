package Server_Logic;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.util.List;
import Database_Access.*;

public class SL_Business_Worker
{
	//attributes
	SL_Payment_Handler _payment_handler;
	Database_Access_Interface _database_access_interface;
	OutputStream out;

	public SL_Business_Worker(Database_Access_Interface database_access_interface) 
	{
		_payment_handler = new SL_Payment_Handler();
		_database_access_interface = database_access_interface;
	}
	
	public void Handle_Order_Credits(List<List<String>> orderlist, Socket socket) throws SQLException, IOException
	{
		out = socket.getOutputStream();
		int creditsprize = 0;
		for(int i = 0; i < orderlist.size(); i++)
		{
			creditsprize = creditsprize + Integer.parseInt(orderlist.get(i).get(3));
		}
		Boolean result = _database_access_interface.Check_Credits(creditsprize);
		int returnedid;
		if(result == true)
		{
			PrintWriter pr = new PrintWriter(out);
			returnedid =_database_access_interface.Write_Order(orderlist);
			if(returnedid > 0)
			{
				pr.println("Die Bestellung wurde erfolgreich verbucht. Ihre Bestellnummer ist " + returnedid + ".");
			}
			else
			{
				
			}
		}
		else
		{
			
		}
		out.flush();
	}

}
