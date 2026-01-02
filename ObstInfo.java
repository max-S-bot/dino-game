import java.awt.Image;
import javax.swing.ImageIcon;

public enum ObstInfo {
    CACTUS("cactus", 1, 1.9),
    CACTI("cacti", 1.7, 1.7),
    PTER("pter", 1, 1);

    private static final String PATH_START = "projects/dinosaur_game/imgs/";
    private static final ObstInfo[] OBSTS = ObstInfo.values();
    private static final int NUM_OBSTS = OBSTS.length;

    public final Image img;
    public final double w;
    public final double h;

    private ObstInfo (String img, double w, double h) {
        this.img = new ImageIcon(PATH_START+img+".png").getImage();
        this.w = w;
        this.h = h;
    }

    public static ObstInfo getRand() {
        final int randInd = (int) (Math.random()*NUM_OBSTS);
        return OBSTS[randInd];
    }
}