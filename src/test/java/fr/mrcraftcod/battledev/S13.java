package fr.mrcraftcod.battledev;

import fr.mrcraftcod.battledev.s13.Ex1;
import fr.mrcraftcod.battledev.s13.Ex2;
import fr.mrcraftcod.battledev.s13.Ex3;
import fr.mrcraftcod.battledev.s13.Ex4;
import fr.mrcraftcod.battledev.utils.Exercise;
import fr.mrcraftcod.battledev.utils.ExerciseCase;
import fr.mrcraftcod.battledev.utils.ExerciseInputsProvider;
import fr.mrcraftcod.battledev.utils.ParallelizableTest;
import fr.mrcraftcod.battledev.utils.Season;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Season(13)
@ParallelizableTest
public class S13{
	@Exercise(value = 1, inputCount = 6)
	@ParameterizedTest
	@ArgumentsSource(ExerciseInputsProvider.class)
	void exercise1(ExerciseCase context) throws IOException{
		try(final ByteArrayOutputStream out = new ByteArrayOutputStream()){
			Ex1.run(context.getInput().asStream(), new PrintStream(out));
			
			out.flush();
			String result = out.toString().trim();
			
			assertEquals(context.getOutput().getContent(), result);
		}
	}
	
	@Exercise(value = 2, inputCount = 5)
	@ParameterizedTest
	@ArgumentsSource(ExerciseInputsProvider.class)
	void exercise2(ExerciseCase context) throws IOException{
		try(final ByteArrayOutputStream out = new ByteArrayOutputStream()){
			Ex2.run(context.getInput().asStream(), new PrintStream(out));
			
			out.flush();
			String result = out.toString().trim();
			
			assertEquals(context.getOutput().getContent(), result);
		}
	}
	
	@Disabled("Not passing")
	@Exercise(value = 3, inputCount = 3)
	@ParameterizedTest
	@ArgumentsSource(ExerciseInputsProvider.class)
	void exercise3(ExerciseCase context) throws IOException{
		try(final ByteArrayOutputStream out = new ByteArrayOutputStream()){
			Ex3.run(context.getInput().asStream(), new PrintStream(out));
			
			out.flush();
			String result = out.toString().trim();
			
			assertEquals(context.getOutput().getContent(), result);
		}
	}
	
	@Disabled("Not passing")
	@Exercise(value = 4, inputCount = 4)
	@ParameterizedTest
	@ArgumentsSource(ExerciseInputsProvider.class)
	void exercise4(ExerciseCase context) throws IOException{
		try(final ByteArrayOutputStream out = new ByteArrayOutputStream()){
			Ex4.run(context.getInput().asStream(), new PrintStream(out));
			
			out.flush();
			String result = out.toString().trim();
			
			assertEquals(context.getOutput().getContent(), result);
		}
	}
}
