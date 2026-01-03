package io.github.mxz_schwarz.dino_game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import static java.lang.System.currentTimeMillis;

class Score {
    private static final Color BACKGROUND = Color.WHITE;
    private static final Color COLOR = Color.BLACK;
    private static final int FONT_SIZE = 20;
    private static final Font FONT = new Font("Times New Roman",Font.BOLD,FONT_SIZE);
    private static final int SCORE_DIV = 250; 

    private int maxScore = 0;
    private final int gameW;
    private final int x;
    private final int y;
    private long startTime = currentTimeMillis();

    Score(int w) {
        this.gameW = w;
        this.x = w - 5*FONT_SIZE;
        this.y = FONT_SIZE;
    }

    void draw(Graphics g, DinoGame game) {
        g.setColor(BACKGROUND);
        g.fillRect(x, 0, gameW-x, 2*y);
        g.setColor(COLOR);
        g.setFont(FONT);
        g.drawString(format(maxScore), x, y);
        g.drawString(format(score()), x, 2*y);
    } 

    int score() {
        return (int) (currentTimeMillis()
        - startTime) / SCORE_DIV;
    }

    void newGame() {
        startTime = currentTimeMillis();
    }

    void endGame() {
        maxScore = Math.max(score(), maxScore);
    }

    private String format(double s) {
        //staying true to 5 digit max score
        return String.format("%05d", (int) s % (int) 1e5);
    }
}