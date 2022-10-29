package fr.rakambda.battledev;

import fr.rakambda.battledev.s16.Ex1;
import fr.rakambda.battledev.s16.Ex2;
import fr.rakambda.battledev.s16.Ex3;
import fr.rakambda.battledev.s16.Ex4;
import fr.rakambda.battledev.s16.Ex5;
import fr.rakambda.battledev.s16.Ex6;
import fr.rakambda.battledev.utils.Exercise;
import fr.rakambda.battledev.utils.ExerciseCase;
import fr.rakambda.battledev.utils.ExerciseInputsProvider;
import fr.rakambda.battledev.utils.ParallelizableTest;
import fr.rakambda.battledev.utils.Season;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

@Season(16)
@ParallelizableTest
public class S16{
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
	
	@Exercise(value = 2, inputCount = 6)
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
	
	@Exercise(value = 3, inputCount = 6)
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
	
	@Disabled("Not done")
	@Exercise(value = 5, inputCount = 5)
	@ParameterizedTest
	@ArgumentsSource(ExerciseInputsProvider.class)
	void exercise5(ExerciseCase context) throws IOException{
		try(final ByteArrayOutputStream out = new ByteArrayOutputStream()){
			assertTimeoutPreemptively(Duration.ofMinutes(1), () -> Ex5.run(context.getInput().asStream(), new PrintStream(out)));
			
			out.flush();
			String result = out.toString().trim();
			
			assertEquals(context.getOutput().getContent(), result);
		}
	}
	
	@Disabled("Not done")
	@Exercise(value = 6, inputCount = 12)
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
