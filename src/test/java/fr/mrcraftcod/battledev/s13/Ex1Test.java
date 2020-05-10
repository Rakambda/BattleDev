package fr.mrcraftcod.battledev.s13;

import fr.mrcraftcod.battledev.ExerciseTest;
import java.io.InputStream;
import java.io.PrintStream;

class Ex1Test extends ExerciseTest{
	@Override
	protected void runExercise(InputStream inputStream, PrintStream printStream){
		Ex1.run(inputStream, printStream);
	}
}