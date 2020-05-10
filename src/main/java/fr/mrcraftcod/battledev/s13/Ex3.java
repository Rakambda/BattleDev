package fr.mrcraftcod.battledev.s13;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Ex3{
	public static void main(final String[] args){
		run(System.in, System.out);
	}
	
	public static void run(InputStream in, PrintStream out){
		final LinkedList<String> inputs = getInputs(in);
		final int n = Integer.parseInt(inputs.pop());
		List<P> coins = new ArrayList<>();
		List<P> mults = new ArrayList<>();
		String l;
		int j = 0;
		while((l = inputs.poll()) != null){
			for(int i = 0; i < n; i++){
				if(l.charAt(i) == 'o'){
					coins.add(new P(i, j));
				}
				else if(l.charAt(i) == '*'){
					mults.add(new P(i, j));
				}
			}
			j++;
		}
		P currPos = new P(0, 0);
		StringBuilder seq = new StringBuilder();
		for(P pp : coins){
			seq.append(currPos.seqTo(pp)).append("x");
			currPos = pp;
		}
		for(P pp : mults){
			seq.append(currPos.seqTo(pp)).append("x");
			currPos = pp;
		}
		out.println(seq);
	}
	
	public static class P{
		int x;
		int y;
		
		public P(int x, int y){
			this.x = x;
			this.y = y;
		}
		
		public String seqTo(P pp){
			StringBuilder seq = new StringBuilder();
			for(int i = 0; i < pp.x - x; i++){
				seq.append(">");
			}
			for(int i = 0; i < x - pp.x; i++){
				seq.append("<");
			}
			for(int i = 0; i < pp.y - y; i++){
				seq.append("v");
			}
			for(int i = 0; i < y - pp.y; i++){
				seq.append("^");
			}
			return seq.toString();
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
