package fr.rakambda.battledev.utils;

import fr.rakambda.battledev.utils.file.InputExerciseFile;
import fr.rakambda.battledev.utils.file.OutputExerciseFile;

public class ExerciseCase{
	private final InputExerciseFile input;
	private final OutputExerciseFile output;
	
	public ExerciseCase(InputExerciseFile input, OutputExerciseFile output){
		this.input = input;
		this.output = output;
	}
	
	public InputExerciseFile getInput(){
		return input;
	}
	
	public OutputExerciseFile getOutput(){
		return output;
	}
	
	@Override
	public String toString(){
		return String.format("IN: %s, OUT: %s", input, output);
	}
}
