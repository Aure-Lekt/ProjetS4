import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


//classe mère
public abstract class ObjetPhysique {
    
    //Variables publiques, car valeurs recuperées par d'autre instances dans certaines futures methodes (expl : collision...)
    public int posX;    // Le centre de l'objet
    public int posY;
    public int tailleX;   //Largeur de la hitbox
    public int tailleY;   //Largeur de la hitbox
    
    /**
     * Le constructeur.
     * @param PX positon en X
     * @param PX positon en Y
     * @param TX Largeur de la hitbox
     * @param TY Largeur de la hitbox
     */ 
    public ObjetPhysique(int PX, int PY,int TX,int TY) {
            posX=PX;
            posY=PY;
            tailleX=TX;
            tailleY=TY;
            //ect..
    }
    /**
     * Le constructeur par défaut
     */ 
    public ObjetPhysique() {
            posX=0;
            posY=0;
            tailleX=2;   // (taille)>0 et (taille/2) entier, pour pouvoir mieux gèrer les collisions.   
            tailleY=2;
    }
    
    //methode d'affcihage a l'écran apellée lors d'un paint().
    public abstract void affiche(Graphics g);
    
    //methode utile pour le débug.
    public String toString() {
        return ("Objet en X="+posX+"//Y="+posY+" : ");
    }
    
    //On ne définit pas de methode equals() et compareTo()
    //Car même si nous utiliserons une arraylist, l'ordre dans lequel les élements sont triés n'a aucune importance.
}
