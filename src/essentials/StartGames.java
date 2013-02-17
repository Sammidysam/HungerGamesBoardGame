package essentials;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import essentials.GridDrawer;

public class StartGames {
	public static void main(String[] args){
        JFrame frame = new JFrame("The Hunger Games Board Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int screenWidth = 726;
        int screenHeight = 700;
        frame.setSize(screenWidth, screenHeight);
        final GridDrawer panel = new GridDrawer();
        frame.setContentPane(panel);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setIconImage(panel.loadIcon());
        panel.drawWhat = 6;
        panel.autoRepaint();
        panel.initializeArray();
        frame.setFocusable(true);
        frame.addKeyListener(new KeyListener(){
            public void keyPressed(KeyEvent e) {
            	
            }
            public void keyReleased(KeyEvent e) {
            	
            }
            public void keyTyped(KeyEvent e) {
            	if(panel.drawWhat == 5){
            		if(panel.usingNumber == true){
	            		if(e.getKeyChar() == KeyEvent.VK_1 || e.getKeyChar() == KeyEvent.VK_NUMPAD1)panel.addToText(1);
	            		if(e.getKeyChar() == KeyEvent.VK_2 || e.getKeyChar() == KeyEvent.VK_NUMPAD2)panel.addToText(2);
	            		if(e.getKeyChar() == KeyEvent.VK_3 || e.getKeyChar() == KeyEvent.VK_NUMPAD3)panel.addToText(3);
	            		if(e.getKeyChar() == KeyEvent.VK_4 || e.getKeyChar() == KeyEvent.VK_NUMPAD4)panel.addToText(4);
	            		if(e.getKeyChar() == KeyEvent.VK_5 || e.getKeyChar() == KeyEvent.VK_NUMPAD5)panel.addToText(5);
	            		if(e.getKeyChar() == KeyEvent.VK_6 || e.getKeyChar() == KeyEvent.VK_NUMPAD6)panel.addToText(6);
	            		if(e.getKeyChar() == KeyEvent.VK_7 || e.getKeyChar() == KeyEvent.VK_NUMPAD7)panel.addToText(7);
	            		if(e.getKeyChar() == KeyEvent.VK_8 || e.getKeyChar() == KeyEvent.VK_NUMPAD8)panel.addToText(8);
	            		if(e.getKeyChar() == KeyEvent.VK_9 || e.getKeyChar() == KeyEvent.VK_NUMPAD9)panel.addToText(9);
	            		if(e.getKeyChar() == KeyEvent.VK_0 || e.getKeyChar() == KeyEvent.VK_NUMPAD0)panel.addToText(0);
	            		if(e.getKeyChar() == KeyEvent.VK_BACK_SPACE)panel.addToText(10);
	            		if(e.getKeyChar() == KeyEvent.VK_ENTER)panel.addToText(11);
            		}
            		if(panel.usingNumber == false){
            			char key = e.getKeyChar();
            			if(key == KeyEvent.VK_ENTER)
            				panel.confirm();
            			if(key != KeyEvent.VK_BACK_SPACE)
            				panel.addToName(key);
            			if(key == KeyEvent.VK_BACK_SPACE)
            				panel.undo();
            		}
            	}
            	if(panel.drawWhat == 4){
            		if(panel.enableCheats == true){
            			char key = e.getKeyChar();
	            		if(e.getKeyChar() == KeyEvent.VK_1)panel.addToInventory(1);
	            		if(e.getKeyChar() == KeyEvent.VK_2)panel.addToInventory(2);
	            		if(e.getKeyChar() == KeyEvent.VK_3)panel.addToInventory(3);
	            		if(e.getKeyChar() == KeyEvent.VK_4)panel.addToInventory(4);
	            		if(e.getKeyChar() == KeyEvent.VK_5)panel.addToInventory(5);
	            		if(e.getKeyChar() == KeyEvent.VK_6)panel.addToInventory(6);
	            		if(e.getKeyChar() == KeyEvent.VK_7)panel.addToInventory(7);
	            		if(e.getKeyChar() == KeyEvent.VK_8)panel.addToInventory(8);
	            		if(e.getKeyChar() == KeyEvent.VK_9)panel.addToInventory(9);
	            		if(e.getKeyChar() == KeyEvent.VK_0)panel.addToInventory(10);
	            		if(key == 'q' || key == 'Q')panel.addToInventory(11);
	            		if(key == 'w' || key == 'W')panel.addToInventory(12);
            		}
            	}
            	if(panel.drawWhat == 1){
        			char key = e.getKeyChar();
        			if(key == 'c' || key == 'C')panel.getGameMode((byte) 1);
        			if(key == 'r' || key == 'R')panel.getGameMode((byte) 2);
            	}
            }
        });
	}
}
