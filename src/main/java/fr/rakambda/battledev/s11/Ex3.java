package fr.rakambda.battledev.s11;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

public class Ex3{
	public static void main(String[] args){
		run(System.in, System.out);
	}
	
	public static void run(InputStream in, PrintStream out){
		LinkedList<String> inputs = getInputs(in);
		List<Integer> rockys = Arrays.stream(inputs.pop().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
		int friends = Integer.parseInt(inputs.pop());
		int bestFriends = Integer.parseInt(inputs.pop());
		Map<Integer, Integer> ff = new HashMap<>();
		for(int i = 0; i < friends; i++){
			List<Integer> rockys2 = Arrays.stream(inputs.pop().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
			int distance = Math.abs(rockys.get(0) - rockys2.get(0)) + Math.abs(rockys.get(1) - rockys2.get(1)) + Math.abs(rockys.get(2) - rockys2.get(2)) + Math.abs(rockys.get(3) - rockys2.get(3)) + Math.abs(rockys.get(4) - rockys2.get(4));
			ff.put(distance, rockys2.get(5));
		}
		int moy = 0;
		List<Integer> intt = ff.keySet().stream().sorted(Comparator.comparingInt(i1 -> i1)).collect(Collectors.toList());
		for(int i = 0; i < bestFriends; i++){
			moy += ff.get(intt.get(i));
		}
		out.println(moy / bestFriends);
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
