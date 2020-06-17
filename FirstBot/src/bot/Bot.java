package bot;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Bot extends JFrame{
	
	private JTextArea ChatArea = new JTextArea();
	private JTextField chatBox = new JTextField();
	
	public Bot() {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLayout(null);
		frame.setSize(600, 600);
		frame.setTitle("ChatBot");
		frame.add(ChatArea);
		frame.add(chatBox);
		
		// for TextArea
		ChatArea.setSize(500, 400);
		ChatArea.setLocation(2, 2);
		
		// TextField
		chatBox.setSize(500, 30);
		chatBox.setLocation(2, 500);
	
		chatBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String gText = chatBox.getText().toLowerCase();
				ChatArea.append("me: " + gText + " \n");
				chatBox.setText("");
				
				if(gText.contains("hi")) {
					bot("Holla");
				} else {
					int rand = (int)(Math.random()*3 + 1);
					if(rand == 1) {
						bot("I don't understand you");
					} else if(rand == 2) {
						bot("I do not understand you man");
					} else if(rand == 3) {
						bot("Please come again");
					}
				}
				
				
			}
			
		});
			
		
		
	}

	
	private void bot(String string) {
			ChatArea.append("BOT: " + string + " \n");
	}
	
	public static void main(String args[]) {
		new Bot();
	}
	
	
}
