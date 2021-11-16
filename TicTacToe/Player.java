import java.util.List;
import java.awt.*;
import java.util.*;

public class Player {
	
	private String name;
	private int id;
	private List<Integer> moves;
	Image dbi;
	Graphics dbg;
	
	public Player(String name, int id) {
		this.name = name;
		this.id = id;
		moves = new ArrayList<Integer>();
	}
	
	public String getName() {
		return name;
	}
	
	public int id() {
		return id;
	}
	
	public void addMove(int move) {
		moves.add(move);
	}
	
	public List<Integer> getMoves(){
		return moves;
	}
}