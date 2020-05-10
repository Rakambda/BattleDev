package fr.mrcraftcod.battledev.s15;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.Scanner;

public class Ex2{
	public static void main(final String[] args){
		run(System.in, System.out);
	}
	
	public static void run(InputStream in, PrintStream out){
		final LinkedList<String> inputs = getInputs(in);
		inputs.pop();
		int bestCount = 0;
		int curentCount = 0;
		String current = null;
		String now;
		while((now = inputs.poll()) != null){
			if(current == null || !current.equals(now)){
				bestCount = Math.max(bestCount, curentCount);
				current = now;
				curentCount = 0;
			}
			curentCount++;
		}
		out.println("" + Math.max(bestCount, curentCount));
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
