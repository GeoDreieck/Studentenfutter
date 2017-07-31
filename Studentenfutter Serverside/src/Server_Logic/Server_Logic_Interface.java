package Server_Logic;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;
import java.util.List;

public interface Server_Logic_Interface 
{
	public void Get_RFD(String infobit, int restaurant_id, Socket socket) throws SQLException, IOException;
	public void Handle_Order_Credits(List<List<String>> orderlist, Socket socket) throws SQLException, IOException;
}
