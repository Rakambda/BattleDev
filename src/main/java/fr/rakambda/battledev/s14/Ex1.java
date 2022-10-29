package fr.rakambda.battledev.s14;

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
		final int people = Integer.parseInt(inputs.pop());
		final String name = inputs.stream().map(line -> line.split(" ")).max((line1, line2) -> Integer.compare(Integer.parseInt(line2[1]), Integer.parseInt(line1[1]))).map(line -> line[0]).orElse(null);
		out.println(name);
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
