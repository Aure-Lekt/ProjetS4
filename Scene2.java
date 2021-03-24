import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Scene2 extends JFrame implements ActionListener,KeyListener  {

    public final double G = 1000.0;  //gravité vers le bas (y positif) 

	private boolean tZ;  //appui de la touche Z
    private boolean tSpace; //etc...
    private boolean tQ;
    private boolean tD;
    
    private ArrayList<ObjetPhysique> Objets;
    private Joueur Player;
    public int dT;
    public javax.swing.Timer Temp;
    
	public Scene2(int TempImage,ArrayList<ObjetPhysique> ObjetSansJoueur, Joueur J) {
        super("Jeu de Plateformes.");
    
        setLocation(300,200);
        setSize(1280,720);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        boolean tZ=false;
        boolean tSpace=false;
        boolean tQ=false;
        boolean tD=false;
    
        dT=TempImage;
        Objets=ObjetSansJoueur;
        Player=J;
        Objets.add(Player);
    

        Temp = new javax.swing.Timer(dT,this);
        Temp.start();
        addKeyListener(this);
	}
	
	
	public void paint(Graphics g) {
        //A minima :
        
        /*
        
        Affichage fond
       
        for (ObjetPhysique ob : Objets) {
            ob.affiche(g);
        }
        */
	}
    
    
    public void actionPerformed(ActionEvent e){
        //On ne cherche pas à determiner la source, le Timer étant la seule source possible.
        //Maj vitesse et posotions
        //detection de eventuelle collisions
        this.repaint();

    }
    
    
    public void keyPressed(KeyEvent e) { 


    }
    public void keyReleased(KeyEvent e) {

    }
    public void keyTyped(KeyEvent e) {}


    
}
