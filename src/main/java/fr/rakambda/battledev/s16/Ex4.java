package fr.rakambda.battledev.s16;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Ex4{
	private static final HashMap<String, Operation> OPERATIONS = new HashMap<>();
	
	private static class Operation{
		private final int min;
		private final int max;
		private Integer result;
		
		public Operation(int min, int max){
			this(min, max, null);
		}
		
		public Operation(int min, int max, Integer result){
			this.min = min;
			this.max = max;
			this.result = result;
		}
		
		public Integer calculate(List<Integer> key){
			if(Objects.nonNull(result)){
				return result;
			}
			
			LinkedList<Integer> subKey = new LinkedList<>(key.subList(min, max + 1));
			
			int result = subKey.pop();
			for(int i : subKey){
				result ^= i;
			}
			this.result = result;
			return result;
		}
		
		public Integer buildResult(List<Integer> key, HashMap<String, Operation> operations){
			if(Objects.nonNull(result)){
				return result;
			}
			
			Operation first = operations.get("0/" + max);
			if(min == 0){
				return first.calculate(key);
			}
			
			Operation second = operations.get("0/" + (min - 1));
			return second.calculate(key) ^ first.calculate(key);
		}
		
		@Override
		public String toString(){
			return min + " / " + max + " => " + result;
		}
	}
	
	public static void main(final String[] args){
		run(System.in, System.out);
	}
	
	public static void run(InputStream in, PrintStream out){
		final LinkedList<String> inputs = getInputs(in);
		
		String[] sizes = inputs.pop().split(" ");
		int keySize = Integer.parseInt(sizes[0]);
		
		List<Integer> key = Arrays.stream(inputs.pop().split(" "))
				.map(Integer::parseInt)
				.collect(Collectors.toList());
		
		int previous = key.get(0);
		OPERATIONS.put("0/0", new Operation(0, 0, previous));
		for(int i = 1; i < keySize; i++){
			int finalI = i;
			Operation operation = Optional.ofNullable(OPERATIONS.get("0/" + (i - 1)))
					.map(o -> new Operation(0, finalI, o.calculate(key) ^ key.get(finalI)))
					.orElseGet(() -> new Operation(0, finalI));
			OPERATIONS.put("0/" + i, operation);
		}
		
		List<Operation> operations = inputs.stream()
				.map(operation -> {
					String[] parts = operation.split(" ");
					int min = Integer.parseInt(parts[0]);
					int max = Integer.parseInt(parts[1]);
					return OPERATIONS.computeIfAbsent(min + "/" + max,
							k -> new Operation(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])));
				}).collect(Collectors.toList());
		
		List<Integer> message = operations.stream()
				.map(operation -> operation.buildResult(key, OPERATIONS))
				.collect(Collectors.toList());
		
		String counts = IntStream.range(0, 256)
				.mapToLong(i -> message.stream().filter(v -> v == i).count())
				.mapToObj(Objects::toString)
				.collect(Collectors.joining(" "));
		
		out.println(counts);
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
