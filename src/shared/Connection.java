package shared;

import java.util.Scanner;

public class Connection {
    private static final int serverPort = 48654;
    private static final int clientPort = 49001;



    public String getServerGameName() {
        return "rmi://localhost/GameInterface";
    }
    public String getClientPrintingName() {
        return "rmi://localhost/PrintingInterface";
    }
    public String getServerPrintingName() {
        return "rmi://localhost/PrintingInterface";
    }

    public int getClientPort() {
        return clientPort;
    }

    public int getServerPort() {
        return serverPort;
    }
}
