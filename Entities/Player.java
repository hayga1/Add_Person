package Entities;

import static utility.HelpMethods.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Main.Game;
import utility.LoadSave;
import utility.Vector2;

public class Player extends entity {

    int index;
    public int width = (int) (30 * Game.SCALE);
    public int height = (int) (44 * Game.SCALE);
    private BufferedImage img;
    private BufferedImage[] imgs;

    public Player(Vector2 position, int width, int height) {
        super(position, width, height);
        img = getSprite(LoadSave.PLAYER_ATLAS);
        imgs = setLengthofAniSprite(8);
        imgs = Animation(imgs, img, 0, 0, width, height, true, false);
    }

    public void update() {
        updatePos();
        updateHitbox();
        index = updateAnimation(imgs, 20);
    }

    public int speedWithOffsetX;
    public int speedWithOffsetY;

    public void render(Graphics g, int lvlOffsetX, int lvlOffsetY) {
        speedWithOffsetX = (int) position.X - lvlOffsetX;
        speedWithOffsetY = (int) position.Y - lvlOffsetY;
        g.drawImage(imgs[index], speedWithOffsetX, speedWithOffsetY, (int) (30 * Game.SCALE),
                (int) (44 * Game.SCALE), null);
        drawHitBox(g, speedWithOffsetX, speedWithOffsetY);
    }

    boolean UP, DOWN, LEFT, RIGHT;
    public float speed = 0.75f * Game.SCALE;

    public boolean isUP() {
        return UP;
    }

    public void setUP(boolean uP) {
        UP = uP;
    }

    public boolean isDOWN() {
        return DOWN;
    }

    public void setDOWN(boolean dOWN) {
        DOWN = dOWN;
    }

    public boolean isLEFT() {
        return LEFT;
    }

    public void setLEFT(boolean lEFT) {
        LEFT = lEFT;
    }

    public boolean isRIGHT() {
        return RIGHT;
    }

    public void setRIGHT(boolean rIGHT) {
        RIGHT = rIGHT;
    }

    private void updatePos() {
        if (!LEFT && !RIGHT && !UP && !DOWN) {
            return;
        }
        velocity.X = 0;
        velocity.Y = 0;
        if (LEFT) {
            velocity.X = -speed;
        }
        if (RIGHT) {
            velocity.X = speed;
        }
        if (UP) {
            velocity.Y = -speed;
        }
        if (DOWN) {
            velocity.Y = speed;
        }
        if (CanMoveHere(position.X + velocity.X, position.Y + velocity.Y, width, height, lvlData)) {
            position = Vector2.Additive(velocity, position);
        }
    }

    public void resetMove() {
        UP = false;
        DOWN = false;
        LEFT = false;
        RIGHT = false;
    }
}
