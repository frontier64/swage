package serverSide;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Chatter {
	
	Socket socket;
	BufferedReader reader;
	PrintWriter writer;
	String name;
	public Chatter(Socket s) throws IOException{
		socket = s;
		writer = new PrintWriter(socket.getOutputStream(), true);
		reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		name = reader.readLine();
	}
	public void sendMessage(String s){
		writer.println(s);
	}
	public String readLine() throws IOException{
		return reader.readLine();
	}
	public String getName(){
		return name;
	}
	public boolean hasNewLine() throws IOException{
		if (reader.ready()){
			return true;
		}
		return false;
	}
}
