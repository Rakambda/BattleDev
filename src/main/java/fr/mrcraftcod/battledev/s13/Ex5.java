package fr.mrcraftcod.battledev.s13;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.Scanner;

public class Ex5{
	public static void main(final String[] args){
		run(System.in, System.out);
	}
	
	public static void run(InputStream in, PrintStream out){
		final LinkedList<String> inputs = getInputs(in);
		final int size = Integer.parseInt(inputs.pop());
		final StringBuilder corridor = new StringBuilder(inputs.pop());
		StringBuilder seq = new StringBuilder();
		while(!done(corridor.toString())){
			boolean done = false;
			int pos = corridor.indexOf("X");
			if(pos > 0){
				if(corridor.charAt(pos - 1) == 'o'){
					seq.append("o");
					corridor.deleteCharAt(pos - 1);
					done = true;
				}
			}
			if(!done){
				if(pos < corridor.length() - 1){
					if(corridor.charAt(pos + 1) == 'o'){
						seq.append("o");
						corridor.deleteCharAt(pos + 1);
						done = true;
					}
				}
			}
			if(!done){
				I cLeft = getInfosLeft(corridor.toString(), pos);
				I cRight = getInfosRight(corridor.toString(), pos);
				if(cLeft.better(cRight)){
					corridor.deleteCharAt(cLeft.pos);
				}
				else{
					corridor.deleteCharAt(cRight.pos);
				}
				seq.append("*");
			}
		}
		out.println(seq.toString());
	}
	
	private static I getInfosLeft(String couloir, int pos){
		I i = new I();
		int status = 0;
		while(pos > 0){
			pos--;
			if(status == 0){
				switch(couloir.charAt(pos)){
					case '*':
						i.setPos(pos);
						i.xs++;
						break;
					case 'o':
						i.setPos(pos);
						status = 1;
						i.os++;
						break;
					default:
					case '.':
						break;
				}
			}
			else{
				switch(couloir.charAt(pos)){
					case '*':
						return i;
					case 'o':
						i.os++;
						break;
					default:
					case '.':
						break;
				}
			}
		}
		return i;
	}
	
	private static I getInfosRight(String couloir, int pos){
		I i = new I();
		int status = 0;
		while(pos < couloir.length() - 1){
			pos++;
			if(status == 0){
				switch(couloir.charAt(pos)){
					case '*':
						i.setPos(pos);
						i.xs++;
						break;
					case 'o':
						i.setPos(pos);
						status = 1;
						i.os++;
						break;
					default:
					case '.':
						break;
				}
			}
			else{
				switch(couloir.charAt(pos)){
					case '*':
						return i;
					case 'o':
						i.os++;
						break;
					default:
					case '.':
						break;
				}
			}
		}
		return i;
	}
	
	public static class I{
		int xs;
		int os;
		int pos;
		
		public I(){
			this.xs = 0;
			this.os = 0;
			this.pos = -1;
		}
		
		public boolean better(I i){
			if(xs == 0 && os == 0){
				return false;
			}
			if(i.xs == 0 && i.os == 0){
				return true;
			}
			if(xs == i.xs){
				return os >= i.os;
			}
			if(os > 0 && i.os == 0){
				return true;
			}
			if(os == 0 && i.os > 0){
				return false;
			}
			return xs <= i.xs;
		}
		
		public void setPos(int pos){
			if(this.pos == -1){
				this.pos = pos;
			}
		}
	}
	
	private static boolean done(String couloir){
		return couloir.chars().mapToObj(i -> (char) i).allMatch(c -> c == 'X' || c == '.');
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
