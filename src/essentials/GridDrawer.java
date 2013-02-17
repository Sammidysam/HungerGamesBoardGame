package essentials;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import kuusisto.tinysound.TinySound;
import reaping.Reaping;
import arena.RandomTerrainGenerator;
import enemyAI.NameReader;
import enemyAI.PlayerCreator;

public class GridDrawer extends JPanel implements MouseMotionListener, MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1281379206929277999L;
	Random rand = new Random();
	public byte drawWhat;
	private String message = "Waiting";
	private byte reaping = 0;
	private byte nameshow = 0;
	private byte create2 = 0;
	private byte create3 = 0;
	private byte menuprogress = 1;
	private boolean shouldinvert = false;
//	private boolean shouldinvert2 = false;
	public byte gameType = 0;
	public byte gameMode = 0;
	private boolean shouldReset = true;
	private Reaping getDate = new Reaping();
	private OptionsReader options = new OptionsReader();
	private PlayerCreator create = new PlayerCreator();
	private String numberS = "";
	private byte digit = 1;
	private boolean badnumber = false;
	private Color[] shading = new Color[4];
	private RandomTerrainGenerator random = new RandomTerrainGenerator();
	private byte[] biome = new byte[4];
//	continue int > byte or short
	private NameReader name = new NameReader();
	private boolean[] enemyShow = new boolean[23];
	private boolean[] enemyStDis = new boolean[23];
	private boolean[] enemySpDis = new boolean[23];
	private boolean[] enemySmDis = new boolean[23];
	private boolean[] enemyWsDis = new boolean[23];
	private boolean movemade = false;
	private boolean invertleft = false;
	private boolean invertmiddle = false;
	private boolean invertright = false;
	private boolean isStarting = true;
	private String text[] = new String[5];
	private boolean[] canMoveTo = new boolean[64];
	private boolean[] canSee = new boolean[64];
	private boolean[] canShootTo = new boolean[64];
	private boolean[] shouldOrange = new boolean[64];
	private int[] tilex = new int[64];
	private int[] tiley = new int[64];
	private boolean isMoving = false;
	private boolean isAttacking = false;
	private boolean hasAttacked = false;
	private int PlayerLocation = 65;
	private boolean[] discovered = new boolean[64];
	private String[] history = new String[25];
	private boolean PlayerAlive = true;
	private boolean[] EnemyAlive = new boolean[23];
	private int turns = 0;
	private int daysnowater = 0;
	private int[] inventoryI = new int[8];
	private boolean showinventory = false;
	private boolean invertx = false;
	private boolean[] selectinv = new boolean[8];
	private boolean invertuse = false;
	private boolean invertuse2 = false;
	private boolean invertuse3 = false;
	private boolean showCoordinates = false;
	private int[] bottletype = new int[8];
	private boolean isInfected = false;
	private boolean[] isScavenged;
	private int[] whereSupplyD;
	private int[] SupplyDCap;
	private int[][] supplies;
	private int[][] onGround = new int[64][100];
	private int[] itemsOG = new int[64];
	private boolean invertReplay = false;
	private boolean isCold = false;
	private int coldDaysLeft = 7;
	private boolean invertOptions = false;
	private boolean invertHistory = false;
	private boolean showOptions = false;
	private boolean showHistory = false;
	private boolean[] invertoptions = new boolean[9];
	private String[] time = new String[30];
	public boolean enableCheats = false;
	public int damage;
	private int attackingweapon = 0;
	private boolean overItem[] = new boolean[8];
	private boolean hasDiscoveredAll = false;
	private int PlayerHealth = 50;
	private int spearLocation = 8;
	private boolean[] isPoisoned = new boolean[64];
	private boolean PlayerPoisoned = false;
	private int[] poisonLength = new int[64];
	private int[] EnemyHealth = new int[23];
	private int[] EnemyLocation = new int[23];
	private boolean[] drawEnemy = new boolean[23];
	private int[][] EnemyInventory = new int[23][8];
	private boolean isObserver = false;
	private boolean[] runAway = new boolean[23];
	private byte deaths = 0;
	private int[] Enemydaysnowater = new int[23];
	private boolean[] EnemyInfected = new boolean[23];
	private boolean[] EnemyPoisoned = new boolean[23];
	private int[] EnemycoldDaysLeft = new int[23];
	private int[][] enemyBottleType = new int[23][8];
	private boolean[][] enemyDiscovered = new boolean[23][64];
	private boolean isMusic = true;
    private SoundPlayer soundplayer = new SoundPlayer();
    private boolean starting = true;
    private int[] EnemyKills = new int[23];
    private int[] EnemyDamageDealt = new int[23];
    private int[] EnemyDamageTaken = new int[23];
    private boolean overPlayer = false;
    private int playerKills = 0;
    private int playerDamageDealt = 0;
    private int playerDamageTaken = 0;
    private boolean cornucopiaScavenge = false;
    private int percent = 0;
    private boolean isLogging = false;
    private byte dataType = 2;
    public boolean usingNumber = false;
    private String nameInput = "";
    private short GC = 120;
    private static final String version = "Version 1.0.2";
