package clientSide;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ServerConnect extends JFrame{
	JTextField port;
	JTextField host;
	JTextField name;
	JButton connectButton;
	Go go;
	
	public ServerConnect(Go go) throws InterruptedException{
		this.go = go;
		this.go.setGoFalse();
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		
		//Initializing basic gui operations
		setTitle("SC2Mafia Standalone Welcome Screen");
		setSize(400,140);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//the Host area
		host = new JTextField("Host");
		host.setBounds(0,0,300,30);
		panel.add(host);
		
		//The name area
		name = new JTextField("Name");
		name.setBounds(0,80,300,30);
		panel.add(name);
		
		//The Button area
		connectButton = new JButton("Connect");
		connectButton.setBounds(300,20,100,80);
		connectButton.addActionListener(new ButtonListener());
		panel.add(connectButton);
			
	}
	class ButtonListener implements ActionListener{

		ButtonListener(){
			
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			go.setGoTrue();
		}

	}
	public String getHost(){
		return host.getText();
	}
	public String getName(){
		return name.getText();
	}
	public void waitForButtonPress(){
		
	}
}
