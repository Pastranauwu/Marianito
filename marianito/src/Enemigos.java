package src;

import java.awt.Image;

public class Enemigos {
    int x;
    int y;
    
    int velocidad = 3;
    Image icon;
    int ancho;
    int alto;
    boolean muerto = false;
    boolean derecha = true;
    boolean izquierda = false;
    int img_M;
    String img_fondo;
    String tipo;
    boolean subiendo;
    int limiteSuperior= 235;
    int limiteInferior= 380;
    
    public Enemigos(String nombre, int cx, int cy){
        
        if(nombre.equals("goomba")){
            ancho = alto = 48;
            this.x = cx;
            this.y = cy;
            this.tipo = nombre;
            img_fondo = "/Assets/goomba1.png";
            img_M = 6;
        }
        if(nombre.equals("koopa")){
            ancho = 48;
            alto = 78;
            this.x = cx;
            this.y = 370;
            this.tipo =  nombre;
            img_fondo = "/Assets/koopa1.png";
            img_M = 41;
        }
        if (nombre.equals("goomba_azul")){
            ancho = alto = 48;
            this.x = cx;
            this.y = 400;
            this.tipo =  nombre;
            img_fondo = "/Assets/goomba_azul1.png";
            img_M = 6;
        }

        if (nombre.equals("planta")){
            ancho = 48;
            alto = 78;
            this.x = cx;
            this.y = 370;
            this.tipo =  nombre;
            img_fondo = "/Assets/planta1.png";
            img_M = 2;
        }
    }
    
    public void mov(){
        if(derecha){
            x=x-velocidad;
        }
        if(izquierda){
            x=x+velocidad;
        }
    }

    public void movVertical() {
        if (!muerto && tipo.equals("planta")) {
            if (subiendo) {
                y -= 2; // Velocidad de movimiento hacia arriba
                if (y <= limiteSuperior) {
                    subiendo = false;
                }
            } else {
                y += 2; // Velocidad de movimiento hacia abajo
                if (y >= limiteInferior) {
                    subiendo = true;
                }
            }
        }
    }

    public void movEstatico(){
        if(derecha){
            x=x-0;
        }
        if(izquierda){
            x=x+0;
        }
    }
}
