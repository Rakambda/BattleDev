package fr.rakambda.battledev.s14;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Ex2{
	public static void main(final String[] args){
		run(System.in, System.out);
	}
	
	public static void run(InputStream in, PrintStream out){
		final LinkedList<String> inputs = getInputs(in);
		final List<Integer> lengths = inputs.stream().map(Integer::parseInt).collect(Collectors.toList());
		final int length = lengths.stream().mapToInt(i -> i).min().orElse(0);
		final int threw = lengths.stream().mapToInt(i -> i - length).sum();
		out.println("" + threw);
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
