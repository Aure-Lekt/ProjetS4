import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//classe mère
public abstract class ObjetMobile extends ObjetPhysique {
    
    public double VitX;  // en pix/sec
    public double VitY;
    
    /**
     * Le constructeur.
     * @param PX positon en X
     * @param PX positon en Y
     * @param TX Largeur de la hitbox
     * @param TY Largeur de la hitbox
     * @param VY Vitesse verticale
     * @param VY Vitesse horizontale
     */ 
    public ObjetMobile(int PX, int PY,int TX,int TY,double VX, double VY) {
        super(PX,PY,TX,TY);
        VitX=VX;
        VitY=VY;
    }
    /**
     * Le constructeur sans la vitesse.
     */ 
    public ObjetMobile(int PX, int PY,int TX,int TY) {
        super(PX,PY,TX,TY);
        VitX=0;
        VitY=0;
    }
    /**
     * Le constructeur par défault
     */ 
    public ObjetMobile() {
        super();
        VitX=0;
        VitY=0;
    }
    
    /**
     * Met à jour la position, fait avancer l'objet d'un 'cran'.
     * @param delta T, le temps (en ms) pendant lequel l'objet avance (un cylce).
     */ 
    
    public void updatePosition (int deltaT) {
        double dt = ((double) deltaT)/1000.0; // dt en sec
        posX=posX+(int)(VitX*dt);
        posY=posY-(int)(VitY*dt); //l'axe de la fenetre étant vers la bas et le notre vers le haut, il faut inverser le sens de la vitesse.
    }
    
    //Methode servant a gerer la gravite (entre autres).
    public abstract void updateVitesseY(int deltaT,double g);
    
    //Methode servant a gerer les collisions entre objets.
    public abstract void collsionsMobileFixe(ObjetFixe F);  //à voir comment on gère
    
    //methode utile pour le débug.
    public String toString() {
        return (super.toString()+("ObjetMobile de type : "));
    }
}
