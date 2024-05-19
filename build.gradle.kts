plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("com.google.cloud:google-cloud-aiplatform:3.35.0")
    implementation("com.google.cloud:google-cloud-vertexai:1.3.0")
}

tasks.test {
    useJUnitPlatform()
}