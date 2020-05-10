package fr.mrcraftcod.battledev.s11;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Ex5{
	public static void main(String[] args){
		run(System.in, System.out);
	}
	
	public static void run(InputStream in, PrintStream out){
		LinkedList<String> inputs = getInputs(in);
		out.println("");
	}
	
	private static LinkedList<String> getInputs(InputStream inputStream){
		LinkedList<String> lines = new LinkedList<>();
		Scanner sc = new Scanner(inputStream);
		while(sc.hasNextLine()){
			lines.add(sc.nextLine());
		}
		sc.close();
		return lines;
	}
}
