package src;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Image;
import java.net.URL;
import java.util.LinkedList;
import java.awt.Polygon;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Nivel {
    protected LinkedList<Bloques> Lista_Bloques;
    protected LinkedList<Enemigos> Lista_Enemigos;
    protected LinkedList<Poderes> Lista_Poderes;
    protected boolean[] choca;
    protected  Image icons[];
    protected URL url;
    protected String img_fondo;
    protected String cancion;
    protected Marianito M;
    protected Polygon marianito;
    protected AudioClip musica;
    protected boolean termina = false;
    protected boolean signivel = false;
    protected int avance_x;
    protected int altura_salto = 0;
    protected int avance_y;
    protected int puntaje = 0;
    protected int vidas = 0;
    protected boolean banderaTocada = false;
    protected long inicio = 0;
    protected int nivel;
    protected int sec;
    protected int sec2;

    protected  int cont;
    protected int tiempo;


    public Nivel(int nivel) {
        Lista_Bloques = new LinkedList<>();
        Lista_Enemigos = new LinkedList<>();
        Lista_Poderes = new LinkedList<>();

        // Inicialización de objetos clave
        M = new Marianito();
        marianito = new Polygon();
        musica = null;
        choca = new boolean[10];
        avance_x = 0;
        avance_y = 0;

        // Inicialización de variables de control
        puntaje = 0;
        vidas = 3;
        sec = 0;
        sec2 = 0;
        cont = 0;
        tiempo = 1000;

        // Configuración del fondo
        icons = new Image[3];
        img_fondo = "/Assets/mundo1.png"; // Ruta del fondo
        cancion = "/Assets/mundo1.wav";
        url = Ventana.class.getResource(img_fondo);
        switch (nivel) {
            case 1:
            cargarBloques();
            cargarEnemigos();
            cargarPoderes();
            imagenes_Fondo();
                break;
            case 2:
            cargarBloques2();
            cargarEnemigos2();
            cargarPoderes2();
            imagenes_Fondo();
                break;
            case 3:
            cargarBloques3();
            cargarEnemigos2();
            cargarPoderes2();
            imagenes_Fondo();
                break;
        }
    }

    protected void cargarPoderes() {
        // Agregar lógica para inicializar poderes si se requieren
        Lista_Poderes.add(new Poderes(1, 1));
    }

    public void imagenes_Fondo() {

        icons[0] = new ImageIcon(url).getImage();
        icons[1] = new ImageIcon(url).getImage();
        icons[2] = new ImageIcon(url).getImage();
        for (int i = 0; i < Lista_Bloques.size(); i++) {
            url = Ventana.class.getResource(Lista_Bloques.get(i).img_fondo);
            Lista_Bloques.get(i).icon = new ImageIcon(url).getImage();
        }
        for (int i = 0; i < Lista_Enemigos.size(); i++) {
            url = Ventana.class.getResource(Lista_Enemigos.get(i).img_fondo);
            Lista_Enemigos.get(i).icon = new ImageIcon(url).getImage();
        }
        for (int i = 0; i < Lista_Poderes.size(); i++) {
            url = Ventana.class.getResource(Lista_Poderes.get(i).img_fondo);
            Lista_Poderes.get(i).icon = new ImageIcon(url).getImage();
        }
        url = Ventana.class.getResource("/Assets/" + M.img_fondo[8]);
        M.icon = new ImageIcon(url).getImage();
    }

    protected void cargarBloques3(){
        Lista_Bloques.add(new Bloques("plat1", 0, 448, true));
        Lista_Bloques.add(new Bloques("plat1", 500, 408, false));
        Lista_Bloques.add(new Bloques("plat5", 820, 278, false));
        Lista_Bloques.add(new Bloques("plat2", 820, 108, false));
        Lista_Bloques.add(new Bloques("plat3", 1400, 158, false));
        // Lista_Bloques.add(new Bloques("Ladrillo_plano_azul", 1000, 208, false));
        // Lista_Bloques.add(new Bloques("Ladrillo_plano_azul", 1100, 108, false));
        // Lista_Bloques.add(new Bloques("Bandera", 400, 0, false));
    }

    protected void cargarBloques2() {
        Lista_Bloques.add(new Bloques("Ladrillo_azul", 760, 178, false));
        Lista_Bloques.add(new Bloques("Ladrillo_azul", 820, 178, false));
        Lista_Bloques.add(new Bloques("Ladrillo_azul", 700, 408, false));
        // Lista_Bloques.add(new Bloques("Ladrillo_plano_azul", 1000, 328, false));
        // Lista_Bloques.add(new Bloques("Ladrillo_plano_azul", 1000, 208, false));
        // Lista_Bloques.add(new Bloques("Ladrillo_plano_azul", 1100, 108, false));
        
        int baseX = 1200; // Coordenada X de la base de la escalera
        int baseY = 448; // Nivel del suelo
        int alturaEscalon = 60; // Altura de cada escalón
    
        for (int i = 0; i < 5; i++) { // 5 escalones
            Lista_Bloques.add(new Bloques("Ladrillo_plano_azul", baseX + (i * 60), baseY - (i * alturaEscalon), false));
        }
        Lista_Bloques.add(new Bloques("Bandera", 2000, 0, false));
    }

    protected void cargarEnemigos2() {
        Lista_Enemigos.add(new Enemigos("goomba_azul", 800, 400));
        // Lista_Enemigos.add(new Enemigos("koopa", 900));
        Lista_Enemigos.add(new Enemigos("goomba_azul", 1300, 400));
    }

    protected void cargarPoderes2() {
        // Agregar lógica para inicializar poderes si se requieren
        Lista_Poderes.add(new Poderes(1, 1));
    }


    protected void cargarBloques() {
        Lista_Bloques.add(new Bloques("Ladrillo_plano", 760, 178, false));
        Lista_Bloques.add(new Bloques("Ladrillo_plano", 820, 178, false));
        Lista_Bloques.add(new Bloques("Tuberia", 1000, 307, true));
        Lista_Bloques.add(new Bloques("Ladrillo", 1200, 178, false));
        Lista_Bloques.add(new Bloques("Ladrillo", 100, 178, true));
        Lista_Bloques.add(new Bloques("Moneda", 1260, 178, false));
        Lista_Bloques.add(new Bloques("Moneda", 1320, 178, false));
        Lista_Bloques.add(new Bloques("Ladrillo", 1260, 28, true));
        Lista_Bloques.add(new Bloques("Ladrillo", 1320, 28, true));
        Lista_Bloques.add(new Bloques("Ladrillo", 1380, 178, false));
        Lista_Bloques.add(new Bloques("Tuberia", 1500, 307, false));
        Lista_Bloques.add(new Bloques("Foso", 1620, 445, false));
        Lista_Bloques.add(new Bloques("Tuberia", 2000, 307, false));
        Lista_Bloques.add(new Bloques("Bandera", 2500, 0, false));
    }

    protected void cargarEnemigos() {
        Lista_Enemigos.add(new Enemigos("goomba", 800, 400));
        Lista_Enemigos.add(new Enemigos("planta", 1000, 370));
        Lista_Enemigos.add(new Enemigos("koopa", 900, 400));
        Lista_Enemigos.add(new Enemigos("koopa", 1300, 400));


    }

    void colision(String d) {
        for (Bloques bloque : Lista_Bloques) {
            if (bloque.tipo.equals("Tuberia")) {
                if (verificarColisionHorizontal(bloque)) {
                    break;
                }
                if (verificarColisionVerticalTuberia(bloque)) {
                    break;
                }
            } else if (bloque.tipo.equals("Foso")) {
                if (marianito.intersects(bloque.x - avance_x, bloque.y - 10, bloque.ancho, bloque.largo)) {
                    M.cayendo = true;
                    M.caida();
                }   
            } else if (bloque.tipo.startsWith("plat")) {
                if (verificarColisionHorizontal(bloque)) {
                    break;
                }
                
                if (verificarColisionVerticalBloques(bloque)) {
                    // M.cayendo = false;
                    break;
                }
                System.out.println("Mario: " + M.x_img + "Mario y: " + M.yp[0]);
                if (M.x_img > 120 ){
                    if (M.yp[0] == 448){
                        M.cayendo = true;
                        M.caida();
                    }
                }                
            }else {
                if (verificarColisionVerticalBloques(bloque)) {
                    break;
                }
            } 
            if (bloque.tipo.equals("Bandera")) {
                if (!this.banderaTocada) {
                    if (marianito.intersects(bloque.x - avance_x, bloque.y, bloque.ancho, bloque.largo)) {
                        this.musica.stop();
                        this.banderaTocada = true;
                        reproducirSonido("/Assets/terminado.wav");
                        JOptionPane.showMessageDialog(null, "Has ganado");
                        // producir sonido de victoria
                        // cargarNivel2();
                        this.banderaTocada = false;
                        this.signivel = true;
                        this.nivel += 1;
                        this.img_fondo = "/Assets/mundo" + this.nivel + ".png";

                        //DETENER CANCION ANTERIOR  
                        this.cancion = "/Assets/mundo" + this.nivel + ".wav";
                        this.musica = Applet.newAudioClip(Ventana.class.getResource(this.cancion));
                        this.musica.loop();

                        this.reset();
                        
                    }
                }
            }
        }
    }

    protected boolean verificarColisionHorizontal(Bloques bloque) {
        if (marianito.intersects(bloque.x2 - avance_x, bloque.y2, bloque.ancho2, bloque.largo2)) {
            if (M.derecha) {
                choca[2] = true;
                return true;
            }
            if (M.izquierda) {
                choca[3] = true;
                return true;
            }
        }
        return false;
    }

    protected boolean verificarColisionVerticalTuberia(Bloques bloque) {
        if (marianito.intersects(bloque.x - avance_x, bloque.y - 10, bloque.ancho + 10, bloque.largo + 10)) {
            if (M.saltando && 448 + avance_y == bloque.y + 70) {
                M.saltando = false;
                M.cayendo = true;
                return true;
            }
            if (M.cayendo && 448 + avance_y - 11 == bloque.y - 10) {
                M.cayendo = false;
                M.encima = true;
                altura_salto = 0;
                return true;
            }
        }
        
        return false;
    }

    protected boolean verificarColisionVerticalBloques(Bloques bloque) {
        if (marianito.intersects(bloque.x - avance_x, bloque.y - 10, bloque.ancho, bloque.largo + 10)) {
            if (M.saltando && 448 + avance_y - 60 == bloque.y + 60) {
                procesarColisionBloque(bloque);
                return true;
            }
            if (M.cayendo && M.yp[0] == bloque.y) {
                // enPlataforma = true;
                M.cayendo = false;
                M.encima = true;
                altura_salto = 0;
                return true;
            }
            //si es plat1, plat2, plat3, plat4, plat5
        }
        return false;
    }

    protected void procesarColisionBloque(Bloques bloque) {
        M.saltando = false;
        M.cayendo = true;
        String ruta = "/Assets/";
        if (bloque.tipo.equals("Moneda")) {
            puntaje++;
            reproducirSonido(ruta + "moneda.wav");
            actualizarBloque(bloque, "Ladrillo_plano", "Ladrillo_plano.png");
        } else if (bloque.tipo.equals("Ladrillo") || bloque.tipo.equals("Ladrillo_plano_azul")) {
            if (bloque.poder) {
                actualizarBloque(bloque, "Ladrillo_plano", "Ladrillo_plano.png");
                // hacer que mario vaya para a abajo despues de golpear el bloque
                // agregar hongo justo arriba del bloque
                Lista_Poderes.add(new Poderes(bloque.x, bloque.y - 50));
                // desplegar sonido de power up al salir
                reproducirSonido("/Assets/powerup_appears.wav");
            } else {
                reproducirSonido(ruta + "ladrillo.wav");
                Lista_Bloques.remove(bloque);
            }
        }
        choca[0] = true;
    }

    protected void actualizarBloque(Bloques bloque, String nuevoTipo, String nuevaImagen) {
        bloque.tipo = nuevoTipo;
        bloque.img_fondo = nuevaImagen;
        bloque.icon = new ImageIcon(getClass().getResource("/Assets/" + nuevaImagen)).getImage();
    }

    @SuppressWarnings("removal")
    protected void reproducirSonido(String archivoSonido) {
        try {

            url = Ventana.class.getResource(archivoSonido);
            AudioClip musica = Applet.newAudioClip(url);
            musica.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reset() {
        choca = new boolean[4];
        choca[0] = false;
        choca[1] = false;
        choca[2] = false;
        choca[3] = false;
        Lista_Bloques = new LinkedList<>();
        Lista_Enemigos = new LinkedList<>();
        Lista_Poderes = new LinkedList<>();
        url = Ventana.class.getResource(this.img_fondo);
        icons = new Image[3];
        img_fondo = "/Assets/mundo1.png";
        
        M = new Marianito();
        marianito = new Polygon();
        marianito.xpoints = M.xp;
        marianito.ypoints = M.yp;
        marianito.npoints = M.xp.length;
        avance_x = 0;
        avance_y = 0;
        sec = 0;

        //resetear cancion
        if (this.signivel){
            this.musica.stop();
            this.musica.loop();
            this.signivel = false;
        }
        
        if (nivel == 1){
            cargarBloques();
            cargarEnemigos();
            cargarPoderes();
            imagenes_Fondo();
        } else if (nivel == 2){
            // img_fondo = "/Assets/mundo2.png";
            cargarBloques2();
            cargarEnemigos2();
            cargarPoderes2();
            imagenes_Fondo();
        } else if (nivel == 3){
            cargarBloques3();
            cargarEnemigos2();
            cargarPoderes2();
            imagenes_Fondo();
        }
        
        termina = false;
    }
    protected void actualizarMovimiento(boolean derecha, String directorio) {
        sec++;
        if (derecha) {
            M.izquierda = false;
            M.derecha = true;
            M.mov_derecha();
            avance_x += M.velocidad;
        } else {
            M.izquierda = true;
            M.derecha = false;
            M.mov_izquierda();
            avance_x -= M.velocidad;
        }

        if (!M.saltando && !M.cayendo) {
            asignarEstadoAnimacion(sec, derecha, directorio);
        } else {
            asignarEstadoSalto(derecha, directorio);
        }
    }

    protected void asignarEstadoAnimacion(int sec, boolean derecha, String directorio) {
        int[][] coords = derecha ? new int[][] { M.xc1, M.xc3, M.xc2, M.xc4 }
                : new int[][] { M.xc1_i, M.xc3_i, M.xc2_i, M.xc4_i };
        int[] fondos = derecha ? new int[] { 0, 4, 2, 6 } : new int[] { 1, 5, 3, 7 };

        int index = (sec - 1) / 2;
        marianito.xpoints = coords[index];
        marianito.ypoints = derecha ? M.yc1 : M.yc1_i;
        marianito.npoints = coords[index].length;
        url = Ventana.class.getResource(directorio + M.img_fondo[fondos[index]]);
        M.x_img = marianito.xpoints[1];
        M.icon = new ImageIcon(url).getImage();
    }

    protected void asignarEstadoSalto(boolean derecha, String directorio) {
        marianito.xpoints = derecha ? M.xs : M.xs_i;
        marianito.ypoints = derecha ? M.ys : M.ys_i;
        marianito.npoints = M.xs.length;
        url = Ventana.class.getResource(directorio + (derecha ? M.img_fondo[10] : M.img_fondo[11]));
        M.icon = new ImageIcon(url).getImage();
    }

    protected void asignarEstadoColision(String directorio) {
        marianito.xpoints = M.xp;
        marianito.ypoints = M.yp;
        marianito.npoints = M.xp.length;
        url = Ventana.class.getResource(directorio + M.img_fondo[8]);
        M.icon = new ImageIcon(url).getImage();
    }

    protected void manejarEstadoParado(boolean izquierda, String directorio) {
        sec = 0;
        marianito.xpoints = izquierda ? M.xp_i : M.xp;
        marianito.ypoints = izquierda ? M.yp_i : M.yp;
        marianito.npoints = izquierda ? M.xp_i.length : M.xp.length;
        url = Ventana.class.getResource(directorio + (izquierda ? M.img_fondo[9] : M.img_fondo[8]));
        M.icon = new ImageIcon(url).getImage();
        M.x_img = marianito.xpoints[0];
    }

    protected void manejarSalto(String directorio) {

        if (!M.cayendo) {
            M.saltando = true;
            asignarEstadoSalto(M.derecha, directorio);
            avance_y -= M.vel_salto;
            altura_salto -= M.vel_salto;
            M.salto();

            if (altura_salto == -10) {
                reproducirSonido(directorio + "salto.wav");
            }
            if (altura_salto == -200) {
                choca[2] = false;
                choca[3] = false;
                M.saltando = false;
                M.cayendo = true;
            }
        }
    }

    protected void manejarCaida(String directorio) {
        asignarEstadoSalto(M.derecha, directorio);
        avance_y += M.vel_salto;
        altura_salto += M.vel_salto;
        M.caida();

        if (avance_y == 0) {
            altura_salto = 0;
            M.cayendo = false;
            choca[2] = false;
            choca[3] = false;
            movimiento("Parado");
        }
    }

    public void movimiento(String direccion) {
        String directorio = "/Assets/";
        marianito = new Polygon();

        switch (direccion) {
            case "Derecha":
                manejarMovimientoHorizontal(true, directorio);
                break;
            case "Izquierda":
                manejarMovimientoHorizontal(false, directorio);
                break;
            case "Parado":
                manejarEstadoParado(false, directorio);
                break;
            case "Parado2":
                manejarEstadoParado(true, directorio);
                break;
            case "Arriba":
                manejarSalto(directorio);
                break;
            case "Abajo":
                manejarCaida(directorio);
                break;
        }

        if (sec == 8) {
            sec = 0;
        }
        colision("");
    }
    private void manejarMovimientoHorizontal(boolean derecha, String directorio) {
        if (derecha) {
            choca[3] = false;
            if (!choca[2]) {
                actualizarMovimiento(true, directorio);
            } else {
                asignarEstadoColision(directorio);
            }
        } else {
            choca[2] = false;
            if (avance_x > 0) {
                if (!choca[3] || (choca[3] && choca[2])) {
                    actualizarMovimiento(false, directorio);
                } else {
                    asignarEstadoColision(directorio);
                }
            }
        }

        if (M.encima && !M.saltando) {
            M.encima = false;
            M.cayendo = true;
        }
    }

}
