plugins {
    id("java")
}

group = "ru.vtb.pptc"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.assertj:assertj-core:3.27.3")
    testImplementation("com.codeborne:selenide:7.12.0")
}

tasks.test {
    useJUnitPlatform()
    systemProperty("selenide.browser", "chrome")
    systemProperty("selenide.headless", "false")
    maxParallelForks = (Runtime.getRuntime().availableProcessors() /2 ).coerceAtLeast(1)
}