package src;
import java.awt.Image;
import java.net.URL;

public class Marianito {
    int[] xp = { 15, 15, 20, 20, 25, 25, 15, 15, 20, 20, 25, 25, 30, 30, 20, 20, 25, 25, 30, 30, 35, 35, 55, 55, 70, 70,
            60, 60, 70, 70, 75, 75, 70, 70, 65, 65, 55, 55, 70, 70, 75, 75, 65, 65, 70, 70, 75, 75, 55, 55, 50, 50, 40,
            40, 35, 35 };

    int[] yp = { 448, 443, 443, 438, 438, 433, 433, 413, 413, 408, 408, 403, 403, 398, 398, 383, 383, 378, 378, 373,
            373, 368, 368, 373, 373, 378, 378, 383, 383, 388, 388, 393, 393, 398, 398, 403, 403, 408, 408, 413, 413,
            433,
            433, 438, 438, 443, 443, 448, 448, 438, 438, 433, 433, 438, 438, 448 };

    int[] xc1 = { 0, 0, 10, 10, 25, 25, 15, 15, 20, 20, 25, 25, 50, 50, 65, 65, 55, 55, 65, 65, 70, 70, 65, 65,
            60, 60, 50, 50, 75, 75, 70, 70, 45, 45, 30, 30, 20, 20, 25, 25, 10, 10, 5, 5, 10, 10, 15, 15, 20, 20, 15,
            15, 10, 10 };

    int[] yc1 = { 423, 408, 408, 403, 403, 398, 398, 383, 383, 373, 373, 368, 368, 373, 373, 378, 378, 383, 383, 388,
            388, 393, 393, 398, 398, 403, 403, 408, 408, 418, 418, 438, 438, 433, 433, 438, 438, 443, 443, 448, 448,
            443, 443, 433, 433, 428, 428, 423, 423, 413, 413, 418, 418, 423 };

    int[] xc2 = { 20, 20, 25, 25, 30, 30, 20, 20, 25, 25, 30, 30, 55, 55, 70, 70, 60, 60, 70, 70, 75, 75, 70, 70, 65,
            65, 55, 55, 60, 60, 65, 65, 60, 60, 65, 65, 50, 50, 30, 30, 25, 25 };

    int[] yc2 = { 428, 408, 408, 403, 403, 398, 398, 383, 383, 373, 373, 368, 368, 373, 373, 378, 378, 383,
            383, 388, 388, 393, 393, 398, 398, 403, 403, 408, 408, 413, 413, 428, 428, 438, 438, 443, 443,
            448, 448, 433, 433, 428 };

    int[] xc3 = { 15, 15, 25, 25, 30, 30, 20, 20, 25, 25, 30, 30, 55, 55, 70, 70, 60, 60, 70, 70, 75, 75, 70, 70, 65,
            65, 50, 50, 55, 55, 65, 65, 60, 60, 65, 65, 45, 45, 40, 40, 35, 35, 20, 20 };

    int[] yc3 = { 448, 428, 428, 408, 408, 398, 398, 383, 383, 373, 373, 368, 368, 373, 373, 378, 378, 383, 383,
            388, 388, 393, 393, 398, 398, 405, 405, 408, 408, 413, 413, 423, 423, 442, 442, 448, 448, 433, 433,
            438, 438, 442, 442, 448 };

    int[] xc4 = { 10, 10, 15, 15, 20, 20, 25, 25, 30, 30, 20, 20, 25, 25, 30, 30, 55, 55, 70, 70, 60, 60, 70, 70, 75,
            75, 70, 70, 65, 65, 70, 70, 65, 65, 60, 60, 55, 55, 50, 50, 55, 55, 35, 35, 15, 15 };

    int[] yc4 = { 438, 428, 428, 413, 413, 408, 408, 403, 403, 398, 398, 383, 383, 373, 373, 368, 368, 373, 373,
            378, 378, 383, 383, 388, 388, 393, 393, 398, 398, 408, 408, 413, 413, 418, 418, 428, 428, 433, 433,
            438, 438, 442, 442, 433, 433, 438 };

