package io.github.mxz_schwarz.dino_game;

import java.util.Collection;

class Obstacle extends ImagePart {

    private static final double P = .14;
    private static final int MAX_OBSTS = 2;
    private static final double M_X = -0.12;

    private Obstacle(int w, int groundY, int h, ObstInfo o) {
        super(w, groundY -
        (int) (o.h * h / 13), 
        (int) (o.w * w / 25), 
        (int) (o.h * h / 13), 
        M_X, o.img);
    }

    static Obstacle makeObstOrNull(Obstacle last, int w, int groundY, int h) {
        return last != null && w-last.x() < 
        w/MAX_OBSTS || Math.random() > P ?  null :
        new Obstacle(w, groundY, h, ObstInfo.getRand());
    }

    static void removeAll(Collection<ImagePart> parts) {
        parts.removeIf(p -> p instanceof Obstacle);
    }
}