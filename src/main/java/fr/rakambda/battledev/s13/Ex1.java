package fr.rakambda.battledev.s13;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.Scanner;

public class Ex1{
	public static void main(final String[] args){
		run(System.in, System.out);
	}
	
	public static void run(InputStream in, PrintStream out){
		final LinkedList<String> inputs = getInputs(in);
		int a = Integer.parseInt(inputs.pop());
		String b;
		while((b = inputs.poll()) != null){
			String[] bb = b.split(" ");
			a += Integer.parseInt(bb[0]) - Integer.parseInt(bb[1]);
		}
		if(a > 10000){
			out.println("KO");
		}
		else if(a < 100){
			out.println("1000");
		}
		else{
			out.println("100");
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
