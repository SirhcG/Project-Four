/**
 * 
 */
package UnitTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cardmonsters.CardFactory;
import cardmonsters.DefensiveMonsterStrategy;
import cardmonsters.MonsterMove;
import cardmonsters.MonsterPlayer;
import gamebase.CardBase;
import gamebase.Player.StrategyType;

/**
 * @author Huaqing Liu
 *
 */
public class CardMonsterTest {

	/**
	 * @throws java.lang.Exception
	 */
	private CardBase s;
	private CardFactory cf;
	MonsterPlayer p;
	MonsterPlayer o;
	DefensiveMonsterStrategy DMS;
	//this class will set up 1 player and 1 opponent
	//and also will set up a set of cards
	@Before
	public void setUp() throws Exception {
		s = new CardBase("test", 5,5);
		cf = new CardFactory();
		p = new MonsterPlayer("Player",StrategyType.DEFENSIVE);
		o = new MonsterPlayer("Opponent", StrategyType.DEFENSIVE);
		ArrayList<CardBase> cards = new ArrayList<CardBase>();
		for(int i = 0;i<5;i++)
		{
			cards.add(cf.getCard("Melee"));
		}
		p.setCards(cards);// this will give player 5 melee cards
		DMS = new DefensiveMonsterStrategy(p);
		ArrayList<CardBase> o_cards = new ArrayList<CardBase>();
		for(int i = 0;i<5;i++)
		{
			o_cards.add(cf.getCard("Melee"));
		}
		o.setCards(o_cards);// this will give opponent 5 melee cards

	}
	@Test
	public void TestGetMethods()
	{
		assertEquals(s.getAttack(),5);
		assertEquals(s.getDamage(),5);
		assertEquals(s.getName(),"test");	
	}
	@Test
	public void TestSetMethods()
	{
		s.setAttack(6);
		s.setHealth(6);
		assertEquals(s.getAttack(),6);
		assertEquals(s.getHealth(),6);
	}
	/**
	 * @throws java.lang.Exception
	 */
	
	@Test
	public void MeleeTest()
	{
		CardBase tester = cf.getCard("Melee");
		assertEquals(tester.getName(),"Melee");
		assertEquals(tester.getAttack(),20);
		assertEquals(tester.getHealth(),50);
		tester.ability(p, o);
	}
	@Test
	public void RangerTest()
	{
		CardBase tester = cf.getCard("Ranger");
		assertEquals(tester.getName(),"Ranger");
		assertEquals(tester.getAttack(),10);
		assertEquals(tester.getHealth(),30);
		tester.ability(p, o);
	}
	
	@Test
	public void HealerTest()
	{
		CardBase tester = cf.getCard("Healer");
		assertEquals(tester.getName(),"Healer");
		assertEquals(tester.getAttack(),0);
		assertEquals(tester.getHealth(),70);
		tester.ability(p, o);
	}
	
	@Test
	public void HealTest()
	{
		CardBase tester = cf.getCard("Heal");
		assertEquals(tester.getName(),"Heal");
		assertEquals(tester.getAttack(),0);
		assertEquals(tester.getHealth(),0);	
		tester.ability(p, o);
	}
	
	@Test
	public void ReachTest()
	{
		CardBase tester = cf.getCard("Reach");
		assertEquals(tester.getName(),"Reach");
		assertEquals(tester.getAttack(),10);
		assertEquals(tester.getHealth(),30);
		tester.ability(p, o);
	}
	@Test
	public void DMSTest()
	{
		assertEquals(DMS.nextMove(),new MonsterMove(0));
	}
	@Test
	public void MCampaginTest()
	{
		
	}
	
	@After
	public void tearDown() throws Exception {
		p.setCards(null);
	}
	
	@Test
	public void DMSTestNull()
	{
		assertNull(DMS.nextMove());
	}


}
