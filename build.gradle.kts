plugins {
    id("java")
}

group = "com.michaeltroger"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.11.3"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(23))
    }
}
tasks.test {
    useJUnitPlatform()
}