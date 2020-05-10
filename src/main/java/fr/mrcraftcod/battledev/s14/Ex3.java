package fr.mrcraftcod.battledev.s14;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Ex3{
	private static class Cable{
		private final List<Slot> slots = new LinkedList<>();
		private final int id;
		
		public Cable(int id){
			this.id = id;
		}
		
		public boolean isNotUsedAt(Slot other){
			return slots.stream().allMatch(slot -> other.getEndTime() <= slot.getStartTime() || other.getStartTime() >= slot.getEndTime());
		}
		
		public void addSlot(Slot use){
			slots.add(use);
		}
		
		public int getId(){
			return id;
		}
	}
	
	private static class Slot{
		private final int startTime;
		private final int endTime;
		private Cable cable;
		
		private Slot(int startTime, int endTime){
			this.startTime = startTime;
			this.endTime = endTime;
		}
		
		public Cable getCable(){
			return this.cable;
		}
		
		public void setCable(Cable cable){
			this.cable = cable;
		}
		
		public int getEndTime(){
			return endTime;
		}
		
		public int getStartTime(){
			return startTime;
		}
	}
	
	public static void main(final String[] args){
		run(System.in, System.out);
	}
	
	public static void run(InputStream in, PrintStream out){
		final LinkedList<String> inputs = getInputs(in);
		final String[] fLine = inputs.pop().split(" ");
		final List<Cable> cables = IntStream.range(0, Integer.parseInt(fLine[0])).mapToObj(Cable::new).collect(Collectors.toList());
		final int requests = Integer.parseInt(fLine[1]);
		final List<Slot> uses = inputs.stream().map(line -> line.split(" ")).map(split -> new Slot(Integer.parseInt(split[0]), Integer.parseInt(split[1]))).collect(Collectors.toList());
		try{
			uses.forEach(use -> {
				Optional<Cable> cOpt = cables.stream().filter(cable -> cable.isNotUsedAt(use)).findAny();
				if(cOpt.isPresent()){
					cOpt.get().addSlot(use);
					use.setCable(cOpt.get());
				}
				else{
					throw new IllegalStateException();
				}
			});
			out.println(uses.stream().map(use -> use.getCable().getId() + 1).map(Object::toString).collect(Collectors.joining(" ")));
		}
		catch(Exception e){
			out.println("pas possible");
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
