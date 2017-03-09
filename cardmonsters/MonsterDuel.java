/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardmonsters;

import gamebase.Move;
import gamebase.Player;
import gamebase.Player.StrategyType;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;



/**
 *
 * @author planb
 */

//the superclass that handles battle logic for cardmonsters
public abstract class MonsterDuel implements Serializable{
    
        private ArrayList<MonsterPlayer> players;
        private CampaignTree opponents;
	MonsterMove move;
	MonsterdoMoveCommand mCommand;
	int dmg = 0, health = 0, val = 0, turn = 1, oppCount = 1, size = 0, count = 0, check = 0, num = 0, keyVal = 0;
    
    public MonsterDuel(MonsterPlayer one, ArrayList<MonsterPlayer> two){
    	
        opponents = new CampaignTree();
        for(int i = 0; i<two.size(); i++){
            opponents.addNode(i, two.get(i), two.get(i).getName());
        }
        num = two.size();
        size = two.size();
        players = new ArrayList<>();
        for(int i=0; i<two.size(); i++){
        players.add(deepClone(one));
    }
    }
    
    public void begin(){
        
        Scanner val = new Scanner(System.in);
        int input = 0;
        
        checkSave();		
        
    
        System.out.println("Welcome to the card monster campaign!!");        
        System.out.println("Opponents remaining: " + num + "\n");
        MonsterPlayer clone = players.get(0);
        
        while(true){
            opponents.inOrder(opponents.root);
            System.out.println("\nPlease choose a listed opponent!! \n");
            System.out.print(">  ");
            Scanner choice = new Scanner(System.in);
            int value = choice.nextInt();
            if(opponents.find(value) == null){
                System.out.println("Invalid Entry \n");
                continue;
            }else{
                keyVal = value;
                break;
            }
            
        }
        
        while(true){
            
                myTurn();
                
    		oppTurn();
                
    		endOfTurn(); //end of turn stuff
    	}
    }
    
    public void oppTurn(){
        
        Scanner val = new Scanner(System.in);
        int input = 0;
        
        MonsterPlayer boss = opponents.find(keyVal).one;
        MonsterPlayer clone = players.get(0);
        
        
        System.out.println("\nOpponent's turn starts!\n");
    		stop();
    		
    		move = (MonsterMove) boss.getStrategy().nextMove();
    		if (move != null){
    			
    			mCommand = new MonsterdoMoveCommand(boss, (MonsterMove) move);
    			mCommand.execute();
    			stop();
    		}else
    			System.out.println("\nOpponent has no cards to play! \n");
    		
    		 //conducts battle for player 2
                
                check();
    		battle(2);
                --check;
                check();
        
    }
    
    public void myTurn(){
        
        Scanner val = new Scanner(System.in);
        int input = 0;
        
        MonsterPlayer boss = opponents.find(keyVal).one;
        MonsterPlayer clone = players.get(0);
        
        clone.showInfo();
        boss.showInfo();
            
            if(clone.getHand().size() > 0){
    			
    			System.out.println("\nChoose which card to play. Numbering starts at 0\n");
        		input = val.nextInt(); //input for which card to play
        		System.out.print(">  ");
                        
	    		while(input >= clone.getHand().size()){ //if invalid number
	    			System.out.println("\nInvalid choice. Choose which card to play. Numbering starts at 0\n");
	        		input = val.nextInt();
	    		}
	    		
	    		move = new MonsterMove(input); //make the move
	    		mCommand = new MonsterdoMoveCommand(clone, move); //attempting to implement command pattern
	    		mCommand.execute();
	    		
    		}
    		else{
    			System.out.println("\nYou have no cards to play. \n");
    			stop();
    		}
            
                clone.showInfo();
    		boss.showInfo();
    		
                check();
    		battle(1); //conducts battle for player 1
                ++check;
    		stop(); //waits for user button press to continue
    		check();
        
        
        
        
    }
    
