package fr.mrcraftcod.battledev.s12;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Ex2{
	public static void main(final String[] args){
		run(System.in, System.out);
	}
	
	public static void run(InputStream in, PrintStream out){
		final LinkedList<String> inputs = getInputs(in);
		int count = Integer.parseInt(inputs.pop());
		int mag = 0;
		for(String s : new HashSet<>(inputs)){
			if(isValid(s)){
				mag++;
			}
		}
		out.println(mag);
	}
	
	private static LinkedList<String> getInputs(final InputStream inputStream){
		final LinkedList<String> lines = new LinkedList<>();
		final Scanner sc = new Scanner(inputStream);
		while(sc.hasNextLine()){
			lines.add(sc.nextLine());
		}
		sc.close();
		return lines;
	}
	
	public static boolean isValid(String str){
		if(str.length() <= 7 && str.length() >= 5){
			if((((int) str.charAt(0)) + 1) == ((int) str.charAt(1))){
				char c = str.charAt(str.length() - 1);
				return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'y';
			}
		}
		return false;
	}
}
