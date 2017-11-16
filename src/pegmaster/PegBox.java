package pegmaster;
import java.awt.*;
public class PegBox {
	
	int index;
	Peg[] pegs;
	Peg[] hints;
	char[] peg;
	char[] hint;
	PegBox(int index, Graphics2D g) {
		this.index = index;
		pegs = new Peg[4];
		hints = new Peg[4];
		peg = new char[4];
		hint = new char[4];
		for(int i = 0;i<=3;i++) {
			peg[i] = 'G';
			hint[i] = 'G';
			pegs[i] = new Peg(g, 55);
			hints[i] = new Peg(g, 25);
		}
	}
	
	public String getVal() {
		String str = "";
		for(int i = 0;i<=3;i++) {
			str = str + peg[i];
		}
		return str;
	}
	
	public void submit() {
		String ans = PegMaster.guess(getVal());
		for(int i = 0;i<=3;i++) {
			hint[i] = ans.charAt(i);
		}
	}
	
	public void hint(char ch, int index) {
		hint[index] = ch;
	}
	
	public void peg(char ch, int index) {
		peg[index] = ch;
	}
	
	public void deselect(int num) {
		pegs[num].setSelected(false);
	}
	
	public void select(int num) {
		pegs[num].setSelected(true);
	}
	
	public void draw(Graphics2D g) {
		g.setColor(Color.black);
		int x = 75;
		g.fillRect(0, 0 + (index*x), 510, 75);
		g.setColor(new Color(139, 69, 19));
		g.fillRect(5, 5 + (index*x), 480, 65);
		g.setColor(Color.black);
		for(int i = 0;i<=3;i++) {
			pegs[i].draw(peg[i], 78*i + 15, index*x+10);
		}
		for(int i = 0;i<2;i++) {
			hints[i].draw(hint[i], 360 + ((i+1)*34), index*x+10);
			
		}
		for(int i = 2;i<=3;i++) {
			hints[i].draw(hint[i], 360 + ((i-1)*34), index*x+40);
			
		}
		for(int i = 1;i<=3;i++) {
			
		}
	    g.setColor(Color.black);
		g.fillRect(340, index*x, 7, 75);
	}
	
}
