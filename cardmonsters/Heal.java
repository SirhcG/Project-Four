/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardmonsters;

import java.util.Scanner;

import gamebase.CardBase;
import gamebase.CardSkill;
import tyrantunleashed.*;

/**
 *
 * @author planb
 */

//a card that heals another card
public class Heal  extends CardBase implements CardSkill {
    
    private int healAmount;
    
    public Heal(String name, int attack, int health){
        super(name, 0, 0);
        this.healAmount = 20;
        
    }
    
    //heal another card
    public void ability(MonsterPlayer p, MonsterPlayer o){
        System.out.println("Please choose a monster to heal");
        Scanner in = new Scanner(System.in);
        int val = in.nextInt();
        int base = p.getCard(val).getHealth();
        base = base + healAmount;
        p.getCard(val).setHealth(base);
        p.getField().remove(this);
        
    }

    //notification
    @Override
    public void checkSkill() {
        System.out.println("Equip card heals a monster by 20hp points!!");
    }
    
}
