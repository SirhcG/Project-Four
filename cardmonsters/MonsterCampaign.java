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

//class that handles the campaign mode in cardmonsters
public abstract class MonsterCampaign implements Serializable, Campaign {
   
    private static final long serialVersionUID = -1789160225751330715L;
    private ArrayList<MonsterPlayer> opponents;
    private ArrayList<MonsterPlayer> clones;
    MonsterMove move;
    MonsterdoMoveCommand mCommand;
    int dmg = 0, health = 0, val = 0, turn = 1, oppCount = 1, size = 0, count = 0, check = 0;
    
    //constructor, makes array of opponents
    public MonsterCampaign(MonsterPlayer main, ArrayList<MonsterPlayer> array){
        opponents = new ArrayList<>();
        opponents = array;
        size = opponents.size();
        clones = new ArrayList<>();
        for(int i=0; i<opponents.size(); i++){
          clones.add(deepClone(main));
        }
    }
    
    //start campaign
    public void begin(){
        
        Scanner val = new Scanner(System.in);
        int input = 0;
        
        checkSave();		
        
    
        System.out.println("Welcome to the card monster campaign!!");        
        System.out.println("Opponents remaining: " + opponents.size());
        MonsterPlayer boss = opponents.get(0);
        MonsterPlayer clone = clones.get(0);
        
        
        while(true){
            
                myTurn();
                System.out.println(check);
                betweenSave();
    		oppTurn();
                System.out.println(check);
                betweenSave();
    		endOfTurn(); //end of turn stuff
    	}
    }
    
    public void oppTurn(){
        
        Scanner val = new Scanner(System.in);
        int input = 0;
        
        MonsterPlayer boss = opponents.get(0);
        MonsterPlayer clone = clones.get(0);
        
        
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
        
        MonsterPlayer boss = opponents.get(0);
        MonsterPlayer clone = clones.get(0);
        
        clone.showInfo();
        boss.showInfo();
            
            if(clone.getHand().size() > 0){
    			
    			System.out.println("\nChoose which card to play. Numbering starts at 0\n");
        		input = val.nextInt(); //input for which card to play
        		
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
                Scanner choice = new Scanner(System.in);
                char s = choice.next().charAt(0);
                if(Character.isWhitespace(s)){
                    continue;
                }
                if(s == 'Y'  || s == 'y'){
                    read();
                    if(check == 1 && clones.get(0).getCardInField() > 0){
                        oppTurn();
                        check = 0;
                    }else if(check == 0 && opponents.get(0).getCardInField() > 0){
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
		MonsterPlayer one = clones.get(0);
		MonsterPlayer two = opponents.get(0);

		//if its the opponents turn
		if(turnPlayer == 2){
			one = opponents.get(0);
			two = clones.get(0);
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
	   if(clones.get(0).getCardInField() == 0 && turn > 2){
		   System.out.println("OPPONENT WINS");
		   System.exit(0);
		   
	   }   
	   else if(opponents.get(0).getCardInField() == 0 && turn > 2){
		   System.out.println("PLAYER BEAT OPPONENT " + oppCount);
                   oppCount++;
                   ++count;
                   turn = 1;
                   //begin();
                   
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
       Scanner choice = new Scanner(System.in);
       char s = choice.next().charAt(0);
       if(s == 'Y'  || s == 'y'){
       try{
       FileOutputStream out = new FileOutputStream("Campaign.txt");
       ObjectOutputStream oos = new ObjectOutputStream(out);
       oos.writeObject(MonsterCampaign.this);
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
       MonsterCampaign resume = (MonsterCampaign) ois.readObject();
       ois.close();
       opponents = resume.opponents;
       clones = resume.clones;
       oppCount = resume.oppCount;
       count = resume.count;
       check = resume.check;
       }catch(Exception e){
           System.out.println(e);
           System.out.println("\nYou do not have a perviously saved game please restart the game!!");
           System.exit(0);
           
       }
   }
   
   //updates opponent array between games?
   private void update(){
      
       clones.remove(0);
       clones.trimToSize();
       opponents.remove(0);
       opponents.trimToSize();          
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
	   for(int i = 0; i< clones.get(0).getHand().size();i++){ //clears any temporary damage boosts for player
		   clones.get(0).getHand().get(i).setDamage(clones.get(0).getHand().get(i).getAttack());
	   }
	   
	   for(int i = 0; i< opponents.get(0).getHand().size();i++){ //clears any temporary damage boosts for opponent
		   opponents.get(0).getHand().get(0).setDamage(opponents.get(0).getHand().get(0).getAttack());
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
   
   public void test(){
	   System.out.println("test");
   }
}

            
            
            
    