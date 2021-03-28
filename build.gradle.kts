plugins {
    idea
    java
    id("com.github.ben-manes.versions") version ("0.38.0")
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
        val moduleName: String by project
        inputs.property("moduleName", moduleName)

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
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
