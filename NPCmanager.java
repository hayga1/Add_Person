package Levels;

import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Random;
import java.util.random.*;

import Entities.*;
import Entities.NPC;
import Main.Game;
import gameState.PLaying;
import utility.LoadSave;
import utility.NPCID;
import utility.Vector2;

import static utility.Constants.NPC.*;

public class NPCmanager {
    private PLaying playing;
    public Random rand;

    private final int MaxNPC = 300;
    private NPC[] npc = new NPC[MaxNPC];
    public int frequency = 9000;
    Vector2 getCenterofWorld;

    public ArrayList<Integer> pool = new ArrayList<Integer>();

    public NPCmanager(PLaying playing) {
        this.playing = playing;
        pool.add(NPCID.CRABBY);
        pool.add(NPCID.BAT);
        pool.add(NPCID.CYCLONE);
        pool.add(NPCID.DEMON);
        pool.add(NPCID.DEMON2);
        pool.add(NPCID.FLYINGBLACKSNAKE);
        pool.add(NPCID.HELLDOG);
        pool.add(NPCID.SNAKE);
        pool.add(NPCID.WEREWOLF);
        pool.add(NPCID.SPIDER);
        pool.add(NPCID.DRAGONMONSTER);
        pool.add(NPCID.HELLDEMON);
        pool.add(NPCID.BOSS);
        getCenterofWorld = LoadSave.CenterOfTheWorld();
    }

    /**
     * Spawn in a npc into the game world using {@code spawnNPC}
     * <p>
     * this code is run on every frame, only stop when hit max NPC
     * <p>
     * {@code int frequency} the higher the frequency the lower chance for NPC to,
     * by default it is 1000
     * spawn
     * {@code ArrayList<NPC> pool} pool of npc that is alloweed to spawn, by default
     * it contain all monster
     */
    protected void spawnNPC(int frequency, ArrayList<Integer> pool) {
        for (int i = 0; i < npc.length; i++) {
            rand = new Random();
            if (npc[i] == null) {
                if (rand.nextInt(frequency) == 1) {
                    Vector2 randPos = Vector2.NextVector2CircularEdge(600f * Game.SCALE,
                            600f * Game.SCALE);
                            int l = rand.nextInt(pool.size());
                            switch (pool.get(l)) {
                                case NPCID.CRABBY:
                                    npc[i] = new Crabby(Vector2.Additive(randPos, playing.getPlayer().position));
                                    npc[i].active = true;
                                    break;
                                case NPCID.BAT:
                                    npc[i] = new Bat(Vector2.Additive(randPos, playing.getPlayer().position));
                                    npc[i].active = true;
                                    break;
                                case NPCID.CYCLONE:
                                    npc[i] = new Cyclone(Vector2.Additive(randPos, playing.getPlayer().position));
                                    npc[i].active = true;
                                    break;
                                case NPCID.DEMON:
                                    npc[i] = new Demon(Vector2.Additive(randPos, playing.getPlayer().position));
                                    npc[i].active = true;
                                    break;
                                case NPCID.DEMON2:
                                    npc[i] = new Demon2(Vector2.Additive(randPos, playing.getPlayer().position));
                                    npc[i].active = true;
                                    break;
                                case NPCID.FLYINGBLACKSNAKE:
                                    npc[i] = new flyingBlackSnake(Vector2.Additive(randPos, playing.getPlayer().position));
                                    npc[i].active = true;
                                    break;
                                case NPCID.HELLDOG:
                                    npc[i] = new hellDog(Vector2.Additive(randPos, playing.getPlayer().position));
                                    npc[i].active = true;
                                    break;
                                case NPCID.SNAKE:
                                    npc[i] = new Snake(Vector2.Additive(randPos, playing.getPlayer().position));
                                    npc[i].active = true;
                                    break;
                                case NPCID.WEREWOLF:
                                    npc[i] = new wereWolf(Vector2.Additive(randPos, playing.getPlayer().position));
                                    npc[i].active = true;
                                    break;
                                case NPCID.SPIDER:
                                    npc[i] = new spider(Vector2.Additive(randPos, playing.getPlayer().position));
                                    npc[i].active = true;
                                    break;
                                case NPCID.DRAGONMONSTER:
                                    npc[i] = new dragonMonster(Vector2.Additive(randPos, playing.getPlayer().position));
                                    npc[i].active = true;
                                    break;
                                case NPCID.HELLDEMON:
                                    npc[i] = new hellDemon(Vector2.Additive(randPos, playing.getPlayer().position));
                                    npc[i].active = true;
                                    break;
                                case NPCID.BOSS:
                                    npc[i] = new Boss(Vector2.Additive(randPos, playing.getPlayer().position));
                                    npc[i].active = true;
                                    break;
                            }
                        }
                    }
                }
            }

    int uniqueID = 0;

    public void update() {
        spawnNPC(frequency, pool);
        for (int i = 0; i < npc.length; i++) {
            if (npc[i] != null) {
                npc[i].player = playing.getPlayer();
                if (uniqueID >= npc.length) {
                    uniqueID = 0;
                }
                if (npc[i].uniqueID == 0) {
                    npc[i].uniqueID = uniqueID;
                    uniqueID++;
                }
                npc[i].update();
            }
        }
    }

    public void draw(Graphics g) {
        for (int i = 0; i < npc.length; i++) {
            if (npc[i] != null) {
                npc[i].draw(g, playing.lvlOffset);
            }
        }
    }
}
