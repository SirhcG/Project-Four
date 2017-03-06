/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardmonsters;

/**
 *
 * @author planb
 */

//makes factories
public class FactoryProducer {
    
    public static AbstractFactory getFactory(String choice){
        
        if(choice.equalsIgnoreCase("Card")){
            return new CardFactory();
        }
        else if(choice.equalsIgnoreCase("Player")){
            return new PlayerFactory();
        }
        
        return null;
    }
    
}
