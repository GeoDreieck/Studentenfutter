package Server_Connection_Handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Server_Connection_Handler implements Server_Connection_Handler_Interface
{
    //attributes
    final String host = "10.60.17.241";
    final int portNumber = 81;

    public Server_Connection_Handler()
    {
        System.out.println("Creating socket to '" + host + "' on port " + portNumber);
    }

    private List<List<String>> GetInfosfromServer(final int infoindex, final int restaurant_id) throws UnknownHostException, IOException, InterruptedException
    {
    	final List<List<String>> infoarray = new ArrayList<List<String>>();
    	final CountDownLatch latch = new CountDownLatch(1);

        System.out.println("Starting Socket-Thread!");
        new Thread(new Runnable(){
            public void run()  {
                Socket socket = null;
                try {
                    System.out.println("Now trying to connect to Server...");
                    socket = new Socket(host, portNumber);
                    System.out.println("Server found! Socket established.");

                } catch (IOException e) {
                    e.printStackTrace();
                    latch.countDown();
                    return;
                }


                //infoindex als output an den Server gibt an, welche Infos angefordert werden
                //Siehe Namen der unten stehenden Funktionen und die and diese Funktion uebergebenen Werte oder die Dokumentation als Referenz
                OutputStream out = null;
                PrintWriter pr = null;
                try {
                    out = socket.getOutputStream();
                    pr = new PrintWriter(out);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                BufferedReader br = null;
                try {
                    br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if(infoindex == 1 || infoindex == 2)
                {

                        pr.println(infoindex);
                        pr.println(restaurant_id);
                        pr.flush();
                }
                else
                {
                    pr.println(infoindex);
                    pr.flush();
                }
                try {
                    out.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                int bit = 0;
                for(int i = -2; i < 0; i++)
                {
                    List<String> templist = new ArrayList<String>();
                    for(int i2 = -2; i2 < 0; i2++)
                    {
                        if (bit == 1)
                            i2 = -1;
                        //Wenn temp == "ENDOFLIST", neue Liste.
                        String temp = null;
                        try {
                            temp = br.readLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        if(temp.equals("ENDOFTEMPLIST"))
                        {
                            i = -2;
                            break;
                        }

                        if(temp != null && !temp.equals("") && i2 == -2 && bit == 0 && !temp.equals("null"))
                        {
                            bit++;
                        }

                        //Wenn temp == ENDOFINFOS, br leer.
                        if(temp.equals("ENDOFINFOS"))
                        {
                            if(bit == 1)
                                i = -1;
                            break;
                        }

                        i2--;
                        templist.add(temp);
                    }
                    if(templist.size() > 0)
                        infoarray.add(templist);
                }
                latch.countDown();
                try {
                    br.close();
                    pr.close();
                    out.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        
        latch.await();
        return infoarray;
    }

    public List<List<String>> GetFoodinfofromRestaurant(int restaurant_id) throws IOException
    {
        List<List<String>> list =null;

        try {
            list = GetInfosfromServer(1, restaurant_id);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<List<String>> GetDrinkinfofromRestaurant(int restaurant_id) throws IOException
    {
        List<List<String>> list =null;

        try {
            list = GetInfosfromServer(2, restaurant_id);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<List<String>> GetRestaurantinfo() throws IOException
    {
        List<List<String>> list =null;

        try {
            list = GetInfosfromServer(3, 0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void OrderwithCredits(final List<List<String>> orderarray) throws IOException, InterruptedException {
    	final CountDownLatch latch = new CountDownLatch(1);
    	
        new Thread(new Runnable(){
            public void run() {
                Socket socket = null;
                try {
                    socket = new Socket(host, portNumber);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                OutputStream out = null;
                try {
                    out = socket.getOutputStream();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                PrintWriter pr = new PrintWriter(out);
                pr.println("Credits");

                for (int i = 0; i < orderarray.size(); i++) {
                    for (int i2 = 0; i2 < orderarray.get(i).size(); i2++) {
                        pr.println(orderarray.get(i).get(i2));
                    }
                    pr.println("");
                }
                pr.flush();

                try {
                    out.flush();
                    latch.countDown();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        
            }).start();
        

//      InetAddress serverAddr = InetAddress.getByName(host);
      latch.await();
    }
}
