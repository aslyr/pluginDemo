import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
plugins {
    java
    kotlin("jvm") version "1.3.50"
    application
}
application {
    mainClassName = "HelloKt"
}
group = "com.aslyr"
version = "1.0-SNAPSHOT"

repositories {
    maven(url="http://maven.aliyun.com/nexus/content/groups/public/")
    maven(url="https://jitpack.io")

}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    testCompile("junit", "junit", "4.12")
    // https://mvnrepository.com/artifact/org.apache.httpcomponents/httpclient
    //compile group: 'org.apache.httpcomponents', name: 'httpclient', version: '4.5.10'
    compile("cn.hutool:hutool-all:4.6.6")
    // https://mvnrepository.com/artifact/org.jetbrains.kotlin/kotlin-reflect
    compile (group= "org.jetbrains.kotlin", name="kotlin-reflect", version= "1.3.50")
    compile("com.github.shaunxiao:kotlinGameEngine:v0.0.4")
    compile("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.1")

}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}
tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
task("hello",{
    doFirst{
        println("hello")
    }
})