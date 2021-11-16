import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class Grid extends JPanel{
	private int x, y, space, size, num = 1, spots;
	private Color c;
	private ArrayList<Tile> tiles;
	private List<List> wins;
	Image dbi;
	Graphics dbg;
	
	public Grid(int x, int y, int size, int spots, int winLength ){
		wins = new ArrayList<List>();
		this.x = x;
		this.y = y;
		space = size/spots;
		this.spots = spots;
		this.size = size;
		this.c = Color.white;
		tiles = new ArrayList<Tile>();
		addTiles();
		setWins(winLength);
	}
	
	public boolean hasWon(Player p){
	    for(List l: wins){
	        if(p.getMoves().containsAll(l))
	            return true;
	    }
	    return false;
	}
	
	public void setWins(int length) {
    	List<Integer> curWin = new ArrayList<Integer>();
        
    	///Gets Horizontal Win Conditions\\\
    	for(int y=0;y<spots*spots;y+=spots){
    		for(int x=0;x<spots - length + 1;x++){
    			for(int i=x;i<x+length;i++)
    				curWin.add(i+y+1);
    			wins.add(new ArrayList<Integer>(curWin));
    			curWin.clear();
    		}
    	}
    	///Gets Vertical Win Conditions\\\
    	for(int x=0;x<spots;x++) {
    		for(int y=0;y<spots*spots-(spots*length)+1;y+=spots){
    			for(int i=y;i<y+(spots*length);i+=spots)
    				curWin.add(i+x+1);
    			wins.add(new ArrayList<Integer>(curWin));
    			curWin.clear();
    		}
    	}
    	///Top Left To Bottom Right\\\
    	for(int i=0;i<spots*spots - (spots*length)+spots;i+=spots+1) {
    		for(int j=i;j<i+(spots*length);j+=spots+1)
    			curWin.add(j+1);
    		wins.add(new ArrayList<Integer>(curWin));
    		curWin.clear();
    	}
    	///Top Right To Bottom Left\\\
    	for(int i=spots-1;i<spots*spots - (spots*length) + length;i+=spots-1){
    	    for(int j=i;j<i+length*(spots-1);j+=spots-1)
    	        curWin.add(j+1);
    	    wins.add(new ArrayList<Integer>(curWin));
    	    curWin.clear();
    	}
    }
	
	public ArrayList<Tile> getTiles(){
		return tiles;
	}
	
	public List<List> getWins(){
		return wins;
	}
	
	public void addTiles() {
		for(int i = x;i<x+size;i+=space) {
			for(int j=y;j<y+size;j+=space) {
				tiles.add(new Tile(i,j,space, num));
				num++;
			}
		}
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
		g.setColor(c);
		for(int i = space;i<size;i+=space) {
			g.drawLine(x+i, y, x+i, y+size);
		}
		for(int i= space;i<size;i+=space) {
			g.drawLine(x, y+i, x+size, y+i);
		}
		for(Tile t: tiles)
			t.draw(g);
		repaint();
	}
}