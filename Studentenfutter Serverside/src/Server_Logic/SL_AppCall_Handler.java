package Server_Logic;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

import Database_Access.*;

public class SL_AppCall_Handler
{
	//attributes
	SL_Get_RFD_Infos get_rfd_infos;
	
	protected SL_AppCall_Handler(Database_Access_Interface database_access) {
		get_rfd_infos = new SL_Get_RFD_Infos(database_access);
	}

	protected void Get_RFD_Info(String infobit, int restaurant_id, Socket socket) throws SQLException, IOException
	{
		switch(infobit)
		{
		case "1":
			get_rfd_infos.Get_Restaurant_Info(socket);
			break;
		case "2":
			get_rfd_infos.Get_Food_Info(restaurant_id, socket);
			break;
		case "3":
			get_rfd_infos.Get_Drink_Info(restaurant_id, socket);
			break;
		}
	}
}
