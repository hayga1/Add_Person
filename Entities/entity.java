package Entities;

import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Main.Game;
import utility.LoadSave;
import utility.Vector2;

public abstract class entity {
    protected int height, width;
    protected Rectangle hitbox;
    protected Rectangle hitboxOffset;
    protected int aniTick, aniIndex;
    public int[][] lvlData;

    protected int Life;
    protected int LifeMax;
    protected int LifeMax2;

    public Vector2 position = Vector2.ZERO;
    public Vector2 velocity = Vector2.ZERO;

    public Rectangle getHitbox() {
        return hitbox;
    }

    public entity(Vector2 position, int width, int height) {
        this.position = position;
        this.width = width;
        this.height = height;
        OffsetHitBox(0, 0, 0, 0);
        initHitBox();
    }

    public void loadlvlData(int[][] lvldata) {
        this.lvlData = lvldata;
    }

    protected void drawHitBox(Graphics g) {
        g.setColor(Color.RED);
        g.drawRect(hitbox.x, hitbox.y, (int) (width * Game.SCALE), (int) (height * Game.SCALE));
    }

    protected void drawHitBox(Graphics g, int speedX, int speedY) {
        g.setColor(Color.RED);
        g.drawRect(speedX, speedY, (int) (width * Game.SCALE), (int) (height * Game.SCALE));
    }

    protected void OffsetHitBox(int x, int y, int w, int h) {
        hitboxOffset = new Rectangle(x, y, w, h);
    }

    protected void initHitBox() {
        hitbox = new Rectangle((int) position.X - hitboxOffset.x, (int) position.Y - hitboxOffset.y,
                width - hitboxOffset.width, height - hitboxOffset.height);
    }

    protected void updateHitbox() {
        hitbox.x = (int) position.X;
        hitbox.y = (int) position.Y;
    }

    protected BufferedImage[] setLengthofAniSprite(int length) {
        return new BufferedImage[length];
    }

    protected BufferedImage[][] setLengthofAniSprite(int lengthX, int lengthY) {
        return new BufferedImage[lengthX][lengthY];
    }

    protected BufferedImage getSprite(String spriteLocation) {
        return LoadSave.GetSpriteAtLas(spriteLocation);
    }

    public BufferedImage[] Animation(BufferedImage[] spriteSheet, BufferedImage img, int x, int y, int w, int h,
            boolean vertical, boolean horizontal) {
        for (int i = 0; i < spriteSheet.length; i++) {
            spriteSheet[i] = img.getSubimage(x + (horizontal ? w * i : 0), y + (vertical ? h * i : 0), w, h);
        }
        return spriteSheet;
    }

    public BufferedImage[][] Animation(BufferedImage[][] spriteSheet, BufferedImage img, int x, int y, int w, int h) {
        for (int j = 0; j < spriteSheet.length; j++) {
            for (int i = 0; i < spriteSheet.length; i++) {
                spriteSheet[j][i] = img.getSubimage(i * x, j * y, w, h);
            }
        }
        return spriteSheet;
    }

    protected int updateAnimation(BufferedImage[] spriteSheet, int AniSpeed) {
        aniTick++;
        if (aniTick >= AniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= spriteSheet.length) {
                aniIndex = 0;
            }
        }
        return aniIndex;
    }

    protected int updateAnimation(BufferedImage[] spriteSheet, int AniSpeed, int Offset, int MaxinumFrame) {
        aniTick++;
        if (aniTick >= AniSpeed) {
            aniTick = Offset;
            aniIndex++;
            if (aniIndex >= MaxinumFrame) {
                aniIndex = 0;
            }
        }
        return aniIndex;
    }
}
