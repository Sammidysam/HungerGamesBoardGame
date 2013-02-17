package enemyAI;

import java.io.*;
import java.util.*;

public class NameReader {
	public String number = "";
	Random rand = new Random();
	public int name;
	private String[] namesM = new String[12];
	private String[] namesF = new String[12];
	private int numberM = 1;
	private int numberF = 1;
	public String getMaleName(int namenumber) {
		String nameS = null;
		File file = new File("NamesMale.txt");
		try {
			Scanner input = new Scanner(file);
			for (int i = 0; i < 1; i++){
				number = input.nextLine();
				int lines = Integer.parseInt(number);
				if(namenumber == 0)
					name = rand.nextInt(lines) + 1;
				if(namenumber != 0)
					name = namenumber;
			}
			for (int i = 0; i < name; i++){
				nameS = input.nextLine();
			}
			input.close();
		}
		catch(FileNotFoundException e){
			System.err.format("no file found");
		}
		catch(NoSuchElementException e){
			System.err.format("line not found");
		}
		if(checkAvailability(true)){
			namesM[numberM] = nameS;
			numberM++;
		}
		if(!checkAvailability(true))nameS = null;
		return nameS;
	}
	public String getFemaleName(int namenumber) {
		String nameS = null;
		File file = new File("NamesFemale.txt");
		try {
			Scanner input = new Scanner(file);
			for (int i = 0; i < 1; i++){
				number = input.nextLine();
				int lines = Integer.parseInt(number);
				if(namenumber == 0)
					name = rand.nextInt(lines) + 1;
				if(namenumber != 0)
					name = namenumber;
			}
			for (int i = 0; i < name; i++){
				nameS = input.nextLine();
			}
			input.close();
		}
		catch(FileNotFoundException e){
			System.err.format("no file found");
		}
		catch(NoSuchElementException e){
			System.err.format("line not found");
		}
		if(checkAvailability(false)){
			namesF[numberF] = nameS;
			numberF++;
		}
		if(!checkAvailability(false))nameS = null;
		return nameS;
	}
	private boolean checkAvailability(boolean malefemale){
		int copy;
		boolean same = false;
		if(malefemale == true){
			copy = numberM - 1;
			for(int j = 0; j < (numberM - 1); j++, copy--){
				if(namesM[numberM] == namesM[copy])same = true;
				if(same == true)break;
			}
		}
		if(malefemale == false){
			copy = numberF - 1;
			for(int k = 0; k < (numberF - 1); k++, copy--){
				if(namesF[numberF] == namesF[copy])same = true;
				if(same == true)break;
			}
		}
		same = !same;
		return same;
	}
	public int getHighest(boolean gender){
		File file = null;
		if(gender == true)file = new File("NamesMale.txt");
		if(gender == false)file = new File("NamesFemale.txt");
		Scanner input;
		try {
			input = new Scanner(file);
		for (int i = 0; i < 1; i++)
			number = input.nextLine();
			input.close();
		} catch (FileNotFoundException e) {
			System.err.format("file not found");
		}
		int numberI = Integer.parseInt(number);
		return numberI;
	}
}