//	0 = nothing, 1 = title screen, 2 = reaping, 3 = character select, 4 = battle board, 5 = name selector, 17 = error
    public GridDrawer(){
    	super();
        addMouseMotionListener(this);
        addMouseListener(this);
    }
    public void paintComponent(Graphics g){
    	if(shouldReset == true){
	    	g.setColor(Color.WHITE);
	    	g.fillRect(0, 0, 725, 700);
    	}
		g.setColor(Color.BLACK);
		if(showCoordinates == true && drawWhat != 3 && drawWhat != 4 && drawWhat != 17)
			g.drawString(message, 0, 10);
    	if(drawWhat == 1){
    		g.drawImage(loadTitle(), 55, 45, null);
    		g.setFont(new Font("Impact", Font.BOLD, 48));
    		g.drawString("                   GAMES BOARD    ", 0, 75);
    		g.drawString("HUNGER", 20, 120);
    		g.drawString("GAME", 530, 120);
    		g.setFont(new Font("Times New Roman", Font.ITALIC, 12));
    		g.drawString(version, 654, 669);
//          Font font3 = new Font("Arial", Font.PLAIN, 17);
//    		if(menuprogress == 0){
//        		g.setFont(font3);
//	    		if(shouldinvert == false)
//	    			g.setColor(Color.BLACK);
//	    		if(shouldinvert == true)
//	    			g.setColor(Color.LIGHT_GRAY);
//	    		g.fillRect(255, 175, 175, 75);
//	    		if(shouldinvert == true)
//	    			g.setColor(Color.BLACK);
//	    		if(shouldinvert == false)
//	    			g.setColor(Color.LIGHT_GRAY);
//	    		g.drawString("Single Player", 283, 220);
//    			if(shouldinvert2 == false)
//    				g.setColor(Color.BLACK);
//    			if(shouldinvert2 == true)
//    				g.setColor(Color.LIGHT_GRAY);
//    			g.fillRect(255, 375, 175, 75);
//    			if(shouldinvert2 == true)
//    				g.setColor(Color.BLACK);
//    			if(shouldinvert2 == false)
//    				g.setColor(Color.LIGHT_GRAY);
//        		g.drawString("Multiplayer", 300, 420);
//        		g.fillRect(14, 505, 175, 75);
//    		}
    		if(menuprogress == 1){
//        		g.setFont(font3);
//	    		if(shouldinvert == false)
//	    			g.setColor(Color.BLACK);
//	    		if(shouldinvert == true)
//	    			g.setColor(Color.LIGHT_GRAY);
//	    		g.fillRect(255, 175, 175, 75);
//	    		if(shouldinvert == true)
//	    			g.setColor(Color.BLACK);
//	    		if(shouldinvert == false)
//	    			g.setColor(Color.LIGHT_GRAY);
//        		g.drawString("Character Select", 283, 220);
//    			if(shouldinvert2 == false)
//    				g.setColor(Color.BLACK);
//    			if(shouldinvert2 == true)
//    				g.setColor(Color.LIGHT_GRAY);
//    			g.fillRect(255, 375, 175, 75);
//    			if(shouldinvert2 == true)
//    				g.setColor(Color.BLACK);
//    			if(shouldinvert2 == false)
//    				g.setColor(Color.LIGHT_GRAY);
//        		g.drawString("Random Player Select", 260, 420);
//        		g.setColor(Color.BLACK);
//        		g.setFont(font4);
//        		g.drawString("Assigns you to a random district and gender!", 230, 462);
//        		g.drawString("Takes you to a screen to select your character and gender!", 200, 262);
        		g.setFont(new Font("Arial", Font.PLAIN, 20));
        		g.drawString("Press C to select your character!", 200, 650);
        		g.drawString("Press R to have your character randomly selected!", 130, 670);
    		}
    	}
    	if(drawWhat == 2){
    		g.setColor(Color.BLACK);
           	Font font2 = new Font("Arial", Font.BOLD, 25);
           	g.setFont(font2);
    		if(reaping == 0){
    			getDate.isDrawn();
    			reaping++;
    		}
    		g.drawString("The Reaping", 250, 25);
    		g.drawImage(loadReaping(), 0, 30, 726, 268, null);
    		g.drawImage(loadReaping2(), 0, 268, 726, 248, null);
    		font2 = new Font("Arial", Font.PLAIN, 23);
    		g.setFont(font2);
    		g.drawString("This year's district " + create.PlayerD + " " + create.PlayerG + " representative of The Hunger Games is...", 30, 546);
    		font2 = new Font("Arial", Font.ITALIC, (int) (96 - create.PlayerN.length() * 1.6));
    		g.setFont(font2);
    		if(nameshow == 0)
    			delaytime();
    		if(nameshow == 1)
    			g.drawString(create.PlayerN, 45, 610);
    		font2 = new Font("Arial", Font.BOLD, 25);
    		g.setFont(font2);
    		getDate.changeYear();
    		g.setColor(Color.BLACK);
    		if(reaping != 0)
    			g.drawString(getDate.getDateTime() + "/" + getDate.changeYear(), 259, 665);
    		if(shouldinvert == false)
    			g.drawImage(loadNext(), 562, 610, null);
    		if(shouldinvert == true)
    			g.drawImage(invertNext(), 562, 610, null);
    	}
    	if(drawWhat == 3){
    		if(showCoordinates == true) 
    			g.drawString(message, 0, 670);
    		g.drawImage(loadSelectScreen(), 0, 0, null);
    	}
    	if(drawWhat == 4){
    		if(showCoordinates == true)
    			g.drawString(message, 0, 670);
    		g.drawImage(loadLogo(), 619, 554, null);
    		if(invertOptions == false)
    			g.setColor(Color.BLACK);
    		if(invertOptions == true)
    			g.setColor(Color.LIGHT_GRAY);
    		g.fillRect(550, 645, 59, 20);
    		g.setFont(new Font("Arial", Font.PLAIN, 12));
    		if(invertOptions == false)
    			g.setColor(Color.LIGHT_GRAY);
    		if(invertOptions == true)
    			g.setColor(Color.BLACK);
    		g.drawString("Options", 558, 658);
//    		
    		if(invertHistory == false)
    			g.setColor(Color.BLACK);
    		if(invertHistory == true)
    			g.setColor(Color.LIGHT_GRAY);
    		g.fillRect(550, 615, 59, 20);
    		g.setFont(new Font("Arial", Font.PLAIN, 12));
    		if(invertHistory == false)
    			g.setColor(Color.LIGHT_GRAY);
    		if(invertHistory == true)
    			g.setColor(Color.BLACK);
    		g.drawString("History", 560, 628);
//    		
    		if(shouldinvert == false)
    			g.setColor(Color.BLACK);
    		if(shouldinvert == true)
    			g.setColor(Color.LIGHT_GRAY);
    		g.fillRect(550, 509, 141, 40);
    		g.setFont(new Font("Arial", Font.PLAIN, 24));
    		if(shouldinvert == false)
    			g.setColor(Color.LIGHT_GRAY);
    		if(shouldinvert == true)
    			g.setColor(Color.BLACK);
    		g.drawString("Inventory", 567, 536);
    		g.setColor(Color.BLACK);
    		g.drawRect(550, 554, 59, 51);
    		g.setFont(new Font("Arial", Font.PLAIN, 18));
    		g.drawString("Player", 554, 569);
    		g.drawString("Info", 566, 599);
    		for(int x = 0; x <= 504; x += 63){
    			g.drawLine(x, 0, x, 504);
    		}
  			for(int y = 0; y <= 504; y += 63){
				g.drawLine(0, y, 504, y);
			}
  			g.setFont(new Font("Arial", Font.PLAIN, 28));
  			g.setColor(Color.DARK_GRAY);
  			g.drawString("Actions", 205, 663);
  			g.drawString("P", 700, 165);
  			g.drawString("l", 700, 194);
  			g.drawString("a", 700, 223);
  			g.drawString("y", 700, 252);
  			g.drawString("e", 700, 281);
  			g.drawString("r", 700, 310);
  			g.drawString("s", 700, 339);
    		int regionx = 0;
    		int regiony = 0;
    		int startx = 0;
    		int starty = 0;
    		int k = 0;
    		for(int i = 0; i < 4; i++){
    			if(i == 0){
    				startx = 1;
    				starty = 1;
    				regionx = 252;
    				regiony = 252;
    			}
    			if(i == 1){
    				startx = 253;
    				starty = 1;
    				regionx = 504;
    				regiony = 252;
    			}
    			if(i == 2){
    				startx = 1;
    				starty = 253;
    				regionx = 252;
    				regiony = 504;
    			}
    			if(i == 3){
    				startx = 253;
    				starty = 253;
    				regionx = 504;
    				regiony = 504;
    			}
	    		for(int x = startx; x < regionx; x += 63){
	    			for(int y = starty; y < regiony; y += 63){
	    				tilex[k] = x;
	    				tiley[k] = y;
			    		if(biome[i] != 5 && biome[i] != 6){
			    			if(k != 15 && k != 19 && k != 44 && k != 48){
				    			if(canSee[k] == true)
				    				g.setColor(shading[i]);
				    			if(canSee[k] == false)
				    				g.setColor(shading[i].darker());
				    			if(canMoveTo[k] == true && movemade == false && isMoving == true){
				    				if(shouldOrange[k] == true)
				    					g.setColor(Color.ORANGE);
				    				if(shouldOrange[k] == false)
				    					g.setColor(Color.YELLOW);
				    			}
				    			if(isAttacking == true && canShootTo[k] == true && showinventory == false){
				    				if(shouldOrange[k] == true)
				    					g.setColor(Color.ORANGE);
				    				if(shouldOrange[k] == false)
				    					g.setColor(Color.YELLOW);
				    			}
					    		g.fillRect(x, y, 62, 62);
			    			}
			    			if(k == 15 || k == 19 || k == 44 || k == 48){
			    				if(canSee[k] == true)
				    				g.setColor(Color.LIGHT_GRAY);
				    			if(canSee[k] == false)
				    				g.setColor(Color.LIGHT_GRAY.darker());
				    			if(canMoveTo[k] == true && movemade == false && isMoving == true){
				    				if(shouldOrange[k] == true)
				    					g.setColor(Color.ORANGE);
				    				if(shouldOrange[k] == false)
				    					g.setColor(Color.YELLOW);
				    			}
				    			if(isAttacking == true && canShootTo[k] == true && showinventory == false){
				    				if(shouldOrange[k] == true)
				    					g.setColor(Color.ORANGE);
				    				if(shouldOrange[k] == false)
				    					g.setColor(Color.YELLOW);
				    			}
				    			g.fillRect(x, y, 62, 62);
			    			}
				    		k++;
			    		}
			    		if(biome[i] == 5){
			    			if(k != 15 && k != 19 && k != 44 && k != 48){
				    			if(canSee[k] == true)
				    				g.drawImage(loadTaiga(), x, y, null);
				    			if(canSee[k] == false)
				    				g.drawImage(loadDarkTaiga(), x, y, null);
				    			if(canMoveTo[k] == true && movemade == false && isMoving == true){
				    				if(shouldOrange[k] == true)
				    					g.setColor(Color.ORANGE);
				    				if(shouldOrange[k] == false)
				    					g.setColor(Color.YELLOW);
				    				g.fillRect(x, y, 62, 62);
				    			}
				    			if(isAttacking == true && canShootTo[k] == true && showinventory == false){
				    				if(shouldOrange[k] == true)
				    					g.setColor(Color.ORANGE);
				    				if(shouldOrange[k] == false)
				    					g.setColor(Color.YELLOW);
				    				g.fillRect(x, y, 62, 62);
				    			}
			    			}
			    			if(k == 15 || k == 19 || k == 44 || k == 48){
			    				if(canSee[k] == true)
				    				g.setColor(Color.LIGHT_GRAY);
				    			if(canSee[k] == false)
				    				g.setColor(Color.LIGHT_GRAY.darker());
				    			if(canMoveTo[k] == true && movemade == false && isMoving == true){
				    				if(shouldOrange[k] == true)
				    					g.setColor(Color.ORANGE);
				    				if(shouldOrange[k] == false)
				    					g.setColor(Color.YELLOW);
				    			}
				    			if(isAttacking == true && canShootTo[k] == true && showinventory == false){
				    				if(shouldOrange[k] == true)
				    					g.setColor(Color.ORANGE);
				    				if(shouldOrange[k] == false)
				    					g.setColor(Color.YELLOW);
				    			}
				    			g.fillRect(x, y, 62, 62);
			    			}
			    			k++;
			    		}
			    		if(biome[i] == 6){
			    			if(k != 15 && k != 19 && k != 44 && k != 48){
				    			if(canSee[k] == true)
				    				g.drawImage(loadMountain(), x, y, null);
				    			if(canSee[k] == false)
				    				g.drawImage(loadDarkMountain(), x, y, null);
				    			if(canMoveTo[k] == true && movemade == false && isMoving == true){
				    				if(shouldOrange[k] == true)
				    					g.setColor(Color.ORANGE);
				    				if(shouldOrange[k] == false)
				    					g.setColor(Color.YELLOW);
				    				g.fillRect(x, y, 62, 62);
				    			}
				    			if(isAttacking == true && canShootTo[k] == true && showinventory == false){
				    				if(shouldOrange[k] == true)
				    					g.setColor(Color.ORANGE);
				    				if(shouldOrange[k] == false)
				    					g.setColor(Color.YELLOW);
				    				g.fillRect(x, y, 62, 62);
				    			}
			    			}
			    			if(k == 15 || k == 19 || k == 44 || k == 48){
			    				if(canSee[k] == true)
				    				g.setColor(Color.LIGHT_GRAY);
				    			if(canSee[k] == false)
				    				g.setColor(Color.LIGHT_GRAY.darker());
				    			if(canMoveTo[k] == true && movemade == false && isMoving == true){
				    				if(shouldOrange[k] == true)
				    					g.setColor(Color.ORANGE);
				    				if(shouldOrange[k] == false)
				    					g.setColor(Color.YELLOW);
				    			}
				    			if(isAttacking == true && canShootTo[k] == true && showinventory == false){
				    				if(shouldOrange[k] == true)
				    					g.setColor(Color.ORANGE);
				    				if(shouldOrange[k] == false)
				    					g.setColor(Color.YELLOW);
				    			}
				    			g.fillRect(x, y, 62, 62);
			    			}
			    			k++;
			    		}
			    		if(k - 1 == 15)
			    			g.drawImage(loadCornucopia1(), tilex[k - 1], tiley[k - 1], null);
			    		if(k - 1 == 19)
			    			g.drawImage(loadCornucopia2(), tilex[k - 1], tiley[k - 1], null);
			    		if(k - 1 == 44)
			    			g.drawImage(loadCornucopia3(), tilex[k - 1], tiley[k - 1], null);
			    		if(k - 1 == 48)
			    			g.drawImage(loadCornucopia4(), tilex[k - 1], tiley[k - 1], null);
			    		if(discovered[k - 1] == true){
			    			if(random.buildings[k - 1] == 0 && k - 1 != 15 && k - 1 != 19 && k - 1 != 44 && k - 1 != 48){
				       			g.drawImage(loadX(), tilex[k - 1], tiley[k - 1], null);
			    			}
				       		if(random.buildings[k - 1] == 1){
				       			g.drawImage(loadLake(), tilex[k - 1], tiley[k - 1], null);
				       		}
				       		if(random.buildings[k - 1] == 2){
				       			g.drawImage(loadSC(), tilex[k - 1], tiley[k - 1], null);
				       		}
				       		if(random.buildings[k - 1] == 3){
				       			if(biome[i] == 3)
				       				g.drawImage(loadMonster(), tilex[k - 1], tiley[k - 1], null);
				       			if(biome[i] == 2)
				       				g.drawImage(loadBee(), tilex[k - 1], tiley[k - 1], null);
				       			if(biome[i] == 1)
				       				g.drawImage(loadMudMan(), tilex[k - 1], tiley[k - 1], null);
				       			if(biome[i] == 4)
				       				g.drawImage(loadLizard(), tilex[k - 1], tiley[k - 1], null);
				       			if(biome[i] == 5)
				       				g.drawImage(loadWolf(), tilex[k - 1], tiley[k - 1], null);
				       			if(biome[i] == 7)
				       				g.drawImage(loadThug(), tilex[k - 1], tiley[k - 1], null);
				       		}
				       		if(random.buildings[k - 1] == 4){
				       			g.drawImage(loadBed(), tilex[k - 1], tiley[k - 1], null);
				       		}
			    		}
			    		if(canSee[k - 1] == true){
			    			for(int p = 0; p < 100; p++){
			    				if(onGround[k - 1][p] > 0){
			    					int item = onGround[k - 1][p];
			    					int locationx = 0;
			    					int locationy = 0;
			    					int locationhelper = 0;
			    					int locationhelper2 = 0;
			    					if(item >= 1 && item <= 6)locationhelper = 1;
			    					if(item >= 6 && item <= 13)locationhelper = 2;
			    					if(item >= 1 && item <= 3)locationhelper2 = 1;
			    					if(item >= 4 && item <= 6)locationhelper2 = 2;
			    					if(item >= 7 && item <= 9)locationhelper2 = 1;
			    					if(item >= 9 && item <= 13)locationhelper2 = 2;
			    					if(locationhelper == 2)locationy = 35;
			    					if(locationhelper == 1)locationy = 5;
			    					if(locationhelper2 == 1)locationx = 5;
			    					if(locationhelper2 == 2)locationx = 28;
			        				if(item == 1)
			        	        		g.drawImage(loadEBottle(), tilex[k - 1] + locationx, tiley[k - 1] + locationy, 29, 22, null);
			        				if(item == 2)
			        	        		g.drawImage(loadSword(), tilex[k - 1] + locationx, tiley[k - 1] + locationy, 29, 22, null);
			        				if(item == 3)
			        	        		g.drawImage(loadSwordOF(), tilex[k - 1] + locationx, tiley[k - 1] + locationy, 29, 22, null);
			        				if(item == 4)
			        					g.drawImage(loadSpear(), tilex[k - 1] + locationx, tiley[k - 1] + locationy, 29, 22, null);
			        				if(item == 5)
			        					g.drawImage(loadSpearOF(), tilex[k - 1] + locationx, tiley[k - 1] + locationy, 29, 22, null);
			        				if(item == 6)
			        					g.drawImage(loadCoat(), tilex[k - 1] + locationx, tiley[k - 1] + locationy, 29, 22, null);
			        				if(item == 7){
			        					g.drawImage(loadQuiver(), tilex[k - 1] + locationx, tiley[k - 1] + locationy, 29, 22, null);
			        				}
			        				if(item == 8)
			        					g.drawImage(loadBow(), tilex[k - 1] + locationx, tiley[k - 1] + locationy, 29, 22, null);
			        				if(item == 9)
			        					g.drawImage(loadMeat(), tilex[k - 1] + locationx, tiley[k - 1] + locationy, 29, 22, null);
			        				if(item == 10)
			        					g.drawImage(loadBanana(), tilex[k - 1] + locationx, tiley[k - 1] + locationy, 29, 22, null);
			        				if(item == 11)
			        					g.drawImage(loadTomato(), tilex[k - 1] + locationx, tiley[k - 1] + locationy, 29, 22, null);
			        				if(item == 12)
			        					g.drawImage(loadMango(), tilex[k - 1] + locationx, tiley[k - 1] + locationy, 29, 22, null);
			        				if(item == 13)
			        					g.drawImage(loadPoison(), tilex[k - 1] + locationx, tiley[k - 1] + locationy, 29, 22, null);
			    				}
			    			}			    			
			    		}
	    			}
	    		}
    		}
//    		if(isStarting == false){
//    			g.setColor(Color.GRAY.brighter());
//	       		fillCircle(g, 252, 252, 50);
//	       		g.setColor(Color.GRAY);
//	       		fillCircle(g, 252, 252, 25);
//	        	g.setColor(Color.GRAY.darker());
//	        	fillCircle(g, 252, 252, 13);
//	        }
//    		if(isStarting == true || canMoveTo[64] == true){
//    			g.setColor(Color.YELLOW);
//    			fillCircle(g, 252, 252, 50);
//    			fillCircle(g, 252, 252, 25);
//    			fillCircle(g, 252, 252, 13);
//    			g.setColor(Color.BLACK);
//    			drawCircle(g, 252, 252, 50);
//    			drawCircle(g, 252, 252, 25);
//    			drawCircle(g, 252, 252, 13);
//    		}
	       	g.setFont(new Font("Arial", Font.PLAIN, 28));
	       	if(invertleft == false)
	       		g.setColor(Color.BLACK);
	       	if(invertleft == true)
	       		g.setColor(Color.LIGHT_GRAY);
	       	g.fillRect(0, 585, 168, 50);
	       	if(invertleft == false)
	       		g.setColor(Color.LIGHT_GRAY);
	       	if(invertleft == true)
	       		g.setColor(Color.BLACK);
	       	g.drawString("Attack", 40, 620);
	       	if(invertmiddle == false)
	       		g.setColor(Color.BLACK);
	       	if(invertmiddle == true)
	       		g.setColor(Color.LIGHT_GRAY);
	       	g.fillRect(169, 585, 168, 50);
	       	if(invertmiddle == false)
	       		g.setColor(Color.LIGHT_GRAY);
	       	if(invertmiddle == true)
	       		g.setColor(Color.BLACK);
	       	if(movemade == false)
	       		g.drawString("Move", 220, 620);
	       	if(movemade == true)
	       		g.drawString("Next Turn", 190, 620);
	       	if(invertright == false)
	       		g.setColor(Color.BLACK);
	       	if(invertright == true)
	       		g.setColor(Color.LIGHT_GRAY);
	       	g.fillRect(338, 585, 167, 50);
	       	if(invertright == false)
	       		g.setColor(Color.LIGHT_GRAY);
	       	if(invertright == true)
	       		g.setColor(Color.BLACK);
	       	int crashstopper = PlayerLocation;
	       	if(PlayerLocation > 63)
	       		crashstopper = 63;
	       	if(discovered[crashstopper] == false || random.buildings[crashstopper] == 0 || random.buildings[crashstopper] == 3 || isObserver || PlayerLocation == 65)
	       		g.drawString("Discover", 368, 620);
	       	if(PlayerLocation < 65 && (discovered[PlayerLocation] == true && random.buildings[PlayerLocation] == 1)){
	       		g.setFont(new Font("Arial", Font.PLAIN, 18));
	       		g.drawString("Refill and Drink", 353, 618);
		       	g.setFont(new Font("Arial", Font.PLAIN, 28));
	       	}
	       	if(PlayerLocation < 65 && (discovered[PlayerLocation] == true && random.buildings[PlayerLocation] == 2))
	       		g.drawString("Scavenge", 368, 620);
	       	if(PlayerLocation < 65 && (discovered[PlayerLocation] == true && random.buildings[PlayerLocation] == 4))
	       		g.drawString("Sleep", 381, 620);
	       	g.setColor(Color.RED);
	       	g.drawLine(168, 585, 168, 634);
	       	g.drawLine(337, 585, 337, 634);
	       	g.setColor(Color.BLACK);
	       	g.drawRect(0, 509, 504, 70);
	       	int y = 0;
	       	for(int i = 0; i < 23; i++){
	       		g.setColor(create.EnemyC[i]);
		       	g.drawRect(550, y, 140, 20);
		       	if(create.EnemyN[i] == null)i++;
		       	g.setFont(new Font("Arial", Font.PLAIN, 12));
		       	g.drawString(create.EnemyN[i], 555, y + 15);
		       	y += 22;
	       	}
	       	g.setColor(Color.BLACK);
	       	g.setFont(new Font("Consolas", Font.PLAIN, 14));
	       	y = 521;
	       	g.drawString(text[0], 1, y);
	       	y += 13;
	       	g.drawString(text[1], 1, y);
	       	y += 13;
	       	g.drawString(text[2], 1, y);
	       	y += 14;
	       	g.drawString(text[3], 1, y);
	       	y += 14;
	       	g.drawString(text[4], 1, y);
	       	for(int i = 0; i < 23; i++){
	       		if(enemyShow[i] == true){
	       			g.drawRect(0, 509, 504, 131);
	       			g.setColor(Color.WHITE);
	       			g.fillRect(1, 510, 503, 130);
	       			g.setColor(Color.BLACK);
	       			if(create.EnemyN[i] == null)i++;
	       			g.setFont(new Font("Arial", Font.PLAIN, 22));
	       			g.drawString("Name: " + create.EnemyN[i], 120, 533);
	       			g.drawLine(115, 540, 504, 540);
	       			g.drawLine(115, 540, 115, 640);
	       			g.drawString("District: " + create.EnemyD[i], 120, 563);
	       			g.drawString("Age: " + create.EnemyA[i], 120, 586);
	       			g.drawString("Gender: " + create.getGenderString(create.EnemyP[i]), 120, 609);
	       			g.drawString("Total Skill: " + create.getTotalSkill(create.EnemySt[i], create.EnemySp[i], create.EnemySm[i], create.EnemyWs[i]), 120, 632);
	       			if(enemyStDis[i] == true)g.drawString("Strength: " + create.EnemySt[i], 310, 563);
	       			if(enemyStDis[i] == false)g.drawString("Strength: ?", 310, 563);
	       			if(enemySpDis[i] == true)g.drawString("Speed: " + create.EnemySp[i], 310, 586);
	       			if(enemySpDis[i] == false)g.drawString("Speed: ?", 310, 586);
	       			if(enemySmDis[i] == true)g.drawString("Smarts: " + create.EnemySm[i], 310, 609);
	       			if(enemySmDis[i] == false)g.drawString("Smarts: ?", 310, 609);
	       			if(enemyWsDis[i] == true)g.drawString("Weapon Skill: " + create.EnemyWs[i], 310, 632);
	       			if(enemyWsDis[i] == false)g.drawString("Weapon Skill: ?", 310, 632);
	       			g.drawString("Kills:  " + EnemyKills[i], 5, 586);
	       			g.setFont(new Font("Arial", Font.PLAIN, 12));
	       			g.drawString("Damage Dealt:  " + EnemyDamageDealt[i], 5, 609);
	       			g.drawString("Damage Taken:  " + EnemyDamageTaken[i], 5, 632);
	       		}
	       	}
	       	if(overPlayer == true){
       			g.drawRect(0, 509, 504, 131);
       			g.setColor(Color.WHITE);
       			g.fillRect(1, 510, 503, 130);
       			g.setColor(Color.BLACK);
       			g.setFont(new Font("Arial", Font.PLAIN, 22));
       			g.drawString("Name: " + create.PlayerN, 120, 533);
       			g.drawLine(115, 540, 504, 540);
       			g.drawLine(115, 540, 115, 640);
       			g.drawString("District: " + create.PlayerD, 120, 563);
       			g.drawString("Age: " + create.PlayerA, 120, 586);
       			g.drawString("Gender: " + create.getGenderString(create.PlayerP), 120, 609);
       			g.drawString("Total Skill: " + create.getTotalSkill(create.PlayerSt, create.PlayerSp, create.PlayerSm, create.PlayerWs), 120, 632);
       			g.drawString("Strength: " + create.PlayerSt, 310, 563);
       			g.drawString("Speed: " + create.PlayerSp, 310, 586);
       			g.drawString("Smarts: " + create.PlayerSm, 310, 609);
       			g.drawString("Weapon Skill: " + create.PlayerWs, 310, 632);
       			g.setFont(new Font("Arial", Font.PLAIN, 12));
       			g.drawString("Health:  " + PlayerHealth, 5, 588);
       			g.drawString("Days of Water Left:" + (7 - daysnowater), 5, 599);
       			g.drawString("Kills:  " + playerKills, 5, 610);
       			g.drawString("Damage Dealt:  " + playerDamageDealt, 5, 621);
       			g.drawString("Damage Taken:  " + playerDamageTaken, 5, 632);
	       	}
//	       	g.setColor(create.PlayerC);
//	       	if(drawPlayer == true)fillCircle(g, tilex[PlayerLocation] + 30, tiley[PlayerLocation] + 30, 25 - create.PlayerP);
	       	for(int p = 0; p < 64; p++){
	       		int people = getPeopleInTile(p);
	       		if(people >= 0 && people <= 4){
	       			int number = 0;
	       			for(int q = 0; q < 23; q++){
	       				if(EnemyAlive[q] && canSee[p] && EnemyLocation[q] == p){
	       					int numberC = number;
	       					int numberX = number;
	       					if(number == 0 || number == 1)numberC = 0;
	       					if(number == 2 || number == 3)numberC = 1;
	       					if(number == 2)numberX = 0;
	       					if(number == 3)numberX = 1;
	       					if(enemyShow[q]){
		       					if(create.getGenderBoolean(create.EnemyP[q]) == true)
		       						g.drawImage(loadHBoy(), tilex[p] + (numberX * 31), tiley[p] + ((numberC) * 31), null);
		       					if(create.getGenderBoolean(create.EnemyP[q]) == false)
		       						g.drawImage(loadHGirl(), tilex[p] + (numberX * 31), tiley[p] + ((numberC) * 31), null);
	       					}
	       					if(!enemyShow[q]){
		       					if(create.getGenderBoolean(create.EnemyP[q]) == true)
		       						g.drawImage(loadBoy(create.EnemyD[q]), tilex[p] + (numberX * 31), tiley[p] + ((numberC) * 31), null);
		       					if(create.getGenderBoolean(create.EnemyP[q]) == false)
		       						g.drawImage(loadGirl(create.EnemyD[q]), tilex[p] + (numberX * 31), tiley[p] + ((numberC) * 31), null);
	       					}
	       					number++;
	       				}
	       			}
	       			if(PlayerLocation == p && PlayerAlive == true){
       					int numberC = number;
       					int numberX = number;
       					if(number == 0 || number == 1)numberC = 0;
       					if(number == 2 || number == 3)numberC = 1;
       					if(number == 2)numberX = 0;
       					if(number == 3)numberX = 1;
       					if(!overPlayer){
	   						if(create.getGenderBoolean(create.PlayerP) == true)
	   							g.drawImage(loadBoy(create.PlayerD), tilex[p] + (numberX * 31), tiley[p] + ((numberC) * 31), null);
	   						if(create.getGenderBoolean(create.PlayerP) == false)
	   							g.drawImage(loadGirl(create.PlayerD), tilex[p] + (numberX * 31), tiley[p] + ((numberC) * 31), null);
       					}
       					if(overPlayer){
	   						if(create.getGenderBoolean(create.PlayerP) == true)
	   							g.drawImage(loadHBoy(), tilex[p] + (numberX * 31), tiley[p] + ((numberC) * 31), null);
	   						if(create.getGenderBoolean(create.PlayerP) == false)
	   							g.drawImage(loadHGirl(), tilex[p] + (numberX * 31), tiley[p] + ((numberC) * 31), null);
       					}
	       			}
	       		}
	       		if(people > 4 && people <= 8){
	       			int number = 0;
	       			for(int q = 0; q < 23; q++){
	       				if(EnemyAlive[q] && canSee[p] && EnemyLocation[q] == p){
	       					int numberC = number;
	       					int numberX = number;
	       					if(number <= 3)numberC = 0;
	       					if(number >= 4)numberC = 1;
	       					if(number == 4)numberX = 0;
	       					if(number == 5)numberX = 1;
	       					if(number == 6)numberX = 2;
	       					if(number == 7)numberX = 3;
	       					if(enemyShow[q]){
		       					if(create.getGenderBoolean(create.EnemyP[q]) == true)
		       						g.drawImage(loadHBoy(), tilex[p] + (numberX * 15), tiley[p] + ((numberC) * 31), 15, 31, null);
		       					if(create.getGenderBoolean(create.EnemyP[q]) == false)
		       						g.drawImage(loadHGirl(), tilex[p] + (numberX * 15), tiley[p] + ((numberC) * 31), 15, 31, null);
	       					}
	       					if(!enemyShow[q]){
		       					if(create.getGenderBoolean(create.EnemyP[q]) == true)
		       						g.drawImage(loadBoy(create.EnemyD[q]), tilex[p] + (numberX * 15), tiley[p] + ((numberC) * 31), 15, 31, null);
		       					if(create.getGenderBoolean(create.EnemyP[q]) == false)
		       						g.drawImage(loadGirl(create.EnemyD[q]), tilex[p] + (numberX * 15), tiley[p] + ((numberC) * 31), 15, 31, null);
	       					}
	       					number++;
	       				}
	       			}
	       			if(PlayerLocation == p && PlayerAlive == true){
       					int numberC = number;
       					int numberX = number;
       					if(number <= 3)numberC = 0;
       					if(number >= 4)numberC = 1;
       					if(number == 4)numberX = 0;
       					if(number == 5)numberX = 1;
       					if(number == 6)numberX = 2;
       					if(number == 7)numberX = 3;
       					if(!overPlayer){
	   						if(create.getGenderBoolean(create.PlayerP) == true)
	   							g.drawImage(loadBoy(create.PlayerD), tilex[p] + (numberX * 15), tiley[p] + ((numberC) * 31), 15, 31, null);
	   						if(create.getGenderBoolean(create.PlayerP) == false)
	   							g.drawImage(loadGirl(create.PlayerD), tilex[p] + (numberX * 15), tiley[p] + ((numberC) * 31), 15, 31, null);
       					}
       					if(overPlayer){
	   						if(create.getGenderBoolean(create.PlayerP) == true)
	   							g.drawImage(loadHBoy(), tilex[p] + (numberX * 15), tiley[p] + ((numberC) * 31), 15, 31, null);
	   						if(create.getGenderBoolean(create.PlayerP) == false)
	   							g.drawImage(loadHGirl(), tilex[p] + (numberX * 15), tiley[p] + ((numberC) * 31), 15, 31, null);
       					}
	       			}
	       		}
	       		if(people > 8){
	       			int number = 0;
	       			for(int q = 0; q < 23; q++){
	       				if(EnemyAlive[q] && canSee[p] && EnemyLocation[q] == p){
	       					int numberC = number;
	       					int numberX = number;
	       					boolean weirdness = false;
	       					if(number <= 3)numberC = 1;
	       					if(number >= 4)numberC = 2;
	       					if(number == 4)numberX = 0;
	       					if(number == 5)numberX = 1;
	       					if(number == 6)numberX = 2;
	       					if(number == 7)numberX = 3;
	       					if(number >= 8){
	       						numberC = 1;
	       						numberX = number - 8;
	       						if(numberX > 2)
	       							numberX = number - (number - 2);
	       						if(number >= 11){
	       							numberC = 2;
	       							int subtraction = Math.abs(0 - numberX) / 2;
	       							numberX -= subtraction;
	       						}
	       						weirdness = true;
	       					}
	       					if(weirdness == false){
	       						if(enemyShow[q]){
			       					if(create.getGenderBoolean(create.EnemyP[q]) == true)
			       						g.drawImage(loadHBoy(), tilex[p] + (numberX * 15), tiley[p] + ((numberC / 2) * 31), 15, 31, null);
			       					if(create.getGenderBoolean(create.EnemyP[q]) == false)
			       						g.drawImage(loadHGirl(), tilex[p] + (numberX * 15), tiley[p] + ((numberC / 2) * 31), 15, 31, null);
	       						}
	       						if(!enemyShow[q]){
			       					if(create.getGenderBoolean(create.EnemyP[q]) == true)
			       						g.drawImage(loadBoy(create.EnemyD[q]), tilex[p] + (numberX * 15), tiley[p] + ((numberC / 2) * 31), 15, 31, null);
			       					if(create.getGenderBoolean(create.EnemyP[q]) == false)
			       						g.drawImage(loadGirl(create.EnemyD[q]), tilex[p] + (numberX * 15), tiley[p] + ((numberC / 2) * 31), 15, 31, null);
	       						}
	       					}
	       					if(weirdness == true){
	       						if(enemyShow[q]){
			       					if(create.getGenderBoolean(create.EnemyP[q]) == true)
			       						g.drawImage(loadHBoy(), tilex[p] + (numberX * 20), tiley[p] + ((numberC / 2) * 21), 15, 31, null);
			       					if(create.getGenderBoolean(create.EnemyP[q]) == false)
			       						g.drawImage(loadHGirl(), tilex[p] + (numberX * 24), tiley[p] + ((numberC / 2) * 21), 15, 31, null);
	       						}
	       						if(!enemyShow[q]){
			       					if(create.getGenderBoolean(create.EnemyP[q]) == true)
			       						g.drawImage(loadBoy(create.EnemyD[q]), tilex[p] + (numberX * 20), tiley[p] + ((numberC / 2) * 21), 15, 31, null);
			       					if(create.getGenderBoolean(create.EnemyP[q]) == false)
			       						g.drawImage(loadGirl(create.EnemyD[q]), tilex[p] + (numberX * 24), tiley[p] + ((numberC / 2) * 21), 15, 31, null);
	       						}
	       					}
	       					number++;
	       				}
	       			}
	       			if(PlayerLocation == p && PlayerAlive == true){
	       				int xmul = 22;
	       				int ymul = 23;
       					int numberC = number;
       					int numberX = number;
       					if(number <= 3)numberC = 0;
       					if(number >= 4)numberC = 1;
       					if(number == 4)numberX = 0;
       					if(number == 5)numberX = 1;
       					if(number == 6)numberX = 2;
       					if(number == 7)numberX = 3;
       					if(number >= 8){
       						numberX = number - 7;
       						numberC = 0;
       						xmul += 7;
       						if(numberX * xmul >= 52)
       							xmul -= 7;
       						if(numberC * ymul >= 52)
       							ymul -= 7;
       					}
       					if(!overPlayer){
	   						if(create.getGenderBoolean(create.PlayerP) == true)
	   							g.drawImage(loadBoy(create.PlayerD), tilex[p] + (numberX + xmul), tiley[p] + ((numberC) * ymul), 15, 31, null);
	   						if(create.getGenderBoolean(create.PlayerP) == false)
	   							g.drawImage(loadGirl(create.PlayerD), tilex[p] + (numberX + xmul), tiley[p] + ((numberC) * ymul), 15, 31, null);
       					}
       					if(overPlayer){
	   						if(create.getGenderBoolean(create.PlayerP) == true)
	   							g.drawImage(loadHBoy(), tilex[p] + (numberX + xmul), tiley[p] + ((numberC) * ymul), 15, 31, null);
	   						if(create.getGenderBoolean(create.PlayerP) == false)
	   							g.drawImage(loadHGirl(), tilex[p] + (numberX + xmul), tiley[p] + ((numberC) * ymul), 15, 31, null);
       					}
	       			}
	       		}
	       	}
    		if(showinventory == true){
    			g.setColor(Color.WHITE);
    			g.fillRect(50, 175, 626, 200);
    			g.setColor(Color.BLACK);
    			g.drawRect(50, 175, 626, 200);
    			if(invertx == false)
    				g.setColor(Color.BLACK);
    			if(invertx == true)
    				g.setColor(Color.LIGHT_GRAY);
    			g.fillRect(636, 180, 35, 35);
    			if(invertx == true)
    				g.setColor(Color.BLACK);
    			if(invertx == false)
    				g.setColor(Color.LIGHT_GRAY);
    			g.setFont(new Font("Times New Roman", Font.PLAIN, 40));
    			g.drawString("X", 639, 211);
    			g.setColor(Color.BLACK);
        		g.drawLine(576, 175, 576, 375);
        		g.drawLine(50, 275, 576, 275);
        		int j = 50;
        		j += 132;
        		g.drawLine(j, 175, j, 375);
        		j += 132;
        		g.drawLine(j, 175, j, 375);
        		j += 131;
        		g.drawLine(j, 175, j, 375);
        		j += 131;
        		g.drawLine(j, 175, j, 375);
        		g.setColor(Color.RED);
        		for(int i = 0, x = 51, b = 176, width = 131; i < 8; i++){
        			if(i == 2 || i == 6){
        				x += 132;
        				width = 130;
        			}
        			if(i == 1 || i == 4 || i == 5){
        				x += 132;
        				width = 131;
        			}
        			if(i == 7 || i == 3)
        				x += 131;
        			if(i == 4){
        				x = 51;
        				b = 276;
        			}
        			if(inventoryI[i] != 0){
        				if(inventoryI[i] == 1){
        					if(bottletype[i] == 0)
        	        			g.drawImage(loadEBottle(), x, b, width, 99, null);
        					if(bottletype[i] == 1)
	        					g.drawImage(loadBottle(), x, b, width, 99, null);
        					if(bottletype[i] == 2)
	        					g.drawImage(loadMedicine(), x, b, width, 99, null);
        					if(bottletype[i] == 3)
        						g.drawImage(loadPoison(), x, b, width, 99, null);
        				}
        				if(inventoryI[i] == 2)
        	        		g.drawImage(loadSword(), x, b, width, 99, null);
        				if(inventoryI[i] == 3)
        	        		g.drawImage(loadSwordOF(), x, b, width, 99, null);
        				if(inventoryI[i] == 4)
        					g.drawImage(loadSpear(), x, b, width, 99, null);
        				if(inventoryI[i] == 5)
        					g.drawImage(loadSpearOF(), x, b, width, 99, null);
        				if(inventoryI[i] == 6)
        					g.drawImage(loadCoat(), x, b, width, 99, null);
        				if(inventoryI[i] == 7)
        					g.drawImage(loadQuiver(), x, b, width, 99, null);
        				if(inventoryI[i] == 8)
        					g.drawImage(loadBow(), x, b, width, 99, null);
        				if(inventoryI[i] == 9)
        					g.drawImage(loadMeat(), x, b, width, 99, null);
        				if(inventoryI[i] == 10)
        					g.drawImage(loadBanana(), x, b, width, 99, null);
        				if(inventoryI[i] == 11)
        					g.drawImage(loadTomato(), x, b, width, 99, null);
        				if(inventoryI[i] == 12)
        					g.drawImage(loadMango(), x, b, width, 99, null);
        			}
        			if(selectinv[i] == true){
        				g.setColor(Color.BLUE);
        				g.drawRect(x, b, width - 1, 98);
        			}
        		}
        		if(invertuse == false)
        			g.setColor(Color.BLACK);
        		if(invertuse == true)
        			g.setColor(Color.LIGHT_GRAY);
        		g.fillRect(577, 335, 99, 40);
        		if(invertuse == true)
        			g.setColor(Color.BLACK);
        		if(invertuse == false)
        			g.setColor(Color.LIGHT_GRAY);
        		g.setFont(new Font("Arial", Font.PLAIN, 35));
        		g.drawString("Use", 590, 367);
        		g.setColor(Color.RED);
        		g.drawLine(577, 335, 676, 335);
        		g.drawLine(577, 294, 676, 294);
        		if(invertuse2 == false)
        			g.setColor(Color.BLACK);
        		if(invertuse2 == true)
        			g.setColor(Color.LIGHT_GRAY);
        		g.fillRect(577, 295, 99, 40);
        		if(invertuse2 == true)
        			g.setColor(Color.BLACK);
        		if(invertuse2 == false)
        			g.setColor(Color.LIGHT_GRAY);
        		g.drawString("Drop", 585, 327);
        		g.setFont(new Font("Arial", Font.PLAIN, 25));
        		if(invertuse3 == false)
        			g.setColor(Color.BLACK);
        		if(invertuse3 == true)
        			g.setColor(Color.LIGHT_GRAY);
        		g.fillRect(577, 254, 99, 40);
        		if(invertuse3 == true)
        			g.setColor(Color.BLACK);
        		if(invertuse3 == false)
        			g.setColor(Color.LIGHT_GRAY);
        		g.drawString("Pick Up", 583, 283);
        		for(int i = 0; i < 8; i++){
        			if(overItem[i] == true){
        				check0();
        				g.setColor(Color.BLUE);
        				if(inventoryI[i] != 0){
        					g.fillRect(getX() + 10, getY(), 122, 20);
        					g.setColor(Color.BLACK);
        					g.drawRect(getX() + 10, getY(), 122, 20);
        				}
        				g.setColor(Color.BLACK);
        				g.setFont(new Font("Arial", Font.PLAIN, 20));
        				if(inventoryI[i] != 0)
        					g.drawString(translateItemC(inventoryI[i]), getX() + 12, getY() + 17);
        			}
        		}
    		}
    		if(PlayerAlive == false){
    			g.setFont(new Font("Arial", Font.PLAIN, 30));
        		if(invertReplay == false)
        			g.setColor(Color.BLACK);
        		if(invertReplay == true)
        			g.setColor(Color.LIGHT_GRAY);
        		g.fillRect(190, 221, 125, 62);
        		if(invertReplay == true)
        			g.setColor(Color.BLACK);
        		if(invertReplay == false)
        			g.setColor(Color.LIGHT_GRAY);
        		g.drawString("Replay", 206, 264);
    		}
    		if(showOptions == true){
    			g.setColor(new Color(242, 242, 242));
    			g.fillRect(25, 25, 669, 621);
    			g.setColor(Color.BLACK);
    			g.drawLine(25, 25, 694, 25);
    			g.drawLine(25, 25, 25, 646);
    			g.drawLine(694, 25, 694, 646);
    			g.drawLine(25, 646, 694, 646);
    			if(invertx == false)
    				g.setColor(Color.BLACK);
    			if(invertx == true)
    				g.setColor(Color.LIGHT_GRAY);
    			g.fillRect(649, 35, 35, 35);
    			if(invertx == true)
    				g.setColor(Color.BLACK);
    			if(invertx == false)
    				g.setColor(Color.LIGHT_GRAY);
    			g.setFont(new Font("Times New Roman", Font.PLAIN, 40));
    			g.drawString("X", 652, 66);
    			if(invertoptions[0] == false)
    				g.setColor(Color.BLACK);
    			if(invertoptions[0] == true)
    				g.setColor(Color.LIGHT_GRAY);
    			g.fillRect(35, 35, 180, 50);
    			if(invertoptions[0] == true)
    				g.setColor(Color.BLACK);
    			if(invertoptions[0] == false)
    				g.setColor(Color.LIGHT_GRAY);
    			g.setFont(new Font("Arial", Font.PLAIN, 11));
    			g.drawString("Show Mouse Coordinates:  " + showCoordinates, 45, 65);
    			if(invertoptions[1] == false)
    				g.setColor(Color.BLACK);
    			if(invertoptions[1] == true)
    				g.setColor(Color.LIGHT_GRAY);
    			g.fillRect(235, 35, 180, 50);
    			if(invertoptions[1] == true)
    				g.setColor(Color.BLACK);
    			if(invertoptions[1] == false)
    				g.setColor(Color.LIGHT_GRAY);
    			g.setFont(new Font("Arial", Font.PLAIN, 17));
    			g.drawString("Discover Everything", 245, 65);
    			if(invertoptions[2] == false)
    				g.setColor(Color.BLACK);
    			if(invertoptions[2] == true)
    				g.setColor(Color.LIGHT_GRAY);
    			g.fillRect(435, 35, 180, 50);
    			if(invertoptions[2] == true)
    				g.setColor(Color.BLACK);
    			if(invertoptions[2] == false)
    				g.setColor(Color.LIGHT_GRAY);
    			g.setFont(new Font("Arial", Font.PLAIN, 17));
    			g.drawString("Enable Cheats:  " + enableCheats, 445, 65);
    			if(invertoptions[3] == false)
    				g.setColor(Color.BLACK);
    			if(invertoptions[3] == true)
    				g.setColor(Color.LIGHT_GRAY);
    			g.fillRect(35, 95, 180, 50);
    			if(invertoptions[3] == true)
    				g.setColor(Color.BLACK);
    			if(invertoptions[3] == false)
    				g.setColor(Color.LIGHT_GRAY);
    			g.setFont(new Font("Arial", Font.PLAIN, 15));
    			g.drawString("Observer Mode:  " + isObserver, 45, 125);
    			if(invertoptions[4] == false)
    				g.setColor(Color.BLACK);
    			if(invertoptions[4] == true)
    				g.setColor(Color.LIGHT_GRAY);
    			g.fillRect(235, 95, 180, 50);
    			if(invertoptions[4] == true)
    				g.setColor(Color.BLACK);
    			if(invertoptions[4] == false)
    				g.setColor(Color.LIGHT_GRAY);
    			g.setFont(new Font("Arial", Font.PLAIN, 25));
    			g.drawString("Music:  " + isMusic, 254, 130);
    			if(invertoptions[5] == false)
    				g.setColor(Color.BLACK);
    			if(invertoptions[5] == true)
    				g.setColor(Color.LIGHT_GRAY);
    			g.fillRect(435, 95, 180, 50);
    			if(invertoptions[5] == true)
    				g.setColor(Color.BLACK);
    			if(invertoptions[5] == false)
    				g.setColor(Color.LIGHT_GRAY);
    			g.setFont(new Font("Arial", Font.PLAIN, 20));
    			g.drawString("Log Memory:  " + isLogging, 443, 130);
    			if(invertoptions[6] == false)
    				g.setColor(Color.BLACK);
    			if(invertoptions[6] == true)
    				g.setColor(Color.LIGHT_GRAY);
    			g.fillRect(35, 155, 180, 50);
    			if(invertoptions[6] == true)
    				g.setColor(Color.BLACK);
    			if(invertoptions[6] == false)
    				g.setColor(Color.LIGHT_GRAY);
    			g.setFont(new Font("Arial", Font.PLAIN, 25));
    			g.drawString("Data Type:  " + dataType, 46, 190);
    		}
    		if(showHistory == true){
    			g.setColor(new Color(242, 242, 242));
    			g.fillRect(25, 25, 669, 621);
    			g.setColor(Color.BLACK);
    			g.drawLine(25, 25, 694, 25);
    			g.drawLine(25, 25, 25, 646);
    			g.drawLine(694, 25, 694, 646);
    			g.drawLine(25, 646, 694, 646);
    			if(invertx == false)
    				g.setColor(Color.BLACK);
    			if(invertx == true)
    				g.setColor(Color.LIGHT_GRAY);
    			g.fillRect(649, 35, 35, 35);
    			if(invertx == true)
    				g.setColor(Color.BLACK);
    			if(invertx == false)
    				g.setColor(Color.LIGHT_GRAY);
    			g.setFont(new Font("Times New Roman", Font.PLAIN, 40));
    			g.drawString("X", 652, 66);
    			for(int i = 0, p = 24, corx = 40, cory = 615; i < 25; i++, p--, cory -= 24){
    				g.setColor(Color.WHITE);
    				g.fillRect(corx, cory, 604, 16);
    				g.setColor(Color.BLACK);
    				g.setFont(new Font("Arial", Font.PLAIN, 16));
    				g.drawString((i + 1) + " " + time[p + 5] + " " + history[p], corx + 3, cory + 14);
    			}
    		}
    	}
    	if(drawWhat == 5){
    		boolean gender = true;
    		if(create.PlayerGU != 1 && create.PlayerGU != 2)gender = create.getGenderBoolean(create.PlayerP);
    		if(create.PlayerGU == 1)gender = true;
    		if(create.PlayerGU == 2)gender = false;
    		g.drawRect(1, 275, 717, 50);
    		g.setFont(new Font("Arial", Font.BOLD, 25));
    		g.drawString("Currently in " + translateMode(usingNumber), 220, 50);
    		g.drawString("Instructions", 275, 345);
    		g.setFont(new Font("Arial", Font.PLAIN, 15));
    		if(usingNumber){
	    		g.drawString("Type in the number of the character you want to play as.  You may only type in numbers between 1 and " + name.getHighest(gender) + ".", 1, 355);
	    		g.drawString("Typing in nothing will give you a random name.  Backspace clears your entry.  Enter submits your number.", 1, 373);
    		}
    		if(!usingNumber){
    			g.drawString("Type in the name of the character you'd like to play as.", 1, 355);
    			g.drawString("Enter submits your name.", 1, 373);
    		}
    		if(invertoptions[7] == false)
    			g.setColor(Color.BLACK);
    		if(invertoptions[7] == true)
    			g.setColor(Color.LIGHT_GRAY);
    		g.fillRect(50, 400, 250, 100);
    		if(invertoptions[7] == true)
    			g.setColor(Color.BLACK);
    		if(invertoptions[7] == false)
    			g.setColor(Color.LIGHT_GRAY);
    		g.setFont(new Font("Arial", Font.PLAIN, 36));
    		g.drawString("Number Mode", 60, 466);
    		if(invertoptions[8] == false)
    			g.setColor(Color.BLACK);
    		if(invertoptions[8] == true)
    			g.setColor(Color.LIGHT_GRAY);
    		g.fillRect(376, 400, 250, 100);
    		if(invertoptions[8] == true)
    			g.setColor(Color.BLACK);
    		if(invertoptions[8] == false)
    			g.setColor(Color.LIGHT_GRAY);
    		g.setFont(new Font("Arial", Font.PLAIN, 46));
    		g.drawString("Text Mode", 390, 468);
    		Font font = new Font("Arial", Font.PLAIN, 42);
    		g.setFont(font);
    		g.setColor(Color.BLACK);
    		if(usingNumber)
    			g.drawString(numberS, 6, 310);
    		if(!usingNumber)
    			g.drawString(nameInput, 6, 310);
    		if(badnumber == true){
    			g.setColor(Color.RED);
    			g.drawString("Invalid number value!", 225, 110);
    		}
    	}
    	if(drawWhat == 6){
    		g.setColor(Color.BLACK);
    		g.fillRect(0, 0, 726, 700);
    		g.drawImage(loadLoading(), 0, 0, 720, 640, null);
			g.setFont(new Font("Times New Roman", Font.BOLD, 40));
			g.setColor(new Color(Color.RED.getRed() - rand.nextInt(50), 0, 0));
			g.drawString("Loading..." + " " + percent + "%", 240, 660);
			g.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			g.drawString("Stuck at " + percent + "%?  Make sure your Java version is Java 7 Update 7 or higher!", 140, 630);
    	}
    	if(drawWhat == 7){
    		int midHei = (726 / 2) - (loadLogo().getHeight(null) / 2);
    		int midWid = (700 / 2) - (loadLogo().getWidth(null) / 2);
    		g.drawImage(loadLogo(), midHei, midWid, null);
    	}
    	if(drawWhat == 17){
    		g.setColor(Color.BLACK);
    		g.drawImage(loadSadFace(), 200, 452, null);
    		for(int wherex = 0; wherex <= 725; wherex += 45){
    			for(int wherey = 0; wherey <= 700; wherey += 12){
    				g.drawString("ERROR", wherex, wherey);
    			}
    		}
	       	Font font = new Font("Arial", Font.PLAIN, 44);
	       	g.setFont(font);
	       	g.drawString("NEVER CRASH THE GAME AGAIN", 0, 352);
    	}
     }
