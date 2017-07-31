package Server_Logic;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;
import java.util.List;

import Database_Access.*;

public class SL_Logic_Access implements Server_Logic_Interface
{
	//attributes
	SL_AppCall_Handler appcall_handler;
	SL_Business_Worker business_worker;
	Database_Access_Interface _database_access;
	
	public SL_Logic_Access(Database_Access_Interface database_access) {
		_database_access = database_access;
		appcall_handler = new SL_AppCall_Handler(_database_access);
		business_worker = new SL_Business_Worker(_database_access);
	}
	
	public void Get_RFD(String infobit, int restaurant_id, Socket socket) throws SQLException, IOException
	{
		appcall_handler.Get_RFD_Info(infobit, restaurant_id, socket);
	}
	
	public void Handle_Order_Credits(List<List<String>> orderlist, Socket socket) throws SQLException, IOException
	{
		business_worker.Handle_Order_Credits(orderlist, socket);
	}
}
