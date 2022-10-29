package fr.rakambda.battledev.s17;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.Scanner;

public class Ex3{
	public static void main(final String[] args){
		run(System.in, System.out);
	}
	
	public static void run(InputStream in, PrintStream out){
		final LinkedList<String> inputs = getInputs(in);
		final LinkedList<String> pastLines = new LinkedList<>();
		
		String lastLine = "";
		int streak = 0;
		for(String line : inputs){
			if(streak >= 4){
				int index = lastLine.indexOf(".");
				if(line.charAt(index) == '#' && pastLines.stream().map(l -> l.charAt(index)).noneMatch(l -> l == '#')){
					out.println("BOOM " + (index + 1));
					return;
				}
			}
			if(line.chars().filter(c -> (char) c == '.').count() == 1){
				if(lastLine.equals(line)){
					streak++;
				}
				else{
					streak = 1;
				}
			}
			else{
				streak = 0;
			}
			lastLine = line;
			pastLines.add(line);
		}
		
		if(streak == 4){
			out.println("BOOM " + (lastLine.indexOf(".") + 1));
		}
		else{
			out.println("NOPE");
		}
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
