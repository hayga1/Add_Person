package utility;

import javax.imageio.ImageIO;

import Main.Game;

import java.io.IOException;
import java.awt.Color;
import java.io.InputStream;
import java.awt.image.BufferedImage;

public class LoadSave {

    public static final String PLAYER_ATLAS = "GhostSlime.png";
    public static final String LEVEL_ATLAS = "rockSprite.png";
    public static final String MISSING_TEXTURE = "MissingTexture.png";
    public static final String MAIN_LEVEL = "MainLevel.png";
    public static final String MENU_ATLAS = "button_atlas.png";
    public static final String MENU_BGS = "menu_background.png";
    public static final String PAUSE_BGS = "pause_menu.png";
    public static final String SOUND_BUTTONS = "sound_button.png";
    public static final String URM_BUTTONS = "urm_buttons.png";
    public static final String VOLUME_BUTTON = "volume_buttons.png";
    public static final String BAT_ATLAS = "Bat.png";
    public static final String CYCLONE_ATLAS = "cyclone.png";

    public static final String CRABBY_ATLAS = "crabby_sprite.png";
    public static final String DEMON_ATLAS = "Demon.png";
    public static final String DEMON2_ATLAS = "Demon2.png";
    public static final String FLYINGBLACKSNAKE_ATLAS = "flyingBlackSnake.png";
    public static final String GHOST_ATLAS = "ghost.png";
    public static final String HELLDOG_ATLAS = "Helldog.png";
    public static final String SNAKE_ATLAS = "Snake.png";
    public static final String WEREWOLF_ATLAS = "werewolf.png";
    public static final String SPIDER_ATLAS = "spider.png";
    public static final String DRAGONMONSTER_ATLAS = "rongquy-removebg-preview.png";
    public static final String HELLDEMON_ATLAS = "hellDemon.png";
    public static final String BOSS_ATLAS = "MainCharacter.png";

    public static BufferedImage GetSpriteAtLas(String fileName) {
        BufferedImage img = null;
        InputStream is = LoadSave.class.getResourceAsStream("/" + fileName);
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
        return img;
    }

    public static Vector2 CenterOfTheWorld() {
        BufferedImage img = GetSpriteAtLas(MAIN_LEVEL);
        return new Vector2((img.getWidth() * Game.TILES_SIZE) / 2,
                (img.getHeight() * Game.TILES_SIZE) / 2);
    }

    public static int[][] GetLevelData() {
        BufferedImage img = GetSpriteAtLas(MAIN_LEVEL);
        int[][] lvlData = new int[img.getHeight()][img.getWidth()];
        for (int j = 0; j < img.getHeight(); j++) {
            for (int i = 0; i < img.getWidth(); i++) {
                Color color = new Color(img.getRGB(i, j));
                int value = color.getRed();
                if (value >= 48) {
                    value = 0;
                }
                lvlData[j][i] = value;
            }
        }
        return lvlData;
    }
}
