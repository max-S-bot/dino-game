package io.github.mxz_schwarz.dino_game;

import java.awt.Image;

class Dino {

    private static final Image IMG;
    private static final Image NO_L_IMG;
    private static final Image NO_R_IMG;
    static {
        try {
            var rsrc = Dino.class.getResource("imgs/dino.png");
            var rsrcNL = Dino.class.getResource("imgs/dinoNoL.png");
            var rsrcNR = Dino.class.getResource("imgs/dinoNoR.png");
            IMG = javax.imageio.ImageIO.read(rsrc);
            NO_L_IMG = javax.imageio.ImageIO.read(rsrcNL);
            NO_R_IMG = javax.imageio.ImageIO.read(rsrcNR);
        } catch (java.io.IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    private static final int JUMP_TIME = 2000;
    private static final int LEEWAY = 8;

    private final int x;
    private final int y0;
    private final int w;
    private final int h;
    private final double a;
    private final double b;
    private long jumpStart = 0;

    Dino(int gameW, int groundY, int gameH) {
        w = gameW / 13;
        h = gameH / 6;
        x = 2 * w;
        y0 = groundY - h;
        a = - gameH / sqr(JUMP_TIME);
        b = gameH / (double) JUMP_TIME; 
        //maxH = h/3;
    }

    boolean colliding(ImagePart o) {
        return o.x() + o.w 
            > x + LEEWAY 
            && x + w 
            > o.x() + LEEWAY 
            && y() + h 
            > o.y() + LEEWAY;
    }

    void startJumping() {
        if (jumping()) return;
        jumpStart = t();
    }

    private boolean jumping() {
        return jumpStart != 0 && 
        t() - jumpStart < JUMP_TIME;
    }

    int y() {
        long tDif = t() - jumpStart;
        return y0 - (int) Math.max(a * sqr(tDif) + b * tDif, 0);
    }

    void draw (java.awt.Graphics g, DinoGame game) {
        Image img = jumping() ? IMG : 
        (byte) t() < 0 ? NO_L_IMG : NO_R_IMG;
        g.drawImage(img, x, y(), w, h, game);
    }

    private static double sqr(long x) {return Math.pow(x, 2);}

    private static long t() {return System.currentTimeMillis();}
}