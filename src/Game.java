import processing.core.PApplet;

import java.util.ArrayList;

public class Game extends PApplet {
    private static final int NUM_ENEMIES = 10;
    private static final int FRAMES_BETWEEN_SHOTS = 6;   // fire rate while holding the mouse

    private Player player;
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private ArrayList<Bullet> bullets = new ArrayList<>();
    private int shotCooldown = 0;

    public void settings() {
        size(800, 800);
    }

    public void setup() {
        player = new Player(width / 2, height / 2);

        for (int i = 0; i < NUM_ENEMIES; i++) {
            enemies.add(new Enemy(random(20, width - 20), random(20, height - 20)));
        }
    }

    public void draw() {
        background(30);

        handleShooting();
        moveEverything();
        handleCollisions();
        drawEverything();
    }

    private void moveEverything() {
        player.move(this);

        for (int i = 0; i < enemies.size(); i++) {
            Enemy e = enemies.get(i);
            e.move();
        }

        // go backwards so we can remove bullets safely while looping
        for (int i = bullets.size() - 1; i >= 0; i--) {
            Bullet b = bullets.get(i);
            b.move();
            if (b.isOffScreen(this)) {
                bullets.remove(i);
            }
        }
    }

    private void drawEverything() {
        for (int i = 0; i < enemies.size(); i++) {
            Enemy e = enemies.get(i);
            e.draw(this);
        }

        for (int i = 0; i < bullets.size(); i++) {
            Bullet b = bullets.get(i);
            b.draw(this);
        }

        player.drawAimLine(this, mouseX, mouseY);
        player.draw(this);
    }

    // Remove any bullet that is hitting an enemy, along with the enemy it hit.
    private void handleCollisions() {
        // TODO: implement this method
    }

    // Holding the left mouse button fires a stream of bullets.
    private void handleShooting() {
        if (shotCooldown > 0) {
            shotCooldown--;
        }

        if (mousePressed && mouseButton == LEFT && shotCooldown == 0) {
            shoot();
        }
    }

    private void shoot() {
        bullets.add(player.shootToward(mouseX, mouseY));
        shotCooldown = FRAMES_BETWEEN_SHOTS;
    }

    public void mousePressed() {
        // fire immediately on every click, even if the stream cooldown hasn't finished
        if (mouseButton == LEFT) {
            shoot();
        }
    }

    public void keyPressed() {
        setMovementKey(true);
    }

    public void keyReleased() {
        setMovementKey(false);
    }

    // Track each arrow key separately so several can be held at once.
    private void setMovementKey(boolean isDown) {
        if (key != CODED) return;

        if (keyCode == UP) player.setMovingUp(isDown);
        else if (keyCode == DOWN) player.setMovingDown(isDown);
        else if (keyCode == LEFT) player.setMovingLeft(isDown);
        else if (keyCode == RIGHT) player.setMovingRight(isDown);
    }

    public static void main(String[] args) {
        PApplet.main("Game");
    }
}
