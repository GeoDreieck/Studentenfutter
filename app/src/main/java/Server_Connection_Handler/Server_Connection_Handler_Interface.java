package Server_Connection_Handler;

import java.io.IOException;
import java.io.Serializable;
import java.net.UnknownHostException;
import java.util.List;

public interface Server_Connection_Handler_Interface extends Serializable
{
	public List<List<String>> GetFoodinfofromRestaurant(int restaurant_id) throws IOException;
	
	public List<List<String>> GetDrinkinfofromRestaurant(int restaurant_id) throws IOException;
	
	public List<List<String>> GetRestaurantinfo() throws IOException;

	public String OrderwithCredits(List<List<String>> orderarray) throws IOException, InterruptedException;

	public int CheckLoggin(final List<String> stringlist) throws IOException, InterruptedException;
	
}
