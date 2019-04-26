package GUI;

import shared.Field;
import shared.GameInterface;
import shared.PrintingInterface;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

public class UI extends JFrame{

    private static final int CLEAR = -1;
    private static final int X = 0;
    private static final int O = 1;
    private static final int XDESTRUCTED = 2;
    private static final int ODESTRUCTED = 3;

    private GameInterface gameInterface;
    private PrintingInterface printingInterface;
    private Container container;
    private JButton[][] fields;
    private boolean player;

    public UI(boolean Player, GameInterface gameInterface_, PrintingInterface printingInterface_) {
        super((Player ? "Second":"First") +" Player");
        player = Player;
        this.setBounds(100,100,250,100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        container = this.getContentPane();
        container.setLayout(new GridLayout(10,10,2,2));

        container = new JPanel();
        fields = new JButton[10][10];
        for(int i=0; i<10; i++){
            for(int j=0; j<10; j++){
                fields[i][j] = new JButton();
                fields[i][j].addActionListener(new ButtonEventListener(i,j));
            }
        }
        for(int i=0; i<10; i++){
            for(int j=0; j<10; j++){
                this.add(fields[i][j]);
            }
        }
        this.pack();
        gameInterface = gameInterface_;
        printingInterface = printingInterface_;
    }

    public void changeIcon(Field field, int type){
        int i = field.getNumericField(), j = field.getWordField();
        switch (type){
            case CLEAR:
                try{
                    Image img = ImageIO.read(getClass().getResource("CLEAR.bmp"));
                    fields[i][j].setIcon(new ImageIcon(img));
                } catch(Exception e){
                    e.printStackTrace();
                }
                break;
            case X:
                try{
                    Image img = ImageIO.read(getClass().getResource("X.bmp"));
                    fields[i][j].setIcon(new ImageIcon(img));
                } catch(Exception e){
                    e.printStackTrace();
                }
                break;
            case O:
                try{
                    Image img = ImageIO.read(getClass().getResource("O.bmp"));
                    fields[i][j].setIcon(new ImageIcon(img));
                } catch(Exception e){
                    e.printStackTrace();
                }
                break;
            case XDESTRUCTED:
                try{
                    Image img = ImageIO.read(getClass().getResource("XDESTRUCTED.bmp"));
                    fields[i][j].setIcon(new ImageIcon(img));
                } catch(Exception e){
                    e.printStackTrace();
                }
                break;
            case ODESTRUCTED:
                try{
                    Image img = ImageIO.read(getClass().getResource("ODESTRUCTED.bmp"));
                    fields[i][j].setIcon(new ImageIcon(img));
                } catch(Exception e){
                    e.printStackTrace();
                }
                break;
        }
    }

    class ButtonEventListener implements ActionListener{

        private int i,j;

        public ButtonEventListener(int i_, int j_){
            i = i_;
            j = j_;
            changeIcon(new Field(i,j), CLEAR);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                int[] status = gameInterface.turn(new Field(i,j),player);
                changeIcon(new Field(status[0],status[1]), status[2]);
                printingInterface.printGamingField(status);
            } catch (RemoteException ex){
                ex.printStackTrace();
            }
        }
    }
}
