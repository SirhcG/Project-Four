/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardmonsters;

import gamebase.Move;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;



/**
 *
 * @author planb
 */

//the superclass that handles battle logic for cardmonsters
public abstract class MonsterDuel {
    
    private ArrayList<MonsterPlayer> players;
	MonsterMove move;
	MonsterdoMoveCommand mCommand;
	int dmg = 0, health = 0, val = 0, turn = 1;
    
    public MonsterDuel(MonsterPlayer one, MonsterPlayer two){
    	
    	players = new ArrayList<>();
    	players.add(one);
    	players.add(two);
    }
    
    public void start(){
    	
    	Scanner sc = new Scanner(System.in);
    	int input = 0;
    	MonsterPlayer player = players.get(0);
    	MonsterPlayer opp = players.get(1);
    	
    	while(true){
    		player.showInfo();
    		opp.showInfo();
    		

    		if(player.getHand().size() > 0){
    			
    			System.out.println("\nChoose which card to play. Numbering starts at 0\n");
        		input = sc.nextInt(); //input for which card to play
        		
	    		while(input >= player.getHand().size()){ //if invalid number
	    			System.out.println("\nInvalid choice. Choose which card to play. Numbering starts at 0\n");
	        		input = sc.nextInt();
	    		}
	    		
	    		move = new MonsterMove(input); //make the move
	    		mCommand = new MonsterdoMoveCommand(player, move); //attempting to implement command pattern
	    		mCommand.execute();
	    		
    		}
    		else{
    			System.out.println("\nYou have no cards to play. \n");
    			stop();
    		}
    			
    		
    		player.showInfo();
    		opp.showInfo();
    		
    		battle(1); //conducts battle for player 1
    		stop(); //waits for user button press to continue
    		
    		System.out.println("\nOpponent's turn starts!\n");
    		stop();
    		
    		move = (MonsterMove) opp.getStrategy().nextMove();
    		if (move != null){
    			
    			mCommand = new MonsterdoMoveCommand(opp, (MonsterMove) move);
    			mCommand.execute();
    			stop();
    		}else
    			System.out.println("\nOpponent has no cards to play! \n");
    		
    		 //conducts battle for player 2
    		battle(2);
    		
    		endOfTurn(); //end of turn stuff
    	}
    }
    
    public void battle(int turnPlayer){
	   
    	if(turn == 1) //cannot attack on first turn
    		return;
    	
	   //if its the players turn
		MonsterPlayer one = players.get(0);
		MonsterPlayer two = players.get(1);

		//if its the opponents turn
		if(turnPlayer == 2){
			one = players.get(1);
			two = players.get(0);
		}
	   
		one.showInfo();
		
		
		
		for(int i=0; i<one.getField().size(); i++){
	
			one.getCard(i).ability(one, two);
	    	   
	    	   if(two.getField().size() > 0){
	    		   dmg = one.getCard(i).getDamage();
		    	   health = two.getCard(0).getHealth();
		    	   val = health - dmg;
	    		   two.getCard(0).setHealth(val);	//deal damage to the card
	    		   
	    		   
	    		   System.out.println(one.getCard(i).getName()+ " attacks " + two.getCard(0).getName() +"!\n");
	    		   stop();
	    		   
	    		   if(two.getCard(0).getHealth() < 0){ // if the opposing card is dead
	    			   System.out.println(one.getCard(i).getName()+ " destroys " + two.getCard(0).getName() + "!\n");
	    			   stop();
	    			   two.getField().remove(0);	//remove it
	    		   }
	    	   }
	    	   
	       }    
   }
   
   private boolean gameOver(){
	   if(players.get(0).getCardInField() == 0){
		   System.out.println("OPPONENT WINS");
		   System.exit(0);
		   return true;
	   }   
	   else if(players.get(1).getCardInField() == 0){
		   System.out.println("PLAYER WINS");
		   System.exit(0);
		   return true;
	   }else
		   return false;
   }

   private void stop(){
	   try {
			System.in.read();
		} catch (IOException e){
			e.printStackTrace();
		}
   }
   
   private void endOfTurn(){ 
	   for(int i = 0; i< players.get(0).getHand().size();i++){ //clears any temporary damage boosts for player
		   players.get(0).getHand().get(i).setDamage(players.get(0).getHand().get(i).getAttack());
	   }
	   
	   for(int i = 0; i< players.get(1).getHand().size();i++){ //clears any temporary damage boosts for opponent
		   players.get(1).getHand().get(i).setDamage(players.get(1).getHand().get(i).getAttack());
	   }
	   
	   turn++;
	   
   }
}

    

