plugins {
    id("java")
}

group = "dev.songpola.seiko"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    implementation("com.formdev:flatlaf:3.5.1")
    implementation("com.formdev:flatlaf-intellij-themes:3.5.1")
}

tasks.test {
    useJUnitPlatform()
}