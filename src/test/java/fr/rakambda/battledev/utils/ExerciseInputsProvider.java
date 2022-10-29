package fr.rakambda.battledev.utils;

import fr.rakambda.battledev.utils.file.ExerciseFile;
import fr.rakambda.battledev.utils.file.InputExerciseFile;
import fr.rakambda.battledev.utils.file.OutputExerciseFile;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ExerciseInputsProvider implements ArgumentsProvider{
	@Override
	public Stream<? extends Arguments> provideArguments(ExtensionContext context){
		int season = extractSeason(context);
		int exercise = extractExercise(context);
		int inputsCount = extractInputCount(context);
		
		return IntStream.rangeClosed(1, inputsCount)
				.mapToObj(caze -> getExerciseCase(season, exercise, caze))
				.map(Arguments::of);
	}
	
	private int extractSeason(ExtensionContext context){
		final Class<?> testClass = context.getTestClass()
				.orElseThrow(() -> new IllegalStateException("No test class present"));
		return Optional.ofNullable(testClass.getAnnotation(Season.class))
				.map(Season::value)
				.orElseThrow(() -> new IllegalArgumentException("No Season annotation defined on test class"));
	}
	
	private int extractExercise(ExtensionContext context){
		final Method testMethod = context.getTestMethod()
				.orElseThrow(() -> new IllegalStateException("No test method present"));
		return Optional.ofNullable(testMethod.getAnnotation(Exercise.class))
				.map(Exercise::value)
				.orElseThrow(() -> new IllegalArgumentException("No Exercise annotation defined on test method"));
	}
	
	private int extractInputCount(ExtensionContext context){
		final Method testMethod = context.getTestMethod()
				.orElseThrow(() -> new IllegalStateException("No test method present"));
		return Optional.ofNullable(testMethod.getAnnotation(Exercise.class))
				.map(Exercise::inputCount)
				.orElseThrow(() -> new IllegalArgumentException("No Exercise annotation defined on test method"));
	}
	
	private static ExerciseCase getExerciseCase(int season, int exercise, int caze){
		final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		
		final InputExerciseFile input = loadInput(
				new Reflections(String.format("s%s.ex%s", season, exercise), Scanners.Resources)
						.getResources(Pattern.compile("input" + caze + "\\.txt"))
						.stream().map(classLoader::getResource)
						.filter(Objects::nonNull)
						.findFirst()
						.orElseThrow(() -> new RuntimeException("Failed to load input resource - no found")),
				InputExerciseFile::new
		);
		final OutputExerciseFile output = loadInput(
				new Reflections(String.format("s%s.ex%s", season, exercise), Scanners.Resources)
						.getResources(Pattern.compile("output" + caze + "\\.txt"))
						.stream().map(classLoader::getResource)
						.filter(Objects::nonNull)
						.findFirst()
						.orElseThrow(() -> new RuntimeException("Failed to load output resource - no found")),
				OutputExerciseFile::new
		);
		
		return new ExerciseCase(input, output);
	}
	
	private static <T extends ExerciseFile> T loadInput(URL resource, Function<Path, T> exerciseFileFactory){
		try{
			Path resourcePath = Paths.get(resource.toURI());
			return exerciseFileFactory.apply(resourcePath);
		}
		catch(URISyntaxException e){
			throw new RuntimeException("Failed to load resource " + resource);
		}
	}
}
