package tyrantunleashed;

import java.io.IOException;

import gamebase.Move;
import gamebase.Player; 

public class doMoveCommand implements Command{

	Move m;
	Player p;
	
	public doMoveCommand(Player _p, Move _m){
		p = _p;
		m = _m;
	}
	
	@Override
	public void execute() {
		
		m.go(p);
	}

}
