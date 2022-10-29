package fr.rakambda.battledev.s13;

import java.io.InputStream;
import java.io.PrintStream;
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
		final int n = Integer.parseInt(inputs.pop());
		final List<Integer> weights = inputs.stream().map(Integer::parseInt).collect(Collectors.toList());
		final int maxWeight = 100;
		int curr = 0;
		int c = 0;
		for(int w : weights){
			if(w + curr > maxWeight){
				c++;
				curr = 0;
			}
			curr += w;
		}
		if(curr > 0){
			c++;
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
