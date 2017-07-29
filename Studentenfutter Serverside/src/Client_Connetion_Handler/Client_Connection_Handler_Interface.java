package Client_Connetion_Handler;

import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.SQLException;

public interface Client_Connection_Handler_Interface
{
	public void HandleClientCalls() throws UnknownHostException, IOException, SQLException;
}
