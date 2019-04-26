package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GameInterface extends Remote {
    int[] turn(Field field,boolean current_player) throws RemoteException;
    void startGame() throws RemoteException;
    boolean isGameEnded() throws RemoteException;
}