    int[] xs = { 0, 0, 10, 10, 25, 25, 15, 15, 20, 20, 25, 25, 50, 50, 60, 60, 75, 75, 70, 70, 65, 65, 60, 60, 75, 75,
            80, 80, 50, 50, 15, 15, 5, 5, 10, 10, 5, 5 };

    int[] ys = { 428, 418, 418, 413, 413, 408, 408, 393, 393, 383, 383, 378, 378, 383, 383, 378, 378, 403, 403,
            413, 413, 418, 418, 423, 423, 418, 418, 438, 438, 443, 443, 448, 448, 438, 438, 433, 433, 428 };

    int xp_i[];
    int yp_i[];
    int xc1_i[];
    int yc1_i[];
    int xc2_i[];
    int yc2_i[];
    int xc3_i[];
    int yc3_i[];
    int xc4_i[];
    int yc4_i[];
    int xs_i[];
    int ys_i[];

    int x_img;
    int max_x;
    int max_y;
    int velocidad = 5;
    boolean encima = false;
    boolean saltando = false;
    boolean derecha = false;
    boolean izquierda = false;
    boolean cayendo = false;
    int vel_salto = 10;
    Image icon;
    String img_fondo[] = {"corre1.png", "corre3.png", "corre2.png", "corre4.png",
    "corre5.png",
    "corre6.png", "corre7.png", "corre8.png", "mario.png", "mario2.png",
    "salta.png", "salta2.png" };
    
    public Marianito() {
        x_img = xp[0];
        xp_i = new int[xp.length];
        yp_i = new int[xp.length];
        xc1_i = new int[xc1.length];
        yc1_i = new int[xc1.length];
        xc2_i = new int[xc2.length];
        yc2_i = new int[xc2.length];
        xc3_i = new int[xc3.length];
        yc3_i = new int[xc3.length];
        xc4_i = new int[xc4.length];
        yc4_i = new int[xc4.length];
        xs_i = new int[xs.length];
        ys_i = new int[xs.length];
        int midpoint = getMidpoint(xp);
        for (int i = 0; i < xp.length; i++) {
            xp_i[i] = xp[i];
            yp_i[i] = yp[i];
        }

        for (int i = 0; i < xc1.length; i++) {
            xc1_i[i] =  xc1[i];
            yc1_i[i] = yc1[i];
        }
        for (int i = 0; i < xc2.length; i++) {
            xc2_i[i] =  xc2[i];
            yc2_i[i] = yc2[i];
        }
        for (int i = 0; i < xc3.length; i++) {
            xc3_i[i] =  xc3[i];
            yc3_i[i] = yc3[i];
        }
        for (int i = 0; i < xc4.length; i++) {
            xc4_i[i] = xc4[i];
            yc4_i[i] = yc4[i];
        }
        for (int i = 0; i < xs.length; i++) {
            xs_i[i] = 80 - xs[i];
            ys_i[i] = ys[i];
        }
    }

