package src;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.LinkedList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Ventana extends JPanel {
    LinkedList<Bloques> Lista_Bloques;
    LinkedList<Enemigos> Lista_Enemigos;
    LinkedList<Poderes> Lista_Poderes;
    Marianito M;
    Polygon marianito;
    boolean termina = false;
    @SuppressWarnings("removal")
    AudioClip musica;
    boolean choca[];
    int avance_x;
    int altura_salto = 0;
    int avance_y;
    int puntaje = 0;
    int vidas = 0;
    int sec;
    int sec2 = 0;
    String img_fondo = "/Assets/mundo1.png";
    URL url;
    Image icons[];
    int cont = 0;
    int tiempo = 1000;
    boolean banderaTocada = false;
    long inicio = 0 ;
    int nivel = 1;
    String cancion = "/Assets/cancion2.wav";

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
        // img_fondo = "/Assets/mundo1.png";
        
        M = new Marianito();
        marianito = new Polygon();
        marianito.xpoints = M.xp;
        marianito.ypoints = M.yp;
        marianito.npoints = M.xp.length;
        avance_x = 0;
        avance_y = 0;
        sec = 0;
        
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

    public Ventana() {
        // Inicialización de listas
        Lista_Bloques = new LinkedList<>();
        Lista_Enemigos = new LinkedList<>();
        Lista_Poderes = new LinkedList<>();

        // img_fondo = "/Assets/mundo1.png"; // Ruta del fondo
        // Inicialización de objetos clave
        url = Ventana.class.getResource(this.img_fondo);
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
    }

    private void cargarBloques3(){
        Lista_Bloques.add(new Bloques("Ladrillo_azul", 760, 178, false));
        Lista_Bloques.add(new Bloques("Ladrillo_azul", 820, 178, false));
        Lista_Bloques.add(new Bloques("Ladrillo_azul", 700, 408, false));
        Lista_Bloques.add(new Bloques("Ladrillo_plano_azul", 1000, 328, false));
        Lista_Bloques.add(new Bloques("Ladrillo_plano_azul", 1000, 208, false));
        Lista_Bloques.add(new Bloques("Ladrillo_plano_azul", 1100, 108, false));
        Lista_Bloques.add(new Bloques("Bandera", 400, 0, false));
    }

    private void cargarBloques2() {
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
        Lista_Bloques.add(new Bloques("Bandera", 400, 0, false));
    }

    private void cargarEnemigos2() {
        Lista_Enemigos.add(new Enemigos("goomba_azul", 800));
        // Lista_Enemigos.add(new Enemigos("koopa", 900));
        Lista_Enemigos.add(new Enemigos("goomba_azul", 1300));
    }

    private void cargarPoderes2() {
        // Agregar lógica para inicializar poderes si se requieren
        Lista_Poderes.add(new Poderes(1, 1));
    }


    private void cargarBloques() {
        Lista_Bloques.add(new Bloques("Ladrillo_plano", 760, 178, false));
        Lista_Bloques.add(new Bloques("Ladrillo_plano", 820, 178, false));
        Lista_Bloques.add(new Bloques("Tuberia", 1000, 307, false));
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
        Lista_Bloques.add(new Bloques("plat1", 200, 448, true));
        Lista_Bloques.add(new Bloques("Bandera", 500, 0, false));
    }

    private void cargarEnemigos() {
        Lista_Enemigos.add(new Enemigos("goomba", 800));
        Lista_Enemigos.add(new Enemigos("koopa", 900));
        Lista_Enemigos.add(new Enemigos("koopa", 1300));
    }

    private void cargarPoderes() {
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
            } else {
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
                        this.nivel += 1;
                        this.img_fondo = "/Assets/mundo" + this.nivel + ".png";

                        //DETENER CANCION ANTERIOR  
                        this.cancion = "/Assets/mundo2.wav";
                        this.musica = Applet.newAudioClip(Ventana.class.getResource(this.cancion));
                        this.musica.loop();

                        this.reset();
                        
                    }
                }
            }
        }
    }

    private boolean verificarColisionHorizontal(Bloques bloque) {
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

    private boolean verificarColisionVerticalTuberia(Bloques bloque) {
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

    private boolean verificarColisionVerticalBloques(Bloques bloque) {
        //altura de ario
        System.out.println("Mario: " + M.yp[0]);
        if (marianito.intersects(bloque.x - avance_x, bloque.y - 10, bloque.ancho, bloque.largo + 10)) {
            if (M.saltando && 448 + avance_y - 60 == bloque.y + 60) {
                procesarColisionBloque(bloque);
                return true;
            }
            if (M.cayendo && M.yp[0] == bloque.y) {
                M.cayendo = false;
                M.encima = true;
                altura_salto = 0;
                return true;
            }
        }
        return false;
    }

    private void procesarColisionBloque(Bloques bloque) {
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

    private void actualizarBloque(Bloques bloque, String nuevoTipo, String nuevaImagen) {
        bloque.tipo = nuevoTipo;
        bloque.img_fondo = nuevaImagen;
        bloque.icon = new ImageIcon(getClass().getResource("/Assets/" + nuevaImagen)).getImage();
    }

    @SuppressWarnings("removal")
    private void reproducirSonido(String archivoSonido) {
        try {

            url = Ventana.class.getResource(archivoSonido);
            musica = Applet.newAudioClip(url);
            musica.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void movimiento(String direccion) {
        String directorio = "/Assets/";
        marianito = new Polygon();
        //imprimir coor del Mario
        // System.out.println("Mario: " + M.yp[0] + M.cayoFoso);
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

    private void actualizarMovimiento(boolean derecha, String directorio) {
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

    private void asignarEstadoAnimacion(int sec, boolean derecha, String directorio) {
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

    private void asignarEstadoSalto(boolean derecha, String directorio) {
        marianito.xpoints = derecha ? M.xs : M.xs_i;
        marianito.ypoints = derecha ? M.ys : M.ys_i;
        marianito.npoints = M.xs.length;
        url = Ventana.class.getResource(directorio + (derecha ? M.img_fondo[10] : M.img_fondo[11]));
        M.icon = new ImageIcon(url).getImage();
    }

    private void asignarEstadoColision(String directorio) {
        marianito.xpoints = M.xp;
        marianito.ypoints = M.yp;
        marianito.npoints = M.xp.length;
        url = Ventana.class.getResource(directorio + M.img_fondo[8]);
        M.icon = new ImageIcon(url).getImage();
    }

    private void manejarEstadoParado(boolean izquierda, String directorio) {
        sec = 0;
        marianito.xpoints = izquierda ? M.xp_i : M.xp;
        marianito.ypoints = izquierda ? M.yp_i : M.yp;
        marianito.npoints = izquierda ? M.xp_i.length : M.xp.length;
        url = Ventana.class.getResource(directorio + (izquierda ? M.img_fondo[9] : M.img_fondo[8]));
        M.icon = new ImageIcon(url).getImage();
        M.x_img = marianito.xpoints[0];
    }

    private void manejarSalto(String directorio) {

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

    private void manejarCaida(String directorio) {
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

    @SuppressWarnings("removal")
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        sec2++;
        cont += 50;
        if (cont % 1000 == 0) {
            tiempo--;
        }
        if (M.yp[0] >= 808) {
            vidas--;
            this.reset();
            if (vidas == 0) {
                vidas = 3;
                termina = true;
            }
        }

        int anchoImagen = icons[0].getWidth(null);
        int totalAncho = getWidth() / anchoImagen + 2;
        int offset_x = avance_x % anchoImagen;

        for (int i = 0; i < totalAncho + 2; i++) {
            g.drawImage(icons[0], i * anchoImagen - offset_x, 0, null);
        }
        g.drawImage(icons[0], 0 - avance_x, 0, null);
        g.drawImage(icons[1], 1024 - avance_x, 0, null);
        g.drawImage(icons[2], 2048 - avance_x, 0, null);

        Font fuente = new Font("Source Code Pro Black", Font.BOLD, 25);
        g.setFont(fuente);
        g.drawString("MARIO", 40, 30);
        g.drawString("" + vidas, 40, 60);
        g.drawString("MONEDAS X " + puntaje, 300, 60);
        g.drawString("MUNDO", 600, 30);
        g.drawString("1-1", 600, 60);
        g.drawString("TIEMPO", 900, 30);
        g.drawString("" + tiempo, 900, 60);

        for (int i = 0; i < Lista_Bloques.size(); i++) {
            // g.drawRect(Lista_Bloques.get(i).x - avance_x, Lista_Bloques.get(i).y,
            // Lista_Bloques.get(i).ancho,
            // Lista_Bloques.get(i).largo);
            if (Lista_Bloques.get(i).tipo.equals("Tuberia")) {
                g.drawRect(Lista_Bloques.get(i).x2 - avance_x, Lista_Bloques.get(i).y2, Lista_Bloques.get(i).ancho2,
                        Lista_Bloques.get(i).largo2);
            }
            // if (Lista_Bloques.get(i).tipo.equals("Bandera")) {
            // g.drawRect((Lista_Bloques.get(i).x - avance_x) , Lista_Bloques.get(i).y,
            // Lista_Bloques.get(i).ancho,
            // Lista_Bloques.get(i).largo);
            // }
            if (Lista_Bloques.get(i).tipo.equals("Moneda")) {
                if (sec2 <= 7) {
                    Lista_Bloques.get(i).img_fondo = "/Assets/Moneda.png";
                    url = Ventana.class.getResource(Lista_Bloques.get(i).img_fondo);
                    Lista_Bloques.get(i).icon = new ImageIcon(url).getImage();
                } else {
                    Lista_Bloques.get(i).img_fondo = "/Assets/Moneda2.png";
                    url = Ventana.class.getResource(Lista_Bloques.get(i).img_fondo);
                    Lista_Bloques.get(i).icon = new ImageIcon(url).getImage();
                }
            }
            // g.drawImage(Lista_Bloques.get(i).icon, Lista_Bloques.get(i).x - avance_x,
            // Lista_Bloques.get(i).y, null);

            if (Lista_Bloques.get(i).tipo.equals("Bandera")) {
                g.drawImage(Lista_Bloques.get(i).icon, (Lista_Bloques.get(i).x - avance_x) - 160,
                        Lista_Bloques.get(i).y - 40, null);
            } else  if (Lista_Bloques.get(i).tipo.equals("Foso")) {
                g.drawImage(Lista_Bloques.get(i).icon, Lista_Bloques.get(i).x - avance_x, Lista_Bloques.get(i).y, null);
            }
                else 
            {
                g.drawImage(Lista_Bloques.get(i).icon, Lista_Bloques.get(i).x - avance_x, Lista_Bloques.get(i).y, null);
            }
            //imprimir posicion de los bloques
            // System.out.println("Bloque: "+ Lista_Bloques.get(i).tipo+"" + Lista_Bloques.get(i).x + " " + Lista_Bloques.get(i).y);
        }
        for (int i = 0; i < Lista_Poderes.size(); i++) {
            url = Ventana.class.getResource(Lista_Poderes.get(i).img_fondo);
            Lista_Poderes.get(i).icon = new ImageIcon(url).getImage();
            g.drawImage(Lista_Poderes.get(i).icon, Lista_Poderes.get(i).x - avance_x, Lista_Poderes.get(i).y, null);

            Rectangle re = new Rectangle(Lista_Poderes.get(i).x - avance_x, Lista_Poderes.get(i).y,
                    Lista_Poderes.get(i).largo, Lista_Poderes.get(i).ancho);
            // verificar colisión con marianito con el hongo
            Rectangle personaje = new Rectangle(M.xp[0], M.yp[0], marianito.getBounds().width,
                    marianito.getBounds().height);
            if (re.intersects(personaje)) {
                url = Ventana.class.getResource("/Assets/vida.wav");
                AudioClip sonido = Applet.newAudioClip(url);
                sonido.play();
                // incrementar vidas
                vidas++;
                // eliminar hongo
                Lista_Poderes.remove(i);
            }
            /// mn g.drawRect(re.x, re.y, re.width, re.height);
            for (int j = 0; j < Lista_Bloques.size(); j++) {
                if (Lista_Bloques.get(j).tipo.equals("Tuberia")) {
                    if (re.intersects(Lista_Bloques.get(j).x2 - avance_x, Lista_Bloques.get(j).y2,
                            Lista_Bloques.get(j).ancho2, Lista_Bloques.get(j).largo2)) {
                        if (Lista_Poderes.get(i).derecha) {
                            Lista_Poderes.get(i).derecha = false;
                            Lista_Poderes.get(i).izquierda = true;
                        }
                    }
                }
                if (re.intersects(Lista_Bloques.get(j).x - avance_x, Lista_Bloques.get(j).y, Lista_Bloques.get(j).ancho,
                        Lista_Bloques.get(j).largo)) {

                    Lista_Poderes.get(i).choca = true;
                    if (Lista_Poderes.get(i).cayendo) {
                        Lista_Poderes.get(i).derecha = true;
                        Lista_Poderes.get(i).cayendo = false;
                    }
                }
            }
            if (Lista_Poderes.size() > 0 && !Lista_Poderes.get(i).choca) {
                if (Lista_Poderes.get(i).derecha && Lista_Poderes.get(i).y < 448) {
                    Lista_Poderes.get(i).derecha = false;
                    Lista_Poderes.get(i).cayendo = true;
                    break;
                }
                if (Lista_Poderes.get(i).y + Lista_Poderes.get(i).largo >= 448) {
                    if (!Lista_Poderes.get(i).izquierda) {
                        Lista_Poderes.get(i).derecha = true;
                    } else {
                        Lista_Poderes.get(i).derecha = false;
                    }
                    Lista_Poderes.get(i).cayendo = false;
                }

            }
            if (Lista_Poderes.size() > 0) {

                Lista_Poderes.get(i).choca = false;
                Lista_Poderes.get(i).movimiento();
                if (marianito.intersects(re)) {
                    if (Lista_Poderes.get(i).img_fondo.equals("hongo.png")) {
                        vidas++;
                        url = Ventana.class.getResource("/Assets/vida.wav");
                        musica = Applet.newAudioClip(url);
                        musica.play();
                        Lista_Poderes.remove(i);
                    }
                }
            }
           
        }

        for (int i = 0; i < Lista_Enemigos.size(); i++) {
            int d = 1;
            if (!Lista_Enemigos.get(i).muerto) {
                if (Lista_Enemigos.get(i).tipo.equals("koopa")) {
                    if (Lista_Enemigos.get(i).derecha) {
                        d = 1;
                    } else {
                        d = 3;
                    }
                }
                if (sec2 <= 7) {
                    Lista_Enemigos.get(i).img_fondo = "/Assets/" + Lista_Enemigos.get(i).tipo + (d + ".png");
                    url = Ventana.class.getResource(Lista_Enemigos.get(i).img_fondo);
                    Lista_Enemigos.get(i).icon = new ImageIcon(url).getImage();
                } else {
                    Lista_Enemigos.get(i).img_fondo = "/Assets/" + Lista_Enemigos.get(i).tipo + (d + 1) + ".png";
                    url = Ventana.class.getResource(Lista_Enemigos.get(i).img_fondo);
                    Lista_Enemigos.get(i).icon = new ImageIcon(url).getImage();
                }
                g.drawImage(Lista_Enemigos.get(i).icon, Lista_Enemigos.get(i).x - avance_x, Lista_Enemigos.get(i).y,
                        null);
                Rectangle re = new Rectangle(Lista_Enemigos.get(i).x - avance_x, Lista_Enemigos.get(i).y,
                        Lista_Enemigos.get(i).alto, Lista_Enemigos.get(i).ancho);
                for (int j = 0; j < Lista_Bloques.size(); j++) {
                    if (Lista_Bloques.get(j).tipo.equals("Tuberia")) {
                        if (re.intersects(Lista_Bloques.get(j).x2 - avance_x, Lista_Bloques.get(j).y2,
                                Lista_Bloques.get(j).ancho2, Lista_Bloques.get(j).largo2)) {
                            if (Lista_Enemigos.get(i).derecha) {
                                Lista_Enemigos.get(i).derecha = false;
                                Lista_Enemigos.get(i).izquierda = true;
                            } else {
                                Lista_Enemigos.get(i).derecha = true;
                                Lista_Enemigos.get(i).izquierda = false;
                            }
                            break;
                        }
                    }
                }
                Lista_Enemigos.get(i).mov();
                if (marianito.intersects(Lista_Enemigos.get(i).x - avance_x, Lista_Enemigos.get(i).y, 48, 48)) {
                    if (avance_y == 0) {
                        url = Ventana.class.getResource("muerte.wav");
                        musica = Applet.newAudioClip(url);
                        musica.play();
                        vidas--;
                        this.reset();
                        if (vidas == 0) {
                            vidas = 3;
                            termina = true;
                        }
                    } else {
                        Lista_Enemigos.get(i).muerto = true;
                        // obtener tipo de enemigo
                        String tipo = Lista_Enemigos.get(i).tipo;
                        reproducirSonidoMuerteEnemigo(tipo);
                    }
                }
            } else {
                Lista_Enemigos.get(i).img_fondo = "/Assets/" + Lista_Enemigos.get(i).tipo + "_M.png";
                url = Ventana.class.getResource(Lista_Enemigos.get(i).img_fondo);
                Lista_Enemigos.get(i).icon = new ImageIcon(url).getImage();
                g.drawImage(Lista_Enemigos.get(i).icon, Lista_Enemigos.get(i).x - avance_x,
                        448 - Lista_Enemigos.get(i).img_M, null);
            }
        }

        if (M.saltando) {
            movimiento("Arriba");
        }
        if (M.cayendo) {
            movimiento("Abajo");
        }

        if (M.cayendo || M.saltando) {
            g.drawImage(M.icon, avance_x, 378 + avance_y, null);
        } else {
            g.drawImage(M.icon, M.x_img, 368 + avance_y, null);

        }
        // g.drawPolygon(marianito);
        if (sec2 == 14) {
            sec2 = 0;
        }

    }

    private void reproducirSonidoMuerteEnemigo(String tipo) {
        URL url = Ventana.class.getResource("/Assets/muerte_" + tipo + ".wav");
        if (url != null) {
            // reproducir sonido de muerte del enemigo
            AudioClip gover = Applet.newAudioClip(url);
            gover.play();
        } else {
            System.err.println("No se pudo cargar el sonido de muerte del enemigo.");
        }
    }


    public static void main(String[] args) throws InterruptedException, Throwable {

        Thread pintado;
        Thread tecladoHilo; // Hilo para manejar las teclas

        JFrame Ventana_Juego = new JFrame("Mario Bross");
        Ventana_Juego.setSize(1324, 615);
        Ventana_Juego.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Ventana juego = new Ventana();

        Ventana_Juego.add(juego);
        Ventana_Juego.setVisible(true);

        // Inicializa la música de fondo
        URL url = Ventana.class.getResource(juego.cancion);
        juego.musica = Applet.newAudioClip(url);
        juego.musica.loop();
        juego.musica.play();

        // Manejo del KeyListener en un hilo aparte
        tecladoHilo = new Thread(() -> {
            Ventana_Juego.addKeyListener((KeyListener) new KeyAdapter() {
                String tecla;

                @Override
                public void keyReleased(KeyEvent e) {
                    tecla = KeyEvent.getKeyText(e.getKeyCode());
                    if (tecla.equals("Derecha") || tecla.equals("Right")) {
                        juego.movimiento("Parado");
                    }
                    if (tecla.equals("Izquierda") || tecla.equals("Left")) {
                        juego.movimiento("Parado2");
                    }
                }

                @Override
                public void keyPressed(KeyEvent e) {
                    tecla = KeyEvent.getKeyText(e.getKeyCode());
                    if (tecla.equals("Derecha") || tecla.equals("Right")) {
                        juego.movimiento("Derecha");
                    }
                    if (tecla.equals("Izquierda") || tecla.equals("Left")) {
                        juego.movimiento("Izquierda");
                    }
                    if (tecla.equals("Arriba") || tecla.equals("Up")) {
                        juego.movimiento("Arriba");
                    }
                }
            });
        });
        tecladoHilo.start(); // Inicia el hilo para manejar las teclas

        // Hilo para el pintado de la ventana
        pintado = new Thread(() -> {
            while (true) {
                if (!juego.termina) {
                    juego.repaint();
                    try {
                        Thread.sleep(47);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                } else {
                    try {
                        URL url2 = Ventana.class.getResource("/Assets/muerte.wav");
                        juego.musica = Applet.newAudioClip(url2);
                        juego.musica.play();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Object[] opciones = { "Si", "No" };
                    int n = JOptionPane.showOptionDialog(null, "¿Quieres volver a jugar?", "Fin del juego",
                            JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones,
                            opciones[1]);
                    if (n == 0) {
                        juego.reset();
                    } else {
                        juego.musica.stop();
                        Ventana_Juego.dispose();
                        break;
                    }
                }
            }
        });
        pintado.start(); // Inicia el hilo para el pintado

    }
}

