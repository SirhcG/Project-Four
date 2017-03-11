/**
 * 
 */
package cardmonsters;

import java.sql.*;
import java.util.ArrayList;

import gamebase.CardBase;
import gamebase.Player.StrategyType;

/**
 * @author Huaqing Liu
 *
 *this class will connect to the database
 *functions:
 *Login feature
 *Sign up feature
 *Change password feature
 *Save feature
 */
public class Database {

	private final String name;
	private final String username;
	private final String passwd;
	private CardFactory cf = new CardFactory();
	
	public Database()
	{
		this.name = "jdbc:mysql://localhost:3306/cardMonster";
		this.username = "yuchihaitachi";
		this.passwd = "leo@1121";
	}
	
	//allow user to Login and get data from database
	//it will return a MonsterPlayer if success
	//it will return a empty MonsterPlayer if fail
	public MonsterPlayer Login(String uname, String userpasswd)
	{
		MonsterPlayer player = new MonsterPlayer(" ",StrategyType.SIMPLE);
		try{  
		Class.forName("com.mysql.jdbc.Driver");  
		Connection con=DriverManager.getConnection(name,username,passwd);  
		Statement stmt=con.createStatement();  
		ResultSet rs=stmt.executeQuery("select * from userinfo where Uname =\"" + uname+"\"");  
		while(rs.next())  
		{
			if(uname.equals(rs.getString(1)))
			{
				if(userpasswd.equals(rs.getString(2)))
				{
					player = new MonsterPlayer(uname, StrategyType.SIMPLE);
					String[] cardName = rs.getString(4).split(",/d+");
					ArrayList<CardBase> cards = new ArrayList<CardBase>();
					for(String str : cardName)
					{
						cards.add(cf.getCard(str));
					}
					player.setCards(cards);
				}
				else
					System.out.print("password does not match");;
			}
			else
				System.out.println("User does not exist");
		}
		con.close(); 
		}catch(Exception e){ System.out.println(e);}  
		return player;
	}
	
	//allow user to create a new user
	//user Name have to be unique, because it primary key
	public String SignUp(String uname, String userPasswd)
	{
		String message = "success";
		try{  
		Class.forName("com.mysql.jdbc.Driver");  
		Connection con=DriverManager.getConnection(name,username,passwd);    
		Statement stmt=con.createStatement();  
		stmt.executeQuery("INSERT INTO `userInfo`(`Uname`, `Passwd`, `Access`, `card`) VALUES \""+uname+"\",\""+userPasswd+"\",1,\" \" ");
		}catch(Exception e){ System.out.println(e);}  
		
		return message;
	}
	
	//allow user to change password
	//user have to provide old password and new password
	//return proper message
	public String ChangePasswd(String uname, String oldPasswd, String newPasswd)
	{
		String message = "success";
		try{  
		Class.forName("com.mysql.jdbc.Driver");  
		Connection con=DriverManager.getConnection(name,username,passwd);  
		Statement stmt=con.createStatement();    
		ResultSet rs=stmt.executeQuery("select * from userinfo where Uname =\"" + uname+"\"");  
		if(rs.getString(2).equals(oldPasswd))
		{
			stmt.executeQuery("update userInfo\n"
							+ "set Passwd = \""+newPasswd+"\"\n"
							+ "where Uname = \""+uname+"\"");
		}
		else
		{
			message = "old password does not match!";
		}
		con.close();  
		}catch(Exception e){ System.out.println(e);}  
		
		return message;
	}
	
	public String Save(MonsterPlayer player)
	{
		String message = "success";
		try{  
		Class.forName("com.mysql.jdbc.Driver");  
		Connection con=DriverManager.getConnection(name,username,passwd);  
		Statement stmt=con.createStatement();  
		String cards = "";
		for(CardBase c : player.getHand())
		{
			cards+=c.getName();
		}
		stmt.executeQuery("update userInfo\n"
						+ "set card =\""+cards+"\"\n"
						+ "where Uname = \""+player.getName()+"\"");
		con.close();  
		}catch(Exception e){ System.out.println(e);} 
		return message;
	}
	
}
