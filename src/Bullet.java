import processing.core.PApplet;

public class Bullet {
    private float x, y;
    private float xSpeed, ySpeed;
    private float speed = 10;
    private float size = 8;

    // Starts at (x, y) and travels toward (targetX, targetY).
    public Bullet(float x, float y, float targetX, float targetY) {
        this.x = x;
        this.y = y;

        float dx = targetX - x;
        float dy = targetY - y;
        float length = PApplet.sqrt(dx * dx + dy * dy);

        if (length == 0) {          // mouse is right on the player: just shoot up
            dx = 0;
            dy = -1;
            length = 1;
        }

        xSpeed = dx / length * speed;
        ySpeed = dy / length * speed;
    }

    public void move() {
        x += xSpeed;
        y += ySpeed;
    }

    public boolean isOffScreen(PApplet window) {
        return x < -size || x > window.width + size || y < -size || y > window.height + size;
    }

    // Circle-circle collision: hitting if the distance between centers
    // is less than the sum of the two radii (size is a diameter).
    public boolean isHitting(Enemy e) {
        float dist = PApplet.dist(x, y, e.getX(), e.getY());
        return dist < size / 2 + e.getSize() / 2;
    }

    public void draw(PApplet window) {
        window.noStroke();
        window.fill(255);
        window.ellipse(x, y, size, size);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getSize() {
        return size;
    }
}
