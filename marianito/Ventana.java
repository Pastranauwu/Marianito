import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Rectangle;
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
    // LinkedList<Poderes> Lista_Poderes;
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
    String img_fondo;
    URL url;
    Image icons[];
    int cont = 0;
    int tiempo = 1000;

    public void reset() {
        choca = new boolean[4];
        choca[0] = false;
        choca[1] = false;
        choca[2] = false;
        choca[3] = false;
        Lista_Bloques = new LinkedList<>();
        Lista_Enemigos = new LinkedList<>();
        // Lista_Poderes = new LinkedList();
        img_fondo = "mundo.png";
        url = Ventana.class.getResource(img_fondo);
        M = new Marianito();
        marianito = new Polygon();
        marianito.xpoints = M.xp;
        marianito.ypoints = M.yp;
        marianito.npoints = M.xp.length;
        avance_x = 0;
        avance_y = 0;
        sec = 0;
        icons = new Image[3];
        Lista_Bloques.add(new Bloques("Ladrillo", 640, 178, true));
        Lista_Bloques.add(new Bloques("Moneda", 700, 178, false));
        Lista_Bloques.add(new Bloques("Ladrillo_plano", 760, 178, false));
        Lista_Bloques.add(new Bloques("Ladrillo_plano", 820, 178, false));
        Lista_Bloques.add(new Bloques("Tuberia", 1000, 307, false));
        Lista_Bloques.add(new Bloques("Ladrillo", 1200, 178, false));
        Lista_Bloques.add(new Bloques("Moneda", 1260, 178, false));
        Lista_Bloques.add(new Bloques("Moneda", 1320, 178, false));
        Lista_Bloques.add(new Bloques("Ladrillo", 1260, 28, true));
        Lista_Bloques.add(new Bloques("Ladrillo", 1320, 28, true));
        Lista_Bloques.add(new Bloques("Ladrillo", 1380, 178, false));
        Lista_Enemigos.add(new Enemigos("goomba", 800));
        Lista_Enemigos.add(new Enemigos("koopa", 900));
        Lista_Enemigos.add(new Enemigos("koopa", 1300));
        Lista_Bloques.add(new Bloques("Tuberia", 1500, 307, false));
        Lista_Enemigos.add(new Enemigos("goomba", 1650));
        Lista_Enemigos.add(new Enemigos("goomba", 1800));
        Lista_Bloques.add(new Bloques("Tuberia", 2000, 307, false));
        termina = false;
    }

    public Ventana() {
        choca = new boolean[4];
        choca[0] = false;
        choca[1] = false;
        choca[2] = false;
        choca[3] = false;
        Lista_Bloques = new LinkedList<>();
        Lista_Enemigos = new LinkedList<>();
        // Lista_Poderes = new LinkedList();
        img_fondo = "mundo.png";
        url = Ventana.class.getResource(img_fondo);
        // System.out.println(url);
        M = new Marianito();
        marianito = new Polygon();
        marianito.xpoints = M.xp;
        marianito.ypoints = M.yp;
        marianito.npoints = M.xp.length;
        avance_x = 0;
        avance_y = 0;
        sec = 0;
        icons = new Image[3];
        Lista_Bloques.add(new Bloques("Ladrillo", 640, 178, true));
        Lista_Bloques.add(new Bloques("Moneda", 700, 178, false));
        Lista_Bloques.add(new Bloques("Ladrillo_plano", 760, 178, false));
        Lista_Bloques.add(new Bloques("Ladrillo_plano", 820, 178, false));
        Lista_Bloques.add(new Bloques("Tuberia", 1000, 307, false));
        Lista_Bloques.add(new Bloques("Ladrillo", 1200, 178, false));
        Lista_Bloques.add(new Bloques("Moneda", 1260, 178, false));
        Lista_Bloques.add(new Bloques("Moneda", 1320, 178, false));
        Lista_Bloques.add(new Bloques("Ladrillo", 1260, 28, true));
        Lista_Bloques.add(new Bloques("Ladrillo", 1320, 28, true));
        Lista_Bloques.add(new Bloques("Ladrillo", 1380, 178, false));
        // Lista_Enemigos.add(new Enemigos("goomba", 800));
        // Lista_Enemigos.add(new Enemigos("koopa", 900));
        // Lista_Enemigos.add(new Enemigos("koopa", 1300));
        Lista_Bloques.add(new Bloques("Tuberia", 1500, 307, false));
        // Lista_Enemigos.add(new Enemigos("goomba", 1650));
        // Lista_Enemigos.add(new Enemigos("goomba", 1800));
        Lista_Bloques.add(new Bloques("Tuberia", 2000, 307, false));
        imagenes_Fondo();
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
        url = Ventana.class.getResource(M.img_fondo[8]);
        M.icon = new ImageIcon(url).getImage();

    }

    @SuppressWarnings("removal")
    void colision(String d) {
        for (int i = 0; i < Lista_Bloques.size(); i++) {
            if (Lista_Bloques.get(i).tipo.equals("Tuberia")) {
                if (marianito.intersects(Lista_Bloques.get(i).x2 - avance_x, Lista_Bloques.get(i).y2,
                        Lista_Bloques.get(i).ancho2, Lista_Bloques.get(i).largo2)) {
                    if (M.derecha) {
                        choca[2] = true;
                        break;
                    }
                    if (M.izquierda) {
                        choca[3] = true;
                        break;
                    }
                }
                if (marianito.intersects(Lista_Bloques.get(i).x - avance_x, Lista_Bloques.get(i).y - 10,
                        Lista_Bloques.get(i).ancho + 10, Lista_Bloques.get(i).largo + 10)) {

                    if (M.saltando) {
                        // System.out.println((448 + avance_y) + " " + (Lista_Bloques.get(i).y + 70));
                        if (448 + avance_y == Lista_Bloques.get(i).y + 70) {
                            M.saltando = false;
                            M.cayendo = true;
                            break;
                        }
                    }
                    if (M.cayendo) {
                        if (448 + avance_y - 11 == Lista_Bloques.get(i).y - 10) {
                            M.cayendo = false;
                            M.encima = true;
                            altura_salto = 0;
                            break;
                        }
                    }
                }
            } else {

                if (marianito.intersects(Lista_Bloques.get(i).x - avance_x, Lista_Bloques.get(i).y - 10,
                        Lista_Bloques.get(i).ancho, Lista_Bloques.get(i).largo + 10)) {

                    if (M.saltando) {
                        if (448 + avance_y - 60 == Lista_Bloques.get(i).y + 60) {
                            M.saltando = false;
                            M.cayendo = true;
                            if (Lista_Bloques.get(i).tipo.equals("Moneda")) {
                                puntaje++;
                                url = Ventana.class.getResource("moneda.wav");
                                musica = Applet.newAudioClip(url);
                                musica.play();
                                Lista_Bloques.get(i).tipo = "Ladrillo_plano";
                                Lista_Bloques.get(i).img_fondo = "Ladrillo_plano.png";
                                url = Ventana.class.getResource(Lista_Bloques.get(i).img_fondo);
                                Lista_Bloques.get(i).icon = new ImageIcon(url).getImage();
                            }

                            if (Lista_Bloques.get(i).tipo.equals("Ladrillo")) {
                                if (Lista_Bloques.get(i).poder) {
                                    // Lista_Poderes.add(new Poderes(Lista_Bloques.get(i).x, Lista_Bloques.get(i).y - 50));
                                    Lista_Bloques.get(i).tipo = "Ladrillo_plano";
                                    Lista_Bloques.get(i).img_fondo = "Ladrillo_plano.png";
                                    url = Ventana.class.getResource(Lista_Bloques.get(i).img_fondo);
                                    Lista_Bloques.get(i).icon = new ImageIcon(url).getImage();
                                } else {
                                    url = Ventana.class.getResource("ladrillo.wav");
                                    musica = Applet.newAudioClip(url);
                                    musica.play();

                                    Lista_Bloques.remove(i);
                                }

                            }
                            choca[0] = true;
                            break;
                        }
                        if (!choca[2]) {
                            choca[2] = true;
                            break;
                        }
                        if (!choca[3]) {
                            choca[3] = true;
                            break;
                        }
                    }

                    if (M.cayendo) {

                        if (448 + avance_y == Lista_Bloques.get(i).y) {
                            M.cayendo = false;
                            M.encima = true;
                            altura_salto = 0;
                        }
                        break;
                    }
                    if (!choca[3] && !M.encima) {
                        choca[3] = true;
                        break;
                    }
                    if (!choca[2] && !M.encima) {
                        choca[2] = true;
                        break;
                    }
                }
            }
        }
    }

    public void movimiento(String direccion) {

        marianito = new Polygon();
        switch (direccion) {
            case "Derecha":
                choca[3] = false;
                sec++;
                if (!choca[2]) {
                    M.izquierda = false;
                    M.derecha = true;
                    M.mov_derecha();
                    avance_x = avance_x + M.velocidad;
                    if (!M.saltando && !M.cayendo) {
                        switch (sec) {
                            case 1:
                            case 2:
                                marianito.xpoints = M.xc1;
                                marianito.ypoints = M.yc1;
                                marianito.npoints = M.xc1.length;
                                M.x_img = marianito.xpoints[1];
                                url = Ventana.class.getResource(M.img_fondo[0]);
                                break;
                            case 3:
                            case 4:
                                marianito.xpoints = M.xc3;
                                marianito.ypoints = M.yc3;
                                marianito.npoints = M.xc3.length;
                                M.x_img = marianito.xpoints[1];
                                url = Ventana.class.getResource(M.img_fondo[4]);
                                break;
                            case 5:
                            case 6:
                                marianito.xpoints = M.xc2;
                                marianito.ypoints = M.yc2;
                                marianito.npoints = M.xc2.length;
                                M.x_img = marianito.xpoints[1];
                                url = Ventana.class.getResource(M.img_fondo[2]);
                                break;
                            case 7:
                            case 8:
                                marianito.xpoints = M.xc4;
                                marianito.ypoints = M.yc4;
                                marianito.npoints = M.xc4.length;
                                M.x_img = marianito.xpoints[1];
                                url = Ventana.class.getResource(M.img_fondo[6]);
                                break;
                        }
                    } else {
                        marianito.xpoints = M.xs;
                        marianito.ypoints = M.ys;
                        marianito.npoints = M.xs.length;
                        url = Ventana.class.getResource(M.img_fondo[10]);
                    }
                } else {
                    marianito.xpoints = M.xp;
                    marianito.ypoints = M.yp;
                    marianito.npoints = M.xp.length;
                    M.x_img = marianito.xpoints[1];
                    url = Ventana.class.getResource(M.img_fondo[8]);
                }
                if (M.encima && !M.saltando) {
                    M.encima = false;
                    M.cayendo = true;
                }

                M.icon = new ImageIcon(url).getImage();
                break;
            case "Izquierda":
                choca[2] = false;
                if (avance_x > 0) {
                    M.izquierda = true;
                    M.derecha = false;
                    sec++;
                    if (!choca[3] || (choca[3] && choca[2])) {
                        M.mov_izquierda();
                        avance_x = avance_x - M.velocidad;
                        if (!M.saltando && !M.cayendo) {
                            switch (sec) {
                                case 1:
                                case 2:
                                    marianito.xpoints = M.xc1_i;
                                    marianito.ypoints = M.yc1_i;
                                    marianito.npoints = M.xc1_i.length;
                                    url = Ventana.class.getResource(M.img_fondo[1]);
                                    break;
                                case 3:
                                case 4:
                                    marianito.xpoints = M.xc3_i;
                                    marianito.ypoints = M.yc3_i;
                                    marianito.npoints = M.xc3_i.length;
                                    url = Ventana.class.getResource(M.img_fondo[5]);
                                    break;
                                case 5:
                                case 6:
                                    marianito.xpoints = M.xc2_i;
                                    marianito.ypoints = M.yc2_i;
                                    marianito.npoints = M.xc2_i.length;
                                    url = Ventana.class.getResource(M.img_fondo[3]);
                                    break;
                                case 7:
                                case 8:
                                    marianito.xpoints = M.xc4_i;
                                    marianito.ypoints = M.yc4_i;
                                    marianito.npoints = M.xc4_i.length;
                                    url = Ventana.class.getResource(M.img_fondo[7]);
                                    break;
                            }
                        } else {
                            marianito.xpoints = M.xs_i;
                            marianito.ypoints = M.ys_i;
                            marianito.npoints = M.xs_i.length;
                            url = Ventana.class.getResource(M.img_fondo[11]);
                        }
                    } else {
                        marianito.xpoints = M.xp_i;
                        marianito.ypoints = M.yp_i;
                        marianito.npoints = M.xp_i.length;
                        url = Ventana.class.getResource(M.img_fondo[8]);
                    }
                    if (M.encima && !M.saltando) {
                        M.encima = false;
                        M.cayendo = true;
                    }
                    M.x_img = 0 + avance_x;
                    M.icon = new ImageIcon(url).getImage();
                }
                break;
            case "Parado":
                sec = 0;
                if (!M.saltando && !M.cayendo) {
                    marianito.xpoints = M.xp;
                    marianito.ypoints = M.yp;
                    marianito.npoints = M.xp.length;
                    url = Ventana.class.getResource(M.img_fondo[8]);
                    M.icon = new ImageIcon(url).getImage();
                    M.x_img = marianito.xpoints[0];
                }
                break;

            case "Parado2":
                sec = 0;
                if (!M.saltando && !M.cayendo) {
                    marianito.xpoints = M.xp_i;
                    marianito.ypoints = M.yp_i;
                    marianito.npoints = M.xp_i.length;
                    url = Ventana.class.getResource(M.img_fondo[9]);
                    M.icon = new ImageIcon(url).getImage();
                }
                break;
            case "Arriba":
                if (!M.cayendo) {
                    M.saltando = true;
                    if (M.derecha) {
                        marianito.xpoints = M.xs;
                        marianito.ypoints = M.ys;
                        marianito.npoints = M.xs.length;
                        url = Ventana.class.getResource(M.img_fondo[10]);
                    }
                    if (M.izquierda) {
                        marianito.xpoints = M.xs_i;
                        marianito.ypoints = M.ys_i;
                        marianito.npoints = M.xs_i.length;
                        url = Ventana.class.getResource(M.img_fondo[11]);
                    }
                    M.icon = new ImageIcon(url).getImage();
                    avance_y = avance_y - M.vel_salto;
                    altura_salto -= M.vel_salto;
                    M.salto();
                    if (altura_salto == -200) {
                        choca[2] = false;
                        choca[3] = false;
                        M.saltando = false;
                        M.cayendo = true;
                    }

                }
                break;
            case "Abajo":
                if (M.derecha) {
                    marianito.xpoints = M.xs;
                    marianito.ypoints = M.ys;
                    marianito.npoints = M.xs.length;
                    url = Ventana.class.getResource(M.img_fondo[10]);
                }
                if (M.izquierda) {
                    marianito.xpoints = M.xs_i;
                    marianito.ypoints = M.ys_i;
                    marianito.npoints = M.xs_i.length;
                    url = Ventana.class.getResource(M.img_fondo[11]);
                }
                M.icon = new ImageIcon(url).getImage();
                avance_y = avance_y + M.vel_salto;
                altura_salto += M.vel_salto;
                M.caida();
                if (avance_y == 0) {
                    altura_salto = 0;
                    M.cayendo = false;
                    choca[2] = false;
                    choca[3] = false;
                    movimiento("Parado");
                }

                break;
        }
        if (sec == 8) {
            sec = 0;
        }
        colision("");
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

        // Calcula la posición del fondo en relación con la posición de Mario
        // System.out.println(icons[0].getWidth(null));
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
            g.drawRect(Lista_Bloques.get(i).x - avance_x, Lista_Bloques.get(i).y, Lista_Bloques.get(i).ancho,
                    Lista_Bloques.get(i).largo);
            if (Lista_Bloques.get(i).tipo.equals("Tuberia")) {
                g.drawRect(Lista_Bloques.get(i).x2 - avance_x, Lista_Bloques.get(i).y2, Lista_Bloques.get(i).ancho2,
                        Lista_Bloques.get(i).largo2);
            }
            if (Lista_Bloques.get(i).tipo.equals("Moneda")) {
                if (sec2 <= 7) {
                    Lista_Bloques.get(i).img_fondo = "Moneda.png";
                    url = Ventana.class.getResource(Lista_Bloques.get(i).img_fondo);
                    Lista_Bloques.get(i).icon = new ImageIcon(url).getImage();
                } else {
                    Lista_Bloques.get(i).img_fondo = "Moneda2.png";
                    url = Ventana.class.getResource(Lista_Bloques.get(i).img_fondo);
                    Lista_Bloques.get(i).icon = new ImageIcon(url).getImage();
                }
            }
            g.drawImage(Lista_Bloques.get(i).icon, Lista_Bloques.get(i).x - avance_x, Lista_Bloques.get(i).y, null);
        }
        // for (int i = 0; i < Lista_Poderes.size(); i++) {
        //     url = Ventana.class.getResource(Lista_Poderes.get(i).img_fondo);
        //     Lista_Poderes.get(i).icon = new ImageIcon(url).getImage();
        //     g.drawImage(Lista_Poderes.get(i).icon, Lista_Poderes.get(i).x - avance_x, Lista_Poderes.get(i).y, null);

        //     Rectangle re = new Rectangle(Lista_Poderes.get(i).x - avance_x, Lista_Poderes.get(i).y,
        //             Lista_Poderes.get(i).largo, Lista_Poderes.get(i).ancho);
        //     /// mn g.drawRect(re.x, re.y, re.width, re.height);
        //     for (int j = 0; j < Lista_Bloques.size(); j++) {
        //         if (Lista_Bloques.get(j).tipo.equals("Tuberia")) {
        //             if (re.intersects(Lista_Bloques.get(j).x2 - avance_x, Lista_Bloques.get(j).y2,
        //                     Lista_Bloques.get(j).ancho2, Lista_Bloques.get(j).largo2)) {
        //                 if (Lista_Poderes.get(i).derecha) {
        //                     Lista_Poderes.get(i).derecha = false;
        //                     Lista_Poderes.get(i).izquierda = true;
        //                 }
        //             }
        //         }
        //         if (re.intersects(Lista_Bloques.get(j).x - avance_x, Lista_Bloques.get(j).y, Lista_Bloques.get(j).ancho,
        //                 Lista_Bloques.get(j).largo)) {

        //             Lista_Poderes.get(i).choca = true;
        //             if (Lista_Poderes.get(i).cayendo) {
        //                 Lista_Poderes.get(i).derecha = true;
        //                 Lista_Poderes.get(i).cayendo = false;
        //             }
        //         }
        //     }
        //     if (!Lista_Poderes.get(i).choca) {
        //         if (Lista_Poderes.get(i).derecha && Lista_Poderes.get(i).y < 448) {
        //             Lista_Poderes.get(i).derecha = false;
        //             Lista_Poderes.get(i).cayendo = true;
        //             break;
        //         }
        //         if (Lista_Poderes.get(i).y + Lista_Poderes.get(i).largo >= 448) {
        //             if (!Lista_Poderes.get(i).izquierda) {
        //                 Lista_Poderes.get(i).derecha = true;
        //             } else {
        //                 Lista_Poderes.get(i).derecha = false;
        //             }
        //             Lista_Poderes.get(i).cayendo = false;
        //         }

        //     }
        //     Lista_Poderes.get(i).choca = false;
        //     Lista_Poderes.get(i).movimiento();
        //     if (marianito.intersects(re)) {
        //         if (Lista_Poderes.get(i).img_fondo.equals("hongo.png")) {
        //             vidas++;
        //             url = Ventana.class.getResource("vida.wav");
        //             musica = Applet.newAudioClip(url);
        //             musica.play();
        //             Lista_Poderes.remove(i);
        //         }
        //     }
        // }

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
                    Lista_Enemigos.get(i).img_fondo = Lista_Enemigos.get(i).tipo + (d + ".png");
                    url = Ventana.class.getResource(Lista_Enemigos.get(i).img_fondo);
                    Lista_Enemigos.get(i).icon = new ImageIcon(url).getImage();
                } else {
                    Lista_Enemigos.get(i).img_fondo = Lista_Enemigos.get(i).tipo + (d + 1) + ".png";
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
                        termina = true;
                    } else {
                        Lista_Enemigos.get(i).muerto = true;

                    }
                }
            } else {
                Lista_Enemigos.get(i).img_fondo = Lista_Enemigos.get(i).tipo + "_M.png";
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
        g.drawPolygon(marianito);
        if (sec2 == 14) {
            sec2 = 0;
        }

    }

    @SuppressWarnings("removal")
    public static void main(String[] args) throws InterruptedException, Throwable {
        JFrame Ventana_Juego = new JFrame("Mario Bross");
        Ventana_Juego.setSize(1324, 615);
        Ventana_Juego.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Ventana juego = new Ventana();

        Ventana_Juego.add(juego);
        Ventana_Juego.setVisible(true);

        // juego.imagenes_Fondo();
        URL url = Ventana.class.getResource("cancion.wav");
        juego.musica = Applet.newAudioClip(url);
        juego.musica.loop();

        Ventana_Juego.addKeyListener(new KeyListener() {
            String tecla;

            @Override
            public void keyTyped(KeyEvent e) {
                // TODO Auto-generated method stub
            }

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
                tecla = e.getKeyText(e.getKeyCode());
                if (tecla.equals("Derecha") || tecla.equals("Right")) {
                    juego.movimiento("Derecha");
                }
                if (tecla.equals("Izquierda") || tecla.equals("Left")) {
                    juego.movimiento("Izquierda");
                }
                if (tecla.equals("Arriba") || tecla.equals("Up")) {
                    if (juego.altura_salto == 0) {
                        juego.url = Ventana.class.getResource("salto.wav");
                        juego.musica = Applet.newAudioClip(juego.url);
                        juego.musica.play();
                        juego.movimiento("Arriba");
                    }

                }
            }
        });

        while (true) {
            if (!juego.termina) {
                juego.repaint();
                Thread.sleep(47);
            } else {
                Object[] opciones = { "Si", "No" };
                int n = 0;
                n = JOptionPane.showOptionDialog(null, "¿Quieres volver a jugar?", "Ping Pong",
                        JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[1]);
                if (n == 0) {

                    juego.reset();
                    juego.imagenes_Fondo();
                } else {
                    juego.musica.stop();
                    Ventana_Juego.dispose();
                    break;
                }
            }
        }
    }
}
