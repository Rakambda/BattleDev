package fr.mrcraftcod.battledev.s11;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class Ex1{
	public static void main(String[] args){
		run(System.in, System.out);
	}
	
	public static void run(InputStream in, PrintStream out){
		LinkedList<String> inputs = getInputs(in);
		int nb = Integer.parseInt(inputs.pop());
		int max = 0;
		for(int i = 0; i < nb; i++){
			max = (int) Math.max(max, Math.ceil(Arrays.stream(inputs.pop().split(" ")).mapToInt(Integer::parseInt).average().orElse(0D)));
		}
		out.println(max);
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