    public void checkSave(){
        
         while(true){
                System.out.println("Do you have a perviously saved game?? [Y/N] \n");
                System.out.print(">  ");
                Scanner choice = new Scanner(System.in);
                char s = choice.next().charAt(0);
                if(Character.isWhitespace(s)){
                    continue;
                }
                if(s == 'Y'  || s == 'y'){
                    read();
                    if(check == 1 && players.get(0).getCardInField() > 0){
                        oppTurn();
                        check = 0;
                    }else if(check == 0 && opponents.find(keyVal).one.getCardInField() > 0){
                        begin();
                    }
                    break;
                }else if(s == 'N' || s == 'n'){
                    break;
                }else
                    System.out.println("Invalid Entry");
                }
        
        
    }
     
    
    public void battle(int turnPlayer){
	   
    	if(turn == 1) //cannot attack on first turn
    		return;
    	
	   //if its the players turn
		MonsterPlayer one = players.get(0);
		MonsterPlayer two = opponents.find(keyVal).one;

		//if its the opponents turn
		if(turnPlayer == 2){
			one = opponents.find(keyVal).one;
			two = players.get(0);
		}
	   
		//one.showInfo();
		
		
		
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
   
   private void check(){
	   if(players.get(0).getCardInField() == 0 && turn > 2){
		   System.out.println("OPPONENT WINS");
		   System.exit(0);
		   
	   }   
	   else if(opponents.find(keyVal).one.getCardInField() == 0 && turn > 2){
		   System.out.println("PLAYER BEAT OPPONENT " + oppCount);
                   oppCount++;
                   ++count;
                   turn = 1;
                   
                   if(size == count){
                       System.out.println("Out of Opponents Game Over!!\n");
                       System.exit(0);
                   }else
                   update();
                   save();
                  // stop();
                   begin();
                   
           }
   }
   
   private void save(){
       while(true){
       System.out.println("Would you like to save?? [Y/N] ");
       System.out.print(">  ");
       Scanner choice = new Scanner(System.in);
       char s = choice.next().charAt(0);
       if(s == 'Y'  || s == 'y'){
       try{
       FileOutputStream out = new FileOutputStream("Campaign.txt");
       ObjectOutputStream oos = new ObjectOutputStream(out);
       oos.writeObject(MonsterDuel.this);
       oos.close();
       System.exit(0);
       }catch(Exception e){
           System.out.println(e);
       }
       }
       else if(s == 'N' || s == 'n'){
           break;
       }
       else
           System.out.println("Invalid Entry");
       }
    }
   
   private void betweenSave(){
       save();
    }
   
   private void read(){
       try{
       FileInputStream fis = new FileInputStream("Campaign.txt");
       ObjectInputStream ois = new ObjectInputStream(fis);
       MonsterDuel resume = (MonsterDuel) ois.readObject();
       ois.close();
       opponents = resume.opponents;
       players = resume.players;
       oppCount = resume.oppCount;
       count = resume.count;
       check = resume.check;
       num = resume.num;
       }catch(Exception e){
           System.out.println(e);
           System.out.println("\nYou do not have a perviously saved game please restart the game!!");
           System.exit(0);
           
       }
   }
   
   //updates opponent array between games?
   private void update(){
      
       players.remove(0);
       players.trimToSize();
       opponents.remove(keyVal);
       --num;
               
   }

   //all this does is pause the game and let you read the output
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
	   
	   for(int i = 0; i< opponents.find(keyVal).one.getHand().size();i++){ //clears any temporary damage boosts for opponent
		   opponents.find(keyVal).one.getHand().get(i).setDamage(opponents.find(keyVal).one.getHand().get(i).getAttack());
	   }
	   
	   turn++;
	   
   }
   
    public static MonsterPlayer deepClone(MonsterPlayer object) 
   {
   
       try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            
            return (MonsterPlayer) ois.readObject();
            }catch (Exception e) {
                    e.printStackTrace();
                    return null;
            }
    }
}

    

