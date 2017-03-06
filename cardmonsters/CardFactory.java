/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardmonsters;

import gamebase.CardBase;

/**
 *
 * @author planb
 */

//CardFactory creates and returns new cards to the caller with the type cardType
public class CardFactory extends AbstractFactory {

    @Override
    CardBase getCard(String cardType) {
        
        if(cardType == null){
            return null;
        }
        
        if(cardType.equalsIgnoreCase("Melee")){
            return new Melee("Melee", 20, 50);
        }
        
        if(cardType.equalsIgnoreCase("Ranger")){
            return new Ranger("Ranger", 10, 30);
        }
        
        if(cardType.equalsIgnoreCase("Healer")){
            return new Healer("Healer", 0, 70);
        }
        
        if(cardType.equalsIgnoreCase("Heal")){
            return new Heal("Heal", 0, 0);
        }
        
        if(cardType.equalsIgnoreCase("Reach")){
            return new Reach("Reach", 10, 30);
        }
        
        return null;
}

    @Override
    //Stub
    MonsterPlayer getPlayer(String Player) {
        return null;
    }
}
