plugins {
    id("java")
}

group = "io.yeahx4"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "io.yeahx4.Main"
    }
}

tasks.test {
    useJUnitPlatform()
}