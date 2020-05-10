package fr.mrcraftcod.battledev.utils;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import java.util.stream.Stream;

public class Args implements ArgumentsProvider{
	@Override
	public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception{
		final Class<?> testClass = context.getTestClass().orElseThrow(() -> new IllegalStateException("No test class is present?"));
		final String[] packageParts = testClass.getPackage().getName().split("\\.");
		return TestUtils.getTestArgsFor(packageParts[packageParts.length - 1], testClass.getSimpleName().replace("Test", "").toLowerCase());
	}
}
