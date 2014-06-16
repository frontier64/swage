package serverSide;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;


public class ChatServer {
	ServerSocket serverSocket;
	ArrayList<Chatter> chatters;
	
	public static final int PORT = 9001;
	ChatServer() throws IOException{
		chatters = new ArrayList<Chatter>();
		
		serverSocket = new ServerSocket(PORT);
		serverSocket.setSoTimeout(1);
	}
	
	public boolean checkAccept() throws IOException{
		boolean added = false;
		Socket socket;
		try {
			socket = serverSocket.accept();
			added = true;
			chatters.add(new Chatter(socket));
		} catch (SocketTimeoutException e){
			
		}
		return added;
		/*if (added){
			return chatters.get(chatters.size()-1);
		}
		return null;*/
	}
	public static void main(String[] args)throws IOException{
		ChatServer server = new ChatServer();
		server.mainLoop();
	}
	public void refreshNameList(){
		String names = "_NAMES_\n";
		for (Chatter c: chatters){
			names += c.getName() + "\n";
		}
		for (Chatter c: chatters){
			System.out.println(c.getName());
			c.sendMessage(names + "_END_");
		}
	}
	
	public void mainLoop() throws IOException{
		while (true){
			if (checkAccept()){
				refreshNameList();
			}
			for (Chatter c: chatters){
				if (c.hasNewLine()){
					String s = c.getName() + ": " + c.readLine();
					for (Chatter k: chatters){
						System.out.println("should be twice");
						k.sendMessage(s);
					}
				}
			}
		}
	}
}
