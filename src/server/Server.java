package server;

import GUI.UI;
import shared.*;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class Server {
    private static final boolean MY_PLAYER = false;
    private static Game game1;
    private static Scanner in;
    private static GameInterface gameInterface;
    private static Registry registry1, registry2;
    private static Printer serverPrinter;
    private static PrintingInterface toClientPrinter;
    private static PrintingInterface serverPrinterInterface;

    public static void main(String[] args) {
        Connection connection = new Connection();

        in = new Scanner(System.in);

        System.out.println("Server Started");
        try {
            game1 = new Game();
            serverPrinter = new Printer();

            //RMI Server registration
            gameInterface = (GameInterface) UnicastRemoteObject.exportObject(game1, 0);
            serverPrinterInterface = (PrintingInterface) UnicastRemoteObject.exportObject(serverPrinter,0);

            registry1 = LocateRegistry.createRegistry(connection.getServerPort());
            registry1.rebind(connection.getServerGameName(), gameInterface);
            registry1.rebind(connection.getServerPrintingName(), serverPrinter);

            System.out.println("Waiting Opponent");

            //RMI Client
            while(!game1.isGameStarted()){
                Thread.sleep(1000);
            }
            registry2 = LocateRegistry.getRegistry(connection.getClientPort());
            toClientPrinter = (PrintingInterface) registry2.lookup(connection.getClientPrintingName());

            System.out.println("Opponent Founded");

            UI ui = new UI(MY_PLAYER, game1, toClientPrinter);
            ui.setVisible(true);
            serverPrinter.setUi(ui);
            while(!game1.isGameEnded()) {
            }
        } catch (Exception e) {
            System.out.println("Server cannot create the game");
            e.printStackTrace();
        }
    }
}
