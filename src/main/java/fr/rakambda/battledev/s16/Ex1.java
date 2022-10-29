package fr.rakambda.battledev.s16;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.Scanner;

public class Ex1{
	public static void main(final String[] args){
		run(System.in, System.out);
	}
	
	public static void run(InputStream in, PrintStream out){
		final LinkedList<String> inputs = getInputs(in);
		inputs.pop();
		
		long count = inputs.stream()
				.filter(Ex1::isSuspect)
				.count();
		
		out.println(count);
	}
	
	private static boolean isSuspect(String name){
		if(name.length() < 5){
			return false;
		}
		
		return name.substring(name.length() - 5).chars().allMatch(Character::isDigit);
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
}
