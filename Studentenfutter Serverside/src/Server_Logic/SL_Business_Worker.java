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
	Database_Access_Interface _database_access_interface;
	OutputStream out;

	public SL_Business_Worker(Database_Access_Interface database_access_interface) 
	{
		_database_access_interface = database_access_interface;
	}
	
	public void Handle_Order_Credits(List<List<String>> orderlist, Socket socket) throws SQLException, IOException
	{
		out = socket.getOutputStream();
		double creditsprize = 0;
		//Der Gesammtpreis der Bestellung wird aus den Werten in der Liste ermittelt
		String temp;
		
		//Zuerst muessen die Stringpreise aus der Liste aufbereitet werden
		for(int i = 0; i < orderlist.size(); i++)
		{
			temp = orderlist.get(i).get(2);
			temp= temp.trim();
            temp = temp.substring(0, temp.length()-1);
            temp = temp.replace(',', '.');
			creditsprize = creditsprize + Double.parseDouble(orderlist.get(i).get(2));
		}
		
		//Die aufgerufene Funktion checkt ob der User genug Credits hat und schickt ein Ergebnis zurück
		Boolean result = _database_access_interface.Check_Credits(creditsprize, orderlist.get(0).get(5));
		int returnedid;
		PrintWriter pr = new PrintWriter(out);
		
		//Falls genug Credits vorhanden sind, bestelle
		if(result == true)
		{
			returnedid =_database_access_interface.Write_Order(orderlist);
			if(returnedid > 0)
			{
				pr.println("Die Bestellung wurde erfolgreich verbucht. Ihre Bestellnummer ist " + returnedid + ".");
			}
			else
			{
				pr.println("Einer der uebergebenen Bestellungen hatte einen Fehler!");
			}
			pr.flush();
		}
		else
		{
			pr.println("Ihr Creditstand war unzureichend.");
			pr.flush();
		}
		out.flush();
	}

}
