package pegmaster;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.awt.event.*;

import javax.swing.*;
@SuppressWarnings("serial")
public class PegMaster extends JPanel implements ActionListener {
	
	public String[] guesses = new String[10];
	public String[] hints = new String[10];
	public static String answer = "";
	int GUESSES = 9;
	public char[] pegs = {'@', '#', '$', '%', '&', '+',};
	public int currentGuess;
	public int currentPeg;
	
	public PegBox[] pegbox = new PegBox[9];
	
	public static Dimension size = new Dimension(520, 500);
	
	public BufferedImage canvas;
	public Graphics2D g2d;
	public JScrollPane scroller;
	JPanel panel;
	
	
	JScrollPane pictureScrollPane;
	
	public int[] gen(int[] numbers) {
		Random gen = new Random();
		boolean done = false;
		boolean same = true;
		int temp;
		int index = 0;
		while(!done) {
			if(index == 3) {
				done = true;
			}
			temp = gen.nextInt(6);
			for(int i = 0;i<=3;i++) {
				same = true;
				if(temp == numbers[i]) {
					break;
				} else {
					same = false;	
				}
			}
			if(!same) {
					numbers[index] = temp;
					index++;
				}
			}
		return numbers;
	}
	
	public static boolean contains(String str, char ch) {
		
		for(int i = 0;i<=str.length()-1;i++) {
			if(str.charAt(i) == ch) {
				return true;
			}
		}
		return false;
	}
	
	public static String guess(String guess) {
		char hintChar = ' ';
		String hint = "";
		if(guess.equals(answer)) {
			hint = "XXXX";
			return hint;
		}
		if(guess.length() != 4) {
			return "@@@@";
		}
		for(int i = 0;i<=3;i++) {
			hintChar = 'E';
			for(int x = 0;x<=3;x++) {
				if(guess.charAt(i) == answer.charAt(x) && x == i) {
					hintChar = 'X';
					break;
				} 
				if(guess.charAt(i) == answer.charAt(x) && i != x) {
					hintChar = 'O';
					break;
				} else if(guess.charAt(i) != answer.charAt(x)) {
					hintChar = 'G';
				}
			}
			hint = hint + hintChar;
		}
		return hint;
	}
	
	public boolean check(String str) {
		char[] arr = str.toCharArray();
		for(int i = 0;i<=3;i++) {
			for(int x = 0;x<=3;x++) {
				if(arr[x] == arr[i] && i != x) {
					return false;
				}
			}
		}
		return true;
	}
	
	public PegMaster() {
		currentGuess = 0;
		currentPeg = 0;
		canvas = new BufferedImage((int)size.getWidth()-30, 75 * GUESSES, BufferedImage.TYPE_INT_RGB);
		g2d = canvas.createGraphics();
		RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.addRenderingHints(hints);
        for(int i = 0;i<=pegbox.length-1;i++) {
        	pegbox[i] = new PegBox(i, g2d);
        }
        ImagePanel img = new ImagePanel(canvas);
        img.add(Box.createVerticalStrut(canvas.getHeight()));
	    pictureScrollPane = new JScrollPane(img);
        pictureScrollPane.setPreferredSize(new Dimension((int)size.getWidth()-10, (int)size.getHeight()-20));
        pictureScrollPane.setViewportBorder(BorderFactory.createLineBorder(Color.black));
        pictureScrollPane.setBackground(Menu.background);
        add(pictureScrollPane);
       
        parseButton("Blue");
		parseButton("Green");
		parseButton("Red");
		parseButton("Black");
		parseButton("Yellow");
		parseButton("White");
		parseButton("Back");
		parseButton("Enter");
		
		char[] pegs = {'@', '#', '$', '%', '&', '+',};
		int[] ans = new int[4];
	    ans = gen(ans);
	    
		for(int i = 0;i<=3;i++) {
			answer = answer + pegs[ans[i]];
		}
	}
	
	public JButton parseButton(String name) {
		JButton button = new JButton(name);
		button.addActionListener(this);
		button.setText(name);
		button.setActionCommand(name);
		add(button);
		return button;
	}
	
	public static void createGUI() {
		JFrame frame;
		frame = new JFrame("Peg Master");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(size);
		
		JComponent content = new PegMaster();
		content.setOpaque(true); 
		
		frame.setContentPane(content);
		frame.setSize((int)size.getWidth(), (int)size.getHeight()+100);
		frame.setVisible(true);
	}

	public static void main(String args[]) {
		 SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	             createGUI();
	            }
	        });
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		
		for(int i = 0;i<=pegbox.length-1;i++) {
        	pegbox[i].draw(g2d);
        }
		repaint();
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		if(command.equals("Enter")) {
			pegbox[currentGuess].submit();
			System.out.println("");
			currentGuess++;
			currentPeg = 0;
			return;
		}
		
		if(command.equals("Back")) {
			if(currentPeg > 0) {
				pegbox[currentGuess].peg('G', currentPeg-1);
				currentPeg -= 1;
				return;
			} else {
				return;
			}
		} 
		
		if(currentPeg >= 4 || currentGuess >= 9) {
			return;
		}
		
		if(command.equals("Red")) {
			pegbox[currentGuess].peg('#', currentPeg);
		} else if(command.equals("Green")) {
			pegbox[currentGuess].peg('$', currentPeg);
		} else if(command.equals("Blue")) {
			pegbox[currentGuess].peg('%', currentPeg);
    	} else if(command.equals("Black")) {
			pegbox[currentGuess].peg('+', currentPeg);
		} else if(command.equals("White")) {
			pegbox[currentGuess].peg('&', currentPeg);
		} else if(command.equals("Yellow")) {
			pegbox[currentGuess].peg('@', currentPeg);
		}
		currentPeg++;
	}
	
	class ImagePanel extends JPanel {
		
	    public Image img;

	    public ImagePanel(Image img){
	        this.img = img;
	    }
	    
	    public void paintComponent(Graphics g){
	        super.paintComponent(g);
	        g.drawImage(img, 0, 0, this);
	    }
	}
} 