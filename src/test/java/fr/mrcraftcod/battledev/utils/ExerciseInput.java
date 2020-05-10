package fr.mrcraftcod.battledev.utils;

import java.nio.file.Path;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExerciseInput{
	private static final Pattern filePattern = Pattern.compile("(input|output)(\\d+)\\.txt");
	private final Path path;
	private final ExerciseInputKind kind;
	private final int id;
	
	public ExerciseInput(Path path){
		this.path = path;
		final Matcher matcher = filePattern.matcher(path.getFileName().toString());
		if(matcher.matches()){
			this.kind = ExerciseInputKind.valueOf(matcher.group(1).toUpperCase());
			this.id = Integer.parseInt(matcher.group(2));
		}
		else{
			throw new IllegalArgumentException("File doesn't have a valid name");
		}
	}
	
	public Path getPath(){
		return path;
	}
	
	public ExerciseInputKind getKind(){
		return kind;
	}
	
	public int getId(){
		return id;
	}
	
	@Override
	public boolean equals(Object o){
		if(this == o){
			return true;
		}
		if(o == null || getClass() != o.getClass()){
			return false;
		}
		ExerciseInput that = (ExerciseInput) o;
		return id == that.id && kind == that.kind;
	}
	
	@Override
	public int hashCode(){
		return Objects.hash(kind, id);
	}
}
