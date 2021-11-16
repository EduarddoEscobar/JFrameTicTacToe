import javax.swing.*;
import java.awt.*;
import java.awt.List;
import java.awt.event.*;
import java.util.*;

public class MyProgram extends JPanel{
    Scanner scan = new Scanner(System.in);
	JPanel mp;
	Grid grid;
	JFrame f;
	Player p1,p2;
	public static Boolean gameStarted = false, gameFinished = false, hoverStart = false, hoverEnd = false, p1turn = true;
	String winner = "", loser = "";
	Image dbi;
	Graphics dbg;
	
	///Startup Code\\\
	public MyProgram() {
		start();
		///Makes it so you can use the event Handler\\\
		Handler handler = new Handler();
		f.addMouseMotionListener(handler);
		f.addMouseListener(handler);
		f.setSize(400,500);
		f.add(this);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void start() {
		f = new JFrame("Tic-Tac-Toe");
		grid = new Grid(50,50,300,3,3);
		p1 = new Player("Player 1", 1);
		p2 = new Player("Player 2", 2);
	}
	
	public void paint(Graphics g) {
		dbi = createImage(getWidth(), getHeight());
		dbg = dbi.getGraphics();
		draw(dbg);
		g.drawImage(dbi,0,0,this);
	}
	
	public void draw(Graphics g) {
		super.paintComponent(g);
		this.setBackground(new Color(33,33,33));
		if(!gameStarted) {
			if(!hoverStart)
				g.setColor(new Color(30,187,215));
			else
				g.setColor(new Color(215,30,187));
			g.fillRect(150, 200, 100, 40);
			g.setColor(Color.black);
			g.drawString("Play!", 190, 225);
			g.setColor(Color.white);
			g.setFont(new Font("Arial", Font.BOLD, 26));
			g.drawString("Tic-Tac-Toe!", 125, 100);
		}else if(!gameFinished) {
			grid.draw(g);
			g.setColor(Color.white);
			g.setFont(new Font("Arial", Font.BOLD, 26));
			g.drawString("Tic-Tac-Toe", 125, 40);
		}else {
			if(!hoverEnd)
				g.setColor(new Color(30,187,215));
			else
				g.setColor(new Color(215,30,187));
			g.fillRect(150, 350, 100, 40);
			g.setColor(Color.black);
			g.drawString("Play!", 190, 375);
			g.setColor(Color.white);
			g.setFont(new Font("Arial", Font.BOLD, 26));
			g.drawString(winner, 120, 100);
			g.setFont(new Font("Arial", Font.BOLD, 13));
			g.drawString(loser, 120, 200);
		}
		repaint();
	}
	
	public static void main(String[] args) {
		MyProgram r = new MyProgram();
	}
	
	
	///Mouse Listeners\\\
	private class Handler implements MouseListener, MouseMotionListener{

		@Override
		public void mouseDragged(MouseEvent e) {}

		@Override
		public void mouseMoved(MouseEvent e) {
			if(e.getX() > 150 && e.getX() < 250 && e.getY() > 215 && e.getY() < 280)
				hoverStart = true;
			else
				hoverStart = false;
			
			if(gameStarted) {
				for(Tile t: grid.getTiles()) {
					if(e.getX() > t.getX() && e.getX() < t.S()+t.getX() && e.getY() > t.getY() && e.getY() < t.getY()+t.S() && t.isTaken() == false) {
						t.on();
					}else {
						t.off();
					}
				}
			}
			if(e.getX() > 150 && e.getX() < 250 && e.getY() > 365 && e.getY() < 430 && gameFinished)
				hoverEnd = true;
			else
				hoverEnd = false;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if(!gameStarted) {
				if(e.getX() > 150 && e.getX() < 250 && e.getY() > 215 && e.getY() < 280)
					gameStarted = !gameStarted;
			}else {
				for(Tile t: grid.getTiles()) {
					if(e.getX() > t.getX() && e.getX() < t.S()+t.getX() && e.getY() > t.getY() && e.getY() < t.getY()+t.S() && t.isTaken() == false) {
						if(p1turn){
							t.changeX(p1);
							if(grid.hasWon(p1)){
							    winner = p1.getName()+" Wins!";
							    loser = p2.getName()+" Lost But Did Good!";
							    gameFinished = true;
							}
						}else{
							t.changeO(p2);
							if(grid.hasWon(p2)){
							    winner = p2.getName()+" Wins!";
							    loser = p1.getName()+" Lost But Did Good!";
							    gameFinished = true;
							}
						}
						p1turn = !p1turn;
					}
				}
				int numTiles = grid.getTiles().size(), count = 0;
				for(Tile t: grid.getTiles()){
				    if(t.isTaken())
				        count++;
				}
				if(count == numTiles){
				    winner = "No one wins!";
				    loser = "";
				    gameFinished = true;
				}
			}
			if(e.getX() > 150 && e.getX() < 250 && e.getY() > 365 && e.getY() < 430 && gameFinished) {
				start();
				gameStarted = false;
				gameFinished = false;
				hoverStart = false;
				hoverEnd = false;
				p1turn = true;
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {}

		@Override
		public void mouseExited(MouseEvent e) {}

		@Override
		public void mousePressed(MouseEvent e) {}

		@Override
		public void mouseReleased(MouseEvent e) {}
		
	}
}