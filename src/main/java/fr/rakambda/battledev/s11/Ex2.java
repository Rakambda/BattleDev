package fr.rakambda.battledev.s11;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.Scanner;

public class Ex2{
	public static void main(String[] args){
		run(System.in, System.out);
	}
	
	public static void run(InputStream in, PrintStream out){
		LinkedList<String> inputs = getInputs(in);
		double sales = 0;
		int price = Integer.parseInt(inputs.pop());
		int tables = Integer.parseInt(inputs.pop());
		for(int i = 0; i < tables; i++){
			int people = Integer.parseInt(inputs.pop());
			if(people >= 10){
				sales += (price * people) * (0.7);
			}
			else if(people >= 6){
				sales += (price * people) * (0.8);
			}
			else if(people >= 4){
				sales += (price * people) * (0.9);
			}
			else{
				sales += (price * people);
			}
		}
		out.println((int) Math.ceil(sales));
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
