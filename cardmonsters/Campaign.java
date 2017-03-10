package cardmonsters;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Campaign extends Remote { //interface for CampaignProxy
	void begin() throws RemoteException;
}
