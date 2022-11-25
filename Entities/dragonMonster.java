package Entities;

import static utility.Constants.NPC.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import utility.LoadSave;
import utility.NPCID;

import utility.Vector2;

public class dragonMonster extends NPC {
    BufferedImage img;

    public dragonMonster(Vector2 position) {
        super(position, 50, 50, NPCID.DRAGONMONSTER);
        img = getSprite(LoadSave.DRAGONMONSTER_ATLAS);
        type = NPCID.DRAGONMONSTER;
    }

    @Override
    public void update() {
        updateHitbox();
        Move();
    }

    float speed = 0.5f;

    public void Move() {
        velocity = Vector2.Multiply(Vector2.SafeNormalize(Vector2.Substact(player.position, position)), speed);
        position = Vector2.Additive(velocity, position);
    }

    @Override
    public void draw(Graphics g, Vector2 lvloffset) {
        if (uniqueID == 20) {
            g.drawLine((int) (player.position.X - lvloffset.X), (int) (player.position.Y - lvloffset.Y),
                    (int) (position.X - lvloffset.X), (int) (position.Y - lvloffset.Y));
        }
        g.drawImage(img, (int) (position.X - lvloffset.X),
                (int) (position.Y - lvloffset.Y), 50, 50, null);
    }
}
