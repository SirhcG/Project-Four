package cardmonsters;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CampaignServer extends UnicastRemoteObject {

	CampaignBattleProxy p;

	public CampaignServer(CampaignBattle c) throws RemoteException{
		p = new CampaignBattleProxy(c);
	}

	public void startServer() {
		try {
			Registry registry = LocateRegistry.createRegistry(1099);
			registry.rebind("cardgame", p);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("server is ready");
	}
	
	
}
