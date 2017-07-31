import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;

import Client_Connetion_Handler.*;
import Database_Access.*;
import Server_Logic.*;

public class Main
{
	//attributes
	Client_Connection_Handler_Interface client_connection_handler_interface;
	Database_Access_Interface database_access_interface;
	Server_Logic_Interface server_logic_interface;
	
	public Main() throws InterruptedException, IOException {
		database_access_interface = new Database_Access();
		server_logic_interface = new SL_Logic_Access(database_access_interface);
		client_connection_handler_interface = new Client_Connection_Handler(server_logic_interface);
	}
	
	public void run() throws UnknownHostException, IOException, SQLException, InterruptedException
	{
		while(true)
		{
			CountDownLatch latch = new CountDownLatch(1);
			new Thread(new Runnable(){
	            public void run()
	            {
	            	try {
	            		client_connection_handler_interface.HandleClientCalls(latch);
					} catch (UnknownHostException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            	
	            }
			}).start();
			latch.await();
		}
		
	}
	
	public static void main(String[] args) throws UnknownHostException, IOException, SQLException, InterruptedException{
	    Main main = new Main();
	    main.run();
	}
}
