package pegmaster;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;

@SuppressWarnings("serial")
public class Menu extends JPanel implements ActionListener {

	private JFrame frame;
	public Toolkit tk;
	public Image logo;
	public Image matrix;
	public JPanel panel;
	public static Color background = new Color(184, 134, 11);
	public static boolean close = false;
	public boolean startup = true;
	
	private URL getURL(String file) {
		URL url = null;
		try {
			url = this.getClass().getResource(file);
		} catch (Exception e) {
			
		}
		return url;
	}
	
	
	public static void main(String[] args) {
		new Menu();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		if(startup) {
				g2d.setColor(Color.black);
				g2d.fillRect(0, 0, getWidth(), getHeight());
				g2d.drawImage(matrix, getWidth()/2-414/2, (getHeight()/2-84/2), 413, 83, this);
			} else {
				g2d.setColor(background);
				g2d.fillRect(0, 0, getWidth(), getHeight());
				g2d.drawImage(logo, getWidth()/2-365/2, 50, 365, 72, this);
				g2d.setColor(Color.black);
				g2d.fillRect(20, 120, getWidth()-40, getHeight()-140);
				g2d.setColor(background);
				g2d.fillRect(25, 125, getWidth()-50, getHeight()-150);
				
			}
		
}
	
	public void createGUI() {
		SizeableButton p = parseButton("Play");
		SizeableButton o = parseButton("Options");
		SizeableButton h = parseButton("Help");
		SizeableButton q = parseButton("Quit");
		panel = new JPanel();
		panel.setOpaque(false);
		panel.setLayout(new GridLayout(4,0));
		panel.add(p);
		panel.add(h);
		panel.add(o);
		panel.add(q);
		
		add(Box.createVerticalStrut(510));
		add(panel);
		frame.add(this);
	}
	
	public SizeableButton parseButton(String str) {
		SizeableButton b = new SizeableButton(str, 600, 60);
		b.setText(str);
		b.setActionCommand(str);
		b.addActionListener(this);
		return b;
	}
	
	SwingWorker<?,?> worker = new SwingWorker<Void,Integer>() {
		protected Void doInBackground() throws InterruptedException {
			long time = System.currentTimeMillis();
			frame.setSize(760, 440);
        	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    		frame.setLocationRelativeTo(null);
    		frame.setResizable(false);
    	    logo = tk.getImage(getURL("PegMaster.png"));
    		createGUI();
    		panel.setVisible(false);
    		frame.setVisible(true);
    		long wait = System.currentTimeMillis() - time;
    		Thread.sleep(500 + wait);
            startup = false;
            panel.setVisible(true);
            repaint();
            return null;  
        }  
	};
	
	public Menu() {
		tk = Toolkit.getDefaultToolkit();
		matrix = tk.getImage(getURL("logo.png"));
		frame = new JFrame("PegMaster");
		worker.execute();
	}
	
	public void actionPerformed(ActionEvent e) {
	String command = e.getActionCommand();
	if(command.equals("Play")) {
		frame.setVisible(false);
		new Window();
	} else if(command.equals("Quit")) {
		System.exit(0);
	}
	}
}
