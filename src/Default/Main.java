package Default;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

import lejos.pc.comm.NXTConnector;

public class Main {
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
		    
		    Scanner sc = new Scanner(System.in);
		    boolean done = false;
		    while (! done)
		    { 
		    	String message = dis.readUTF();		
				
				System.out.println();
				System.out.println(message);
				
				dos.writeUTF(message.toUpperCase());
				dos.flush();
				
			
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
}
