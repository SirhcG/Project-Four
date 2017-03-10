package cardmonsters;

import java.io.Serializable;
import java.rmi.RemoteException;

public class CampaignBattleProxy implements Serializable, Campaign{
	CampaignBattle realCampaign;

	private static final long serialVersionUID = -1789161115751330715L;
	public CampaignBattleProxy(CampaignBattle c){
		realCampaign = c;
	}
	
	@Override
	public void begin() throws RemoteException {
		
		System.out.println("Starting remote campaign now: ");
		realCampaign.begin();
		
	}
	
}
