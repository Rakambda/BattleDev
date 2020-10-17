package fr.mrcraftcod.battledev.utils.file;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public abstract class ExerciseFile{
	private final Path path;
	
	protected ExerciseFile(Path path){this.path = path;}
	
	public InputStream asStream() throws IOException{
		if(!Files.isReadable(getPath())){
			throw new IllegalStateException("Failed to open provided files: " + getPath());
		}
		return Files.newInputStream(getPath(), StandardOpenOption.READ);
	}
	
	public String getContent() throws IOException{
		return new String(Files.readAllBytes(getPath())).trim().replace("\n", System.lineSeparator());
	}
	
	public Path getPath(){
		return path;
	}
	
	@Override
	public String toString(){
		return path.getFileName().toString();
	}
}
