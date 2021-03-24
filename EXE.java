import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EXE {
	
	public static void main (String[] args) {
		
        String[][] Sauce = new String[2][3];
        Sauce[0][0] = "/LeftUP.png";
        Sauce[0][1] = "/Left.png";
        Sauce[0][2] = "/LeftDown.png";
        Sauce[1][0] = "/RightUp.png";
        Sauce[1][1] = "/Right.png";
        Sauce[1][2] = "/RightDown.png";
        
        //vitesse minimun = 100 px/sec (sinon aucun déplacement ne s'opere pour un dt de 10ms)
        Joueur J = new Joueur(100,100,100,0,Sauce);
        
        
        //fenTest F = new fenTest(J);
        Scene A = new Scene();
	}
}

