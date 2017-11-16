package pegmaster;

import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Window extends JFrame {
	public static Dimension size = new Dimension(520, 500);
	
	public Window() {
		JComponent content = new PegMaster();
		setBackground(Menu.background);
		content.setOpaque(true); 
		setContentPane(content);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize((int)size.getWidth(), (int)size.getHeight()+100);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
