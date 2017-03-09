/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardmonsters;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author planb
 */

public class MonsterBattle extends MonsterDuel implements Serializable {
    
     public MonsterBattle(MonsterPlayer one, ArrayList<MonsterPlayer> two) {
        super(one, two);
    }
    
}
