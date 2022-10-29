package fr.rakambda.battledev.s16;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Ex3{
	public static void main(final String[] args){
		run(System.in, System.out);
	}
	
	public static void run(InputStream in, PrintStream out){
		final LinkedList<String> inputs = getInputs(in);
		inputs.pop();
		
		List<Person> people = inputs.stream()
				.map(a -> {
					String[] parts = a.split(" ");
					return new Person(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
				}).collect(Collectors.toList());
		
		Person dolan = new Person(0, 0, 1);
		
		LinkedList<Person> toFindChildren = new LinkedList<>();
		toFindChildren.add(dolan);
		people.remove(dolan);
		
		while(!toFindChildren.isEmpty()){
			Person process = toFindChildren.pop();
			
			List<Person> children = people.stream()
					.filter(person -> person.hasParent(process))
					.collect(Collectors.toList());
			
			process.addChildren(children);
			toFindChildren.addAll(children);
			people.removeAll(children);
		}
		
		String countPerDepth = IntStream.rangeClosed(1, 10)
				.mapToLong(dolan::countAtDepth)
				.mapToObj(Objects::toString)
				.collect(Collectors.joining(" "));
		
		out.println(countPerDepth);
	}
	
	static class Person{
		private final int id;
		private final int parentId;
		private final List<Person> childrenId;
		
		private Integer depth;
		
		Person(int id, int parentId){
			this(id, parentId, null);
		}
		
		Person(int id, int parentId, Integer depth){
			this.id = id;
			this.parentId = parentId;
			this.depth = depth;
			this.childrenId = new LinkedList<>();
		}
		
		public void addChildren(List<Person> children){
			children.forEach(c -> c.depth = this.depth + 1);
			this.childrenId.addAll(children);
		}
		
		public long countAtDepth(int i){
			if(depth == i){
				return 1;
			}
			return childrenId.stream()
					.mapToLong(c -> c.countAtDepth(i))
					.sum();
		}
		
		public boolean hasParent(Person person){
			return this.parentId == person.id;
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
