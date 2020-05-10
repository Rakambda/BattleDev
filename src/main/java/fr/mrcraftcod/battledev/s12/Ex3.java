package fr.mrcraftcod.battledev.s12;

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
		int n = Integer.parseInt(inputs.pop());
		double f = n / 2.0;
		int c = 0;
		Integer prev = null;
		for(String s : inputs.pop().split(" ")){
			int i = Integer.parseInt(s);
			if(prev != null){
				if(prev == i && prev == f){
					out.println("INF");
					return;
				}
				if(i == f){
					c++;
				}
				else if((f > prev && f < i) || (f < prev && f > i)){
					c++;
				}
			}
			prev = i;
		}
		out.println(c);
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
