package src;

import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.sound.sampled.Clip;

public class Ventana extends JPanel {
    LinkedList<Bloques> Lista_Bloques;
    LinkedList<Enemigos> Lista_Enemigos;
    LinkedList<Poderes> Lista_Poderes;
    Marianito M;
    Polygon marianito;
    boolean termina = false;
    Clip bgmClip;
    boolean choca[];
    int avance_x;
    int altura_salto = 0;
    int avance_y;
    int puntaje = 0;
    int vidas = 0;
    int sec;
    int sec2 = 0;
    URL url;
    Image icons[];
    int cont = 0;
    int tiempo = 1000;
    boolean banderaTocada = false;
    long inicio = 0;
    int nivel = 1;
    boolean enPlat = false;
    int angulo =0;
    String img_fondo = "/Assets/mundo" + nivel + ".png";
    String cancion = "/Assets/mundo" + nivel + ".wav";
    boolean signivel = false;
    static boolean pantallaCarga = false;
    // Transición de nivel sin hilos bloqueantes
    private long loadingUntilMs = 0L;
    private boolean pendingLevelAdvance = false;
    private boolean gameOverHandled = false;
    private static final int TOLERANCIA = 12; // tolerancia de píxeles para colisiones verticales
    // Lógica a paso fijo (~47ms como antes) para no acelerar a 60 FPS
    private static final int LOGIC_DT_MS = 47;
    private long lastLogicUpdateMs = 0L;
    // Menu y pausa
    boolean inMenu = true;
    boolean paused = false;
    boolean gameWon = false;
    private static final int MAX_NIVEL = 4;
    // boolean enPlataforma = true;

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
        banderaTocada = false;
        // img_fondo = "/Assets/mundo1.png";

        M = new Marianito();
        marianito = new Polygon();
        marianito.xpoints = M.xp;
        marianito.ypoints = M.yp;
        marianito.npoints = M.xp.length;
        avance_x = 0;
        avance_y = 0;
        sec = 0;

        // Música de fondo con Clip (no bloqueante)
        if (this.signivel) {
            // cambiar música de nivel
            SoundManager.stop(bgmClip);
            bgmClip = SoundManager.loop(this.cancion);
            this.signivel = false;
        } else if (bgmClip == null) {
            bgmClip = SoundManager.loop(cancion);
        }

