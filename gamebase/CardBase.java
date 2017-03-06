/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamebase;

import java.io.Serializable;

/**
 *
 * @author planb
 */

//superclass that handles cardtypes from both games
public class CardBase implements Serializable {
    
	private String name;
    private int baseAttack;
    private int baseHealth;
    private int damage;
    
    public CardBase(String _name, int Attack, int Health){
        
    	name = _name;
        this.baseAttack = Attack;
        this.baseHealth = Health;
        damage = Attack;
    }
    
    public void setAttack(int Attack){
        this.baseAttack = Attack;
    }
    
    public void setDamage(int _damage){
    	damage = _damage;
    }
    
    public int getDamage(){
        return this.damage;
    }
    
    public void setHealth(int Health){
        this.baseHealth = Health;
    }
    
    public int getHealth(){
        return this.baseHealth;
    }
    
    public int getAttack(){
        return this.baseAttack;
    }
    
   public void ability(Player one, Player two){
       // child classes will overload this.
   }
    
    @Override
     public String toString(){
        return(name + " Attack " + getAttack() + " Health " + getHealth());
    }
    
    public String getName(){
    	return name;
    }
    
    public void checkName() {
        System.out.println(name);
    }
   
}
