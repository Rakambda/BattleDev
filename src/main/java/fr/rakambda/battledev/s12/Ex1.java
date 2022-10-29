package fr.rakambda.battledev.s12;

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
		int count = Integer.parseInt(inputs.pop());
		int price = Integer.parseInt(inputs.pop());
		String best = "KO";
		for(String s : inputs){
			String[] parts = s.split(" ");
			int p = Integer.parseInt(parts[0]);
			if(p > price){
				price = p;
				best = parts[1];
			}
		}
		out.println(best.toUpperCase());
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
