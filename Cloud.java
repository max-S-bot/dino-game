import java.awt.Image;
import javax.swing.ImageIcon;

public class Cloud extends ImagePart {

    private static final String CLOUD_PATH = "projects/dinosaur_game/imgs/cloud.png";
    private static final Image IMG = new ImageIcon(CLOUD_PATH).getImage();
    private static final double M_X = -0.03;
    private static final double P = .014;
    private static final int W_DIV = 10; 
    private static final int H_DIV = 8;

    private Cloud(int w, int h) {
        super(w, randY(h/3 - h/H_DIV),
        w/W_DIV, h/H_DIV, M_X, IMG);
    }

    private static int randY(int yRange) {
        return (int) (Math.random()*yRange);
    }

    public static Cloud makeCloudOrNull(int w, int h) {
        return Math.random() > P ? null : new Cloud(w, h);
    }
}