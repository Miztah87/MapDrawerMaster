package Default;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFrame;

import lejos.pc.comm.NXTConnector;

public class Main {
	static MapComponent mc = new MapComponent();
	 
	 public static void main(String[] args){
		 
		try
		{
		    System.out.println("Connecting to NXT...");
		    
		    NXTConnector conn = new NXTConnector();
		    boolean connected = conn.connectTo("btsTpp://NoFun");
		    
		    while (! connected)
		    {
		    	System.out.println("ERROR - Unable to connect to NXT");
		    	System.exit(2);
		    }
		  
		    
		    DataInputStream dis = new DataInputStream(conn.getInputStream());
		    DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
		    
		    if (connected) {
		    	 System.out.println("Connection established ...");
		    	 createMap();

			}
		    
		    Scanner sc = new Scanner(System.in);
		    boolean done = false;
		    while (! done)
		    { 
		    	String message = dis.readUTF();		
				
				
				
				dos.writeUTF(message.toUpperCase());
				dos.flush();
				String forward = message.substring(0, 7);
				System.out.println(forward);
				if ((forward.equalsIgnoreCase("forward"))) {
					System.out.println();
					System.out.println(message);
					String coordinates = message.substring(7);
					 String[] coords =	coordinates.split("#");
					int x = Math.round(Float.parseFloat((coords[0])));
					int y = Math.round(Float.parseFloat((coords[1])));
					mc.addPosition(x, y);

				}
			
			if (message.equalsIgnoreCase("quit"))
			{
			    done = true;
			}
		    }
		    System.out.println("Client terminating");	    
		} 
		catch (IOException e)
		{
		    e.printStackTrace();
		}
	 }
	 
	 public static void createMap()
	 {
		 JFrame frame = new JFrame();
			frame.setTitle("Robot Map");
			frame.setSize(1200, 1200);
			frame.setLocationRelativeTo(null);
			
			frame.add(mc);
			frame.setVisible(true);
			
			// test data
			mc.addPosition(100,  -100);
			mc.addPosition(150,  50);
			mc.addPosition(0, 0);
	
	 }
	 
}
