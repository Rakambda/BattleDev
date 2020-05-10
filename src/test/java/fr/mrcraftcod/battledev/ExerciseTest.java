package fr.mrcraftcod.battledev;

import fr.mrcraftcod.battledev.utils.Args;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class ExerciseTest{
	@ParameterizedTest(name = ParameterizedTest.DISPLAY_NAME_PLACEHOLDER + " - " + ParameterizedTest.ARGUMENTS_PLACEHOLDER)
	@ArgumentsSource(Args.class)
	public void testExercise(Path inputsPath, Path expectedResultPath) throws IOException{
		if(Objects.isNull(inputsPath) || Objects.isNull(expectedResultPath)){
			System.out.println("No files provided");
			return;
		}
		if(!Files.isReadable(inputsPath) || !Files.isReadable(expectedResultPath)){
			throw new IllegalStateException("Failed to open provided files IN: " + inputsPath + "\t OUT: " + expectedResultPath);
		}
		String expectedResult = new String(Files.readAllBytes(expectedResultPath)).trim();
		try(final ByteArrayOutputStream out = new ByteArrayOutputStream()){
			runExercise(Files.newInputStream(inputsPath, StandardOpenOption.READ), new PrintStream(out));
			out.flush();
			String result = new String(out.toByteArray()).trim();
			assertEquals(expectedResult, result, "Calculated result isn't valid for " + inputsPath);
		}
	}
	
	protected abstract void runExercise(InputStream inputStream, PrintStream printStream);
}
