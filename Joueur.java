import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Joueur extends ObjetMobile{
	
    private Image[][] Sprites;   // [Posture vericale][Posture Horizontal]
    private int Dir;   // 0 ou 1, delcarée en tant que varialbe pour l'avoir toujours en mémoire.
    private final double Vmax=2000.0; //VitY max
    private boolean SautPossible;
    
    /**
     * Le constructeur.
     * @param PX positon en X
     * @param PX positon en Y
     * @param TX Largeur de la hitbox
     * @param TY Largeur de la hitbox
     * @param VY Vitesse verticale
     * @param VY Vitesse horizontale
     * @param SourceSp chemin d'acces des images des Sprites du personnage.
     */ 
	public Joueur (int PX, int PY,int TX,int TY,double VX, double VY,String[][] SourceSp) {
        super(PX,PY,TX,TY,VX,VY);
        Sprites = ObtainImage(SourceSp);   // 2x3
        Dir=1;  //Orientation à droite de base.
	}
    /**
     * Le constructeur plus intelligent.
     * Determine la taille de l'objet en fonction des Srpites.
     * @param PX positon en X
     * @param PX positon en Y
     * @param VY Vitesse verticale
     * @param VY Vitesse horizontale
     * @param SourceSp chemin d'acces des images des Sprites du personnage.
     */ 
    public Joueur (int PX, int PY,double VX, double VY,String[][] SourceSp) {
        super(PX,PY,0,0,VX,VY);
        Sprites = ObtainImage(SourceSp);   // 2x3
        this.tailleX=Sprites[0][0].getWidth(null);  //Determination de la taille de l'objet en fonction des sprites,
        this.tailleY=Sprites[0][0].getHeight(null); // selon les hypothese de fonctionnement de la methode ObtainImage.
        Dir=1;  //Orientation a droite de base.
	}
    /**
     * Methode créant le tableau contant les Sprites avec leur chemin d'acces.
     * Ne sert que dans le constructeur.
     * @param source Chemin d'acces des images des Sprites du personnage.
     * @return Im Le tableau contant les Sprites
     */ 
    private Image[][] ObtainImage(String[][] source) {
        // Hypothéses de fonctionnement :
        // Source doit être absolument de taille 2x3 (ou plus, mais ça ne sert à rien).
        // Toutes les images indiquées par les chemins ont la même taille en pixels.
        Image[][] Im = new Image[2][3];
        Im[0][0] = new ImageIcon(getClass().getResource(source[0][0])).getImage();
        Im[0][1] = new ImageIcon(getClass().getResource(source[0][1])).getImage();
        Im[0][2] = new ImageIcon(getClass().getResource(source[0][2])).getImage();
        Im[1][0] = new ImageIcon(getClass().getResource(source[1][0])).getImage();
        Im[1][1] = new ImageIcon(getClass().getResource(source[1][1])).getImage();
        Im[1][2] = new ImageIcon(getClass().getResource(source[1][2])).getImage();
        return Im;
    }
    
    /**
     * Affichage de l'objet dans la fenêtre.
     * @param g Element graphique dans lequel on travaille.
     */ 
    public void affiche(Graphics g) {
        //rappel : posX/posY indiquent le CENTRE de l'objet.
        g.drawImage(getSprite(),posX-tailleX/2,posY-tailleY/2,tailleX,tailleY,null);
        isKilled(g);
    }
    /**
     * Determiantion da la posture du personage, donc du Sprite à utiliser.
     * @return le Sprite choisi.
     */ 
    private Image getSprite() {
        //Dir : 0=gauche    1=droite
        //Dir a toujours une valeur meme si elle n'est pas mise à jour dans la méthode (si VitX==0)
        if (VitX>0) {
            Dir=1 ;
        }else if (VitX<0) {
            Dir=0 ;
        }
        int seuil=50;
        int Vertical=1;
        //Vertical : 1 = stable(default)  0=saut  2=chute
        if (VitY>seuil) {
           Vertical=0;
        }else if (VitY<-seuil) {
            Vertical=2;
        } else if (VitY==0) {
            Vertical=1;
        }
        return Sprites[Dir][Vertical]; //renvoi la posture du personnage (droite-haut,...,gauche-bas)
    }
    
    /**
     * Simulation de la gravité
     * @param delta T, le temps (en ms) pendant lequel l'objet avance (un cylce).
     * @param g l'attraction du sol en pix/s²
     */ 
    public void updateVitesseY(int deltaT,double g) {
        double dt = ((double) deltaT)/1000.0; // dt en sec
        VitY = VitY-(g*dt);
        if (VitY>Vmax) //vitY max
            VitY=Vmax;
        if (VitY<-Vmax) //vitY max
            VitY=-Vmax;  
    }
    
    /**
     * Gestion des collsion (sans rebond) avec un objet fixe.
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
        SautPossible=false; //Le saut n'est possible que dans une situation précise.
        //
        if ((dPXabs<=ecartMinX)&&(dPYabs<=ecartMinY)) {  //Cas de collision !
            //On considère que notre programme ne peut jamais aboutir à un cas dP=0.
            if(dPXabs<=dPYabs) {  //collision sur l'axe Y
                if (dPY>0) { //On est en-dessous de l'objet fixe. (l'axe y est vers bas)
                    if(VitY>0)
                        VitY=0;
                    posY=posY+(ecartMinY-dPYabs);   //l'axe y est vers le bas.
                }
                if (dPY<0) { //On est au-dessus de l'objet fixe. (l'axe y est vers bas)
                    if(VitY<0)
                        VitY=0;
                    posY=posY-(ecartMinY-dPYabs);   //l'axe y est vers le bas.
                    SautPossible=true;   //On ne peut sauter que depuis le "sol".
                }
            }
            //
            if(dPYabs<=dPXabs) {  //collision sur l'axe X
                if (dPX>0) { //On est à droite de l'objet fixe. 
                    if(VitX<0)
                        VitX=0;
                    posX=posX+(ecartMinX-dPXabs);   
                }
                if (dPX<0) { //On est à gauche de l'objet fixe. 
                    if(VitX>0)
                        VitX=0;
                    posX=posX-(ecartMinX-dPXabs);   
                }
            }
            //
        }
    }
    
    //temp
    private void isKilled(Graphics g) {
        if(posY>(720+tailleY/2)) {
            g.setColor(Color.red);
            g.fillRect(0,0,2000,2000); //taille volontairement superieur à la taille de la fenetre.
            posX=50;
            posY=50;
        }
    }
    
    /**
     * Methode apellée à l'appui de la touche de saut (barre espace)
     */
    public void Jump() {
        if (SautPossible)
            VitY= VitY + Vmax/2;
    }

    //methode utile pour le débug.
    public String toString() {
        return (super.toString()+("Joueur."));
    }
    
}