        if (nivel == 1) {
            cargarBloques();
            cargarEnemigos();
            cargarPoderes();
            imagenes_Fondo();
        } else if (nivel == 2) {
            // img_fondo = "/Assets/mundo2.png";
            cargarBloques2();
            cargarEnemigos2();
            cargarPoderes2();
            imagenes_Fondo();
        } else if (nivel == 3) {
            cargarBloques3();
            cargarEnemigos3();
            cargarPoderes3();
            imagenes_Fondo();
    } else if (nivel == 4) {
            cargarBloques4();
            cargarEnemigos4();
            cargarPoderes();
            imagenes_Fondo();
    } else if (nivel > MAX_NIVEL) {
            // No mostrar JOptionPane aquí (se invoca desde paint y puede romperse)
            gameWon = true;
        }

    }

    public Ventana() {
        // Inicialización de listas
        Lista_Bloques = new LinkedList<>();
        Lista_Enemigos = new LinkedList<>();
        Lista_Poderes = new LinkedList<>();

        url = Ventana.class.getResource(this.img_fondo);
        M = new Marianito();
        marianito = new Polygon();

    // Música de fondo con Clip (no bloqueante)
    bgmClip = SoundManager.loop(cancion);
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
        if (nivel == 1) {
            cargarBloques();
            cargarEnemigos();
            cargarPoderes();
            imagenes_Fondo();
        } else if (nivel == 2) {
            // img_fondo = "/Assets/mundo2.png";
            cargarBloques2();
            cargarEnemigos2();
            cargarPoderes2();
            imagenes_Fondo();
        } else if (nivel == 3) {
            cargarBloques3();
            cargarEnemigos3();
            cargarPoderes3();
            imagenes_Fondo();
        }else if (nivel == 4) {
            cargarBloques4();
            cargarEnemigos4();
            cargarPoderes();
            imagenes_Fondo();
        } else {
            JOptionPane.showMessageDialog(null, "¡Felicidades! Has completado el juego");
            System.exit(0);
        }

    }

    private void cargarBloques3() {
        Lista_Bloques.add(new Bloques("plat1", 0, 448, true));
        Lista_Bloques.add(new Bloques("plat1", 500, 408, false));
        Lista_Bloques.add(new Bloques("plat5", 820, 278, false));
        Lista_Bloques.add(new Bloques("plat2", 820, 108, false));
        Lista_Bloques.add(new Bloques("plat3", 1300, 158, false));
        Lista_Bloques.add(new Bloques("plat3", 1800, 408, false));
        Lista_Bloques.add(new Bloques("Bandera", 2000, 0, false));
        Lista_Bloques.add(new Bloques("Moneda", 1060, 28, false));
        Lista_Bloques.add(new Bloques("Moneda", 1120, 28, false));
        Lista_Bloques.add(new Bloques("Ladrillo", 560, 128, true));
        Lista_Bloques.add(new Bloques("Ladrillo", 620, 128, true));
        Lista_Bloques.add(new Bloques("Ladrillo", 680, 128, false));
        // Lista_Bloques.add(new Bloques("Ladrillo_plano_azul", 1000, 208, false));
        // Lista_Bloques.add(new Bloques("Ladrillo_plano_azul", 1100, 108, false));
    }

    private void cargarBloques2() {
        // // Bloques iniciales

        // Plataforma elevada
        Lista_Bloques.add(new Bloques("Ladrillo_plano_azul", 400, 388, false));
        Lista_Bloques.add(new Bloques("Ladrillo_plano_azul", 460, 388, false));
        Lista_Bloques.add(new Bloques("Ladrillo_plano_azul", 520, 388, false));

        // Escalera
        int baseX = 1000;
        int baseY = 448;
        int alturaEscalon = 60;
        for (int i = 0; i < 5; i++) {
            Lista_Bloques.add(new Bloques("Ladrillo_plano_azul", baseX + (i * 60), baseY - (i * alturaEscalon), false));
        }

        Lista_Bloques.add(new Bloques("Ladrillo_azul", 600, 328, false));
        Lista_Bloques.add(new Bloques("Ladrillo_azul", 660, 328, false));
        Lista_Bloques.add(new Bloques("Ladrillo_azul", 720, 328, false));

        // Cubrir la escalera de ladrillos azules planos
        for (int i = 0; i < 5; i++) {
            Lista_Bloques
                    .add(new Bloques("Ladrillo_plano_azul", baseX + (i * 60), baseY - (i * alturaEscalon) - 60, false));
        }
        // Plataforma alta
        Lista_Bloques.add(new Bloques("Ladrillo_plano_azul", 1400, 208, false));
        Lista_Bloques.add(new Bloques("Ladrillo_plano_azul", 1460, 208, false));
        Lista_Bloques.add(new Bloques("Ladrillo_plano_azul", 1520, 208, false));

        // Bloques finales
        Lista_Bloques.add(new Bloques("Ladrillo_plano_azul", 1800, 448, true));
        Lista_Bloques.add(new Bloques("Ladrillo_plano_azul", 1860, 448, true));
        Lista_Bloques.add(new Bloques("Ladrillo_plano_azul", 1920, 448, true));

        Lista_Bloques.add(new Bloques("Ladrillo_azul", 1940, 208, false));
        Lista_Bloques.add(new Bloques("Ladrillo_azul", 2060, 208, false));
        Lista_Bloques.add(new Bloques("Moneda", 2000, 208, false));
        Lista_Bloques.add(new Bloques("Ladrillo_azul", 2120, 208, false));
        Lista_Bloques.add(new Bloques("Ladrillo_azul", 2120, 148, false));

        // Bandera
        Lista_Bloques.add(new Bloques("Bandera", 2500, 0, false));
    }
    // Ladrillos azules con altura considerable

    private void cargarEnemigos2() {
        Lista_Enemigos.add(new Enemigos("goomba_azul", 800, 400));
        Lista_Enemigos.add(new Enemigos("goomba_azul", 1300, 400));
    }

    private void cargarEnemigos3() {
        Lista_Enemigos.add(new Enemigos("goomba", 1000, 230));
        Lista_Enemigos.add(new Enemigos("goomba", 1000, 200));
        Lista_Enemigos.add(new Enemigos("goomba", 1000, 170));
        Lista_Enemigos.add(new Enemigos("koopa_volador", 300, 150));
    }

    private void cargarPoderes2() {
        // Agregar lógica para inicializar poderes si se requieren
        Lista_Poderes.add(new Poderes(1, 1));
    }

    private void cargarPoderes3() {
        // Agregar lógica para inicializar poderes si se requieren
        // Lista_Poderes.add(new Poderes(1, 1));
    }

    private void cargarBloques() {
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

    private void cargarEnemigos4() {
        Lista_Enemigos.add(new Enemigos("goomba", 800, 400));
        Lista_Enemigos.add(new Enemigos("bola", 750, 220, 70));
        Lista_Enemigos.add(new Enemigos("bola", 750, 220, 100));
        Lista_Enemigos.add(new Enemigos("bola", 750, 220, 130));
        Lista_Enemigos.add(new Enemigos("bola", 750, 220, 160));
        Lista_Enemigos.add(new Enemigos("planta", 1030, 370));
        Lista_Enemigos.add(new Enemigos("koopa", 900, 375));
        Lista_Enemigos.add(new Enemigos("browser", 1750, 320));
    }
    
    private void cargarBloques4() {
        Lista_Bloques.add(new Bloques("Ladrillo_plano", 760, 178, false));
        Lista_Bloques.add(new Bloques("Tuberia", 1000, 307, true));
        Lista_Bloques.add(new Bloques("Ladrillo", 1200, 178, false));
        Lista_Bloques.add(new Bloques("Ladrillo", 100, 178, true));
        Lista_Bloques.add(new Bloques("Moneda", 1260, 178, false));
        Lista_Bloques.add(new Bloques("Moneda", 1320, 178, false));
        Lista_Bloques.add(new Bloques("Ladrillo", 1260, 28, true));
        Lista_Bloques.add(new Bloques("Ladrillo", 1320, 28, true));
        Lista_Bloques.add(new Bloques("Ladrillo", 1380, 28, true));
        Lista_Bloques.add(new Bloques("Moneda", 1380, 178, false));
        Lista_Bloques.add(new Bloques("Moneda", 1440, 178, false));
        Lista_Bloques.add(new Bloques("Ladrillo", 1500, 178, false));
        Lista_Bloques.add(new Bloques("Tuberia", 1600, 307, false));
        Lista_Bloques.add(new Bloques("Tuberia", 2000, 307, false));
        Lista_Bloques.add(new Bloques("Bandera", 2500, 0, false));
    }

    private void cargarEnemigos() {
        Lista_Enemigos.add(new Enemigos("goomba", 800, 400));
        Lista_Enemigos.add(new Enemigos("planta", 1030, 370));
        Lista_Enemigos.add(new Enemigos("koopa", 900, 375));
        Lista_Enemigos.add(new Enemigos("koopa", 1300, 375));

    }

    private void cargarPoderes() {
        // Agregar lógica para inicializar poderes si se requieren
        Lista_Poderes.add(new Poderes(1, 1));
    }

    public void imagenes_Fondo() {
        if (url == null) {
            url = Ventana.class.getResource(this.img_fondo);
        }
    if (url != null) {
    icons[0] = AssetCache.get(this.img_fondo);
        icons[1] = icons[0];
        icons[2] = icons[0];
        }
        for (int i = 0; i < Lista_Bloques.size(); i++) {
            if (Lista_Bloques.get(i).img_fondo != null) {
                Lista_Bloques.get(i).icon = AssetCache.get(Lista_Bloques.get(i).img_fondo);
            }
        }
        for (int i = 0; i < Lista_Enemigos.size(); i++) {
            if (Lista_Enemigos.get(i).img_fondo != null) {
                Lista_Enemigos.get(i).icon = AssetCache.get(Lista_Enemigos.get(i).img_fondo);
            }
        }
        for (int i = 0; i < Lista_Poderes.size(); i++) {
            if (Lista_Poderes.get(i).img_fondo != null) {
                Lista_Poderes.get(i).icon = AssetCache.get(Lista_Poderes.get(i).img_fondo);
            }
        }
        M.icon = AssetCache.get("/Assets/" + M.img_fondo[8]);
    }

    void colision(String d) {
        for (int idx = 0; idx < Lista_Bloques.size(); idx++) {
            Bloques bloque = Lista_Bloques.get(idx);
            if (bloque.tipo.equals("Tuberia")) {
                if (verificarColisionHorizontal(bloque) || verificarColisionVerticalTuberia(bloque)) {
                    break;
                }
            } else if (bloque.tipo.equals("Foso")) {
                if (marianito.intersects(bloque.x - avance_x, bloque.y - 10, bloque.ancho, bloque.largo)) {
                    M.cayendo = true;
                    M.caida();
                }
            } else if (bloque.tipo.startsWith("plat")) {
                if (verificarColisionHorizontal(bloque) || verificarColisionVerticalBloques(bloque)) {
                    break;
                }
                if (M.x_img > 120 && M.yp[0] >= 440 && M.yp[0] <= 456) {
                    M.cayendo = true;
                    M.caida();
                }
            } else {
                if (verificarColisionVerticalBloques(bloque)) {
                    break;
                }
            }
            if (bloque.tipo.equals("Bandera") && !this.banderaTocada) {
                if (marianito.intersects(bloque.x - avance_x, bloque.y, bloque.ancho, bloque.largo)) {
                    // System.out.println("tocaste bandera");
        this.banderaTocada = true;
            SoundManager.stop(bgmClip);
                    SoundManager.play("/Assets/terminado.wav");
            // Activar pantalla de carga y programar el avance de nivel sin hilos bloqueantes
            pantallaCarga = true;
            pendingLevelAdvance = true;
            loadingUntilMs = System.currentTimeMillis() + 1200; // 1.2s de carga
            SwingUtilities.invokeLater(this::repaint);
                }
            }
        }
    }

    private boolean verificarColisionHorizontal(Bloques bloque) {
        if (marianito.intersects(bloque.x2 - avance_x, bloque.y2, bloque.ancho2, bloque.largo2)) {
            if (M.derecha) {
                choca[2] = true;
            } else if (M.izquierda) {
                choca[3] = true;
            }
            return true;
        }
        return false;
    }

    private boolean verificarColisionVerticalTuberia(Bloques bloque) {
        Rectangle pb = marianito.getBounds();
        if (marianito.intersects(bloque.x - avance_x, bloque.y - 10, bloque.ancho + 10, bloque.largo + 10)) {
            int playerTop = pb.y;
            int playerBottom = pb.y + pb.height;
            int bloqueTop = bloque.y;
            int bloqueBottom = bloque.y + bloque.largo;
            // golpea por debajo de la tubería
            if (M.saltando && Math.abs(playerTop - (bloqueBottom)) <= TOLERANCIA) {
                M.saltando = false;
                M.cayendo = true;
                return true;
            }
            // aterriza sobre la tubería
            if (M.cayendo && Math.abs(playerBottom - bloqueTop) <= TOLERANCIA) {
                M.cayendo = false;
                M.encima = true;
                altura_salto = 0;
                return true;
            }
        }
        return false;
    }

    private boolean verificarColisionVerticalBloques(Bloques bloque) {
        Rectangle pb = marianito.getBounds();
        if (marianito.intersects(bloque.x - avance_x, bloque.y - 10, bloque.ancho, bloque.largo + 10)) {
            int playerTop = pb.y;
            int playerBottom = pb.y + pb.height;
            int bloqueTop = bloque.y;
            // golpea por debajo del bloque
            if (M.saltando && Math.abs(playerTop - (bloqueTop + bloque.largo)) <= TOLERANCIA) {
                procesarColisionBloque(bloque);
                return true;
            }
            // aterriza encima del bloque
            if (M.cayendo && Math.abs(playerBottom - bloqueTop) <= TOLERANCIA) {
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
        } else if (bloque.tipo.equals("Ladrillo") || bloque.tipo.equals("Ladrillo_plano_azul")
                || bloque.tipo.equals("Ladrillo_azul")) {
            if (bloque.poder) {
                actualizarBloque(bloque, "Ladrillo_plano", "Ladrillo_plano.png");
                Lista_Poderes.add(new Poderes(bloque.x, bloque.y - 50));
                reproducirSonido("/Assets/powerup_appears.wav");
            } else {
                reproducirSonido(ruta + "ladrillo.wav");
                // Desactivar bloque sin eliminarlo para evitar problemas al iterar
                bloque.ancho = 0;
                bloque.largo = 0;
                bloque.img_fondo = null;
                bloque.icon = null;
                bloque.tipo = "";
            }
        }
        choca[0] = true;
    }

    private void actualizarBloque(Bloques bloque, String nuevoTipo, String nuevaImagen) {
        bloque.tipo = nuevoTipo;
        bloque.img_fondo = nuevaImagen;
        bloque.icon = new ImageIcon(getClass().getResource("/Assets/" + nuevaImagen)).getImage();
    }

    private void reproducirSonido(String archivoSonido) {
    SoundManager.play(archivoSonido);
    }

    public void movimiento(String direccion) {
        if (inMenu || paused) {
            return; // no mover mientras está en menú o pausa
        }
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

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // Menú inicial
        if (inMenu) {
            if (icons == null || icons.length < 1 || icons[0] == null) {
                URL bg = Ventana.class.getResource(this.img_fondo);
                if (bg != null) {
                    icons = new Image[] { new ImageIcon(bg).getImage(), new ImageIcon(bg).getImage(), new ImageIcon(bg).getImage() };
                }
            }
            if (icons != null && icons[0] != null) {
                g.drawImage(icons[0], 0, 0, getWidth(), getHeight(), null);
            } else {
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
            g.setColor(new Color(0,0,0,150));
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(Color.WHITE);
            g.setFont(new Font("SansSerif", Font.BOLD, 48));
            g.drawString("MARIANITO", getWidth()/2 - 150, getHeight()/2 - 60);
            g.setFont(new Font("SansSerif", Font.PLAIN, 22));
            g.drawString("Enter: Iniciar", getWidth()/2 - 90, getHeight()/2 - 10);
            g.drawString("Flechas: Mover | Up: Saltar | P: Pausa | Esc: Salir", getWidth()/2 - 240, getHeight()/2 + 20);
            return;
        }

        boolean doLogic = !paused && shouldUpdateLogic();
        if (doLogic) {
            sec2++;
            cont += 50;
            if (cont % 1000 == 0) {
                tiempo--;
            }
        }
        if (M.yp[0] >= 808) {
            vidas--;
            this.reset();
            if (nivel == 3) {
                choca[0] = false;
            }
        }
    // Manejo de pantalla de carga y transición de nivel sin dormir hilos
        if (pantallaCarga) {
            if (pendingLevelAdvance && System.currentTimeMillis() >= loadingUntilMs) {
                // Avanzar de nivel
                pendingLevelAdvance = false;
                banderaTocada = false;
                signivel = true;
                nivel += 1;
                img_fondo = "/Assets/mundo" + nivel + ".png";
                cancion = "/Assets/mundo" + nivel + ".wav";
                // música se gestiona con SoundManager en reset()
                this.reset();
                // Mostrar un poco más la pantalla de carga para suavizar
                loadingUntilMs = System.currentTimeMillis() + 300; // 0.3s adicional opcional
            }
            if (System.currentTimeMillis() < loadingUntilMs) {
                g.drawImage(new ImageIcon(getClass().getResource("/Assets/cargando.gif")).getImage(), 0, 0, null);
                return;
            } else {
                pantallaCarga = false; // Termina la pantalla de carga
            }
        }
        // Asegurar fondo inicializado para evitar NPE
        if (icons == null || icons.length < 3 || icons[0] == null) {
            Image bg = AssetCache.get(this.img_fondo);
            if (bg != null) {
                icons = new Image[] { bg, bg, bg };
            } else {
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, getWidth(), getHeight());
                return;
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

        for (int i = 0; i < Lista_Poderes.size(); i++) {
            Lista_Poderes.get(i).icon = AssetCache.get(Lista_Poderes.get(i).img_fondo);
            g.drawImage(Lista_Poderes.get(i).icon, Lista_Poderes.get(i).x - avance_x, Lista_Poderes.get(i).y, null);

            Rectangle re = new Rectangle(Lista_Poderes.get(i).x - avance_x, Lista_Poderes.get(i).y,
                    Lista_Poderes.get(i).largo, Lista_Poderes.get(i).ancho);
            // verificar colisión con marianito con el hongo
            Rectangle personaje = new Rectangle(M.xp[0], M.yp[0], marianito.getBounds().width,
                    marianito.getBounds().height);
            if (doLogic) {
                if (re.intersects(personaje)) {
                    SoundManager.play("/Assets/vida.wav");
                    vidas++;
                    Lista_Poderes.remove(i);
                    // importante: ajustar índice
                    i--;
                    continue;
                }
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

                            if (Lista_Poderes.size()>i) {
                                Lista_Poderes.get(i).choca = true;
                                if (Lista_Poderes.get(i).cayendo) {
                                    Lista_Poderes.get(i).derecha = true;
                                    Lista_Poderes.get(i).cayendo = false;
                                }
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
                        SoundManager.play("/Assets/vida.wav");
                        Lista_Poderes.remove(i);
                    }
                }
            }
            }

        }

        for (int i = 0; i < Lista_Enemigos.size(); i++) {
            int d = 1;
            if (!Lista_Enemigos.get(i).muerto) {
                if (nivel == 3 && Lista_Enemigos.get(i).tipo.equals("koopa_volador")) {
                    if (doLogic) {
                        Enemigos enemigo = Lista_Enemigos.get(i);
                        enemigo.movVertical();
                    }
                } else {
                    if (Lista_Enemigos.get(i).tipo.equals("koopa")) {
                        if (Lista_Enemigos.get(i).derecha) {
                            d = 1;
                        } else {
                            d = 3;
                        }
                    }
                }

                String tipo = Lista_Enemigos.get(i).tipo;
        int frameBase = 1;

        if ("koopa".equals(tipo)) {
            // koopa normal: 1-2 derecha, 3-4 izquierda
            frameBase = Lista_Enemigos.get(i).derecha ? 1 : 3;
        } else if ("koopa_volador".equals(tipo)) {
            // volador: si solo tienes 1 y 2, deja esto en 1
            frameBase = 1;
            // Si además tienes 3/4 para mirar a la izquierda, usa:
            // frameBase = Lista_Enemigos.get(i).derecha ? 1 : 3;
        } else if ("goomba".equals(tipo) || "goomba_azul".equals(tipo)) {
            frameBase = 1; // 1-2
        } else if ("bomba".equals(tipo)) {
            frameBase = 1; // bomba no alterna si no tienes 2 frames
        }

        int frame = (sec2 <= 7) ? frameBase : frameBase + 1;
        String path = "/Assets/" + tipo + frame + ".png";
        Lista_Enemigos.get(i).img_fondo = path;
        Lista_Enemigos.get(i).icon = AssetCache.get(path);

        g.drawImage(Lista_Enemigos.get(i).icon, Lista_Enemigos.get(i).x - avance_x, Lista_Enemigos.get(i).y, null);
                        
                Rectangle re = new Rectangle(Lista_Enemigos.get(i).x - avance_x, Lista_Enemigos.get(i).y,
                        Lista_Enemigos.get(i).alto, Lista_Enemigos.get(i).ancho);
                if (doLogic) {
                for (int j = 0; j < Lista_Bloques.size(); j++) {
                    if (Lista_Bloques.get(j).tipo.equals("Tuberia")) {
                        if (re.intersects(Lista_Bloques.get(j).x2 - avance_x, Lista_Bloques.get(j).y2,
                                Lista_Bloques.get(j).ancho2, Lista_Bloques.get(j).largo2)) {
                            if (Lista_Enemigos.get(i).derecha) {
                                if ( Lista_Enemigos.get(i).tipo.equals("browser")) {
                                    Random r = new Random();
                                    if (r.nextBoolean()) {
                                        Lista_Enemigos.add(new Enemigos("bomba", Lista_Enemigos.get(i).x, 100));
                                    }
                                }
                                Lista_Enemigos.get(i).derecha = false;
                                Lista_Enemigos.get(i).izquierda = true;
                            } else {
                                if ( Lista_Enemigos.get(i).tipo.equals("browser")) {
                                    Random r = new Random();
                                    if (r.nextBoolean()) {
                                        Lista_Enemigos.add(new Enemigos("bomba", Lista_Enemigos.get(i).x, 250));
                                    }
                                }
                                Lista_Enemigos.get(i).derecha = true;
                                Lista_Enemigos.get(i).izquierda = false;
                            }
                            break;
                        }
                    } else if (re.intersects(Lista_Bloques.get(j).x - avance_x, Lista_Bloques.get(j).y,
                            Lista_Bloques.get(j).ancho, Lista_Bloques.get(j).largo)) {
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
                if (doLogic) {
                    if (Lista_Enemigos.get(i).tipo.equals("planta")) {
                        Enemigos enemigo = Lista_Enemigos.get(i);
                        enemigo.movVertical();
                    } else {
                        if (!(nivel == 3 && Lista_Enemigos.get(i).tipo.equals("koopa_volador"))) {
                            if ( Lista_Enemigos.get(i).tipo.equals("bola")) {
                                Lista_Enemigos.get(i).movCircular(780, 200, Lista_Enemigos.get(i).radio, angulo );
                                angulo = (angulo + 2) % 360;
                            } else {
                                Lista_Enemigos.get(i).mov();
                            }
                        }
                    }
                }

                if (doLogic && marianito.intersects(Lista_Enemigos.get(i).x - avance_x, Lista_Enemigos.get(i).y, 48, 48)) {
                    if (avance_y == 0) {
                        SoundManager.play("/Assets/muerte.wav");
                        vidas--;
                        if (vidas == 0) {
                            termina = true;
                            SoundManager.stop(bgmClip);
                        }
                        this.reset();
                    } else {
                        Lista_Enemigos.get(i).muerto = true;
                        // obtener tipo de enemigo
                        if (!(Lista_Enemigos.get(i).tipo.equals("bala")||Lista_Enemigos.get(i).tipo.equals("planta"))) {
                            reproducirSonidoMuerteEnemigo(tipo);
                        }
                    }
                }
            } else {
                Lista_Enemigos.get(i).img_fondo = "/Assets/" + Lista_Enemigos.get(i).tipo + "_M.png";
                url = Ventana.class.getResource(Lista_Enemigos.get(i).img_fondo);
                if (Lista_Enemigos.get(i).tipo.equals("planta") || Lista_Enemigos.get(i).tipo.equals("bomba")|| Lista_Enemigos.get(i).tipo.equals("bola") ) {
                    if (doLogic) {
                        SoundManager.play("/Assets/muerte.wav");
                        vidas--;
                        if (vidas == 0) {
                            termina = true;
                            SoundManager.stop(bgmClip);
                        }
                        this.reset();
                    }
                } else {
                    Lista_Enemigos.get(i).icon = new ImageIcon(url).getImage();
                    g.drawImage(Lista_Enemigos.get(i).icon, Lista_Enemigos.get(i).x - avance_x,
                    448 - Lista_Enemigos.get(i).img_M, null);
                }
            }
        }

        for (int i = 0; i < Lista_Bloques.size(); i++) {
            if (Lista_Bloques.get(i).tipo.equals("Tuberia")) {
                g.drawRect(Lista_Bloques.get(i).x2 - avance_x, Lista_Bloques.get(i).y2, Lista_Bloques.get(i).ancho2,
                        Lista_Bloques.get(i).largo2);
            }
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
            if (Lista_Bloques.get(i).tipo.equals("Bandera")) {
                g.drawImage(Lista_Bloques.get(i).icon, (Lista_Bloques.get(i).x - avance_x) - 160,
                        Lista_Bloques.get(i).y - 40, null);
            } else if (Lista_Bloques.get(i).tipo.equals("Foso")) {
                g.drawImage(Lista_Bloques.get(i).icon, Lista_Bloques.get(i).x - avance_x, Lista_Bloques.get(i).y, null);
            } else {
                if (Lista_Bloques.get(i).icon != null) {
                    g.drawImage(Lista_Bloques.get(i).icon, Lista_Bloques.get(i).x - avance_x, Lista_Bloques.get(i).y, null);
                }
            }

        }

        // Actualizar lógica de salto/caída sólo en ticks lógicos (no en pausa)
        if (doLogic) {
            if (M.saltando) {
                movimiento("Arriba");
            }
            if (M.cayendo) {
                movimiento("Abajo");
            }
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

        // Overlay de pausa después de dibujar todo (mantiene visible a Mario y el mundo)
        if (paused) {
            g.setColor(new Color(0,0,0,140));
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(Color.WHITE);
            g.setFont(new Font("SansSerif", Font.BOLD, 36));
            g.drawString("PAUSA", getWidth()/2 - 60, getHeight()/2);
            return;
        }

        // Pantalla final si se ganó el juego
        if (gameWon) {
            g.setColor(new Color(0,0,0,200));
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(Color.WHITE);
            g.setFont(new Font("SansSerif", Font.BOLD, 40));
            g.drawString("¡Felicidades! Has completado el juego", getWidth()/2 - 360, getHeight()/2 - 20);
            g.setFont(new Font("SansSerif", Font.PLAIN, 22));
            g.drawString("Enter: Jugar de nuevo | Esc: Salir", getWidth()/2 - 190, getHeight()/2 + 20);
        }

    }

    private void reproducirSonidoMuerteEnemigo(String tipo) {
    SoundManager.play("/Assets/muerte_" + tipo + ".wav");
    }

    // Controla si corresponde actualizar la lógica este frame (paso fijo ~47ms)
    private boolean shouldUpdateLogic() {
        long now = System.currentTimeMillis();
        if (lastLogicUpdateMs == 0L) {
            lastLogicUpdateMs = now;
            return true;
        }
        if (now - lastLogicUpdateMs >= LOGIC_DT_MS) {
            // avanzar en múltiplos de LOGIC_DT_MS para evitar drift
            long steps = (now - lastLogicUpdateMs) / LOGIC_DT_MS;
            lastLogicUpdateMs += steps * LOGIC_DT_MS;
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws InterruptedException, Throwable {
        // Ejecutar todo en el EDT para evitar condiciones de carrera con Swing
        JFrame Ventana_Juego = new JFrame("Mario Bross");
        Ventana_Juego.setSize(1324, 615);
        Ventana_Juego.setResizable(false);
        Ventana_Juego.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Ventana_Juego.setLocationRelativeTo(null);
        Ventana juego = new Ventana();

        Ventana_Juego.add(juego);
        Ventana_Juego.setVisible(true);

        // Manejo del KeyListener directamente (EDT)
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
                // Inicio desde el menú
                if (tecla.equals("Intro") || tecla.equals("Enter")) {
                    if (juego.inMenu) {
                        juego.inMenu = false;
                        juego.reset();
                        return;
                    }
                }
                // Pausa
                if (tecla.equalsIgnoreCase("P")) {
                    juego.paused = !juego.paused;
                    return;
                }
                // Salir
                if (tecla.equals("Escape")) {
                    SoundManager.stop(juego.bgmClip);
                    Ventana_Juego.dispose();
                    System.exit(0);
                }
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

    // Timer de juego para repintado suave (60 FPS aprox) y manejo de fin de juego
    Timer timer = new Timer(16, e -> {
            if (!juego.termina) {
                juego.repaint();
            } else if (!juego.gameOverHandled) {
                juego.gameOverHandled = true;
        SoundManager.stop(juego.bgmClip);
        SoundManager.play("/Assets/gameover.wav");
                Object[] opciones = { "Si", "No" };
                int n = JOptionPane.showOptionDialog(null, "¿Quieres volver a jugar?", "Fin del juego",
                        JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[1]);
                if (n == 0) {
                    juego.vidas = 3; // Reiniciar vidas
                    juego.termina = false;
                    juego.gameOverHandled = false;
                    juego.reset();
                } else {
            SoundManager.stop(juego.bgmClip);
                    Ventana_Juego.dispose();
                    System.exit(0);
                }
            }
        });
        timer.start();

    }
}
