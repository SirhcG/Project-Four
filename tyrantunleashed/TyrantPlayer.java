/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tyrantunleashed;

import java.util.ArrayList;

import gamebase.CardBase;
import gamebase.Player;
/**
 *
 * @author planb
 */
public class TyrantPlayer extends Player{
    
    private int Health;

    
    public TyrantPlayer(String _name, int Health , int cardAmount, StrategyType s){ //why do you need to pass in cardAmmount? is this just the maximum field and handsize? 
    	super(_name);
    	
        this.Health = Health;
        
        if(s == StrategyType.SIMPLE)
        	strategy = new SimpleTyrantStrategy(this);
        else if (s == StrategyType.DEFENSIVE)
        	strategy = new DefensiveTyrantStrategy(this);
        
    }
    
    public void setHealth(int Health){
        this.Health = Health;
    }
    
    public int getHealth(){
        return this.Health;
    }
    
    public void setCardInField(){
        this.CardsInField++;
    }
    
    public int getCardInField(){
        
    	return Field.size();
    }
    
    public void removeCard(){
       int temp = this.CardsInField--;
       if(temp < 0){
           this.CardsInField = 0;
       }
    }
    
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
    }
    
    public void showInfo(){
    	System.out.println("\n"+ name + " INFORMATION");
        System.out.println("Health : " + getHealth());
        System.out.println(name + " HAND");
        System.out.println(getHand());
        System.out.println(name + " FIELD");
        System.out.println(getField());
    }
    
}
