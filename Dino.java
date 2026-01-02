import java.awt.Image;
import javax.swing.ImageIcon;

public class Dino {

    private static final String DINO_PATH = "projects/dinosaur_game/imgs/dino";
    private static final Image IMG = new ImageIcon(DINO_PATH + ".png").getImage();
    private static final int JUMP_TIME = 2000;
    private static final int LEEWAY = 8;
    private static final Image NO_L_IMG = 
    new ImageIcon(DINO_PATH + "NoL" + ".png").getImage();
    private static final Image NO_R_IMG = 
    new ImageIcon(DINO_PATH + "NoR" + ".png").getImage();

    private final int x;
    private final int y0;
    private final int w;
    private final int h;
    private final double a;
    private final double b;
    private long jumpStart = 0;

    public Dino(int gameW, int groundY, int gameH) {
        w = gameW / 13;
        h = gameH / 6;
        x = 2 * w;
        y0 = groundY - h;
        a = - gameH / sqr(JUMP_TIME);
        b = gameH / (double) JUMP_TIME; 
        //maxH = h/3;
    }

    public boolean colliding(ImagePart o) {
        return o.x() + o.w 
            > x + LEEWAY 
            && x + w 
            > o.x() + LEEWAY 
            && y() + h 
            > o.y() + LEEWAY;
    }

    public void startJumping() {
        if (jumping()) return;
        jumpStart = t();
    }

    private boolean jumping() {
        return jumpStart != 0 && 
        t() - jumpStart < JUMP_TIME;
    }

    public int y() {
        long tDif = t() - jumpStart;
        return y0 - (int) Math.max(a * sqr(tDif) + b * tDif, 0);
    }

    public void draw (java.awt.Graphics g, DinoGame game) {
        Image img = jumping() ? IMG : 
        (byte) t() < 0 ? NO_L_IMG : NO_R_IMG;
        g.drawImage(img, x, y(), w, h, game);
    }

    static double sqr(long x) {return Math.pow(x, 2);}

    static long t() {return System.currentTimeMillis();}
}