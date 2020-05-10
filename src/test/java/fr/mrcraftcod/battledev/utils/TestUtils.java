package fr.mrcraftcod.battledev.utils;

import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.junit.jupiter.params.provider.Arguments;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.junit.jupiter.params.provider.Arguments.of;

public class TestUtils{
	public static ExerciseResources getResourcesForExercise(String season, String exercise){
		Pattern.compile("/s13/ex1/(input|output)\\d+\\.txt");
		final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		return new Reflections(String.format("%s.%s", season, exercise), new ResourcesScanner()).getResources(Pattern.compile("(input|output)\\d+\\.txt")).stream().map(classLoader::getResource).filter(Objects::nonNull).map(url -> {
			try{
				return Paths.get(url.toURI());
			}
			catch(URISyntaxException e){
				e.printStackTrace();
			}
			return null;
		}).filter(Objects::nonNull).map(ExerciseInput::new).collect(ExerciseResources::new, ExerciseResources::addResource, ExerciseResources::merge);
	}
	
	public static Stream<Arguments> getTestArgsFor(String season, String exercise){
		final ExerciseResources exerciseResources = getResourcesForExercise(season, exercise);
		List<Arguments> args = exerciseResources.getIds().stream().map(id -> {
			final Set<ExerciseInput> inputs = exerciseResources.getInputsForId(id);
			final ExerciseInput in = inputs.stream().filter(input -> input.getKind() == ExerciseInputKind.INPUT).findAny().orElseThrow(() -> new IllegalArgumentException("No input file for season " + season + " exercise " + exercise + " with id " + id));
			final ExerciseInput out = inputs.stream().filter(input -> input.getKind() == ExerciseInputKind.OUTPUT).findAny().orElseThrow(() -> new IllegalArgumentException("No output file for season " + season + " exercise " + exercise + " with id " + id));
			return of(in.getPath(), out.getPath());
		}).collect(Collectors.toList());
		if(args.size() < 1){
			return Stream.of(of(null, null));
		}
		return args.stream();
	}
}
