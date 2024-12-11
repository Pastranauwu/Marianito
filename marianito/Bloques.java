import java.awt.Image;
import java.awt.Rectangle;
import java.net.URL;

public class Bloques {
    String tipo;
    int largo;
    int ancho;
    int ancho2;
    int largo2;
    int x;
    int y;
    int x2;
    int y2;
    Image icon;
    String img_fondo;
    boolean poder;

    public Bloques(String tipo, int x, int y, boolean poder) {
        this.tipo = tipo;
        this.x = x;
        this.y = y;
        if (tipo.equals("Tuberia")) {
            largo = 60;
            ancho = 120;
            largo2 = 80;
            ancho2 = 100;
            this.x2 = x + 10;
            this.y2 = y + largo;

        } else {
            largo = 60;
            ancho = 60;
        }
        img_fondo = tipo + ".png";
        this.poder = poder;
    }

}
