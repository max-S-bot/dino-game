package io.github.mxz_schwarz.dino_game;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Collection;

abstract class ImagePart {

    private final Image img;
    private final int x0;
    private final int y0;
    final int w;
    final int h;
    private final double mx;
    private final long t0 = t();

    ImagePart(int x, int y, int w, int h, double mx, Image img) {
        this.x0 = x;
        this.y0 = y;
        this.w = w;
        this.h = h;
        this.mx = mx;
        this.img = img;
    }

    int x() {
        return x0 + (int) ((t()-t0) * mx);
    }

    int y() {return y0;}

    void draw (Graphics g, DinoGame game) {
        g.drawImage(img, x(), y(), w, h, game);
    }

    static void manage(Collection<ImagePart> parts) {
        parts.removeIf(p -> p == null || p.x() + p.w < 0);
    }

    private static long t() {return System.currentTimeMillis();}
}