package fr.mrcraftcod.battledev.s17;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Ex4{
	public static void main(final String[] args){
		run(System.in, System.out);
	}
	
	public static void run(InputStream in, PrintStream out){
		final LinkedList<String> inputs = getInputs(in);
		int orbitSize = Integer.parseInt(inputs.pop());
		int halfOrbit = orbitSize / 2;
		String orbit = inputs.pop();
		int count = 0;
		
		if(orbitSize % 2 == 1){
			out.println("0");
			return;
		}
		
		Map<Character, Long> c1 = count(orbit, 0, halfOrbit);
		Map<Character, Long> c2 = count(orbit, halfOrbit, orbitSize);

		for(int i = 1; i <= halfOrbit; i++){
			c1.compute(orbit.charAt(halfOrbit + i - 1), (k, v) -> v == null ? 1 : v + 1);
			c1.compute(orbit.charAt(i - 1), (k, v) -> v == null ? 0 : v - 1);
			
			c2.compute(orbit.charAt(i - 1), (k, v) -> v == null ? 1 : v + 1);
			c2.compute(orbit.charAt(halfOrbit + i - 1), (k, v) -> v == null ? 0 : v - 1);
			
			if(equals(c1, c2)){
				count++;
			}
		}
		
		out.println(count * 2);
	}
	
	private static boolean equals(Map<Character, Long> c1, Map<Character, Long> c2){
		Map<Character, Long> cc1 = c1.entrySet().stream()
				.filter(e -> e.getValue() != 0)
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
		Map<Character, Long> cc2 = c2.entrySet().stream()
				.filter(e -> e.getValue() != 0)
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
		return cc1.equals(cc2);
	}
	
	private static Map<Character, Long> count(String orbit, int min, int max){
		return orbit.substring(min, max).chars()
				.mapToObj(i -> (char) i)
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
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
