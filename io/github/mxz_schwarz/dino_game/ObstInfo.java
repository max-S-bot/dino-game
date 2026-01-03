package io.github.mxz_schwarz.dino_game;

enum ObstInfo {
    CACTUS("cactus", 1, 1.9),
    CACTI("cacti", 1.7, 1.7),
    PTER("pter", 1, 1);

    private static final ObstInfo[] OBSTS = ObstInfo.values();
    private static final int NUM_OBSTS = OBSTS.length;

    final java.awt.Image img;
    final double w;
    final double h;

    private ObstInfo (String img, double w, double h)  {
        try {
            var rsrc = ObstInfo.class.getResource("imgs/"+img+".png");
            this.img = javax.imageio.ImageIO.read(rsrc);
        } catch (java.io.IOException ioe) {
            throw new RuntimeException(ioe);
        }
        this.w = w;
        this.h = h;
    }

    static ObstInfo getRand() {
        final int randInd = (int) (Math.random()*NUM_OBSTS);
        return OBSTS[randInd];
    }
}