package fr.mrcraftcod.battledev.s17;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class Ex5{
	public static void main(final String[] args){
		run(System.in, System.out);
	}
	
	public static void run(InputStream in, PrintStream out){
		final LinkedList<String> inputs = getInputs(in);
		String[] pppp = inputs.pop().split(" ");
		int totalTime = Integer.parseInt(pppp[0]);
		int activationTime = Integer.parseInt(pppp[1]);
		int cooldownTime = Integer.parseInt(pppp[2]);
		
		Integer[] objects = Arrays.stream(inputs.pop().split(" "))
				.map(Integer::parseInt)
				.toArray(Integer[]::new);
		int pos = 0;
		int count = 0;
		int window = activationTime + cooldownTime;
		
		while(pos < totalTime){
			Pair<Integer, Integer> best = getBest(objects, totalTime, pos, activationTime, window);
			count += best.value;
			pos = best.key + activationTime + cooldownTime;
		}
		
		out.println(Arrays.stream(objects).mapToInt(i -> i).sum() - count);
	}
	
	private static Pair<Integer, Integer> getBest(Integer[] objects, int totalTime, int pos, int activationTime, int window){
		int bestSum = 0;
		int bestIndex = totalTime;
		
		for(int i = 0; i < window; i++){
			int sum = 0;
			if(pos + i + activationTime < totalTime){
				break;
			}
			for(int j = 0; j < activationTime; j++){
				sum += objects[pos + i + j];
			}
			if(sum > bestSum){
				bestSum = sum;
				bestIndex = pos + i;
			}
		}
		return new Pair<>(bestIndex, bestSum);
	}
	
	static class Pair<K, V>{
		K key;
		V value;
		
		public Pair(K key, V value){
			this.key = key;
			this.value = value;
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
