package Server_Logic;

import java.sql.SQLException;
import java.util.List;
import Database_Access.*;

public class SL_Business_Worker
{
	//attributes
	SL_Payment_Handler _payment_handler;
	Database_Access_Interface _database_access_interface;

	public SL_Business_Worker(Database_Access_Interface database_access_interface) 
	{
		_payment_handler = new SL_Payment_Handler();
		_database_access_interface = database_access_interface;
	}
	
	public void Handle_Order_Credits(List<List<String>> orderlist) throws SQLException
	{
		int creditsprize = 0;
		for(int i = 0; i < orderlist.size(); i++)
		{
			creditsprize = creditsprize + Integer.parseInt(orderlist.get(i).get(4));
		}
		Boolean result = _database_access_interface.Check_Credits(creditsprize);
		if(result == true)
		{
			result =_database_access_interface.Write_Order(orderlist);
		}
		else
		{
			
		}
		
	}

}
