package gamebase;
import cardmonsters.*;

import java.rmi.RemoteException;
import java.util.Scanner;
import tyrantunleashed.*;

public class game {

	public static void main(String[] args) throws RemoteException {
		
		//Only plays the new game, old game still works
		CardMonsters game = new CardMonsters();
                game.go();
              
		TyrantUnleashed game2 = new TyrantUnleashed();
		//game2.go();

	}

}
