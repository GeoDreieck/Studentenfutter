import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.SQLException;

import Client_Connetion_Handler.*;
import Database_Access.*;
import Server_Logic.*;

public class Main
{
	//attributes
	Client_Connection_Handler_Interface client_connection_handler_interface;
	Database_Access_Interface database_access_interface;
	Server_Logic_Interface server_logic_interface;
	
	public Main() {
		database_access_interface = new Database_Access();
		server_logic_interface = new SL_Logic_Access(database_access_interface);
		client_connection_handler_interface = new Client_Connection_Handler(server_logic_interface);
	}
	
	public void run() throws UnknownHostException, IOException, SQLException
	{
		client_connection_handler_interface.HandleClientCalls();
	}
	
	public static void main(String[] args) throws UnknownHostException, IOException, SQLException{
	    Main main = new Main();
	    main.run();
	}
}
