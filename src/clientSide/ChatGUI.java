package clientSide;



import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

public class ChatGUI extends JFrame{
	JTextField chat;
	JTextPane names;
	JScrollPane log;
	JTextArea logText;
	Client master;
	
	
	public ChatGUI(Client clientele, String name){
		master = clientele;
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(null);
		panel.setBackground(Color.BLACK);
		
		setTitle("chat thingamajig, typing as " + name);
		setSize(900,700);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		logText = new JTextArea("Welcome to this chat thingamajig. You are currently typing as " + name);
		logText.setEditable(false);
		logText.setBackground(Color.BLACK);
		logText.setForeground(Color.GREEN);
		log = new JScrollPane(logText);
		log.setBounds(0,0,700,600);
		log.validate();
		panel.add(log);
		
		//This is a SmartScroller the SmartScroller scrolls the log very nicely.
		new SmartScroller(log);
		
		//Usernames list
		names = new JTextPane();
		names.setText("names");
		names.setBackground(Color.BLACK);
		names.setForeground(Color.RED);
		names.setEditable(false);
		names.setBounds(700,0,200,600);
		panel.add(names);
		
		//This is where you type.
		chat = new JTextField();
		chat.setBounds(0,600,900,30);
		panel.add(chat);
		chat.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER){
					String field = chat.getText();
					master.sendMessage(field);
					chat.setText("");
				} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
					chat.setText("");
				}
			}
			public void keyTyped(KeyEvent e) {}
			
		});
		
		//this is where it tells you what your name is
		JLabel yourNameHere = new JLabel(name);
		yourNameHere.setBounds(0,640,900,30);
		panel.add(yourNameHere);
	}
	public void addToLog(String s){
		logText.append("\n" + s);
		log.validate();
	}
	public void setNames(String[] names){
		String s = "";
		for (String name: names){
			s += name + "\n";
		}
		this.names.setText(s);
	}
}
