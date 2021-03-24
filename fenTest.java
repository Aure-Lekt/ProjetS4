import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class fenTest extends JFrame implements ActionListener  {
    
    ObjetMobile O;
    public int dT=10;
    public double G = 1000.0;  //bas 1m=100pix
    public Timer Chrono;

    public fenTest(ObjetMobile ob) {
        super("TEST");
        
        O=ob;
        
        setLocation(300,200);
        setSize(1280,720);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Chrono = new Timer(dT,this);
        Chrono.start();
        
    }
    
    public void paint(Graphics g){
        g.setColor(Color.blue);
        g.fillRect(0,0,this.getWidth(),this.getHeight());
        O.affiche(g);
    }
    
    public void actionPerformed(ActionEvent e){
        if (e.getSource()==Chrono)  {
                O.updateVitesseY(dT,G);
                O.updatePosition(dT);
            repaint();
        }
    }
}



