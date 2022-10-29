package fr.rakambda.battledev.s15;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.*;

public class Ex5{
	public static void main(final String[] args){
		run(System.in, System.out);
	}
	
	public static void run(InputStream in, PrintStream out){
		final LinkedList<String> inputs = getInputs(in);
		String[] parts = inputs.pop().split(" ");
		int size = Integer.parseInt(parts[0]);
		int peopleCount = Integer.parseInt(parts[1]);
		String things = inputs.pop();
		Optional<List<String>> soluce = solve(peopleCount, things, 0);
		out.println(soluce.map(l -> String.join(" ", l)).orElse("IMPOSSIBLE"));
	}
	
	private static Optional<List<String>> solve(int peopleCount, String things, int depth){
		// System.out.println(String.format("%s %03d - ENTER => %d %s", tab(depth), depth, peopleCount, things));
		if(things.length() < peopleCount){
			return Optional.empty();
		}
		if(peopleCount == 0){
			if(things.isEmpty()){
				return Optional.of(Collections.emptyList());
			}
			return Optional.empty();
		}
		int length = 1;
		while(length <= things.length()){
			String part = things.substring(0, length);
			if(isPlaindrome(part)){
				// System.out.println(String.format("%s %03d - Found palindrome => %s | %d %s", tab(depth), depth, part, peopleCount - 1, things.substring(length)));
				Optional<List<String>> subParts = solve(peopleCount - 1, things.substring(length), depth + 1);
				if(subParts.isPresent()){
					LinkedList<String> l = new LinkedList<>();
					l.add(part);
					l.addAll(subParts.get());
					return Optional.of(l);
				}
			}
			length++;
		}
		return Optional.empty();
	}
	
	private static String tab(int depth){
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < depth; i++){
			sb.append("\t");
		}
		return sb.toString();
	}
	
	public static boolean isPlaindrome(String s){
		if(s.isEmpty()){
			return false;
		}
		for(int i = 0; i < s.length(); i++){
			if(s.charAt(i) != s.charAt(s.length() - 1 - i)){
				return false;
			}
		}
		return true;
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
