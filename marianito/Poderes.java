import java.awt.Image;
import java.awt.Rectangle;

public class Poderes {
    int x;
    int y;
    int largo;
    int ancho;
    int velx=3;
    int vely=3;
    String tipo;
    Image icon;
    String img_fondo;
    Rectangle r;
    boolean cayendo=false;
    boolean derecha=true;
    boolean izquierda=false;
    boolean choca = true;
    
    public Poderes(int x, int y){
        this.x = x;
        this.y = y;
        largo = 46;
        ancho = 50;
        r = new Rectangle(x, y, largo, ancho);
        img_fondo = "hongo.png";
    }
    
    void movimiento(){
        if(derecha){
            x += velx;
        }
        if(cayendo){
            y += vely;
        }
        if(izquierda){
            x -= velx;
        }
    }
    
}
