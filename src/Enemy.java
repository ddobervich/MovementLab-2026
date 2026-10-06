import processing.core.PApplet;

public class Enemy {
    private float x, y;
    private float size = 30;

    public Enemy(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void move() {
        // enemies don't move yet
    }

    public void draw(PApplet window) {
        window.noStroke();
        window.fill(220, 50, 50);
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
