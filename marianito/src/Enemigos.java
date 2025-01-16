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
    int radio = 0;
    boolean subiendo;
    int limiteSuperior= 240;
    int limiteInferior= 380;
    
    public Enemigos(String nombre, int cx, int cy, int radio){
        if (nombre.equals("bola")) {
            ancho = 2;
            alto = 2;
            this.radio = radio;
            this.x = cx;
            this.y = cy;
            this.tipo =  nombre;
            img_fondo = "/Assets/bola1.png";
            img_M = 2;
        }
    }

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
            this.y = cy;
            this.tipo =  nombre;
            img_fondo = "/Assets/koopa1.png";
            img_M = 41;
        }
        if(nombre.equals("koopa_volador")){
            ancho = 48;
            alto = 78;
            this.x = cx;
            this.y = cy;
            this.tipo =  nombre;
            img_fondo = "/Assets/koopa_volador1.png";
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

        if (nombre.equals("browser")) {
            ancho = 48;
            alto = 78;
            this.x = cx;
            this.y = cy;
            this.tipo =  nombre;
            img_fondo = "/Assets/browser1.png";
            img_M = 2;
        }

        if (nombre.equals("bomba")) {
            ancho = 48;
            alto = 78;
            this.x = cx;
            this.y = cy;
            this.tipo =  nombre;
            img_fondo = "/Assets/bomba1.png";
            img_M = 1;
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

    public void movCircular(int centerX, int centerY, double radius, double angle) {
        double angleInRadians = angle * Math.PI / 360;
            x = (int)(centerX + radius * Math.cos(angleInRadians));
            y = (int)(centerY + radius * Math.sin(angleInRadians));
    }
    public void movVertical() {
        if (tipo.equals("planta") || tipo.equals("koopa_volador")) {
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
