import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ObjetFixe extends ObjetPhysique {
	
	Color couleur;
	
	public ObjetFixe (int posX, int posY, int largeur, int hauteur, Color c){
		super(posX, posY, largeur, hauteur);
		couleur=c;
	}
	
	public void affiche (Graphics g){
		g.setColor(couleur);
		g.drawRect(posX, posY, tailleX, tailleY);
	}
		
}
