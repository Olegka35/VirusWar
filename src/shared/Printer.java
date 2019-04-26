package shared;

import GUI.UI;

import java.rmi.RemoteException;

public class Printer implements PrintingInterface {
    private UI ui;

    public void setUi(UI ui) {
        this.ui = ui;
    }

    @Override
    public void printGamingField(int[] status) throws RemoteException {
        ui.changeIcon(new Field(status[0],status[1]),status[2]);
    }
}
