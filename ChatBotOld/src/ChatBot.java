import java.awt.event.KeyEvent;		
import java.awt.event.KeyListener; 	// da bi registrovao enter
import java.awt.Color; 				// za pozadinu

// Kozmetički i organizacija
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;


public class ChatBot extends JFrame implements KeyListener{
	
	JPanel 		panel 		= new JPanel();
	JTextArea 	dialog 		= new JTextArea(20,50);
	JTextArea 	input		= new JTextArea(1,50);
	JScrollPane scroll		= new JScrollPane(
			dialog,
			JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
			);
	
	public static void main(String[] args) {
		new ChatBot();
	}
	
	public ChatBot() {
		
		// naslov
		super("Chat Bot");
		
		// veličina prozora
		setSize(600,400);
		
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// da ne može korisnik da piše ovde
		dialog.setEditable(false);
		
		// trigeruje key listenere u ovoj klasi a ne nekoj drugoj
		input.addKeyListener(this);
		
		
		/*
		 * Dodajemo prethodno definisane okvire
		 * Da bi dijalog mogao da se skroluje, mora da bude 
		 * unutar scroll-a.
		 */
		panel.add(scroll);
		panel.add(input);
		
		panel.setBackground(new Color(255, 200, 0));
		
		// dodajemo panel u JFrame i činimo ga vidljivim
		add(panel);
		setVisible(true);
	};
	
	/* dvodimenzionalni niz koji sadrži sve fraze koje bot može da izgovori
	 * i sve fraze na koje može da reaguje.
	 * U podeljenim grupama u prvom redu je ono što korisnik napiše
	 * u drugom redu je odgovor bota.
	 */
	
	 String[][] chatBot= {
			 // standardni pozdravi
			 {"hi", "hello", "hola", "ola", "howdy"},
			 {"hi", "hello", "hey"},
			 
			 // upitivački pozdravi
			 {"how are you", "how r you", "how r u", "how are u"},
			 {"good", "doing well"},
			 
			 // default, ako ne bude nijedna od prethodnih fraza
			 {"shut up", "you're bad", "I do not understand you", "What did that even mean?"}
	 };
	
	
	// Metode za pritisnute tastere
	@Override
	public void keyTyped(KeyEvent e) {
		
		
		
	};

	@Override
	public void keyPressed(KeyEvent e) {
		
		// KeyEvent sadrži kodove za getKeyCode metodu
		if(e.getKeyCode()==KeyEvent.VK_ENTER) {
			input.setEditable(false);
			
			// dohvatiti citat
			String quote = input.getText();
			input.setText("");
			addText("You:\t" + quote);
			quote = quote.trim();
			while(quote.charAt(quote.length() - 1 ) == '!' ||
				  quote.charAt(quote.length() - 1 ) == '.' ||
				  quote.charAt(quote.length() - 1 ) == '?') {
						quote = quote.substring(0, quote.length() - 1);
			}; 
			
			quote = quote.trim();
			
			/*
			 * Vrednost response:
			 * 0: traži kroz chatBot[][] za poklapanja
			 * 1: nije ništa našao
			 * 2: našao nešto
			 * */
			
			byte response = 0;
			
			// proveriti poklapanja
			
			int j = 0; // ovo određuje iz kojeg grupe će iz niza odgovoriti
			
			while(response==0) {
				if(inArray(quote.toLowerCase(), chatBot[j*2])) {
					response = 2;
					
					int r = (int)Math.floor(
								 Math.random()
								 *chatBot[(j * 2) + 1].length);
					
					addText("Bot:\t" + chatBot[(j * 2) + 1][r]);
				};
				
				j++;
				
				// da proverimo da li je prošao kroz sve opcije
				if(j * 2 == chatBot.length - 1 && response == 0) {
					
					// znači nije našao ništa
					response = 1;
				};
			};
			
			// default reakcije
			
			if(response == 1) {
				
				int r = (int)Math.floor(
						 Math.random()
						 *chatBot[chatBot.length - 1].length);
			
				addText("Bot:\t" + chatBot[chatBot.length - 1][r]);
			};
			
			addText("\n");
			
		};
	};

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_ENTER) {
			input.setEditable(true);
		};
	};
	
	
	public void addText(String str) {
		dialog.setText(dialog.getText() + str + "\n");
	};
	
	public boolean inArray(String in, String[] str) {
		boolean match = false;
		
		for(int i = 0; i < str.length; i++) {
			if(str[i].equals(in)) {
				match = true;
			};
		};
		
		return match;
	};
};
