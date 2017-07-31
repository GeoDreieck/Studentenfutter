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
    final String host = "77.20.227.221";
    final int portNumber = 81;

    public Server_Connection_Handler()
    {
        System.out.println("Creating socket to '" + host + "' on port " + portNumber);
    }

    private List<List<String>> GetInfosfromServer(final int infoindex, final int restaurant_id) throws UnknownHostException, IOException, InterruptedException
    {
    	final List<List<String>> infoarray = new ArrayList<List<String>>();
    	final CountDownLatch latch = new CountDownLatch(1);
    	
        new Thread(new Runnable(){
            public void run()  {
                Socket socket = null;
                try {
                    socket = new Socket("77.20.227.221", portNumber);



                } catch (IOException e) {
                    e.printStackTrace();
                }


                //infoindex als output an den Server gibt an, welche Infos angefordert werden
                //Siehe Namen der unten stehenden Funktionen und die and diese Funktion uebergebenen Werte oder die Dokumentation als Referenz
                OutputStream out = null;
                try {
                    out = socket.getOutputStream();
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
                    try {
                        out.write(infoindex);
                        out.write(restaurant_id);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else
                {
                    try {
                        out.write(infoindex);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                for(int i = 0; i < 0; i++)
                {
                    List<String> templist = new ArrayList<String>();
                    for(int i2 = 0; i2 < 0; i2++)
                    {
                        //Wenn temp == "", neue Liste.
                        String temp = null;
                        try {
                            temp = br.readLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if(temp == "")
                        {
                            i = -2;
                            break;
                        }

                        //Wenn temp == null, br leer.
                        if(temp == null)
                        {
                            break;
                        }

                        templist.add(temp);
                    }
                    infoarray.add(templist);
                }
                latch.countDown();
                try {
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

                for (int i = 0; i < orderarray.size(); i++) {
                    for (int i2 = 0; i2 < orderarray.get(i).size(); i2++) {
                        pr.println(orderarray.get(i).get(i2));
                    }
                    pr.println("");
                    //pr.flush(); Achtung, moegliche Fehlerquelle.
                }

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
