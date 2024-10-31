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
    constraints {
        implementation("org.apache.commons:commons-text:1.10.0") {
            because(
                """
                Dependency maven:org.apache.commons:commons-text:1.9 is vulnerable
                Upgrade to 1.10.0
                GHSA-599f-7c49-w659,  Score: 9.8
                Read More: https://osv.dev/vulnerability/GHSA-599f-7c49-w659
                """.trimIndent()
            )
        }
    }
}

application {
    mainClass = "dev.songpola.seiko.Main"
}

tasks.test {
    useJUnitPlatform()
}