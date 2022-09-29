package fr.mrcraftcod.battledev;

import fr.mrcraftcod.battledev.s12.*;
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
import java.time.Duration;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

@Season(12)
@ParallelizableTest
public class S12{
	@Exercise(value = 1, inputCount = 5)
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
	
	@Exercise(value = 3, inputCount = 5)
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
	
	@Exercise(value = 4, inputCount = 5)
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
	
	@Disabled("Not passing")
	@Exercise(value = 5, inputCount = 5)
	@ParameterizedTest
	@ArgumentsSource(ExerciseInputsProvider.class)
	void exercise5(ExerciseCase context) throws IOException{
		try(final ByteArrayOutputStream out = new ByteArrayOutputStream()){
			assertTimeoutPreemptively(Duration.ofMinutes(1), () -> Ex5.run(context.getInput().asStream(), new PrintStream(out)));
			
			out.flush();
			String result = out.toString().trim();
			
			assertThat(result).isEqualToIgnoringNewLines(context.getOutput().getContent());
		}
	}
	
	@Disabled("Not done")
	@Exercise(value = 6, inputCount = 3)
	@ParameterizedTest
	@ArgumentsSource(ExerciseInputsProvider.class)
	void exercise6(ExerciseCase context) throws IOException{
		try(final ByteArrayOutputStream out = new ByteArrayOutputStream()){
			Ex6.run(context.getInput().asStream(), new PrintStream(out));
			
			out.flush();
			String result = out.toString().trim();
			
			assertEquals(context.getOutput().getContent(), result);
		}
	}
}
