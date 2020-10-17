package fr.mrcraftcod.battledev.s12;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Ex5{
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
		
		public int get(boolean b1){
			return b1 ? x : y;
		}
	}
	
	public static void main(final String[] args){
		run(System.in, System.out);
	}
	
	public static void run(InputStream in, PrintStream out){
		final LinkedList<String> inputs = getInputs(in);
		int students = Integer.parseInt(inputs.pop());
		ArrayList<P> values = new ArrayList<>();
		for(String s : inputs){
			String[] parts = s.split(" ");
			values.add(new P(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])));
		}
		PriorityQueue<String> q = new PriorityQueue<>(2, (c1, c2) -> Integer.compare(c2.length(), c1.length()));
		q.add("1");
		q.add("2");
		String next;
		while((next = q.poll()) != null){
			String q1 = next + "1";
			String q2 = next + "2";
			if(isValid(values, q1)){
				if(q1.length() >= students){
					display(q1);
					for(char c : q1.toCharArray()){
						out.println(c);
					}
					return;
				}
				q.add(q1);
			}
			if(isValid(values, q2)){
				if(q2.length() >= students){
					display(q2);
					for(char c : q2.toCharArray()){
						out.println(c);
					}
					return;
				}
				q.add(q2);
			}
		}
		out.println("KO");
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
	
	public static boolean isValid(ArrayList<P> l, String m){
		if(l == null){
			return false;
		}
		for(int i = 0; i < m.length(); i++){
			for(int j = i + 1; j < m.length(); j++){
				boolean xx1 = m.charAt(i) == '1';
				boolean xx2 = m.charAt(j) == '1';
				int x1 = l.get(i).get(xx1);
				int x2 = l.get(j).get(xx2);
				if(testOverlap(x1, x2, x1 + 60, x2 + 60)){
					return false;
				}
			}
		}
		return true;
	}
	
	private static void display(String q1){
		for(char c : q1.toCharArray()){
			System.out.println(c);
		}
	}
	
	public static boolean testOverlap(int x1, int x2, int y1, int y2){
		if(y1 < x2){
			return false;
		}
		return y2 >= x1;
	}
}
