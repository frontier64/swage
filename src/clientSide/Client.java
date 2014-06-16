package clientSide;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;


import serverSide.ChatServer;

public class Client {
	
	public static final int PORT = 9001;
	
	String name;
	Socket thisSocket;
	
	BufferedReader reader;
	PrintWriter writer;
	
	ChatGUI g;
	
	Client() throws InterruptedException, IOException{
		initGUI();
		writer.println(name);
		chatBoxGUI();
	}
	public void initGUI() throws InterruptedException, IOException {
		Go go = new Go(true);
		ServerConnect s = new ServerConnect(go);
		s.setVisible(true);
		while (!go.getGo()) {
			Thread.sleep(10);
		}
		boolean worked = false;
		Socket ourSocket = null;
		try {
			ourSocket = new Socket(s.getHost(), PORT);
			worked = true;
		} catch (UnknownHostException e) {
				
		}
		if (worked){
			name = s.getName();
			thisSocket = ourSocket;
			writer = new PrintWriter(thisSocket.getOutputStream(), true);
			reader = new BufferedReader(new InputStreamReader(thisSocket.getInputStream()));
			s.dispose();
		} else {
			System.out.println("you are a failure");
		}
	}
	
	public static void main(String[] args) throws UnknownHostException,IOException, InterruptedException{
		Client a = new Client();
	}
	public void chatBoxGUI() throws IOException{
		this.g = new ChatGUI(this, name);
		g.setVisible(true);
		System.out.println(reader.readLine());
		String next = reader.readLine().trim();
		ArrayList<String> chatNames = new ArrayList<String>();
		while (!next.equals("_END_")){
			System.out.println(next);
			System.out.println("does again");
			chatNames.add(next);
			next = reader.readLine();
		}
		for (String s: chatNames){
			System.out.println("namerino: " + s);
		}
		g.setNames(chatNames.toArray(new String[0]));
		System.out.println("asdf?");
		mainLoop();
	}
	
	public void mainLoop() throws IOException{
		while (true){
			String l = reader.readLine();
			System.out.println("here we are");
			System.out.println(l);
			if (l != null){
				l.trim();
			} else{
				continue;
			}
			if (l.equals("_NAMES_")){
				String next = reader.readLine();
				ArrayList<String> chatNames = new ArrayList<String>();
				while (!next.equals("_END_")){
					chatNames.add(next);
					next = reader.readLine();
				}
				g.setNames(chatNames.toArray(new String[0]));
			}
			else if (l.equals("_EXIT_")){
				System.exit(0);
			}
			else{
				g.addToLog(l);
			}
		}
		
	}
	public void sendMessage(String message){
		writer.println(message);
	}
}
