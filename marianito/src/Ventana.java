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
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Ventana extends JPanel {

    
    static Nivel nivelVentana;
    boolean termina = false;

    @Override
    public void paint(Graphics g) {

        super.paint(g);
        nivelVentana.sec2++;
        nivelVentana.cont += 50;
        if (nivelVentana.cont % 1000 == 0) {
            nivelVentana.tiempo--;
        }
        if (nivelVentana.M.yp[0] >= 808) {
            nivelVentana.vidas--;
            nivelVentana.reset();
            if (nivelVentana.nivel == 3) {
                nivelVentana.choca[0] = false;
            }
        }

        int anchoImagen = nivelVentana.icons[0].getWidth(null);
        int totalAncho = getWidth() / anchoImagen + 2;
        int offset_x = nivelVentana.avance_x % anchoImagen;

        for (int i = 0; i < totalAncho + 2; i++) {
            g.drawImage(nivelVentana.icons[0], i * anchoImagen - offset_x, 0, null);
        }
        g.drawImage(nivelVentana.icons[0], 0 - nivelVentana.avance_x, 0, null);
        g.drawImage(nivelVentana.icons[1], 1024 - nivelVentana.avance_x, 0, null);
        g.drawImage(nivelVentana.icons[2], 2048 - nivelVentana.avance_x, 0, null);

        Font fuente = new Font("Source Code Pro Black", Font.BOLD, 25);
        g.setFont(fuente);
        g.drawString("MARIO", 40, 30);
        g.drawString("" + nivelVentana.vidas, 40, 60);
        g.drawString("MONEDAS X " + nivelVentana.puntaje, 300, 60);
        g.drawString("MUNDO", 600, 30);
        g.drawString("1-1", 600, 60);
        g.drawString("TIEMPO", 900, 30);
        g.drawString("" + nivelVentana.tiempo, 900, 60);

        for (int i = 0; i < nivelVentana.Lista_Bloques.size(); i++) {
            // g.drawRect(Lista_Bloques.get(i).x - avance_x, Lista_Bloques.get(i).y,
            // Lista_Bloques.get(i).ancho,
            // Lista_Bloques.get(i).largo);
            if (nivelVentana.Lista_Bloques.get(i).tipo.equals("Tuberia")) {
                g.drawRect(nivelVentana.Lista_Bloques.get(i).x2 - nivelVentana.avance_x,
                        nivelVentana.Lista_Bloques.get(i).y2, nivelVentana.Lista_Bloques.get(i).ancho2,
                        nivelVentana.Lista_Bloques.get(i).largo2);
            }
            // if (Lista_Bloques.get(i).tipo.equals("Bandera")) {
            // g.drawRect((Lista_Bloques.get(i).x - avance_x) , Lista_Bloques.get(i).y,
            // Lista_Bloques.get(i).ancho,
            // Lista_Bloques.get(i).largo);
            // }
            if (nivelVentana.Lista_Bloques.get(i).tipo.equals("Moneda")) {
                if (nivelVentana.sec2 <= 7) {
                    nivelVentana.Lista_Bloques.get(i).img_fondo = "/Assets/Moneda.png";
                    nivelVentana.url = Ventana.class.getResource(nivelVentana.Lista_Bloques.get(i).img_fondo);
                    nivelVentana.Lista_Bloques.get(i).icon = new ImageIcon(nivelVentana.url).getImage();
                } else {
                    nivelVentana.Lista_Bloques.get(i).img_fondo = "/Assets/Moneda2.png";
                    nivelVentana.url = Ventana.class.getResource(nivelVentana.Lista_Bloques.get(i).img_fondo);
                    nivelVentana.Lista_Bloques.get(i).icon = new ImageIcon(nivelVentana.url).getImage();
                }
            }
            // g.drawImage(Lista_Bloques.get(i).icon, Lista_Bloques.get(i).x - avance_x,
            // Lista_Bloques.get(i).y, null);

            if (nivelVentana.Lista_Bloques.get(i).tipo.equals("Bandera")) {
                g.drawImage(nivelVentana.Lista_Bloques.get(i).icon,
                        (nivelVentana.Lista_Bloques.get(i).x - nivelVentana.avance_x) - 160,
                        nivelVentana.Lista_Bloques.get(i).y - 40, null);
            } else if (nivelVentana.Lista_Bloques.get(i).tipo.equals("Foso")) {
                g.drawImage(nivelVentana.Lista_Bloques.get(i).icon,
                        nivelVentana.Lista_Bloques.get(i).x - nivelVentana.avance_x,
                        nivelVentana.Lista_Bloques.get(i).y, null);
            } else {
                g.drawImage(nivelVentana.Lista_Bloques.get(i).icon,
                        nivelVentana.Lista_Bloques.get(i).x - nivelVentana.avance_x,
                        nivelVentana.Lista_Bloques.get(i).y, null);
            }
            // imprimir posicion de los bloques
            // System.out.println("Bloque: "+ Lista_Bloques.get(i).tipo+"" +
            // Lista_Bloques.get(i).x + " " + Lista_Bloques.get(i).y);
        }
        for (int i = 0; i < nivelVentana.Lista_Poderes.size(); i++) {
            nivelVentana.url = Ventana.class.getResource(nivelVentana.Lista_Poderes.get(i).img_fondo);
            nivelVentana.Lista_Poderes.get(i).icon = new ImageIcon(nivelVentana.url).getImage();
            g.drawImage(nivelVentana.Lista_Poderes.get(i).icon,
                    nivelVentana.Lista_Poderes.get(i).x - nivelVentana.avance_x, nivelVentana.Lista_Poderes.get(i).y,
                    null);

            Rectangle re = new Rectangle(nivelVentana.Lista_Poderes.get(i).x - nivelVentana.avance_x,
                    nivelVentana.Lista_Poderes.get(i).y,
                    nivelVentana.Lista_Poderes.get(i).largo, nivelVentana.Lista_Poderes.get(i).ancho);
            // verificar colisión con marianito con el hongo
            Rectangle personaje = new Rectangle(nivelVentana.M.xp[0], nivelVentana.M.yp[0],
                    nivelVentana.marianito.getBounds().width,
                    nivelVentana.marianito.getBounds().height);
            if (re.intersects(personaje)) {
                nivelVentana.url = Ventana.class.getResource("/Assets/vida.wav");
                AudioClip sonido = Applet.newAudioClip(nivelVentana.url);
                sonido.play();
                // incrementar vidas
                nivelVentana.vidas++;
                // eliminar hongo
                nivelVentana.Lista_Poderes.remove(i);
            }
            /// mn g.drawRect(re.x, re.y, re.width, re.height);
            for (int j = 0; j < nivelVentana.Lista_Bloques.size(); j++) {
                if (nivelVentana.Lista_Bloques.get(j).tipo.equals("Tuberia")) {
                    if (re.intersects(nivelVentana.Lista_Bloques.get(j).x2 - nivelVentana.avance_x,
                            nivelVentana.Lista_Bloques.get(j).y2,
                            nivelVentana.Lista_Bloques.get(j).ancho2, nivelVentana.Lista_Bloques.get(j).largo2)) {
                        if (nivelVentana.Lista_Poderes.get(i).derecha) {
                            nivelVentana.Lista_Poderes.get(i).derecha = false;
                            nivelVentana.Lista_Poderes.get(i).izquierda = true;
                        }
                    }
                }
                if (re.intersects(nivelVentana.Lista_Bloques.get(j).x - nivelVentana.avance_x,
                        nivelVentana.Lista_Bloques.get(j).y, nivelVentana.Lista_Bloques.get(j).ancho,
                        nivelVentana.Lista_Bloques.get(j).largo)) {

                    nivelVentana.Lista_Poderes.get(i).choca = true;
                    if (nivelVentana.Lista_Poderes.get(i).cayendo) {
                        nivelVentana.Lista_Poderes.get(i).derecha = true;
                        nivelVentana.Lista_Poderes.get(i).cayendo = false;
                    }
                }
            }
            if (nivelVentana.Lista_Poderes.size() > 0 && !nivelVentana.Lista_Poderes.get(i).choca) {
                if (nivelVentana.Lista_Poderes.get(i).derecha && nivelVentana.Lista_Poderes.get(i).y < 448) {
                    nivelVentana.Lista_Poderes.get(i).derecha = false;
                    nivelVentana.Lista_Poderes.get(i).cayendo = true;
                    break;
                }
                if (nivelVentana.Lista_Poderes.get(i).y + nivelVentana.Lista_Poderes.get(i).largo >= 448) {
                    if (!nivelVentana.Lista_Poderes.get(i).izquierda) {
                        nivelVentana.Lista_Poderes.get(i).derecha = true;
                    } else {
                        nivelVentana.Lista_Poderes.get(i).derecha = false;
                    }
                    nivelVentana.Lista_Poderes.get(i).cayendo = false;
                }

            }
            if (nivelVentana.Lista_Poderes.size() > 0) {

                nivelVentana.Lista_Poderes.get(i).choca = false;
                nivelVentana.Lista_Poderes.get(i).movimiento();
                if (nivelVentana.marianito.intersects(re)) {
                    if (nivelVentana.Lista_Poderes.get(i).img_fondo.equals("hongo.png")) {
                        nivelVentana.vidas++;
                        nivelVentana.url = Ventana.class.getResource("/Assets/vida.wav");
                        nivelVentana.musica = Applet.newAudioClip(nivelVentana.url);
                        nivelVentana.musica.play();
                        nivelVentana.Lista_Poderes.remove(i);
                    }
                }
            }

        }

        for (int i = 0; i < nivelVentana.Lista_Enemigos.size(); i++) {
            int d = 1;
            if (!nivelVentana.Lista_Enemigos.get(i).muerto) {
                if (nivelVentana.Lista_Enemigos.get(i).tipo.equals("koopa")) {
                    if (nivelVentana.Lista_Enemigos.get(i).derecha) {
                        d = 1;
                    } else {
                        d = 3;
                    }
                }
                if (nivelVentana.sec2 <= 7) {
                    nivelVentana.Lista_Enemigos.get(i).img_fondo = "/Assets/" + nivelVentana.Lista_Enemigos.get(i).tipo
                            + (d + ".png");
                    nivelVentana.url = Ventana.class.getResource(nivelVentana.Lista_Enemigos.get(i).img_fondo);
                    nivelVentana.Lista_Enemigos.get(i).icon = new ImageIcon(nivelVentana.url).getImage();
                } else {
                    nivelVentana.Lista_Enemigos.get(i).img_fondo = "/Assets/" + nivelVentana.Lista_Enemigos.get(i).tipo
                            + (d + 1) + ".png";
                    nivelVentana.url = Ventana.class.getResource(nivelVentana.Lista_Enemigos.get(i).img_fondo);
                    nivelVentana.Lista_Enemigos.get(i).icon = new ImageIcon(nivelVentana.url).getImage();
                }
                g.drawImage(nivelVentana.Lista_Enemigos.get(i).icon,
                        nivelVentana.Lista_Enemigos.get(i).x - nivelVentana.avance_x,
                        nivelVentana.Lista_Enemigos.get(i).y,
                        null);
                Rectangle re = new Rectangle(nivelVentana.Lista_Enemigos.get(i).x - nivelVentana.avance_x,
                        nivelVentana.Lista_Enemigos.get(i).y,
                        nivelVentana.Lista_Enemigos.get(i).alto, nivelVentana.Lista_Enemigos.get(i).ancho);
                for (int j = 0; j < nivelVentana.Lista_Bloques.size(); j++) {
                    if (nivelVentana.Lista_Bloques.get(j).tipo.equals("Tuberia")) {
                        if (re.intersects(nivelVentana.Lista_Bloques.get(j).x2 - nivelVentana.avance_x,
                                nivelVentana.Lista_Bloques.get(j).y2,
                                nivelVentana.Lista_Bloques.get(j).ancho2, nivelVentana.Lista_Bloques.get(j).largo2)) {
                            if (nivelVentana.Lista_Enemigos.get(i).derecha) {
                                nivelVentana.Lista_Enemigos.get(i).derecha = false;
                                nivelVentana.Lista_Enemigos.get(i).izquierda = true;
                            } else {
                                nivelVentana.Lista_Enemigos.get(i).derecha = true;
                                nivelVentana.Lista_Enemigos.get(i).izquierda = false;
                            }
                            break;
                        }
                    }
                }
                if (nivelVentana.Lista_Enemigos.get(i).tipo.equals("planta")) {
                    Enemigos enemigo = nivelVentana.Lista_Enemigos.get(i);
                    enemigo.movEstatico();
                } else {
                    nivelVentana.Lista_Enemigos.get(i).mov();
                }

                if (nivelVentana.marianito.intersects(nivelVentana.Lista_Enemigos.get(i).x - nivelVentana.avance_x,
                        nivelVentana.Lista_Enemigos.get(i).y, 48, 48)) {
                    if (nivelVentana.avance_y == 0) {
                        nivelVentana.url = Ventana.class.getResource("muerte.wav");
                        nivelVentana.musica = Applet.newAudioClip(nivelVentana.url);
                        nivelVentana.musica.play();
                        nivelVentana.vidas--;
                        nivelVentana.reset();
                        if (nivelVentana.vidas == 0) {
                            nivelVentana.vidas = 3;
                            termina = true;
                        }
                    } else {
                        nivelVentana.Lista_Enemigos.get(i).muerto = true;
                        // obtener tipo de enemigo
                        String tipo = nivelVentana.Lista_Enemigos.get(i).tipo;
                        reproducirSonidoMuerteEnemigo(tipo);
                    }
                }
            } else {
                nivelVentana.Lista_Enemigos.get(i).img_fondo = "/Assets/" + nivelVentana.Lista_Enemigos.get(i).tipo
                        + "_M.png";
                nivelVentana.url = Ventana.class.getResource(nivelVentana.Lista_Enemigos.get(i).img_fondo);
                nivelVentana.Lista_Enemigos.get(i).icon = new ImageIcon(nivelVentana.url).getImage();
                g.drawImage(nivelVentana.Lista_Enemigos.get(i).icon,
                        nivelVentana.Lista_Enemigos.get(i).x - nivelVentana.avance_x,
                        448 - nivelVentana.Lista_Enemigos.get(i).img_M, null);
            }
        }

        if (nivelVentana.M.saltando) {
            nivelVentana.movimiento("Arriba");
        }
        if (nivelVentana.M.cayendo) {
            nivelVentana.movimiento("Abajo");
        }

        if (nivelVentana.M.cayendo || nivelVentana.M.saltando) {
            g.drawImage(nivelVentana.M.icon, nivelVentana.avance_x, 378 + nivelVentana.avance_y, null);
        } else {
            g.drawImage(nivelVentana.M.icon, nivelVentana.M.x_img, 368 + nivelVentana.avance_y, null);

        }
        // g.drawPolygon(marianito);
        if (nivelVentana.sec2 == 14) {
            nivelVentana.sec2 = 0;
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

    @SuppressWarnings("removal")
    public static void main(String[] args) throws InterruptedException, Throwable {

        nivelVentana = new Nivel(1);
        Thread pintado;
        Thread tecladoHilo; // Hilo para manejar las teclas

        JFrame Ventana_Juego = new JFrame("Mario Bross");
        Ventana_Juego.setSize(1324, 615);
        Ventana_Juego.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Ventana juego = new Ventana();

        Ventana_Juego.add(juego);
        Ventana_Juego.setVisible(true);

        // Inicializa la música de fondo
        URL url = Ventana.class.getResource(nivelVentana.cancion);
        nivelVentana.musica = Applet.newAudioClip(url);
        nivelVentana.musica.loop();
        nivelVentana.musica.play();

        // Manejo del KeyListener en un hilo aparte
        tecladoHilo = new Thread(() -> {
            Ventana_Juego.addKeyListener((KeyListener) new KeyAdapter() {
                String tecla;

                @Override
                public void keyReleased(KeyEvent e) {
                    tecla = KeyEvent.getKeyText(e.getKeyCode());
                    if (tecla.equals("Derecha") || tecla.equals("Right")) {
                        nivelVentana.movimiento("Parado");
                    }
                    if (tecla.equals("Izquierda") || tecla.equals("Left")) {
                        nivelVentana.movimiento("Parado2");
                    }
                }

                @Override
                public void keyPressed(KeyEvent e) {
                    tecla = KeyEvent.getKeyText(e.getKeyCode());
                    if (tecla.equals("Derecha") || tecla.equals("Right")) {
                        nivelVentana.movimiento("Derecha");
                    }
                    if (tecla.equals("Izquierda") || tecla.equals("Left")) {
                        nivelVentana.movimiento("Izquierda");
                    }
                    if (tecla.equals("Arriba") || tecla.equals("Up")) {
                        nivelVentana.movimiento("Arriba");
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
                        nivelVentana.musica = Applet.newAudioClip(url2);
                        nivelVentana.musica.play();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Object[] opciones = { "Si", "No" };
                    int n = JOptionPane.showOptionDialog(null, "¿Quieres volver a jugar?", "Fin del juego",
                            JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones,
                            opciones[1]);
                    if (n == 0) {
                        nivelVentana.reset();
                    } else {
                        nivelVentana.musica.stop();
                        Ventana_Juego.dispose();
                        break;
                    }
                }
            }
        });
        pintado.start(); // Inicia el hilo para el pintado

    }

}
