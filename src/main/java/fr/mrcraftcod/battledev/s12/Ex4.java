package fr.mrcraftcod.battledev.s12;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Ex4{
	static class P{
		private final int y;
		private final int x;
		
		public P(int x, int y){
			this.x = x;
			this.y = y;
		}
		
		@Override
		public String toString(){
			return x + "//" + y;
		}
	}
	
	public static void main(final String[] args){
		run(System.in, System.out);
	}
	
	public static void run(InputStream in, PrintStream out){
		final LinkedList<String> inputs = getInputs(in);
		int c = 0;
		int students = Integer.parseInt(inputs.pop());
		ArrayList<P> values = new ArrayList<>();
		for(String s : inputs){
			String[] parts = s.split(" ");
			values.add(new P(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])));
		}
		for(int i = 0; i < Math.pow(2, students); i++){
			c = Math.max(genSolution(students, i, values), c);
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
	
	private static int genSolution(int s, int i, ArrayList<P> values){
		ArrayList<P> a = new ArrayList<>();
		for(int j = 0; j < s; j++){
			if(((i >> j) & 0x01) == 0x01){
				a.add(values.get(j));
			}
		}
		int best = 0;
		for(int j = 0; j < Math.pow(2, a.size()); j++){
			ArrayList<P> b = new ArrayList<>();
			for(int k = 0; k < a.size(); k++){
				int p;
				if(((j >> k) & 0x01) == 0x01){
					p = a.get(k).x;
				}
				else{
					p = a.get(k).y;
				}
				b.add(new P(p, p + 60));
			}
			if(isValid(b)){
				best = Math.max(best, b.size());
			}
		}
		return best;
	}
	
	public static boolean isValid(ArrayList<P> l){
		for(P elem1 : l){
			for(P elem2 : l){
				if(elem1 != elem2){
					if(testOverlap(elem1.x, elem2.x, elem1.x + 60, elem2.x + 60)){
						return false;
					}
				}
			}
		}
		return true;
	}
	
	public static boolean testOverlap(int x1, int x2, int y1, int y2){
		if(y1 < x2){
			return false;
		}
		return y2 >= x1;
	}
}
