plugins {
    idea
    java
    id("com.github.ben-manes.versions") version ("0.39.0")
}

group = "fr.raksrinana"
description = "BattleDev"

dependencies {
    testImplementation(libs.reflections)

    testImplementation(libs.bundles.junit)
    testRuntimeOnly(libs.bundles.junitRuntime)

    testImplementation(libs.assertj)
}

repositories {
    mavenCentral()
}

tasks {
    processResources {
        expand(project.properties)
    }

    compileJava {
        options.encoding = "UTF-8"
        options.isDeprecation = true
    }

    test {
        useJUnitPlatform()
    }

    wrapper {
        val wrapperVersion: String by project
        gradleVersion = wrapperVersion
    }
}

java {
    toolchain{
        languageVersion.set(JavaLanguageVersion.of(11))
    }
}
