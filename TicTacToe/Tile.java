import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Tile extends JPanel{
	
	protected int x, y, size, num;
	public Color c, o;
	private boolean taken = false, takeMove = false, X = false, O = false;
	private Image dbi;
	private Graphics dbg;
	
	public Tile(int x, int y, int size, int num) {
		this.size = size-2;
		this.x = x+1;
		this.y = y+1;
		this.num = num;
		this.c = new Color(33,33,33);
		this.o = c;
		
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int S() {
		return size;
	}
	public int getNum() {
		return num;
	}
	public void on() {
		this.c = Color.BLUE;
	}
	public void off() {
		this.c = o;
	}
	public boolean isTaken() {
		return taken;
	}
	public void setTaken(boolean taken) {
		this.taken = taken;
	}
	
	public void changeX(Player p) {
		X = true;
		p.addMove(num);
		taken = true;
	}
	
	public void changeO(Player p) {
		O = true;
		p.addMove(num);
		taken = true;
	}
	
	public void paint(Graphics g) {
		dbi = createImage(getWidth(), getHeight());
		dbg = dbi.getGraphics();
		draw(dbg);
		g.drawImage(dbi,0,0,this);
	}
	
	public void draw(Graphics g) {
		super.paintComponent(g);
		g.setColor(c);
		g.fillRect(x, y, size, size);
		if(X) {
			g.setColor(Color.BLUE);
			g.drawLine(this.x, this.y, this.x+this.size, this.y+this.size);
			g.drawLine(this.x+this.size, this.y, this.x, this.y+this.size);
		}else if(O) {
			g.setColor(Color.RED);
			g.drawOval(this.x, this.y, this.size, this.size);
		}
		repaint();
	}
	
	public String toString() {
		return x+", "+y+", "+size+", "+size;
	}
}