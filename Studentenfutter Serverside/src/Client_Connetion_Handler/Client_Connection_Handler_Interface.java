package Client_Connetion_Handler;

import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;

public interface Client_Connection_Handler_Interface
{
	public void HandleClientCalls(CountDownLatch latch) throws UnknownHostException, IOException, SQLException;
}
