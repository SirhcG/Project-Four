package cardmonsters;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class CampaignClient extends UnicastRemoteObject {

	protected CampaignClient() throws RemoteException {
		super();
	}

	CampaignBattleProxy p;
	
	public void startClient(){
		try{
			
			Registry myRegistry = LocateRegistry.getRegistry("127.0.0.1",1099);
			
			p = (CampaignBattleProxy) myRegistry.lookup("cardgame");
			
			p.begin();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