    private int getMidpoint(int[] coordinates) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int coord : coordinates) {
            if (coord < min) min = coord;
            if (coord > max) max = coord;
        }
        return (min + max) / 2;
    }

    void setMax(int[] x, int y[]) {
        int max_x = 0;
        int max_y = 0;
        for (int i = 1; i < x.length; i++) {
            if (x[max_x] < x[i]) {
                max_x = x[i];
            }
            if (y[max_y] < y[i]) {
                max_y = y[i];
            }
        }
        this.max_x = max_x;
        this.max_x = max_y;
    }

    public void mov_derecha() {
        for (int i = 0; i < xp.length; i++) {
            xp[i] = xp[i] + velocidad;
            xp_i[i] = xp_i[i] + velocidad;
        }
        for (int i = 0; i < xc1.length; i++) {
            xc1[i] = xc1[i] + velocidad;
            xc1_i[i] = xc1_i[i] + velocidad;
        }
        for (int i = 0; i < xc2.length; i++) {
            xc2[i] = xc2[i] + velocidad;
            xc2_i[i] = xc2_i[i] + velocidad;
        }
        for (int i = 0; i < xc3.length; i++) {
            xc3[i] = xc3[i] + velocidad;
            xc3_i[i] = xc3_i[i] + velocidad;
        }
        for (int i = 0; i < xc4.length; i++) {
            xc4[i] = xc4[i] + velocidad;
            xc4_i[i] = xc4_i[i] + velocidad;
        }
        for (int i = 0; i < xs.length; i++) {
            xs[i] = xs[i] + velocidad;
            xs_i[i] = xs_i[i] + velocidad;
        }
    }

    public void mov_izquierda() {
        for (int i = 0; i < xp.length; i++) {
            xp[i] = xp[i] - velocidad;
            xp_i[i] = xp_i[i] - velocidad;
        }
        for (int i = 0; i < xc1.length; i++) {
            xc1[i] = xc1[i] - velocidad;
            xc1_i[i] = xc1_i[i] - velocidad;
        }
        for (int i = 0; i < xc2.length; i++) {
            xc2[i] = xc2[i] - velocidad;
            xc2_i[i] = xc2_i[i] - velocidad;
        }
        for (int i = 0; i < xc3.length; i++) {
            xc3[i] = xc3[i] - velocidad;
            xc3_i[i] = xc3_i[i] - velocidad;
        }
        for (int i = 0; i < xc4.length; i++) {
            xc4[i] = xc4[i] - velocidad;
            xc4_i[i] = xc4_i[i] - velocidad;
        }
        for (int i = 0; i < xs.length; i++) {
            xs[i] = xs[i] - velocidad;
            xs_i[i] = xs_i[i] - velocidad;
        }
    }

    public void salto() {
        for (int i = 0; i < yp.length; i++) {
            yp[i] = yp[i] - vel_salto;
            yp_i[i] = yp_i[i] - vel_salto;
        }
        for (int i = 0; i < yc1.length; i++) {
            yc1[i] = yc1[i] - vel_salto;
            yc1_i[i] = yc1_i[i] - vel_salto;
        }
        for (int i = 0; i < yc2.length; i++) {
            yc2[i] = yc2[i] - vel_salto;
            yc2_i[i] = yc2_i[i] - vel_salto;
        }
        for (int i = 0; i < yc3.length; i++) {
            yc3[i] = yc3[i] - vel_salto;
            yc3_i[i] = yc3_i[i] - vel_salto;
        }
        for (int i = 0; i < yc4.length; i++) {
            yc4[i] = yc4[i] - vel_salto;
            yc4_i[i] = yc4_i[i] - vel_salto;
        }
        for (int i = 0; i < xs.length; i++) {
            ys[i] = ys[i] - vel_salto;
            ys_i[i] = ys_i[i] - vel_salto;
        }

    }

    public void caida() {
        for (int i = 0; i < yp.length; i++) {
            yp[i] = yp[i] + vel_salto;
            yp_i[i] = yp_i[i] + vel_salto;
        }
        for (int i = 0; i < yc1.length; i++) {
            yc1[i] = yc1[i] + vel_salto;
            yc1_i[i] = yc1_i[i] + vel_salto;
        }
        for (int i = 0; i < yc2.length; i++) {
            yc2[i] = yc2[i] + vel_salto;
            yc2_i[i] = yc2_i[i] + vel_salto;
        }
        for (int i = 0; i < yc3.length; i++) {
            yc3[i] = yc3[i] + vel_salto;
            yc3_i[i] = yc3_i[i] + vel_salto;
        }
        for (int i = 0; i < yc4.length; i++) {
            yc4[i] = yc4[i] + vel_salto;
            yc4_i[i] = yc4_i[i] + vel_salto;
        }
        for (int i = 0; i < xs.length; i++) {
            ys[i] = ys[i] + vel_salto;
            ys_i[i] = ys_i[i] + vel_salto;
        }
    }

}
