package Database_Access;

import java.sql.SQLException;
import java.util.List;

public interface Database_Access_Interface {
	public List<List<String>> Get_R_From_Database() throws SQLException;
	public List<List<String>> Get_F_From_Database(int restaurant_id) throws SQLException;
	public List<List<String>> Get_D_From_Database(int restaurant_id) throws SQLException;
	public int Write_Order(List<List<String>> orderlist) throws SQLException;
	public Boolean Check_Credits(double creditprize, String user_id) throws SQLException;
	public Boolean Check_Userlogin(List<String> arraylist) throws SQLException;
}
