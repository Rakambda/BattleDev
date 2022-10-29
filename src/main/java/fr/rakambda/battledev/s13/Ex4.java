package fr.rakambda.battledev.s13;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Ex4{
	public static void main(final String[] args){
		run(System.in, System.out);
	}
	
	public static void run(InputStream in, PrintStream out){
		final LinkedList<String> inputs = getInputs(in);
		final int n = Integer.parseInt(inputs.pop());
		ArrayList<String> words = new ArrayList<>(inputs);
		String base = words.get(0);
		for(int length = words.stream().mapToInt(String::length).min().orElse(0); length > 0; length--){
			List<String> seqs = buildSeqs(base, base.length() - length);
			for(String s : seqs){
				if(words.stream().allMatch(ss -> contains(ss, s))){
					out.println(s);
					return;
				}
			}
		}
		out.println("KO");
	}
	
	private static boolean contains(String word, String seq){
		int nextChar = 0;
		for(char c : word.toCharArray()){
			if(nextChar < seq.length()){
				if(seq.charAt(nextChar) == c){
					nextChar++;
				}
			}
		}
		return nextChar == seq.length();
	}
	
	private static List<String> buildSeqs(String base, int toRemove){
		if(toRemove > 0){
			return IntStream.range(0, base.length()).mapToObj(i -> new StringBuilder(base).deleteCharAt(i).toString()).flatMap(s -> buildSeqs(s, toRemove - 1).stream()).collect(Collectors.toList());
		}
		ArrayList<String> a = new ArrayList<>();
		a.add(base);
		return a;
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
