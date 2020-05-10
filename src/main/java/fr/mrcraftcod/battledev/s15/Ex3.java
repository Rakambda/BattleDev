package fr.mrcraftcod.battledev.s15;

import java.io.InputStream;
import java.io.PrintStream;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Ex3{
	private static final DateTimeFormatter DF = DateTimeFormatter.ofPattern("HH:mm");
	
	public static void main(final String[] args){
		run(System.in, System.out);
	}
	
	public static void run(InputStream in, PrintStream out){
		final LinkedList<String> inputs = getInputs(in);
		inputs.pop();
		List<Truple<Integer, LocalTime, LocalTime>> indispo = inputs.stream().map(line -> {
			String[] parts = line.split(" ");
			String[] subParts = parts[1].split("-");
			LocalTime start = LocalTime.parse(subParts[0], DF);
			LocalTime end = LocalTime.parse(subParts[1], DF);
			return new Truple<>(Integer.parseInt(parts[0]), start, end);
		}).collect(Collectors.toList());
		Truple<Integer, LocalTime, LocalTime> time = IntStream.rangeClosed(1, 6).mapToObj(day -> new Pair<>(day, indispo.stream().filter(v -> v.getKey().equals(day)).collect(Collectors.toList()))).map(schedules -> findAvailable(schedules.getKey(), schedules.getValue()).orElse(null)).filter(Objects::nonNull).findAny().get();
		out.println(time.getKey() + " " + time.getValue().format(DF) + "-" + time.getValue2().format(DF));
	}
	
	private static Optional<Truple<Integer, LocalTime, LocalTime>> findAvailable(int day, List<Truple<Integer, LocalTime, LocalTime>> schedules){
		if(schedules.isEmpty()){
			return Optional.of(new Truple<>(day, LocalTime.of(8, 0, 0), LocalTime.of(8, 59, 0)));
		}
		LocalTime startOfDay = LocalTime.of(8, 0, 0);
		if(!overlaps(startOfDay, schedules)){
			return Optional.of(new Truple<>(day, startOfDay, startOfDay.plusMinutes(59)));
		}
		return schedules.stream().filter(d -> {
			List<Truple<Integer, LocalTime, LocalTime>> l = new ArrayList<>(schedules);
			l.remove(d);
			return !overlaps(d.getValue2().plusMinutes(1), l);
		}).filter(d -> d.getValue2().plusMinutes(60).isBefore(LocalTime.of(18, 0, 0))).findAny().map(d -> new Truple<>(day, d.getValue2().plusMinutes(1), d.getValue2().plusMinutes(60)));
	}
	
	private static boolean overlaps(LocalTime date, List<Truple<Integer, LocalTime, LocalTime>> schedules){
		return schedules.stream().filter(s -> s.getValue2().isAfter(date) || s.getValue2().equals(date)).anyMatch(s -> !s.getValue().isAfter(date.plusMinutes(59)));
	}
	
	static class Pair<K, V>{
		K key;
		V value;
		
		public Pair(K key, V value){
			this.key = key;
			this.value = value;
		}
		
		public K getKey(){
			return key;
		}
		
		public V getValue(){
			return value;
		}
	}
	
	static class Truple<K, V, W>{
		K key;
		V value;
		W value2;
		
		public Truple(K key, V value, W value2){
			this.key = key;
			this.value = value;
			this.value2 = value2;
		}
		
		public K getKey(){
			return key;
		}
		
		public V getValue(){
			return value;
		}
		
		public W getValue2(){
			return value2;
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