//    private void fillCircle(Graphics cg, int xCenter, int yCenter, int r) {
//    	cg.fillOval(xCenter-r, yCenter-r, 2*r, 2*r);
//    }
//    private void drawCircle(Graphics cg, int xCenter, int yCenter, int r) {
//    	cg.drawOval(xCenter-r, yCenter-r, 2*r, 2*r);
//    }
    private Image loadLogo(){
    	ImageIcon logoIcon = new ImageIcon(this.getClass().getResource("logo.png"));
    	Image logo = logoIcon.getImage();
    	return logo;
    }
    private Image loadNext(){
    	ImageIcon nextIcon = new ImageIcon(this.getClass().getResource("next.png"));
    	Image next = nextIcon.getImage();
    	return next;
    }
    private Image invertNext(){
    	ImageIcon nextIcon = new ImageIcon(this.getClass().getResource("nextinv.png"));
    	Image next = nextIcon.getImage();
    	return next;
    }
    private Image loadTitle(){
    	ImageIcon titleIcon = new ImageIcon(this.getClass().getResource("mockingjay.png"));
    	Image titlescreen = titleIcon.getImage();
    	return titlescreen;
    }
    private Image loadReaping(){
    	ImageIcon reapingIcon = new ImageIcon(this.getClass().getResource("reapingchosen.png"));
    	Image reaping = reapingIcon.getImage();
    	return reaping;
    }
    private Image loadReaping2(){
    	ImageIcon reapingIcon = new ImageIcon(this.getClass().getResource("reapingwaiting.png"));
    	Image reaping = reapingIcon.getImage();
    	return reaping;
    }
    private Image loadSadFace(){
    	ImageIcon sadfaceIcon = new ImageIcon(this.getClass().getResource("sadface.png"));
    	Image sadface = sadfaceIcon.getImage();
    	return sadface;
    }
    private Image loadSelectScreen(){
    	ImageIcon selectScreenIcon = new ImageIcon(this.getClass().getResource("selectscreen.png"));
    	Image SelectScreen = selectScreenIcon.getImage();
    	return SelectScreen;
    }
    private Image loadTaiga(){
    	ImageIcon gameBoardIcon = new ImageIcon(this.getClass().getResource("taigatile.png"));
    	Image gameBoard = gameBoardIcon.getImage();
    	return gameBoard;
    }
    private Image loadMountain(){
    	ImageIcon gameBoardIcon = new ImageIcon(this.getClass().getResource("mountainrange.png"));
    	Image gameBoard = gameBoardIcon.getImage();
    	return gameBoard;
    }
    private Image loadMonster(){
    	ImageIcon gameBoardIcon = new ImageIcon(this.getClass().getResource("monster.png"));
    	Image gameBoard = gameBoardIcon.getImage();
    	return gameBoard;
    }
    private Image loadBed(){
    	ImageIcon gameBoardIcon = new ImageIcon(this.getClass().getResource("bed.png"));
    	Image gameBoard = gameBoardIcon.getImage();
    	return gameBoard;
    }
    private Image loadSC(){
    	ImageIcon gameBoardIcon = new ImageIcon(this.getClass().getResource("sc.png"));
    	Image gameBoard = gameBoardIcon.getImage();
    	return gameBoard;
    }
    private Image loadLake(){
    	ImageIcon gameBoardIcon = new ImageIcon(this.getClass().getResource("lake.png"));
    	Image gameBoard = gameBoardIcon.getImage();
    	return gameBoard;
    }
    private Image loadEBottle(){
    	ImageIcon gameBoardIcon = new ImageIcon(this.getClass().getResource("emptybottle.png"));
    	Image gameBoard = gameBoardIcon.getImage();
    	return gameBoard;
    }
    private Image loadBottle(){
    	ImageIcon gameBoardIcon = new ImageIcon(this.getClass().getResource("bottle.png"));
    	Image gameBoard = gameBoardIcon.getImage();
    	return gameBoard;
    }
    private Image loadSword(){
    	ImageIcon gameBoardIcon = new ImageIcon(this.getClass().getResource("sword.png"));
    	Image gameBoard = gameBoardIcon.getImage();
    	return gameBoard;
    }
    private Image loadSwordOF(){
    	ImageIcon gameBoardIcon = new ImageIcon(this.getClass().getResource("swordoffire.png"));
    	Image gameBoard = gameBoardIcon.getImage();
    	return gameBoard;
    }
    private Image loadMedicine(){
    	ImageIcon gameBoardIcon = new ImageIcon(this.getClass().getResource("medicine.png"));
    	Image gameBoard = gameBoardIcon.getImage();
    	return gameBoard;
    }
    private Image loadSpear(){
    	ImageIcon gameBoardIcon = new ImageIcon(this.getClass().getResource("spear.png"));
    	Image gameBoard = gameBoardIcon.getImage();
    	return gameBoard;
    }
    private Image loadSpearOF(){
    	ImageIcon gameBoardIcon = new ImageIcon(this.getClass().getResource("spearoffire.png"));
    	Image gameBoard = gameBoardIcon.getImage();
    	return gameBoard;
    }
    private Image loadX(){
    	ImageIcon gameBoardIcon = new ImageIcon(this.getClass().getResource("x.png"));
    	Image gameBoard = gameBoardIcon.getImage();
    	return gameBoard;
    }
    private Image loadDarkTaiga(){
    	ImageIcon gameBoardIcon = new ImageIcon(this.getClass().getResource("taigatiledark.png"));
    	Image gameBoard = gameBoardIcon.getImage();
    	return gameBoard;
    }
    private Image loadDarkMountain(){
    	ImageIcon gameBoardIcon = new ImageIcon(this.getClass().getResource("mountainrangedark.png"));
    	Image gameBoard = gameBoardIcon.getImage();
    	return gameBoard;
    }
    private Image loadCoat(){
    	ImageIcon gameBoardIcon = new ImageIcon(this.getClass().getResource("coat.png"));
    	Image gameBoard = gameBoardIcon.getImage();
    	return gameBoard;
    }
    private Image loadQuiver(){
    	ImageIcon gameBoardIcon = new ImageIcon(this.getClass().getResource("quiver.png"));
    	Image gameBoard = gameBoardIcon.getImage();
    	return gameBoard;
    }
    private Image loadBow(){
    	ImageIcon gameBoardIcon = new ImageIcon(this.getClass().getResource("bow.png"));
    	Image gameBoard = gameBoardIcon.getImage();
    	return gameBoard;
    }
    private Image loadMeat(){
    	ImageIcon gameBoardIcon = new ImageIcon(this.getClass().getResource("steak.png"));
    	Image gameBoard = gameBoardIcon.getImage();
    	return gameBoard;
    }
    private Image loadBanana(){
    	ImageIcon gameBoardIcon = new ImageIcon(this.getClass().getResource("banana.png"));
    	Image gameBoard = gameBoardIcon.getImage();
    	return gameBoard;
    }
    private Image loadTomato(){
    	ImageIcon gameBoardIcon = new ImageIcon(this.getClass().getResource("tomato.png"));
    	Image gameBoard = gameBoardIcon.getImage();
    	return gameBoard;
    }
    private Image loadMango(){
    	ImageIcon gameBoardIcon = new ImageIcon(this.getClass().getResource("mango.png"));
    	Image gameBoard = gameBoardIcon.getImage();
    	return gameBoard;
    }
    private Image loadPoison(){
    	ImageIcon gameBoardIcon = new ImageIcon(this.getClass().getResource("poison.png"));
    	Image gameBoard = gameBoardIcon.getImage();
    	return gameBoard;
    }
    private Image loadCornucopia1(){
    	ImageIcon gameBoardIcon = new ImageIcon(this.getClass().getResource("cornucopia1.png"));
    	Image gameBoard = gameBoardIcon.getImage();
    	return gameBoard;
    }
    private Image loadCornucopia2(){
    	ImageIcon gameBoardIcon = new ImageIcon(this.getClass().getResource("cornucopia2.png"));
    	Image gameBoard = gameBoardIcon.getImage();
    	return gameBoard;
    }
    private Image loadCornucopia3(){
    	ImageIcon gameBoardIcon = new ImageIcon(this.getClass().getResource("cornucopia3.png"));
    	Image gameBoard = gameBoardIcon.getImage();
    	return gameBoard;
    }
    private Image loadCornucopia4(){
    	ImageIcon gameBoardIcon = new ImageIcon(this.getClass().getResource("cornucopia4.png"));
    	Image gameBoard = gameBoardIcon.getImage();
    	return gameBoard;
    }
    public Image loadIcon(){
    	ImageIcon gameBoardIcon = new ImageIcon(this.getClass().getResource("icon.png"));
    	Image gameBoard = gameBoardIcon.getImage();
    	return gameBoard;
    }
    private Image loadBoy(int boy){
    	String ending = Integer.toString(boy);
    	ImageIcon gameBoardIcon = new ImageIcon(this.getClass().getResource("boy" + ending + ".png"));
    	Image gameBoard = gameBoardIcon.getImage();
    	return gameBoard;
    }
    private Image loadGirl(int boy){
    	String ending = Integer.toString(boy);
    	ImageIcon gameBoardIcon = new ImageIcon(this.getClass().getResource("girl" + ending + ".png"));
    	Image gameBoard = gameBoardIcon.getImage();
    	return gameBoard;
    }
    private Image loadHBoy(){
    	ImageIcon gameBoardIcon = new ImageIcon(this.getClass().getResource("boyhighlighted.png"));
    	Image gameBoard = gameBoardIcon.getImage();
    	return gameBoard;
    }
    private Image loadHGirl(){
    	ImageIcon gameBoardIcon = new ImageIcon(this.getClass().getResource("girlhighlighted.png"));
    	Image gameBoard = gameBoardIcon.getImage();
    	return gameBoard;
    }
    private Image loadLoading(){
    	ImageIcon gameBoardIcon = new ImageIcon(this.getClass().getResource("loading.png"));
    	Image gameBoard = gameBoardIcon.getImage();
    	return gameBoard;
    }
    private Image loadBee(){
    	ImageIcon gameBoardIcon = new ImageIcon(this.getClass().getResource("bee.png"));
    	Image gameBoard = gameBoardIcon.getImage();
    	return gameBoard;
    }
    private Image loadMudMan(){
    	ImageIcon gameBoardIcon = new ImageIcon(this.getClass().getResource("mudman.png"));
    	Image gameBoard = gameBoardIcon.getImage();
    	return gameBoard;
    }
    private Image loadLizard(){
    	ImageIcon gameBoardIcon = new ImageIcon(this.getClass().getResource("lizard.png"));
    	Image gameBoard = gameBoardIcon.getImage();
    	return gameBoard;
    }
    private Image loadWolf(){
    	ImageIcon gameBoardIcon = new ImageIcon(this.getClass().getResource("wolf.png"));
    	Image gameBoard = gameBoardIcon.getImage();
    	return gameBoard;
    }
    private Image loadThug(){
    	ImageIcon gameBoardIcon = new ImageIcon(this.getClass().getResource("thug.png"));
    	Image gameBoard = gameBoardIcon.getImage();
    	return gameBoard;
    }
    private int x;
    private int y;
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void setPosition(Point mousePosition) {
        if (mousePosition != null) {
            x = mousePosition.x;
            y = mousePosition.y;
        }
    }
	public void mouseDragged(MouseEvent e) {
	}
	public void mouseMoved(MouseEvent e) {
		setPosition(getMousePosition());
		shouldinvert = false;
//		shouldinvert2 = false;
		invertleft = false;
		invertmiddle = false;
		invertright = false;
		invertx = false;
		invertuse = false;
		invertuse2 = false;
		invertReplay = false;
		invertuse3 = false;
		invertHistory = false;
		invertOptions = false;
		overPlayer = false;
		for(int i = 0; i < invertoptions.length; i++)
			invertoptions[i] = false;
		for(int i = 0; i < overItem.length; i++)
			overItem[i] = false;
		message = "mouse at " + getX() + " " + getY();
		if(drawWhat == 1){
			if(getX() >= 255 && getX() <= 430 && getY() >= 175 && getY() <= 250)shouldinvert = true;
//			if(getX() >= 255 && getX() <= 430 && getY() >= 375 && getY() <= 450)shouldinvert2 = true;
		}
		if(drawWhat == 2){
			if(getX() >= 562 && getX() <= 712 && getY() >= 610 && getY() <= 671)shouldinvert = true;
		}
		if(drawWhat == 4){
			for(int i = 0; i < 64; i++){
				if((canMoveTo[i] == true || canShootTo[i] == true) && getX() >= tilex[i] && getX() <= (tilex[i] + 61) && getY() >= tiley[i] && getY() <= (tiley[i] + 61) && showinventory == false)
					shouldOrange[i] = true;
				else{
					shouldOrange[i] = false;
				}
			}
			if(showinventory == true){
				for(int i = 0, x = 51, b = 176, width = 131; i < 8; i++, x += 131){
        			if(i == 2 || i == 3 || i == 6 || i == 7)
        				width = 130;
        			if(i == 0 || i == 1 || i == 4 || i == 5)
        				width = 131;
        			if(i == 4){
        				x = 51;
        				b = 276;
        			}
        			if(getX() >= x && getX() <= (x + width) && getY() >= b && getY() <= (b + 99))
        				overItem[i] = true;
				}
			}
    		if(getX() >= 550 && getX() <= 609 && getY() >= 645 && getY() <= 665)invertOptions = true;
    		if(getX() >= 550 && getX() <= 609 && getY() >= 615 && getY() <= 635)invertHistory = true;
			if(getX() >= 190 && getX() <= 315 && getY() >= 221 && getY() <= 283)invertReplay = true;
			if(getX() >= 577 && getX() <= 676 && getY() >= 254 && getY() <= 294)invertuse3 = true;
			if(getX() >= 577 && getX() <= 676 && getY() >= 295 && getY() <= 334)invertuse2 = true;
			if(getX() >= 577 && getX() <= 676 && getY() >= 335 && getY() <= 375)invertuse = true;
			if(showinventory == true && getX() >= 636 && getX() <= 671 && getY() >= 180 && getY() <= 215)invertx = true;
			if(showOptions == true && getX() >= 649 && getX() <= 684 && getY() >= 35 && getY() <= 70)invertx = true;
			if(showOptions == true && getX() >= 35 && getX() <= 215 && getY() >= 35 && getY() <= 85)invertoptions[0] = true;
			if(showOptions == true && getX() >= 235 && getX() <= 415 && getY() >= 35 && getY() <= 85)invertoptions[1] = true;
			if(showOptions == true && getX() >= 435 && getX() <= 615 && getY() >= 35 && getY() <= 85)invertoptions[2] = true;
			if(showOptions == true && getX() >= 35 && getX() <= 215 && getY() >= 95 && getY() <= 145)invertoptions[3] = true;
			if(showOptions == true && getX() >= 235 && getX() <= 415 && getY() >= 95 && getY() <= 145)invertoptions[4] = true;
			if(showOptions == true && getX() >= 435 && getX() <= 615 && getY() >= 95 && getY() <= 145)invertoptions[5] = true;
			if(showOptions == true && getX() >= 35 && getX() <= 215 && getY() >= 155 && getY() <= 205)invertoptions[6] = true;
			if(showHistory == true && getX() >= 649 && getX() <= 684 && getY() >= 35 && getY() <= 70)invertx = true;
			if(getX() >= 550 && getX() <= 691 && getY() >= 509 && getY() <= 549)shouldinvert = true;
			if(getX() >= 0 && getX() <= 168 && getY() >= 585 && getY() <= 635)invertleft = true;
			if(getX() >= 169 && getX() <= 337 && getY() >= 585 && getY() <= 635)invertmiddle = true;
			if(getX() >= 338 && getX() <= 504 && getY() >= 585 && getY() <= 635)invertright = true;
			int y1 = 0;
			int y2 = y1 + 20;
			for(int i = 0; i < 23; i++){
				if(getY() >= y1 && getY() <= y2 && getX() >= 550 && getX() <= 690 && showinventory == false && showOptions == false && showHistory == false){
					enemyShow[i] = true;
				}
				else{
					enemyShow[i] = false;
				}
				y1 += 22;
				y2 = y1 + 20;
			}
			if(getX() >= 550 && getX() <= 609 && getY() >= 554 && getY() <= 605)overPlayer = true;
		}
		if(drawWhat == 5){
    		if(getX() >= 50 && getX() <= 300 && getY() >= 400 && getY() <= 500)invertoptions[7] = true;
    		if(getX() >= 376 && getX() <= 626 && getY() >= 400 && getY() <= 500)invertoptions[8] = true;
		}
		canMoveTo(PlayerLocation);
		canSee(PlayerLocation);
		if(isStarting){
			canSee[15] = true;
			canSee[19] = true;
			canSee[44] = true;
			canSee[48] = true;
		}
		specialRepaint();
	}
	public void mouseClicked(MouseEvent e){
		check0();
		shouldinvert = false;
		if (e.getButton() == MouseEvent.BUTTON1) {
			if(drawWhat == 3){
				getCharacter();
				if(create.PlayerP > 0){
					drawWhat = 5;
					specialRepaint();
				}
			}
			if(drawWhat == 1){
				if(menuprogress == 0){
					getGameType();
					if(gameType != 2)
						menuprogress = 1;
					if(gameType == 2)
						drawWhat = 17;
			        specialRepaint();
				}
			}
			if(drawWhat == 5){
	    		if(getX() >= 50 && getX() <= 300 && getY() >= 400 && getY() <= 500)usingNumber = true;
	    		if(getX() >= 376 && getX() <= 626 && getY() >= 400 && getY() <= 500)usingNumber = false;
			}
			if(drawWhat == 4){
				if(getX() >= 190 && getX() <= 315 && getY() >= 221 && getY() <= 283 && PlayerAlive == false){
					drawWhat = 1;
					initializeArray();
					reaping = 0;
					nameshow = 0;
					create2 = 0;
					create3 = 0;
					menuprogress = 1;
					PlayerLocation = 65;
					movemade = false;
					isStarting = true;
					gameType = 0;
					gameMode = 0;
					turns = 0;
					daysnowater = 0;
					create.i = 1;
					create.PlayerP = 0;
					random.blank = 0;
					random.lakes = 0;
					random.supplyd = 0;
					random.killc = 0;
					random.sleepp = 0;
					deaths = 0;
					PlayerHealth = 50;
					inventoryI[0] = 1;
					for(int i = 1; i < 8; i++)
						inventoryI[i] = 0;
					for(int i = 0; i < 64; i++)
						random.buildings[i] = 0;
					for(int i = 0; i < 8; i++)
						random.biome[i] = false;
					PlayerAlive = true;
				}
				if(showinventory == true){
					for(int i = 0, x = 51, b = 176, width = 131; i < 8; i++, x += 131){
	        			if(i == 2 || i == 3 || i == 6 || i == 7)
	        				width = 130;
	        			if(i == 0 || i == 1 || i == 4 || i == 5)
	        				width = 131;
	        			if(i == 4){
	        				x = 51;
	        				b = 276;
	        			}
	        			if(getX() >= x && getX() <= (x + width) && getY() >= b && getY() <= (b + 99))
	        				selectinv[i] = !selectinv[i];
					}
				}
				if(showinventory == true && getX() >= 636 && getX() <= 671 && getY() >= 180 && getY() <= 215)showinventory = false;
				if(getX() >= 550 && getX() <= 691 && getY() >= 509 && getY() <= 549 && PlayerAlive == true && !isObserver)showinventory = true;
				if(showOptions == true && getX() >= 649 && getX() <= 684 && getY() >= 35 && getY() <= 70)showOptions = false;
				if(showOptions == true && getX() >= 35 && getX() <= 215 && getY() >= 155 && getY() <= 205){
					boolean madeChange = false;
					if(dataType == 2 && !madeChange){
						dataType = 0;
						madeChange = true;
					}
					if(dataType < 2 && !madeChange){
						dataType++;
						madeChange = true;
					}
					options.writeToOptions(showCoordinates, enableCheats, isObserver, isMusic, isLogging, dataType);
				}
				if(showOptions == true && getX() >= 435 && getX() <= 615 && getY() >= 95 && getY() <= 145){
					isLogging = !isLogging;
					options.writeToOptions(showCoordinates, enableCheats, isObserver, isMusic, isLogging, dataType);
				}
				if(showOptions == true && getX() >= 35 && getX() <= 215 && getY() >= 35 && getY() <= 85){
					showCoordinates = !showCoordinates;
					options.writeToOptions(showCoordinates, enableCheats, isObserver, isMusic, isLogging, dataType);
				}
				if(showOptions == true && getX() >= 435 && getX() <= 615 && getY() >= 35 && getY() <= 85){
					enableCheats = !enableCheats;
					options.writeToOptions(showCoordinates, enableCheats, isObserver, isMusic, isLogging, dataType);
				}
				if(showOptions == true && getX() >= 35 && getX() <= 215 && getY() >= 95 && getY() <= 145){
					isObserver = !isObserver;
					options.writeToOptions(showCoordinates, enableCheats, isObserver, isMusic, isLogging, dataType);
				}
				if(showOptions == true && getX() >= 235 && getX() <= 415 && getY() >= 35 && getY() <= 85){
					boolean ranCode = false;
					if(hasDiscoveredAll == false && ranCode == false){
//						if(PlayerLocation < 64){
							for(int p = 0; p < 64; p++)
								discovered[p] = true;
							hasDiscoveredAll = true;
//						}
//						if(PlayerLocation >= 64)
//							addToTextBox("You must move first before you can discover everything!");
						ranCode = true;
					}
					if(hasDiscoveredAll == true && ranCode == false){
						for(int p = 0; p < 64; p++)
							discovered[p] = false;
						hasDiscoveredAll = false;
						ranCode = true;
					}
				}
				if(showOptions == true && getX() >= 235 && getX() <= 415 && getY() >= 95 && getY() <= 145){
					isMusic = !isMusic;
					if(isMusic == false)
						TinySound.shutdown();
					if(isMusic == true){
						TinySound.init();
						soundplayer.sword = TinySound.loadSound(new File(".\\res\\sounds\\sword.wav"), true);
						percent = (100 / 7) * 1;
						soundplayer.bow = TinySound.loadSound(new File(".\\res\\sounds\\bow.wav"), true);
						percent = (100 / 7) * 2;
						soundplayer.death = TinySound.loadSound(new File(".\\res\\sounds\\death.wav"), true);
						percent = (100 / 7) * 3;
						soundplayer.eat = TinySound.loadSound(new File(".\\res\\sounds\\eat.wav"), true);
						percent = (100 / 7) * 4;
						soundplayer.slurp = TinySound.loadSound(new File(".\\res\\sounds\\slurp.wav"), true);
						percent = (100 / 7) * 5;
						soundplayer.spear = TinySound.loadSound(new File(".\\res\\sounds\\spear.wav"), true);
						percent = (100 / 7) * 6;
						soundplayer.punch = TinySound.loadSound(new File(".\\res\\sounds\\punch.wav"), true);
						percent = (100 / 7) * 7;
						soundplayer.startLoadingSound();
					}
					options.writeToOptions(showCoordinates, enableCheats, isObserver, isMusic, isLogging, dataType);
				}
				if(showHistory == true && getX() >= 649 && getX() <= 684 && getY() >= 35 && getY() <= 70)showHistory = false;
	    		if(getX() >= 550 && getX() <= 609 && getY() >= 645 && getY() <= 665)showOptions = true;
	    		if(getX() >= 550 && getX() <= 609 && getY() >= 615 && getY() <= 635)showHistory = true;
				if(showinventory == true && getX() >= 577 && getX() <= 676 && getY() >= 335 && getY() <= 375){
					for(int i = 0; i < 8; i++)
						if(selectinv[i] == true){
							if(inventoryI[i] == 1){
								if(bottletype[i] == 0){
									addToTextBox("You don't have anything to drink!");
								}
								if(bottletype[i] == 1){
									if(isMusic)
										soundplayer.slurp.play();
									addToTextBox("You drink the water in your bottle!");
									bottletype[i] = 0;
									daysnowater = 0;
								}
								if(bottletype[i] == 2){
									if(isMusic)
										soundplayer.slurp.play();
									addToTextBox("You drink the medicine in your bottle!");
									bottletype[i] = 0;
									isInfected = false;
									PlayerPoisoned = false;
								}
								if(bottletype[i] == 3){
									addToTextBox("You poison the items on the tile you're standing on!");
									bottletype[i] = 0;
									isPoisoned[PlayerLocation] = true;
								}
							}
							if(inventoryI[i] == 9){
								PlayerHealth = 50;
								addToTextBox("You replenished your health to max health by eating the meat!");
								if(isMusic)
									soundplayer.eat.play();
								inventoryI[i] = 0;
							}
							if(inventoryI[i] == 10 || inventoryI[i] == 11 || inventoryI[i] == 12){
								int healthDuplicate = PlayerHealth;
								for(int p = 0; p < 5; p++)
									if(PlayerHealth < 50)
										PlayerHealth++;
								int difference = PlayerHealth - healthDuplicate;
								addToTextBox("You increased your health by " + difference + " by eating the " + translateItem(inventoryI[i]) + "!");
								if(isMusic)
									soundplayer.eat.play();
								inventoryI[i] = 0;
							}
							selectinv[i] = false;
						}
				}
				if(showinventory == true && getX() >= 577 && getX() <= 676 && getY() >= 295 && getY() <= 334){
					for(int i = 0; i < 8; i++)
						if(selectinv[i] == true){
							if(PlayerLocation < 65){
								if(inventoryI[i] != 1){
									itemsOG[PlayerLocation] = rand.nextInt(100);
									if(itemsOG[PlayerLocation] >= 100)itemsOG[PlayerLocation] = 0;
									if(onGround[PlayerLocation][itemsOG[PlayerLocation]] > 0)itemsOG[PlayerLocation]++;
									if(itemsOG[PlayerLocation] < 100 && onGround[PlayerLocation][itemsOG[PlayerLocation]] == 0)
										onGround[PlayerLocation][itemsOG[PlayerLocation]] = inventoryI[i];
									addToTextBox("Player drops a " + translateItem(inventoryI[i]) + "!");
									inventoryI[i] = 0;
									selectinv[i] = false;
								}
								if(inventoryI[i] == 1 && bottletype[i] == 0){
									itemsOG[PlayerLocation] = rand.nextInt(100);
									if(itemsOG[PlayerLocation] >= 100)itemsOG[PlayerLocation] = 0;
									if(onGround[PlayerLocation][itemsOG[PlayerLocation]] > 0)itemsOG[PlayerLocation]++;
									if(itemsOG[PlayerLocation] < 100 && onGround[PlayerLocation][itemsOG[PlayerLocation]] == 0)
										onGround[PlayerLocation][itemsOG[PlayerLocation]] = inventoryI[i];
									addToTextBox("Player drops a " + translateItem(inventoryI[i]) + "!");
									inventoryI[i] = 0;
									selectinv[i] = false;
								}
								if(inventoryI[i] == 1 && bottletype[i] > 0){
									bottletype[i] = 0;
									addToTextBox("Player empties the liquid out of their bottle!");
									selectinv[i] = false;
								}
							}
							if(PlayerLocation >= 65)
								addToTextBox("You cannot drop until you are on the battlefield!");
						}
				}
				if(showinventory == true && getX() >= 577 && getX() <= 676 && getY() >= 254 && getY() <= 294){
					int items = 0;
					String report = "On the ground is ";
					int[] itemsI;
					int[] itemLocation;
					for(int k = 0; k < 100; k++){
						if(PlayerLocation < 65){
							if(onGround[PlayerLocation][k] > 0){
								items++;
							}
						}
					}
					itemsI = new int[items];
					itemLocation = new int[items];
					int[] numberOf = new int[12];
					for(int k = 0, y = 0; k < 100; k++){
						if(inventoryI[0] > 0 && inventoryI[1] > 0 && inventoryI[2] > 0 && inventoryI[3] > 0 && inventoryI[4] > 0 && inventoryI[5] > 0 && inventoryI[6] > 0 && inventoryI[7] > 0){
							addToTextBox("Inventory too full to pick up everything...");
						}
						if(PlayerLocation < 65){
							if(onGround[PlayerLocation][k] > 0){
								itemsI[y] = onGround[PlayerLocation][k];
								itemLocation[y] = k;
								numberOf[onGround[PlayerLocation][k] - 1]++;
								if(isPoisoned[PlayerLocation] == true){
									addToTextBox("There was poison on the items and you got poisoned!");
									PlayerPoisoned = true;
									isPoisoned[PlayerLocation] = false;
								}
								y++;
							}
						}
					}
					int last = 13;
					boolean two = false;
					for(int q = 0; q < 12; q++){
						if(numberOf[q] > 0 && two == true)
							last = q;
						if(numberOf[q] > 0 && two == false)
							two = true;
					}
					for(int q = 0; q < 12; q++){
						if(numberOf[q] > 0){
							if(last == q)report += " and ";
							if(numberOf[q] > 1)
								report += (numberOf[q] + " " + translateItemP(q + 1));
							if(numberOf[q] == 1)
								report += (numberOf[q] + " " + translateItem(q + 1));
							if(last != q)report += ", ";
						}
					}
					report += ".";
					if(report.equalsIgnoreCase("On the ground is ."))
						report = "On the ground is nothing.";
					if(PlayerLocation < 64)
						addToTextBox(report);
					for(int i = 0; i < items; i++){
						for(int k = 0; k < 8; k++){
							if(inventoryI[k] == 0){
								inventoryI[k] = itemsI[i];
								onGround[PlayerLocation][itemLocation[i]] = 0;
								break;
							}
						}
					}
					if(PlayerLocation < 65 && report.equalsIgnoreCase("On the ground is nothing.") == false)
						addToTextBox("Player picks up!");
					if(PlayerLocation >= 65)
						addToTextBox("You are not in a valid location to pick up items!");
				}
				if(getX() >= 0 && getX() <= 168 && getY() >= 585 && getY() <= 635 && PlayerAlive == true && isObserver == false){
					if(!movemade)
						addToTextBox("You must move before you can attack!");
					if(movemade){
						if(hasAttacked == true){
							addToTextBox("You have already attacked this turn!");
						}
						if(isAttacking == true){
							boolean attacked = true;
							int whichSlot = 8;
							spearLocation = 8;
							damage = create.PlayerSt / 2;
							for(int p = 0; p < 8; p++){
								if(selectinv[p] == true){
									whichSlot = p;
									break;
								}
							}
							if(whichSlot < 8){
								if(inventoryI[whichSlot] == 0){
									addToTextBox("You selected to attack with nothing, so you will attack with your fist!");
									addToTextBox("You attack with your fist!");
									if(isMusic)
										soundplayer.punch.play();
								}
								if(inventoryI[whichSlot] == 1){
									addToTextBox("You attack with your bottle, breaking it!");
									damage = 5;
									attackingweapon = 1;
									inventoryI[whichSlot] = 0;
								}
								if(inventoryI[whichSlot] == 2){
									addToTextBox("You attack with your sword!");
									if(isMusic)
										soundplayer.sword.play();
									attackingweapon = 2;
									damage = 10;
								}
								if(inventoryI[whichSlot] == 3){
									addToTextBox("You attack with your sword of fire!");
									if(isMusic)
										soundplayer.sword.play();
									attackingweapon = 3;
									damage = 15;
								}
								if(inventoryI[whichSlot] == 4){
									addToTextBox("Select which tile you'd like to throw your spear to or press attack to attack in your tile.");
									damage = 8;
									canShootTo(4);
									showinventory = false;
									attackingweapon = 4;
									spearLocation = whichSlot;
									attacked = false;
								}
								if(inventoryI[whichSlot] == 5){
									addToTextBox("Select which tile you'd like to throw your spear to or press attack to attack in your tile.");
									damage = 13;
									canShootTo(5);
									showinventory = false;
									attackingweapon = 5;
									spearLocation = whichSlot;
									attacked = false;
								}
								if(inventoryI[whichSlot] == 6){
									addToTextBox("You attack with your coat, breaking it!");
									damage = 0;
									attackingweapon = 6;
									inventoryI[whichSlot] = 0;
								}
								if(inventoryI[whichSlot] == 7){
									addToTextBox("You attack with your quiver, breaking it!");
									damage = 6;
									attackingweapon = 7;
									inventoryI[whichSlot] = 0;
								}
								if(inventoryI[whichSlot] == 8){
									boolean quiverPresent = false;
									for(int i = 0; i < 8; i++){
										if(inventoryI[i] == 7){
											quiverPresent = true;
											break;
										}
									}
									if(quiverPresent == false)
										addToTextBox("There is not a quiver present; you will attack with your fist!");
									if(quiverPresent == true){
										addToTextBox("Select which tile you'd like to shoot an arrow into.");
										canShootTo(8);
										damage = 10;
										attackingweapon = 8;
										showinventory = false;
										attacked = false;
									}
								}
								if(inventoryI[whichSlot] >= 9 && inventoryI[whichSlot] <= 12){
									addToTextBox("You attack with your food, destroying it!");
									damage = 1;
									attackingweapon = inventoryI[whichSlot];
									inventoryI[whichSlot] = 0;
								}
							}
							if(whichSlot == 8){
								addToTextBox("You didn't select an item to attack with, you will attack with your fist.");
								addToTextBox("You attack with your fist!");
								if(isMusic)
									soundplayer.punch.play();
							}
							if(attacked == true){
								hasAttacked = true;
								isAttacking = false;
								showinventory = false;
								int attackedperson = 0;
								int people = 0;
								int attackedtile = PlayerLocation;
								for(int y = 0; y < 23; y++)
									if(EnemyLocation[y] == attackedtile){
										if(attackedperson > 0){
											people++;
										}
										if(attackedperson == 0){
											attackedperson = y;
											people++;
										}
									}
								if(people == 0){
									addToTextBox("You missed!");
								}
								if(people == 1){
									System.out.println(EnemyHealth[attackedperson]);
									EnemyHealth[attackedperson] -= damage;
									playerDamageDealt += damage;
									if(attackingweapon == 3){
										EnemyInfected[attackedperson] = true;
										addToTextBox("You infected " + create.EnemyN[attackedperson] + "!");
									}
									System.out.println(EnemyHealth[attackedperson]);
									addToTextBox("You dealt " + damage + " damage to " + create.EnemyN[attackedperson] + "!");
									if(EnemyHealth[attackedperson] <= 0){
										EnemyAlive[attackedperson] = false;
										drawEnemy[attackedperson] = false;
										dropEnemyInventory(attackedperson);
										EnemyLocation[attackedperson] = 65;
										playerKills++;
										deaths++;
										System.out.println("Enemy " + create.EnemyN[attackedperson] + " dies of player attack.");
										addToTextBox("You killed " + create.EnemyN[attackedperson] + "!");
									}
								}
								if(people > 1){
									int[] peopleC = new int[people];
									for(int y = 0, w = 0; y < 23; y++)
										if(EnemyLocation[y] == attackedtile){
											peopleC[w] = y;
											w++;
										}
									int choice = rand.nextInt(people);
									System.out.println(EnemyHealth[peopleC[choice]]);
									EnemyHealth[peopleC[choice]] -= damage;
									playerDamageDealt += damage;
									if(attackingweapon == 3)
										EnemyInfected[attackedperson] = true;
									System.out.println(EnemyHealth[peopleC[choice]]);
									addToTextBox("You dealt " + damage + " damage to " + create.EnemyN[peopleC[choice]] + "!");
									if(EnemyHealth[peopleC[choice]] <= 0){
										EnemyAlive[peopleC[choice]] = false;
										drawEnemy[peopleC[choice]] = false;
										dropEnemyInventory(attackedperson);
										EnemyLocation[peopleC[choice]] = 65;
										playerKills++;
										deaths++;
										System.out.println("Enemy " + create.EnemyN[peopleC[choice]] + " dies of player attack.");
										addToTextBox("You killed " + create.EnemyN[peopleC[choice]] + "!");
									}
								}
							}
							if(whichSlot < 8)
								selectinv[whichSlot] = false;
						}
						if(isAttacking == false && hasAttacked == false){
							isAttacking = true;
							for(int i = 0; i < 8; i++)
								selectinv[i] = false;
							showinventory = true;
							addToTextBox("Select your weapon to attack with and then click attack.");
						}
					}
				}
				if(getX() >= 169 && getX() <= 337 && getY() >= 585 && getY() <= 635 && movemade == false && PlayerAlive == true){
					isMoving = true;
				}
				if(getX() >= 169 && getX() <= 337 && getY() >= 585 && getY() <= 635 && movemade == true && PlayerAlive == true){
					addToTextBox("You end your turn!");
					endTurn();
				}
				if(getX() >= 338 && getX() <= 504 && getY() >= 585 && getY() <= 635 && PlayerLocation < 65 && discovered[PlayerLocation] == true && PlayerAlive == true && !isObserver){
					if(random.buildings[PlayerLocation] == 1){
						for(int i = 0; i < 8; i++){
							if(inventoryI[i] == 1){
								if(bottletype[i] == 0){
									bottletype[i] = 1;
									break;
								}
							}
						}
						daysnowater = 0;
						if(isMusic)
							soundplayer.slurp.play();
						addToTextBox("You drink and refill your water bottle!");
					}
					if(random.buildings[PlayerLocation] == 2){
						for(int k = 0; k < random.supplyd; k++){
							if(whereSupplyD[k] == PlayerLocation){
								if(isScavenged[k] == true){
									addToTextBox("Supply drop already scavenged!");
								}
								if(isScavenged[k] == false){
									addToTextBox("You scavenge!");
									for(int i = 0, pro = 0; i < 8; i++){
										if(inventoryI[i] == 0){
											if(SupplyDCap[k] > 0){
												inventoryI[i] = supplies[k][pro];
												if(inventoryI[i] == 1){
													int type = rand.nextInt(2);
													if(type == 0){
														bottletype[i] = 2;
														addToTextBox("You scavenge medicine!");
													}
													if(type == 1){
														bottletype[i] = 3;
														addToTextBox("You scavenge poison!");
													}
												}
												if(inventoryI[i] > 1)
													addToTextBox("You scavenge a " + translateItem(inventoryI[i]) + "!");
											}
											pro++;
											SupplyDCap[k]--;
											if(SupplyDCap[k] == 0)isScavenged[k] = true;
										}
										if(inventoryI[0] > 0 && inventoryI[1] > 0 && inventoryI[2] > 0 && inventoryI[3] > 0 && inventoryI[4] > 0 && inventoryI[5] > 0 && inventoryI[6] > 0 && inventoryI[7] > 0 && isScavenged[k] == false){
											addToTextBox("Please drop an item to scavenge this.  You can always just ignore the supply drop though.");
											addToTextBox("Please note that there " + SupplyDCap[k] + " items in this supply drop.");
											break;
										}
									}
								}
							}
						}
					}
					if(random.buildings[PlayerLocation] == 4){
//						turns += 2;
//						daysnowater++;
						if(PlayerHealth < 50)
							PlayerHealth++;
//						movemade = false;
						addToTextBox("You sleep for a day and feel ready to continue on!");
						endTurn();
//						int turnsmax = 7;
//						for(int k = 0; k < 4; k++){
//							if(random.biomeint[k] == 4){
//								int start = 16 * k;
//								int end = start + 15;
//								if(PlayerLocation >= start && PlayerLocation <= end){
//									turnsmax = 4;
//								}
//								else{
//									turnsmax = 7;
//								}
//							}
//						}
//						int daysleft = turnsmax - daysnowater;
//						if(daysleft > 0 && daysleft != 1)
//							addToTextBox("You have " + daysleft + " days left of water.");
//						if(daysleft > 0 && daysleft == 1)
//							addToTextBox("You have " + daysleft + " day left of water.");
//						if(daysnowater >= turnsmax){
//							if(isMusic)
//								soundplayer.death.play();
//							PlayerAlive = false;
//							addToTextBox("You died of not drinking enough!");
//						}
					}
				}
				if(getX() >= 338 && getX() <= 504 && getY() >= 585 && getY() <= 635 && PlayerLocation < 65 && PlayerAlive == true && !isObserver){
					if(!discovered[PlayerLocation] && PlayerLocation != 15 && PlayerLocation != 19 && PlayerLocation != 44 && PlayerLocation != 48)
						addToTextBox("You discover!");
					Discover(PlayerLocation, true, 0);
				}
				if(getX() >= 338 && getX() <= 504 && getY() >= 585 && getY() <= 635 && PlayerLocation >= 65 && !isObserver)
					addToTextBox("Cannot discover yet!");
				for(int i = 0; i < 64; i++)
					if(shouldOrange[i] == true && PlayerAlive == true && showinventory == false && isMoving == true){
						int locationcopy = PlayerLocation;
						if(locationcopy >= 0 && locationcopy <= 15)locationcopy = random.biomeint[0];
						if(locationcopy >= 16 && locationcopy <= 31)locationcopy = random.biomeint[1];
						if(locationcopy >= 32 && locationcopy <= 47)locationcopy = random.biomeint[2];
						if(locationcopy >= 48 && locationcopy <= 63)locationcopy = random.biomeint[3];
						addToTextBox("You move!");
						isMoving = false;
						PlayerLocation = i;
						isStarting = false;
						movemade = true;
						int start = 0;
						for(int k = 0; k < 4; k++){
							if(random.biomeint[k] == 4)
								for(int p = start; p < start + 16; p++){
									if((PlayerLocation >= start && PlayerLocation <= start + 16) && locationcopy != 4){
										boolean inCornucopia = false;
										if(PlayerLocation >= 0 && PlayerLocation <= 15)if(PlayerLocation == 15)inCornucopia = true;
										if(PlayerLocation >= 16 && PlayerLocation <= 31)if(PlayerLocation == 19)inCornucopia = true;
										if(PlayerLocation >= 32 && PlayerLocation <= 47)if(PlayerLocation == 44)inCornucopia = true;
										if(PlayerLocation >= 48 && PlayerLocation <= 63)if(PlayerLocation == 48)inCornucopia = true;
										if(daysnowater >= 4 && !inCornucopia)
											addToTextBox("You are entering a desert.  You'll need to drink water this turn or else you'll die!");
										break;
									}
								}
							start += 16;
						}
					}
				for(int i = 0; i < 64; i++)
					if(shouldOrange[i] == true && PlayerAlive == true && showinventory == false && isAttacking == true && hasAttacked == false){
						int attackTarget = i;
						if(attackingweapon == 4 || attackingweapon == 5 || attackingweapon == 8){
							boolean miss = false;
							int missprobability = rand.nextInt(create.PlayerWs);
							if(create.PlayerWs < 2)missprobability = rand.nextInt(2);
							if(missprobability == 0)
								miss = true;
							if(attackingweapon == 4 || attackingweapon == 5){
								if(attackTarget != PlayerLocation)
									inventoryI[spearLocation] = 0;
								if(attackTarget == PlayerLocation)
									miss = false;
							}
							if(miss == false){
								if(isMusic){
									if(attackingweapon == 4 || attackingweapon == 5)
										soundplayer.spear.play();
									if(attackingweapon == 8)
										soundplayer.bow.play();
								}
								int attackedperson = 0;
								int people = 0;
								for(int y = 0; y < 23; y++)
									if(EnemyLocation[y] == attackTarget){
										if(attackedperson > 0){
											people++;
										}
										if(attackedperson == 0){
											attackedperson = y;
											people++;
										}
									}
									if(people == 0){
										addToTextBox("You missed!");
									}
									if(people == 1){
										System.out.println(EnemyHealth[attackedperson]);
										EnemyHealth[attackedperson] -= damage;
										playerDamageDealt += damage;
										if(attackingweapon == 5){
											EnemyInfected[attackedperson] = true;
											addToTextBox("You infected " + create.EnemyN[attackedperson] + "!");
										}
										System.out.println(EnemyHealth[attackedperson]);
										addToTextBox("You dealt " + damage + " damage to " + create.EnemyN[attackedperson] + "!");
										if(EnemyHealth[attackedperson] <= 0){
											EnemyAlive[attackedperson] = false;
											drawEnemy[attackedperson] = false;
											dropEnemyInventory(attackedperson);
											EnemyLocation[attackedperson] = 65;
											deaths++;
											System.out.println("Enemy " + create.EnemyN[attackedperson] + " dies of player attack.");
											addToTextBox("You killed " + create.EnemyN[attackedperson] + "!");
										}
									}
									if(people > 1){
										int[] peopleC = new int[people];
										for(int y = 0, w = 0; y < 23; y++)
											if(EnemyLocation[y] == attackTarget){
												peopleC[w] = y;
												w++;
											}
										int choice = rand.nextInt(people);
										System.out.println(EnemyHealth[peopleC[choice]]);
										EnemyHealth[peopleC[choice]] -= damage;
										playerDamageDealt += damage;
										if(attackingweapon == 5)
											EnemyInfected[attackedperson] = true;
										System.out.println(EnemyHealth[peopleC[choice]]);
										addToTextBox("You dealt " + damage + " damage to " + create.EnemyN[peopleC[choice]] + "!");
										if(EnemyHealth[peopleC[choice]] <= 0){
											EnemyAlive[peopleC[choice]] = false;
											drawEnemy[peopleC[choice]] = false;
											dropEnemyInventory(peopleC[choice]);
											EnemyLocation[peopleC[choice]] = 65;
											playerKills++;
											deaths++;
											System.out.println("Enemy " + create.EnemyN[peopleC[choice]] + " dies of player attack.");
											addToTextBox("You killed " + create.EnemyN[peopleC[choice]] + "!");
										}
									}
							}
							if(miss == true){
								addToTextBox("You missed!");
							}
							hasAttacked = true;
							isAttacking = false;
						}
					}
			}
			if(drawWhat == 2){
				if(getX() >= 562 && getX() <= 712 && getY() >= 610 && getY() <= 671){
					drawWhat = 4;
					runEnemyAI();
					getShading();
					canSee(PlayerLocation);
				}
			}
		}
		canMoveTo(PlayerLocation);
		canSee(PlayerLocation);
		specialRepaint();
	}
	public void mouseEntered(MouseEvent e) {
		specialRepaint();
	}
	public void mouseExited(MouseEvent e) {
		specialRepaint();
	}
	public void mousePressed(MouseEvent e) {
		specialRepaint();
	}
	public void mouseReleased(MouseEvent e) {
		check0();
		specialRepaint();
	}
	public int getGameMode(byte gamemode){
		byte number = gamemode;
        if(number == 1)drawWhat = 3;
        if(number == 2)drawWhat = 2;
		if(number == 2 && create2 == 0){
			create.RunCode();
			create2++;
			initializeText();
			initializeEnemies();
		}
		gameMode = number;
		return number;
	}
	private int getGameType(){
		byte number = 0;
		if(drawWhat == 1){
			if(getX() >= 255 && getX() <= 430 && getY() >= 175 && getY() <= 250)number = 1;
			if(getX() >= 255 && getX() <= 430 && getY() >= 375 && getY() <= 450)number = 2;
		}
		gameType = number;
		return number;
	}
	private int getCharacter(){
		int character = 0;
		if(drawWhat == 3){
			if(getX() >= 1 && getX() <= 120 && getY() >= 0 && getY() <= 151)character = 1;
			if(getX() >= 121 && getX() <= 239 && getY() >= 0 && getY() <= 151)character = 2;
			if(getX() >= 240 && getX() <= 358 && getY() >= 0 && getY() <= 151)character = 3;
			if(getX() >= 359 && getX() <= 477 && getY() >= 0 && getY() <= 151)character = 4;
			if(getX() >= 478 && getX() <= 596 && getY() >= 0 && getY() <= 151)character = 5;
			if(getX() >= 597 && getX() <= 718 && getY() >= 0 && getY() <= 151)character = 6;
			if(getX() >= 1 && getX() <= 120 && getY() >= 152 && getY() <= 302)character = 7;
			if(getX() >= 121 && getX() <= 239 && getY() >= 152 && getY() <= 302)character = 8;
			if(getX() >= 240 && getX() <= 358 && getY() >= 152 && getY() <= 302)character = 9;
			if(getX() >= 359 && getX() <= 477 && getY() >= 152 && getY() <= 302)character = 10;
			if(getX() >= 478 && getX() <= 596 && getY() >= 152 && getY() <= 302)character = 11;
			if(getX() >= 597 && getX() <= 718 && getY() >= 152 && getY() <= 302)character = 12;
			if(getX() >= 1 && getX() <= 120 && getY() >= 303 && getY() <= 452)character = 13;
			if(getX() >= 121 && getX() <= 239 && getY() >= 303 && getY() <= 452)character = 14;
			if(getX() >= 240 && getX() <= 358 && getY() >= 303 && getY() <= 452)character = 15;
			if(getX() >= 359 && getX() <= 477 && getY() >= 303 && getY() <= 452)character = 16;
			if(getX() >= 478 && getX() <= 596 && getY() >= 303 && getY() <= 452)character = 17;
			if(getX() >= 597 && getX() <= 718 && getY() >= 303 && getY() <= 452)character = 18;
			if(getX() >= 1 && getX() <= 120 && getY() >= 453 && getY() <= 600)character = 19;
			if(getX() >= 121 && getX() <= 239 && getY() >= 453 && getY() <= 600)character = 20;
			if(getX() >= 240 && getX() <= 358 && getY() >= 453 && getY() <= 600)character = 21;
			if(getX() >= 359 && getX() <= 477 && getY() >= 453 && getY() <= 600)character = 22;
			if(getX() >= 478 && getX() <= 596 && getY() >= 453 && getY() <= 600)character = 23;
			if(getX() >= 597 && getX() <= 718 && getY() >= 453 && getY() <= 600)character = 24;
		}
		create.PlayerP = character;
		return character;
	}
	private void specialRepaint(){
    	x = -50;
    	y = -50;
    	repaint();
    	x = 0;
    	y = 0;
    	repaint();
	}
	private void check0(){
		if(getX() == 0 || getY() == 0)setPosition(getMousePosition());
	}
