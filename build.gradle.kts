plugins {
    application
}

group = "dev.songpola.seiko"
version = "0.1.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    implementation("com.formdev:flatlaf:3.5.1")
    implementation("com.formdev:flatlaf-intellij-themes:3.5.1")
    implementation("com.opencsv:opencsv:5.5.2")
}

application {
    mainClass = "dev.songpola.seiko.Main"
}

tasks.test {
    useJUnitPlatform()
}