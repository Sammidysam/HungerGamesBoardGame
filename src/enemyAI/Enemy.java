package enemyAI;

import org.newdawn.slick.Color;

public class Enemy extends enemyAI.PlayerCreator{
	public int Enemy;
	public String name;
	public int Age = rand.nextInt(7) + 12;
	public Enemy(int categorize){
		Enemy = categorize;
	}
	public void getInfo(){
		getName(0);
		WhichDistrict();
		System.out.println(name + ": " + Enemy + " " + WhichDistrict() + " " + convertGender());
	}
	public int WhichGender(){
		int gender = 0;
		if(Enemy % 2 == 0)gender = 2;
		if(Enemy % 2 != 0)gender = 1;
		return gender;
	}
	public void getName(int namenumber){
		NameReader reader = new NameReader();
		if(WhichGender() == 1){
			if(namenumber == 0)
				name = reader.getMaleName(0);
			if(namenumber != 0)
				name = reader.getMaleName(namenumber);
		}
		if(WhichGender() == 2){
			if(namenumber == 0)
				name = reader.getFemaleName(0);
			if(namenumber != 0)
				name = reader.getFemaleName(namenumber);
		}
	}
	public String convertGender(){
		String gender = null;
		if(WhichGender() == 2)gender = "girl";
		if(WhichGender() == 1)gender = "boy";
		return gender;
	}
	public int WhichDistrict(){
		int district = Enemy / WhichGender();
		if(Enemy == 2)district = 1;
		if(Enemy == 3)district = 2;
		if(Enemy == 5)district = 3;
		if(Enemy == 7)district = 4;
		if(Enemy == 9)district = 5;
		if(Enemy == 11)district = 6;
		if(Enemy == 13)district = 7;
		if(Enemy == 15)district = 8;
		if(Enemy == 17)district = 9;
		if(Enemy == 19)district = 10;
		if(Enemy == 21)district = 11;
		if(Enemy == 23)district = 12;
		return district;
	}
	public Color getColor(){
		Color color = null;
		if(Enemy == 1)color = new Color(255, 0, 0);
		if(Enemy == 2)color = new Color(232, 17, 45);
		if(Enemy == 3)color = new Color(0, 158, 173);
		if(Enemy == 4)color = new Color(0, 162, 232);
		if(Enemy == 5)color = new Color(140, 214, 0);
		if(Enemy == 6)color = new Color(99, 176, 0);
		if(Enemy == 7)color = new Color(252, 209, 21);
		if(Enemy == 8)color = new Color(239, 170, 3);
		if(Enemy == 9)color = new Color(93, 138, 168);
		if(Enemy == 10)color = new Color(0, 48, 143);
		if(Enemy == 11)color = new Color(255, 28, 102);
		if(Enemy == 12)color = new Color(255, 22, 54);
		if(Enemy == 13)color = new Color(226, 166, 53);
		if(Enemy == 14)color = new Color(123, 63, 0);
		if(Enemy == 15)color = new Color(0, 255, 255);
		if(Enemy == 16)color = new Color(0, 183, 168);
		if(Enemy == 17)color = new Color(195, 176, 145);
		if(Enemy == 18)color = new Color(189, 183, 107);
		if(Enemy == 19)color = new Color(97, 46, 32);
		if(Enemy == 20)color = new Color(134, 89, 113);
		if(Enemy == 21)color = new Color(0, 114, 187);
		if(Enemy == 22)color = new Color(0, 35, 102);
		if(Enemy == 23)color = new Color(5, 144, 51);
		if(Enemy == 24)color = new Color(6, 198, 69);
		return color;
	}
}
