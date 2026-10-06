import processing.core.PApplet;

public class Player {
    private static final float AIM_LINE_LENGTH = 50;

    private float x, y;
    private float speed = 4;
    private float size = 30;

    private boolean movingUp, movingDown, movingLeft, movingRight;

    public Player(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void move(PApplet window) {
        float dx = 0;
        float dy = 0;

        if (movingUp) dy -= 1;
        if (movingDown) dy += 1;
        if (movingLeft) dx -= 1;
        if (movingRight) dx += 1;

        // normalize so moving diagonally isn't faster than moving straight
        float length = PApplet.sqrt(dx * dx + dy * dy);
        if (length > 0) {
            x += dx / length * speed;
            y += dy / length * speed;
        }

        // keep the player on the screen
        x = PApplet.constrain(x, size / 2, window.width - size / 2);
        y = PApplet.constrain(y, size / 2, window.height - size / 2);
    }

    public Bullet shootToward(float targetX, float targetY) {
        return new Bullet(x, y, targetX, targetY);
    }

    public void drawAimLine(PApplet window, float targetX, float targetY) {
        float dx = targetX - x;
        float dy = targetY - y;
        float length = PApplet.sqrt(dx * dx + dy * dy);
        if (length == 0) return;

        float endX = x + dx / length * AIM_LINE_LENGTH;
        float endY = y + dy / length * AIM_LINE_LENGTH;

        window.stroke(255, 255, 0);
        window.strokeWeight(2);
        window.line(x, y, endX, endY);
    }

    public void draw(PApplet window) {
        window.noStroke();
        window.fill(0, 150, 255);
        window.ellipse(x, y, size, size);
    }

    public void setMovingUp(boolean movingUp) {
        this.movingUp = movingUp;
    }

    public void setMovingDown(boolean movingDown) {
        this.movingDown = movingDown;
    }

    public void setMovingLeft(boolean movingLeft) {
        this.movingLeft = movingLeft;
    }

    public void setMovingRight(boolean movingRight) {
        this.movingRight = movingRight;
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
