package fr.mrcraftcod.battledev.utils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExerciseResources{
	private final Map<Integer, Set<ExerciseInput>> exerciseInputs = new HashMap<>();
	
	public void addResource(ExerciseInput exerciseInput){
		exerciseInputs.compute(exerciseInput.getId(), (key, val) -> {
			if(Objects.nonNull(val)){
				val.add(exerciseInput);
				return val;
			}
			return Stream.of(exerciseInput).collect(Collectors.toSet());
		});
	}
	
	public void merge(ExerciseResources exerciseResources){
		exerciseResources.exerciseInputs.forEach((key, value) -> this.exerciseInputs.merge(key, value, (set1, set2) -> Stream.concat(set1.stream(), set2.stream()).collect(Collectors.toSet())));
	}
	
	public Set<ExerciseInput> getInputsForId(int id){
		return this.exerciseInputs.get(id);
	}
	
	public Set<Integer> getIds(){
		return exerciseInputs.keySet();
	}
}
