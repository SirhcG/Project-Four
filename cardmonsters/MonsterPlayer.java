/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardmonsters;
import gamebase.CardBase;
import gamebase.Player;
import java.io.Serializable;

import java.util.ArrayList;

import tyrantunleashed.*;
/**
 *
 * @author planb
 */

//handles the player logic for cardmonsters
public class MonsterPlayer extends Player implements Serializable {
    
    private static final long serialVersionUID = 6831716097066398573L;
    
    public MonsterPlayer(String _name, StrategyType s){  
    	super(_name);
    	name = _name;
        
    	//pick strategy for computer
        if(s == StrategyType.SIMPLE)
        	strategy = new SimpleMonsterStrategy(this);
        else if (s == StrategyType.DEFENSIVE)
        	strategy = new DefensiveMonsterStrategy(this);
        
    }
    
    
    
   
    //attach array of cards to the hand
    public void setCards(ArrayList<CardBase> a){ 
        this.Hand = a;
    }
    
    //attach array of cards to the field
    public void setField(ArrayList<CardBase> b){
        this.Field = b;
    }
    
    
    //getter for turn
    public int getTurn(){
        return this.turnCounter;
    }
    
    //setter for turn
    public void setTurn(){
        this.turnCounter++;
    }
    
    //increase card counter
    public void setCardInField(){
        this.CardsInField++;
    }
    

    //removes a card from the field in first position
    public void removeCard(){
       int temp = this.CardsInField--;
       if(temp < 0){
           this.CardsInField = 0;
       }
    }
    
    /* UNUSED METHODS
    public void setCheck(){
        this.Check++;
    }
    public int Check(){
        return Check;
    }
    
    
    public void setPosition(){
        this.FieldPosition++;
    }
    
    public int getPosition(){
        return FieldPosition;
    }
    
    public void removePosition(){
        int temp = this.FieldPosition--;
        if(temp < 0){
            this.FieldPosition = 0;
        }
    }

    public void setCard(){
        
       CardBase temp = getHandCard(0); 
       Field.set(FieldPosition, temp); 
       Hand.remove(0);
       Hand.trimToSize(); 
    }*/
    
   public CardBase getCard(int index){ 
       return Field.get(index);
   }
   
   public CardBase getHandCard(int index){ 
       return Hand.get(index);
   }
   
   
  
   //remove card from field from specific index
   public void remove(int index){ 
       Field.remove(index);
   }
   
   //remove card from hand from specific index
   public void removeHand(int index){ 
        Hand.remove(index);
        
   }
    
  
    public ArrayList<CardBase> getField(){
    	return Field;
    }
    
    public ArrayList<CardBase> getHand(){
        return Hand;
    }
    
    public void playCard(int i){ //plays a card from hand to field
    	
    	CardBase c = Hand.get(i);
    	Field.add(c);
    	Hand.remove(i);
    }
    
    public void doMove(MonsterMove move){ //takes in a move object and does that move
    	playCard(move.handIndex);
    	
    	
    }
    
    public void showInfo(){
    	System.out.println("\n"+ name + " INFORMATION");
        System.out.println(name + " HAND");
        System.out.println(getHand());
        System.out.println(name + " FIELD");
        System.out.println(getField());
    }
    
    public String getName(){
    	return name;
    }
}

    

