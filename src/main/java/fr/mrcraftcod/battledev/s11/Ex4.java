package fr.mrcraftcod.battledev.s11;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Ex4{
	public static HashMap<String, Integer> tried = new HashMap<>();
	
	public static void main(String[] args){
		run(System.in, System.out);
	}
	
	public static void run(InputStream in, PrintStream out){
		LinkedList<String> inputs = getInputs(in);
		List<Integer> sizes = IntStream.range(0, 6).map(i -> Integer.parseInt(inputs.pop())).boxed().collect(Collectors.toList());
		out.println(search(sizes, 0));
	}
	
	private static int search(List<Integer> sizes, int count){
		String code = String.valueOf(sizes.get(0)) + sizes.get(1) + sizes.get(2) + sizes.get(3) + sizes.get(4) + sizes.get(5);
		int aaa = isDone(code);
		if((aaa >= 0 && aaa <= count) || count > 15){
			return Integer.MAX_VALUE;
		}
		tried.put(code, count);
		if(isCorrect(sizes)){
			return count;
		}
		int ccount = Integer.MAX_VALUE;
		for(int i = 2; i <= sizes.size(); i++){
			ccount = Math.min(ccount, search(flipAt(sizes, i), count + 1));
		}
		return ccount;
	}
	
	private static LinkedList<Integer> flipAt(List<Integer> sizes, int i){
		LinkedList<Integer> l = new LinkedList<>();
		for(int j = 0; j < i; j++){
			l.add(sizes.get(j));
		}
		Collections.reverse(l);
		for(int j = i; j < sizes.size(); j++){
			l.add(sizes.get(j));
		}
		return l;
	}
	
	private static int isDone(String code){
		for(String list : tried.keySet()){
			if(list.equals(code)){
				return tried.get(list);
			}
		}
		return -1;
	}
	
	public static boolean isCorrect(List<Integer> list){
		for(int i = 0; i < list.size() - 1; i++){
			if(list.get(i) > list.get(i + 1)){
				return false;
			}
		}
		return true;
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
