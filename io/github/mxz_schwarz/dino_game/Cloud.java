package io.github.mxz_schwarz.dino_game;

// who needs imports when you have fully qualified names?

public class Cloud extends ImagePart {

    private static final java.awt.Image IMG;
    static {
        try {
            var rsrc = Cloud.class.getResource("imgs/cloud.png");
            IMG = javax.imageio.ImageIO.read(rsrc);
        } catch (java.io.IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }
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