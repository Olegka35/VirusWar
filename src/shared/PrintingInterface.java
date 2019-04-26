package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PrintingInterface extends Remote {
    void printGamingField(int[] status) throws RemoteException;
}
