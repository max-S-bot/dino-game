package io.github.mxz_schwarz.dino_game;

import java.awt.Graphics;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JFrame;
import javax.swing.Timer;

/*
 * I'm not too crazy about how 
 * much this manages parts.
 */
class DinoGame extends javax.swing.JPanel {

    static void main(String[] args) {
        JFrame frame = new JFrame("Dinosaur Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 450);
        frame.add(new DinoGame(frame.getSize()));
        frame.setVisible(true);
    }

    private static final int DELAY = 100;
    private static final double GRND_H_MUL = 1.0/4;
    private static final double GRND_Y_MUL = 3.0/4;

    private final int w;
    private final int h;
    private boolean inProgress = false;
    private Obstacle lastObst = null;

    private final Set<ImagePart> parts = new HashSet<>();
    private final Score score;
    private final Dino dino;
    private final Timer timer = new Timer(DELAY, e -> repaint());

    DinoGame(java.awt.Dimension d) {
        w = d.width;
        h = d.height;
        score = new Score(w);
        dino = new Dino(w, (int) (h*GRND_Y_MUL), h); 
        var game = this;
        addKeyListener(new java.awt.event.KeyAdapter(){
            @Override
            public void keyPressed(java.awt.event.KeyEvent e) {
                final char ch = e.getKeyChar();
                if (ch == ' ' && game.inProgress())
                    dino.startJumping();
                if (ch == 'n') game.newGame();
            }
        });
        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGround(g);
        ImagePart.manage(parts);
        for (var p : parts) {
            if (p instanceof Obstacle && 
            dino.colliding(p)) inProgress = false;
            p.draw(g, this);
        }
        dino.draw(g, this);
        score.draw(g, this);
        manage();
    }

    private void manage() {
        parts.add(Cloud.makeCloudOrNull(w, h));
        var o = Obstacle.makeObstOrNull
        (lastObst, w, (int) (h*GRND_Y_MUL), h);
        if (o != null) parts.add(lastObst = o);
        if (!inProgress) endGame();
    }

    private void endGame() {
        Obstacle.removeAll(parts);
        lastObst = null;
        timer.stop();
        score.endGame();
    }

    void newGame() {
        if (inProgress) endGame();
        inProgress = true;
        score.newGame();
        timer.start();
    }

    private void drawGround(Graphics g) {
        g.setColor(java.awt.Color.GRAY);
        g.fillRect(0, (int) (h*GRND_Y_MUL), w, (int) (h*GRND_H_MUL));
    }

    boolean inProgress() {
        return inProgress;
    }
}