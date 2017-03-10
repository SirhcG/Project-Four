/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardmonsters;

import gamebase.Player;
import gamebase.CardBase;
//import tyrantunleashed.*;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author planb
 */

//sets up and starts the cardmonsters game
public class CardMonsters {
    
        AbstractFactory cardFactory;
        AbstractFactory playerFactory;
        MonsterPlayer copy;
        MonsterPlayer first;
        MonsterPlayer second;
        MonsterPlayer third;
        MonsterPlayer fourth;
        MonsterPlayer fifth;
        CampaignBattle battle;
        MonsterDuel battle2;
        CampaignServer mcserver;
        CampaignClient mcclient;
        
	
     public CardMonsters() {
         
    	 //factories
         cardFactory = FactoryProducer.getFactory("card");
         playerFactory = FactoryProducer.getFactory("player");
         
         //creating the cards
         CardBase one = cardFactory.getCard("melee");
         CardBase two = cardFactory.getCard("melee");
         CardBase three = cardFactory.getCard("melee");
         
         CardBase four = cardFactory.getCard("melee");
         CardBase five = cardFactory.getCard("melee");
         CardBase six = cardFactory.getCard("melee");
         
         CardBase seven = cardFactory.getCard("melee");
         CardBase eight = cardFactory.getCard("melee");
         CardBase nine = cardFactory.getCard("melee");
         
         CardBase ten = cardFactory.getCard("melee");
         CardBase eleven = cardFactory.getCard("melee");
         CardBase twelve = cardFactory.getCard("melee");
         
         CardBase a = cardFactory.getCard("melee");
         CardBase b = cardFactory.getCard("melee");
         CardBase c = cardFactory.getCard("melee");
         
         
         
         first = playerFactory.getPlayer("user");
         second = playerFactory.getPlayer("opponent");
         third = playerFactory.getPlayer("opponent");
         fourth = playerFactory.getPlayer("opponent");
         fifth = playerFactory.getPlayer("opponent");
         
         ArrayList<MonsterPlayer> opp = new ArrayList<>();
         opp.add(second);
         opp.add(third);
         opp.add(fourth);
         opp.add(fifth);
        
         //putting the cards into the hand ArrayList
         ArrayList<CardBase> val = new ArrayList<>();
         val.add((CardBase) one);
         val.add((CardBase) two);
         val.add((CardBase) three);
         
         ArrayList<CardBase> val2 = new ArrayList<>();
         val2.add((CardBase) four);
         val2.add((CardBase) five);
         val2.add((CardBase) six);
         
         ArrayList<CardBase> val3 = new ArrayList<>();
         val3.add((CardBase) seven);
         val3.add((CardBase) eight);
         val3.add((CardBase) nine);
         
         ArrayList<CardBase> val4 = new ArrayList<>();
         val4.add((CardBase) ten);
         val4.add((CardBase) eleven);
         val4.add((CardBase) twelve);
         
         ArrayList<CardBase> val5 = new ArrayList<>();
         val5.add((CardBase) a);
         val5.add((CardBase) b);
         val5.add((CardBase) c);
         
        // System.out.println(ten.find(1).one);
         
         //attaches the hand to the player
         first.setCards(val);
         second.setCards(val2);
         third.setCards(val3);
         fourth.setCards(val4);
         fifth.setCards(val5);
         battle = new CampaignBattle(first, opp);
         battle2 = new MonsterBattle(first, opp);
        
         
         
        }
     
     //starts the cardmonsters game
     public void go() throws RemoteException{
         
         System.out.println("Please choose between simple campaign [1] or advance campaign [2] or host a campaign battle as server[3] or connect to remote battle as client[4]\n");
         Scanner choice = new Scanner(System.in);
         while(true){
         System.out.print(">  ");
         int val = choice.nextInt();
         if(val == 1){ 
            battle.begin();
            break;
         }else if(val == 2){
            battle2.begin();
            break;
         }else if(val == 3){
        	 mcserver = new CampaignServer(battle);
        	 mcserver.startServer();
         }else if(val == 4){
        	 mcclient = new CampaignClient();
        	 mcclient.startClient();
         }else
             System.out.println("Invalid Entry\n");
         }  
     }
    
 }


