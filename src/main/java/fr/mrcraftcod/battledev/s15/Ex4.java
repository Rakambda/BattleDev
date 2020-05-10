package fr.mrcraftcod.battledev.s15;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

public class Ex4{
	public static void main(final String[] args){
		run(System.in, System.out);
	}
	
	public static void run(InputStream in, PrintStream out){
		final LinkedList<String> inputs = getInputs(in);
		out.println("");
		int count = Integer.parseInt(inputs.pop());
		List<Type> otherDeck = Arrays.stream(inputs.pop().split(" ")).map(String::toUpperCase).map(Type::valueOf).collect(Collectors.toList());
		List<Type> myDeck = Arrays.stream(inputs.pop().split(" ")).map(String::toUpperCase).map(Type::valueOf).collect(Collectors.toList());
		Optional<List<Type>> winSet = calculateBest(myDeck, otherDeck);
		out.println(winSet.map(list -> list.stream().map(Type::name).map(String::toLowerCase).collect(Collectors.joining(" "))).orElse("-1"));
	}
	
	private static Optional<List<Type>> calculateBest(List<Type> myDeck, List<Type> otherDeck){
		List<List<Type>> myDecks = generatePerm(myDeck);
		for(List<Type> deck : myDecks){
			if(isWinner(new LinkedList<>(deck), new LinkedList<>(otherDeck))){
				return Optional.of(deck);
			}
		}
		return Optional.empty();
	}
	
	private static boolean isWinner(LinkedList<Type> deck, LinkedList<Type> otherDeck){
		while(!deck.isEmpty() && !otherDeck.isEmpty()){
			Type my = deck.peek();
			Type other = otherDeck.peek();
			if(my.beats(other)){
				otherDeck.pop();
			}
			else if(other.beats(my)){
				deck.pop();
			}
			else{
				deck.pop();
				otherDeck.pop();
			}
		}
		return otherDeck.isEmpty() && !deck.isEmpty();
	}
	
	public static <E> List<List<E>> generatePerm(List<E> original){
		if(original.isEmpty()){
			List<List<E>> result = new ArrayList<>();
			result.add(new ArrayList<>());
			return result;
		}
		E firstElement = original.remove(0);
		List<List<E>> returnValue = new ArrayList<>();
		List<List<E>> permutations = generatePerm(original);
		for(List<E> smallerPermutated : permutations){
			for(int index = 0; index <= smallerPermutated.size(); index++){
				List<E> temp = new ArrayList<>(smallerPermutated);
				temp.add(index, firstElement);
				returnValue.add(temp);
			}
		}
		return returnValue;
	}
	
	enum Type{
		FEU("PLANTE", "GLACE"), EAU("FEU"), PLANTE("EAU", "POISON", "VOL"), GLACE(), POISON(), SOL("EAU", "PLANTE"), VOL();
		private final List<String> wins;
		
		Type(String... wins){
			this.wins = Arrays.asList(wins);
		}
		
		public boolean beats(Type other){
			return this.wins.contains(other.name());
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
