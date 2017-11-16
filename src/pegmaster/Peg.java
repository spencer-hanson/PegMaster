package pegmaster;
import java.awt.*;

public class Peg {
	int r;
	int x;
	int y;
	char chara;
	Graphics2D g;
	boolean selected;
	
	public Peg(Graphics2D _g, int _r) {
		this.x = 0;
		this.y = 0;
		this.r = _r;
		g = _g;
		selected = false;
	}
	
	public void setSelected(boolean sel) {
		selected = sel;
	}
	
	public boolean getSelected() {
		return selected;
	}
	
	public char getVal() {
		return chara;
	}
	
	public void circle(Color color) {
		g.setColor(new Color(0, 0, 0));
		g.fillOval(x, y, r, r);
		g.setColor(color);
		g.fillOval(x+3, y+3, r-6, r-6);
		if(selected) {
			g.setColor(new Color(0, 255, 0));
			g.drawOval(x+4, y+4, r+1, r+1);
		}
		
	}
	public void draw(char ch, int x, int y) {
		this.x = x;
		this.chara = ch;
		this.y = y;
		if(ch == '$') {
			circle(new Color(0, 255, 0));
		} 
		if(ch == '%') {
			circle(new Color(0, 0, 255));
		} 
		if(ch == '#') {
			circle(new Color(255, 0, 0));
		}
		if(ch == '+') {
			circle(new Color(0, 0, 0));
		} 
		if(ch == '@') {
			circle(Color.yellow);
		}
		if(ch == '&') {
			circle(new Color(255, 255, 255));
		} 
		if(ch == 'O') {
			circle(new Color(0, 0, 0));
		} 
		if(ch == 'X') {
			circle(new Color(255, 255, 255));
		} 
		if(ch == 'G') {
			circle(new Color(139, 137, 137));
		}
	}
}
