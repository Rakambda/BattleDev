package fr.rakambda.battledev.s16;

import java.io.InputStream;
import java.io.PrintStream;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Ex2{
	private static final DateTimeFormatter DF = DateTimeFormatter.ofPattern("HH:mm");
	private static final LocalTime TWENTY = LocalTime.of(20, 0, 0);
	private static final LocalTime EIGHT = LocalTime.of(8, 0, 0);
	
	public static void main(final String[] args){
		run(System.in, System.out);
	}
	
	public static void run(InputStream in, PrintStream out){
		final LinkedList<String> inputs = getInputs(in);
		inputs.pop();
		
		List<LocalTime> tweetTimes = inputs.stream()
				.map(a -> LocalTime.parse(a, DF))
				.collect(Collectors.toList());
		
		long suspiciousTweetTimes = tweetTimes.stream()
				.filter(a -> a.isAfter(TWENTY) || a.equals(TWENTY) || a.isBefore(EIGHT))
				.count();
		
		boolean isSuspicious = suspiciousTweetTimes > (tweetTimes.size() / 2);
		out.println(isSuspicious ? "SUSPICIOUS" : "OK");
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
