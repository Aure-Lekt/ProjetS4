import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Ennemis extends ObjetMobile  {
     
    private Image Sprite;  
    private boolean peutTomber;
    private final double Vmax=2000.0; //VitY max

    /**
     * Le constructeur.
     * @param PX positon en X
     * @param PX positon en Y
     * @param TX Largeur de la hitbox
     * @param TY Largeur de la hitbox
     * @param VY Vitesse verticale
     * @param VY Vitesse horizontale
     * @param SourceSp chemin d'acces de l'image du Sprite
     */ 
	public Ennemis (int PX, int PY,int TX,int TY,double VX, double VY,String SourceSp,boolean pt) {
        super(PX,PY,TX,TY,VX,VY);
        Sprite = new ImageIcon(getClass().getResource(SourceSp)).getImage();
        peutTomber=pt;
	}
    /**
     * Le constructeur plus intelligent.
     * Determine la taille de l'objet en fonction du Srpite.
     * @param PX positon en X
     * @param PX positon en Y
     * @param VY Vitesse verticale
     * @param VY Vitesse horizontale
     * @param SourceSp chemin d'acces de l'image du Sprite
     */ 
    public Ennemis (int PX, int PY,double VX, double VY,String SourceSp,boolean pt) {
        super(PX,PY,0,0,VX,VY);
        Sprite = new ImageIcon(getClass().getResource(SourceSp)).getImage();
        this.tailleX=Sprite.getWidth(null);  
        this.tailleY=Sprite.getHeight(null);
        peutTomber=pt;
	}
    
    /**
     * Affichage de l'objet dans la fenêtre.
     * @param g Element graphique dans lequel on travaille.
     */ 
    public void affiche(Graphics g) {
        //rappel : posX/posY indiquent le CENTRE de l'objet.
        g.drawImage(Sprite,posX-tailleX/2,posY-tailleY/2,tailleX,tailleY,null);
    }
        
    /**
     * Simulation de la gravité
     * @param delta T, le temps (en ms) pendant lequel l'objet avance (un cylce).
     * @param g l'attraction du sol en pix/s²
     */ 
    public void updateVitesseY(int deltaT,double g) {
        double dt = ((double) deltaT)/1000.0; // dt en sec
        if (peutTomber) {
            VitY = VitY-(g*dt);
            //la vitesse ne peut être que négative.
            if (VitY<-Vmax) //vitY max
                VitY=-Vmax;  
        }else {
            VitY=0;
        }
    }
    
    /**
     * Gestion des collsion (avec rebond en x) avec un objet fixe.
     * @param F l'objet en question.
     */
    public void collsionsMobileFixe(ObjetFixe OF) {
        //
        int dPX=this.posX-OF.posX;  //ecarts en X
        int dPY=this.posY-OF.posY;  //ecrats en Y
        int dPXabs=Math.abs(dPX);   //valeur absolue
        int dPYabs=Math.abs(dPY);   //valeur absolue
        int ecartMinX= (tailleX+OF.tailleX)/2;
        int ecartMinY= (tailleY+OF.tailleY)/2;
        //
        if ((dPXabs<=ecartMinX)&&(dPYabs<=ecartMinY)) {  //Cas de collision !
            //On considère que notre programme ne peut jamais aboutir à un cas dP=0.
            if((dPXabs<=dPYabs)&&(dPY<0)&&(peutTomber)) {  //collision sur l'axe Y, considérant que l'objet ne peut que tomber ou rester stable.
                if(VitY<0)
                    VitY=0;
                posY=posY-(ecartMinY-dPYabs);   //l'axe y est vers le bas.
            }
            //
            if(dPYabs<=dPXabs) {  //collision sur l'axe X
                VitX=-VitX;    //à droite comme à gauche
                if (dPX>0)  //On est à droite de l'objet fixe. 
                    posX=posX+(ecartMinX-dPXabs);   
                if (dPX<0)  //On est à gauche de l'objet fixe. 
                    posX=posX-(ecartMinX-dPXabs);   
            }
            //
        }
    }
   
    //methode utile pour le débug.
    public String toString() {
        return (super.toString()+("Ennemi."));
    }
	
}