//	private String convertGameMode(){
//		String gamemode = null;
//		if(gameMode == 1)gamemode = "Character Select";
//		if(gameMode == 2)gamemode = "Random Character Select";
//		if(gameMode != 1 && gameMode != 2)gamemode = "Game mode unknown.";
//		return gamemode;
//	}
//	private String convertGameType(){
//		String gametype = null;
//		if(gameType == 1)gametype = "Single Player";
//		if(gameType == 2)gametype = "Multiplayer";
//		if(gameType != 1 && gameType != 2)gametype = "Game type unknown.";
//		return gametype;
//	}
	public void delaytime(){
        int delay = 0200;
        Timer timer = new Timer();
        timer.schedule(new TimerTask(){
        	public void run(){
                nameshow = 1;
                specialRepaint();
        	}
        }, delay);
   }
	public void addToText(int number){
		boolean gender = create.getGenderBoolean(create.PlayerP);
		if(number == 10){
			numberS = "";
			digit = 1;
		}
		if(number == 11){
			if(numberS != "")
				create.PlayerNN = Integer.parseInt(numberS);
			if(numberS == "")
				create.PlayerNN = rand.nextInt(600) + 1;
			if(create.PlayerNN > name.getHighest(gender) || create.PlayerNN < 1){
				addToText(10);
				badnumber = true;
			}
			if(create.PlayerNN <= name.getHighest(gender) && create.PlayerNN >= 1){
				drawWhat = 2;
				if(create3 == 0){
					create.RunCode();
					create3++;
					initializeText();
					initializeEnemies();
				}
			}
		}
		if(digit < 4 && number < 10){
			badnumber = false;
			numberS += number;
			digit++;
		}
		specialRepaint();
	}
	public void getShading(){
		random.getBiomes();
		random.getBuildings();
		shading[0] = random.shading[1];
		biome[0] = random.biomeint[0];
		shading[1] = random.shading[2];
		biome[1] = random.biomeint[1];
		shading[2] = random.shading[3];
		biome[2] = random.biomeint[2];
		shading[3] = random.shading[4];
		biome[3] = random.biomeint[3];
		isScavenged = new boolean[random.supplyd];
		whereSupplyD = new int[random.supplyd];
		SupplyDCap = new int[random.supplyd];
		supplies = new int[random.supplyd][3];
		for(int i = 0, k = 0; k < 64; k++){
			if(random.buildings[k] == 2){
				whereSupplyD[i] = k;
				i++;
			}
		}
		for(int i = 0; i < random.supplyd; i++){
			SupplyDCap[i] = getSupplyCapacity();
			supplies[i] = getSupplies(SupplyDCap[i]);
		}
	}
	public void initializeArray(){
//		for(int y = 0; y < 8; y++)
//			inventoryI[y] = 1;
		boolean[] option = options.getOptions();
		showCoordinates = option[0];
		enableCheats = option[1];
		isObserver = option[2];
		isMusic = option[3];
		isLogging = option[4];
		byte datatype = options.getDataType();
		dataType = datatype;
		short gc = options.getGC();
		GC = gc;
		System.out.println("GC: " + GC);
		if(starting == true){
			autoGC();
			if(isMusic){
				TinySound.init();
				soundplayer.sword = TinySound.loadSound(new File(".\\res\\sounds\\sword.wav"), true);
				percent = (100 / 7) * 1;
				soundplayer.bow = TinySound.loadSound(new File(".\\res\\sounds\\bow.wav"), true);
				percent = (100 / 7) * 2;
				soundplayer.death = TinySound.loadSound(new File(".\\res\\sounds\\death.wav"), true);
				percent = (100 / 7) * 3;
				soundplayer.eat = TinySound.loadSound(new File(".\\res\\sounds\\eat.wav"), true);
				percent = (100 / 7) * 4;
				soundplayer.slurp = TinySound.loadSound(new File(".\\res\\sounds\\slurp.wav"), true);
				percent = (100 / 7) * 5;
				soundplayer.spear = TinySound.loadSound(new File(".\\res\\sounds\\spear.wav"), true);
				percent = (100 / 7) * 6;
				soundplayer.punch = TinySound.loadSound(new File(".\\res\\sounds\\punch.wav"), true);
				percent = (100 / 7) * 7;
				soundplayer.startLoadingSound();
			}
		}
		inventoryI[0] = 1;
		for(int i = 0; i < 64; i++){
			canMoveTo[i] = false;
			shouldOrange[i] = false;
			canShootTo[i] = false;
			itemsOG[i] = 101;
			discovered[i] = false;
		}
		for(int i = 0; i < 23; i++){
			enemyShow[i] = false;
			EnemyAlive[i] = true;
			EnemyHealth[i] = 50;
			EnemyLocation[i] = 65;
			int betterprobability = rand.nextInt(23);
			boolean runaway = false;
			if(betterprobability > 8)
				runaway = true;
			runAway[i] = runaway;
			EnemycoldDaysLeft[i] = 7;
			Enemydaysnowater[i] = 0;
			EnemyInfected[i] = false;
			EnemyPoisoned[i] = false;
			EnemyKills[i] = 0;
			EnemyDamageDealt[i] = 0;
			EnemyDamageTaken[i] = 0;
			for(int y = 0; y < 8; y++){
				EnemyInventory[i][y] = 0;
				enemyBottleType[i][y] = 0;
				enemyDiscovered[i][y] = false;
			}
		}
		for(int i = 0; i < 25; i++)
			history[i] = "";
		for(int i = 0; i < 30; i++)
			time[i] = "";
		for(int i = 0; i < 8; i++)
			bottletype[i] = 0;
		for(int i = 0; i < 64; i++)
			for(int o = 0; o < 100; o++)
				onGround[i][o] = 0;
		for(int i = 0; i < 5; i++)
			text[i] = "";
		for(int i = 0; i < 25; i++)
			history[i] = "";
		for(int i = 0; i < 30; i++)
			time[i] = "";
		if(!starting){
			drawWhat = 1;
		}
		if(starting){
			drawWhat = 7;
			logoDelay();
			starting = false;
		}
	}
	private void initializeEnemies(){
		for(int i = 0; i < 23; i++){
			int decider = rand.nextInt(2);
			boolean discoverAll = false;
			if(create.getGenderBoolean(create.PlayerP) == true)
				if(create.PlayerP - 1 == i){
					decider = 1;
					discoverAll = true;
				}
			if(create.getGenderBoolean(create.PlayerP) == false)
				if(create.PlayerP - 2 == i){
					decider = 1;
					discoverAll = true;
				}
			if(decider == 1){
				int decider2 = rand.nextInt(4);
				if(decider2 == 0)
					enemySmDis[i] = true;
				if(decider2 == 1)
					enemyStDis[i] = true;
				if(decider2 == 2)
					enemySpDis[i] = true;
				if(decider2 == 3)
					enemyWsDis[i] = true;
				if(discoverAll == true){
					enemySmDis[i] = true;
					enemyStDis[i] = true;
					enemySpDis[i] = true;
					enemyWsDis[i] = true;
				}
			}
		}
	}
	public void initializeText(){
		addToTextBox("Player " + create.PlayerN + " from district " + create.PlayerD + " who is a " + create.PlayerG + " created.");
		time[0] = getDate.getRealDateTime();
		addToTextBox("Player total rating of " + create.getTotalSkill(create.PlayerSt, create.PlayerSp, create.PlayerSm, create.PlayerWs) + " with strength of " + create.PlayerSt + ", speed of " + create.PlayerSp + ", smarts of " + create.PlayerSm + ", and " + "weapon skill of " + create.PlayerWs + ".");
		time[1] = getDate.getRealDateTime();
		time[2] = getDate.getRealDateTime();
		addToTextBox("Select your start area.  If an area is yellow, you can move to it.");
		time[3] = getDate.getRealDateTime();
		addToTextBox("If you hover over an area that you can move to, it will turn orange.");
		time[4] = getDate.getRealDateTime();
	}
	private void addToTextBox(String addition){
		if(text[0] != ""){
			if(doesOverflow(addition)){
				addToHistory(text[0]);
				addToHistory(text[1]);
			}
			if(!doesOverflow(addition))
				addToHistory(text[0]);
		}
		if(doesOverflow(addition)){
			String[] add = new String[2];
			int endstart = 63;
			while(addition.charAt(endstart) != ' '){
				endstart--;
				if(addition.charAt(endstart) == ' ')
					break;
			}
			add[0] = addition.substring(0, endstart);
			add[1] = addition.substring(endstart + 1, addition.length());
			text[0] = text[2];
			time[0] = time[2];
			text[1] = text[3];
			time[1] = time[3];
			text[2] = text[4];
			time[2] = time[4];
			text[3] = add[0];
			time[3] = getDate.getRealDateTime();
			text[4] = add[1];
			time[4] = getDate.getRealDateTime();
		}
		if(!doesOverflow(addition)){
			text[0] = text[1];
			time[0] = time[1];
			text[1] = text[2];
			time[1] = time[2];
			text[2] = text[3];
			time[2] = time[3];
			text[3] = text[4];
			time[3] = time[4];
			text[4] = addition;
			time[4] = getDate.getRealDateTime();
		}
	}
	private void addToHistory(String addition){
		for(int p = 0; p < 24; p++){
			history[p] = history[p + 1];
			time[p + 5] = time[p + 6];
		}
		history[24] = addition;
		time[29] = time[0];
	}
	private void canMoveTo(int location){
		if(isStarting == true && isMoving == true){
			canMoveTo[15] = true;
			canMoveTo[19] = true;
			canMoveTo[44] = true;
			canMoveTo[48] = true;
		}
		else{
			for(int i = 0; i < 64; i++){
				canMoveTo[i] = false;
			}
			if(location == 0){
				canMoveTo[0] = true;
				canMoveTo[1] = true;
				canMoveTo[4] = true;
				canMoveTo[5] = true;
			}
			if(location == 1){
				canMoveTo[0] = true;
				canMoveTo[1] = true;
				canMoveTo[2] = true;
				canMoveTo[4] = true;
				canMoveTo[5] = true;
				canMoveTo[6] = true;
			}
			if(location == 2){
				canMoveTo[1] = true;
				canMoveTo[2] = true;
				canMoveTo[3] = true;
				canMoveTo[5] = true;
				canMoveTo[6] = true;
				canMoveTo[7] = true;
			}
			if(location == 3){
				canMoveTo[2] = true;
				canMoveTo[3] = true;
				canMoveTo[32] = true;
				canMoveTo[6] = true;
				canMoveTo[7] = true;
				canMoveTo[36] = true;
			}
			if(location == 4){
				canMoveTo[0] = true;
				canMoveTo[1] = true;
				canMoveTo[4] = true;
				canMoveTo[5] = true;
				canMoveTo[8] = true;
				canMoveTo[9] = true;
			}
			if(location == 5){
				canMoveTo[0] = true;
				canMoveTo[1] = true;
				canMoveTo[2] = true;
				canMoveTo[4] = true;
				canMoveTo[5] = true;
				canMoveTo[6] = true;
				canMoveTo[8] = true;
				canMoveTo[9] = true;
				canMoveTo[10] = true;
			}
			if(location == 6){
				canMoveTo[1] = true;
				canMoveTo[2] = true;
				canMoveTo[3] = true;
				canMoveTo[5] = true;
				canMoveTo[6] = true;
				canMoveTo[7] = true;
				canMoveTo[9] = true;
				canMoveTo[10] = true;
				canMoveTo[11] = true;
			}
			if(location == 7){
				canMoveTo[2] = true;
				canMoveTo[3] = true;
				canMoveTo[32] = true;
				canMoveTo[6] = true;
				canMoveTo[7] = true;
				canMoveTo[36] = true;
				canMoveTo[10] = true;
				canMoveTo[11] = true;
				canMoveTo[40] = true;
			}
			if(location == 8){
				canMoveTo[4] = true;
				canMoveTo[5] = true;
				canMoveTo[8] = true;
				canMoveTo[9] = true;
				canMoveTo[12] = true;
				canMoveTo[13] = true;
			}
			if(location == 9){
				canMoveTo[4] = true;
				canMoveTo[5] = true;
				canMoveTo[6] = true;
				canMoveTo[8] = true;
				canMoveTo[9] = true;
				canMoveTo[10] = true;
				canMoveTo[12] = true;
				canMoveTo[13] = true;
				canMoveTo[14] = true;
			}
			if(location == 10){
				canMoveTo[5] = true;
				canMoveTo[6] = true;
				canMoveTo[7] = true;
				canMoveTo[9] = true;
				canMoveTo[10] = true;
				canMoveTo[11] = true;
				canMoveTo[13] = true;
				canMoveTo[14] = true;
				canMoveTo[15] = true;
			}
			if(location == 11){
				canMoveTo[6] = true;
				canMoveTo[7] = true;
				canMoveTo[36] = true;
				canMoveTo[10] = true;
				canMoveTo[11] = true;
				canMoveTo[40] = true;
				canMoveTo[14] = true;
				canMoveTo[15] = true;
				canMoveTo[44] = true;
			}
			if(location == 12){
				canMoveTo[8] = true;
				canMoveTo[9] = true;
				canMoveTo[12] = true;
				canMoveTo[13] = true;
				canMoveTo[16] = true;
				canMoveTo[17] = true;
			}
			if(location == 13){
				canMoveTo[8] = true;
				canMoveTo[9] = true;
				canMoveTo[10] = true;
				canMoveTo[12] = true;
				canMoveTo[13] = true;
				canMoveTo[14] = true;
				canMoveTo[16] = true;
				canMoveTo[17] = true;
				canMoveTo[18] = true;
			}
			if(location == 14){
				canMoveTo[9] = true;
				canMoveTo[10] = true;
				canMoveTo[11] = true;
				canMoveTo[13] = true;
				canMoveTo[14] = true;
				canMoveTo[15] = true;
				canMoveTo[17] = true;
				canMoveTo[18] = true;
				canMoveTo[19] = true;
			}
			if(location == 15){
				canMoveTo[10] = true;
				canMoveTo[11] = true;
				canMoveTo[40] = true;
				canMoveTo[14] = true;
				canMoveTo[15] = true;
				canMoveTo[44] = true;
				canMoveTo[18] = true;
				canMoveTo[19] = true;
				canMoveTo[48] = true;
			}
			if(location == 16){
				canMoveTo[12] = true;
				canMoveTo[13] = true;
				canMoveTo[16] = true;
				canMoveTo[17] = true;
				canMoveTo[20] = true;
				canMoveTo[21] = true;
			}
			if(location == 17){
				canMoveTo[12] = true;
				canMoveTo[13] = true;
				canMoveTo[14] = true;
				canMoveTo[16] = true;
				canMoveTo[17] = true;
				canMoveTo[18] = true;
				canMoveTo[20] = true;
				canMoveTo[21] = true;
				canMoveTo[22] = true;
			}
			if(location == 18){
				canMoveTo[13] = true;
				canMoveTo[14] = true;
				canMoveTo[15] = true;
				canMoveTo[17] = true;
				canMoveTo[18] = true;
				canMoveTo[19] = true;
				canMoveTo[21] = true;
				canMoveTo[22] = true;
				canMoveTo[23] = true;
			}
			if(location == 19){
				canMoveTo[14] = true;
				canMoveTo[15] = true;
				canMoveTo[44] = true;
				canMoveTo[18] = true;
				canMoveTo[19] = true;
				canMoveTo[48] = true;
				canMoveTo[22] = true;
				canMoveTo[23] = true;
				canMoveTo[52] = true;
			}
			if(location == 20){
				canMoveTo[16] = true;
				canMoveTo[17] = true;
				canMoveTo[20] = true;
				canMoveTo[21] = true;
				canMoveTo[24] = true;
				canMoveTo[25] = true;
			}
			if(location == 21){
				canMoveTo[16] = true;
				canMoveTo[17] = true;
				canMoveTo[18] = true;
				canMoveTo[20] = true;
				canMoveTo[21] = true;
				canMoveTo[22] = true;
				canMoveTo[24] = true;
				canMoveTo[25] = true;
				canMoveTo[26] = true;
			}
			if(location == 22){
				canMoveTo[17] = true;
				canMoveTo[18] = true;
				canMoveTo[19] = true;
				canMoveTo[21] = true;
				canMoveTo[22] = true;
				canMoveTo[23] = true;
				canMoveTo[25] = true;
				canMoveTo[26] = true;
				canMoveTo[27] = true;
			}
			if(location == 23){
				canMoveTo[18] = true;
				canMoveTo[19] = true;
				canMoveTo[48] = true;
				canMoveTo[22] = true;
				canMoveTo[23] = true;
				canMoveTo[52] = true;
				canMoveTo[26] = true;
				canMoveTo[27] = true;
				canMoveTo[56] = true;
			}
			if(location == 24){
				canMoveTo[20] = true;
				canMoveTo[21] = true;
				canMoveTo[24] = true;
				canMoveTo[25] = true;
				canMoveTo[28] = true;
				canMoveTo[29] = true;
			}
			if(location == 25){
				canMoveTo[20] = true;
				canMoveTo[21] = true;
				canMoveTo[22] = true;
				canMoveTo[24] = true;
				canMoveTo[25] = true;
				canMoveTo[26] = true;
				canMoveTo[28] = true;
				canMoveTo[29] = true;
				canMoveTo[30] = true;
			}
			if(location == 26){
				canMoveTo[21] = true;
				canMoveTo[22] = true;
				canMoveTo[23] = true;
				canMoveTo[25] = true;
				canMoveTo[26] = true;
				canMoveTo[27] = true;
				canMoveTo[29] = true;
				canMoveTo[30] = true;
				canMoveTo[31] = true;
			}
			if(location == 27){
				canMoveTo[22] = true;
				canMoveTo[23] = true;
				canMoveTo[52] = true;
				canMoveTo[26] = true;
				canMoveTo[27] = true;
				canMoveTo[56] = true;
				canMoveTo[30] = true;
				canMoveTo[31] = true;
				canMoveTo[60] = true;
			}
			if(location == 28){
				canMoveTo[24] = true;
				canMoveTo[25] = true;
				canMoveTo[28] = true;
				canMoveTo[29] = true;
			}
			if(location == 29){
				canMoveTo[24] = true;
				canMoveTo[25] = true;
				canMoveTo[26] = true;
				canMoveTo[28] = true;
				canMoveTo[29] = true;
				canMoveTo[30] = true;
			}
			if(location == 30){
				canMoveTo[25] = true;
				canMoveTo[26] = true;
				canMoveTo[27] = true;
				canMoveTo[29] = true;
				canMoveTo[30] = true;
				canMoveTo[31] = true;
			}
			if(location == 31){
				canMoveTo[26] = true;
				canMoveTo[27] = true;
				canMoveTo[56] = true;
				canMoveTo[30] = true;
				canMoveTo[31] = true;
				canMoveTo[60] = true;
			}
			if(location == 32){
				canMoveTo[3] = true;
				canMoveTo[32] = true;
				canMoveTo[33] = true;
				canMoveTo[7] = true;
				canMoveTo[36] = true;
				canMoveTo[37] = true;
			}
			if(location == 33){
				canMoveTo[32] = true;
				canMoveTo[33] = true;
				canMoveTo[34] = true;
				canMoveTo[36] = true;
				canMoveTo[37] = true;
				canMoveTo[38] = true;
			}
			if(location == 34){
				canMoveTo[33] = true;
				canMoveTo[34] = true;
				canMoveTo[35] = true;
				canMoveTo[37] = true;
				canMoveTo[38] = true;
				canMoveTo[39] = true;
			}
			if(location == 35){
				canMoveTo[34] = true;
				canMoveTo[35] = true;
				canMoveTo[38] = true;
				canMoveTo[39] = true;
			}
			if(location == 36){
				canMoveTo[3] = true;
				canMoveTo[32] = true;
				canMoveTo[33] = true;
				canMoveTo[7] = true;
				canMoveTo[36] = true;
				canMoveTo[37] = true;
				canMoveTo[11] = true;
				canMoveTo[40] = true;
				canMoveTo[41] = true;
			}
			if(location == 37){
				canMoveTo[32] = true;
				canMoveTo[33] = true;
				canMoveTo[34] = true;
				canMoveTo[36] = true;
				canMoveTo[37] = true;
				canMoveTo[38] = true;
				canMoveTo[40] = true;
				canMoveTo[41] = true;
				canMoveTo[42] = true;
			}
			if(location == 38){
				canMoveTo[33] = true;
				canMoveTo[34] = true;
				canMoveTo[35] = true;
				canMoveTo[37] = true;
				canMoveTo[38] = true;
				canMoveTo[39] = true;
				canMoveTo[41] = true;
				canMoveTo[42] = true;
				canMoveTo[43] = true;
			}
			if(location == 39){
				canMoveTo[34] = true;
				canMoveTo[35] = true;
				canMoveTo[38] = true;
				canMoveTo[39] = true;
				canMoveTo[42] = true;
				canMoveTo[43] = true;
			}
			if(location == 40){
				canMoveTo[7] = true;
				canMoveTo[36] = true;
				canMoveTo[37] = true;
				canMoveTo[11] = true;
				canMoveTo[40] = true;
				canMoveTo[41] = true;
				canMoveTo[15] = true;
				canMoveTo[44] = true;
				canMoveTo[45] = true;
			}
			if(location == 41){
				canMoveTo[36] = true;
				canMoveTo[37] = true;
				canMoveTo[38] = true;
				canMoveTo[40] = true;
				canMoveTo[41] = true;
				canMoveTo[42] = true;
				canMoveTo[44] = true;
				canMoveTo[45] = true;
				canMoveTo[46] = true;
			}
			if(location == 42){
				canMoveTo[37] = true;
				canMoveTo[38] = true;
				canMoveTo[39] = true;
				canMoveTo[41] = true;
				canMoveTo[42] = true;
				canMoveTo[43] = true;
				canMoveTo[45] = true;
				canMoveTo[46] = true;
				canMoveTo[47] = true;
			}
			if(location == 43){
				canMoveTo[38] = true;
				canMoveTo[39] = true;
				canMoveTo[42] = true;
				canMoveTo[43] = true;
				canMoveTo[46] = true;
				canMoveTo[47] = true;
			}
			if(location == 44){
				canMoveTo[11] = true;
				canMoveTo[40] = true;
				canMoveTo[41] = true;
				canMoveTo[15] = true;
				canMoveTo[44] = true;
				canMoveTo[45] = true;
				canMoveTo[19] = true;
				canMoveTo[48] = true;
				canMoveTo[49] = true;
			}
			if(location == 45){
				canMoveTo[40] = true;
				canMoveTo[41] = true;
				canMoveTo[42] = true;
				canMoveTo[44] = true;
				canMoveTo[45] = true;
				canMoveTo[46] = true;
				canMoveTo[48] = true;
				canMoveTo[49] = true;
				canMoveTo[50] = true;
			}
			if(location == 46){
				canMoveTo[41] = true;
				canMoveTo[42] = true;
				canMoveTo[43] = true;
				canMoveTo[45] = true;
				canMoveTo[46] = true;
				canMoveTo[47] = true;
				canMoveTo[49] = true;
				canMoveTo[50] = true;
				canMoveTo[51] = true;
			}
			if(location == 47){
				canMoveTo[42] = true;
				canMoveTo[43] = true;
				canMoveTo[46] = true;
				canMoveTo[47] = true;
				canMoveTo[50] = true;
				canMoveTo[51] = true;
			}
			if(location == 48){
				canMoveTo[15] = true;
				canMoveTo[44] = true;
				canMoveTo[45] = true;
				canMoveTo[19] = true;
				canMoveTo[48] = true;
				canMoveTo[49] = true;
				canMoveTo[23] = true;
				canMoveTo[52] = true;
				canMoveTo[53] = true;
			}
			if(location == 49){
				canMoveTo[44] = true;
				canMoveTo[45] = true;
				canMoveTo[46] = true;
				canMoveTo[48] = true;
				canMoveTo[49] = true;
				canMoveTo[50] = true;
				canMoveTo[52] = true;
				canMoveTo[53] = true;
				canMoveTo[54] = true;
			}
			if(location == 50){
				canMoveTo[45] = true;
				canMoveTo[46] = true;
				canMoveTo[47] = true;
				canMoveTo[49] = true;
				canMoveTo[50] = true;
				canMoveTo[51] = true;
				canMoveTo[53] = true;
				canMoveTo[54] = true;
				canMoveTo[55] = true;
			}
			if(location == 51){
				canMoveTo[46] = true;
				canMoveTo[47] = true;
				canMoveTo[50] = true;
				canMoveTo[51] = true;
				canMoveTo[54] = true;
				canMoveTo[55] = true;
			}
			if(location == 52){
				canMoveTo[19] = true;
				canMoveTo[48] = true;
				canMoveTo[49] = true;
				canMoveTo[23] = true;
				canMoveTo[52] = true;
				canMoveTo[53] = true;
				canMoveTo[27] = true;
				canMoveTo[56] = true;
				canMoveTo[57] = true;
			}
			if(location == 53){
				canMoveTo[48] = true;
				canMoveTo[49] = true;
				canMoveTo[50] = true;
				canMoveTo[52] = true;
				canMoveTo[53] = true;
				canMoveTo[54] = true;
				canMoveTo[56] = true;
				canMoveTo[57] = true;
				canMoveTo[58] = true;
			}
			if(location == 54){
				canMoveTo[49] = true;
				canMoveTo[50] = true;
				canMoveTo[51] = true;
				canMoveTo[53] = true;
				canMoveTo[54] = true;
				canMoveTo[55] = true;
				canMoveTo[57] = true;
				canMoveTo[58] = true;
				canMoveTo[59] = true;
			}
			if(location == 55){
				canMoveTo[50] = true;
				canMoveTo[51] = true;
				canMoveTo[54] = true;
				canMoveTo[55] = true;
				canMoveTo[58] = true;
				canMoveTo[59] = true;
			}
			if(location == 56){
				canMoveTo[23] = true;
				canMoveTo[52] = true;
				canMoveTo[53] = true;
				canMoveTo[27] = true;
				canMoveTo[56] = true;
				canMoveTo[57] = true;
				canMoveTo[31] = true;
				canMoveTo[60] = true;
				canMoveTo[61] = true;
			}
			if(location == 57){
				canMoveTo[52] = true;
				canMoveTo[53] = true;
				canMoveTo[54] = true;
				canMoveTo[56] = true;
				canMoveTo[57] = true;
				canMoveTo[58] = true;
				canMoveTo[60] = true;
				canMoveTo[61] = true;
				canMoveTo[62] = true;
			}
			if(location == 58){
				canMoveTo[53] = true;
				canMoveTo[54] = true;
				canMoveTo[55] = true;
				canMoveTo[57] = true;
				canMoveTo[58] = true;
				canMoveTo[59] = true;
				canMoveTo[61] = true;
				canMoveTo[62] = true;
				canMoveTo[63] = true;
			}
			if(location == 59){
				canMoveTo[54] = true;
				canMoveTo[55] = true;
				canMoveTo[58] = true;
				canMoveTo[59] = true;
				canMoveTo[62] = true;
				canMoveTo[63] = true;
			}
			if(location == 60){
				canMoveTo[27] = true;
				canMoveTo[56] = true;
				canMoveTo[57] = true;
				canMoveTo[31] = true;
				canMoveTo[60] = true;
				canMoveTo[61] = true;
			}
			if(location == 61){
				canMoveTo[56] = true;
				canMoveTo[57] = true;
				canMoveTo[58] = true;
				canMoveTo[60] = true;
				canMoveTo[61] = true;
				canMoveTo[62] = true;
			}
			if(location == 62){
				canMoveTo[57] = true;
				canMoveTo[58] = true;
				canMoveTo[59] = true;
				canMoveTo[61] = true;
				canMoveTo[62] = true;
				canMoveTo[63] = true;
			}
			if(location == 63){
				canMoveTo[58] = true;
				canMoveTo[59] = true;
				canMoveTo[62] = true;
				canMoveTo[63] = true;
			}
		}
		if(isStarting){
			canSee[15] = true;
			canSee[19] = true;
			canSee[44] = true;
			canSee[48] = true;
		}
	}
	private void canSee(int tilelocation){
		for(int i = 0; i < 23; i++)
			drawEnemy[i] = false;
		for(int i = 0; i < 64; i++){
			canSee[i] = false;
		}
		int place = 65;
		int location = 1;
		for(int i = 0; i < 4; i++){
			if(random.biomeint[i] == 1){
				if(i == 0)location = 0;
				if(i == 1)location = 16;
				if(i == 2)location = 32;
				if(i == 3)location = 48;
				if(place != 64)
					place = 66;
			}
			if(random.biomeint[i] == 3)place = 64;
		}
		canMoveTo(tilelocation);
		int start = 0;
		for(int k = 0; k < 4; k++){
			if(random.biomeint[k] != 3)
				for(int i = start; i < start + 16; i++){
					canSee[i] = canMoveTo[i];
				}
			if(random.biomeint[k] == 3){
				if(k == 0 && canMoveTo[15] == true)canSee[15] = true;
				if(k == 1 && canMoveTo[19] == true)canSee[19] = true;
				if(k == 2 && canMoveTo[44] == true)canSee[44] = true;
				if(k == 3 && canMoveTo[48] == true)canSee[48] = true;
			}
			start += 16;
		}
		if((place != 65 && place != 64) || (place == 64 && location != 1)){
			if(tilelocation != 15 && tilelocation != 19 && tilelocation != 44 && tilelocation != 48){
				int shouldChange = 0;
				if(location == 0)if(canSee[15] == true)shouldChange = 1;
				if(location == 16)if(canSee[19] == true)shouldChange = 1;
				if(location == 32)if(canSee[44] == true)shouldChange = 1;
				if(location == 48)if(canSee[48] == true)shouldChange = 1;
				if(tilelocation == location){
					canSee[location] = true;
					canSee[location + 1] = true;
					canSee[location + 4] = true;
					canSee[location + 5] = true;
					canSee[location + 8] = true;
					canSee[location + 9] = true;
					canSee[location + 10] = true;
					canSee[location + 2] = true;
					canSee[location + 6] = true;
				}
				if(tilelocation == location + 1){
					canSee[location + 1] = true;
					canSee[location] = true;
					canSee[location + 2] = true;
					canSee[location + 4] = true;
					canSee[location + 5] = true;
					canSee[location + 6] = true;
					canSee[location + 8] = true;
					canSee[location + 9] = true;
					canSee[location + 10] = true;
					canSee[location + 11] = true;
					canSee[location + 3] = true;
					canSee[location + 7] = true;
				}
				if(tilelocation == location + 2){
					canSee[location + 2] = true;
					canSee[location + 1] = true;
					canSee[location + 3] = true;
					canSee[location + 5] = true;
					canSee[location + 6] = true;
					canSee[location + 7] = true;
					canSee[location] = true;
					canSee[location + 4] = true;
					canSee[location + 8] = true;
					canSee[location + 9] = true;
					canSee[location + 10] = true;
					canSee[location + 11] = true;
				}
				if(tilelocation == location + 3){
					canSee[location + 2] = true;
					canSee[location + 3] = true;
					canSee[location + 6] = true;
					canSee[location + 7] = true;
					canSee[location + 1] = true;
					canSee[location + 5] = true;
					canSee[location + 9] = true;
					canSee[location + 10] = true;
					canSee[location + 11] = true;
				}
				if(tilelocation == location + 4){
					canSee[location] = true;
					canSee[location + 1] = true;
					canSee[location + 4] = true;
					canSee[location + 5] = true;
					canSee[location + 8] = true;
					canSee[location + 9] = true;
					canSee[location + 2] = true;
					canSee[location + 6] = true;
					canSee[location + 10] = true;
					canSee[location + 12] = true;
					canSee[location + 13] = true;
					canSee[location + 14] = true;
				}
				if(tilelocation == location + 5){
					for(int q = location; q <= location + 15; q++){
						canSee[q] = true;
					}
				}
				if(tilelocation == location + 6){
					for(int q = location; q <= location + 15; q++){
						canSee[q] = true;
					}
				}
				if(tilelocation == location + 7){
					canSee[location + 2] = true;
					canSee[location + 3] = true;
					canSee[location + 6] = true;
					canSee[location + 7] = true;
					canSee[location + 10] = true;
					canSee[location + 11] = true;
					canSee[location + 1] = true;
					canSee[location + 5] = true;
					canSee[location + 9] = true;
					canSee[location + 13] = true;
					canSee[location + 14] = true;
					canSee[location + 15] = true;
				}
				if(tilelocation == location + 8){
					canSee[location + 4] = true;
					canSee[location + 5] = true;
					canSee[location + 8] = true;
					canSee[location + 9] = true;
					canSee[location + 12] = true;
					canSee[location + 13] = true;
					canSee[location] = true;
					canSee[location + 1] = true;
					canSee[location + 2] = true;
					canSee[location + 6] = true;
					canSee[location + 10] = true;
					canSee[location + 14] = true;
				}
				if(tilelocation == location + 9){
					for(int q = location; q <= location + 15; q++){
						canSee[q] = true;
					}
				}
				if(tilelocation == location + 10){
					for(int q = location; q <= location + 15; q++){
						canSee[q] = true;
					}
				}
				if(tilelocation == location + 11){
					canSee[location + 5] = true;
					canSee[location + 9] = true;
					canSee[location + 13] = true;
					canSee[location + 1] = true;
					canSee[location + 2] = true;
					canSee[location + 6] = true;
					canSee[location + 10] = true;
					canSee[location + 14] = true;
					canSee[location + 3] = true;
					canSee[location + 7] = true;
					canSee[location + 11] = true;
					canSee[location + 15] = true;
				}
				if(tilelocation == location + 12){
					canSee[location + 4] = true;
					canSee[location + 5] = true;
					canSee[location + 6] = true;
					canSee[location + 8] = true;
					canSee[location + 9] = true;
					canSee[location + 10] = true;
					canSee[location + 12] = true;
					canSee[location + 13] = true;
					canSee[location + 14] = true;
				}
				if(tilelocation == location + 13){
					canSee[location + 4] = true;
					canSee[location + 5] = true;
					canSee[location + 6] = true;
					canSee[location + 8] = true;
					canSee[location + 9] = true;
					canSee[location + 10] = true;
					canSee[location + 11] = true;
					canSee[location + 7] = true;
					canSee[location + 12] = true;
					canSee[location + 13] = true;
					canSee[location + 14] = true;
					canSee[location + 15] = true;
				}
				if(tilelocation == location + 14){
					canSee[location + 4] = true;
					canSee[location + 5] = true;
					canSee[location + 6] = true;
					canSee[location + 8] = true;
					canSee[location + 9] = true;
					canSee[location + 10] = true;
					canSee[location + 11] = true;
					canSee[location + 7] = true;
					canSee[location + 12] = true;
					canSee[location + 13] = true;
					canSee[location + 14] = true;
					canSee[location + 15] = true;
				}
				if(tilelocation == location + 15){
					canSee[location + 5] = true;
					canSee[location + 6] = true;
					canSee[location + 9] = true;
					canSee[location + 10] = true;
					canSee[location + 13] = true;
					canSee[location + 14] = true;
					canSee[location + 7] = true;
					canSee[location + 11] = true;
					canSee[location + 15] = true;
				}
				if(shouldChange == 0){
					if(location == 0)canSee[15] = false;
					if(location == 16)canSee[19] = false;
					if(location == 32)canSee[44] = false;
					if(location == 48)canSee[48] = false;
				}
			}
			if(isStarting){
				canSee[15] = true;
				canSee[19] = true;
				canSee[44] = true;
				canSee[48] = true;
			}
		}
		if(place == 64 && tilelocation < 65)canSee[tilelocation] = true;
		if(isObserver){
			for(int i = 0; i < 64; i++){
				canSee[i] = true;
				daysnowater = 0;
				movemade = true;
			}
		}
		for(int i = 0; i < 64; i++){
			for(int p = 0; p < 23; p++){
				if(EnemyLocation[p] == i && canSee[i] == true)drawEnemy[p] = true;
			}
		}
	}
	private void canShootTo(int weapon){
		for(int i = 0; i < 64; i++)
			canShootTo[i] = false;
		if(weapon == 4 || weapon == 5){
			canMoveTo(PlayerLocation);
			for(int i = 0; i < 64; i++)
				if(canSee[i] == true)
					canShootTo[i] = canMoveTo[i];
		}
		if(weapon == 8){
			canSee(PlayerLocation);
			for(int i = 0; i < 64; i++)
				canShootTo[i] = canSee[i];
		}
	}
	private void Discover(int location, boolean isHuman, int enemyCategorize){
		if(isHuman){
			if(location != 15 && location != 19 && location != 44 && location != 48){
				if(discovered[location] == true && random.buildings[location] == 0)
					addToTextBox("You already discovered this!");
				if(discovered[location] == false){
					if(random.buildings[location] == 0)addToTextBox("You discovered nothing!");
					if(random.buildings[location] == 1)addToTextBox("You discovered a lake!");
					if(random.buildings[location] == 2)addToTextBox("You discovered a supply drop!");
					if(random.buildings[location] == 3){
						addToTextBox("You discovered a " + getAnimalName(getBiome(location)) + "!");
						boolean cankill = false;
						for(int i = 0; i < 8; i++){
							if(inventoryI[i] == 2 || inventoryI[i] == 3 || inventoryI[i] == 4 || inventoryI[i] == 5){
								cankill = true;
								break;
							}
						}
						if(cankill == true){
							addToTextBox("You killed the " + getAnimalName(getBiome(location)) + "!");
							for(int i = 0; i < 8; i++){
								if(inventoryI[i] == 0){
									inventoryI[i] = 9;
									break;
								}
							}
							random.buildings[PlayerLocation] = 0;
						}
						if(cankill == false){
							if(isMusic)
								soundplayer.death.play();
							addToTextBox("You were killed by the " + getAnimalName(getBiome(location)) + "!");
							PlayerAlive = false;
							String ending = null;
							if(turns == 1)ending = ".";
							else{
								ending = "s.";
							}
							logGame(false);
							addToTextBox("You lived for " + turns + " turn" + ending);
						}
					}
					if(random.buildings[location] == 4)addToTextBox("You discovered a good place to rest!");
				}
				discovered[location] = true;
			}
			if((location == 15 || location == 19 || location == 44 || location == 48) && cornucopiaScavenge == true){
				addToTextBox("You have already gotten something from the cornucopia this turn!");
			}
			if((location == 15 || location == 19 || location == 44 || location == 48) && cornucopiaScavenge == false){
				discovered[location] = true;
				int inventoryAdd = 0;
				int item = rand.nextInt(390) + 1;
				if(item > 0 && item <= 45)inventoryAdd = 1;
				if(item > 45 && item <= 89)inventoryAdd = 2;
				if(item == 90)inventoryAdd = 3;
				if(item > 90 && item <= 119)inventoryAdd = 4;
				if(item == 120)inventoryAdd = 5;
				if(item > 120 && item <= 165)inventoryAdd = 6;
				if(item > 165 && item <= 210)inventoryAdd = 7;
				if(item > 210 && item <= 255)inventoryAdd = 8;
				if(item > 255 && item <= 300)inventoryAdd = 10;
				if(item > 300 && item <= 345)inventoryAdd = 11;
				if(item > 345 && item <= 390)inventoryAdd = 12;
				int lastplace = 8;
				for(int i = 0; i < 8; i++){
					if(inventoryI[i] == 0){
						inventoryI[i] = inventoryAdd;
						if(inventoryI[i] == 1){
							int type = rand.nextInt(2);
							if(type == 0)
								bottletype[i] = 2;
							if(type == 1)
								bottletype[i] = 3;
						}
						lastplace = i;
						break;
					}
				}
				if(inventoryI[lastplace] != 1)
					addToTextBox("You picked up a " + translateItem(inventoryAdd) + " from the cornucopia!");
				if(inventoryI[lastplace] == 1){
					if(bottletype[lastplace] == 2)
						addToTextBox("You picked up medicine from the cornucopia!");
					if(bottletype[lastplace] == 3)
						addToTextBox("You picked up poison from the cornucopia!");
				}
				cornucopiaScavenge = true;
			}
		}
		if(!isHuman){
			if(location != 15 && location != 19 && location != 44 && location != 48){
				if(random.buildings[location] == 3){
					boolean cankill = false;
					for(int i = 0; i < 8; i++){
						if(EnemyInventory[enemyCategorize][i] == 2 || EnemyInventory[enemyCategorize][i] == 3 || 
								EnemyInventory[enemyCategorize][i] == 4 || EnemyInventory[enemyCategorize][i] == 5){
							cankill = true;
							break;
						}
					}
					if(cankill == true){
						for(int i = 0; i < 8; i++){
							if(EnemyInventory[enemyCategorize][i] == 0){
								EnemyInventory[enemyCategorize][i] = 9;
								break;
							}
						}
						random.buildings[EnemyLocation[enemyCategorize]] = 0;
					}
					if(cankill == false){
						drawEnemy[enemyCategorize] = false;
						EnemyAlive[enemyCategorize] = false;
						deaths++;
						dropEnemyInventory(enemyCategorize);
						System.out.println("Enemy " + create.EnemyN[enemyCategorize] + " dies of wild animals.");
					}
				}
			}
			enemyDiscovered[enemyCategorize][location] = true;
		}
		if(location == 15 || location == 19 || location == 44 || location == 48){
			enemyDiscovered[enemyCategorize][location] = true;
			int inventoryAdd = 0;
			int item = rand.nextInt(390) + 1;
			if(item > 0 && item <= 45)inventoryAdd = 1;
			if(item > 45 && item <= 89)inventoryAdd = 2;
			if(item == 90)inventoryAdd = 3;
			if(item > 90 && item <= 119)inventoryAdd = 4;
			if(item == 120)inventoryAdd = 5;
			if(item > 120 && item <= 165)inventoryAdd = 6;
			if(item > 165 && item <= 210)inventoryAdd = 7;
			if(item > 210 && item <= 255)inventoryAdd = 8;
			if(item > 255 && item <= 300)inventoryAdd = 10;
			if(item > 300 && item <= 345)inventoryAdd = 11;
			if(item > 345 && item <= 390)inventoryAdd = 12;
			for(int i = 0; i < 8; i++){
				if(EnemyInventory[enemyCategorize][i] == 0){
					EnemyInventory[enemyCategorize][i] = inventoryAdd;
					if(EnemyInventory[enemyCategorize][i] == 1){
						int type = rand.nextInt(2);
						if(type == 0)
							enemyBottleType[enemyCategorize][i] = 2;
						if(type == 1)
							enemyBottleType[enemyCategorize][i] = 3;
					}
					break;
				}
			}
		}
	}
	private int getSupplyCapacity(){
		return rand.nextInt(3) + 1;
	}
	private int[] getSupplies(int howmany){
		int[] supplies = new int[howmany];
		for(int i = 0; i < howmany; i++){
			int item = rand.nextInt(390) + 1;
			if(item > 0 && item <= 45)supplies[i] = 1;
			if(item > 45 && item <= 89)supplies[i] = 2;
			if(item == 90)supplies[i] = 3;
			if(item > 90 && item <= 119)supplies[i] = 4;
			if(item == 120)supplies[i] = 5;
			if(item > 120 && item <= 165)supplies[i] = 6;
			if(item > 165 && item <= 210)supplies[i] = 7;
			if(item > 210 && item <= 255)supplies[i] = 8;
			if(item > 255 && item <= 300)supplies[i] = 10;
			if(item > 300 && item <= 345)supplies[i] = 11;
			if(item > 345 && item <= 390)supplies[i] = 12;
		}
		return supplies;
	}
	private String translateItem(int ID){
		String item = null;
		if(ID == 1)item = "bottle";
		if(ID == 2)item = "sword";
		if(ID == 3)item = "sword of fire";
		if(ID == 4)item = "spear";
		if(ID == 5)item = "spear of fire";
		if(ID == 6)item = "coat";
		if(ID == 7)item = "quiver";
		if(ID == 8)item = "bow";
		if(ID == 9)item = "meat";
		if(ID == 10)item = "banana";
		if(ID == 11)item = "tomato";
		if(ID == 12)item = "mango";
		return item;
	}
	private String translateItemC(int ID){
		String item = translateItem(ID);
		if(ID == 3)
			item = "Sword of Fire";
		if(ID == 5)
			item = "Spear of Fire";
		else{
			String firstLetter = item.substring(0, 1);
			firstLetter = firstLetter.toUpperCase();
			String rest = item.substring(1, item.length());
			item = firstLetter + rest;
		}
		return item;
	}
	private String translateItemP(int ID){
		String item = null;
		if(ID == 1)item = "bottles";
		if(ID == 2)item = "swords";
		if(ID == 3)item = "swords of fire";
		if(ID == 4)item = "spears";
		if(ID == 5)item = "spears of fire";
		if(ID == 6)item = "coats";
		if(ID == 7)item = "quivers";
		if(ID == 8)item = "bows";
		if(ID == 9)item = "meats";
		if(ID == 10)item = "bananas";
		if(ID == 11)item = "tomatoes";
		if(ID == 12)item = "mangoes";
		return item;
	}
	public void addToInventory(int item){
		for(int i = 0; i < 8; i++){
			if(inventoryI[i] == 0){
				inventoryI[i] = item;
				break;
			}
		}
		specialRepaint();
	}
	private int getWeaponDamage(int weapon){
		int damage = 0;
		if(weapon == 2)damage = 10;
		if(weapon == 3)damage = 15;
		if(weapon == 4)damage = 8;
		if(weapon == 5)damage = 13;
		if(weapon == 8)damage = 10;
		return damage;
	}
	public void autoRepaint(){
        double delay = 1000 / 60;
        Timer timer = new Timer();
        timer.schedule(new TimerTask(){
        	public void run(){
//        		System.gc();
//        		move garbage collection to customizable loop
        		specialRepaint();
                autoRepaint();
                Runtime runtime = Runtime.getRuntime();
                try {
                	if(isLogging){
						BufferedWriter bw = new BufferedWriter(new FileWriter("memory.log", true));
					    long allocatedMemory = runtime.totalMemory();
					    long freeMemory = runtime.freeMemory();
					    bw.newLine();
					    String memoryValue = null;
					    if(dataType == 0)memoryValue = Long.toString((allocatedMemory / (1)) - (freeMemory / (1)));
					    if(dataType == 1)memoryValue = Long.toString((allocatedMemory / (1024)) - (freeMemory / (1024)));
					    if(dataType == 2)memoryValue = Long.toString((allocatedMemory / (1024 * 1024)) - (freeMemory / (1024 * 1024)));
					    if(dataType < 0 || dataType > 2)memoryValue = "invalid data type";
					    bw.write(memoryValue);
					    bw.close();
                	}
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
        }, (long) delay);
	}
	private void autoGC(){
        double delay = 1000 / GC;
        Timer timer = new Timer();
        timer.schedule(new TimerTask(){
        	public void run(){
        		System.gc();
        	}
        }, (long) delay);
	}
	private void runEnemyAI(){
		for(int i = 0; i < 23; i++){
			if(EnemyHealth[i] > 0 && EnemyAlive[i] == true){
				if(isStarting){
					int startLocation = rand.nextInt(4);
					if(startLocation == 0)EnemyLocation[i] = 15;
					if(startLocation == 1)EnemyLocation[i] = 19;
					if(startLocation == 2)EnemyLocation[i] = 44;
					if(startLocation == 3)EnemyLocation[i] = 48;
					EnemyInventory[i][0] = 1;
//					for(int p = 1, u = 0; p < 8; p++){
//						if(EnemyInventory[i][p] == 0 && u < 3){
//							int item = rand.nextInt(390) + 1;
//							if(item > 0 && item <= 45){
//								EnemyInventory[i][p] = 1;
//								int bottleType = rand.nextInt(2);
//								if(bottleType == 0)enemyBottleType[i][p] = 2;
//								if(bottleType == 1)enemyBottleType[i][p] = 3;
//							}
//							if(item > 45 && item <= 89)EnemyInventory[i][p] = 2;
//							if(item == 90)EnemyInventory[i][p] = 3;
//							if(item > 90 && item <= 119)EnemyInventory[i][p] = 4;
//							if(item == 120)EnemyInventory[i][p] = 5;
//							if(item > 120 && item <= 165)EnemyInventory[i][p] = 6;
//							if(item > 165 && item <= 210)EnemyInventory[i][p] = 7;
//							if(item > 210 && item <= 255)EnemyInventory[i][p] = 8;
//							if(item > 255 && item <= 300)EnemyInventory[i][p] = 10;
//							if(item > 300 && item <= 345)EnemyInventory[i][p] = 11;
//							if(item > 345 && item <= 390)EnemyInventory[i][p] = 12;
//							u++;
//						}
//					}
				}
				if(!isStarting){
					if(Enemydaysnowater[i] >= 5)
						for(int r = 0; r < 8; r++)
							if(EnemyInventory[i][r] == 1)
								if(enemyBottleType[i][r] == 1){
									Enemydaysnowater[i] = 0;
									enemyBottleType[i][r] = 0;
									break;
								}
					if(enemyDiscovered[i][EnemyLocation[i]] == false)
						Discover(EnemyLocation[i], false, i);
					if(enemyDiscovered[i][EnemyLocation[i]] && random.buildings[EnemyLocation[i]] == 1){
						for(int p = 0; p < 8; p++){
							if(EnemyInventory[i][p] == 1){
								if(enemyBottleType[i][p] == 0){
									enemyBottleType[i][p] = 1;
								}
							}
						}
						Enemydaysnowater[i] = 0;
						System.out.println(create.EnemyN[i] + " fetches a drink.");
					}
					if(enemyDiscovered[i][EnemyLocation[i]] && random.buildings[EnemyLocation[i]] == 2){
						for(int k = 0; k < random.supplyd; k++){
							if(whereSupplyD[k] == EnemyLocation[i]){
								for(int u = 0, pro = 0; u < 8; u++){
									if(EnemyInventory[i][u] == 0){
										if(SupplyDCap[k] > 0){
											EnemyInventory[i][u] = supplies[k][pro];
											System.out.println("Item " + translateItem(supplies[k][pro]) + " added to inventory of " + create.EnemyN[i]);
											if(EnemyInventory[i][u] == 1){
												int type = rand.nextInt(2);
												if(type == 0)
													enemyBottleType[i][u] = 2;
												if(type == 1)
													enemyBottleType[i][u] = 3;
											}
										}
										pro++;
										SupplyDCap[k]--;
										if(SupplyDCap[k] == 0)isScavenged[k] = true;
									}
								}
							}
						}
					}
					if(enemyDiscovered[i][EnemyLocation[i]] && random.buildings[EnemyLocation[i]] == 4){
						if(EnemyHealth[i] < 50){
							System.out.println(EnemyHealth[i]);
							EnemyHealth[i]++;
							System.out.println(EnemyHealth[i]);
						}
					}
					if(EnemyHealth[i] <= 45){
						for(int q = 0; q < 8; q++){
							if(EnemyInventory[i][q] == 9 || EnemyInventory[i][q] == 10 || EnemyInventory[i][q] == 11 || EnemyInventory[i][q] == 12){
								if(EnemyInventory[i][q] == 9){
									EnemyHealth[i] = 50;
								}
								else{
									for(int w = 0; w < 5; w++)
										if(EnemyHealth[i] < 50)
											EnemyHealth[i]++;
								}
								EnemyInventory[i][q] = 0;
							}
						}
					}
					if(EnemyInfected[i] == true)
						for(int q = 0; q < 8; q++)
							if(EnemyInventory[i][q] == 1)
								if(enemyBottleType[i][q] == 2){
									EnemyInfected[i] = false;
									enemyBottleType[i][q] = 0;
									break;
								}
					for(int u = 0; u < 100; u++){
						if(onGround[EnemyLocation[i]][u] > 0)
							for(int e = 0; e < 8; e++)
								if(EnemyInventory[i][e] == 0){
									EnemyInventory[i][e] = onGround[EnemyLocation[i]][u];
									onGround[EnemyLocation[i]][u] = 0;
									if(isPoisoned[EnemyLocation[i]] == true){
										EnemyPoisoned[i] = true;
										isPoisoned[EnemyLocation[i]] = false;
									}
								}
						boolean slotOpen = false;
						for(int e = 0; e < 8; e++)
							if(EnemyInventory[i][e] == 0)
								slotOpen = true;
						if(slotOpen == false)
							break;
					}
					for(int q = 0; q < 8; q++)
						if(EnemyInventory[i][q] == 1){
							boolean itemsOnTile = false;
							for(int y = 0; y < 100; y++)
								if(onGround[EnemyLocation[i]][y] > 0)
									itemsOnTile = true;
							if(enemyBottleType[i][q] == 3 && itemsOnTile == true){
								isPoisoned[EnemyLocation[i]] = true;
								enemyBottleType[i][q] = 0;
								break;
							}
						}
//					start move
					canSee(EnemyLocation[i]);
					boolean moveMade = false;
					if((Enemydaysnowater[i] >= 3 && moveMade == false) || (inWhatBiome(EnemyLocation[i]) == 4 && Enemydaysnowater[i] > 1)){
						int distance = 64;
						int bestPlace = EnemyLocation[i];
						for(int q = 0; q < 64; q++){
							if(random.buildings[q] == 1){
								System.out.println(Math.abs(q - EnemyLocation[i]));
								if(Math.abs(q - EnemyLocation[i]) < distance){
									bestPlace = q;
									distance = Math.abs(q - EnemyLocation[i]);
								}
							}
						}
						if(moveMade == false){
							moveTowards(bestPlace, i);
							moveMade = true;
						}
					}
					if(EnemyLocation[i] == 15 || EnemyLocation[i] == 19 || EnemyLocation[i] == 44 || EnemyLocation[i] == 48){
						boolean hasWeapon = false;
						for(int y = 0; y < 8; y++){
							if(EnemyInventory[i][y] == 2 || EnemyInventory[i][y] == 3 || EnemyInventory[i][y] == 4 || EnemyInventory[i][y] == 5 || EnemyInventory[i][y] == 8)
								hasWeapon = true;
						}
						moveMade = !hasWeapon;
					}
					for(int y = 0; y < 64; y++){
						if(moveMade == true)
							break;
						for(int t = 0; t < 23; t++){
							if(moveMade == true)
								break;
							if(canSee[y] == true && moveMade == false){
								if(t == i)t++;
								if(t == 23)break;
								if((EnemyLocation[t] == y || PlayerLocation == y) && runAway[i] == false){
									moveTowards(y, i);
									moveMade = true;
								}
							}
						}
					}
					if(moveMade == false && runAway[i] == true && turns <= 2){
						if(EnemyLocation[i] >= 0 && EnemyLocation[i] <= 15)moveTowards(0, i);
						if(EnemyLocation[i] >= 16 && EnemyLocation[i] <= 31)moveTowards(28, i);
						if(EnemyLocation[i] >= 32 && EnemyLocation[i] <= 47)moveTowards(35, i);
						if(EnemyLocation[i] >= 48 && EnemyLocation[i] <= 63)moveTowards(63, i);
						moveMade = true;
					}
					if(moveMade == false){
						moveTowards(rand.nextInt(64), i);
						moveMade = true;
					}
//					start attack
					boolean SAPF = false;
					boolean attackedEnemy = false;
					int decider = rand.nextInt(4);
					if(decider == 0 && PlayerLocation == EnemyLocation[i] && PlayerHealth > 0)SAPF = true;
					for(int q = 0; q < 23; q++){
						if(SAPF == true)break;
						if(q == i)q++;
						if(q == 23)break;
						if(attackedEnemy == true)break;
						if(EnemyLocation[q] == EnemyLocation[i]){
							if(EnemyHealth[i] > 0){
								int weapons = 0;
								for(int w = 0; w < 8; w++){
									if(EnemyInventory[i][w] == 2 || EnemyInventory[i][w] == 3 || EnemyInventory[i][w] == 4 || EnemyInventory[i][w] == 5)
										weapons++;
								}
								int[] weaponLocation = new int[weapons];
								int[] weaponDamage = new int[weapons];
								for(int w = 0, a = 0; w < 8; w++){
									if(EnemyInventory[i][w] == 2 || EnemyInventory[i][w] == 3 || EnemyInventory[i][w] == 4 || EnemyInventory[i][w] == 5){
										weaponLocation[a] = w;
										weaponDamage[a] = getWeaponDamage(EnemyInventory[i][w]);
										a++;
									}
								}
								int damage = create.EnemySt[i] / 2;
								int weaponChoice = 0;
								for(int s = 0; s < weapons; s++){
									if(weaponDamage[s] > damage){
										damage = weaponDamage[s];
										weaponChoice = s;
									}
								}
								if(weaponChoice > 0)
									EnemyHealth[q] -= damage;
								if(weaponChoice == 0)
									EnemyHealth[q] -= damage;
								if(weapons > 0)
									if(EnemyInventory[i][weaponLocation[weaponChoice]] == 3)
										EnemyInfected[q] = true;
								EnemyDamageTaken[q] += damage;
								if(EnemyHealth[q] <= 0){
									System.out.println("Enemy " + create.EnemyN[q] + " is dead.");
									EnemyAlive[q] = false;
									drawEnemy[q] = false;
									dropEnemyInventory(q);
									EnemyLocation[q] = 65;
									deaths++;
									EnemyKills[i]++;
									EnemyDamageDealt[i] += damage;
								}
								System.out.println("Enemy " + create.EnemyN[i] + " attacked enemy " + create.EnemyN[q] + " and dealt " + damage);
								if(weapons > 0)
									System.out.println(" with a " + translateItem(EnemyInventory[i][weaponLocation[weaponChoice]]));
								attackedEnemy = true;
								break;
							}
						}
					}
					if(attackedEnemy == false && SAPF == false){
						for(int r = 0; r < 8; r++){
							boolean hasQuiver = false;
							if(EnemyInventory[i][r] == 8)
								for(int e = 0; e < 8; e++)
									if(EnemyInventory[i][e] == 7)
										hasQuiver = true;
							if(EnemyInventory[i][r] == 4 || EnemyInventory[i][r] == 5 || (EnemyInventory[i][r] == 8 && hasQuiver)){
								for(int q = 0; q < 23; q++){
									if(q == i)q++;
									if(q == 23)break;
									for(int y = 0; y < 64; y++){
										if(EnemyLocation[q] == y && canSee[y] == true){
											int damage = 0;
											int weaponLocation = 0;
											int whatWeapon = 0;
											for(int w = 0; w < 8; w++){
												if(EnemyInventory[i][r] == 4 || EnemyInventory[i][r] == 5 || (EnemyInventory[i][r] == 8 && hasQuiver == true)){
													if(getWeaponDamage(EnemyInventory[i][r]) > damage){
														damage = getWeaponDamage(EnemyInventory[i][r]);
														weaponLocation = w;
														whatWeapon = EnemyInventory[i][r];
													}
												}
											}
											if((whatWeapon == 4 || whatWeapon == 5) && y != EnemyLocation[i])
												EnemyInventory[i][weaponLocation] = 0;
											boolean miss = false;
											int missProbability = rand.nextInt(create.EnemyWs[i]);
											if(create.EnemyWs[i] <= 2)missProbability = rand.nextInt(2);
											if(missProbability == 0)miss = true;
											if(miss == true){
												System.out.println("Enemy " + create.EnemyN[i] + " missed attacking " + create.EnemyN[q]);
												attackedEnemy = true;
											}
											if(miss == false){
												EnemyHealth[q] -= damage;
												if(whatWeapon == 5)
													EnemyInfected[q] = true;
												EnemyDamageTaken[q] += damage;
												if(EnemyHealth[q] <= 0){
													System.out.println("Enemy " + create.EnemyN[q] + " is dead.");
													EnemyAlive[q] = false;
													drawEnemy[q] = false;
													dropEnemyInventory(q);
													EnemyLocation[q] = 65;
													deaths++;
													EnemyKills[i]++;
													EnemyDamageDealt[i] += damage;
												}
												System.out.println("Enemy " + create.EnemyN[i] + " range attacked enemy " + create.EnemyN[q] + " and dealt " + damage + " with a " + translateItem(whatWeapon) + "!");
												attackedEnemy = true;
												break;
											}
										}
									}
									if(attackedEnemy == true)
										break;
								}
							}
							if(attackedEnemy == true)
								break;
						}
					}
					if(!isObserver && PlayerHealth > 0){
						if(PlayerLocation == EnemyLocation[i] && attackedEnemy == false){
							int weapons = 0;
							for(int w = 0; w < 8; w++){
								if(EnemyInventory[i][w] == 2 || EnemyInventory[i][w] == 3 || EnemyInventory[i][w] == 4 || EnemyInventory[i][w] == 5 || EnemyInventory[i][w] == 8)
									weapons++;
							}
							int[] weaponLocation = new int[weapons];
							int[] weaponDamage = new int[weapons];
							for(int w = 0, a = 0; w < 8; w++){
								if(EnemyInventory[i][w] == 2 || EnemyInventory[i][w] == 3 || EnemyInventory[i][w] == 4 || EnemyInventory[i][w] == 5){
									weaponLocation[a] = w;
									weaponDamage[a] = getWeaponDamage(EnemyInventory[i][w]);
									a++;
								}
								if(EnemyInventory[i][w] == 8){
									boolean hasQuiver = false;
									for(int k = 0; k < 8; k++)
										if(EnemyInventory[i][k] == 7)hasQuiver = true;
									if(hasQuiver == true){
										weaponLocation[a] = w;
										weaponDamage[a] = getWeaponDamage(EnemyInventory[i][w]);
									}
								}
							}
							int damage = create.EnemySt[i] / 2;
							int weaponChoice = weapons + 1;
							for(int s = 0; s < weapons; s++){
								if(weaponDamage[s] > damage){
									damage = weaponDamage[s];
									weaponChoice = s;
								}
							}
							String weapon = "";
							if(weaponChoice < weapons + 1)weapon = translateItem(EnemyInventory[i][weaponLocation[weaponChoice]]);
							if(weaponChoice == weapons + 1)weapon = "fist";
							addToTextBox("Your health was lowered to " + (PlayerHealth - damage) + " after an attack from " + create.EnemyN[i] + " with a " + weapon + "!");
							if(weaponChoice > 0)
								PlayerHealth -= damage;
							if(weaponChoice == 0)
								PlayerHealth -= damage;
							if(isMusic)
								soundplayer.death.play();
							playerDamageTaken += damage;
							if(weapons > 0)
								if(EnemyInventory[i][weaponLocation[weaponChoice]] == 3){
									isInfected = true;
									addToTextBox("You were infected by " + create.EnemyN[i]);
								}
							if(PlayerHealth <= 0){
								addToTextBox("You were killed by " + create.EnemyN[i] + "!");
								PlayerAlive = false;
								deaths++;
								EnemyKills[i]++;
								logGame(false);
							}
							System.out.println("Enemy " + create.EnemyN[i] + " attacked " + create.PlayerN + " and dealt " + damage);
							attackedEnemy = true;
						}
						if(attackedEnemy == false)
							for(int r = 0; r < 8; r++){
								boolean hasQuiver = false;
								if(EnemyInventory[i][r] == 8)
									for(int e = 0; e < 8; e++)
										if(EnemyInventory[i][e] == 7)
											hasQuiver = true;
								if(EnemyInventory[i][r] == 4 || EnemyInventory[i][r] == 5 || (EnemyInventory[i][r] == 8 && hasQuiver)){
									for(int y = 0; y < 64; y++){
										if(PlayerLocation == y && canSee[y] == true){
											int damage = 0;
											int weaponLocation = 0;
											int whatWeapon = 0;
											for(int w = 0; w < 8; w++){
												if(EnemyInventory[i][r] == 4 || EnemyInventory[i][r] == 5 || (EnemyInventory[i][r] == 8 && hasQuiver == true)){
													if(getWeaponDamage(EnemyInventory[i][r]) > damage){
														damage = getWeaponDamage(EnemyInventory[i][r]);
														weaponLocation = w;
														whatWeapon = EnemyInventory[i][r];
													}
												}
											}
											if((whatWeapon == 4 || whatWeapon == 5) && y != EnemyLocation[i])
												EnemyInventory[i][weaponLocation] = 0;
											boolean miss = false;
											int missProbability = rand.nextInt(create.EnemyWs[i]);
											if(create.EnemyWs[i] <= 2)missProbability = rand.nextInt(2);
											if(missProbability == 0)miss = true;
											if(miss == true){
												addToTextBox("Enemy " + create.EnemyN[i] + " missed attacking you with a " + translateItem(whatWeapon) + "!");
												attackedEnemy = true;
											}
											if(miss == false){
												PlayerHealth -= damage;
												if(whatWeapon == 5){
													isInfected = true;
													addToTextBox("You were infected by " + create.EnemyN[i]);
												}
												if(isMusic)
													soundplayer.death.play();
												playerDamageTaken += damage;
												if(PlayerHealth <= 0){
													addToTextBox("You were killed by " + create.EnemyN[i] + "!");
													PlayerAlive = false;
													deaths++;
													EnemyKills[i]++;
													logGame(false);
												}
												addToTextBox("Your health was lowered to " + PlayerHealth + " after a ranged attack from " + create.EnemyN[i] + " with a " + translateItem(whatWeapon) + "!");											attackedEnemy = true;
												break;
											}
										}
									}
								}
								if(attackedEnemy == true)
									break;
							}
					}
				}
			}
		}
	}
	private void dropEnemyInventory(int EnemyC){
		for(int i = 0; i < 8; i++){
			if(EnemyLocation[EnemyC] < 65){
				itemsOG[EnemyLocation[EnemyC]] = rand.nextInt(100);
				if(itemsOG[EnemyLocation[EnemyC]] >= 100)itemsOG[EnemyLocation[EnemyC]] = 0;
				if(onGround[EnemyLocation[EnemyC]][itemsOG[EnemyLocation[EnemyC]]] > 0)itemsOG[EnemyLocation[EnemyC]]++;
				if(itemsOG[EnemyLocation[EnemyC]] < 100 && onGround[EnemyLocation[EnemyC]][itemsOG[EnemyLocation[EnemyC]]] == 0)
					onGround[EnemyLocation[EnemyC]][itemsOG[EnemyLocation[EnemyC]]] = EnemyInventory[EnemyC][i];
			}
		}
	}
	private void moveTowards(int spot, int enemy){
		boolean canUseMove[] = new boolean[14];
		int moves[] = new int[14];
		moves[0] = -29;
		moves[1] = 29;
		moves[2] = -1;
		moves[3] = 1;
		moves[4] = -4;
		moves[5] = 4;
		moves[6] = 3;
		moves[7] = -5;
		moves[8] = 5;
		moves[9] = -3;
		moves[10] = -25;
		moves[11] = -33;
		moves[12] = 33;
		moves[13] = 25;
		canUseMove[2] = true;
		canUseMove[3] = true;
		canUseMove[4] = true;
		canUseMove[5] = true;
		canUseMove[6] = true;
		canUseMove[7] = true;
		canUseMove[8] = true;
		canUseMove[9] = true;
		for(int q = 0; q < 8; q++){
			if(EnemyLocation[enemy] == 3 + (4 * q)){
				if(q < 7)
					canUseMove[12] = true;
				if(q > 0)
					canUseMove[13] = true;
				canUseMove[8] = false;
				canUseMove[9] = false;
				canUseMove[3] = false;
				canUseMove[1] = true;
			}
			if(EnemyLocation[enemy] == 32 + (4 * q)){
				if(q < 7)
					canUseMove[10] = true;
				if(q > 0)
					canUseMove[11] = true;
				canUseMove[6] = false;
				canUseMove[7] = false;
				canUseMove[2] = false;
				canUseMove[0] = true;
			}
			if(EnemyLocation[enemy] == 0 + (4 * q)){
				canUseMove[2] = false;
			}
			if(EnemyLocation[enemy] == 35 + (4 * q)){
				canUseMove[3] = false;
			}
			if((EnemyLocation[enemy] >= 0 && EnemyLocation[enemy] <= 3) || (EnemyLocation[enemy] >= 32 && EnemyLocation[enemy] <= 35)){
				canUseMove[4] = false;
				canUseMove[7] = false;
				canUseMove[9] = false;
			}
			if((EnemyLocation[enemy] >= 28 && EnemyLocation[enemy] <= 31) || (EnemyLocation[enemy] >= 60 && EnemyLocation[enemy] <= 63)){
				canUseMove[5] = false;
				canUseMove[6] = false;
				canUseMove[8] = false;
			}
		}
		int possibleMoves = 0;
		for(int q = 0; q < 14; q++)
			if(canUseMove[q] == true)
				possibleMoves++;
		int[] afterMove = new int[possibleMoves];
		int[] actualMove = new int[possibleMoves];
		for(int q = 0, k = 0; q < 14; q++){
			if(canUseMove[q] == true){
				afterMove[k] = EnemyLocation[enemy] + moves[q];
				actualMove[k] = q;
				k++;
			}
		}
		int bestMove = 14;
		int bestDecider = Math.abs(spot - EnemyLocation[enemy]);
		for(int q = 0; q < possibleMoves; q++){
			if(Math.abs(spot - afterMove[q]) < bestDecider){
				bestMove = actualMove[q];
				bestDecider = Math.abs(spot - afterMove[q]);
			}
		}
		if(bestMove < 14)
			EnemyLocation[enemy] += moves[bestMove];
//		int distanceBetween = EnemyLocation[enemy] - spot;
//		System.out.println(distanceBetween);
//		for(int q = 0; q < 14; q++){
//			if(canUseMove[q] == true){
//				bestMove = q;
//				System.out.println(distanceBetween);
//			}
//		}
//		if(bestMove < 14)
//			EnemyLocation[enemy] += moves[bestMove];
	}
	private int getPeopleInTile(int tile){
		int people = 0;
		if(PlayerLocation == tile)people++;
		for(int p = 0; p < 23; p++)
			if(EnemyAlive[p] && EnemyLocation[p] == tile)
				people++;
		return people;
	}
	private int inWhatBiome(int location){
		int biome = 0;
		if(location >= 0 && location <= 15)biome = random.biomeint[0];
		if(location >= 16 && location <= 31)biome = random.biomeint[1];
		if(location >= 32 && location <= 47)biome = random.biomeint[2];
		if(location >= 48 && location <= 63)biome = random.biomeint[3];
		return biome;
	}
	private void endTurn(){
		System.out.println();
		cornucopiaScavenge = false;
		turns++;
		daysnowater++;
		for(int y = 0; y < 23; y++)
			Enemydaysnowater[y]++;
		hasAttacked = false;
		movemade = false;
		isStarting = false;
		if(isInfected == true || PlayerPoisoned == true){
			PlayerHealth -= 5;
			if(isMusic)
				soundplayer.death.play();
			if(isInfected == true)
				addToTextBox("You took 5 damage from being infected!");
			if(PlayerPoisoned == true)
				addToTextBox("You took 5 damage from being poisoned!");
			if(PlayerHealth <= 0){
				PlayerAlive = false;
				String cause = null;
				if(isInfected == true)
					cause = "infection!";
				if(PlayerPoisoned == true)
					cause = "poison!";
				logGame(false);
				addToTextBox("You died of " + cause);
			}
		}
		int turnsmax = 7;
		for(int k = 0; k < 4; k++){
			if(random.biomeint[k] == 4){
				boolean inCornucopia = false;
				if(k == 0)
					if(PlayerLocation == 15)
						inCornucopia = true;
				if(k == 1)
					if(PlayerLocation == 19)
						inCornucopia = true;
				if(k == 2)
					if(PlayerLocation == 44)
						inCornucopia = true;
				if(k == 3)
					if(PlayerLocation == 48)
						inCornucopia = true;
				int start = 16 * k;
				int end = start + 15;
				if(PlayerLocation >= start && PlayerLocation <= end && inCornucopia == false){
					turnsmax = 4;
				}
				else{
					turnsmax = 7;
				}
			}
		}
		int daysleft = turnsmax - daysnowater;
		if(daysleft > 0 && daysleft != 1)
			addToTextBox("You have " + daysleft + " days left of water.");
		if(daysleft > 0 && daysleft == 1)
			addToTextBox("You have " + daysleft + " day left of water.");
		if(daysnowater >= turnsmax){
			if(isMusic)
				soundplayer.death.play();
			PlayerAlive = false;
			logGame(false);
			addToTextBox("You died of not drinking enough!");
		}
		for(int k = 0; k < 4; k++){
			if(random.biomeint[k] == 5){
				boolean inCornucopia = false;
				if(k == 0)
					if(PlayerLocation == 15)
						inCornucopia = true;
				if(k == 1)
					if(PlayerLocation == 19)
						inCornucopia = true;
				if(k == 2)
					if(PlayerLocation == 44)
						inCornucopia = true;
				if(k == 3)
					if(PlayerLocation == 48)
						inCornucopia = true;
				int start = 16 * k;
				int end = start + 15;
				int cause = 0;
				if(PlayerLocation >= start && PlayerLocation <= end && inCornucopia == false){
					for(int u = 0; u < 8; u++){
						if(inventoryI[u] == 6){
							isCold = false;
							cause = 2;
							break;
						}
						else{
							isCold = true;
							cause = 1;
						}
					}
				}
				else{
					isCold = false;
				}
				if(cause == 0)coldDaysLeft = 7;
			}
		}
		if(isCold == true)coldDaysLeft--;
		if(isCold == true){
			if(coldDaysLeft > 0 && coldDaysLeft != 1)
				addToTextBox("You have " + coldDaysLeft + " days left before you freeze.");
			if(coldDaysLeft > 0 && coldDaysLeft == 1)
				addToTextBox("You have " + coldDaysLeft + " day before you freeze.");
		}
		if(coldDaysLeft == 0){
			if(isMusic)
				soundplayer.death.play();
			PlayerAlive = false;
			logGame(false);
			addToTextBox("You froze to death!");
		}
//		start of enemy endturn logic
		for(int y = 0; y < 23; y++){
			if(EnemyAlive[y] == true){
				if(EnemyInfected[y] == true || EnemyPoisoned[y] == true){
					EnemyHealth[y] -= 5;
					if(EnemyHealth[y] <= 0){
						EnemyAlive[y] = false;
						drawEnemy[y] = false;
						dropEnemyInventory(y);
						EnemyLocation[y] = 65;
						deaths++;
						System.out.println("Enemy " + create.EnemyN[y] + " dies of infection/poison.");
					}
				}
				int turnsmax2 = 7;
				for(int k = 0; k < 4; k++){
					if(random.biomeint[k] == 4){
						boolean inCornucopia = false;
						if(k == 0)
							if(EnemyLocation[y] == 15)
								inCornucopia = true;
						if(k == 1)
							if(EnemyLocation[y] == 19)
								inCornucopia = true;
						if(k == 2)
							if(EnemyLocation[y] == 44)
								inCornucopia = true;
						if(k == 3)
							if(EnemyLocation[y] == 48)
								inCornucopia = true;
						int start = 16 * k;
						int end = start + 15;
						if(EnemyLocation[y] >= start && EnemyLocation[y] <= end && inCornucopia == false){
							turnsmax2 = 4;
						}
						else{
							turnsmax2 = 7;
						}
					}
				}
				if(Enemydaysnowater[y] >= turnsmax2){
					EnemyAlive[y] = false;
					drawEnemy[y] = false;
					dropEnemyInventory(y);
					EnemyLocation[y] = 65;
					deaths++;
					System.out.println("Enemy " + create.EnemyN[y] + " dies of dehydration.");
				}
				for(int k = 0; k < 4; k++){
					if(random.biomeint[k] == 5){
						boolean inCornucopia = false;
						if(k == 0)
							if(EnemyLocation[y] == 15)
								inCornucopia = true;
						if(k == 1)
							if(EnemyLocation[y] == 19)
								inCornucopia = true;
						if(k == 2)
							if(EnemyLocation[y] == 44)
								inCornucopia = true;
						if(k == 3)
							if(EnemyLocation[y] == 48)
								inCornucopia = true;
						int start = 16 * k;
						int end = start + 15;
						int cause = 0;
						if(EnemyLocation[y] >= start && EnemyLocation[y] <= end && inCornucopia == false){
							for(int u = 0; u < 8; u++){
								if(inventoryI[u] == 6){
									isCold = false;
									cause = 2;
									break;
								}
								else{
									isCold = true;
									cause = 1;
								}
							}
						}
						else{
							isCold = false;
						}
						if(cause == 0)EnemycoldDaysLeft[y] = 7;
					}
				}
				if(isCold == true)EnemycoldDaysLeft[y]--;
				if(EnemycoldDaysLeft[y] == 0){
					EnemyAlive[y] = false;
					drawEnemy[y] = false;
					dropEnemyInventory(y);
					EnemyLocation[y] = 65;
					deaths++;
					System.out.println("Enemy " + create.EnemyN[y] + " dies of cold.");
				}
			}
		}
		for(int p = 0; p < 64; p++){
			if(isPoisoned[p] == true){
				if(poisonLength[p] >= 5){
					isPoisoned[p] = false;
					poisonLength[p] = 0;
				}
				poisonLength[p]++;
			}
		}
		if(deaths >= 23){
			if(PlayerAlive == true){
				addToTextBox("You win!");
				addToTextBox("This will be logged in Logs.txt.  You can review the win there.");
				logGame(true);
			}
		}
		addToTextBox("There have been " + deaths + " deaths.");
		addToTextBox("It is now turn " + turns + ".");
		runEnemyAI();
	}
	private void logGame(boolean won){
		String wonS = "lost";
		if(won)wonS = "won";
		try{
			BufferedWriter bw = new BufferedWriter(new FileWriter("Logs.txt", true));
			bw.newLine();
			bw.write("---------------------");
			bw.newLine();
			bw.write("On " + getDate.getDayMonthYear() + " " + getDate.getRealDateTime() + ",");
			bw.newLine();
			bw.write(create.PlayerN + " " + wonS + " the game in " + turns + " turns.");
			bw.newLine();
			bw.write("Player Kills:  " + playerKills);
			bw.newLine();
			bw.write("Player Damage Dealt:  " + playerDamageDealt);
			bw.newLine();
			bw.write("Player Damage Taken:  " + playerDamageTaken);
			bw.close();
		}catch (IOException e1){
			System.err.format("Modifying log file FAILED!");
		}
		PlayerAlive = false;
	}
	private String getAnimalName(int biome){
		String name = "";
		if(biome == 1)name = "mud man";
		if(biome == 2)name = "bee";
		if(biome == 3)name = "snake";
		if(biome == 4)name = "lizard";
		if(biome == 5)name = "wolf";
		if(biome == 7)name = "thug";
		if(name == "")name = "error";
		return name;
	}
	private int getBiome(int location){
		int biome = 8;
		if(location >= 0 && location <= 15)biome = random.biomeint[0];
		if(location >= 16 && location <= 31)biome = random.biomeint[1];
		if(location >= 32 && location <= 47)biome = random.biomeint[2];
		if(location >= 48 && location <= 63)biome = random.biomeint[3];
		return biome;
	}
	private void logoDelay(){
        double delay = 1500;
        Timer timer = new Timer();
        timer.schedule(new TimerTask(){
        	public void run(){
        		drawWhat = 1;
        	}
        }, (long) delay);
	}
	private boolean doesOverflow(String text){
		boolean doesOverflow = false;
		if(text.length() > 63)doesOverflow = true;
		return doesOverflow;
	}
	public void addToName(char letter){
		nameInput += letter;
	}
	public void undo(){
		if(nameInput.length() > 0)
			nameInput = nameInput.substring(0, nameInput.length() - 1);
	}
	public void confirm(){
		drawWhat = 2;
		if(create3 == 0){
			create.RunCode();
			create3++;
			initializeText();
			initializeEnemies();
		}
		if(nameInput == "")nameInput = getHumorousName();
		create.PlayerN = nameInput;
	}
	private String getHumorousName(){
		String name = "";
		int choice = rand.nextInt(5);
		if(choice == 0)name = "LoLzylander 22";
		if(choice == 1)name = "Cowboy Steve";
		if(choice == 2)name = "Senator Veintinueve";
		if(choice == 3)name = "Mr. 29";
		if(choice == 4)name = "Marve the Favre";
		return name;
	}
	private String translateMode(boolean usingNumber){
		String mode = null;
		if(usingNumber)mode = "Number Mode";
		if(!usingNumber)mode = "Text Mode";
		return mode;
	}
}
